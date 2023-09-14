package assertion;

public abstract class Argument {

	public static void isNotNull(final Object obj) {
		isTrue( obj != null, " Cannot be null " );
	}

	public static void isNotNull(final Object obj, String msg) {
		isTrue( obj != null, msg );
	}

	public static void isNull(final Object obj) {
		isTrue( obj == null, " Must be null " );
	}

	public static void isTrue(final boolean test) {
		isTrue(test, "Condition must be true");
	}

	public static void isTrue(final boolean test, final String msg) {
		if (test == true) {
			return;
		}
		throwException(msg);
	}

	public static void isNotEmpty(final String str) {
		isTrue( str != null, 
				"The string cannot be null not empty" );

		isTrue( str.trim().length() > 0, 
				"The string cannot be null not empty" );
	}

	public static void isNotEmpty(final String str, final String msg) {
		isTrue( str != null, msg );
		isTrue( str.trim().length() > 0, msg );
	}

	protected static void throwException(final String msg) {
		throw new IllegalArgumentException( msg );
	}

}
