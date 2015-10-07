package moe.eairpeter.jsonutils.parsed;

/**
 * Represents a JSON number.
 * @author EAirPeter
 */
public class JsonNumber extends JsonBase {

	/**
	 * The data.
	 */
	public String data;
	
	/**
	 * Construct a JSON number {@literal 0}.
	 */
	public JsonNumber() {
		this("0");
	}
	
	/**
	 * Construct a JSON number with given {@code byte}.
	 */
	public JsonNumber(byte data_) {
		data = Byte.toString(data_);
	}
	
	/**
	 * Construct a JSON number with given {@code double}.
	 */
	public JsonNumber(double data_) {
		data = Double.toString(data_);
	}
	
	/**
	 * Construct a JSON number with given {@code float}.
	 */
	public JsonNumber(float data_) {
		data = Float.toString(data_);
	}
	
	/**
	 * Construct a JSON number with given {@code int}.
	 */
	public JsonNumber(int data_) {
		data = Integer.toString(data_);
	}
	
	/**
	 * Construct a JSON number with given {@code long}.
	 */
	public JsonNumber(long data_) {
		data = Long.toString(data_);
	}
	
	/**
	 * Construct a JSON number with given {@code short}.
	 */
	public JsonNumber(short data_) {
		data = Short.toString(data_);
	}
	
	/**
	 * Construct a JSON number with given {@code String}.<br>
	 * This method does <strong>NOT</strong> validate the argument.
	 */
	public JsonNumber(String data_) {
		data = data_;
	}
	
	@Override
	public Double unwrap() {
		return Double.parseDouble(data);
	}
	
	@Override
	public String toString() {
		return data;
	}
	
	@Override
	public Type getType() {
		return Type.NUMBER;
	}

}
