package br.com.tradutores.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	
	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "/" + "test.txt"));

		) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				//System.out.println(line);
				String pattern = "[A-z]*[a-z]*[0-9]*";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(line);

				while (m.find() ) {
					String min = m.group();
					System.out.println(min);
					}
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
