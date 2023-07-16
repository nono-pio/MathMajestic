package math.element.elements;

import math.element.Element;
import math.element.ElementType;
import math.element.functions.Log;
import math.element.primary.Number;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.tools.StringFormat;

public class Power extends Element {

	public Element base;
	public Element exponent;

	// <------------ Constructor ------------>

	public Power(Element base, Element exponent) {
		this.base = base;
		this.exponent = exponent;
	}

	public Power(Element base, float exponent) {
		this.base = base;
		this.exponent = new Number(exponent);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Power;
	}

	// <---------------- Values ------------->

	public Element[] getValues() {
		return new Element[] { base, exponent };
	}

	public void setValues(Element[] values) {
		base = values[0];
		exponent = values[1];
	}

	public Element clone() {
		return new Power(base.clone(), exponent.clone());
	}

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		String str;
		if (settings.isLaTeX)
			str = "{" + values[0] + "}^{" + values[1] + "}";
		else
			str = "(" + values[0] + ")^(" + values[1] + ")";
		if (parentType == ElementType.Power)
			return StringFormat.bracket(str, settings.isLaTeX);
		else
			return str;
	}

	// <--------------- Math ---------------->

	public Element recipFunction(int[] path, Element curRecip) {
		if (path[0] == 0) {
			Element newRecip = new Power(curRecip, new Division(new Number(1), exponent));
			return base.recipFunction(newPath(path), newRecip);
		} else {
			Element newRecip = new Log(curRecip, base);
			return exponent.recipFunction(newPath(path), newRecip);
		}
	}

	public Element develop() {
		// TODO
		return this;
	}

	public Element derivative(DerivativeSettings settings, int index) {

		if (index == 0) {
			return new Product(exponent.clone(),
					new Power(base.clone(), new Addition(exponent.clone(), new Number(-1))));
		} else
			return new Product(new Log(base.clone()), this.clone());
	}

	// <---------------- ToValue ------------>

	public double calculateReal() {
		return Math.pow(base.calculateReal(), exponent.calculateReal());
	}
}
