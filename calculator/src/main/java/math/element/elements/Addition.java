package math.element.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.simplification.InfinitElement;
import math.tools.StringFormat;
import math.tools.Tools;

public class Addition extends Element implements InfinitElement {

	public List<Element> values;

	// <------------ Constructor ------------>

	public Addition(Element ... values) {
		this.values = new ArrayList<>(List.of(values));
	}

	public Addition(Element value1, Element value2, boolean subtract) {
		if (subtract)
			this.values = new ArrayList<>(List.of(value1, new Product(new Number(-1), value2)));
		else
			this.values = new ArrayList<>(List.of(value1, value2));
	}

	public Addition(List<Element> values) {
		this.values = new ArrayList<>(values);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Addition;
	}

	// <---------------- Values ------------->

	public Element[] getValues() {
		return values.toArray(new Element[values.size()]);
	}

	public void setValues(Element[] values) {
		this.values = new ArrayList<>(List.of(values));
	}

	public Element clone() {
		return new Addition(Tools.cloneElementArray(getValues()));
	}
	
	public int size() {
		return values.size();
	}

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {

		String str = StringFormat.arrayStr(values, " + ");

		if (parentType == null || parentType == ElementType.Division || parentType == ElementType.Addition)
			return str;
		else
			return StringFormat.bracket(str, settings.isLaTeX);
	}

	// <--------------- Math ---------------->

	public Element add(Element add) {
		if (!add.isEqual(Number.zero))
			values.add(add);
		return this;
	}

	public Element recipFunction(int[] path, Element curRecip) {

		Element[] newRecip = new Element[values.size()];
		int index = 1;
		for (int i = 0; i < values.size(); i++) {
			if (i != path[0]) {
				newRecip[index] = new Product(new Number(-1), values.get(i));
				index++;
			}
		}
		newRecip[0] = curRecip;

		return values.get(path[0]).recipFunction(newPath(path), new Addition(newRecip));
	}

	public Element clonedSimplify() {

		for (int i = 0; i < values.size(); i++) {
			if (values.get(i).getType() == ElementType.Addition) {
				values.addAll(((Addition) values.get(i)).values);
				values.remove(i);
			}
		}

		Number cste = new Number(0);

		for (Element child : values) {
			if (child.getType() == ElementType.Number)
				cste.add((Number) child);
			else {
				Element elem;
				Number coef;
				if (child.getType() == ElementType.Product) {
					Product childPro = (Product) child;
					elem = childPro.getRest();
					coef = childPro.getCst();
				} else {
					elem = child;
					coef = new Number(1);
				}

			}
		}

		return this;
	}

	public Element develop() {
		return this;
	}

	public Element reduceNumber() {
		Number sum = new Number(0);

		for (int i = values.size() - 1; i >= 0; i--) {
			if (values.get(i) instanceof Number number) {
				
				sum.add(number);
				values.remove(i);
			}
		}
		
		if (!sum.isZero()) {
			values.add(sum);
		}

		return this;
	}
	
	public Number getCoef(int index) {
		
		if (values.get(index) instanceof Product product) {
			return product.getCst();
		}
		
		return new Number(1);
	}
	
	public Element getElement(int index) {
		
		if (values.get(index) instanceof Product product) {
			return product.getRest();
		}
		
		return values.get(index);
	}

	public Element derivative(DerivativeSettings settings, int index) {
		return new Number(1);
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return MathN.sum(values);
	}
}
