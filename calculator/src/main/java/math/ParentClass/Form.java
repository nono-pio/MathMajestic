package math.ParentClass;

import math.numbers.Number;

public abstract class Form extends Element {

	
	
	public abstract Element toElement();
	public abstract Element clone();
	
	public ElementType getType() { return ElementType.Form; }
	public Number toValue() { return toElement().toValue(); }
	public Element recipFunction(int[] path, Element curRecip) {
		return toElement().recipFunction(path, curRecip); }
	public Element[] getValues() { return toElement().getValues(); }
	public void setValues(Element[] values) {
		// TODO Auto-generated method stub

	}
	public String toString(ElementType parentType, boolean isLaTeX) {
		return toElement().toString(parentType, isLaTeX); }
	public Element clonedSimplify() { return toElement().clonedSimplify(); }

}
