package br.com.korturl.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "url", "urlShort" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortenUrlResponse {

	@Getter
	@Setter
	private String url;

	@Getter
	@Setter
	private String urlShort;

}