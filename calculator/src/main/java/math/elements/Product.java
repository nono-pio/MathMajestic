package math.elements;

import java.util.ArrayList;
import java.util.Arrays;

import math.MathN;
import math.ParentClass.Element;
import math.ParentClass.ElementType;
import math.math.AdditionExtention;
import math.numbers.Number;
import math.tools.StringFormat;
import math.tools.StringSettings;
import math.tools.Tools;
import math.tools.ElementCoef;


public class Product extends Element{

    public Element[] values;

    public Product(Element[] values) { this.values = values; }
    public Product(Element value1, Element value2) { this.values = new Element[]{ value1, value2 }; }
    public Product(Element value1, Element value2, Element value3) { this.values = new Element[]{ value1, value2, value3 }; }


    public ElementType getType() { return ElementType.Product; }
    public Number toValue(Number[] values) { return MathN.product(values); }
    public Element[] getValues() { return values; }
    public Element recipFunction(int[] path, Element curRecip) {
        
        Element[] div = new Element[values.length - 1];
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if (i != path[0])
            {
                div[index] = values[i];
                index++;
            }
        }

        return values[path[0]].recipFunction(newPath(path), new Division(curRecip, new Product(div)));
    }
    public Number getCst()
    {
    	for (Element element : values) {
			if (element.getType() == ElementType.Number) return (Number) element;
		}
    	return new Number(1);
    }
    public Element getRest()
    {
    	ArrayList<Element> rest = new ArrayList<Element>();
    	for (Element element : values) {
			if (element.getType() != ElementType.Number) rest.add(element);
		}
    	if (rest.size() == 1) return rest.get(0);
    	return new Product(rest.toArray(new Element[rest.size()]));
    }
	public void setValues(Element[] values) { this.values = values; }
	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		
		String str = StringFormat.arrayStr(values, settings.isLaTeX? " \\cdot " : " * ");
		
		if (parentType == null || parentType == ElementType.Addition) return str;
		else return StringFormat.bracket(str, settings.isLaTeX);
	}
	public Element clone() { return new Product(Tools.cloneElementArray(values)); }
	public Element clonedSimplify()
	{
		
		ArrayList<Element> newChilds = new ArrayList<>(); // extends the values with child Product and Sign
		for (Element child : values)
		{
			if (child.getType() == ElementType.Product)
				newChilds.addAll(Arrays.asList(child.getValues()));
			else newChilds.add(child);
		}
		values = newChilds.toArray(new Element[newChilds.size()]);
		
		// arrange child
		Number cste = new Number(1);
        ElementCoef elemCoef = new ElementCoef();
        ArrayList<Addition> additionChildren = new ArrayList<Addition>();
        
        for (Element child : values) {
            if ( child.getType() == ElementType.Number ) cste.mult((Number) child);
            else if (child.getType() == ElementType.Addition) additionChildren.add((Addition) child); 
            else
            {
            	Element elem;
            	Number coef;
            	if (child.getType() == ElementType.Power && ((Power) child).exponent.getType() == ElementType.Number)
            	{
            		Power childPow = (Power) child;
            		elem = childPow.base;
            		coef = (Number) childPow.exponent;
            	} else
            	{
            		elem = child;
            		coef = new Number(1);
            	}
            	elemCoef.add(coef, elem);
            }
        }

        if (cste.isZero()) return new Number(0);
        
        boolean hasCste = !cste.isEqual(new Number(1));
        
        if (additionChildren.size() == 0 && elemCoef.size() == 0) return cste;
        
        ArrayList<Element> pro = elemCoef.getElementsPower();
        
    	if (hasCste) pro.add(cste);
    	if (additionChildren.size() == 0)
    	{
    		Element[] newPro = pro.toArray( new Element[pro.size()]);
    		Arrays.sort(newPro);
    		return new Product(newPro);
    	}
    	
        if (additionChildren.size() == 1 && pro.size() == 0) return additionChildren.get(0).clonedSimplify();
        
        return AdditionExtention.Product(additionChildren.toArray(new Addition[additionChildren.size()]), pro).simplify();
	}
    
}
