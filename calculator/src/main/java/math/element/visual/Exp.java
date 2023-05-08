package math.element.visual;

import math.MathN;
import math.element.Element;
import math.element.elements.Power;

public class Exp extends Power {


	public Exp(Element exponent) {
		super(MathN.E, exponent);
	}
	
	public Exp(Element base, Element exponent) {
		super(base, exponent);
	}

}
