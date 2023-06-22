package math.string_converter;

import java.util.ArrayList;
import java.util.List;

import math.element.Element;
import math.element.PrimaryElement;

public class ElementBuilder {

	NodeElementBuilder firstNode;

	List<PrimaryElement> primarys;

	public ElementBuilder() {
		primarys = new ArrayList<>();
	}

	public void add(NodeElementType type) {

		firstNode = firstNode.add(type);

	}

	public void add(PrimaryElement primary) {

		primarys.add(primary);

		if (firstNode == null) {
			firstNode = new NodeElementBuilder(primarys.size() - 1);
		} else {
			firstNode.addPrimary(new NodeElementBuilder(primarys.size() - 1));
		}
	}

	public Element build() {
		return firstNode.build(primarys);
	}

	public String toString() {

		return primarys + "\t" + firstNode;
	}

}
