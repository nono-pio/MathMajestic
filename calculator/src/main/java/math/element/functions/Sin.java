package math.element.functions;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.FunctionElement;
import math.element.primary.Number;

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

	// <------------- String ---------------->

	// <--------------- Math ---------------->

	public Element recipFunction(Element y) {
		// TODO
		return y;
	}

	public Element derivative() {
		return new Cos(value.clone());
	}
	
	public Element clonedSimplify() {
		// TODO
		return this;
	}


	// <---------------- ToValue ------------>

	public Number toValue(Number value) {
		return MathN.sin(value);
	}
}
