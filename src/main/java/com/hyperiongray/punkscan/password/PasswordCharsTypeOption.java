package com.hyperiongray.punkscan.password;

public enum PasswordCharsTypeOption {
	// LOWER(0,new char[]
	// {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}),
	// UPPER(1,new char[]
	// {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}),
	// DIGITS(2,new char[] {'0','1','2','3','4','5','6','7','8','9'}),
	// SYMBOLS(3,new char[] {'!','#','$','@','&'}) ;

	LOWER(0, "abcdefghijklmnopqrstuvwxyz"), UPPER(1, "ABCDEFGHIJKLMNOPQRSTUVWXYZ"), DIGITS(2,"0123456789"), SYMBOLS(3, "!#$@&")	;

	private int code;
	private String chars;

	PasswordCharsTypeOption(int code, String chars) {
		this.code = code;
		this.chars = chars;
	}

	public int getCode() {
		return this.code;
	}

	public char[] getChars() {
		return this.chars.toCharArray();
	}

	public String getString() {
		return this.chars;
	}

}
