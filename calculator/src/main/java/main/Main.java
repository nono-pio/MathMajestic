package main;

import exeptions.math.MathException;
import math.element.Element;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.random.RandomElement;
import math.simplification.Simplification;

public class Main {

	public static void main(String[] args) {

		StringBuilder str = new StringBuilder();

		for (int i = 0; i < 10; i++) {

			Element element = RandomElement.randomBasic(5, 25, 5, 3);

			String result;

			try {

				Element simp = Simplification.getSimplification(element).simplify().toElement();

				result = simp.toLaTeX();

			} catch (MathException e) {

				result = "Undefined Equation";

			}

			str.append("\\begin{equation}\n").append(element.toLaTeX()).append(" = ").append(result)
					.append("\n\\end{equation}\n\n");

		}

		print(str);

		/*
		 * randomElement.forEach((e, p) -> { if (!(e instanceof PrimaryElement))
		 * System.out.println(e.getType()); });
		 */

		// LaTex latex = new LaTex(pythagore.toLaTeX());

		// new Frame("Title", latex, 1000, 500, 50);
	}

	static <T> void print(T str) {
		System.out.println(str.toString());
	}

	static void print(String str) {
		System.out.println(str);
	}

	static Variable a = new Variable("a");
	static Variable b = new Variable("b");
	static Variable c = new Variable("c");
	static Variable x = new Variable("x");
	static Variable y = new Variable("y");
	static Variable z = new Variable("z");

	static Number n0 = new Number(0);
	static Number n1 = new Number(1);
	static Number n2 = new Number(2);
	static Number n5 = new Number(5);
	static Number n10 = new Number(10);
	static Number n20 = new Number(20);
	static Number n50 = new Number(50);
}
