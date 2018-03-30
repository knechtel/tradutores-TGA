package br.com.tradutores.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	private Map<String, String> tabelaSimbolo = new HashMap<String, String>();

	public static void regex(String line) {

		if (line.contains("#")) {
			// include

			System.out.print("include = ");
			System.out.println(line);

		} else {

			if (line.equals(" ")) { 
				// espaço em branco
			} else {

				System.out.println(line);
				String pattern = "\\{|\\)|\\(|void|[A-Z][a-z]*|[A-Z][a-z]*[A-Z][a-z]*";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);

				while (m.find()) {
					String min = m.group();	
					System.out.println("group cont = "+m.groupCount());
					if (min.trim().equals("void")) {
						System.out.println("[reserved_word, void] ");
					}
					
					if(min.trim().equals("\\(")){
						System.out.println("[l_paren, (]");
					}
					
					if(min.trim().equals(")")){
						System.out.println("[r_paren, )]");
					}
					
					if(min.trim().equals("{")){
						System.out.println("[l_bracket, {]");
					}
					
					if(min.trim().equals("}")){					
						System.out.println("[r_bracket, }]");
					}
					System.out.println(min);

				}
			}
		}
	}
}
