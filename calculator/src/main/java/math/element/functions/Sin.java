package math.element.functions;

import math.element.Element;
import math.element.ElementType;
import math.element.FunctionElement;

public class Sin extends FunctionElement {

	// <------------ Constructor ------------>

	public Sin(Element value) {
		super(value);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Sin;
	}

	public String functionName() {
		return "sin";
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Sin(value.clone());
	}

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		return new Asin(y);
	}

	public Element derivative() {
		return new Cos(value.clone());
	}

	public Element develop() {
		// TODO
		return this;
	}

	// <---------------- ToValue ------------>

	public double calculateReal() {
		return Math.sin(value.calculateReal());
	}
}
