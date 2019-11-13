/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		System.out.println("This is the Fraction Calculator.");
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter an expression, or type \"quit\" to quit the program.");
		String input = userInput.nextLine();
		System.out.println("Value 2: " + produceAnswer(input));
	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		int idx = input.indexOf(' ');
		String val1 = input.substring(0, idx);
		System.out.println("Value 1: " + val1);

		String remaining = input.substring(idx + 1);
		// System.out.println("Remaining: " + remaining);

		idx = remaining.indexOf(' ');
		String op = remaining.substring(0, idx);
		System.out.println("Operator: " + op);

		remaining = remaining.substring(idx + 1);
		// System.out.println("Remaining: " + remaining);

		String val2 = remaining;
		// System.out.println("Value 2: " + val2);

		//
		// Parse Value 1 into parts
		//
//		idx = val1.indexOf('_');
//		if (idx != -1) {
//			String w1 = val1.substring(0, idx);
//			 System.out.println("W1: " + w1);
//			val1 = val1.substring(idx + 1);
//		}
//		idx = val1.indexOf('/');
//		int n1 = Integer.parseInt(val1.substring(0, idx));
//		int d1 = Integer.parseInt(val1.substring(idx + 1));
//		 System.out.println("N1: " + n1 + " D1: " + d1);

		//
		// Parse Value 2 into parts
		//
//		idx = val2.indexOf('_');
//	    if (idx!=-1) {
//	      String w2 = val2.substring(0, idx);
//	      System.out.println("W2: " + w2);
//	      val2 = val2.substring(idx + 1);
//	    }
//	    idx = val2.indexOf('/');
//	    int n2 = Integer.parseInt(val2.substring(0, idx));
//	    int d2 = Integer.parseInt(val2.substring(idx + 1));
//	    System.out.println("N2: " + n2 + " D2: " + d2);
		return val2;
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need

}
