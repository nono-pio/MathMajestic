package api;

import math.ParentClass.Element;
import math.ParentClass.ElementType;

public class ElementTree {

	private final ElementType type;
	private final ElementTree[] values;
	
	public ElementTree(Element element)
	{
		this.type = element.getType();
		
		Element[] values = element.getValues();
		this.values = new ElementTree[values.length];
		for (int i = 0; i < values.length; i++) {
			this.values[i] = new ElementTree(values[i]);
		}
	}

	public ElementType getType() {
		return type;
	}

	public ElementTree[] getValues() {
		return values;
	}
	
}
