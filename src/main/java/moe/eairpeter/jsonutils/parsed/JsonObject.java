package moe.eairpeter.jsonutils.parsed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class JsonObject extends JsonBase {

	public final ArrayList<Entry<JsonString, JsonBase>> data = new ArrayList<Entry<JsonString, JsonBase>>();
	public final HashSet<String> set = new HashSet<String>();
	
	public JsonObject() {
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('{');
		boolean first = true;
		for (Entry<JsonString, JsonBase> p : data) {
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
