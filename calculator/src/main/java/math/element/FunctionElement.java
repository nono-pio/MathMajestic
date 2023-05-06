package math.element;

import math.tools.StringFormat;
import math.tools.StringSettings;
import math.element.primary.Number;

public abstract class FunctionElement extends Element {

	Element value;

	// <------------ Constructor ------------>

	public FunctionElement(Element value) {
		this.value = value;
	}

	// <----------------- Type -------------->

	public abstract ElementType getType();

	public abstract String functionName();

	// <---------------- Values ------------->

	public Element[] getValues() {
		return new Element[] { value };
	}

	public void setValues(Element[] values) {
		value = values[0];
	}

	public abstract Element clone();

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		
		String str = toString(settings, values[0]);
		
		if (parentType == ElementType.Power)
			return StringFormat.bracket(str, settings.isLaTeX);
		
		return str;
	}

	public String toString(StringSettings settings, String value) {
		return functionName() + StringFormat.bracket(value, settings.isLaTeX);
	}

	// <--------------- Math ---------------->

	public Element recipFunction(int[] path, Element curRecip) {
		return value.recipFunction(newPath(path), recipFunction(curRecip));
	}

	public abstract Element recipFunction(Element y);

	public abstract Element clonedSimplify();

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return toValue(values[0]);
	}

	public abstract Number toValue(Number value);

}
