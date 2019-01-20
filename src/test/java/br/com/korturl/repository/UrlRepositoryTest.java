package br.com.korturl.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.korturl.model.domain.Url;
import br.com.korturl.model.repository.UrlRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ImportAutoConfiguration(exclude = FlywayAutoConfiguration.class)
public class UrlRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UrlRepository urlRepository;
	
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void should_find_no_urls_if_repository_is_empty() {
		Iterable<Url> seeds = urlRepository.findAll();

		assertThat(seeds).isEmpty();
	}

	@Test
	public void should_store_a_url() {
		Url url = urlRepository.save(new Url("233344", "http://"));

		assertThat(url).hasFieldOrPropertyWithValue("url","http://");
		assertThat(url).hasFieldOrPropertyWithValue("urlKey","233344");
	}

	@Test(expected = ConstraintViolationException.class)
	public void should_throw_constraint_violation_execption_url_is_null() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("The field url is required");
		thrown.expectMessage("must not be null");
		Url url = new Url("http://", null);
		entityManager.persistAndFlush(url);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void should_throw_constraint_violation_execption_url_key_is_null() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("The field urlKey is required");
		thrown.expectMessage("must not be null");
		Url url = new Url(null, "12345");
		entityManager.persistAndFlush(url);
	}

	@Test
	public void should_found_store_a_url() {
		Url url = new Url("233344", "http://");
		url.setCreateBy("root@localhost");
		url.setCreatedDate(new Date());
		entityManager.persist(url);
		
		Url found = urlRepository.findByUrl(url.getUrl());
		assertThat(found.getUrl()).isEqualTo(url.getUrl());
	}
	
	@Test
	public void should_found_null_url() {
		Url fromDb = urlRepository.findByUrl("doesNotExist");
		assertThat(fromDb).isNull();
	}
	
	@Test
	public void whenFindById_thenReturnUrl() {
		Url url = new Url("233344", "http://");
		url.setCreateBy("root@localhost");
		url.setCreatedDate(new Date());
		entityManager.persistAndFlush(url);

		Url fromDb = urlRepository.findById(url.getId()).orElse(null);
		assertThat(fromDb.getUrl()).isEqualTo(url.getUrl());
	}

	@Test
	public void whenInvalidId_thenReturnNull() {
		Url fromDb = urlRepository.findById(-11l).orElse(null);
		assertThat(fromDb).isNull();
	}

	@Test
	public void givenSetOfUrls_whenFindAll_thenReturnAllUrls() {
		Url url1 = new Url("233344", "http://");
		url1.setCreateBy("root@localhost");
		url1.setCreatedDate(new Date());

		Url url2 = new Url("233344", "http://");
		url2.setCreateBy("root@localhost");
		url2.setCreatedDate(new Date());
		
		Url url3 = new Url("233344", "http://");
		url3.setCreateBy("root@localhost");
		url3.setCreatedDate(new Date());
	
		entityManager.persist(url1);
		entityManager.persist(url2);
		entityManager.persist(url3);
		entityManager.flush();

		List<Url> allUrls = urlRepository.findAll();

		assertThat(allUrls).hasSize(3).extracting(Url::getUrl).containsOnly(url1.getUrl(), url2.getUrl(), url3.getUrl());
	}
}
