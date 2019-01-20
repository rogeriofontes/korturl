/*
 * 
 */
package br.com.korturl.util;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.UtilConstructException;

/**
 * The Class NetworkUtils.
 */
public class NetworkUtils {
	
	/**
	 * Instantiates a new network utils.
	 */
	private NetworkUtils() {
		throw new UtilConstructException(Contants.CLASS_CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Checks if is url valid.
	 *
	 * @param url the url
	 * @return true, if is url valid
	 */
	public static boolean isUrlValid(String url) {
		boolean valid = true;
		try {
			new URL(url);
		} catch (MalformedURLException e) {
			valid = false;
		}
		return valid;
	}
}
