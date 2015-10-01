package moe.eairpeter.jsonutils.parsed;

public abstract class JsonBase {
	
	public static enum Type {
		OBJECT,
		ARRAY,
		STRING,
		NUMBER,
		BOOL,
	}
	
	public abstract Type getType();
	
}
