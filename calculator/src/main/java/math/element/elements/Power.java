package math.element.elements;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.functions.Log;
import math.element.primary.Number;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.math.AdditionExtention;
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

	public Element clonedSimplify() {

		if (exponent.getType() == ElementType.Number) {
			Number exp = (Number) exponent;
			if (exp.isEqual(new Number(1)))
				return base;
			else if (exp.isEqual(new Number(0)))
				return new Number(1);
			else if (exp.isInteger() && base.getType() == ElementType.Addition)
				return AdditionExtention.Power((Addition) base, exp.toInteger());
		}

		if (exponent.getType() == ElementType.Log) {
			Log exp = (Log) exponent;
			if (base.isEqual(exp.base))
				return exp.value;
		}

		if (base.getType() == ElementType.Number) {
			Number bas = (Number) base;
			if (bas.isEqual(new Number(1)))
				return new Number(1);
			else if (bas.isEqual(new Number(0)))
				return new Number(0);
		}

		if (base.getType() == ElementType.Power) {
			Power bas = (Power) base;
			return new Power(bas.base, new Product(exponent, bas.exponent).clonedSimplify()).clonedSimplify();
		}

		return this;
	}
	
	@Override
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

	public Number toValue(Number[] values) {
		return MathN.pow(values[0], values[1]);
	}
}
