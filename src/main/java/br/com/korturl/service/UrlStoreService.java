/*
 * 
 */
package br.com.korturl.service;

import java.util.List;

import br.com.korturl.model.domain.Url;

// TODO: Auto-generated Javadoc
/**
 * The Interface UrlStoreService.
 */
public interface UrlStoreService {
	
	/**
	 * Find url by short id.
	 *
	 * @param key the key
	 * @return the shorten url response
	 */
	Url findUrlByShortId(String key);
	
	/**
	 * Store url.
	 *
	 * @param url the url
	 * @return the shorten url response
	 */
	Url storeUrl(Url url);
	
	/**
	 * Save.
	 *
	 * @param url the url
	 * @return the url
	 */
	Url save(Url url);
	
	/**
	 * List all.
	 *
	 * @return the list
	 */
	List<Url> listAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the url
	 */
	Url findById(Long id);
}
