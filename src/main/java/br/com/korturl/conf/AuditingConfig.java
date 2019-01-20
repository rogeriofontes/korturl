/*
 * 
 */
package br.com.korturl.conf;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * The Class AuditingConfig.
 */
@EnableJpaAuditing
@Configuration
public class AuditingConfig {
	
	/**
	 * Creates the auditor provider.
	 *
	 * @return the auditor aware
	 */
	@Bean
	public AuditorAware<String> createAuditorProvider() {
		return new SecurityAuditor();
	}

	/**
	 * Creates the auditing listener.
	 *
	 * @return the auditing entity listener
	 */
	@Bean
	public AuditingEntityListener createAuditingListener() {
		return new AuditingEntityListener();
	}

	/**
	 * The Class SecurityAuditor.
	 */
	public static class SecurityAuditor implements AuditorAware<String> {
		
		/* (non-Javadoc)
		 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
		 */
		@Override
		public Optional<String> getCurrentAuditor() {
			return Optional.of("root@localhost");
		}
	}
}
