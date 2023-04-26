package math.numbers;

import java.util.ArrayList;

public class VariableData {

    public int variableCount;
    public ArrayList<int[]> paths = new ArrayList<>();
    public Number value;
    
    public VariableData(Number value)
    {
        this.variableCount = 1;
        this.value = value; 
    }

    @Override
    public String toString() //str = "x= val=3; count=2; paths=[1-2-3; 2-3-4]"
    {
        if (paths == null || paths.size() == 0) return value +"; count=" +variableCount;
        StringBuilder strPaths = new StringBuilder(toStringPath(0));

        for (int i = 1; i < paths.size(); i++) {
            strPaths.append("; " + toStringPath(i));
        }

        String str = value +"; count=" +variableCount+"; paths=[" + strPaths +"]";
        return str;
    }

    private String toStringPath(int index)
    {
        StringBuilder strPath = new StringBuilder(String.valueOf(paths.get(index)[0]));
        for (int i = 1; i < paths.get(index).length; i++) {
            strPath.append("-"+paths.get(index)[i]);
        }
        return strPath.toString();
    }

}
