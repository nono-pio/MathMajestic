package math.simplification;

import java.util.ArrayList;
import java.util.List;

import math.MathN;
import math.element.Element;
import math.element.ElementType;
import math.element.elements.Addition;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.elements.Product;
import math.element.primary.Number;

public class InfinitSimplification extends Simplification {

	Number cste;

	List<Simplification> simplifications;
	List<Number> coefs;

	InfinitType type;

	public InfinitSimplification(Element element) {

		Simplification[] values = getSimplifications(element);
		simplifications = new ArrayList<>(values.length);
		coefs = new ArrayList<>(values.length);

		// System.out.println(element.toString());
		// System.out.println(Arrays.toString(values));

		ElementType elemType = element.getType();
		type = (elemType == ElementType.Addition) ? InfinitType.Sum : InfinitType.Product;

		// System.out.println(type);

		setDefaultCste();

		if (elemType == ElementType.Addition) {
			generateAdd(values);
		} else if (elemType == ElementType.Product) {
			generatePro(values);
		} else if (elemType == ElementType.Division) {
			generateDiv(values[0], values[1]);
		} else if (elemType == ElementType.Power) {
			generatePow(values[0], ((NumberSimplification) values[1]).number);
		} else {
			throw new RuntimeException("InfinitSimplification is only for Product and Addition");
		}

		// System.out.println(toString());
	}

	/* <------------------- Generator Table ---------------------> */

	public void generateAdd(Simplification[] simplifications) {

		for (Simplification simplification : simplifications) {

			if (simplification instanceof NumberSimplification num) {

				updateCste(num.number);

			} else if (simplification instanceof InfinitSimplification infSim) {

				if (infSim.type == InfinitType.Sum) {
					merch(infSim);
				} else {

					Number cste = infSim.cste;

					if (infSim.size() == 1 && infSim.coefs.get(0).isEqual(1)) {
						add(infSim.simplifications.get(0), cste);
					} else {
						infSim.setDefaultCste();
						add(infSim, cste);
					}
				}

			} else {

				add(simplification, new Number(1));
			}
		}
	}

	public void generatePro(Simplification[] simplifications) {

		for (Simplification simplification : simplifications) {

			if (simplification instanceof NumberSimplification num) {

				updateCste(num.number);

			} else if (simplification instanceof InfinitSimplification infSim && infSim.type == InfinitType.Product) {
				merch(infSim);

			} else {

				add(simplification, new Number(1));
			}
		}
	}

	public void generateDiv(Simplification num, Simplification den) {

		// numerator
		if (num instanceof NumberSimplification number) {

			updateCste(number.number);

		} else if (num instanceof InfinitSimplification infSim && infSim.type == InfinitType.Product) {
			merch(infSim);

		} else {

			add(num, new Number(1));
		}

		// denominator
		if (den instanceof NumberSimplification number) {

			cste.div(number.number);

		} else if (den instanceof InfinitSimplification infSim && infSim.type == InfinitType.Product) {
			merchDen(infSim);

		} else {

			add(den, new Number(-1));
		}

	}

	public void generatePow(Simplification base, Number exp) {

		if (base instanceof NumberSimplification number) {
			updateCste(number.number.mult(exp));

		} else if (base instanceof InfinitSimplification infSim && infSim.type == InfinitType.Product) {
			merchPow(infSim, exp);

		} else {
			add(base, exp);
		}

	}

	/* <--------------------- Simplify -----------------------------> */

	public Simplification simplify() {

		// Mult and Add
		for (int i = simplifications.size() - 1; i >= 0; i--) { // remove useless thermes x^0 or 0*x

			if (coefs.get(i).isZero()) {
				coefs.remove(i);
				simplifications.remove(i);
			}
		}

		int usefullCste = cste.isEqual(defaultCste()) ? 0 : 1;
		int count = usefullCste + simplifications.size();

		if (count == 0) // (+) 0 or (*) 1
			return new NumberSimplification(defaultCste());
		if (count == 1 && usefullCste == 1) { // 2 + 0*x +0*y or 4 * x^0 * y^0
			return new NumberSimplification(cste);
		}

		// Add or Mult
		if (type == InfinitType.Sum) {
			return simplifyAdd();
		} else {
			return simplifyMult();
		}
	}

