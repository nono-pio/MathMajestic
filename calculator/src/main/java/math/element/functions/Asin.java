package math.element.functions;

import math.element.Element;
import math.element.ElementType;
import math.element.FunctionElement;

public class Asin extends FunctionElement {

	// <------------ Constructor ------------>

	public Asin(Element value) {
		super(value);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Cos;
	}

	public String functionName() {
		return "asin";
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Asin(value.clone());
	}

	// <------------- String ---------------->

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		return new Sin(y);
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
		return Math.asin(value.calculateReal());
	}
}