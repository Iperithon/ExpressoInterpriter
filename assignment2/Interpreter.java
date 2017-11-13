package assignment2;

import java.util.*;
import java.io.*;

public class Interpreter {
	
	public static int tester (String x){
		String[] input = x.split(" ");
		int res = 0;
		
		if (input[0].equalsIgnoreCase("read")){
			res = 1;
		} else if (input[0].equalsIgnoreCase("print")){
			res = 2;
		} else {
			res = 3;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BufferedReader IO;
		String file = "";
		int check;
		
		System.out.print("Please enter a file you would like to read: ");
		file = sc.nextLine();
		System.out.println("loading [" + file + ".txt]");
		try {
			IO = new BufferedReader(new FileReader(file + ".txt"));
			String line = IO.readLine();
			int linenum = 1;
			
			while (line != null) {
				System.out.println("Line[" + linenum +"]: "+ line);
				check = tester(line);
				System.out.println(check);
				line = IO.readLine();
				linenum++;
			}
			IO.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
