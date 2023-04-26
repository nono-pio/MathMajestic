package math.ParentsElements;

import java.util.ArrayList;
import java.util.Arrays;
import math.ParentClass.*;
import math.numbers.*;
import math.numbers.Number;
import math.tools.ErrorMessage;

public class Equation extends ParentElement{
    
    public Element rightElement;
    public Element leftElement;
    
    public static VariableData dataCurSolve;

    public Equation(Element rightElement, Element leftElement)
    {
        super( new Element[] { rightElement.clone(), leftElement.clone() } );
        this.rightElement = rightElement.clone();
        this.leftElement = leftElement.clone(); 
    }

    public Number[] solveFor(String variable)
    {
        VariableData varData = getData(variable);
        if (varData.variableCount == 0) throw ErrorMessage.NoVariable(variable);
        if (varData.variableCount == 1)
        {
            int[] path = varData.paths.get(0);
            Element[] sequence = getSequences();

            Element elementVar = sequence[path[0]];
            Number value = sequence[1 - path[0]].toValue();

            int[] newPath = Arrays.copyOfRange(path, 1, path.length);

            Number result = elementVar.recipFunction(newPath, value).toValue();

            return new Number[] {result};
        }

        return null;
    }
    
    public VariableData getData(String variable)
    {
    	if (dataCurSolve == null) dataCurSolve = new VariableData(null);
    	dataCurSolve.variableCount = 0;
    	dataCurSolve.paths = new ArrayList<int[]>();
    	forEach((e, p) -> {
    		if (e.getType() == ElementType.Variable && ((Variable) e).variable == variable)
    		{
    			Equation.dataCurSolve.variableCount++;
    			Equation.dataCurSolve.paths.add(p.getPath().clone());
    		}
    	});
    	return dataCurSolve;
    }

    public boolean isTrue() { return rightElement.toValue().isEqual(leftElement.toValue()); }

    @Override
    public String toString() { return rightElement.toString() + " = " + leftElement.toString(); }
    public String toLaTeX() { return rightElement.toLaTeX() + " = " + leftElement.toLaTeX(); }
    
    @Override
    public Element[] getSequences() { return new Element[] {rightElement, leftElement}; }

    public Element getRecipFunc(String variable)
    {

        VariableData data = getData(variable);
        if (data.variableCount != 1) return null;
        
        int[] path = data.paths.get(0);

        Element reciprocal = path[0] == 0 ? leftElement : rightElement;
        Element rest = path[0] == 0 ? rightElement : leftElement;

        return rest.recipFunction(Element.newPath(path), reciprocal);
    }

}