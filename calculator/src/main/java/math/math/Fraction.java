package math.math;

public class Fraction {

	public int numerator;
	public int denominator;

	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public float toFloat() {
		return numerator / (float) denominator;
	}

	public static Fraction valueOf(float value) {
		return valueOf(value, 6);
	}

	public static Fraction valueOf(float value, int precision) {
		int n = (int) value;
		float rest = value - n;

		if (rest == 0 || precision <= 0)
			return new Fraction(n, 1);

		Fraction fracRest = valueOf(1 / rest, precision - 1);

		return new Fraction(n * fracRest.numerator + fracRest.denominator, fracRest.numerator);
	}

	@Override
	public String toString() {
		return "Fraction [numerator=" + numerator + ", denominator=" + denominator + "]";
	}

}
