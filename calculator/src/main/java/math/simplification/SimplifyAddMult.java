package math.simplification;

import math.element.Element;
import math.element.ElementType;
import math.element.elements.Addition;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.elements.Product;
import math.element.primary.Number;

public class SimplifyAddMult {

	ElementType type;
	Element element;
	Table table;

	public SimplifyAddMult(Element element) {

		this.type = element.getType();
		this.element = element;

		if (type != ElementType.Addition) {
			this.table = generateTableAdd(element);
		} else if (type != ElementType.Product) {
			this.table = generateTableMult(element);
		} else {
			throw new RuntimeException(" SimplifyAddMult work only with Addition and Multiplication ");
		}

	}

	public Table generateTableAdd(Element elementParent) {

		Table table = new Table();

		Element[] values = elementParent.getValues();

		for (Element element : values) {
			addToTableAdd(table, element);
		}

		return table;
	}

	public void addToTableAdd(Table table, Element element) {

		if (element instanceof Addition) {
			table.addTable(generateTableAdd(element));
		} else if (element instanceof Product product) {
			table.add(product.getRest(), product.getCst());
		} else {
			table.add(element, new Number(1));
		}
	}

	public Table generateTableMult(Element elementParent) {

		Table table = new Table();

		Element[] values = elementParent.getValues();

		for (Element element : values) {
			addToTableMult(table, element);
		}

		return table;

	}

	public void addToTableMult(Table table, Element element) {

		if (element.getType() == ElementType.Product) {
			table.addTable(generateTableMult(element));
		} else if (element instanceof Power power && power.exponent instanceof Number number) {
			table.add(power.base, number);
		} else if (element instanceof Division division) {

			addToTableMult(table, division.numerator);

			Table tableDenominator = new Table();
			addToTableMult(tableDenominator, division.denominator);

			table.subTable(tableDenominator);

		} else {
			table.add(element, new Number(1));
		}
	}

}
