package math.string_converter;

import java.util.ArrayList;
import java.util.List;

import math.element.Element;
import math.element.primary.Number;

public class ElementBuilder {

	NodeElementBuilder firstNode;

	boolean lastIsOperator = true;

	List<Element> elements;

	public ElementBuilder() {
		elements = new ArrayList<>();
	}

	public void add(NodeElementType type) {

		if (type == NodeElementType.Subtract && lastIsOperator) {
			add(new Number(-1));
			add(NodeElementType.Product);
			return;
		}

		firstNode = firstNode.add(type);
		lastIsOperator = true;

	}

	public void add(Element element) {

		if (!lastIsOperator) {
			add(NodeElementType.Product);
		}

		lastIsOperator = false;

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
