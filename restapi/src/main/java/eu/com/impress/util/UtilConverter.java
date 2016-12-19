package eu.com.impress.util;

public class UtilConverter {
	
	public boolean isNumeric(String s) {
		try {
			Long.parseLong(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

}
