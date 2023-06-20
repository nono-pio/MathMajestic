package math.random;

import math.element.Element;
import math.element.elements.Addition;
import math.element.elements.Division;
import math.element.elements.Power;
import math.element.elements.Product;
import math.element.primary.Number;
import math.element.primary.Variable;

public class RandomElement {

	static String[] variables = { "x", "y", "z", "a", "b", "c" };

	public static Element randomBasic(int nbBranche, int pourcentVariable, int maxNumberSize, int maxLength) {

		if (nbBranche <= 0) {

			int random = pourcentRandom();
			if (random < pourcentVariable) {
				return new Variable(variables[randomRange(0, variables.length - 1)]);
			} else {
				return new Number(randomRange(-maxNumberSize, maxNumberSize));
			}

		}

		int function = randomRange(0, 3);

		switch (function) {
		case 0: // Addition

			return new Addition(randomBasics(nbBranche - 1, pourcentVariable, maxNumberSize, 2, maxLength));
		case 1: // Product

			return new Product(randomBasics(nbBranche - 1, pourcentVariable, maxNumberSize, 2, maxLength));
		case 2: // Division
			Element[] elements = randomBasics(nbBranche - 1, pourcentVariable, maxNumberSize, 2, 2);
			return new Division(elements[0], elements[1]);
		case 3: // Power

			return new Power(randomBasic(nbBranche - 1, pourcentVariable, maxNumberSize, maxLength),
					new Number(randomRange(-maxNumberSize, maxNumberSize)));
		}

		return new Variable("Error fx:" + function + " brch:" + nbBranche);

	}

	public static Element[] randomBasics(int nbBranche, int pourcentVariable, int maxNumberSize, int minLength,
			int maxLength) {

		Element[] elements = new Element[randomRange(minLength, maxLength)];

		for (int i = 0; i < elements.length; i++) {

			int brch = (i + 1 == elements.length) ? nbBranche : randomRange(0, nbBranche);
			nbBranche -= brch;

			elements[i] = randomBasic(brch, pourcentVariable, maxNumberSize, maxLength);

		}

		return elements;

	}

	public static int pourcentRandom() {
		return (int) (100 * Math.random());
	}

	public static int randomRange(int start, int end) {
		return start + (int) Math.round((end - start) * Math.random());
	}

}
