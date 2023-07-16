package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;

import exeptions.math.MathException;
import math.element.Element;
import math.element.elements.Addition;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.elements.Product;
import math.element.functions.Log;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.element.visual.Exp;
import math.random.RandomElement;
import math.simplification.Simplification;
import math.variables.GlobalVariable;

public class Main {

	public static void main(String[] args) {

		// String str = randomEqu(10);

		// print(str);
		// saveKboard(str);

		Element sigmoide = new Division(n1, new Addition(n1, new Exp(new Product(new Number(-1), new Variable("x")))));
		Element recSigmoide = new Product(new Number(-1),
				new Log(new Addition(new Division(n1, new Variable("x")), new Number(-1))));

		Element fx = new Power(new Variable("x"), n2);
		System.out.println(fx);

		fx.replaceVariable(x, recSigmoide);
		sigmoide.replaceVariable(x, fx);

		print(sigmoide);

		long start = System.nanoTime();

		int n = 100;
		double[] result = new double[n + 1];

		double deltaX = 1 / (double) n;

		// [0, 1]
		double x = 0;
		for (int i = 0; i <= n; i++) {

			GlobalVariable.setVariable("x", new Number((float) x));
			result[i] = sigmoide.calculateReal();

			x += deltaX;
		}

		long end = System.nanoTime();

		System.out.println(Arrays.toString(result));
		System.out.println("Time to solve : " + ((end - start) / (double) 10e6) + " ms");
		System.out.println("Time to solve : " + (10e9 / (double) (end - start)) + " Hz");

		// calculateReal : 100k - 200k calc per second 33 times faster
		// toValue : 3.5k - 5.5k calc per second

		/*
		 * ObjectMapper obj = new ObjectMapper();
		 * 
		 * Object myObj = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
		 * MetaData());
		 * 
		 * try { String json =
		 * obj.writerWithDefaultPrettyPrinter().writeValueAsString(myObj); print(json);
		 * } catch (JsonProcessingException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		/*
		 * randomElement.forEach((e, p) -> { if (!(e instanceof PrimaryElement))
		 * System.out.println(e.getType()); });
		 */

		// LaTex latex = new LaTex(pythagore.toLaTeX());

		// new Frame("Title", latex, 1000, 500, 50);
	}

	static void saveKboard(String message) {

		Clipboard kboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		kboard.setContents(new StringSelection(message), null);

	}

	static String randomEqu(int nb) {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < nb; i++) {

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
		return str.toString();
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
