package math.element.elements;

import java.util.ArrayList;
import java.util.List;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.tools.StringFormat;
import math.tools.Tools;

public class Addition extends Element {

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

	public Element develop() {
		return this;
	}
	
	public Element derivative(DerivativeSettings settings, int index) {
		return new Number(1);
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return MathN.sum(values);
	}
}
