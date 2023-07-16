package math.element.functions;

import math.element.Element;
import math.element.ElementType;
import math.element.FunctionElement;

public class Acos extends FunctionElement {

	// <------------ Constructor ------------>

	public Acos(Element value) {
		super(value);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Cos;
	}

	public String functionName() {
		return "acos";
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Acos(value.clone());
	}

	// <------------- String ---------------->

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		return new Cos(y);
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
		return Math.acos(value.calculateReal());
	}
}