package math.element.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.math.AdditionExtention;
import math.simplification.ElementCoef;
import math.simplification.InfinitElement;
import math.tools.StringFormat;
import math.tools.Tools;

public class Product extends Element implements InfinitElement {

	public List<Element> values;

	// <------------ Constructor ------------>

	public Product(Element... values) {
		this.values = new ArrayList<Element>(List.of(values));
	}

	public Product(List<Element> values) {
		this.values = new ArrayList<Element>(values);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Product;
	}

	// <---------------- Values ------------->

	public Element[] getValues() {
		return values.toArray(new Element[values.size()]);
	}

	public void setValues(Element[] values) {
		this.values = new ArrayList<Element>(List.of(values));
	}

	public Element clone() {
		return new Product(Tools.cloneElementArray(getValues()));
	}
	
	public int size() {
		return values.size();
	}

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {

		String str = StringFormat.arrayStr(values, settings.isLaTeX ? " \\cdot " : " * ");

		if (parentType == null || parentType == ElementType.Addition)
			return str;
		else
			return StringFormat.bracket(str, settings.isLaTeX);
	}

	// <--------------- Math ---------------->

	public Element mult(Element pro) {
		if (!pro.isEqual(Number.one))
			values.add(0, pro);
		return this;
	}

	public Element recipFunction(int[] path, Element curRecip) {

		Element[] div = new Element[values.size() - 1];
		int index = 0;
		for (int i = 0; i < values.size(); i++) {
			if (i != path[0]) {
				div[index] = values.get(i);
				index++;
			}
		}

		return values.get(path[0]).recipFunction(newPath(path), new Division(curRecip, new Product(div)));
	}

	public Element clonedSimplify() {

		ArrayList<Element> newChilds = new ArrayList<>(); // extends the values with child Product and Sign
		for (Element child : values) {
			if (child.getType() == ElementType.Product)
				newChilds.addAll(Arrays.asList(child.getValues()));
			else
				newChilds.add(child);
		}
		values = newChilds;

		// arrange child
		Number cste = new Number(1);
		ElementCoef elemCoef = new ElementCoef();
		ArrayList<Addition> additionChildren = new ArrayList<Addition>();

		for (Element child : values) {
			if (child.getType() == ElementType.Number)
				cste.mult((Number) child);
			else if (child.getType() == ElementType.Addition)
				additionChildren.add((Addition) child);
			else {
				Element elem;
				Number coef;
				if (child.getType() == ElementType.Power && ((Power) child).exponent.getType() == ElementType.Number) {
					Power childPow = (Power) child;
					elem = childPow.base;
					coef = (Number) childPow.exponent;
				} else {
					elem = child;
					coef = new Number(1);
				}
				elemCoef.add(coef, elem);
			}
		}

		if (cste.isZero())
			return new Number(0);

		boolean hasCste = !cste.isEqual(new Number(1));

		if (additionChildren.size() == 0 && elemCoef.size() == 0)
			return cste;

		ArrayList<Element> pro = elemCoef.getElementsPower();

		if (hasCste)
			pro.add(cste);
		if (additionChildren.size() == 0) {
			Element[] newPro = pro.toArray(new Element[pro.size()]);
			Arrays.sort(newPro);
			return new Product(newPro);
		}

		if (additionChildren.size() == 1 && pro.size() == 0)
			return additionChildren.get(0).clonedSimplify();

		return AdditionExtention.Product(additionChildren, pro);
	}

	public Element develop() {

		List<Element> elements = new ArrayList<>();
		List<Addition> additions = new ArrayList<>();

		for (Element element : values) {
			if (element instanceof Addition add)
				additions.add(add);
			else
				elements.add(element);
		}

		if (additions.size() == 0)
			return this;

		return AdditionExtention.Product(additions, elements);
	}

	public Number getCoef(int index) {

		if (values.get(index) instanceof Power power && power.exponent instanceof Number number) {
			return number;

		}

		return new Number(1);
	}
	
	public Element getElement(int index) {

		if (values.get(index) instanceof Power power && power.exponent instanceof Number) {
			return power.base;

		}

		return new Number(1);
	}

	public Element reduceNumber() {
		Number pro = Number.one;

		for (int i = values.size() - 1; i >= 0; i--) {
			if (values.get(i).getType() == ElementType.Number) {
				pro.mult((Number) values.get(i));
				values.remove(i);
			}
		}
		if (!pro.isEqual(Number.one)) {
			values.add(pro);
		}

		return this;
	}

	public Element derivative(DerivativeSettings settings, int index) {

		List<Element> elements = new ArrayList<Element>(Arrays.asList(Tools.cloneElementArray(getValues())));
		elements.remove(index);

		if (elements.size() == 1)
			return elements.get(0);
		return new Product(elements);
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return MathN.product(values);
	}

	// <----------- Other Function ---------->

	public Number getCst() {
		Number cst = new Number(1);
		for (Element element : values) {
			if (element.getType() == ElementType.Number)
				cst.mult((Number) element);
			;
		}
		return cst;
	}

	public Element getRest() {
		Element[] rest = getRestList();
		if (rest.length == 1)
			return rest[0];
		return new Product(rest);
	}

	public Element[] getRestList() {
		List<Element> rest = new ArrayList<Element>();
		for (Element element : values) {
			if (element.getType() != ElementType.Number)
				rest.add(element);
		}
		return rest.toArray(new Element[rest.size()]);
	}
}
