package exeptions.math;

public abstract class MathException extends RuntimeException {

	public MathException() {

	}

	public MathException(String message) {
		super(message);
	}

	public MathException(Throwable throwable) {
		super(throwable);
	}

	public MathException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
