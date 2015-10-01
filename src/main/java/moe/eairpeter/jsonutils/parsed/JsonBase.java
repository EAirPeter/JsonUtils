package moe.eairpeter.jsonutils.parsed;

public abstract class JsonBase {
	
	public abstract Object unwrap();
	
	public static enum Type {
		OBJECT,
		ARRAY,
		STRING,
		NUMBER,
		BOOL,
	}
	
	public abstract Type getType();
	
}
