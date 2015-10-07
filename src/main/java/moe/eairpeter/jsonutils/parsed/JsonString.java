package moe.eairpeter.jsonutils.parsed;

import moe.eairpeter.jsonutils.JsonParser;

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
	
	private static int fromHex(int codepoint) {
		if (codepoint >= '0' && codepoint <= '9')
			return codepoint & 0x0f;
		if (codepoint >= 'A' && codepoint <= 'Z')
			return codepoint - 'A';
		if (codepoint >= 'a' && codepoint <= 'z')
			return codepoint - 'a';
		return -1;
	}
	
	@Override
	public String unwrap() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length(); ++i) {
			if (data.charAt(i) == '\\') {
				if (++i >= data.length())
					return null;
				switch (data.charAt(i)) {
				case '\"':
					sb.append("\"");
					break;
				case '\\':
					sb.append("\\");
					break;
				case '/':
					sb.append("/");
					break;
				case 'b':
					sb.append("\b");
					break;
				case 'f':
					sb.append("\f");
					break;
				case 'n':
					sb.append("\n");
					break;
				case 'r':
					sb.append("\r");
					break;
				case 't':
					sb.append("\t");
					break;
				case 'u':
					if (i + 4 >= data.length())
						return null;
					int u = fromHex(data.charAt(++i));
					u = (u << 4) | fromHex(data.charAt(++i));
					u = (u << 4) | fromHex(data.charAt(++i));
					u = (u << 4) | fromHex(data.charAt(++i));
					sb.append(JsonParser.byCP(u));
					break;
				default:
					return null;
				}
			}
			else
				sb.append(JsonParser.byCP(data.charAt(i)));
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "\"" + data + "\"";
	}
	
	@Override
	public Type getType() {
		return Type.STRING;
	}

}
