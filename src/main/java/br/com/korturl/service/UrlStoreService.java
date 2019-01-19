package br.com.korturl.service;

import br.com.korturl.resources.dto.ShortenUrlRequest;
import br.com.korturl.resources.dto.ShortenUrlResponse;

public interface UrlStoreService {
	ShortenUrlResponse findUrlByShortId(String key);
	ShortenUrlResponse storeUrl(ShortenUrlRequest shortenUrlDTO);
}
