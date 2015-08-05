package com.abc.helpers;

import java.math.BigDecimal;

public class TextHelper {
	//Make sure correct plural of word is created based on the number passed in:
	public static String formatToSingularOrPlural(int number, String word) {
		return number + " " + ((number % 10 == 1 && number % 100 != 11) ? word : word + "s");
	}

	public static String toAbsoluteDollars(BigDecimal d){
		return String.format("$%,.2f", d.abs().doubleValue());
	}
}
