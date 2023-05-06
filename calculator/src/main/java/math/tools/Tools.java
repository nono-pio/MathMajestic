package math.tools;

import java.util.Arrays;

import math.element.Element;

public final class Tools {
	
	public static Element[] cloneElementArray(Element[] realValues)
	{
		Element[] newValues = new Element[realValues.length];
		for (int i = 0; i < newValues.length; i++) {
			newValues[i] = realValues[i].clone();
		}
		return newValues;
	}

	public static Element[] mergeArray(Element[] array1, Element[] array2)
	{
		Element[] result = new Element[array1.length + array2.length];
		System.arraycopy(array1, 0, result, 0, array1.length);
		System.arraycopy(array2, 0, result, array1.length, array2.length);
		return result;
	}
	
	public static <T> T[] addToArray(T[] array, T add)
	{
		T[] newArray = Arrays.copyOf(array, array.length + 1);
		newArray[newArray.length - 1] = add;
		return newArray;
	}
	public static int[] addToArray(int[] array, int add)
	{
		int[] newArray = Arrays.copyOf(array, array.length + 1);
		newArray[newArray.length - 1] = add;
		return newArray;
	}
}
