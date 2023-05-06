package math.tools;

import math.element.ElementType;

public class Position {

	int[] path;
	ElementType[] parentTypes;
	
	public Position(int[] path, ElementType[] parentTypes)
	{
		this.path = path;
		this.parentTypes = parentTypes;
	}
	public Position(int startPath)
	{
		path = new int[] {startPath};
		parentTypes = new ElementType[0];
	}
	
	public Position generateNewPosition(ElementType parentType, int pathIndex)
	{
		int[] newPath = Tools.addToArray(path, pathIndex);
		ElementType[] newParentTypes = Tools.addToArray(parentTypes, parentType);
		
		return new Position(newPath, newParentTypes);
	}
	
	public int[] getPath() {
		return path;
	}
	public ElementType[] getParentTypes() {
		return parentTypes;
	}
}
