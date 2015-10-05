package moe.eairpeter.jsonutils.parsed;

/**
 * Represents a JSON {@literal true} or {@literal false} value.
 * @author EAirPeter
 */
public class JsonBool extends JsonBase {

	/**
	 * The data.
	 */
	public boolean data;
	
	/**
	 * Construct a JSON {@code false} value.
	 */
	public JsonBool() {
		this(false);
	}
	
	/**
	 * Construct with given {@code boolean}.
	 * @param data_ The {@code boolean}.
	 */
	public JsonBool(boolean data_) {
		data = data_;
	}
	
	@Override
	public Boolean unwrap() {
		return data;
	}
	
	@Override
	public String toString() {
		return data ? "true" : "false";
	}
	
	@Override
	public Type getType() {
		return Type.BOOL;
	}

}
