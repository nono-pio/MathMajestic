package math.set;

import java.util.List;

import math.MathFlag;

import java.util.ArrayList;
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
		boolean startInclude = false;

		List<Ensemble> newValues = new ArrayList<Ensemble>();

		for (Ensemble ensemble : values) {
			newValues.add(new Ensemble(startInclude, start, !ensemble.minInclude(), ensemble.min()));
			start = ensemble.max();
			startInclude = !ensemble.maxInclude();
		}

		newValues.add(new Ensemble(startInclude, start, false, Float.POSITIVE_INFINITY));

		values = newValues;

	}

	public void union(Set set) {
		for (Ensemble ensemble : set.values) {
			union(ensemble);
		}
	}

	public void union(float value) {
		union(new Ensemble(value));
	}

	public void union(Ensemble ensemble) {

		int indexStart = -1;
		int indexEnd = -1;

		boolean ensMinInclude = ensemble.minInclude();
		float ensMin = ensemble.min();
		boolean ensMaxInclude = ensemble.maxInclude();
		float ensMax = ensemble.max();

		boolean minInclude = true;
		boolean maxInclude = true;

		for (int i = 0; i < values.size(); i++) {

			Ensemble curEns = values.get(i);
			float curMin = curEns.min();
			float curMax = curEns.max();

			if (indexStart == -1) {
				if (ensMin < curMax) {
					indexStart = i;
					minInclude = curEns.minInclude();

				} else if (ensMin == curMax && (curEns.maxInclude() || ensMinInclude)) {
					indexStart = i;
					minInclude = curEns.minInclude();
				}
			}

			if (curMin < ensMax) {
				indexEnd = i;
				maxInclude = curEns.maxInclude();

			} else if (curMin == ensMax && (curEns.minInclude() || ensMaxInclude)) {
				indexEnd = i;
				maxInclude = curEns.maxInclude();
			} else
				break;
		}

		if (indexEnd == -1)
			indexEnd = values.size() - 1;

		float min;
		float max;

		if (ensMin < values.get(indexStart).min()) {
			minInclude = ensMinInclude;
			min = ensMin;
		} else
			min = values.get(indexStart).min();

		if (ensMax > values.get(indexEnd).max()) {
			maxInclude = ensMaxInclude;
			max = ensMax;
		} else
			max = values.get(indexEnd).max();

		Ensemble ens = new Ensemble(minInclude, min, maxInclude, max);

		for (int i = indexEnd; i >= indexStart; i--) {
			values.remove(i);
		}

		values.add(indexStart, ens);
	}

	public void intersection(Set set) {

		List<Ensemble> newValues = new ArrayList<Ensemble>();

		for (Ensemble ensemble : set.values) {

			newValues.addAll(getIntersection(values, ensemble));

		}

		values = newValues;

		Collections.sort(values);
	}

	public void intersection(Ensemble ensemble) {

		for (int i = values.size() - 1; i >= 0; i--) {

			Ensemble ens = ensemble.intersection(values.get(i));
			values.remove(i);

			if (ens != null)
				values.add(i, ens);
		}
	}

	public static List<Ensemble> getIntersection(List<Ensemble> A, Ensemble B) {

		List<Ensemble> result = new ArrayList<Ensemble>();

		for (int i = A.size() - 1; i >= 0; i--) {

			Ensemble ens = B.intersection(A.get(i));

			if (ens != null)
				result.add(ens);
		}

		return result;
	}

	public void difference(Set B) {

		B.complement();
		intersection(B);

	}

	public void difference(Ensemble ensemble) {
		difference(new Set(ensemble));
	}

	public void symmetricDifference(Set B) {

		Set A = clone();
		A.intersection(B);
		A.complement();

		union(B);
		intersection(A);

	}

	public void symmetricDifference(Ensemble ensemble) {
		symmetricDifference(new Set(ensemble));
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

	public Set clone() {

		Ensemble[] clonedValues = new Ensemble[values.size()];

		for (int i = 0; i < values.size(); i++) {
			clonedValues[i] = values.get(i).clone();
		}

		return new Set(clonedValues);
	}

}
