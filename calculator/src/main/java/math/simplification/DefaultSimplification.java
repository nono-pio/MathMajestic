package math.simplification;

import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;

public class DefaultSimplification extends Simplification {

	Element element;
	Simplification[] values;
	ElementType type;

	public DefaultSimplification(Element element) {
		type = element.getType();
		values = getSimplifications(element);
		this.element = element;
	}

	public boolean isEqual(Simplification simplification) {
		if (simplification instanceof DefaultSimplification defaultSimp) {

			if (type != defaultSimp.type)
				return false;

			if (values.length != defaultSimp.values.length)
				return false;

			for (int i = 0; i < values.length; i++) {
				if (!values[i].isEqual(defaultSimp.values[i]))
					return false;

			}
			return true;

		}

		return false;
	}

	public Simplification simplify() {

		System.out.println("Simplify");

		boolean allNumbers = true;
		for (Simplification simplification : values) {
			if (!(simplification instanceof NumberSimplification)) {
				allNumbers = false;
			}
		}

		if (allNumbers) {

			Number[] valuesNumber = new Number[values.length];
			for (int i = 0; i < valuesNumber.length; i++) {
				valuesNumber[i] = ((NumberSimplification) values[i]).number;
			}

			Number number = element.toValue(valuesNumber);

			return new NumberSimplification(number);
		}

		return this;
	}

	public Element toElement() {
		return element;
	}

	public String toString() {

		StringBuilder str = new StringBuilder("[").append(type.name());

		for (Simplification simplification : values) {
			str.append(";").append(simplification.toString());
		}

		return str.append("]").toString();
	}

}
