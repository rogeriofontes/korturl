/*
 * 
 */
package br.com.korturl.service;

import br.com.korturl.resources.dto.ShortenUrlRequest;
import br.com.korturl.resources.dto.ShortenUrlResponse;

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
	ShortenUrlResponse findUrlByShortId(String key);
	
	/**
	 * Store url.
	 *
	 * @param shortenUrlDTO the shorten url DTO
	 * @return the shorten url response
	 */
	ShortenUrlResponse storeUrl(ShortenUrlRequest shortenUrlDTO);
}
