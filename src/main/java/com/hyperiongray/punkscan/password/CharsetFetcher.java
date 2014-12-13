package com.hyperiongray.punkscan.password;


public class CharsetFetcher extends AbstractCharsetIterator {

	public CharsetFetcher(char[] charset, int passwordLength) {
		super(charset, passwordLength);
	}

	@Override
	protected void setMetapositions() {
		super.setMetapositionsToFirst();
	}


	
}