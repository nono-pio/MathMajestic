package math.forms;

import java.util.List;
import java.util.ArrayList;

import math.element.Element;
import math.element.ElementType;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.primary.Number;
import math.element.primary.Variable;

public class Polynome {

	List<Monome> monomes;
	int degree;
	
	public Polynome(Element variable, Element ... values)
	{
		monomes = new ArrayList<Monome>();
		degree = values.length - 1;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != null && !values[i].isEqual(Number.zero))
			{
				monomes.add( new Monome(values[i], variable, degree - i) );
			}
		}
	}
	
	// <----------- ??? ---------->
	
	public static boolean isPolynome(Element element, String variable)
	{
		if (element instanceof Variable var && var.variable.contentEquals(variable)) return true;
		
		if (!element.containVariable(variable)) return true;
		
		if (element instanceof Division div) {
			
			boolean denoHasVar = div.denominator.containVariable(variable);
			
			return isPolynome(div.numerator, variable) && !denoHasVar; 
		}
		
		if (element instanceof Power pow) {
			
			return isPolynome(pow.base, variable)
					&& pow.exponent instanceof Number num 
					&& num.isInteger();
		}
		
		if (element.getType() == ElementType.Log) return false;
		
		for (Element child : element.getValues()) {
			if (!isPolynome(child, variable)) return false;
		}
		return true;
	}
	
	// <---------- Other Function ------->

	public String toString() {

		StringBuilder str = new StringBuilder(monomes.get(0).toString());
		for (int i = 1; i < monomes.size(); i++) {
			str.append(" + " + monomes.get(i));
		}
		return str.toString();
	}
}
