/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {
//	public static int w1;
//	public static int w2;
//	public static int wresult;
//	public static int n1;
//	public static int n2;
//	public static int nresult;
//	public static int d1;
//	public static int d2;
//	public static int dresult;
//	public String op;
//	
	public static void main(String[] args) {
		System.out.println("This is the Fraction Calculator.");
		Scanner userInput = new Scanner(System.in);
		boolean run = true;
		while (run) {
			System.out.println("Enter an expression, or type \"quit\" to quit the program.");
			String inputFake = userInput.nextLine();
			String input = inputFake.toLowerCase();
			if (!input.equals("quit")) {
				System.out.println(produceAnswer(input));
			} else {
				System.out.println("You have chosen to quit this program. Bye.");
				run = false;
			}
		}
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
		int w1 = 0, n1 = 0, d1 = 0;
		int w2 = 0, n2 = 0, d2 = 0;

		int idx = input.indexOf(' ');
		String val1 = input.substring(0, idx);

		String remaining = input.substring(idx + 1);

		idx = remaining.indexOf(' ');
		String op = remaining.substring(0, idx);

		remaining = remaining.substring(idx + 1);

		String val2 = remaining;

		//
		// Parse Value 1 into parts
		//
		idx = val1.indexOf('_');
		if (idx != -1) {
			w1 = Integer.parseInt(val1.substring(0, idx));
			// System.out.println("W1: " + w1);
			val1 = val1.substring(idx + 1);
			idx = val1.indexOf("/");
			n1 = Integer.parseInt(val1.substring(0, idx));
			d1 = Integer.parseInt(val1.substring(idx + 1));
			// System.out.println("N1: " + n1 + " D1: " + d1);
			String printOne = "whole:" + w1 + "numerator" + n1 + "denominator" + d1;
		} else {
			idx = val1.indexOf('/');
			if (idx != -1) {
				w1 = 0;
				n1 = Integer.parseInt(val1.substring(0, idx));
				d1 = Integer.parseInt(val1.substring(idx + 1));
				// System.out.println("numerator: " + n1 + " denominator: " + d1);
			} else {
				n1 = 0;
				d1 = 1;
				w1 = Integer.parseInt(val1);
				String printOne = "whole:" + val1 + "numerator:0" + "denominator:1";
			}
		}
		//
		// Parse Value 2 into parts
		//
		idx = val2.indexOf('_');
		if (idx != -1) {
			w2 = Integer.parseInt(val2.substring(0, idx));
			val2 = val2.substring(idx + 1);
			idx = val2.indexOf("/");
			n2 = Integer.parseInt(val2.substring(0, idx));
			d2 = Integer.parseInt(val2.substring(idx + 1));
			String printTwo = "whole:" + w2 + " numerator:" + n2 + " denominator:" + d2;
			// return printTwo;

		} else {
			idx = val2.indexOf('/');
			if (idx != -1) {
				n2 = Integer.parseInt(val2.substring(0, idx));
				d2 = Integer.parseInt(val2.substring(idx + 1));
				w2 = 0;
				String printTwo = "whole:0" + " numerator:" + n2 + " denominator:" + d2;
				// return printTwo;
			} else {
				n2 = 0;
				d2 = 1;
				w2 = Integer.parseInt(val2);
				String printTwo = "whole:" + val2 + " numerator:0" + " denominator:1";
				// return printTwo;
			}
		}

//		System.out.printf("Parsed: %d %d/%d %s %d %d/%d\n",
//				w1, n1, d1, op, w2, n2, d2);

		Number num1 = new Number();
		num1.Whole = w1;
		num1.Numerator = n1;
		num1.Denominator = d1;

		Number num2 = new Number();
		num2.Whole = w2;
		num2.Numerator = n2;
		num2.Denominator = d2;

		Number result = compute(num1, op, num2);

		return result.toString();
	}

	public static Number compute(Number num1, String op, Number num2) {

		System.out.println("Original:");
		System.out.println("num1: " + num1.toString());
		System.out.println("num2: " + num2.toString());
		System.out.println("op " + op);

		num1.convertToImproper();
		num2.convertToImproper();

		System.out.println("After convert to improper:");
		System.out.println("num1: " + num1.toString());
		System.out.println("num2: " + num2.toString());

		Number result = new Number();
		// result.IsNegative = num1.isNegative() != num2.isNegative();

		int newWhole1 = Math.abs(num1.Whole);
		int newWhole2 = Math.abs(num2.Whole);
		int newNum1 = (num1.Denominator * newWhole1) + num1.Numerator;
		int newNum2 = (num2.Denominator * newWhole2) + num2.Numerator;
		if (op.equals("+")) {
			result.Numerator = (newNum1 * num2.Denominator) + (newNum2 * num1.Denominator);
			result.Denominator = num1.Denominator * num2.Denominator;
		} else if (op.equals("-")) {
			result.Numerator = (newNum1 * num2.Denominator) - (newNum2 * num1.Denominator);
			result.Denominator = num1.Denominator * num2.Denominator;

		} else if (op.equals("*")) {
			result.Numerator = newNum1 * newNum2;
			result.Denominator = num1.Denominator * num2.Denominator;

		} else if (op.equals("/")) {
			result.Numerator = newNum1 * num2.Denominator;
			result.Denominator = num1.Denominator * newNum2;

		}

		return result;
	}

//	public static int reduce(int Numerator, int Denominator) {
//		int wholeNum = Numerator / Denominator;
//		int newNumerator = Numerator % Denominator;
//
//	}
//
//	public static void gcd(int Numerator, int Denominator) {
//
//	}

}
