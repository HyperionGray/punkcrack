package com.hyperiongray.punkscan.utils;

import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.hyperiongray.punkscan.password.PasswordCharsTypeOption;

public class PasswordCharsTypesHelper {

	// max - min, por la cant de combinacion de cada numero en el
	// medio 16>8 + 16>9
	public static BigDecimal getMaxCombinations(List<PasswordCharsTypeOption> passwordCharsTypeOptions, final int passwordLengthMin,
			final int passwordLengthMax) {

		Integer validCharsNumber = 0;
		for (PasswordCharsTypeOption passwordCharsTypeOption : passwordCharsTypeOptions) {
			validCharsNumber = validCharsNumber + passwordCharsTypeOption.getChars().length;
		}

		double combinationAcum = 0;
		for (Integer i = passwordLengthMin; i < passwordLengthMax; i++) {
			combinationAcum = combinationAcum + Math.pow(validCharsNumber.doubleValue(), i.doubleValue());
		}
		return new BigDecimal(combinationAcum);
	}

	// lower|upper |number|symbols
	// 0 | 0| 0 |0
	public static String getCode(List<PasswordCharsTypeOption> passwordCharsTypeOptions) {

		StringBuilder sb = new StringBuilder();

		if (passwordCharsTypeOptions.contains(PasswordCharsTypeOption.LOWER)) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		if (passwordCharsTypeOptions.contains(PasswordCharsTypeOption.UPPER)) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		if (passwordCharsTypeOptions.contains(PasswordCharsTypeOption.DIGITS)) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		if (passwordCharsTypeOptions.contains(PasswordCharsTypeOption.SYMBOLS)) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		return sb.toString();
	}

	public static List<PasswordCharsTypeOption> getPasswordCharsTypeOptionsByCharsTypeOptionCode(String str) {

		List<PasswordCharsTypeOption> passwordCharsTypeOptions = Lists.newArrayList();
		if (str.charAt(0) == '1') {
			passwordCharsTypeOptions.add(PasswordCharsTypeOption.LOWER);
		}
		if (str.charAt(1) == '1') {
			passwordCharsTypeOptions.add(PasswordCharsTypeOption.UPPER);
		}
		if (str.charAt(2) == '1') {
			passwordCharsTypeOptions.add(PasswordCharsTypeOption.DIGITS);
		}
		if (str.charAt(3) == '1') {
			passwordCharsTypeOptions.add(PasswordCharsTypeOption.SYMBOLS);
		}

		return passwordCharsTypeOptions;
	}
	
	public static String getCharsByCharsTypeOptionCode(String str) {

		StringBuilder sb = new StringBuilder();
		for (PasswordCharsTypeOption passwordCharsTypeOption : getPasswordCharsTypeOptionsByCharsTypeOptionCode(str)) {
			sb.append(passwordCharsTypeOption.getString());
		}
		return sb.toString();
	}
	

	public static void main(String[] args) {

		List<PasswordCharsTypeOption> passwordCharsTypeOptions = Lists.newArrayList(PasswordCharsTypeOption.LOWER,
				PasswordCharsTypeOption.UPPER, PasswordCharsTypeOption.DIGITS, PasswordCharsTypeOption.SYMBOLS);

		String code = PasswordCharsTypesHelper.getCode(passwordCharsTypeOptions);
		BigDecimal maxCombinations = PasswordCharsTypesHelper.getMaxCombinations(passwordCharsTypeOptions, 8, 14);
		
		String charsByCharsTypeOptionCode = PasswordCharsTypesHelper.getCharsByCharsTypeOptionCode("1111");

		System.out.println(code);
		System.out.println(maxCombinations);
		System.out.println(charsByCharsTypeOptionCode);
	}
}
