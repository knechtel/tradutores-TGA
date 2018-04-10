package br.com.tradutores.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tradutores.bean.Token;

public class Scanner {

	private static Map<String, Token> tabelaSimbolo = new HashMap<String, Token>();

	static boolean nextLine = true;
	private static Integer contTabelaSimbolo = 0;
	

	private static Integer escopo=0;

	public static void regex(String line) {

		if (line.contains("#")) {
			// include

			System.out.print("include = ");
			System.out.println(line);

		} else {

			System.out.println(line);
			String pattern = "//|=|float|\\{|\\)|\\(|void|[A-z]*[0-9]|[A-z]*/|[a-b]*";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(line);

			while (m.find() ) {
				String min = m.group();

				if (min.trim().equals("//")) {
					System.out.println("comentário : " + line);
					nextLine = false;
				}
				if (min.trim().equals("void")) {
					System.out.println("[reserved_word, void] ");
				}

				if (min.trim().equals("(")) {
					System.out.println("[l_paren, (]");
				}

				if (min.trim().equals(")")) {
					System.out.println("[r_paren, )]");
				}

				if (min.trim().equals("{")) {
					System.out.println("[l_bracket, {]");
					escopo++;
				}

				if (min.trim().equals("}")) {
					System.out.println("[r_bracket, }]");
					escopo--;
				}
				if (min.trim().equals("=")) {
					System.out.println(" [Equal_Op, =] ");
				}

				if (min.trim().equals("float")) {
					System.out.println("[reserved_word, float] ");
					String str = line.replace("float", " ");
					String strnew = str.replace(";", "");

					String[] arrayString = strnew.split(",");

					for (String sr : arrayString) {
						if (tabelaSimbolo.containsKey(sr)) {
							
						} else {

							Token t = new Token();
							t.setEscopo(escopo);
							t.setId(contTabelaSimbolo);
							t.setPadrao(str.trim());
							tabelaSimbolo.put(t.getPadrao(),t);
							System.out.println("[id, " + contTabelaSimbolo + "]");
							contTabelaSimbolo++;

						}

					}

				}
				
				

			}

		}
	}
}
