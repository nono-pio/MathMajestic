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
import math.tools.StringFormat;
import math.tools.Tools;
import math.tools.ElementCoef;

public class Product extends Element {

	public List<Element> values;

	// <------------ Constructor ------------>

	public Product(Element... values) {
		this.values = List.of(values);
	}

	public Product(List<Element> values) {
		this.values = values;
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
		this.values = List.of(values);
	}

	public Element clone() {
		return new Product(Tools.cloneElementArray(getValues()));
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

		return AdditionExtention.Product(additionChildren.toArray(new Addition[additionChildren.size()]), pro)
				.simplify();
	}

	public Element derivative(DerivativeSettings settings) {

		Element cste = getCst();
		int isCste = cste.isEqual(new Number(1)) ? 0 : 1;
		Element[] var = getRestList();
		
		Element[] derivative = new Element[var.length];

		for (int i = 0; i < var.length; i++) {

			Element[] pro = new Element[var.length + isCste];

			if (isCste == 1)
				pro[0] = cste.clone();
			
			for (int j = isCste; j < pro.length; j++) {
				if (j != i + isCste)
					pro[j] = var[j - isCste].clone();
				else
					pro[j] = var[i].derivative(settings);
			}

			if (pro.length > 1)
				derivative[i] = new Product(pro);
			else 
				derivative[i] = pro[0];
		}

		if (derivative.length > 1)
			return new Addition(derivative);
		else 
			return derivative[0];
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
				cst.mult((Number) element);;
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
