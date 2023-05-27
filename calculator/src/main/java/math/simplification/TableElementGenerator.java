package math.simplification;

import math.element.Element;
import math.element.primary.Number;

@FunctionalInterface
public interface TableElementGenerator {
	
	public Element generateElement(Element element, Number data);

}
