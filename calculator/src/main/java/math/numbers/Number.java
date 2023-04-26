package math.numbers;

import math.ParentClass.Element;
import math.ParentClass.ElementType;
import math.tools.ErrorMessage;

public class Number extends Element{
    
	public static final Number zero = new Number(0);
	
	
	public float value;
    public NumberType numberType;

    public Number(float realNumber) 
    {
    	if (realNumber == 0) value = 0;
    	else value = realNumber;
        numberType = NumberType.Real;
    }

    public void add(Number num) { value += num.value; }
    public void sub(Number num) { value -= num.value; }
    public void mult(Number num) { value *= num.value; }
    public void div(Number num) { value /= num.value; }

    public boolean isEqual(Number num)
    {
        return Math.abs(value - num.value) <= 0.005;
    }
    public boolean isZero() { return isEqual(new Number(0)); }
    public boolean isInteger() { return isEqual( new Number(Math.round(value))); }

    public int toInteger() { return (int) value; }
    
    public ElementType getType() { return ElementType.Number; }
    public Number toValue() { return this; }
    public Element[] getValues() { return new Element[0]; }
    public Number reciprocal(int[] path, Number value) { throw ErrorMessage.NumberRecip(); }
    public Element recipFunction(int[] path, Element curRecip) { throw ErrorMessage.NumberRecip(); }

	@Override
	public boolean isEqual(Element elem) {
		return elem.getType() == ElementType.Number && isEqual((Number) elem);
	}
	
    @Override
	public int compareTo(Element element2) {
		if (element2.getType() != getType()) return getType().compareTo(element2.getType());
		Number num = (Number) element2;
		return Float.compare(value, num.value);
	}

	public void setValues(Element[] values) {}
	public String toString(ElementType parentType, boolean isLaTeX) {
		if (isInteger()) return String.valueOf(toInteger());
		return String.valueOf(value); }
	public Element clone() { return new Number(value); }
	public Element clonedSimplify() { return this; }
	@Override
	public Element simplify() { return clone(); }
}