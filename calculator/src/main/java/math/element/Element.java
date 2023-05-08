package math.element;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import math.element.elements.Addition;
import math.element.elements.Division;
import math.element.elements.Product;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.element.settings.DerivativeSettings;
import math.element.settings.IElement;
import math.element.settings.NumberResponse;
import math.element.settings.StringSettings;
import math.tools.Position;

/**
 * 
 * Element is an abstract class. It represents all the elements of an equation,
 * number, variable, mathematical operation, ect.
 * 
 * @author Nolan Piccand
 */
public abstract class Element implements Comparable<Element> {

	// <--------------- Type ---------------------->

	public abstract ElementType getType();

	// <--------------- Values -------------------->

	public abstract Element[] getValues();

	public abstract void setValues(Element[] values);

	public abstract Element clone();

	// <--------------- String -------------------->

	public abstract String toString(ElementType parentType, StringSettings settings, String[] values);

	protected String toString(ElementType parentType, StringSettings settings) {
		Element[] childs = getValues();
		String[] childString = new String[childs.length];
		for (int i = 0; i < childString.length; i++) {
			childString[i] = childs[i].toString(getType(), settings);
		}
		return toString(parentType, settings, childString);
	}

	public String toString(StringSettings settings) {
		return toString(null, settings);
	}

	public String toString() {
		return toString(null, new StringSettings());
	}

	public String toLaTeX() {
		return toString(null, new StringSettings().setLaTeX(true));
	}

	// <---------------- Math --------------------->

	public Element add(Element add) {
		if (add.isEqual(Number.zero))
			return this;
		return new Addition(this, add);
	}

	public Element sub(Element sub) {
		if (sub.isEqual(Number.zero))
			return this;
		return new Addition(this, sub, true);
	}

	public Element mult(Element pro) {
		if (pro.isEqual(Number.one))
			return this;
		return new Product(pro, this);
	}

	public Element div(Element div) {
		if (div.isEqual(Number.one))
			return this;
		return new Division(this, div);
	}

	public abstract Element recipFunction(int[] path, Element curRecip);

	public abstract Element clonedSimplify();

	public abstract Element derivative(DerivativeSettings settings, int index);

	public Element derivative(DerivativeSettings settings) {

		Element[] values = getValues();

		List<Element> ddx = new ArrayList<>();
		for (int i = 0; i < values.length; i++) {

			if (values[i].containVariable(settings.variable)) {

				ddx.add(derivative(settings, i).mult(values[i].derivative(settings)));
			}
		}

		if (ddx.size() == 0)
			return Number.zero;
		else if (ddx.size() == 1)
			return ddx.get(0);
		else
			return new Addition(ddx);

	}

	public Element derivative(String variable) {
		return derivative(new DerivativeSettings(variable));
	}

	public Element simplify() {
		Element cloneElement = clone();
		Element[] values = cloneElement.getValues();

		boolean isCst = true;
		Element[] valuesSimplified = new Element[values.length];
		for (int i = 0; i < values.length; i++) {
			valuesSimplified[i] = values[i].simplify();
			if (valuesSimplified[i].getType() != ElementType.Number)
				isCst = false;
		}

		cloneElement.setValues(valuesSimplified);

		if (isCst)
			return cloneElement.toValue().getImportantValue();

		return cloneElement.clonedSimplify();
	}

	// <--------------- ToValue ------------------->

	public abstract Number toValue(Number[] values);

	public NumberResponse toValue() {

		Element[] childs = getValues();
		Number[] valuesChilds = new Number[childs.length];

		for (int i = 0; i < valuesChilds.length; i++) {
			valuesChilds[i] = childs[i].toValue().getImportantValue();
		}

		return new NumberResponse(toValue(valuesChilds));
	}

	// <---------------- Other Function ----------->

	public static int[] newPath(int[] curPath) {
		return Arrays.copyOfRange(curPath, 1, curPath.length);
	}

	public boolean isConstant() {
		if (getType() == ElementType.Variable)
			return false;
		for (Element child : getValues())
			if (child.isConstant() == false)
				return false;
		return true;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Element elem) {
			return isEqual(elem);
		} else
			return false;
	}

	public boolean isEqual(Element elem) {
		if (getType() != elem.getType())
			return false;
		Element[] values = getValues();
		Element[] values2 = elem.getValues();

		if (values.length != values2.length)
			return false;
		for (int i = 0; i < values.length; i++)
			if (!values[i].isEqual(values2[i]))
				return false;
		return true;
	}

	public int compareTo(Element element2) {

		if (getType() != element2.getType())
			return getType().compareTo(element2.getType());

		return Arrays.compare(getValues(), element2.getValues());
	}

	public void forEach(IElement func) {
		forEachChild(func, new Position(0));
	}

	public void forEachChild(IElement func, Position position) {
		func.invoke(this, position);
		Element[] childs = getValues();
		for (int i = 0; i < childs.length; i++) {
			childs[i].forEachChild(func, position.generateNewPosition(getType(), i));
		}
	}

	public void replaceVariable(Variable variable, Element replace) {
		Element[] childs = getValues();
		boolean hasChange = false;

		for (int i = 0; i < childs.length; i++) {
			if (childs[i].getType() == ElementType.Variable && childs[i].isEqual(variable)) {
				childs[i] = replace;
				hasChange = true;
			} else
				childs[i].replaceVariable(variable, replace);
		}

		if (hasChange) {
			setValues(childs);
		}
	}

	public boolean containVariable(String variable) {
		if (getType() == ElementType.Variable)
			return isEqual(new Variable(variable));

		for (Element child : getValues()) {
			if (child.containVariable(variable))
				return true;
		}
		return false;
	}
}