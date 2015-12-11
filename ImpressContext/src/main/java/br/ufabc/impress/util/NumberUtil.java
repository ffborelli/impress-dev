package br.ufabc.impress.util;

public class NumberUtil {
	
	public boolean isNumeric(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
