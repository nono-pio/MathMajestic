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

		System.out.println("|-| Start : " + element);

		boolean isPrimary = reduceNumber();
		if (isPrimary) {
			return element;
		}

		System.out.println("|-| Step red Num : " + element);

		develop();

		System.out.println("|-| Step develop : " + element);

		isPrimary = reduceNumber();
		if (isPrimary) {
			return element;
		}

		System.out.println("|-| Step red Num : " + element);

		simplifie();

		return element;
	}

	public void simplifie() {
		element = simplifie(element);
	}

	public static Element simplifie(Element element) {

		simplifieValues(element);

		return element;

	}

	public static Element simplifieValues(Element element) {

		Element[] values = element.getValues();

		for (int i = 0; i < values.length; i++) {
			values[i] = simplifie(values[i]);
		}

		element.setValues(values);

		return element;

	}

	public ElementCoef getElementCoef(InfinitElement infinitElement) {

		ElementCoef elementCoef = new ElementCoef();

		for (int i = 0; i < infinitElement.size(); i++) {

			Number coef = infinitElement.getCoef(i);
			Element element = infinitElement.getElement(i);

			elementCoef.add(coef, element);

		}

		return elementCoef;
	}

	public void develop() {
		element = develop(element);
	}

	public static Element develop(Element element) {

		Element[] values = element.getValues();

		for (int i = 0; i < values.length; i++) {
			values[i] = develop(values[i]);
		}

		element.setValues(values);

		return element.develop();
	}

	// s'occupper des numbers ln(6) -> 2.3 3+5+x -> 8+x
	public boolean reduceNumber() {
		element = reduceNumber(element);
		return element instanceof PrimaryElement;
	}

	public static Element reduceNumber(Element element) {

		if (element instanceof PrimaryElement)
			return element;

		Element[] values = element.getValues();

		boolean allNumber = true;

		for (int i = 0; i < values.length; i++) {
			values[i] = reduceNumber(values[i]);

			if (values[i].getType() != ElementType.Number) {
				allNumber = false;
			}
		}

		if (allNumber) {
			return element.toValue(toNumberArray(values));
		}

		element.setValues(values);

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
