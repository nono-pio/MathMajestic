package math.elements;

import math.MathN;
import math.ParentClass.Element;
import math.ParentClass.ElementType;
import math.math.AdditionExtention;
import math.numbers.Number;
import math.tools.StringFormat;

public class Power extends Element {
	
	public Element base;
	public Element exponent;
	
	public Power(Element base, Element exponent)
	{
		this.base = base;
		this.exponent = exponent;
	}

	public ElementType getType() { return ElementType.Power; }
	public Number toValue() { return MathN.pow(base.toValue(), exponent.toValue()); }
	public Element recipFunction(int[] path, Element curRecip) {
		if (path[0] == 0)
		{
			Element newRecip = new Power(curRecip, new Division(new Number(1), exponent));
			return base.recipFunction(newPath(path), newRecip);
		} else
		{
			Element newRecip = new Log(curRecip, base);
			return exponent.recipFunction(newPath(path), newRecip);
		}
	}
	public Element[] getValues() { return new Element[] { base, exponent }; }
	public void setValues(Element[] values) {
		base = values[0];
		exponent = values[1]; 
	}
	public String toString(ElementType parentType, boolean isLaTeX) {
		String str;
		if (isLaTeX)
			str = "{" + base.toString(getType(), isLaTeX) + "}^{" + exponent.toString(getType(), isLaTeX) +"}";
		else
			str = "(" + base.toString(getType(), isLaTeX) + ")^(" + exponent.toString(getType(), isLaTeX) +")";
		if (parentType == ElementType.Power)
			return StringFormat.bracket(str, isLaTeX);
		else return str;
	}
	public Element clone() { return new Power(base.clone(), exponent.clone()); }
	public Element clonedSimplify() {
		
		if (exponent.getType() == ElementType.Number)
		{
			Number exp = (Number) exponent;
			if (exp.isEqual(new Number(1))) return base.clonedSimplify();
			else if (exp.isEqual(new Number(0))) return new Number(1);
			else if (exp.isInteger() && base.getType() == ElementType.Addition)
				return AdditionExtention.Power((Addition) base, exp.toInteger()).simplify();
		}
		
		if (exponent.getType() == ElementType.Log)
		{
			Log exp = (Log) exponent;
			if (base.isEqual(exp.base))
				return exp.value.clonedSimplify();
		}
		
		if (base.getType() == ElementType.Number)
		{
			Number bas = (Number) base;
			if (bas.isEqual(new Number(1))) return new Number(1);
			else if (bas.isEqual(new Number(0))) return new Number(0);
		}
		
		if (base.getType() == ElementType.Power)
		{
			Power bas = (Power) base;
			return new Power(bas.base, new Product(exponent, bas.exponent).clonedSimplify()).clonedSimplify();
		}
		
		return this;
		}

}
