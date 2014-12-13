package com.hyperiongray.punkscan.utils;

import java.util.Calendar;
import java.util.List;

import com.google.common.collect.Lists;

public class CharsetUtil {

	public static int findPositionInArray(char element, char[] array) {
		for (int i = 0; i < array.length; i++) {
			if (element == array[i]) {
				return i;
			}
		}
		System.out.println("Warning!!!!- char not found!!");
		return -1;
	}

}
