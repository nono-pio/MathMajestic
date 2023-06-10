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

public class Product extends Element {

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

	public Element[] getRestList() {
		List<Element> rest = new ArrayList<Element>();
		for (Element element : values) {
			if (element.getType() != ElementType.Number)
				rest.add(element);
		}
		return rest.toArray(new Element[rest.size()]);
	}
}
