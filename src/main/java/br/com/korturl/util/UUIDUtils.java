/*
 * 
 */
package br.com.korturl.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.UUID;

import br.com.korturl.constants.Contants;
import br.com.korturl.exception.UtilConstructException;

/**
 * The Class UUIDUtils.
 */
public class UUIDUtils {
	
	/**
	 * Instantiates a new UUID utils.
	 */
	private UUIDUtils() {
		throw new UtilConstructException(Contants.CLASS_CANNOT_BE_INSTANTIATED);
	}

	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public static UUID getUUID() {
		return UUID.randomUUID();
	}

	/**
	 * Gets the short UUID.
	 *
	 * @return the short UUID
	 */
	public static String getShortUUID() {
		Encoder encoder = Base64.getEncoder();

		byte[] src = ByteBuffer.wrap(new byte[16]).putLong(getUUID().getMostSignificantBits())
				.putLong(getUUID().getLeastSignificantBits()).array();

		return encoder.encodeToString(src).substring(0, 8);
	}
}