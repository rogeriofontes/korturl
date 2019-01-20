/*
 * 
 */
package br.com.korturl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.korturl.model.repository.UrlRepository;
import br.com.korturl.service.UrlStaticsService;

/**
 * The Class UrlStaticsServiceImpl.
 */
@Service
public class UrlStaticsServiceImpl implements UrlStaticsService {

	/** The url repository. */
	@Autowired
	private UrlRepository urlRepository;

	/* (non-Javadoc)
	 * @see br.com.korturl.service.UrlStaticsService#countUrl()
	 */
	@Override
	public Long countUrl() {
		return urlRepository.countUrl();
	}
}
