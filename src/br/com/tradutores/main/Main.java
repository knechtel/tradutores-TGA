package br.com.tradutores.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import br.com.tradutores.util.Scanner;

public class Main {

	public static void macOsx() {

		System.out.println("Mac os x");
		try (BufferedReader reader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "/" + "codigo.c"));

		) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				//System.out.println(line);
				Scanner.regex(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void windows() {
		// windows
		try (BufferedReader reader = new BufferedReader(
				new FileReader(System.getProperty("user.dir") + "\\" + "codigo.c"));) {
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				Scanner.regex(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		if ("Mac OS X".equals(System.getProperty("os.name"))) {
			macOsx();
		} else {
			windows();

		}
	}

}
