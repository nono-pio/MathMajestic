package math.element.functions;

import math.MathN;
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

	// <------------- String ---------------->

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		// TODO
		return y;
	}

	public Element derivative() {
		return new Product(new Number(-1), new Sin(this.value.clone()));
	}
	
	public Element clonedSimplify() {
		// TODO
		return this;
	}


	// <---------------- ToValue ------------>

	public Number toValue(Number value) {
		return MathN.cos(value);
	}
}