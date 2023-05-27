package math.element.elements;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.tools.StringFormat;

public class Division extends Element {

	public Element numerator;
	public Element denominator;

	// <------------ Constructor ------------>

	public Division(Element numerator, Element denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Division;
	}

	// <---------------- Values ------------->

	public Element[] getValues() {
		return new Element[] { numerator, denominator };
	}

	public void setValues(Element[] values) {
		this.numerator = values[0];
		this.denominator = values[1];
	}

	public Element clone() {
		return new Division(numerator.clone(), denominator.clone());
	}

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		if (settings.isLaTeX)
			return "\\frac{" + values[0] + "}{" + values[1] + "}";
		else {
			String str = values[0] + "/" + values[1];
			if (parentType == null || parentType == ElementType.Addition)
				return str;
			else
				return StringFormat.bracket(str, settings.isLaTeX);
		}
	}

	// <--------------- Math ---------------->

	public Element recipFunction(int[] path, Element curRecip) {
		Element newRecip;
		if (path[0] == 0) {
			newRecip = new Product(curRecip, denominator);

		} else {
			newRecip = new Division(numerator, curRecip);
		}

		return (path[0] == 0 ? numerator : denominator).recipFunction(newPath(path), newRecip);
	}

	public Element develop() {
		// TODO
		return null;
	}

	public Element derivative(DerivativeSettings settings, int index) {

		if (index == 0) {
			return new Division(new Number(1), denominator.clone());
		} else
			return new Division(
					new Product(new Number(-1), numerator.clone()),
					new Power(denominator.clone(), new Number(2)));
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return MathN.div(values[0], values[1]);
	}
}
