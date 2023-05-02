package math.set;

public class NumberSet extends IEnsemble {

	float value;
	
	public NumberSet(float value) {
		this.value = value;
	}

	public float min() { return value; }
	public float max() { return value; }
	public boolean inRange(float value) { return value == this.value; }
	public String toString() { return String.valueOf(value); }

}
