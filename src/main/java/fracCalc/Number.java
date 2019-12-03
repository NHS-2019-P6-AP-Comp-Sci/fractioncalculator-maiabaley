package fracCalc;

public class Number {
	
	public boolean IsNegative;
	public int Whole;
	public int Numerator;
	public int Denominator;
	
	public String toString() {
		if (Whole != 0) {
			return String.format("%d_%d/%d", Whole, Numerator, Denominator);
		}
		else {
			return String.format("%d/%d", Numerator, Denominator);
		}
	}

	public boolean isNegative() {
		if (Whole == 0) {
			return IsNegative;
		}
		return Whole < 0;
	}
	public void convertToImproper() {
		int newN = (Math.abs(Whole) * Denominator) + Numerator;
		if (Whole < 0) {
			newN = -(Math.abs(newN));
		}
		Whole = 0;
		Numerator = newN;
	}
	
}
