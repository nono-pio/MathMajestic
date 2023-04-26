package math.string_converter;

import math.ParentClass.Element;
import math.elements.*;

public enum PriorityOperator {

	Root (-1),
	
	RootElem (0),
	
	Add (5),
	Sub (4),
	Mult (3),
	Div (2),
	Pow (1);

	int power;
	PriorityOperator(int i) {
		power = i;
	}
	
	public Element toElement(Element[] values)
	{
		switch (this) {
		case Add : return new Addition(values);
		case Sub : return new Addition(values[0], values[1], true);
		case Mult : return new Product(values);
		case Div : return new Division(values[0], values[1]);
		case Pow : return new Power(values[0], values[1]);
		
		default :
		throw new IllegalArgumentException("Unexpected value: " + this);
		}
	}
}
