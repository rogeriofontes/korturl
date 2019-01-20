/*
 * 
 */
package br.com.korturl.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * The Class CacheConfig.
 */
@EnableCaching
@Configuration
public class CacheConfig {

	/**
	 * Urls cache.
	 *
	 * @return the caffeine cache
	 */
	@Bean
	public CaffeineCache urlsCache() {
		return new CaffeineCache("urlsInCache",
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}
}
