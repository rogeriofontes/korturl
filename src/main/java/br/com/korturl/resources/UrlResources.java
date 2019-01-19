package br.com.korturl.resources;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.URLException;
import br.com.korturl.resources.dto.ShortenUrlRequest;
import br.com.korturl.resources.dto.ShortenUrlResponse;
import br.com.korturl.service.UrlStoreService;

@RestController
@RequestMapping(path = "/api/v1")
public class UrlResources {

	private static final Logger LOGGER = LogManager.getLogger(UrlResources.class);

	@Autowired
	private UrlStoreService urlStoreService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ShortenUrlResponse shortenUrl(@Valid @RequestBody ShortenUrlRequest shortenUrlDTO) {
		LOGGER.info("Url: " + shortenUrlDTO.toString());
		return urlStoreService.storeUrl(shortenUrlDTO);
	}

	@GetMapping(path = "/{key}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "urlsInCache", allEntries = true)
	public ShortenUrlResponse redirectToUrl(@PathVariable String key) throws Exception {
		final ShortenUrlResponse urlResponse = urlStoreService.findUrlByShortId(key);
		if (urlResponse != null) {
			LOGGER.info("Url: " + urlResponse.toString());
			return urlResponse;
		} else {
			throw new URLException(Contants.URL_IS_NOT_FOUND);
		}
	}

}
