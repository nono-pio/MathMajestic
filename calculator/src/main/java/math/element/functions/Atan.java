package math.element.functions;

import math.element.Element;
import math.element.ElementType;
import math.element.FunctionElement;

public class Atan extends FunctionElement {

	// <------------ Constructor ------------>

	public Atan(Element value) {
		super(value);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Cos;
	}

	public String functionName() {
		return "atan";
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Atan(value.clone());
	}

	// <------------- String ---------------->

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		return new Tan(y);
	}

	public Element derivative() {
		// TODO
		return this;
	}

	public Element develop() {
		// TODO
		return this;
	}

	// <---------------- ToValue ------------>

	public double calculateReal() {
		return Math.atan(value.calculateReal());
	}
}