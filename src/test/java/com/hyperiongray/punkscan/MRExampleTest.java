package com.hyperiongray.punkscan;


public class MRExampleTest {

	
//	@Test
	public void mainTestMD5(){
		
		String[] args = new String[12];
		args[0]="-basedir";
		args[1]="/home/tomas/Proyecto/PunkCrack/workspace/punkcrack/hadoop/ ";
		args[2]="-charsetcode";
		args[3]="1000";
		args[4]="-minpasswordlenght";
		args[5]="1";
		args[6]="-maxpasswordlenght";
		args[7]="5";
		args[8]="-encryptiontype";
		args[9]="md5";
		args[10]="-hash";
//		args[11]="EF79B95D8C277D2B3EC3B0AF26AC595C";
		args[11]="D79096188B670C2F81B7001F73801117"; //"passw";
		
		
		try {
			MRexample.main(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args){

		MRExampleTest mrExampleTest = new MRExampleTest();
		mrExampleTest.mainTestMD5();
	}
	
}

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
