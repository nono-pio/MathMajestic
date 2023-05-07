package math.element.settings;

import math.element.primary.Number;

public class NumberResponse {
	
	final Number[] values; 
	
	public NumberResponse(Number... values) {
		this.values = values;
	}
	
	public Number getImportantValue() {
		return values[0];
	}
}