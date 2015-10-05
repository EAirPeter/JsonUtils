package moe.eairpeter.jsonutils.parsed;

/**
 * Represents a JSON number.
 * @author EAirPeter
 */
public class JsonNumber extends JsonBase {

	/**
	 * The data.
	 */
	public double data;
	
	/**
	 * Construct a JSON number {@literal 0}.
	 */
	public JsonNumber() {
		this(0);
	}
	
	/**
	 * Construct a JSON number with given {@code double}.
	 * @param data_ The {@code double}.
	 */
	public JsonNumber(double data_) {
		data = data_;
	}
	
	@Override
	public Double unwrap() {
		return data;
	}
	
	@Override
	public String toString() {
		return data == (int) data ? String.valueOf((int) data) : String.valueOf(data);
	}
	
	@Override
	public Type getType() {
		return Type.NUMBER;
	}

}
