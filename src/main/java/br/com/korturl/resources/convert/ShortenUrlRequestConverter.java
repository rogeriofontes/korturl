package br.com.korturl.resources.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.korturl.model.domain.Url;
import br.com.korturl.resources.dto.ShortenUrlRequest;
import br.com.korturl.util.UUIDUtils;

@Component
public class ShortenUrlRequestConverter implements Converter<ShortenUrlRequest, Url> {

	@Override
	public Url convert(ShortenUrlRequest value) {
		String urlKey = UUIDUtils.getShortUUID();
		String url = value.getUrl();
		return new Url(urlKey, url);
	}

}
