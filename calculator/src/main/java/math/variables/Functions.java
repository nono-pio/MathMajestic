package math.variables;

import java.util.HashMap;

import math.element.Element;
import math.element.primary.Number;
import math.element.primary.Variable;

public class Functions {

	HashMap<String, FunctionData> functions = new HashMap<>();

	public FunctionData setFunction(String functionName, Element function, Variable variable) {
		FunctionData data = functions.get(functionName);

		if (data == null) {
			data = new FunctionData(function, variable);
		} else
			data.function = function;

		functions.put(functionName, data);
		return data;
	}

	public boolean isGlobal(String variable) {
		return functions.containsKey(variable);
	}

	public FunctionData getData(String variable) {
		return functions.get(variable);
	}

	public Number toValue(String functionName) {
		FunctionData data = getData(functionName);

		if (data != null)
			return data.function.toValue().getImportantValue();

		return null;
	}

}
