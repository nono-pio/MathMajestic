package math.element;

import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
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

	public Element develop() {
		return this;
	}

	public abstract Element derivative(DerivativeSettings settings);

	public Element derivative(DerivativeSettings settings, int index) {
		return null;
	}
}
