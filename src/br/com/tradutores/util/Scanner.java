package br.com.tradutores.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tradutores.bean.Token;
import br.com.tradutores.main.Main;

public class Scanner {

	public static Map<String, Token> tabelaSimbolo = new HashMap<String, Token>();

	static boolean nextLine = true;

	static boolean comentarioMultiLinha = false;
	static boolean aspasDuplas = false;
	static boolean comentario = false;
	public static Integer contTabelaSimbolo = 0;
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

			String pattern = "%|:|[A-Z]+|" + "char|" + "/\\*.+" // inicio comentário
					+ "|\\*/|" + // fim comentário
					"<=|" + ">=|" + "<|" + ">|" + ">=|" + "==|" + "=|" + "[0-9]+|" + "==|" + // relacional ==
					"\\(|" + // abre parenteses
					"\\{|" + // abre chaves
					"\\)|" + // fecha parenteses
					"\\}|" + // fecha chaves
					"\"+" + // string
					"|;|" + // ponto e virgula
					"[a-z]*|";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(line);

			while (m.find()) {
				String min = m.group();
				if (min.trim().contains("\"")) {
					aspasDuplas = !aspasDuplas;
					if (aspasDuplas)
						System.out.print("[string literal, ");
					else
						System.out.print("]");
				}
				if (aspasDuplas) {
					System.out.print(min.replace("\"", ""));
				}
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
				if (min.trim().equals("<=") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[ Relational_Op,<=] ");
				}
				if (min.trim().equals(">=") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[ Relational_Op,>=] ");
				}

				if (min.trim().equals("==") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[ Relational_Op,==] ");
				}

				if (min.trim().equals("<") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[ Relational_Op,<] ");
				}

				if (min.trim().equals(">") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[ Relational_Op,>] ");
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

						}

					} else {

					}

				}

				if (tabelaSimbolo.containsKey(min.trim()) && !comentarioMultiLinha) {
					// System.out.println("[id, " + tabelaSimbolo.get(min.trim()).getId() + "]");
				}

				if (min.trim().equals("float") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {

					if (escopo == 0) {
						System.out.println("[reserved_word, float] ");
						Util.scannerAuxFloat(line);
					} else {
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
				}

				if (min.trim().equals("int") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					if (escopo == 0) {
						System.out.println("[reserved_word, int] ");

						Util.scannerAuxInt(line);
					} else {
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
				}

				if (min.trim().equals("string") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					if (escopo == 0) {
						System.out.println("[reserved_word, string] ");
						Util.scannerAuxString(line);
					} else {
						System.out.println("[reserved_word, string] ");
						String str = line.replace("string", " ");
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
				}

				if (min.trim().equals("char") && !nextLine && !aspasDuplas && !comentarioMultiLinha) {
					if (escopo == 0) {
						System.out.println("[reserved_word, char] ");
					} else {
						System.out.println("[reserved_word, char] ");
						String str = line.replace("char", " ");
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
				}

				if (min.trim().equals("if")) {
					System.out.println("[reserved_word, if] ");
				}
				if (min.trim().equals("return") && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[reserved_word, return] ");
				}

				if (Util.isNumberDouble(min.trim()) && !aspasDuplas && !comentarioMultiLinha) {
					System.out.println("[num, " + min.trim() + "]");
				}
				if (tabelaSimbolo.containsKey(min.trim()) && !aspasDuplas && !comentarioMultiLinha) {
					Token t1 = tabelaSimbolo.get(min.trim());

					// System.out.println("[id, " + t1.getId() + "]");

					// System.out.println(">>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<");

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

	public static void main(String[] args) {
		if ("Mac OS X".equals(System.getProperty("os.name"))) {
			new Main().macOsx();
		} else {
			new Main().windows();

		}
	}

}
