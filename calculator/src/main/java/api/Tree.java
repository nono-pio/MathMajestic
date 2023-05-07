package api;

import math.element.Element;
import math.element.settings.StringSettings;

public class Tree {

	private final ElementTree element;

	public Tree(Element element) {
		
		this.element = new ElementTree(element, new StringSettings());
	}
	
	public Tree(Element element, StringSettings settings) {
		
		this.element = new ElementTree(element, settings);
	}
	
	public ElementTree getTree() {
		return element;
	}

}
