package math.ParentClass;

import java.util.HashMap;

import math.numbers.Number;
import math.numbers.VariableData;
import math.numbers.Variable;
import math.tools.Position;


public abstract class ParentElement {
    
    public HashMap<String, VariableData> variables;

    public ParentElement(Element[] sequences) 
    {
        // find variable
        variables = new HashMap<String, VariableData>();
    }

    public abstract String toString();
    public abstract Element[] getSequences();

    public void setVariable(String variable, Number value)
    {
        forEach((e, p) -> {
        	if (e.getType() == ElementType.Variable && ((Variable) e).variable == variable)
        	{
        		((Variable) e).setVariable(variables, p.getPath(), value);
        	}
        });
    }

    public Element getChild(int[] path)
    {
        Element child = getSequences()[path[0]];
        for (int i = 1; i < path.length; i++) {
            Element[] childs = child.getValues();
            if ( childs.length - 1 < path[i] ) throw new RuntimeException("Path invalid");
            child = childs[path[i]];
        }
        return child;
    }

    public void forEach(IElement func)
    {
        Element[] sequences = getSequences();
        for (int i = 0; i < sequences.length; i++) {
            sequences[i].forEachChild(func, new Position(i));
        }
    }
}
