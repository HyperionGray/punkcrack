package com.hyperiongray.punkscan.utils;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class EncryptorTestSHA {

	@Test
	public void encryptorTest(){
		Encryptor encryptor = EncryptorFactory.getEncryptor("SHA1");
		String pwd = "password";
		byte[] encode = encryptor .encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8", bytesToHex);
	}

	@Test
	public void encryptorTest_256(){
		Encryptor encryptor = EncryptorFactory.getEncryptor("SHA-256");
		String pwd = "password";
		byte[] encode = encryptor .encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("5E884898DA28047151D0E56F8DC6292773603D0D6AABBDD62A11EF721D1542D8", bytesToHex);
	}

	@Test
	public void encryptorTest_384(){
		Encryptor encryptor = EncryptorFactory.getEncryptor("SHA-384");
		String pwd = "password";
		byte[] encode = encryptor .encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("A8B64BABD0ACA91A59BDBB7761B421D4F2BB38280D3A75BA0F21F2BEBC45583D446C598660C94CE680C47D19C30783A7", bytesToHex);
	}

	@Test
	public void encryptorTest_512(){
		Encryptor encryptor = EncryptorFactory.getEncryptor("SHA-512");
		String pwd = "password";
		byte[] encode = encryptor .encode(pwd.getBytes());
		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
		Assert.assertEquals("B109F3BBBC244EB82441917ED06D618B9008DD09B3BEFD1B5E07394C706A8BB980B1D7785E5976EC049B46DF5F1326AF5A2EA6D103FD07C95385FFAB0CACBC86", bytesToHex);
	}

	
//	@Test
//	public void MD5EncryptorTest_2(){
//		String pwd = "password";
//		Encryptor encryptor = EncryptorFactory.getEncryptor("SHA");
//		byte[] encode = encryptor.encode(pwd.getBytes());
//		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
//		Assert.assertEquals("5F4DCC3B5AA765D61D8327DEB882CF99", bytesToHex);
//	}
//
//	@Test
//	public void MD5EncryptorTest_3(){
//		String pwd = "passw";
//		Encryptor encryptor = EncryptorFactory.getEncryptor("SHA");
//		byte[] encode = encryptor.encode(pwd.getBytes());
//		String bytesToHex = HexConverter.bytesToHex(encode); //[72, 44, -127, 29, -91, -43, -76, -68, 109, 73, 127, -6, -104, 73, 30, 56]
//		Assert.assertEquals("D79096188B670C2F81B7001F73801117", bytesToHex);
//	
//	}
	
	
	

}
