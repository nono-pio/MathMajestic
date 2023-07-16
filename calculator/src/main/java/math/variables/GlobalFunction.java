package math.variables;

import math.element.Element;
import math.element.primary.Variable;

public final class GlobalFunction {

	public static Functions globalVariables = new Functions();

	public static FunctionData setFunction(String functionName, Element function, Variable variable) {
		return globalVariables.setFunction(functionName, function, variable);
	}

	public static boolean isGlobal(String functionName) {
		return globalVariables.isGlobal(functionName);
	}

	public static FunctionData getData(String functionName) {
		return globalVariables.getData(functionName);
	}

	public static double calculateReal(String functionName) {
		return globalVariables.calculateReal(functionName);
	}

}
