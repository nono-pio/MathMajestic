package math.math;

import java.util.ArrayList;
import java.util.Arrays;

import math.ParentClass.Element;
import math.elements.Addition;
import math.elements.Power;
import math.elements.Product;
import math.numbers.Number;

public final class AdditionExtention {

	
	public static Addition Power(Addition add, int n) { // multinome by Newton
		
		Element[] elemAdd = add.getValues();
		ArrayList<int[]> kVector = getPowerCouples(n, elemAdd.length);
		
		int[] facArray = Factorial.factorialArray(n);
		
		Element[] newElemAdd = new Element[kVector.size()];
		for (int i = 0; i < newElemAdd.length; i++) {
			int[] k = kVector.get(i);
			ArrayList<Element> product = new ArrayList<>();
			
			Number coef = new Number(Factorial.multinome(n, k, facArray));
			if (!coef.isEqual(new Number(1))) product.add(coef);
			
			for (int j = 0; j < k.length; j++) {
				if (k[j] == 0) continue;
				else if (k[j] == 1) product.add(elemAdd[j]);
				else product.add(new Power(elemAdd[j], new Number(k[j])));
			}
			
			if (product.size() == 1) newElemAdd[i] = product.get(0);
			else newElemAdd[i] = new Product(product.toArray(new Element[product.size()]));
		}
		
		return new Addition(newElemAdd);
	}
	
	public static Addition Product(Addition[] adds, ArrayList<Element> other)
	{
		
		if (adds == null) adds = new Addition[0];
		if (other == null) other = new ArrayList<>();
		if (adds.length == 1 && other.size() == 0) return adds[0];
		
		ArrayList<Element[]> addsValues = new ArrayList<>(adds.length);
		for (Addition add : adds) {
			addsValues.add(add.getValues());
 		}
		
		ArrayList<Element[]> couples = getProductCouples(addsValues, other);
		Product[] monome = new Product[couples.size()];
		for (int i = 0; i < monome.length; i++) {
			monome[i] = new Product(couples.get(i));
		}
		
		return new Addition(monome);
	}
	
	private static ArrayList<int[]> getPowerCouples(int n, int length)
	{
		ArrayList<int[]> couples = new ArrayList<>();
		if (n == 0)
		{
			couples.add(new int[length]);
			return couples;
		} else if (length == 1)
		{
			couples.add(new int[] {n});
			return couples;
		}
		for (int i = 0; i <= n; i++) {
			ArrayList<int[]> subCouples = getPowerCouples(n - i, length - 1);
			for (int[] subCouple : subCouples) {
				int[] newSubCouple = Arrays.copyOf(subCouple, subCouple.length + 1);
				newSubCouple[newSubCouple.length - 1] = i;
				couples.add(newSubCouple);
			}
		}
		return couples;
	}

	
	public static ArrayList<Element[]> getProductCouples(ArrayList<Element[]> couplesList, ArrayList<Element> base)
	{ return AdditionExtention.getProductCouples(new Element[couplesList.size()], couplesList, 0, base); }

	public static ArrayList<Element[]> getProductCouples(Element[] curPath, ArrayList<Element[]> paths, int index, ArrayList<Element> base)
	{
		if (index >= paths.size())
		{ 
			ArrayList<Element[]> r = new ArrayList<>();
			Element[] path = new Element[base.size() + curPath.length];
			System.arraycopy(base.toArray(new Element[base.size()]), 0, path, 0, base.size());  
			System.arraycopy(curPath, 0, path, base.size(), curPath.length); 
			r.add(path);
			return r;
		}
		ArrayList<Element[]> couples = new ArrayList<Element[]>();
		for (Element path : paths.get(index)) {
			curPath[index] = path;
			couples.addAll(getProductCouples(curPath, paths, index + 1, base));
		}
		
		return couples;
	}

}
