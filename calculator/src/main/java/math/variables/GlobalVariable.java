package math.variables;

import math.element.primary.Number;

public final class GlobalVariable {

	public static Variables globalVariables = new Variables();

	public static VariableData setVariable(String variable) {
		return globalVariables.setVariable(variable);
	}

	public static VariableData setVariable(String variable, Number value) {
		return globalVariables.setVariable(variable, value);
	}

	public static boolean isGlobal(String variable) {
		return globalVariables.isGlobal(variable);
	}

	public static VariableData getData(String variable) {
		return globalVariables.getData(variable);
	}

	public static Number toValue(String variable) {
		return globalVariables.toValue(variable);
	}

}
