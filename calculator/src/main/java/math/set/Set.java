package math.set;

import java.util.List;

import math.MathFlag;

import java.util.ArrayList;
import java.util.Collections;

public class Set {

	List<IEnsemble> values = new ArrayList<IEnsemble>();
	SetNumber setType;

	public Set(SetNumber setType) {

		this.setType = setType;
		if (setType == SetNumber.Natural)
			values.add(new Ensemble(MathFlag.Positive));
		else
			values.add(new Ensemble(MathFlag.InfinitySet));

	}

	public Set(SetNumber setType, float... values) {
		this.setType = setType;
		for (float f : values) {
			this.values.add(new NumberSet(f));
		}
		
		Collections.sort(this.values);
	}
	
	// <------------ Math ------------>
	
	public boolean inRange(float value)
	{
		boolean inRange = false;
		for (IEnsemble ensemble : values) {
			if (ensemble.inRange(value))
			{
				inRange = true;
				break;
			}
		}
		return inRange;
	}
	
	public void intersection(IEnsemble ensemble)
	{
		
	}

	// <------- Other Function -------->
	
	public String toString() {

		StringBuilder str = new StringBuilder(setType.toString() + " of ").append(values.get(0).toString());

		for (int i = 1; i < values.size(); i++) {
			str.append(" U ").append(values.get(i));
		}
		return str.toString();
	}

}