	public Simplification simplifyAdd() {
		return this;
	}

	public Simplification simplifyMult() {

		if (cste.isZero()) {
			return new NumberSimplification(new Number(0));
		}

		return this;
	}

	/* <--------------------- Other Function -----------------------------> */

	public int size() {
		return simplifications.size();
	}

	public Element toElement() {

		int usefulCste = cste.isEqual(defaultCste()) ? 0 : 1;
		Element[] childsElement = new Element[size() + usefulCste];

		if (usefulCste == 1) {
			childsElement[0] = cste;
		}

		for (int i = 0; i < simplifications.size(); i++) {

			childsElement[i + usefulCste] = toChildElement(i);

		}

		if (childsElement.length == 1) {
			return childsElement[0];
		}

		if (type == InfinitType.Sum) {
			return new Addition(childsElement);
		} else {
			return new Product(childsElement);
		}
	}

	public Element toChildElement(int index) {

		Simplification simplification = simplifications.get(index);
		Number coef = coefs.get(index);

		if (type == InfinitType.Sum) {

			if (coef.isEqual(new Number(1))) {
				return simplification.toElement();
			} else {
				return new Product(coef, simplification.toElement());
			}

		} else {

			if (coef.isEqual(new Number(1))) {
				return simplification.toElement();
			} else if (coef.isEqual(new Number(-1))) {
				return new Division(new Number(1), simplification.toElement());
			} else {
				return new Power(simplification.toElement(), coef);
			}

		}
	}

	public void setDefaultCste() {
		cste = defaultCste();
	}

	public Number defaultCste() {

		if (type == InfinitType.Sum) {
			return new Number(0);
		} else {
			return new Number(1);
		}
	}

	public void updateCste(Number number) {

		if (type == InfinitType.Sum) {
			cste.add(number);
		} else {
			cste.mult(number);
		}
	}

	public void add(Simplification simp, Number value) {

		for (int i = 0; i < simplifications.size(); i++) {
			if (simp.isEqual(simplifications.get(i))) {
				coefs.set(i, coefs.get(i).add(value));
				return;
			}
		}

		simplifications.add(simp);
		coefs.add(value);
	}

	public void merch(InfinitSimplification simplification) {

		updateCste(simplification.cste);

		for (int i = 0; i < simplification.simplifications.size(); i++) {

			add(simplification.simplifications.get(i), simplification.coefs.get(i));

		}

	}

	public void merchDen(InfinitSimplification simplification) {

		cste.div(simplification.cste);

		for (int i = 0; i < simplification.simplifications.size(); i++) {

			add(simplification.simplifications.get(i), simplification.coefs.get(i).mult(new Number(-1)));

		}

	}

	public void merchPow(InfinitSimplification simplification, Number exp) {

		cste.mult(MathN.pow(simplification.cste, exp));

		for (int i = 0; i < simplification.simplifications.size(); i++) {

			add(simplification.simplifications.get(i), simplification.coefs.get(i).mult(exp));

		}

	}

	public boolean isEqual(Simplification simplification) {
		if (simplification instanceof InfinitSimplification inf) {

			if (inf.type != type)
				return false;

			if (inf.simplifications.size() != simplifications.size())
				return false;

			for (int i = 0; i < simplifications.size(); i++) {
				if (!simplifications.get(i).isEqual(inf.simplifications.get(i)))
					return false;
				if (!coefs.get(i).isEqual(inf.coefs.get(i)))
					return false;
			}

			return true;
		}

		return false;
	}

	public String toString() {

		StringBuilder str = new StringBuilder("[").append(type.name()).append(";").append(cste.toString());
		for (int i = 0; i < coefs.size(); i++) {
			str.append(";").append(toChildString(i));
		}

		return str.append("]").toString();
	}

	public String toChildString(int index) {

		return simplifications.get(index).toString() + '/' + coefs.get(index).toString();

	}

	enum InfinitType {
		Sum, Product
	}
}
