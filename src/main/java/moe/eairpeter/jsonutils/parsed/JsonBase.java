package moe.eairpeter.jsonutils.parsed;

/**
 * Represents a JSON value.
 * @author EAirPeter
 */
public abstract class JsonBase {
	
	/**
	 * Get the Java {@code Object} represented by this JSON value.
	 * @return
	 */
	public abstract Object unwrap();
	
	/**
	 * Value types.
	 * @author EAirPeter
	 */
	public static enum Type {
		OBJECT,
		ARRAY,
		STRING,
		NUMBER,
		BOOL,
	}
	
	/**
	 * @return The type of this JSON value.
	 */
	public abstract Type getType();
	
}
