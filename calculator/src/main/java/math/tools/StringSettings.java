package math.tools;

public class StringSettings {
	
	public boolean isLaTeX = false;
	public boolean showVariableValue = false;

	public StringSettings() {}

	public StringSettings(boolean isLaTeX) {
		this.isLaTeX = isLaTeX;
	}
	
	public StringSettings(boolean isLaTeX, boolean showVariableValue) {
		this.isLaTeX = isLaTeX;
		this.showVariableValue = showVariableValue;
	}

}
