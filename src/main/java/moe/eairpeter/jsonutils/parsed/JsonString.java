package moe.eairpeter.jsonutils.parsed;

/**
 * Represents a JSON string.
 * @author EAirPeter
 */
public class JsonString extends JsonBase {

	/**
	 * The data.<br>
	 * It's shall <strong>NEVER</strong> be {@literal null}.
	 */
	public String data = new String();
	
	/**
	 * Construct an JSON string "".
	 */
	public JsonString() {
	}
	
	/**
	 * Construct an JSON string with given {@code String}
	 * @param data_
	 */
	public JsonString(String data_) {
		data = data_;
	}
	
	@Override
	public String unwrap() {
		return data;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('\"');
		for (int i = 0; i < data.length(); ++i)
			switch (data.charAt(i)) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(data.charAt(i));
				break;
			}
		return sb.append('\"').toString();
	}
	
	@Override
	public Type getType() {
		return Type.STRING;
	}

}
