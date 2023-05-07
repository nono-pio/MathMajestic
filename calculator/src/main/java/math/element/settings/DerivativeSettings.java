package math.element.settings;

public class DerivativeSettings {

	public final String variable;

	boolean useFonctionValue;

	public DerivativeSettings(String variable) {
		this.variable = variable;
	}

	public DerivativeSettings setUseFonctionValue(boolean useFonctionValue) {
		this.useFonctionValue = useFonctionValue;
		return this;
	}
}
