package br.com.korturl.resources.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "url" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortenUrlRequest {
  
	@NotNull
    @Size(min = 5, max = 1024)
	@Getter
	@Setter
    private String url;

}
