package exeptions.math;

public class ZeroPowerZeroException extends MathException {

	public ZeroPowerZeroException() {

	}

	public ZeroPowerZeroException(String message) {
		super(message);

	}

	public ZeroPowerZeroException(Throwable throwable) {
		super(throwable);

	}

	public ZeroPowerZeroException(String message, Throwable throwable) {
		super(message, throwable);

	}

}
