package com.hyperiongray.punkscan.utils;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EncryptorTestMD5 {

	@Test
	public void encryptorTest_5(){
		Encryptor encryptor = EncryptorFactory.getEncryptor("MD5");
		String pwd = "password123";
		byte[] encode = encryptor .encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("482C811DA5D5B4BC6D497FFA98491E38", bytesToHex);
	}
	
	@Test
	public void encryptorTest_51(){
		String pwd = "password";
		Encryptor encryptor = EncryptorFactory.getEncryptor("MD5");
		byte[] encode = encryptor.encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("5F4DCC3B5AA765D61D8327DEB882CF99", bytesToHex);
	}

	@Test
	public void encryptorTest_52(){
		String pwd = "passw";
		Encryptor encryptor = EncryptorFactory.getEncryptor("MD5");
		byte[] encode = encryptor.encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("D79096188B670C2F81B7001F73801117", bytesToHex);
	}
	
	@Test
	public void encryptorTest_2(){
		String pwd = "password";
		Encryptor encryptor = EncryptorFactory.getEncryptor("MD2");
		byte[] encode = encryptor.encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("f03881a88c6e39135f0ecc60efd609b9".toUpperCase(), bytesToHex);
	}

}
