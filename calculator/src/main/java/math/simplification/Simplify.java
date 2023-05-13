package math.simplification;

import math.element.Element;
import math.element.ElementType;
import math.element.PrimaryElement;
import math.element.primary.Number;

public class Simplify {

	public Element element;
	public SimplifySettings settings;

	public Simplify(Element element) {
		this.element = element;
	}

	public Element simplify() {

		boolean isPrimary = reduceNumber();
		if (isPrimary) {
			return element;
		}

		// dev

		// s'occupper des numbers ln(6) -> 2.3 3+5+x -> 8+x

		// simplifier

		return element;
	}

	// s'occupper des numbers ln(6) -> 2.3 3+5+x -> 8+x
	public boolean reduceNumber() {
		element = reduceNumber(element);
		return element instanceof PrimaryElement;
	}

	public static Element reduceNumber(Element element) {

		Element[] childs = element.getValues();
		Element[] newChilds = new Element[childs.length];

		boolean allNumber = !(childs.length == 0); // primary -> false // other -> true

		for (int i = 0; i < newChilds.length; i++) {
			newChilds[i] = reduceNumber(childs[i]);

			if (newChilds[i].getType() != ElementType.Number) {
				allNumber = false;
			}
		}

		if (allNumber) {
			return element.toValue(toNumberArray(newChilds));
		}

		element.setValues(newChilds);

		if (element instanceof InfinitElement inElement) {
			element = inElement.reduceNumber();
		}

		return element;
	}

	private static Number[] toNumberArray(Element[] elements) {
		Number[] result = new Number[elements.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = (Number) elements[i];
		}

		return result;
	}

}
