package br.com.tradutores.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tradutores.bean.Token;

public class Util {

	static List<Token> listLookAhead = new ArrayList<Token>();

	public static void scannerAux(String line) {
		String pattern = "\\+|\\)\\(|[A-z]*[0-9]|[A-Z]*";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		boolean inicio = false;
		while (m.find()) {
			String str = m.group();
			System.out.println(str);
			if (str.equals("(")) {
				Token t = new Token();
				t.setPadrao("(");
				listLookAhead.add(t);
			}

			if (str.equals(")")) {
				Token t = new Token();
				t.setPadrao(")");
				listLookAhead.add(t);
			}

			if (str.equals("+")) {
				Token t = new Token();
				t.setPadrao("+");
				listLookAhead.add(t);
			}

			Token t = Scanner.getTabelaSimbolo().get(str);
			if (t != null)
				listLookAhead.add(t);

		}
	}

	public static Integer parse(String str) {

		return null;
	}

	public static boolean isNumberDouble(String number) {

		try {
			Double.parseDouble(number);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(Util.isNumberDouble("6.0"));
	}

}
