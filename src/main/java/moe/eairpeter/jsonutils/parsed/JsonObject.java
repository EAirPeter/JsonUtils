package moe.eairpeter.jsonutils.parsed;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JsonObject extends JsonBase {

	public final HashMap<JsonString, JsonBase> data = new HashMap<JsonString, JsonBase>();
	
	public JsonObject() {
	}
	
	public JsonObject(Map<? extends JsonString, ? extends JsonBase> m) {
		data.putAll(m);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('{');
		boolean first = true;
		for (Entry<JsonString, JsonBase> p : data.entrySet()) {
			if (first)
				first = false;
			else
				sb.append(',');
			sb.append(p.getKey().toString()).append(':').append(p.getValue().toString());
		}
		return sb.append('}').toString();
	}
	
	@Override
	public Type getType() {
		return Type.OBJECT;
	}

}
