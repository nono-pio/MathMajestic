package math.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import math.ParentClass.Element;
import math.ParentClass.ElementType;
import math.numbers.Number;
import math.tools.StringFormat;
import math.tools.Tools;
import math.tools.ElementCoef;


public class Addition extends Element{

    public Element[] values;

    public Addition(Element[] values) { this.values = values; }
    public Addition(Element value1, Element value2) { this.values = new Element[] {value1, value2}; }
    public Addition(Element value1, Element value2, boolean subtract)
    {
        if (subtract)
            this.values = new Element[] {value1, new Product(new Number(-1), value2)};
        else
            this.values = new Element[] {value1, value2};
    }
    public Addition(Element value1, Element value2, Element value3) { this.values = new Element[] {value1, value2, value3}; }
    public Addition(List<Element> list) {
		this.values = list.toArray(new Element[list.size()]); }
    
	public Number toValue() {
        Number sum = new Number(0);
        for (Element value : values) {
            sum.add(value.toValue());
        }
        return sum;
    }
    public Element[] getValues() {return values;}
    public ElementType getType() { return ElementType.Addition; }
    public Element recipFunction(int[] path, Element curRecip) {
        Element[] newRecip = new Element[values.length];
        int index = 1;
        for (int i = 0; i < values.length; i++) {
            if (i != path[0])
            {
                newRecip[index] = new Product(new Number(-1), values[i]);
                index++;
            }
        }
        newRecip[0] = curRecip;

        return values[path[0]].recipFunction(newPath(path), new Addition(newRecip));
    }
	
	public void setValues(Element[] values) { this.values = values; }
	public String toString(ElementType parentType, boolean isLaTeX) {
		
		String str = StringFormat.arrayStr(values, " + ", getType(), isLaTeX);
		
        if (parentType == null || parentType == ElementType.Division || parentType == ElementType.Addition) return str;
        else return StringFormat.bracket(str, isLaTeX);
	}
	public Element clone() { return new Addition(Tools.cloneElementArray(values)); }
	public Element clonedSimplify() {
		
		ArrayList<Element> newChilds = new ArrayList<>();
		for (Element child : values)
		{
			if (child.getType() == ElementType.Addition)
				newChilds.addAll(Arrays.asList(child.getValues()));
			else newChilds.add(child);
		}
		values = newChilds.toArray(new Element[newChilds.size()]);
		
		Number cste = new Number(0);
		ElementCoef elemCoef = new ElementCoef();
        
        for (Element child : values) {
            if (child.getType() == ElementType.Number)
                cste.add((Number) child);
            else {
            	Element elem;
            	Number coef;
            	if (child.getType() == ElementType.Product)
            	{
            		Product childPro = (Product) child;
            		elem = childPro.getRest();
            		coef = childPro.getCst();
            	} else
            	{
            		elem = child;
            		coef = new Number(1);
            	}
            	elemCoef.add(coef, elem);
            }
        }

        if (elemCoef.size() == 0) return cste;
        if (elemCoef.size() == 1 && cste.isZero()) return elemCoef.getElementProduct(0).clonedSimplify();
        
        ArrayList<Element> newValues = elemCoef.getElementsProduct();
        if (!cste.isZero()) newValues.add(cste);
        
        if (newValues.size() == 0) return new Number(0);
        else if (newValues.size() == 1) return newValues.get(0);
        
        values = newValues.toArray(new Element[newValues.size()]);
        Arrays.sort(values);
        
        return this;
	}
}
