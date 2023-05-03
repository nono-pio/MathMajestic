package math.set;

import java.util.List;

import math.MathFlag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Set {

	List<Ensemble> values = new ArrayList<Ensemble>();

	public Set(MathFlag flag) {

		values.add(new Ensemble(flag));

	}

	public Set(float... values) {

		for (float f : values) {
			this.values.add(new Ensemble(f, f));
		}

		Collections.sort(this.values);
	}

	public Set(Ensemble... values) {

		for (Ensemble ens : values) {
			this.values.add(ens);
		}

		Collections.sort(this.values);
	}

	// <------------ Math ------------>

	public boolean inRange(float value) {
		boolean inRange = false;
		for (Ensemble ensemble : values) {
			if (ensemble.inRange(value)) {
				inRange = true;
				break;
			}
		}
		return inRange;
	}

	public void complement() {
		
		float start = Float.NEGATIVE_INFINITY;

		List<Ensemble> newValues = new ArrayList<Ensemble>();
		
		for (Ensemble ensemble : values) {
			newValues.add(new Ensemble(start, ensemble.min()));
			start = ensemble.max();
		}
		
		newValues.add(new Ensemble(start, Float.POSITIVE_INFINITY));
		
		values = newValues;
		
	}

	public void union(Ensemble ensemble) {

		/*
		 * List = [3,4] U [5,6] U [10, +inf[
		 * 
		 * List U [a, b]
		 * 
		 */

		int indexStart = -1;
		int indexEnd = -1;

		for (int i = 0; i < values.size(); i++) {

			if (values.get(i).max() >= ensemble.min() && indexStart == -1) {
				indexStart = i;
			}

			if (values.get(i).min() > ensemble.max()) {
				indexEnd = i - 1;
				break;
			}
		}

		if (indexEnd == -1)
			indexEnd = values.size() - 1;

		System.out.println("index = " + indexStart + " / " + indexEnd);

		Ensemble ens = new Ensemble(Math.min(values.get(indexStart).min(), ensemble.min()),
				Math.max(values.get(indexEnd).max(), ensemble.max()));

		for (int i = indexEnd; i > indexStart; i--) {
			values.remove(i);
		}

		values.set(indexStart, ens);
	}

	public void intersection(Ensemble ensemble) {

		for (int i = values.size() - 1; i >= 0; i--) {

			Ensemble ens = ensemble.intersection(values.get(i));
			values.remove(i);

			if (ens != null)
				values.add(i, ens);
		}
	}

	public void difference(Ensemble ensemble) {

	}

	public void symmetricDifference(Ensemble ensemble) {

	}

	// <------- Other Function -------->

	public String toString() {

		if (values.size() <= 0)
			return "\\O";
		StringBuilder str = new StringBuilder(values.get(0).toString());

		for (int i = 1; i < values.size(); i++) {
			str.append(" U ").append(values.get(i));
		}
		return str.toString();
	}

}
