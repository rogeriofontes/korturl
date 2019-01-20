/*
 * 
 */
package br.com.korturl.resources;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.URLException;
import br.com.korturl.model.domain.Url;
import br.com.korturl.resources.dto.ShortenUrlRequest;
import br.com.korturl.resources.dto.ShortenUrlResponse;
import br.com.korturl.service.UrlStoreService;

/**
 * The Class UrlResources.
 */
@RestController
@RequestMapping(path = "/api/v1/urls")
public class UrlResources {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(UrlResources.class);

	/** The url store service. */
	@Autowired
	private UrlStoreService urlStoreService;

	@Autowired
	private ConversionService conversionService;

	/**
	 * Shorten url.
	 *
	 * @param shortenUrlDTO
	 *            the shorten url DTO
	 * @return the shorten url response
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ShortenUrlResponse> shortenUrl(@Valid @RequestBody ShortenUrlRequest shortenUrlDTO) {
	
		LOGGER.info("Url: " + shortenUrlDTO.toString());
		Url urlRequest = conversionService.convert(shortenUrlDTO, Url.class);
		Url urlStored = urlStoreService.storeUrl(urlRequest);
		
		ShortenUrlResponse response = conversionService.convert(urlStored, ShortenUrlResponse.class);
		return new ResponseEntity<ShortenUrlResponse>(response, HttpStatus.CREATED);
	}

	/**
	 * Redirect to url.
	 *
	 * @param key
	 *            the key
	 * @return the shorten url response
	 * @throws Exception
	 *             the exception
	 */
	@GetMapping(path = "/{key}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "urlsInCache", allEntries = true)
	public ResponseEntity<ShortenUrlResponse> redirectToUrl(@PathVariable String key) throws Exception {

		Url urlResponse = urlStoreService.findUrlByShortId(key);
		ShortenUrlResponse response = conversionService.convert(urlResponse, ShortenUrlResponse.class);
		if (response == null) {
			throw new URLException(Contants.URL_IS_NOT_FOUND);
		}

		LOGGER.info("Url: " + response.toString());
		return new ResponseEntity<ShortenUrlResponse>(response, HttpStatus.OK);
	}

}
