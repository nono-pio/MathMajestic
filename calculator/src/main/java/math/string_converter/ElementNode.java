package math.string_converter;

import java.util.ArrayList;
import java.util.Arrays;

import math.element.Element;

public class ElementNode {

	ArrayList<ElementNode> child = new ArrayList<>();
	PriorityOperator op;
	int indexRoot;
	
	public ElementNode(PriorityOperator op)
	{
		this.op = op;
	}
	public ElementNode(int index)
	{
		this.op = PriorityOperator.RootElem;
		indexRoot = index;
	}
	
	public ElementNode addChild(ElementNode node)
	{
		if (op == PriorityOperator.Root) return node;
		
		if (node.op.power > op.power)
			return node.addChild(this);
		else if (node.op.power == op.power && !isMax())
			child.add(node);
		else
		{
			int lastIndex = child.size() - 1;
			if (lastIndex >= 0)
			{
				ElementNode lastChild = child.get(lastIndex);
				if (node.op.power > lastChild.op.power)
					child.set(lastIndex, lastChild.addChild(node));
				else if (isMax())
					lastChild.addChild(node);
				else
					child.add(node);
				
			} else
			{
				child.add(node);
			}
		}
		return this;
	}
	
	public boolean isMax()
	{
		if (op == PriorityOperator.Root || op == PriorityOperator.RootElem) return true;
		return child.size() >= 2;
	}
	
	public Element toElement(ArrayList<Element> rootElement)
	{
		if (op == PriorityOperator.RootElem) return rootElement.get(indexRoot);
		
		PriorityOperator ImOP = (op == PriorityOperator.Add || op == PriorityOperator.Mult) ? op : null;
		
		ArrayList<Element> childsElement = new ArrayList<Element>(2);
		for (int i = 0; i < child.size(); i++)
		{
			if (child.get(i).op != ImOP)
				childsElement.add(child.get(i).toElement(rootElement));
			else
				childsElement.addAll(Arrays.asList(child.get(i).toElement(rootElement).getValues()));
		}
		
		return op.toElement(childsElement.toArray(new Element[childsElement.size()]));
	}

	@Override
	public String toString() {
		return op + " : " + child;
	}
}
