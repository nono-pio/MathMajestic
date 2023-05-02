package math.set;

public abstract class IEnsemble implements Comparable<IEnsemble> {

	public abstract float min();
	public abstract float max();
	
	public abstract boolean inRange(float value);
	
	public abstract String toString();
	
	public int compareTo(IEnsemble ensemble) {
		
		if (ensemble.min() < min() && ensemble.max() < max())
			return -1;
		else if (ensemble.min() > min() && ensemble.max() > max())
			return 1;
		else
			return 0;
		
	}
}
