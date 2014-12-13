package com.hyperiongray.punkscan.password;

import java.util.Arrays;

import com.hyperiongray.punkscan.utils.CharsetUtil;
import com.hyperiongray.punkscan.utils.PasswordCharsTypesHelper;

public class CharsetIterator  extends AbstractCharsetIterator{

	private final char[] passwordGuessFirst;
//	private final char[] passwordGuessLast;
	
	public CharsetIterator(char[] charset, int passwordLength, String passwordGuessFirst) {
		super(charset, passwordLength);
		this.passwordGuessFirst = passwordGuessFirst.toCharArray();
//		this.passwordGuessLast = passwordGuessLast.toCharArray();
		super.initialize();		
	}

	@Override
	public void setMetapositions(){
		char[] passwordAsCharArray = passwordGuessFirst;
//		metaPositions[0]=0; //--> un uno aca significa que termino
		for (int i = 0; i < passwordAsCharArray.length; i++) {
			metaPositions[i] = CharsetUtil.findPositionInArray(passwordAsCharArray[i], charset);
		}
	}


//	@Override
//	public boolean hasNext() {
//		return _hasNext;// && currentIteration <= numberOfIterations;
//	}

//	public char[] nextUntil(char[] passwordGuessLast) {
//		char[] output = next();
//		if (Arrays.equals(output, this.passwordGuessLast)) {
//			_hasNext = false;
//		}
//		return output;
//	}

	public static void main(String[] args) {

//		main1(null);
		System.out.println("-------------------------");
//		main2(null);
		
		System.out.println("-------------------------");
		main3(null);
	}
		
	public static void main1(String[] args) {
		char[] charset = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode("1000").toCharArray();
		String passwordGuessFirst ="aa";
		String passwordGuessLast = "ac";
		CharsetIterator charsetIterator = new CharsetIterator(charset, passwordGuessFirst.length(),passwordGuessFirst);
		while (charsetIterator.hasNext()) {
			char[] next = charsetIterator.next();
			System.out.println(new String(next));
			if (Arrays.equals(next, passwordGuessLast.toCharArray())) {
				break;
			}
		}
	}

	public static void main2(String[] args) {
		char[] charset = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode("1000").toCharArray();
		String passwordGuessFirst ="aa";
//		String passwordGuessLast = "ac";
		CharsetIterator charsetIterator = new CharsetIterator(charset, passwordGuessFirst.length(),passwordGuessFirst);
		while (charsetIterator.hasNext()) {
			char[] next = charsetIterator.next();
			System.out.println(new String(next));
		}
	}
	
	public static void main3(String[] args) {
		char[] charset = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode("1000").toCharArray();
		String passwordGuessFirst ="aa";
//		String passwordGuessLast = "ac";
		while(passwordGuessFirst.length()<10){
			CharsetIterator charsetIterator = new CharsetIterator(charset, passwordGuessFirst.length(),passwordGuessFirst);
			while (charsetIterator.hasNext()) {
				char[] next = charsetIterator.nextBigStep();
				System.out.println(new String(next));
			}
			passwordGuessFirst = passwordGuessFirst+"a";
		}
	}


//	public char[] nextUntil(char[] passwordGuessLast) {
//	char[] output = next();
//	if (Arrays.equals(output, this.passwordGuessLast)) {
//		_hasNext = false;
//	}
//	return output;
//}

}
