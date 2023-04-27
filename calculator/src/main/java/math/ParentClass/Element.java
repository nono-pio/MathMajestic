package math.ParentClass;

import java.util.Arrays;

import math.numbers.Number;
import math.tools.Position;

public abstract class Element implements Comparable<Element> {
	
    // #region Abstract Method

	public abstract ElementType getType();

	public abstract Number toValue();
    public abstract Element recipFunction(int[] path, Element curRecip);

    public abstract Element[] getValues();
    public abstract void setValues(Element[] values);
    
    protected abstract String toString(ElementType parentType, boolean isLaTeX, String[] values);
    protected String toString(ElementType parentType, boolean isLaTeX)
    {
    	Element[] childs = getValues();
    	String[] childString = new String[childs.length];
    	for (int i = 0; i < childString.length; i++) {
			childString[i] = childs[i].toString(getType(), isLaTeX);
		}
    	return toString(parentType, isLaTeX, childString);
    }
    public String toString() { return toString(null, false); }
    public String toLaTeX() { return toString(null, true); } 
    
    public abstract Element clone();
    public abstract Element clonedSimplify();

    // #endregion
    
    public int compareTo(Element element2) {
		
    	if (getType() != element2.getType()) return getType().compareTo(element2.getType());
    	
		return Arrays.compare(getValues(), element2.getValues());
	}
    
    public Element simplify()
    {
    	Element cloneElement = clone();
    	Element[] values = cloneElement.getValues();
    	
    	boolean isCst = true;
    	Element[] valuesSimplified = new Element[values.length];
    	for (int i = 0; i < values.length; i++) {
    		valuesSimplified[i] = values[i].simplify();
    		if (valuesSimplified[i].getType() != ElementType.Number) isCst = false;
		}
    	
    	cloneElement.setValues(valuesSimplified);
    	
    	if (isCst) return cloneElement.toValue();
    	
    	return cloneElement.clonedSimplify();
    }
    
    public static int[] newPath(int[] curPath) { return Arrays.copyOfRange(curPath, 1, curPath.length); }
    
    public boolean isConstant()
    {
        if (getType() == ElementType.Variable) return false;
        for (Element child : getValues()) if (child.isConstant() == false) return false;
        return true;
    }

    public void forEach(IElement func)
    {
        forEachChild(func, new Position(0));
    }
    public void forEachChild(IElement func, Position position)
    {
        func.invoke(this, position);
        Element[] childs = getValues();
        for (int i = 0; i < childs.length; i++) {
            childs[i].forEachChild(func, position.generateNewPosition(getType(), i));
        }
    }
    public boolean isEqual(Element elem)
    {
    	if (getType() != elem.getType()) return false;
    	Element[] values = getValues();
    	Element[] values2 = elem.getValues();
    	
    	if (values.length != values2.length) return false;
    	for (int i=0; i < values.length; i++)
    		if (!values[i].isEqual(values2[i])) return false;
    	return true;
    }
}