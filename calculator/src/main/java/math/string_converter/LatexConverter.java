package math.string_converter;

import java.util.Arrays;

import math.element.Element;
import math.element.primary.Number;
import math.element.primary.Variable;

public class LatexConverter {

	static final String[] greekLetter = { "alpha", "beta", "gamma", "delta", "epsilon", "yeta", "phi", "pi", "theta" };

	StringBuilder stringData;
	Mode mode;

	ElementBuilder builder;

	LatexConverter parenthesis;
	int parenthesisLevel = 0;

	public LatexConverter() {
		this.builder = new ElementBuilder();
		this.stringData = new StringBuilder();
	}

	public void add(char c) {

		if (mode(c)) {
			return;
		}

		// variable or fonction
		if (Character.isLetter(c)) {

			builder.add(new Variable(c + ""));
			return;
		}

		// start number
		if (Character.isDigit(c) || c == '.' || c == ',') {

			mode = Mode.Number;
			stringData.append(c);
			return;
		}

		// check operation signe
		switch (c) {

		case ' ': // ignore space if not
			return;

		case '\\':
			mode = Mode.Latex; // \LaTeX
			return;

		case '(': // start parenthesis
			startParenthesis();
			return;
		case ')': // end parenthesis
			endParenthesis();
			return;
		case '{': // start parenthesis
			startParenthesis();
			return;
		case '}': // end parenthesis
			endParenthesis();
			return;

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
	}

	public boolean mode(char c) {

		if (mode == null) {
			return false;
		}

		switch (mode) {
		case Parenthesis:

			parenthesis.add(c);
			if (parenthesis.parenthesisLevel < 0) {
				endParenthesis();
				// parenthesisLevel += parenthesis.parenthesisLevel;
			}

			return true;

		case Latex:

			if (c == ' ') {
				mode = null;
				// do latex
			} else {
				stringData.append(c);
			}

			return true;

		case Number:

			if (!Character.isDigit(c) || c != '.' || c != ',') {

				mode = null;

				try {

					float value = Float.parseFloat(stringData.toString());
					builder.add(new Number(value));
					stringData = new StringBuilder();

				} catch (NumberFormatException e) {

					System.out.println(stringData);
					throw e;
				}

				return false;

			} else {
				stringData.append(c);
				return true;

			}

		default:
			return false;

		}
	}

	public void startParenthesis() {
		mode = Mode.Parenthesis;
		parenthesis = new LatexConverter();
		parenthesisLevel++;
	}

	public void endParenthesis() {
		mode = null;
		parenthesisLevel--;
		if (parenthesisLevel >= 0) {
			parenthesis.end();
			builder.add(parenthesis.toElement());
		}
	}

	public void doLatex() {

		String latex = stringData.toString();
		stringData = new StringBuilder();

		switch (latex) {
		case "cdot":
			builder.add(NodeElementType.Product);
			return;
		case "left(":
			startParenthesis();
			return;
		case "right)":
			endParenthesis();
			return;

		default:

			if (Arrays.stream(greekLetter).anyMatch(x -> x.contentEquals(latex))) {

				builder.add(new Variable('\\' + latex));

			}

		}

	}

	public void end() {

		if (mode == null) {
			return;
		}

		switch (mode) {

		case Number:
			float value = Float.parseFloat(stringData.toString());
			builder.add(new Number(value));
			break;

		case Parenthesis:
			endParenthesis();
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
			System.out.println(str + "\t" + converter);
		}

		converter.end();

		System.out.println(converter);

		return converter.toElement();
	}

	public String toString() {
		return "LatexConverter [stringData=" + stringData + ", dataType=" + mode + ", builder=" + builder
				+ ", parenthesisLevel=" + parenthesisLevel + ", parenthesis=" + parenthesis + "]";
	}

	enum Mode {
		Number, Latex, Variable, MathFunction, Parenthesis
	}

}
