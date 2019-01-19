package br.com.korturl.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.korturl.model.domain.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	Url findByUrlKey(String key);
	Url findByUrl(String url);
	
	@Query("select count(u) from Url u")
	Long countUrl();
}
