package moe.eairpeter.jsonutils.parsed;

public class JsonNumber extends JsonBase {

	public double data = 0;
	
	public JsonNumber() {
	}
	
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
