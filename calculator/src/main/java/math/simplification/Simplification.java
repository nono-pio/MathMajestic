package math.simplification;

import math.element.Element;
import math.element.ElementType;
import math.element.PrimaryElement;
import math.element.elements.Power;
import math.element.primary.Number;

public abstract class Simplification {

	public abstract boolean isEqual(Simplification simplification);

	public abstract Simplification simplify();

	public abstract Element toElement();

	public Simplification[] getSimplifications(Element element) {

		Element[] values = element.getValues();
		Simplification[] simplifications = new Simplification[values.length];

		for (int i = 0; i < simplifications.length; i++) {
			simplifications[i] = getSimplification(values[i]).simplify();

			// Test if all numbers
		}

		// if all numbers return new NumberSimplification of result of number

		return simplifications;
	}

	public static Simplification getSimplification(Element element) {

		// reduce Number

		if (element instanceof Number number) {
			return new NumberSimplification(number);
		} else if (element instanceof PrimaryElement primary) {
			return new PrimarySimplification(primary);
		} else if (element.getType() == ElementType.Addition || element.getType() == ElementType.Product
				|| element.getType() == ElementType.Division
				|| (element instanceof Power pow && pow.exponent.getType() == ElementType.Number)) {
			return new InfinitSimplification(element);
		}

		return new DefaultSimplification(element);
	}
}
