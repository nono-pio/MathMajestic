package math.forms;

import math.ParentClass.Element;
import math.elements.Power;
import math.elements.Product;
import math.numbers.Number;

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
