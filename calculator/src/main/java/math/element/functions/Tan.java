package math.element.functions;

import math.element.Element;
import math.element.ElementType;
import math.element.FunctionElement;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.primary.Number;

public class Tan extends FunctionElement {

	// <------------ Constructor ------------>

	public Tan(Element value) {
		super(value);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Tan;
	}

	public String functionName() {
		return "tan";
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Tan(value.clone());
	}

	// <------------- String ---------------->

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		return new Atan(y);
	}

	public Element derivative() {
		return new Division(new Number(1), new Power(new Cos(this.value.clone()), new Number(2)));
	}

	public Element develop() {
		// TODO
		return this;
	}

	// <---------------- ToValue ------------>

	public double calculateReal() {
		return Math.tan(value.calculateReal());
	}
}