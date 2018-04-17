package br.com.tradutores.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tradutores.bean.Token;
import br.com.tradutores.test.Test;

public class Util {

	static List<Token> listLookAhead = new ArrayList<Token>();
	boolean desativa = false;

	public static double compExpress() {
		boolean comp = false;
		boolean divisao = false;
		Double vlr = 0.0;

		for (int i = 0; i < listLookAhead.size(); i++) {
			if (i + 1 < listLookAhead.size()) {

				Token t = listLookAhead.get(i);
				Token tNext = listLookAhead.get(i + 1);
				Double result = 0.0;
				Integer resultInt = 0;

				boolean isDouble = false;
				boolean isInt = false;
				if (!t.getPadrao().equals(")") && !t.getPadrao().equals("(") && !t.getPadrao().equals("+")
						&& !t.getPadrao().equals("-") && !t.getPadrao().equals("*")) {
				}

				if (!tNext.getPadrao().equals(")") && !tNext.getPadrao().equals("(") && !tNext.getPadrao().equals("+")
						&& !tNext.getPadrao().equals("-") && !tNext.getPadrao().equals("*")) {

					Token tNexProx = listLookAhead.get(i + 2);
					String s = getOperador(tNexProx.getLexema());

					if (s.trim().equals("+")) {
						if (isDouble) {
							result = result + Double.parseDouble(tNext.getLexema());
							isDouble = false;
						}

						if (isInt) {
							resultInt = resultInt + Integer.parseInt(tNext.getLexema());
							isInt = false;
						}

					} else if (s.trim().equals("-")) {

						if (isDouble) {
							result = result - Double.parseDouble(tNext.getLexema());
							isDouble = false;
						}

						if (isInt) {
							resultInt = resultInt - Integer.parseInt(tNext.getLexema());
							isInt = false;
						}

					} else if (s.trim().equals("*")) {

					} else if (s.trim().equals("/")) {

					}

				} else {
					System.out.println("verificar...");

				}

			} else {

			}
		}

		return vlr;
	}

	public static void scannerAuxInt(String line) {

		String pattern = "\\(|[A-Z]+[a-z]+[A-Z]+[a-z]+|" + "[a-z]+|int";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		boolean isInt = false;

		boolean inicioCompVarMetodos = false;
		while (m.find()) {
			String str = m.group();

			if (str.equals("(")) {
				inicioCompVarMetodos = true;
			}

			if (isInt && inicioCompVarMetodos) {
				if (!br.com.tradutores.util.Scanner.tabelaSimbolo.containsKey(str)) {
					Token t = new Token();
					t.setPadrao(str);
					t.setId(br.com.tradutores.util.Scanner.contTabelaSimbolo);
					br.com.tradutores.util.Scanner.tabelaSimbolo.put(t.getPadrao(), t);
					// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
					System.out.println("[id, " + br.com.tradutores.util.Scanner.contTabelaSimbolo + "]");
					br.com.tradutores.util.Scanner.contTabelaSimbolo++;
				}
			} else {

			}

			if (str.equals("int") && inicioCompVarMetodos) {
				isInt = true;
			} else {
				isInt = false;
			}

			// System.out.println(str);
		}

		// System.out.println("FIM_FUNCAO_ALERTA");
	}

	
	public static void scannerAuxFloat(String line) {

		String pattern = "\\(|[A-Z]+[a-z]+[A-Z]+[a-z]+|" + "[a-z]+|int";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		boolean isInt = false;

		boolean inicioCompVarMetodos = false;
		while (m.find()) {
			String str = m.group();

			if (str.equals("(")) {
				inicioCompVarMetodos = true;
			}

			if (isInt && inicioCompVarMetodos) {
				if (!br.com.tradutores.util.Scanner.tabelaSimbolo.containsKey(str)) {
					Token t = new Token();
					t.setPadrao(str);
					t.setId(br.com.tradutores.util.Scanner.contTabelaSimbolo);
					br.com.tradutores.util.Scanner.tabelaSimbolo.put(t.getPadrao(), t);
					// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
					System.out.println("[id, " + br.com.tradutores.util.Scanner.contTabelaSimbolo + "]");
					br.com.tradutores.util.Scanner.contTabelaSimbolo++;
				}
			} else {

			}

			if (str.equals("float") && inicioCompVarMetodos) {
				isInt = true;
			} else {
				isInt = false;
			}

			// System.out.println(str);
		}

		// System.out.println("FIM_FUNCAO_ALERTA");
	}
	
	
	public static void scannerAuxString(String line) {

		String pattern = "\\(|[A-Z]+[a-z]+[A-Z]+[a-z]+|" + "[a-z]+|int";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		boolean isInt = false;

		boolean inicioCompVarMetodos = false;
		while (m.find()) {
			String str = m.group();

			if (str.equals("(")) {
				inicioCompVarMetodos = true;
			}

			if (isInt && inicioCompVarMetodos) {
				if (!br.com.tradutores.util.Scanner.tabelaSimbolo.containsKey(str)) {
					Token t = new Token();
					t.setPadrao(str);
					t.setId(br.com.tradutores.util.Scanner.contTabelaSimbolo);
					br.com.tradutores.util.Scanner.tabelaSimbolo.put(t.getPadrao(), t);
					// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
					System.out.println("[id, " + br.com.tradutores.util.Scanner.contTabelaSimbolo + "]");
					br.com.tradutores.util.Scanner.contTabelaSimbolo++;
				}
			} else {

			}

			if (str.equals("string") && inicioCompVarMetodos) {
				isInt = true;
			} else {
				isInt = false;
			}

			// System.out.println(str);
		}

		// System.out.println("FIM_FUNCAO_ALERTA");
	}
	
	public static String getOperador(String str) {

		if (str.trim().equals("+")) {
			return str;
		} else if (str.trim().equals("-")) {
			return str;
		} else if (str.trim().equals("*")) {
			return str;
		} else if (str.trim().equals("/")) {
			return str;

		}

		return str;

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

	public static boolean isNumberInteger(String number) {

		try {
			Integer.parseInt(number);
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new Test().main(args);
	}

}
