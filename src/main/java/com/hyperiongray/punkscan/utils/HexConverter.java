package com.hyperiongray.punkscan.utils;

import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class HexConverter {

	public static String convertStringToHex(String str) {

		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}

		return hex.toString();
	}

	public static String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		// 49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for (int i = 0; i < hex.length() - 1; i += 2) {

			// grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			// convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			// convert the decimal to character
			sb.append((char) decimal);

			temp.append(decimal);
		}
		System.out.println("Decimal : " + temp.toString());

		return sb.toString();
	}

	final protected static char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	public static byte[] getCharArrayAsByteArray(char[] chars) {
		// byte[] bytes = new byte[chars.length * 2];
		// for (int i = 0; i < chars.length; i++) {
		// bytes[i * 2] = (byte) (chars[i] >> 8);
		// bytes[i * 2 + 1] = (byte) chars[i];
		// }
		// return bytes;
		return Charset.forName("ASCII").encode(CharBuffer.wrap(chars)).array();
	}

	public String charArraytoString(char[] chars){
		return new String(chars);
		}
	
	public static void main(String[] args) {

		// HexConverter strToHex = new HexConverter();
		System.out.println("\n***** Convert ASCII to Hex *****");
		// String str = "I Love Java!";
		String str = "passwo";
		System.out.println("Original input : " + str);

		String hex = HexConverter.convertStringToHex(str);

		System.out.println("Hex : " + hex);

		System.out.println("\n***** Convert Hex to ASCII *****");
		System.out.println("Hex : " + hex);
		System.out.println("ASCII : " + HexConverter.convertHexToString(hex));

		System.out.println("Bytes :"
				+ new String(HexConverter.getCharArrayAsByteArray(str.toCharArray()), Charset.forName("UTF-8")));

	}
	
	public static void mainLong(String[] args) {

		// HexConverter strToHex = new HexConverter();
		System.out.println("\n***** Convert ASCII to Hex *****");
		// String str = "I Love Java!";
		String str = "password123";
		System.out.println("Original input : " + str);

		String hex = HexConverter.convertStringToHex(str);

		System.out.println("Hex : " + hex);

		System.out.println("\n***** Convert Hex to ASCII *****");
		System.out.println("Hex : " + hex);
		System.out.println("ASCII : " + HexConverter.convertHexToString(hex));

		System.out.println("Bytes :"
				+ new String(HexConverter.getCharArrayAsByteArray("password123".toCharArray()), Charset.forName("UTF-8")));

	}
}
