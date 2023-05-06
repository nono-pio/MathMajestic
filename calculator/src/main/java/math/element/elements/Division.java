package math.element.elements;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.tools.ErrorMessage;
import math.tools.StringFormat;
import math.tools.StringSettings;

public class Division extends Element{

    public Element numerator;
    public Element denominator;

    public Division(Element numerator, Element denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public ElementType getType() { return ElementType.Division; }
    public Number toValue(Number[] values) { return MathN.div(values[0], values[1]); }
    public Element[] getValues() { return new Element[] {numerator, denominator}; }
    public Element recipFunction(int[] path, Element curRecip)
    {
        Element newRecip;
        if (path[0] == 0)
        {
            newRecip = new Product(curRecip, denominator);

        } else
        {
            newRecip = new Division(numerator, curRecip);
        }

        return (path[0] == 0 ? numerator : denominator).recipFunction(newPath(path), newRecip);
    }
    public Element simplify() {
        
        Element numSim = numerator.simplify();
        if (numSim.getType() == ElementType.Number && ((Number) numSim).isZero()) return new Number(0);

        Element denSim = denominator.simplify();
        if (denSim.getType() == ElementType.Number)
        {
            Number denN = (Number) denSim;
            if (denN.isZero()) throw ErrorMessage.DividedByZero();
            else if (denN.isEqual(new Number(1))) return numSim;

            if (numSim.getType() == ElementType.Number) return MathN.div((Number) numSim, denN);
        }

        return new Division(numSim, denSim);
    }

	public void setValues(Element[] values) {
		this.numerator = values[0];
		this.denominator = values[1];
	}
	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		if (settings.isLaTeX)
			return "\\frac{" + values[0] + "}{"+ values[1] +"}";
		else {
			String str = values[0] + "/" + values[1];
			if (parentType == null || parentType == ElementType.Addition) return str;
			else return StringFormat.bracket(str, settings.isLaTeX);
		}
	}
	public Element clone() { return new Division(numerator.clone(), denominator.clone()); }

	@Override
	public Element clonedSimplify() {
		// TODO Auto-generated method stub
		return null;
	}
}
