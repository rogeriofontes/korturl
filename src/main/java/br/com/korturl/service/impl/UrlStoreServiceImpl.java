/*
 * 
 */
package br.com.korturl.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.URLException;
import br.com.korturl.model.domain.Url;
import br.com.korturl.model.repository.UrlRepository;
import br.com.korturl.service.UrlStoreService;
import br.com.korturl.util.NetworkUtils;

/**
 * The Class UrlStoreServiceImpl.
 */
@Service
public class UrlStoreServiceImpl implements UrlStoreService {

	/** The url repository. */
	@Autowired
	private UrlRepository urlRepository;

	/* (non-Javadoc)
	 * @see br.com.korturl.service.UrlStoreService#findUrlByShortId(java.lang.String)
	 */
	@Override
	@Cacheable("urlsInCache")
	public Url findUrlByShortId(String key) {
		return urlRepository.findByUrlKey(key);
	}

	/* (non-Javadoc)
	 * @see br.com.korturl.service.UrlStoreService#storeUrl(br.com.korturl.model.domain.Url)
	 */
	@Override
	public Url storeUrl(Url url) {
		Url response = new Url();

		Url findedUrl = urlRepository.findByUrl(url.getUrl());
		if (findedUrl != null) {
			throw new URLException(Contants.URL_FOUNDED);
		} else {
			if (StringUtils.isNotEmpty(url.getUrl()) && NetworkUtils.isUrlValid(url.getUrl())) {
				response = urlRepository.save(url);
			} else {
				throw new URLException(Contants.URL_IS_NOT_VALID);
			}
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see br.com.korturl.service.UrlStoreService#save(br.com.korturl.model.domain.Url)
	 */
	@Override
	public Url save(Url url) {
		return urlRepository.save(url);
	}

	/* (non-Javadoc)
	 * @see br.com.korturl.service.UrlStoreService#listAll()
	 */
	@Override
	public List<Url> listAll() {
		return urlRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see br.com.korturl.service.UrlStoreService#findById(java.lang.Long)
	 */
	@Override
	public Url findById(Long id) {
		Optional<Url> url = urlRepository.findById(id);
		if (url.isPresent()) {
			return url.get();
		}
		return null;
	}
}
