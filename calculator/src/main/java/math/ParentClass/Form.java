package math.ParentClass;

import math.numbers.Number;
import math.tools.StringSettings;

/**
 * Form is a abstract class that extends Element.
 * Form is used for replace multiple math operation that represent regular mathematical expression as polynome.
 * 
 * 
 * @author Nolan Piccand
 *
 */
public abstract class Form extends Element {

	
	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Form;
	}

	// <---------------- Values ------------->

	public abstract Element toElement();
	
	public Element[] getValues() {
		return toElement().getValues();
	}

	public void setValues(Element[] values) {

	}

	public abstract Element clone();

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		return toElement().toString(parentType, settings, values);
	}

	// <--------------- Math ---------------->

	public Element recipFunction(int[] path, Element curRecip) {
		return toElement().recipFunction(path, curRecip);
	}

	public Element clonedSimplify() {
		return toElement().clonedSimplify();
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return toElement().toValue(values);
	}
}
