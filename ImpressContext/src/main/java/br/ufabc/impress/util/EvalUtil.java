package br.ufabc.impress.util;

public class EvalUtil {
	
	public static String setTime(String point, String message, String d1, String d2){

		String[] vector = message.split(d1);
		
		for (int i = 0; i < vector.length; i++){
			
			String[] tmp = vector[i].split(d2);
			
			if (tmp[0].equalsIgnoreCase(point)){
				tmp[1] = String.valueOf(System.currentTimeMillis());
				vector[i]= tmp[0]+"="+tmp[1];
				break;
			}
		}
		String ret = "";
		for (int i = 0; i < vector.length; i++){
			if (i<vector.length-1){
				ret += vector[i] + ";";
			}
			else{
				ret += vector[i];
			}
		}
		
		return ret;
		
	}
	

}
