package math.simplification;

import math.element.Element;
import math.element.ElementType;
import math.element.PrimaryElement;
import math.element.primary.Number;

public class Simplify {

	public Element element;
	public SimplifySettings settings;
	
	public static int indentLevel = 0;

	public Simplify(Element element) {
		this.element = element;
	}

	public Element simplify() {
		
		indentLevel++;

		print("Start");

		// <--------------------- Simplify Child ---------------->
		Element[] values = element.getValues();

		for (int i = 0; i < values.length; i++) {
			if (!(values[i] instanceof PrimaryElement))
				values[i] = values[i].simplify();
		}

		element.setValues(values);

		print("Simplify Child");

		// <--------------------- Reduce Number ------------------>

		boolean isPrimary = reduceNumber();
		
		print("Reduce Number");
		
		if (isPrimary) {
			indentLevel--;
			return element;
		}

		// <--------------------- Develop ------------------>

		develop();

		print("Develop");

		// <--------------------- Reduce Number ------------------>

		isPrimary = reduceNumber();
		
		print("Reduce Number");
		
		if (isPrimary) {
			indentLevel--;
			return element;
		}

		// <--------------------- Reduce ------------------>

		simplifiCurrentElement();

		print("Finish");
		
		indentLevel--;

		return element;
	}

	public void simplifiCurrentElement() {

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

		element.develop();

	}

	public boolean reduceNumber() {

		if (element instanceof PrimaryElement)
			return true;

		Element[] values = element.getValues();

		boolean allNumber = true;

		for (int i = 0; i < values.length; i++) {
			if (values[i].getType() != ElementType.Number) {
				allNumber = false;
			}
		}

		if (allNumber) {
			element = element.toValue(toNumberArray(values));
			return true;
		}

		if (element instanceof InfinitElement inElement) {
			element = inElement.reduceNumber();
		}

		return element instanceof PrimaryElement;
	}

	private static Number[] toNumberArray(Element[] elements) {
		Number[] result = new Number[elements.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = (Number) elements[i];
		}

		return result;
	}
	
	private void print(String step) {
		
		StringBuilder str = new StringBuilder("|");
		
		for (int i = 0; i < indentLevel; i++) {
			str.append("-|");
		}
		
		str.append(' ').append(step).append(" : ").append(element);
		
		System.out.println(str.toString());
		
	}

}
