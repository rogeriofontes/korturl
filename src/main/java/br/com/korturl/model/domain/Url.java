package br.com.korturl.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "url")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "urlKey", "url" })
@Data
public class Url extends AudityEntity {

	private static final long serialVersionUID = -2545117841829118399L;

	@NotNull(message = "The field urlKey is required")
	@Getter
	@Setter
	@Column(name = "url_key")
	private String urlKey;
	
	@NotNull(message = "The field url is required")
	@Getter
	@Setter
	private String url;	
}