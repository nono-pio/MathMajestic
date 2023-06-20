package math.element.primary;

import exeptions.math.DivideByZeroException;
import math.element.Element;
import math.element.ElementType;
import math.element.PrimaryElement;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;

public class Number extends PrimaryElement {

	public static final Number zero = new Number(0);
	public static final Number one = new Number(1);

	public float value;

	// <------------ Constructor ------------>

	public Number(float realNumber) {
		if (realNumber == 0)
			value = 0;
		else
			value = realNumber;
	}

	// <-------- Number basic function --------->

	public Number add(Number num) {
		value += num.value;
		return this;
	}

	public Number add(float num) {
		value += num;
		return this;
	}

	public Number sub(Number num) {
		value -= num.value;
		return this;
	}

	public Number sub(float num) {
		value -= num;
		return this;
	}

	public Number mult(Number num) {
		value *= num.value;
		return this;
	}

	public Number mult(float num) {
		value *= num;
		return this;
	}

	public Number div(Number num) {
		if (num.value == 0)
			throw new DivideByZeroException();

		value /= num.value;
		return this;
	}

	public Number div(float num) {
		if (num == 0)
			throw new DivideByZeroException();

		value /= num;
		return this;
	}

	public boolean isEqual(Number num) {
		return Math.abs(value - num.value) <= 0.005;
	}

	public boolean isEqual(float num) {
		return Math.abs(value - num) <= 0.005;
	}

	public boolean isZero() {
		return isEqual(new Number(0));
	}

	public boolean isInteger() {
		return isEqual(new Number(Math.round(value)));
	}

	public int toInteger() {
		return (int) value;
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Number;
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Number(value);
	}

	// <---------------- Math --------------->

	public Element derivative(DerivativeSettings settings) {
		return zero;
	}

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		if (isInteger())
			return String.valueOf(toInteger());
		return String.valueOf(value);
	}

	// <---------------- ToValue ------------>

	public Number toValue(Number[] values) {
		return this;
	}

	// <----------- Other Function ----------->

	public int compareTo(Element element2) {
		if (element2.getType() != getType())
			return getType().compareTo(element2.getType());

		Number num = (Number) element2;
		return Float.compare(value, num.value);
	}

	public boolean isEqual(Element elem) {
		return elem.getType() == ElementType.Number && isEqual((Number) elem);
	}
}