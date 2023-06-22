package math.string_converter;

import math.element.Element;
import math.element.primary.Number;
import math.element.primary.Variable;

public class LatexConverter {

	StringBuilder stringData;
	StringDataType dataType;

	ElementBuilder builder;

	public LatexConverter() {
		this.builder = new ElementBuilder();
		this.stringData = new StringBuilder();
	}

	public void add(char c) {

		if (dataType == StringDataType.Latex) {

			if (c == ' ') {
				dataType = null;
				// end latex
				return;
			}

			stringData.append(c);
			return;
		}

		if (c == ' ') {
			return;
		}

		// check if is part of a number with digit and .,
		if (Character.isDigit(c) || c == '.' || c == ',') {

			if (dataType != StringDataType.Number) {
				dataType = StringDataType.Number;
			}
			stringData.append(c);

			return;

		} else if (dataType == StringDataType.Number) {

			dataType = null;

			try {

				float value = Float.parseFloat(stringData.toString());
				builder.add(new Number(value));
				stringData = new StringBuilder();

			} catch (NumberFormatException e) {

				System.out.println(stringData);
				throw e;
			}

		}

		// variable or fonction
		if (Character.isLetter(c)) {

			builder.add(new Variable(c + ""));
			return;
		}

		// check operation signe
		switch (c) {
		case '+':
			builder.add(NodeElementType.Addition);
			return;
		case '-':
			builder.add(NodeElementType.Subtract);
			return;
		case '*':
			builder.add(NodeElementType.Product);
			return;
		case '^':
			builder.add(NodeElementType.Power);
			return;
		case '/':
			builder.add(NodeElementType.Division);
			return;
		}

		if (c == '\\') {
			dataType = StringDataType.Latex;
			return;
		}

	}

	public void end() {

		if (dataType == null) {
			return;
		}

		switch (dataType) {

		case Number:
			float value = Float.parseFloat(stringData.toString());
			builder.add(new Number(value));
			break;

		default:
			break;

		}

	}

	public Element toElement() {
		return builder.build();
	}

	public static Element convert(String string) {

		LatexConverter converter = new LatexConverter();

		String str = "";

		for (char c : string.toCharArray()) {
			converter.add(c);

			str += c;
			// System.out.println(str + "\t" + converter);
		}

		converter.end();

		System.out.println(converter);

		return converter.toElement();
	}

	public String toString() {
		return "LatexConverter [stringData=" + stringData + ", dataType=" + dataType + ", builder=" + builder + "]";
	}

	enum StringDataType {
		Number, Latex, Variable, MathFunction
	}

}
