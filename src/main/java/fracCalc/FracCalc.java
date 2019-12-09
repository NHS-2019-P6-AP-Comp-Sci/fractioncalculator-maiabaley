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

	// second operand
	public static int w2;
	public static int n2;
	public static int d2;

	// result
	public static int wr;
	public static int nr;
	public static int dr;

	// operator
	public static String op;

	public static void main(String[] args) {
		System.out.println("This is the Fraction Calculator. ");
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
		// clear global variables between each run
		w1 = n1 = d1 = 0;
		w2 = n2 = d2 = 0;
		wr = nr = dr = 0;

		//
		//  Parse operand1, operator, operand2
		//
		int idx = input.indexOf(' ');
		if(idx==-1) {
			return "ERROR: Input is in an invalid format.";
		}
		String val1 = input.substring(0, idx);
		String remaining = input.substring(idx + 1);
		idx = remaining.indexOf(' ');
		if(idx==-1) {
			return "ERROR: Input is in an invalid format.";
		}
		op = remaining.substring(0, idx);
		if(!op.equals("+") && !op.equals("-") && !op.equals("*") && !op.equals("/") ) {
			return String.format("ERROR: Invalid operator '%s'", op);
		}
		remaining = remaining.substring(idx + 1);
		String val2 = remaining;

		//
		// Parse first operand into parts
		//
		idx = val1.indexOf('_');
		if (idx != -1) {
			String tmp = val1.substring(0, idx);
			if(tmp.length()==0) {
				return "ERROR: Input is in an invalid format.";
			}
			w1 = Integer.parseInt(tmp);
			val1 = val1.substring(idx + 1);
			idx = val1.indexOf("/");
			if(idx==-1) {
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
		if(d1==0) {
			return "ERROR: Cannot divide by zero.";
		}
		
		//
		// Parse second operand into parts
		//
		idx = val2.indexOf('_');
		if (idx != -1) {
			w2 = Integer.parseInt(val2.substring(0, idx));
			val2 = val2.substring(idx + 1);
			idx = val2.indexOf("/");
			if(idx==-1) {
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
		if(d2==0) {
			return "ERROR: Cannot divide by zero.";
		}

		System.out.printf("Parsed: %d %d/%d %s %d %d/%d\n", w1, n1, d1, op, w2, n2, d2);

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

		System.out.println("RESULT: " + result);

		return result;

// 		if (wr != 0) {
//			return String.format("%d_%d/%d", wr, nr, dr);
//		} else {
//			return String.format("%d/%d", nr, dr);
//		}
	}

	public static void compute() {

		//
		// Convert first operand to improper
		//
		int tempN = (Math.abs(w1) * d1) + n1;
		if (w1 < 0) {
			tempN = -(Math.abs(tempN));
		}
		w1 = 0;
		n1 = tempN;

		//
		// Convert second operand to improper
		//
		tempN = (Math.abs(w2) * d2) + n2;
		if (w2 < 0) {
			tempN = -(Math.abs(tempN));
		}
		w2 = 0;
		n2 = tempN;

		//
		// Compute operation
		//
		int newWhole1 = Math.abs(w1);
		int newWhole2 = Math.abs(w2);
		int newNum1 = (d1 * newWhole1) + n1;
		int newNum2 = (d2 * newWhole2) + n2;
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

		System.out.println(String.format("Result before reducing: %d %d/%d", wr, nr, dr));

		if (nr != 0) {

			if ((nr < 0 && dr > 0) || (dr < 0 && nr > 0)) {
				// one of numerator or denominator is negative
				nr = -(Math.abs(nr));
				dr = Math.abs(dr);
			} else {
				// both numerator and denominator are positive or negative
				nr = Math.abs(nr);
				dr = Math.abs(dr);
			}

			int gcd = gcd(Math.abs(nr), Math.abs(dr));

			System.out.println(String.format("GCD of %d and %d is %d", nr, dr, gcd));

			nr = nr / gcd;
			dr = dr / gcd;

			System.out.println(String.format("Result after reducing: %d %d/%d", wr, nr, dr));

			// converts improper fraction to whole
			boolean isNegative = false;
			if (nr < 0) {
				isNegative = true;
				nr = Math.abs(nr);
			}
			if (nr >= dr) {
				wr = nr / dr;
				nr = nr % dr;
			}
			if (isNegative) {
				if(wr!=0) {
					wr = -wr;
				}
				else {
					nr = -nr;
				}
			}
		}
	}

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
