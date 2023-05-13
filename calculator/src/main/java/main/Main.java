package main;

import latex.*;

import math.set.*;
import math.set.SetNumber;

import math.MathFlag;
import math.MathN;
import math.math.Fraction;

import math.element.Element;
import math.element.elements.*;
import math.element.functions.*;
import math.element.primary.*;
import math.element.primary.Number;
import math.element.settings.*;
import math.element.visual.*;

import math.forms.*;
import math.string_converter.StringConverter;
import math.variables.GlobalVariable;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {

		Element element = new Addition(x, y, n1, new Log(n2, n5));

		print(element);
		print(element.simplify());

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
