package com.hyperiongray.punkscan.utils;

import java.security.MessageDigest;

public class Encryptor {

	private MessageDigest md = null;


	public byte[] encode(byte[] input) {
		return md.digest(input);
	}

	public void setMessageDigest(MessageDigest md) {
		this.md = md;
	}

}

