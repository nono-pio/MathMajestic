package math.numbers;

import java.util.HashMap;

import math.ParentClass.ParentElement;

public final class GlobalVariable {

	public static HashMap<String, VariableData> globalVariables = new HashMap<>();
	
	public static VariableData setVariable(String variable, Number value) { return setVariable(variable, value, globalVariables); }
	public static VariableData setVariable(String variable, Number value, HashMap<String, VariableData> variables)
	{
		VariableData data = variables.get(variable);
        if (data == null)
        {
        	data = new VariableData(value);
        } else
        	data.value = value;
        variables.put(variable, data);
        return data;
	}
	
	public static boolean isGlobal(String variable)
	{
		return globalVariables.containsKey(variable);
	}
	
	public static VariableData getData(String variable)
	{
		return globalVariables.get(variable);
	}
	public static VariableData getData(String variable, ParentElement parent)
	{
		if (parent != null)
		{
			VariableData parData = parent.variables.get(variable);
			if (parData != null) return parData;
		}
		return globalVariables.get(variable);
	}
	
	public static Number toValue(String variable)
	{
		VariableData data = getData(variable);
		if (data != null) return data.value;
		return null;
	}
	public static Number toValue(String variable, ParentElement parent)
	{
		VariableData data = getData(variable, parent);
		if (data != null) return data.value;
		return null;
	}
	
}
