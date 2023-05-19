package math.simplification;

import math.element.Element;
import math.element.primary.Number;

public interface InfinitElement {

	public Element reduceNumber(); // return Number or self
	public Number getCoef(int index);
	public Element getElement(int index);
	
	public int size();
	
}
