package main;

import latex.*;

import math.set.*;
import math.MathFlag;
import math.MathN;
import math.ParentClass.*;
import math.element.Element;
import math.element.Element.NumberResponse;
import math.element.elements.*;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.forms.Polynome;
import math.math.Fraction;
import math.numbers.GlobalVariable;
import math.set.SetNumber;
import math.string_converter.StringConverter;
import math.tools.StringSettings;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		
		Element polynome = new Addition(
				x,
				new Product(n1, n5, new Division(n2, x))
				);
		
		
		print(polynome.isEqual(polynome.simplify()));
		
		
		
		//print("");
		//element.forEach((e, p) -> print(e.getType() + " : " + e));
		
		//LaTex latex = new LaTex(pythagore.toLaTeX());
		
		//new Frame("Title", latex, 1000, 500, 50);
	}
	
	
	static <T> void print(T str) { System.out.println(str.toString()); }
	static void print(String str) { System.out.println(str); }
	
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
