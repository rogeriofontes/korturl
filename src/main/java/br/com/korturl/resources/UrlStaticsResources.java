package br.com.korturl.resources;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.URLException;
import br.com.korturl.service.UrlStaticsService;

@RestController
@RequestMapping(path = "/api/v1")
public class UrlStaticsResources {

	private static final Logger LOGGER = LogManager.getLogger(UrlStaticsResources.class);

	@Autowired
	private UrlStaticsService urlStaticService;

	@GetMapping(path = "/statistics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Long getStatics() throws Exception {
		final Long total = urlStaticService.countUrl();
		if (total != null) {
			LOGGER.info("Url: " + total.toString());
			return total;
		} else {
			throw new URLException(Contants.CONT_IS_NOT_FOUND);
		}
	}

}
