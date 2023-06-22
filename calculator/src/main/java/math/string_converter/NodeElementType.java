package math.string_converter;

public enum NodeElementType {

	Primary(0),

	Addition(5),
	Subtract(4),
	Product(3),
	Power(1),
	Division(2);

	final int priority;

	NodeElementType(int priority) {
		this.priority = priority;
	}

}
