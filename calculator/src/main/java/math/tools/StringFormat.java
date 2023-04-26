package math.tools;

import math.ParentClass.Element;
import math.ParentClass.ElementType;

public final class StringFormat {
	
	
	public static String bracket(String value, boolean isLaTex)
	{
		
		if (isLaTex) return "\\left(" + value + "\\right)";
		else return "(" + value + ")";
	}
	 
	public static String arrayStr(Element[] values, String separator, ElementType type, boolean isLaTex)
	{
		StringBuilder str = new StringBuilder(values[0].toString(type, isLaTex));
		for (int i = 1; i < values.length; i++) {
			str.append(separator + values[i].toString(type, isLaTex));
		}
		return str.toString();
	}

}
