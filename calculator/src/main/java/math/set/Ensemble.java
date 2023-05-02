package math.set;

import math.MathFlag;

public class Ensemble extends IEnsemble {

	boolean startInclude = true;
	float start;
	
	boolean endInclude = true;
	float end;

	public Ensemble(MathFlag flag)
	{
		if (flag == MathFlag.InfinitySet)
		{
			this.startInclude = false;
			this.start = Float.NEGATIVE_INFINITY;
			this.endInclude = false;
			this.end = Float.POSITIVE_INFINITY;
			
		} else if (flag == MathFlag.Positive)
		{
			this.start = 0;
			this.endInclude = false;
			this.end = Float.POSITIVE_INFINITY;
			
		} else if (flag == MathFlag.Negative)
		{
			this.startInclude = false;
			this.start = Float.NEGATIVE_INFINITY;
			this.end = 0;
			
		} else
		{
			throw new RuntimeException(" Unvalid Set : " + flag);
		}
	}
	
	public Ensemble(float start, float end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Ensemble(boolean startInclude, float start, boolean endInclude, float end) {
		super();
		this.startInclude = startInclude;
		this.start = start;
		this.endInclude = endInclude;
		this.end = end;
	}
	
	public Ensemble(boolean startInclude, float start, float end) {
		super();
		this.startInclude = startInclude;
		this.start = start;
		this.end = end;
	}
	
	public Ensemble(float start, boolean endInclude, float end) {
		super();
		this.start = start;
		this.endInclude = endInclude;
		this.end = end;
	}

	public float min() { return start; }

	public float max() { return end; }

	public boolean inRange(float value) {
		
		boolean isBigger;
		if (startInclude) isBigger = value >= start;
		else isBigger = value > start;
		
		boolean isSmaller;
		if (startInclude) isSmaller = value <= end;
		else isSmaller = value < end;
		
		return isBigger && isSmaller;
		
	}

	@Override
	public String toString() {
		String startS = startInclude ? "[" + start :  "]" + start;
		String endS = endInclude ? end + "]" : end + "[" ;
		return startS + "; " + endS;
	}

}
