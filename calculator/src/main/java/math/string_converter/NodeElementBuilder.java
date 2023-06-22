package math.string_converter;

import java.util.ArrayList;
import java.util.List;

import math.element.Element;
import math.element.PrimaryElement;
import math.element.elements.Addition;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.elements.Product;
import math.element.primary.Number;

public class NodeElementBuilder {

	int indexPrimary;
	List<NodeElementBuilder> childs;

	NodeElementType type;

	/* <------------------- Constructor --------------------> */
	public NodeElementBuilder(NodeElementType type, NodeElementBuilder firstNode) {
		this.type = type;
		this.childs = new ArrayList<>(2);
		this.childs.add(firstNode);
	}

	public NodeElementBuilder(int indexPrimary) {
		this.type = NodeElementType.Primary;
		this.indexPrimary = indexPrimary;
		this.childs = new ArrayList<>();
	}

	/* <------------------- Relation --------------------> */

	public NodeElementBuilder add(NodeElementType type) {

		if (type.priority > this.type.priority) {
			return new NodeElementBuilder(type, this);
		}

		childs.set(1, childs.get(1).add(type));

		return this;

	}

	public void addPrimary(NodeElementBuilder node) {

		if (childs.size() != 2) {
			childs.add(node);

		} else {
			childs.get(1).addPrimary(node);
		}

	}

	/* <------------------- Build --------------------> */
	public Element build(List<PrimaryElement> primarys) {

		Element[] buildChilds = getBuildChild(primarys);

		switch (type) {

		case Primary:
			return primarys.get(indexPrimary);

		case Addition:
			return new Addition(buildChilds);

		case Subtract:
			return new Addition(buildChilds[0], new Product(new Number(-1), buildChilds[1]));

		case Product:
			return new Product(buildChilds);

		case Division:
			return new Division(buildChilds[0], buildChilds[1]);

		case Power:
			return new Power(buildChilds[0], buildChilds[1]);

		}

		throw new RuntimeException("Invalid type");
	}

	private Element[] getBuildChild(List<PrimaryElement> primarys) {

		Element[] buildChilds = new Element[childs.size()];

		for (int i = 0; i < buildChilds.length; i++) {
			buildChilds[i] = childs.get(i).build(primarys);
		}

		return buildChilds;
	}

	/* <---------------- Other Method ------------------------> */

	public String toString() {

		if (type == NodeElementType.Primary) {
			return "[Primary;" + indexPrimary + "]";
		}

		StringBuilder str = new StringBuilder();

		for (NodeElementBuilder nodeElementBuilder : childs) {
			str.append(';').append(nodeElementBuilder.toString());
		}

		return "[" + type.name() + str.toString() + "]";
	}

}
