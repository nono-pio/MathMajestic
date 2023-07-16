package math.string_converter;

import java.util.ArrayList;
import java.util.Arrays;

import math.element.Element;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.primary.Number;
import math.element.primary.Variable;

// todo : new sys end latex see mathquill + add function \sin x and sin x
public class LatexConverter {

	static final String[] greekLetter = { "alpha", "beta", "gamma", "delta", "epsilon", "yeta", "phi", "pi", "theta" };

	StringBuilder stringData;
	Mode mode;

	ElementBuilder builder;

	LatexConverter parenthesis;
	int parenthesisLevel = 0;

	int latexParameter = 0;

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
			}

			return true;

		case Latex:

			if (c == '{') {
				latexParameter++;
			}

			if (!Character.isLetter(c) && latexParameter == 0) {
				mode = null;
				if (c != ' ') {
					stringData.append(c);
				}
				doLatex();
			} else {
				stringData.append(c);
			}

			if (c == '}') {
				latexParameter--;
			}

			return true;

		case Number:

			if (!Character.isDigit(c) && c != '.' && c != ',') {

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

		String[] latexStructur = extractParameter(stringData.toString()); // [latex name; ...parameters... ]
		String latex = latexStructur[0];
		stringData = new StringBuilder();

		System.out.println(Arrays.toString(latexStructur) + "--185");

		switch (latex) {
		case "cdot":
			builder.add(NodeElementType.Product);
			return;
		case "frac":
			builder.add(new Division(convert(latexStructur[1]), convert(latexStructur[2])));
			return;
		case "sqrt":
			builder.add(new Power(convert(latexStructur[1]), new Number(0.5f)));
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

	public String[] extractParameter(String latex) {

		ArrayList<String> structur = new ArrayList<>();
		String[] nameAndParameters = latex.split("\\{", 2);
		int latexParameterLevel = 1;

		if (nameAndParameters.length == 1) {
			return new String[] { nameAndParameters[0] };
		}

		System.out.println(latex + " " + Arrays.toString(nameAndParameters));
		structur.add(nameAndParameters[0]);

		StringBuilder str = new StringBuilder();
		for (char c : nameAndParameters[1].toCharArray()) {

			if (c == '}') {
				latexParameterLevel--;
			}

			if (c == '{') {
				latexParameterLevel++;
				if (latexParameterLevel == 1) {
					continue;
				}
			}

			if (latexParameterLevel > 0) {
				str.append(c);
			} else {
				structur.add(str.toString());
				str = new StringBuilder();
			}

		}

		return structur.toArray(new String[structur.size()]);

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
			// System.out.println(str + "\t" + converter);
		}

		converter.end();

		// System.out.println(converter);

		return converter.toElement();
	}

	public static Element convertDebug(String string) {

		LatexConverter converter = new LatexConverter();

		String str = "";

		for (char c : string.toCharArray()) {
			converter.add(c);

			str += c;
			System.out.println("\'" + c + "\' + " + str + "\t" + converter);
		}

		converter.end();

		System.out.println(converter);

		return converter.toElement();
	}

	@Override
	public String toString() {
		return "LatexConverter [stringData=" + stringData + ", mode=" + mode + ", builder=" + builder + ", parenthesis="
				+ parenthesis + ", parenthesisLevel=" + parenthesisLevel + ", latexParameter=" + latexParameter + "]";
	}

	enum Mode {
		Number, Latex, Variable, MathFunction, Parenthesis
	}

}
