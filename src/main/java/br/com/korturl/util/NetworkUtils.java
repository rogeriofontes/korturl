package br.com.korturl.util;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.UtilConstructException;

public class NetworkUtils {
	
	private NetworkUtils() {
		throw new UtilConstructException(Contants.CLASS_CANNOT_BE_INSTANTIATED);
	}

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
