package br.com.korturl.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.URLException;
import br.com.korturl.model.domain.Url;
import br.com.korturl.model.repository.UrlRepository;
import br.com.korturl.resources.dto.ShortenUrlRequest;
import br.com.korturl.resources.dto.ShortenUrlResponse;
import br.com.korturl.service.UrlStoreService;
import br.com.korturl.util.NetworkUtils;
import br.com.korturl.util.UUIDUtils;

@Service
public class UrlStoreServiceImpl implements UrlStoreService {

	@Autowired
	private UrlRepository urlRepository;

	@Override
	@Cacheable("urlsInCache")
	public ShortenUrlResponse findUrlByShortId(String key) {
		Url url = urlRepository.findByUrlKey(key);
		return new ShortenUrlResponse(url.getUrl(), url.getUrlKey());
	}

	@Override
	public ShortenUrlResponse storeUrl(ShortenUrlRequest shortenUrlDTO) {
		ShortenUrlResponse response = new ShortenUrlResponse();
		String sortUrl = UUIDUtils.getShortUUID();
		String url = shortenUrlDTO.getUrl();

		Url findedUrl = urlRepository.findByUrl(url);
		if (findedUrl != null) {
			response = new ShortenUrlResponse(findedUrl.getUrl(), findedUrl.getUrlKey());
		} else {
			if (StringUtils.isNotEmpty(url) && NetworkUtils.isUrlValid(url)) {
				Url u = new Url();
				u.setUrl(url);
				u.setUrlKey(sortUrl);
				Url savedUrl = urlRepository.save(u);
				response = new ShortenUrlResponse(savedUrl.getUrl(), savedUrl.getUrlKey());
			} else {
				throw new URLException(Contants.URL_IS_NOT_VALID);
			}
		}

		return response;
	}
}
