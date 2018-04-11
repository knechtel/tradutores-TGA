package br.com.tradutores.util;

public class Util {
	
	
	public static Integer parse(String str) {
		
		return null;
	}
	
	public static boolean isNumber(String number) {
	
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return true;
		}
		return false;
	}

}
