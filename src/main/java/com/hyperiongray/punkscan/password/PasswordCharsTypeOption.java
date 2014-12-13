package com.hyperiongray.punkscan.password;

public enum PasswordCharsTypeOption {

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
