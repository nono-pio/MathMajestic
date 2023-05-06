package math.forms;

import math.element.Element;
import math.element.elements.Power;
import math.element.elements.Product;
import math.element.primary.Number;

public class Monome {

	Element coef;
	Element variable;
	int degree;
	
	public Monome(Element coef, Element variable, int degree) {
		this.coef = coef;
		this.variable = variable;
		this.degree = degree;
	}
	
	public Element toElement()
	{
		return new Product(coef, new Power(variable, new Number(degree)));
	}

	@Override
	public String toString() {
		return coef + "*" + variable + "^" + degree;
	}
}
