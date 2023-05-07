package math.element.functions;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.FunctionBaseElement;
import math.element.elements.Addition;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.elements.Product;
import math.element.primary.Number;

public class Log extends FunctionBaseElement {

	// <------------ Constructor ------------>

	public Log(Element value) {
		super(value, MathN.E);
	}

	public Log(Element base, Element value) {
		super(value, base);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Log;
	}

	public String functionName() {
		return "log";
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Log(value.clone(), base.clone());
	}

	// <------------- String ---------------->

	
	
	// <--------------- Math ---------------->

	public Element recipFunctionXBase(Element y) { // log_x(b) = y --> b^(1/y)
		return new Power(value, new Division(new Number(1), y));
	}

	public Element recipFunctionXValue(Element y) {
		return new Power(base, y); // log_b(x) = y --> x = b^y
	}

	public Element clonedSimplify() {
		// TODO
		return this;
	}
	
	public Element derivative(Element value, Element base) {
		
		if (base.isEqual(Number.zero)) {
			return derivativeValue(value);
		}
		
		if (value.isEqual(Number.zero)) {
			return derivativeBase(base);
		}
		
		return new Addition(derivativeValue(value), derivativeBase(base));
	}
	
	public Element derivativeValue(Element value) {
		return new Division(value, new Product(this.value.clone(), new Log(this.base.clone())));
	}
	
	public Element derivativeBase(Element base) {
		return new Division(
				new Product(new Number(-1), base, new Log(this.value.clone())),
				new Product(this.base.clone(), new Power(new Log(this.base.clone()), new Number(2))));
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number value, Number base) {
		return MathN.log(value, base);
	}
}
