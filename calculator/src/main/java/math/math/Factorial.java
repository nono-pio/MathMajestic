package math.math;

public class Factorial {

	public static int factorial(int n) {
		if ( n == 0 || n == 1) return 1;
		else return n * factorial(n - 1);
	}
	
	public static int[] factorialArray(int n)
	{
		int[] array = new int[n + 1];
		array[0] = 1;
		for (int i = 1; i < array.length; i++) {
			array[i] = i * array[i - 1];
		}
		return array;
	}
	
	public static int binome(int n, int k)
	{
		return (int) (factorial(n) / (float) (factorial(k) * factorial(n - k)));
	}
	
	public static int multinome(int n, int[] kVector)
	{
		int product = 1;
		for (int k : kVector) {
			product *= factorial(k);
		}
		return (int) (factorial(n) / (float) product);
	}
	
	public static int multinome(int n, int[] kVector, int[] arrayFac)
	{
		int product = 1;
		for (int k : kVector) {
			product *= arrayFac[k];
		}
		return (int) (arrayFac[n] / (float) product);
	}
}
