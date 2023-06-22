package main;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import exeptions.math.MathException;
import math.element.Element;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.random.RandomElement;
import math.simplification.Simplification;
import math.string_converter.LatexConverter;

public class Main {

	public static void main(String[] args) {

		// String str = randomEqu(10);

		// print(str);
		// saveKboard(str);

		long start = System.nanoTime();

		Simplification.getSimplification(LatexConverter.convert(" 1 + 2 * 3 ^ 4 / 5 "));

		long end = System.nanoTime();

		System.out.println((end - start) / (double) 10e6);

		// print(LatexConverter.convert(" 1 * 2 ^ 3 "));

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

	static void methodElif(char c) {

		if (c == 'a') {
			return;
		} else if (c == 'b') {
			return;
		} else if (c == 'c') {
			return;
		} else if (c == 'd') {
			return;
		} else if (c == 'e') {
			return;
		} else if (c == 'f') {
			return;
		} else if (c == 'g') {
			return;
		} else if (c == 'h') {
			return;
		} else if (c == 'i') {
			return;
		} else if (c == 'j') {
			return;
		} else if (c == 'k') {
			return;
		} else if (c == 'l') {
			return;
		} else if (c == 'm') {
			return;
		} else if (c == 'n') {
			return;
		} else if (c == 'o') {
			return;
		} else if (c == 'p') {
			return;
		} else if (c == 'q') {
			return;
		} else if (c == 'r') {
			return;
		} else if (c == 's') {
			return;
		}

		return;
	}

	static void methodSwitch(char c) {

		switch (c) {
		case 'a':
			return;
		case 'b':
			return;
		case 'c':
			return;
		case 'd':
			return;
		case 'e':
			return;
		case 'f':
			return;
		case 'g':
			return;
		case 'h':
			return;
		case 'i':
			return;
		case 'j':
			return;
		case 'k':
			return;
		case 'l':
			return;
		case 'm':
			return;
		case 'n':
			return;
		case 'o':
			return;
		case 'p':
			return;
		case 'q':
			return;
		case 'r':
			return;
		case 's':
			return;

		}

		return;
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
