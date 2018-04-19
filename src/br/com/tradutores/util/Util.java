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

	public static void scannerAuxChar(String line) {
		//String pattern = "\\(|[\\*]+[a-z]+" ;
				String pattern = "\\(|[\\*]+( )*[a-z]+|" + "[a-z]+|char";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);
				boolean isInt = false;
				boolean isPointer = false;
				boolean inicioCompVarMetodos = false;
				while (m.find()) {
					String str = m.group();

					if (str.equals("(")) {
						inicioCompVarMetodos = true;
					}

					if (isInt && inicioCompVarMetodos) {
						
						if(str.contains("*")) {
							isPointer=true;
							str=str.replace("*", "");
						}
						if (!br.com.tradutores.util.Scanner.tabelaSimbolo.containsKey(str.trim())) {
							Token t = new Token();
						
							t.setPadrao(str.trim());
							t.setId(br.com.tradutores.util.Scanner.contTabelaSimbolo);
							t.setPonteiro(isPointer);
							System.out.println("char "+str.trim());
							//if (str.contains("*"))
								
							
							br.com.tradutores.util.Scanner.tabelaSimbolo.put(t.getPadrao(), t);
							// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
							System.out.println("[id, " + br.com.tradutores.util.Scanner.contTabelaSimbolo + "]");
							br.com.tradutores.util.Scanner.contTabelaSimbolo++;
						}else {
							
						}
					} else {

					}

					if (str.equals("char") && inicioCompVarMetodos) {
						isInt = true;
					} else {
						isInt = false;
					}
					
					isPointer=false;
					
				}
	}
	public static void scannerAuxInt(String line) {

		//String pattern = "\\(|[\\*]+[a-z]+" ;
		String pattern = "\\(|[\\*]+( )*[a-z]+|" + "[a-z]+|int";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		boolean isInt = false;
		boolean isPointer = false;
		boolean inicioCompVarMetodos = false;
		while (m.find()) {
			String str = m.group();

			if (str.equals("(")) {
				inicioCompVarMetodos = true;
			}

			if (isInt && inicioCompVarMetodos) {
				
				if(str.contains("*")) {
					isPointer=true;
					str=str.replace("*", "");
				}
				if (!br.com.tradutores.util.Scanner.tabelaSimbolo.containsKey(str.trim())) {
					Token t = new Token();
				
					t.setPadrao(str.trim());
					t.setId(br.com.tradutores.util.Scanner.contTabelaSimbolo);
					t.setPonteiro(isPointer);
				
					//if (str.contains("*"))
						
					
					br.com.tradutores.util.Scanner.tabelaSimbolo.put(t.getPadrao(), t);
					// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
					System.out.println("[id, " + br.com.tradutores.util.Scanner.contTabelaSimbolo + "]");
					br.com.tradutores.util.Scanner.contTabelaSimbolo++;
				}else {
					
				}
			} else {

			}

			if (str.equals("int") && inicioCompVarMetodos) {
				isInt = true;
			} else {
				isInt = false;
			}
			
			isPointer=false;
			
		}

	}

	public static void scannerAuxFloat(String line) {

		//String pattern = "\\(|[\\*]+[a-z]+" ;
		String pattern = "\\(|[\\*]+( )*[a-z]+|" + "[a-z]+|char";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		boolean isInt = false;
		boolean isPointer = false;
		boolean inicioCompVarMetodos = false;
		while (m.find()) {
			String str = m.group();

			if (str.equals("(")) {
				inicioCompVarMetodos = true;
			}

			if (isInt && inicioCompVarMetodos) {
				
				if(str.contains("*")) {
					isPointer=true;
					str=str.replace("*", "");
				}
				if (!br.com.tradutores.util.Scanner.tabelaSimbolo.containsKey(str.trim())) {
					Token t = new Token();
				
					t.setPadrao(str.trim());
					t.setId(br.com.tradutores.util.Scanner.contTabelaSimbolo);
					t.setPonteiro(isPointer);
					System.out.println("char "+str.trim());
					//if (str.contains("*"))
						
					
					br.com.tradutores.util.Scanner.tabelaSimbolo.put(t.getPadrao(), t);
					// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
					System.out.println("[id, " + br.com.tradutores.util.Scanner.contTabelaSimbolo + "]");
					br.com.tradutores.util.Scanner.contTabelaSimbolo++;
				}else {
					
				}
			} else {

			}

			if (str.equals("float") && inicioCompVarMetodos) {
				isInt = true;
			} else {
				isInt = false;
			}
			
			isPointer=false;
			
		}

		// System.out.println("FIM_FUNCAO_ALERTA");
	}

	public static void scannerAuxString(String line) {

		//String pattern = "\\(|[\\*]+[a-z]+" ;
				String pattern = "\\(|[\\*]+( )*[a-z]+|" + "[a-z]+|char";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);
				boolean isInt = false;
				boolean isPointer = false;
				boolean inicioCompVarMetodos = false;
				while (m.find()) {
					String str = m.group();

					if (str.equals("(")) {
						inicioCompVarMetodos = true;
					}

					if (isInt && inicioCompVarMetodos) {
						
						if(str.contains("*")) {
							isPointer=true;
							str=str.replace("*", "");
						}
						if (!br.com.tradutores.util.Scanner.tabelaSimbolo.containsKey(str.trim())) {
							Token t = new Token();
						
							t.setPadrao(str.trim());
							t.setId(br.com.tradutores.util.Scanner.contTabelaSimbolo);
							t.setPonteiro(isPointer);
							System.out.println("char "+str.trim());
							//if (str.contains("*"))
								
							
							br.com.tradutores.util.Scanner.tabelaSimbolo.put(t.getPadrao(), t);
							// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
							System.out.println("[id, " + br.com.tradutores.util.Scanner.contTabelaSimbolo + "]");
							br.com.tradutores.util.Scanner.contTabelaSimbolo++;
						}else {
							
						}
					} else {

					}

					if (str.equals("string") && inicioCompVarMetodos) {
						isInt = true;
					} else {
						isInt = false;
					}
					
					isPointer=false;
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
