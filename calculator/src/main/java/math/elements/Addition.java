package math.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import math.MathN;
import math.ParentClass.Element;
import math.ParentClass.ElementType;
import math.numbers.Number;
import math.tools.StringFormat;
import math.tools.StringSettings;
import math.tools.Tools;
import math.tools.ElementCoef;


public class Addition extends Element{

    public List<Element> values;

    public Addition(Element ... values) { this.values = List.of(values); }
    public Addition(Element value1, Element value2, boolean subtract)
    {
        if (subtract)
            this.values = List.of(value1, new Product(new Number(-1), value2));
        else
        	this.values = List.of(value1, value2);
    }
    public Addition(List<Element> values) { this.values = values; }
    
	public Number toValue(Number[] values) { return MathN.sum(values); }
    public Element[] getValues() { return values.toArray(new Element[values.size()]); }
    public ElementType getType() { return ElementType.Addition; }
    public Element recipFunction(int[] path, Element curRecip) {
    	
        Element[] newRecip = new Element[values.size()];
        int index = 1;
        for (int i = 0; i < values.size(); i++) {
            if (i != path[0])
            {
                newRecip[index] = new Product(new Number(-1), values.get(i));
                index++;
            }
        }
        newRecip[0] = curRecip;

        return values.get(path[0]).recipFunction(newPath(path), new Addition(newRecip));
    }
	
	public void setValues(Element[] values) { this.values = List.of(values); }
	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		
		String str = StringFormat.arrayStr(values, " + ");
		
        if (parentType == null || parentType == ElementType.Division || parentType == ElementType.Addition) return str;
        else return StringFormat.bracket(str, settings.isLaTeX);
	}
	public Element clone() { return new Addition(Tools.cloneElementArray(getValues())); }
	public Element clonedSimplify() {
		
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i).getType() == ElementType.Addition)
			{
				values.addAll(((Addition) values.get(i)).values);
				values.remove(i);
			}
		}
		
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
        
        values = newValues;
        Collections.sort(values);
        
        return this;
	}
}
