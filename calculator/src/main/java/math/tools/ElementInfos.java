package math.tools;

import java.util.List;
import java.util.ArrayList;

import math.MathFlag;
import math.element.Element;

public class ElementInfos {

	List<Integer> path;
	List<MathFlag> attributes;

	Element parent;

	public ElementInfos() {

		path = new ArrayList<>();
		attributes = new ArrayList<>();
		parent = null;

	}

	public ElementInfos(List<Integer> path, List<MathFlag> attribute, Element parent) {

		this.path = path;
		this.attributes = attribute;
		this.parent = parent;

	}

	public ElementInfos childInfo(Element element, MathFlag newAttribute, int childIndex) {

		List<Integer> newPath = new ArrayList<>(path);
		newPath.add(childIndex);

		List<MathFlag> newAttributes = new ArrayList<>(attributes);
		newAttributes.add(newAttribute);

		return new ElementInfos(newPath, newAttributes, element);
	}
	
	public ElementInfos clearAttribute()
	{
		attributes.clear();
		return this;
	}
	
	public ElementInfos addAttibute(MathFlag attribute)
	{
		attributes.add(attribute);
		return this;
	}

	public boolean has(MathFlag attribute) {
		return attributes.contains(attribute);
	}

}
