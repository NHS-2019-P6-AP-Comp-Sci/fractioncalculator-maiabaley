/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	// first operand (whole, numerator, denominator parts)
	public static int w1;
	public static int n1;
	public static int d1;

	// second operand parts (whole, numerator, denominator parts)
	public static int w2;
	public static int n2;
	public static int d2;

	// result parts (whole, numerator, denominator parts)
	public static int wr;
	public static int nr;
	public static int dr;

	// operator
	public static String op;

	public static void main(String[] args) {
		System.out.println("This is the Fraction Calculator. ");
		Scanner userInput = new Scanner(System.in);
		// running the program and allowing for user input or closing the program
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
		// clear global variables between each run
		w1 = n1 = d1 = 0;
		w2 = n2 = d2 = 0;
		wr = nr = dr = 0;

		//
		// Parse operand1, operator, operand2
		//
		int idx = input.indexOf(' ');
		if (idx == -1) {
			// returns an error if the user inputs the expression incorrectly
			return "ERROR: Input is in an invalid format.";
		}
		// parses the first value in the expression
		String val1 = input.substring(0, idx);
		String remaining = input.substring(idx + 1);
		idx = remaining.indexOf(' ');
		if (idx == -1) {
			// returns an error if the user inputs the expression incorrectly
			return "ERROR: Input is in an invalid format.";
		}
		op = remaining.substring(0, idx);
		if (!op.equals("+") && !op.equals("-") && !op.equals("*") && !op.equals("/")) {
			return String.format("ERROR: Invalid operator '%s'", op);
		}
		remaining = remaining.substring(idx + 1);
		String val2 = remaining;

		//
		// Parse first operand into parts (whole, numerator, denominator)
		//
		idx = val1.indexOf('_');
		if (idx != -1) {
			String tmp = val1.substring(0, idx);
			if (tmp.length() == 0) {
				// returns an error if the user inputs the expression incorrectly
				return "ERROR: Input is in an invalid format.";
			}
			w1 = Integer.parseInt(tmp);
			val1 = val1.substring(idx + 1);
			idx = val1.indexOf("/");
			if (idx == -1) {
				// returns an error if the user inputs the expression incorrectly
				return "ERROR: Input is in an invalid format.";
			}
			n1 = Integer.parseInt(val1.substring(0, idx));
			d1 = Integer.parseInt(val1.substring(idx + 1));
		} else {
			idx = val1.indexOf('/');
			if (idx != -1) {
				w1 = 0;
				n1 = Integer.parseInt(val1.substring(0, idx));
				d1 = Integer.parseInt(val1.substring(idx + 1));
			} else {
				n1 = 0;
				d1 = 1;
				w1 = Integer.parseInt(val1);
			}
		}
		if (d1 == 0) {
			// returns an error if the user inputs an operand with zero as the denominator
			return "ERROR: Cannot divide by zero.";
		}

		//
		// Parse second operand into parts (whole, numerator, denominator)
		//
		idx = val2.indexOf('_');
		if (idx != -1) {
			w2 = Integer.parseInt(val2.substring(0, idx));
			val2 = val2.substring(idx + 1);
			idx = val2.indexOf("/");
			if (idx == -1) {
				// returns an error if the user inputs the expression incorrectly
				return "ERROR: Input is in an invalid format.";
			}
			n2 = Integer.parseInt(val2.substring(0, idx));
			d2 = Integer.parseInt(val2.substring(idx + 1));

		} else {
			idx = val2.indexOf('/');
			if (idx != -1) {
				n2 = Integer.parseInt(val2.substring(0, idx));
				d2 = Integer.parseInt(val2.substring(idx + 1));
				w2 = 0;

			} else {
				n2 = 0;
				d2 = 1;
				w2 = Integer.parseInt(val2);
			}
		}
		if (d2 == 0) {
			// returns an error if the user inputs an operand with zero as the denominator
			return "ERROR: Cannot divide by zero.";
		}

		//
		// Calculate results into globals wr, nr, dr
		//
		compute();

		//
		// Format the whole and fractional parts
		//
		String w = "";
		String f = "";
		if (wr != 0) {
			w = String.format("%d", wr);
		}
		if (nr != 0) {
			f = String.format("%d/%d", nr, dr);
		}

		//
		// Format the final result string
		//
		String result;
		if (w != "" && f != "") {
			// both whole and fractional parts have value
			result = String.format("%s_%s", w, f);
		} else if (w != "") {
			// only whole part has value
			result = w;
		} else if (f != "") {
			// only fractional part has value
			result = f;
		} else {
			// neither parts have value
			result = "0";
		}

		return result;

	}

	public static void compute() {

		//
		// Convert first operand to an improper fraction
		//
		int tempN = (Math.abs(w1) * d1) + n1;
		if (w1 < 0) {
			tempN = -(Math.abs(tempN));
		}
		w1 = 0;
		n1 = tempN;

		//
		// Convert second operand to an improper fraction
		//
		tempN = (Math.abs(w2) * d2) + n2;
		if (w2 < 0) {
			tempN = -(Math.abs(tempN));
		}
		w2 = 0;
		n2 = tempN;

		int newWhole1 = Math.abs(w1);
		int newWhole2 = Math.abs(w2);
		int newNum1 = (d1 * newWhole1) + n1;
		int newNum2 = (d2 * newWhole2) + n2;
		//
		// computes expression
		//
		if (op.equals("+")) {
			nr = (newNum1 * d2) + (newNum2 * d1);
			dr = d1 * d2;
		} else if (op.equals("-")) {
			nr = (newNum1 * d2) - (newNum2 * d1);
			dr = d1 * d2;
		} else if (op.equals("*")) {
			nr = newNum1 * newNum2;
			dr = d1 * d2;
		} else if (op.equals("/")) {
			nr = newNum1 * d2;
			dr = d1 * newNum2;
		}

		if (nr != 0) {

			if ((nr < 0 && dr > 0) || (dr < 0 && nr > 0)) {
				// either the numerator or denominator is negative
				nr = -(Math.abs(nr));
				dr = Math.abs(dr);
			} else {
				// both the numerator and denominator are positive or both are negative
				nr = Math.abs(nr);
				dr = Math.abs(dr);
			}

			// Get greatest common divisor of numerator and denominator and use it to reduce
			int gcd = gcd(Math.abs(nr), Math.abs(dr));
			nr = nr / gcd;
			dr = dr / gcd;

			// converts improper fraction to whole fraction
			boolean isNegative = false;
			if (nr < 0) {
				// checking if the numerator is negative
				isNegative = true;
				nr = Math.abs(nr);
			}
			if (nr >= dr) {
				// finding the whole number result and the numerator result
				wr = nr / dr;
				nr = nr % dr;
			}
			if (isNegative) {
				// checking if numerator was originally negative to make whole number negative
				if (wr != 0) {
					wr = -wr;
				} else {
					nr = -nr;
				}
			}
		}
	}

	// finding greatest common denominator
	public static int gcd(int num1, int num2) {
		if (num2 > num1) {
			// swap num1 and num2 to ensure num2 is smaller
			int tmp = num1;
			num1 = num2;
			num2 = tmp;
		}

		int gcd = num2;
		while ((num1 % gcd != 0) || (num2 % gcd != 0)) {
			gcd--;
		}

		return gcd;
	}

}
