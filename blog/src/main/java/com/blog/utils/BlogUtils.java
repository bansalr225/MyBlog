package com.blog.utils;
import java.security.SecureRandom;

/**
 * Utility class for generating Universal Unique Identifiers and for numerical validations
 */
public class BlogUtils {

private BlogUtils() {
	// TODO Auto-generated constructor stub
}

	private static final SecureRandom r = new SecureRandom();

	public static int getRandomNumber() {
		int min = 1;
		int max = 9999999;
		return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
	}

	public static boolean isNumeric(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}