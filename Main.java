package assignment2;

import java.io.*;
import java.util.*;

/*
Project to make a 'compiler' / 'interpreter'
*/

public class Main {
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	private static boolean isOperator(String c) {
        return c.equalsIgnoreCase("+") || c.equalsIgnoreCase("-") || c.equalsIgnoreCase("*") || c.equalsIgnoreCase("/") || 
        		c.equalsIgnoreCase("^") || c.equalsIgnoreCase("(") || c.equalsIgnoreCase(")");
	}
	
	private static int precedence(String op)
	{
	    switch (op.charAt(0))
	    {
	        case '+':
	        case '-':
	            return 1;

	        case '*':
	        case '/':
	            return 2;

	        case '^':
	            return 3;

	        default:
	            return -1;
	    }
	}
	
	private static String convert(String x){
		String[] eq = x.split(" ");
		Stack<String> st1 = new Stack<String>();
		StringBuffer res = new StringBuffer(eq.length);
		
		System.out.println(Arrays.toString(eq));
		
		for(int i = 0;i < eq.length;i++){
			if (isInteger(eq[i])){
				System.out.println("this is a digit: " + eq[i] );
				res.append(eq[i] + " ");
			} else if (eq[i].matches("[a-zA-z]")){
				System.out.println("This is a Variable");
				res.append(eq[i] + " ");
			} else if (eq[i].equalsIgnoreCase("(")){
				st1.push(eq[i]);
			} else if (eq[i].equalsIgnoreCase(")")){
				while (!st1.isEmpty() && !st1.peek().equalsIgnoreCase("(")){
					res.append(st1.pop() + " ");
				}
				if (!st1.isEmpty() && !st1.peek().equalsIgnoreCase("(")){
					return null;
				} else if (!st1.isEmpty()){
					st1.pop();
				}
			} else if (isOperator(eq[i])){
				if (!st1.isEmpty() && precedence(eq[i]) <= precedence(st1.peek()) ){
					res.append(st1.pop() + " ");
				}
				st1.push(eq[i]);
			} else { 
				if (eq[i].matches("^[a-zA-Z]*$")){
					System.out.println("index [" + i + "] is not a valid variable" );
				} else {
					System.out.println("index [" + i + "] Does not compute");
				}
			
			}	
		}
		while (!st1.isEmpty()){	res.append(st1.pop()+ " "); }
		return res.toString();
	}
	
	public static void main (String[] args){
		//Scanner sc = new Scanner(System.in);
		
		//String eq;  //in take equation
		// char[] infix, postfix; //infix array and postfix array
		
		//System.out.print("Please enter an Infix Equation: ");
		//eq = sc.nextLine();
		System.out.println(convert("a + b * ( c ^ d - e ) ^ ( f + g * h ) - i"));
		
	}	
}
