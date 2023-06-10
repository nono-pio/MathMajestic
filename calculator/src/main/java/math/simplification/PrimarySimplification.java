package math.simplification;

import math.element.Element;
import math.element.PrimaryElement;

public class PrimarySimplification extends Simplification {

	PrimaryElement element;

	public PrimarySimplification(PrimaryElement element) {
		this.element = element;
	}

	public boolean isEqual(Simplification simplification) {
		if (simplification instanceof PrimarySimplification primary) {
			return primary.element.isEqual(element);
		}
		return false;
	}

	public Simplification simplify() {
		return this;
	}

	public Element toElement() {
		return element;
	}

	public String toString() {
		return element.toString();
	}

}
