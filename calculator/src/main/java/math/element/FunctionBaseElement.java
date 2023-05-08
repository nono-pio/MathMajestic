package math.element;

import math.tools.StringFormat;
import math.element.primary.Number;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;

public abstract class FunctionBaseElement extends Element {

	public Element value;
	public Element base;

	// <------------ Constructor ------------>

	public FunctionBaseElement(Element value, Element base) {
		this.value = value;
		this.base = base;
	}

	// <----------------- Type -------------->

	public abstract ElementType getType();

	public abstract String functionName();

	// <---------------- Values ------------->

	public Element[] getValues() {
		return new Element[] { value, base };
	}

	public void setValues(Element[] values) {
		value = values[0];
		base = values[1];
	}

	public abstract Element clone();

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {

		String str = toString(settings, values[0], values[1]);

		if (parentType == ElementType.Power)
			return StringFormat.bracket(str, settings.isLaTeX);

		return str;
	}

	public String toString(StringSettings settings, String value, String base) {
		String str;
		if (settings.isLaTeX) {
			String strBase = functionName() + "_{\\small " + base + "}";
			str = strBase + "{" + StringFormat.bracket(value, settings.isLaTeX) + "}";
		} else
			str = functionName() + "_" + StringFormat.bracket(base, settings.isLaTeX)
					+ StringFormat.bracket(value, settings.isLaTeX);
		return str;
	}

	// <--------------- Math ---------------->

	public Element recipFunction(int[] path, Element curRecip) {
		if (path[0] == 0)
			return value.recipFunction(newPath(path), recipFunctionXValue(curRecip));
		else
			return value.recipFunction(newPath(path), recipFunctionXBase(curRecip));
	}

	public abstract Element recipFunctionXBase(Element y);

	public abstract Element recipFunctionXValue(Element y);

	public abstract Element clonedSimplify();

	public abstract Element derivativeBase();

	public abstract Element derivativeValue();

	public Element derivative(DerivativeSettings settings, int index) {

		if (index == 0) {
			return derivativeValue();
		} else
			return derivativeBase();
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return toValue(values[0], values[1]);
	}

	public abstract Number toValue(Number value, Number base);

}
