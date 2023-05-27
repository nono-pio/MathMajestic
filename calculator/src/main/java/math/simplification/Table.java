package math.simplification;

import java.util.List;
import java.util.ArrayList;

import math.element.Element;
import math.element.primary.Number;

public class Table {

	List<Element> elements;
	List<Number> datas;

	public Table() {
		elements = new ArrayList<>();
		datas = new ArrayList<>();
	}

	public void add(Element element, Number data) {

		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).isEqual(element)) {

				datas.set(i, datas.get(i).add(data));
				return;
			}
		}

		elements.add(element);
		datas.add(data);

	}

	public void addTable(Table table) {

		for (int i = 0; i < table.elements.size(); i++) {

			add(table.elements.get(i), table.datas.get(i));

		}
	}

	public void subTable(Table table) {

		for (int i = 0; i < table.elements.size(); i++) {

			add(table.elements.get(i), table.datas.get(i).mult(new Number(-1)));

		}
	}

	public Element getElement(int index, TableElementGenerator generator) {
		return generator.generateElement(elements.get(index), datas.get(index));
	}

	public List<Element> getListElement(TableElementGenerator generator) {

		List<Element> list = new ArrayList<>(elements.size());

		for (int i = 0; i < elements.size(); i++) {
			list.add(generator.generateElement(elements.get(i), datas.get(i)));
		}

		return list;
	}

}
