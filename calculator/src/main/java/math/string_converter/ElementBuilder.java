package math.string_converter;

import java.util.ArrayList;
import java.util.List;

import math.element.Element;

public class ElementBuilder {

	NodeElementBuilder firstNode;

	List<Element> elements;

	public ElementBuilder() {
		elements = new ArrayList<>();
	}

	public void add(NodeElementType type) {

		firstNode = firstNode.add(type);

	}

	public void add(Element element) {

		elements.add(element);

		if (firstNode == null) {
			firstNode = new NodeElementBuilder(elements.size() - 1);
		} else {
			firstNode.addPrimary(new NodeElementBuilder(elements.size() - 1));
		}
	}

	public Element build() {
		return firstNode.build(elements);
	}

	public String toString() {

		return elements + "\t" + firstNode;
	}

}
