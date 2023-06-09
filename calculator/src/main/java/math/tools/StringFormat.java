package math.tools;

public final class StringFormat {
	
	
	public static String bracket(String value, boolean isLaTex)
	{
		
		if (isLaTex) return "\\left(" + value + "\\right)";
		else return "(" + value + ")";
	}
	 
	public static String arrayStr(String[] values, String separator)
	{
		return String.join(separator, values);
	}

}
