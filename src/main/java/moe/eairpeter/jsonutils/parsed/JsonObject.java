package moe.eairpeter.jsonutils.parsed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.Map.Entry;

public class JsonObject extends JsonBase {

	public final ArrayList<Entry<JsonString, JsonBase>> data = new ArrayList<Entry<JsonString, JsonBase>>();
	public final HashSet<String> set = new HashSet<String>();
	
	public JsonObject() {
	}
	
	public boolean put(JsonString string, JsonBase value) {
		if (set.contains(string.data))
			return false;
		set.add(string.data);
		data.add(new SimpleEntry<JsonString, JsonBase>(string, value));
		return true;
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
			sb.append(p.getKey()).append(':').append(p.getValue());
		}
		return sb.append('}').toString();
	}
	
	@Override
	public Type getType() {
		return Type.OBJECT;
	}

}
