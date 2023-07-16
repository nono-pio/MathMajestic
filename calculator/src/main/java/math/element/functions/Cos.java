package math.element.functions;

import math.element.Element;
import math.element.ElementType;
import math.element.FunctionElement;
import math.element.elements.Product;
import math.element.primary.Number;

public class Cos extends FunctionElement {

	// <------------ Constructor ------------>

	public Cos(Element value) {
		super(value);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Cos;
	}

	public String functionName() {
		return "cos";
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Cos(value.clone());
	}

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		return new Acos(y);
	}

	public Element derivative() {
		return new Product(new Number(-1), new Sin(this.value.clone()));
	}

	public Element develop() {
		// TODO
		return this;
	}

	// <---------------- ToValue ------------>

	public double calculateReal() {
		return Math.cos(value.calculateReal());
	}
}