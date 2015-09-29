package moe.eairpeter.jsonutils.parsed;

public class JsonNumber extends JsonBase {

	public double data = 0;
	
	public JsonNumber() {
	}
	
	public JsonNumber(double data_) {
		data = data_;
	}
	
	@Override
	public String toString() {
		return String.valueOf(data);
	}
	
	@Override
	public Type getType() {
		return Type.NUMBER;
	}

}
