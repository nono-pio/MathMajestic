package math.ParentClass;

import math.numbers.Number;
import math.tools.StringSettings;

public abstract class Form extends Element {

	
	public abstract Element toElement();
	public abstract Element clone();
	
	public ElementType getType() { return ElementType.Form; }
	public Number toValue(Number[] values) { return toElement().toValue(values); }
	public Element recipFunction(int[] path, Element curRecip) {
		return toElement().recipFunction(path, curRecip); }
	public Element[] getValues() { return toElement().getValues(); }
	public void setValues(Element[] values) {
		// TODO Auto-generated method stub

	}
	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		return toElement().toString(parentType, settings); }
	public Element clonedSimplify() { return toElement().clonedSimplify(); }

}
