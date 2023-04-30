package math.elements;

import math.MathN;
import math.ParentClass.Element;
import math.ParentClass.ElementType;
import math.numbers.Number;
import math.tools.StringFormat;
import math.tools.StringSettings;

public class Log extends Element {
	
	public Element base;
	public Element value;
	
	public Log(Element value) {
		this.base = MathN.E; 
		this.value = value;
	}
	public Log(Element base, Element value) {
		this.base = base;
		this.value = value;
	}

	public ElementType getType() { return ElementType.Log; }
	public Number toValue(Number[] values) { return MathN.log(values[0], values[1]); }
	public Element recipFunction(int[] path, Element curRecip) {
		if (path[0] == 0)
		{
			Element newRecip = new Power(value, new Division(new Number(1), curRecip));
			return base.recipFunction(newPath(path), newRecip);
		} else
		{
			Element newRecip = new Power(base, curRecip);
			return value.recipFunction(newPath(path), newRecip);
		}
	}
	public Element[] getValues() { return new Element[] { base, value}; }
	public void setValues(Element[] values) {
		base = values[0];
		value = values[1];
	}
	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		String str;
		if (settings.isLaTeX)
		{
			String strBase = "log_{\\small "+ values[0] +"}";
			str = strBase + "{" + StringFormat.bracket(values[1], settings.isLaTeX) + "}";
		} else
			str = "log_" + StringFormat.bracket(values[0], settings.isLaTeX) + StringFormat.bracket(values[1], settings.isLaTeX);
		if (parentType == ElementType.Power)
			return StringFormat.bracket(str, settings.isLaTeX);
		else return str;
	}
	public Element clone() { return new Log(base.clone(), value.clone()); }
	public Element clonedSimplify() { return this;
		
		
	}

}
