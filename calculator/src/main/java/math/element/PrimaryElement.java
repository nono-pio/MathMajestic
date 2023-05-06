package math.element;

import math.tools.StringSettings;
import math.element.primary.Number;
import math.tools.ErrorMessage;

public abstract class PrimaryElement extends Element {

	// <----------------- Type -------------->

	public abstract ElementType getType();

	// <---------------- Values ------------->

	public Element[] getValues() {
		return new Element[0];
	}

	public void setValues(Element[] values) {

	}

	public abstract Element clone();

	// <------------- String ---------------->

	public abstract String toString(ElementType parentType, StringSettings settings, String[] values);

	// <--------------- Math ---------------->

	public Element recipFunction(int[] path, Element curRecip) {
		throw ErrorMessage.InvalidRecip(getType());
	}

	public Element clonedSimplify() {
		return this;
	}

	// <---------------- ToValue ------------>

	public abstract Number toValue(Number[] values);


}
