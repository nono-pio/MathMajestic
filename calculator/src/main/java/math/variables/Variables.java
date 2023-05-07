package math.variables;

import java.util.HashMap;

import math.element.primary.Number;

public class Variables {

	HashMap<String, VariableData> variables = new HashMap<>();

	public VariableData setVariable(String variable) {
		return setVariable(variable, null);
	}

	public VariableData setVariable(String variable, Number value) {
		VariableData data = variables.get(variable);
		
		if (data == null) {
			data = new VariableData(value);
		} else
			data.value = value;
		
		variables.put(variable, data);
		return data;
	}

	public boolean isGlobal(String variable) {
		return variables.containsKey(variable);
	}

	public VariableData getData(String variable) {
		return variables.get(variable);
	}

	public Number toValue(String variable) {
		VariableData data = getData(variable);
		
		if (data != null)
			return data.value;
		
		return null;
	}

}
