package br.com.korturl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.korturl.model.repository.UrlRepository;
import br.com.korturl.service.UrlStaticsService;

@Service
public class UrlStaticsServiceImpl implements UrlStaticsService {

	@Autowired
	private UrlRepository urlRepository;

	@Override
	public Long countUrl() {
		return urlRepository.countUrl();
	}
}
