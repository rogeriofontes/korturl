package br.com.korturl.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.korturl.model.domain.Url;
import br.com.korturl.service.UrlStoreService;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlResources.class)
public class UrlResourcesTest {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(UrlResourcesTest.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UrlStoreService service;

	@Test
	public void list_urls_and_thenStatus200_and_short_url() throws Exception {
		String key = "OnwOm0fg";
		String response = "{\"url\":\"http://\",\"urlShort\":\"OnwOm0fg\"}";

		Url url = new Url("OnwOm0fg", "http://");
		given(service.findUrlByShortId("OnwOm0fg")).willReturn(url);

		mockMvc.perform(get("/api/v1/urls/{key}", key)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(response));
	}
	
	@Test
	public void givenEmployees_whenGetEmployees_thenStatus201() throws Exception {

		Url url = new Url("OnwOm0fg", "http://www.fallingfalling.com/");
		when(this.service.save(url)).thenReturn(url);
		
		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/v1/urls")
				.accept(MediaType.APPLICATION_JSON).content("{\"url\": \"http://www.fallingfalling.com/\"}")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		String content = response.getContentAsString();
		LOGGER.info(content);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
}
