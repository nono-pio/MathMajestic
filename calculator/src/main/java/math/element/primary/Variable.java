package math.element.primary;

import math.element.Element;
import math.element.ElementType;
import math.element.PrimaryElement;
import math.element.settings.DerivativeSettings;
import math.element.settings.StringSettings;
import math.tools.ErrorMessage;
import math.variables.GlobalVariable;
import math.variables.VariableData;
import math.variables.Variables;

public class Variable extends PrimaryElement {

	public String variable;
	public VariableData variableData;

	// <------------ Constructor ------------>

	public Variable(String variable) {
		this.variable = variable;
		variableData = GlobalVariable.setVariable(variable, null);
	}

	public Variable(String variable, Number value) {
		this.variable = variable;
		this.variableData = GlobalVariable.setVariable(variable, value);
	}

	public Variable(String variable, float value) {
		this.variable = variable;
		this.variableData = GlobalVariable.setVariable(variable, new Number(value));
	}

	public Variable(String variable, VariableData data) {
		this.variable = variable;
		variableData = data;
	}

	// <----------------- Type -------------->

	public ElementType getType() {
		return ElementType.Variable;
	}

	// <---------------- Values ------------->

	public Element clone() {
		return new Variable(variable, variableData);
	}

	// <---------------- Math --------------->

	public Element derivative(DerivativeSettings settings) {

		if (settings.variable.contentEquals(this.variable))
			return new Number(1);
		return Number.zero;
	}

	// <------------- String ---------------->

	public String toString(ElementType parentType, StringSettings settings, String[] values) {
		if (settings.showVariableValue && variableData.value != null)
			return variableData.value.toString();
		return variable;
	}

	// <---------------- ToValue ------------>

	public double calculateReal() {
		if (variableData == null || variableData.value == null)
			throw ErrorMessage.VariableNotSet(variable);
		return variableData.value.value;
	}

	// <---------- Other Function ------------>

	public boolean isEqual(Element elem) {
		return elem instanceof Variable var && this.variable.contentEquals(var.variable);
	}

	public int compareTo(Element element2) {
		if (element2.getType() != getType())
			return getType().compareTo(element2.getType());
		Variable var = (Variable) element2;
		return variable.compareTo(var.variable);
	}

	public void setValue(Number value) {
		variableData.value = value;
	}

	public void setVariable(Variables variables, int[] curPath, Number value) {
		variableData = variables.setVariable(variable);
		variableData.paths.add(curPath.clone());
		variableData.variableCount = variableData.paths.size();
		variableData.value = value;
	}
}
