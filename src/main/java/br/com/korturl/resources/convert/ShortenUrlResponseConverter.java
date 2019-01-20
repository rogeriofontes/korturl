package br.com.korturl.resources.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.korturl.model.domain.Url;
import br.com.korturl.resources.dto.ShortenUrlResponse;

@Component
public class ShortenUrlResponseConverter implements Converter<Url, ShortenUrlResponse> {

	@Override
	public ShortenUrlResponse convert(Url source) {
		ShortenUrlResponse response = new ShortenUrlResponse();
		response.setUrl(source.getUrl());
		response.setUrlShort(source.getUrlKey());
		return response;
	}

}
