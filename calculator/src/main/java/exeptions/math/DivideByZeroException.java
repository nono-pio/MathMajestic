package exeptions.math;

public class DivideByZeroException extends MathException {

	public DivideByZeroException() {

	}

	public DivideByZeroException(String message) {
		super(message);

	}

	public DivideByZeroException(Throwable throwable) {
		super(throwable);

	}

	public DivideByZeroException(String message, Throwable throwable) {
		super(message, throwable);

	}

}
