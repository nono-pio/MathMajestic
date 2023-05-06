package math.string_converter;

import java.util.ArrayList;

import math.tools.ErrorMessage;
import math.element.Element;
import math.element.primary.Number;
import math.element.primary.Variable;

public class StringConverter {

	public static final String numberPatern = "\\-?[0-9]+((\\.|,)[0-9]+)?";
	
	private String string;
	private ArrayList<Element> rootElement = new ArrayList<>();
	
	public StringConverter(String string)
	{
		this.string = string;
	}
	
	
	public Element toElement()
	{ 
		
		StringBuilder curString = new StringBuilder();
		
		ElementNode rootNode = new ElementNode(PriorityOperator.Root);
		
		/*
		 * 1) isNumber -> false -> add
		 * 			   -> true -> append
		 * 2) isMathSymbol -> true -> add
		 * 				   -> false -> last isMathSymbol -> true -> continue
		 * 												 -> false -> add "*"
		 * 3) isLetter -> true -> add
		 * */
		
		boolean lastIsMathSymbol = true;
		
		int bracket = 0;
		boolean onBracket = false;
		
		for (char c : string.toCharArray()) {
			
			if (c == ' ') continue;
			
			if (c == '(')
			{
				if (bracket != 0) curString.append(c);
				
				onBracket = true;
				bracket++;
				continue;
				
			}else if (c == ')')
			{
				bracket--;
				if (bracket == 0)
				{
					onBracket = false;
					rootElement.add( new StringConverter(curString.toString()).toElement() );
					rootNode = rootNode.addChild(new ElementNode(rootElement.size() - 1));
					curString = new StringBuilder();
				} else
				{
					curString.append(c);
				}
				continue;
				
			}else if (onBracket)
			{
				curString.append(c);
				continue;
			}
			
			if (Character.isDigit(c) || ",.".indexOf(c) != -1) // if integer or , or .
			{
				curString.append(c);
				continue;
				
			} else if (!curString.isEmpty()) //if number isn't empty add to rootElem
			{
				String numStr = curString.toString();
				if (!numStr.matches(numberPatern)) throw ErrorMessage.IndeterminedString(numStr);
				rootElement.add(new Number(Float.valueOf(numStr.replace(',', '.'))));
				rootNode = rootNode.addChild(new ElementNode(rootElement.size() - 1));
				curString = new StringBuilder();
			}
			
			boolean isMathSymbol = true;
			if (c == '+') // if c is a math symbol
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Add));
				
			} else if (c == '-')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Sub));
			}  else if (c == '*')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Mult));
			} else if (c == '/')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Div));
			} else if (c == '^')
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Pow));
			} else if (!lastIsMathSymbol)
			{
				rootNode = rootNode.addChild(new ElementNode(PriorityOperator.Mult));
				isMathSymbol = false;
				
			} else
			{
				isMathSymbol = false;
			}
			lastIsMathSymbol = isMathSymbol;
			
			if (Character.isAlphabetic(c))
			{
				rootElement.add(new Variable(String.valueOf(c)));
				rootNode = rootNode.addChild(new ElementNode(rootElement.size() - 1));
			}
		}
		
		if (!curString.isEmpty())
		{
			String numStr = curString.toString();
			if (!numStr.matches(numberPatern)) throw ErrorMessage.IndeterminedString(numStr);
			rootElement.add(new Number(Float.valueOf(numStr.replace(',', '.'))));
			rootNode = rootNode.addChild(new ElementNode(rootElement.size() - 1));
			curString = new StringBuilder();
		}
		
		return rootNode.toElement(rootElement);
	}
	
	@Override
	public String toString()
	{
		return string;
	}
}
