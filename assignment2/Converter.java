package assignment2;

import java.io.*;
import java.util.*;

/*
This is the converter class, it houses the Conversion from infix to postfix as well as method to evaluate postfix.
*/

@SuppressWarnings("unused")

public class Converter {
	
	private static boolean isInteger(String s) {
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
	    }
	    return -1;
	}
	
	private static String convert(String x){
		String[] eq = x.split(" ");
		Stack<String> st1 = new Stack<String>();
		StringBuffer res = new StringBuffer(eq.length);
		
		System.out.println(Arrays.toString(eq));
		
		for(int i = 0;i < eq.length;i++){
			if (isInteger(eq[i])){   //if e is an Operand, append to output string
				res.append(eq[i] + " ");
			} else if (eq[i].matches("[a-zA-z]")){ //if e is an Operand, append to output string
				res.append(eq[i] + " ");
			} else if (eq[i].equalsIgnoreCase("(")){ // if e is a left parentheses, "(" 
				st1.push(eq[i]);
			} else if (eq[i].equalsIgnoreCase(")")){ //check for closing bracket
				while (!st1.isEmpty() && !st1.peek().equalsIgnoreCase("(")){ //if top is not the opening bracket, append
					res.append(st1.pop() + " ");
				}
				if (!st1.isEmpty()){ // else pop the ( from the stack
					st1.pop();
				}
			} else if (isOperator(eq[i])){ //if e is an Opperator check the stack for precedence
				if (!st1.isEmpty() && precedence(eq[i]) <= precedence(st1.peek()) ){ //if greater, append to output
					res.append(st1.pop() + " ");
				}
				st1.push(eq[i]); //otherwise if stack is empty put it on the stack
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
    
	public static int postfixEvaluate(String exp) {
	 	Stack<Integer> s = new Stack<Integer> ();
		Scanner tokens = new Scanner(exp);
		
		while (tokens.hasNext()) {
			if (tokens.hasNextInt()) {
				s.push(tokens.nextInt());
			} else {
				int num2 = s.pop();
				int num1 = s.pop();
				String op = tokens.next();
				
				if (op.equals("+")) {
					s.push(num1 + num2);
				} else if (op.equals("-")) {
					s.push(num1 - num2);
				} else if (op.equals("*")) {
					s.push(num1 * num2);
				} else {
					s.push(num1 / num2);
				}
				
			//  "+", "-", "*", "/"
			}
		}
		tokens.close();
		return s.pop();
    }
 
	public static void main (String[] args){
	/*	int test = 0;
		String equation = "3 - 1 * ( 6 / 2 ) + 10 / ( 1 - 4 + 5 )"; //-5
		
		equation = convert(equation);
		System.out.println(equation);
		test = postfixEvaluate(equation);
		System.out.println("Evaluate postfix: " + test); //-5
		test = 3 - 1 * ( 6 / 2 ) + 10 / ( 1 - 4 + 5 );
		System.out.println("Evaluate infix: " + test); //5?
		*/
	} 	
}
