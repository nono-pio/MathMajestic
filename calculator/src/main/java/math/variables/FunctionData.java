package math.variables;

import math.element.Element;
import math.element.primary.Variable;

public class FunctionData {

	Element function;
	Variable variable;

	public FunctionData(Element function, Variable variable) {
		this.function = function;
		this.variable = variable;
	}
	

	public Element getFunction() {
		return function;
	}

	public void setFunction(Element function) {
		this.function = function;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}


}
