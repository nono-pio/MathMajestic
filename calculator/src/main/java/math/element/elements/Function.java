/**
 * 
 */
package math.element.elements;

import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.tools.StringFormat;
import math.tools.StringSettings;

/**
 * Function is a defined function that represent f(x). 
 * 
 * @author Nolan Piccand
 *
 */
public class Function extends Element {

	FunctionData function;
	Element value;
	
	public Function(String functionName, Variable var, Element function) {
		this.function = new FunctionData(functionName, var, function);
		this.value = var.clone();
	}
	
	public Function(FunctionData function, Element value) {
		this.function = function;
		this.value = value;
	}
	
	public Function of(Element value)
	{	
		return new Function(function, value);
	}
	
	// <----------------- Type -------------->
	
	public ElementType getType() {
		return ElementType.Function;
	}
	
	// <---------------- Values ------------->
	
	public Element[] getValues() {
		return new Element[] { value, function.function };
	}
	
	public void setValues(Element[] values) {
		this.value = values[0];
		this.function.function = values[1];
	}
	
	public Element clone() {
		return new Function(function, value.clone());
	}
	
	// <------------- String ---------------->
	
	public String toString(ElementType parentType, StringSettings settings, String[] values) {
	
		if (settings.showFunctionValue)
		{
			return values[1].replaceAll(function.variable.variable, values[0]);
		}
	
		String variable = settings.showVariableFunction ?
				StringFormat.bracket(values[0], settings.isLaTeX) : "";
		
		return function.name + variable;
	}
	
	// <--------------- Math ---------------->
	
	public Element recipFunction(int[] path, Element curRecip) {
		// TODO Auto-generated method stub
		return null;
	}

	public Element clonedSimplify() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// <---------------- ToValue ------------>
	
	public Number toValue(Number[] values) {
		return values[0];
	}
	
	// <---------------- SubClass ----------->
	
	class FunctionData
	{
		String name;
		Variable variable;
		public Element function;
		
		public FunctionData(String name, Variable variable, Element function) {
			this.name = name;
			this.variable = variable;
			this.function = function;
		}
	}
}
