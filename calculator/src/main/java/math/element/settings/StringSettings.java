package math.element.settings;

public class StringSettings {

	public boolean isLaTeX = false;
	public boolean showVariableValue = false;
	public boolean showFunctionValue = false;
	public boolean showVariableFunction = true;

	public StringSettings() {
	}

	public StringSettings setLaTeX(boolean isLaTeX) {
		this.isLaTeX = isLaTeX;
		return this;
	}

	public StringSettings setShowVariableValue(boolean showVariableValue) {
		this.showVariableValue = showVariableValue;
		return this;
	}

	public StringSettings setShowFunctionValue(boolean showFunctionValue) {
		this.showFunctionValue = showFunctionValue;
		return this;
	}

	public StringSettings setShowVariableFunction(boolean showVariableFunction) {
		this.showVariableFunction = showVariableFunction;
		return this;
	}

}
