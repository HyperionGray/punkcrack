package com.hyperiongray.punkscan.utils;

import java.util.Calendar;
import java.util.List;

import com.google.common.collect.Lists;

public class CharsetUtil {

//	private Boolean hasNext;
//	private int passwordLength;
//	private char[] charset;
//	private int[] metaPositions;
//
//	public CharsetUtil(int passwordLength, char[] charset, int[] metaPositions, Boolean hasNext){
//		this.passwordLength = passwordLength;
//		this.charset = charset;
//		this.metaPositions = metaPositions;
//		this.hasNext = hasNext;
//	}
	
	public static int findPositionInArray(char element, char[] array) {
		for (int i = 0; i < array.length; i++) {
			if (element == array[i]) {
				return i;
			}
		}
		System.out.println("Warning!!!!- char not found!!");
		return -1;
	}


//	public static void main2(String[] args) {
//
//		int passwordLength = 4;
//		System.out.println(Calendar.getInstance().getTimeInMillis());
//		String charsetCode = "1000";
//		char[] charset = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode(charsetCode).toCharArray();
//
//		int[] metaPositions = new int[passwordLength];
//		for (int i = 0; i < passwordLength; i++) {
//			metaPositions[i] = 0;
//		}
//
//		Boolean _hasNext = true;
//		int increment = 1000000;
//		System.out.println(getCurrentCharset(charset, passwordLength, metaPositions));
//
//		shiftPosition(passwordLength, charset, metaPositions, _hasNext, increment);
//
//		System.out.println(getCurrentCharset(charset, passwordLength, metaPositions));
//		System.out.println(Calendar.getInstance().getTimeInMillis());
//
//	}

}
