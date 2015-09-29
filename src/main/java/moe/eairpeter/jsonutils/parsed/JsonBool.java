package moe.eairpeter.jsonutils.parsed;

public class JsonBool extends JsonBase {

	private boolean data = false;
	
	public JsonBool() {
	}
	
	public JsonBool(boolean data_) {
		data = data_;
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
