package br.com.tradutores.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tradutores.bean.Token;

public class Scanner {

	private static Map<String, Token> tabelaSimbolo = new HashMap<String, Token>();

	static boolean nextLine = true;

	static boolean comentarioMultiLinha = false;
	static boolean aspasDuplas = false;
	static boolean comentario = false;
	private static Integer contTabelaSimbolo = 0;
	private static String lookAhead = "";

	private static Integer escopo = 0;

	public static void regex(String line) {

		if (line.contains("#")) {
			// include

			System.out.print("include = ");
			System.out.println(line);

		} else {
			lookAhead = line;
			System.out.println(line);
			String pattern = "\\(|[//|"
					+ "/\\*.+"
					+ "|\\*/|\""
					+ "|;"
					+ "|[0-9]+.[0-9]*"
					+ "|/|"
					+ "//|"
					+ "=|"
					+ "float|"
					+ "\\{|"
					+ "\\)|"
					+ ""
					+ "void|"
					+ "[A-z]*[0-9]|"
					+ "a-z]*|int";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(line);

			while (m.find()) {
				String min = m.group();
				
				if (min.contains("/*")) {
					System.out.println("Começa comentário");
					comentarioMultiLinha = !comentarioMultiLinha;
				}

				if (min.trim().equals("*/")) {
					System.out.println("FIM comentário ../*");
					comentarioMultiLinha = !comentarioMultiLinha;
				}

				if (min.trim().equals("//")) {
					String[] coment = line.split("//");
					if (coment.length >= 2)
						System.out.println("comentário : " + coment[1]);
					nextLine = false;
				}

				if (min.trim().equals("void") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[reserved_word, void] ");
				}
				if (min.trim().equals(";") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[semicolon, ;]");
				}

				if (min.trim().equals("(") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[l_paren, (]");
				}

				if (min.trim().equals(")") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[r_paren, )]");
				}

				if (min.trim().equals("\"") && !nextLine && !comentarioMultiLinha) {
					aspasDuplas = !aspasDuplas;

					System.out.println("estou aqui ");
				}
				if (min.trim().equals("{") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[l_bracket, {]");
					escopo++;
				}

				if (min.trim().equals("}") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[r_bracket, }]");
					escopo--;
				}

				if (min.trim().equals("/") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					if (!line.contains("//") && !line.contains("/*"))
						System.out.println("[, division] ");
				}
				if (min.trim().equals("=") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println(" [Equal_Op, =] ");

					String[] sArray = lookAhead.split("=");

					if (sArray.length == 2 && !nextLine) {
						String aux = sArray[1].replace(";", "");
						String tokenAux = sArray[0];

						Token t = new Token();

						t.setPadrao(tokenAux);
						System.out.println("string verify = " + tokenAux.trim());
						if (!tabelaSimbolo.containsKey(tokenAux.trim())) {
							t.setId(contTabelaSimbolo);
							tabelaSimbolo.put(tokenAux, t);
						} else {
							// t.setId( contTabelaSimbolo);
							Token t1 = tabelaSimbolo.get(tokenAux.trim());
							System.out.println("[id, " + t1.getId() + "]");
							contTabelaSimbolo++;
							System.out.println(" contains");
						}

						System.out.println("segunda parte" + aux);
					} else {

					}

				}

				if (tabelaSimbolo.containsKey(min.trim()) && !comentarioMultiLinha) {
					System.out.println("[id, " + tabelaSimbolo.get(min.trim()).getId() + "]");
				}

				if (min.trim().equals("float") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[reserved_word, float] ");
					String str = line.replace("float", " ");
					String strnew = str.replace(";", "");

					String[] arrayString = strnew.split(",");

					for (String sr : arrayString) {
						if (tabelaSimbolo.containsKey(sr)) {
							System.out.println("entrou aqui");
						} else {

							Token t = new Token();
							t.setEscopo(escopo);
							t.setId(contTabelaSimbolo);
							t.setPadrao(sr.trim());
							tabelaSimbolo.put(t.getPadrao(), t);
							// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
							System.out.println("[id, " + contTabelaSimbolo + "]");
							contTabelaSimbolo++;

						}

					}

				}

				if (min.trim().equals("int") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {

					System.out.println("[reserved_word, int] ");
					String str = line.replace("int", " ");
					String strnew = str.replace(";", "");
					System.out.println(line);
					String[] arrayString = strnew.split(",");

					for (String sr : arrayString) {
						if (tabelaSimbolo.containsKey(sr)) {
							System.out.println("entrou aqui");
						} else {

							Token t = new Token();
							t.setEscopo(escopo);
							t.setId(contTabelaSimbolo);
							t.setPadrao(sr.trim());
							tabelaSimbolo.put(t.getPadrao(), t);
							// System.out.println(t.getPadrao() + " padrao -- -- -- -- ");
							System.out.println("[id, " + contTabelaSimbolo + "]");
							contTabelaSimbolo++;

						}

					}

				}

				if (Util.isNumberDouble(min.trim()) && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[num, " + min.trim() + "]");
				}

				nextLine = false;

			}

		}
	}

	public static Map<String, Token> getTabelaSimbolo() {
		return tabelaSimbolo;
	}

	public static void setTabelaSimbolo(Map<String, Token> tabelaSimbolo) {
		Scanner.tabelaSimbolo = tabelaSimbolo;
	}

}
