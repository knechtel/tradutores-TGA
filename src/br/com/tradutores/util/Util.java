package br.com.tradutores.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	private static Map<String, Integer> tabelaSimbolo = new HashMap<String, Integer>();

	
	private static Integer contTabelaSimbolo = 0;
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
				String pattern = "float|\\{|\\)|\\(|void|[A-Z][a-z]*|[A-Z][a-z]*[A-Z][a-z]*";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);

				while (m.find()) {
					String min = m.group();	
					
					if (min.trim().equals("void")) {
						System.out.println("[reserved_word, void] ");
					}
					
					if(min.trim().equals("(")){
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
					if(min.trim().equals("float")){
						System.out.println("[reserved_word, float] [id, 1");
						String str = line.replace("float", " ");
						String strnew = str.replace(";", "");
						
						String[] arrayString = strnew.split(",");
						
						for(String sr :arrayString){
							if(tabelaSimbolo.containsKey(contTabelaSimbolo)){
								
							}else{
								tabelaSimbolo.put(str.trim(),contTabelaSimbolo );
								System.out.println("[id, "+contTabelaSimbolo+"]");
								contTabelaSimbolo++;
								
								
							}
						
						}
						
					}
					
					System.out.println(min);

				}
			}
		}
	}
}
