package math;

import exeptions.math.DivideByZeroException;
import exeptions.math.NegativeSqrtException;
import exeptions.math.ZeroPowerZeroException;
import math.element.primary.Number;

public class MathN {

	// basic

	public static Number add(Number a, Number b) {
		return new Number(a.value + b.value);
	}

	public static Number sub(Number a, Number b) {
		return new Number(a.value - b.value);
	}

	public static Number mult(Number a, Number b) {
		return new Number(a.value * b.value);
	}

	public static Number div(Number a, Number b) {
		if (b.value == 0)
			throw new DivideByZeroException();

		return new Number(a.value / b.value);
	}

	public static Number pow(Number a, Number b) {
		if (a.value == 0 && b.value == 0)
			throw new ZeroPowerZeroException();

		return new Number((float) Math.pow(a.value, b.value));
	}

	public static Number sqrt(Number a, Number b) {
		if (b.value < 0)
			throw new NegativeSqrtException();

		return new Number((float) Math.pow(a.value, 1 / b.value));
	}

	// log

	public static Number log(Number a, Number b) {
		return new Number((float) (Math.log(a.value) / Math.log(b.value)));
	}

	// trigo

	public static Number sin(Number a) {
		return new Number((float) Math.sin(a.value));
	}

	public static Number asin(Number a) {
		return new Number((float) Math.asin(a.value));
	}

	public static Number cos(Number a) {
		return new Number((float) Math.cos(a.value));
	}

	public static Number acos(Number a) {
		return new Number((float) Math.acos(a.value));
	}

	public static Number tan(Number a) {
		return new Number((float) Math.tan(a.value));
	}

	public static Number atan(Number a) {
		return new Number((float) Math.atan(a.value));
	}

	// sum / product

	public static Number sum(Number... numbers) {
		Number sum = new Number(0);
		for (int i = 0; i < numbers.length; i++) {
			sum.add(numbers[i]);
		}
		return sum;
	}

	public static Number product(Number... numbers) {
		Number sum = new Number(1);
		for (int i = 0; i < numbers.length; i++) {
			sum.mult(numbers[i]);
		}
		return sum;
	}

	// cste

	public static final Number PI = new Number((float) Math.PI);
	public static final Number E = new Number((float) Math.E);
}
