/**
 * 
 */
package math.element.elements;

import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.tools.StringFormat;
import math.variables.FunctionData;
import math.variables.GlobalFunction;

/**
 * Function is a defined function that represent f(x).
 * 
 * @author Nolan Piccand
 *
 */
public class Function extends Element {

	FunctionData data; // function reference
	String functionName;
	Element value; // x in f(x)

	public Function(String functionName, Variable var, Element function) {
		this.functionName = functionName;
		this.data = GlobalFunction.setFunction(functionName, function, var);
		this.value = var.clone();
	}

	public Function(String functionName, Element value) {
		this.functionName = functionName;
		this.data = GlobalFunction.getData(functionName);
		this.value = value;
	}

	public Function of(Element value) {
		return new Function(functionName, value);
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Function;
	}

	// <---------------- Values ------------->

	public Element[] getValues() {
		return new Element[] { value, data.getFunction() };
	}

	public void setValues(Element[] values) {
		this.value = values[0];
		// this.data.function = values[1];
	}

	public Element clone() {
		return new Function(functionName, value.clone());
	}

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {

		if (settings.showFunctionValue) {
			return values[1].replaceAll(data.getVariable().variable, values[0]);
		}

		String variable = settings.showVariableFunction ? StringFormat.bracket(values[0], settings.isLaTeX) : "";

		return functionName + variable;
	}

	// <--------------- Math ---------------->

	public Element recipFunction(int[] path, Element curRecip) {
		throw new RuntimeException("Function can not manage reciprocal");
	}

	public Element develop() {
		// TODO
		return this;
	}

	public Element derivative(DerivativeSettings settings, int index) {
		return data.getFunction().clone().derivative(settings, 0);
	}

	// <---------------- ToValue ------------>
	
	public Number toValue(Number[] values) {
		
		data.getVariable().setValue(values[0]);
		
		return data.getFunction().toValue().getImportantValue();
	}

	
}
