package exeptions.math;

public class NegativeSqrtException extends MathException {

	public NegativeSqrtException() {

	}

	public NegativeSqrtException(String message) {
		super(message);

	}

	public NegativeSqrtException(Throwable throwable) {
		super(throwable);

	}

	public NegativeSqrtException(String message, Throwable throwable) {
		super(message, throwable);

	}

}
