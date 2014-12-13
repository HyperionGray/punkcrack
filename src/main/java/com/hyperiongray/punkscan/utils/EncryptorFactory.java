package com.hyperiongray.punkscan.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptorFactory {

	/**
	 * MD2: The MD2 message digest algorithm as defined in RFC 1319.
	 * 
	 * MD5: The MD5 message digest algorithm as defined in RFC 1321.
	 * 
	 * SHA-1: The Secure Hash Algorithm, as defined in Secure Hash Standard,
	 * NIST FIPS 180-1.
	 * 
	 * SHA-256, SHA-384, and SHA-512: New hash algorithms for which the
	 * draft Federal Information Processing Standard 180-2, Secure Hash
	 * Standard (SHS) is now available. SHA-256 is a 256-bit hash function
	 * intended to provide 128 bits of security against collision attacks,
	 * while SHA-512 is a 512-bit hash function intended to provide 256 bits
	 * of security. A 384-bit hash may be obtained by truncating the SHA-512
	 * output.
	 */
	public static Encryptor getEncryptor(String encryptorCode) {

		MessageDigest md = null;
		Encryptor encryptorWrapper = new Encryptor();

		try {
			// if (encryptorCode.toUpperCase().equals("MD5")) {
			// md = MessageDigest.getInstance("MD5");
			// }
			md = MessageDigest.getInstance(encryptorCode.toUpperCase());

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("NoSuchAlgorithm!");
		}
		encryptorWrapper.setMessageDigest(md);
		return encryptorWrapper;
	}

	// public byte[] encodeMD5(byte[] input) {
	// try {
	// md = MessageDigest.getInstance("MD5");
	// } catch (NoSuchAlgorithmException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return md.digest(input);
	// }

	// private void loadEncryptorMap(){
	//
	//
	//
	// }
}
