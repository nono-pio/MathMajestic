package api;

import java.util.HashMap;

import math.element.Element;
import math.element.ElementType;
import math.element.primary.Number;
import math.element.primary.Variable;
import math.element.settings.StringSettings;

public class ElementTree {

	private final HashMap<String, String> infos;
	private final ElementTree[] values;
	private final String latex;
	
	public ElementTree(Element element, StringSettings settings) {
		
		this.infos = new HashMap<String, String>();
		this.infos.put("type", element.getType().toString());
		
		if (element.getType() == ElementType.Number)
		{
			this.infos.put("value", String.valueOf(((Number) element).value));
			
		} else if (element.getType() == ElementType.Variable)
		{
			this.infos.put("variable", ((Variable) element).variable);
		}
		
		
		Element[] values = element.getValues();
		String[] valuesLaTeX = new String[values.length];
		this.values = new ElementTree[values.length];
		
		for (int i = 0; i < values.length; i++) {
			this.values[i] = new ElementTree(values[i], settings);
			valuesLaTeX[i] = this.values[i].latex;
		}
		
		
		this.latex = element.toString(null, settings, valuesLaTeX);
	}

	public HashMap<String, String> getInfos()
	{
		return infos;
	}
	
	public String getLatex()
	{
		return latex;
	}
	
	public ElementTree[] getValues()
	{
		return values;
	}
}
