  package br.com.tradutores.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.tradutores.bean.Token;
import br.com.tradutores.util.Util;

public class Test {
	public static void test(String  line) {

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

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "/" + "test.txt"));

		) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				// System.out.println(line);
				Util.scannerAuxInt(line);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
