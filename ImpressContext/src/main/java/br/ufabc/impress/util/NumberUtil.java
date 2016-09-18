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
	
	public int convertBinarytoInt(String input){
		
		
		char[] charArray = input.toCharArray();
		
		int r = 0;
		
		for (int i = 0; i < charArray.length; i++){
			r += Integer.parseInt(String.valueOf(charArray[i])) * Math.pow(2, (charArray.length - 1 - i));
		}
		
		return r;
	}

}
