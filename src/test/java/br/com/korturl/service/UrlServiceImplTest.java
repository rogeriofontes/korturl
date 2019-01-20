package br.com.korturl.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.korturl.exception.URLException;
import br.com.korturl.model.domain.Url;
import br.com.korturl.model.repository.UrlRepository;
import br.com.korturl.service.impl.UrlStoreServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceImplTest {

	@InjectMocks
	UrlStoreServiceImpl urlStoreService;

	@Mock
	UrlRepository urlRepository;
	
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllEmployeesTest() {
		List<Url> list = new ArrayList<>();

		Url url1 = new Url("233344", "http://");
		url1.setCreateBy("root@localhost");
		url1.setCreatedDate(new Date());

		Url url2 = new Url("233344", "http://");
		url2.setCreateBy("root@localhost");
		url2.setCreatedDate(new Date());

		Url url3 = new Url("233344", "http://");
		url3.setCreateBy("root@localhost");
		url3.setCreatedDate(new Date());

		list.add(url1);
		list.add(url2);
		list.add(url3);

		when(urlRepository.findAll()).thenReturn(list);

		// test
		List<Url> urls = urlStoreService.listAll();

		assertEquals(3, urls.size());
		verify(urlRepository, times(1)).findAll();
	}

	@Test
	public void getUrlByIdTest() {
		when(urlRepository.findById(1L)).thenReturn(Optional.of(new Url("233344", "http://")));

		Url url = urlStoreService.findById(1L);

		assertEquals("233344", url.getUrlKey());
		assertEquals("http://", url.getUrl());
	}

	@Test
	public void getFindUrlByShortIdTest() {
		Url url = new Url("233344", "http://");
		when(urlStoreService.findUrlByShortId("233344")).thenReturn(url);

		Url result = urlStoreService.findUrlByShortId("233344");

		assertEquals("http://", result.getUrl());
		assertEquals("233344", result.getUrlKey());
	}

	@Test
	public void createUrlTest() {
		Url url = new Url("233344", "http://");
		urlStoreService.save(url);

		verify(urlRepository, times(1)).save(url);
	}

	@Test
	public void createAndStoreUrlTest() {
		Url url = new Url("233344", "http://");
		urlStoreService.save(url);

		when(urlStoreService.storeUrl(url)).thenReturn(url);
		Url result = urlStoreService.storeUrl(url);

		assertEquals("http://", result.getUrl());
	}
	
	@Test(expected = URLException.class)
	public void createAndStoreUrlTest_and_should_throw_constraint_violation_execption() {
		thrown.expect(URLException.class);
		thrown.expectMessage("Url is not valid!");
		
		Url url = new Url(null, null);
		urlStoreService.save(url);

		when(urlStoreService.storeUrl(url)).thenReturn(url);
		Url result = urlStoreService.storeUrl(url);

		assertEquals("http://", result.getUrl());
	}

}
