package math.element.functions;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.FunctionBaseElement;
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

	@Override
	public Element develop() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public Element derivativeValue() {
		return new Division(new Number(1), new Product(value.clone(), new Log(base.clone())));
	}
	
	public Element derivativeBase() {
		return new Division(
				new Product(new Number(-1), new Log(value.clone())),
				new Product(base.clone(), new Power(new Log(base.clone()), new Number(2))));
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number value, Number base) {
		return MathN.log(value, base);
	}
}
