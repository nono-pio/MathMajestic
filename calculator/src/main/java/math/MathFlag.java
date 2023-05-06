package math;

public enum MathFlag {
	
	
	// <------------ Set of number ----------->
	InfinitySet,
	Positive,
	Negative,
	
	// <------------ Type of Number ---------->
	NonZero,
	Zero,
	Infinity,
	NegativeInfivity,
	
	// <------- Type of child element -------->
	Term, // Term + Term
	Factor, // factor * factor
	Denominator, // 4/denominator
	BasePower, //base^2
	BaseLogarithm, //log_baselog(x)
	Exponent,  // 2^Exponent
	AntiLogarithm, // log( AntiLogarithm )

}
