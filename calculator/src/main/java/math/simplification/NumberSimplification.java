package math.simplification;

import math.element.Element;
import math.element.primary.Number;

public class NumberSimplification extends Simplification {

	Number number;

	public NumberSimplification(Number number) {
		this.number = (Number) number.clone();
	}

	public boolean isEqual(Simplification simplification) {
		return simplification instanceof NumberSimplification num && num.number.isEqual(number);
	}

	public Simplification simplify() {
		return this;
	}

	public Element toElement() {
		return number;
	}

	public String toString() {
		return number.toString();
	}

}
