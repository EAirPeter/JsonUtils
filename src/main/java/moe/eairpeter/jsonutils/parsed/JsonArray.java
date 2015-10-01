package moe.eairpeter.jsonutils.parsed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class JsonArray extends JsonBase {

	public final ArrayList<JsonBase> data = new ArrayList<JsonBase>();
	
	public JsonArray() {
	}
	
	public JsonArray(JsonBase ...values) {
		for (JsonBase v : values)
			data.add(v);
	}
	
	public JsonArray(Collection<? extends JsonBase> c) {
		data.addAll(c);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append('[');
		boolean first = true;
		for (JsonBase v : data) {
			if (first)
				first = false;
			else
				sb.append(',');
			sb.append(v);
		}
		return sb.append(']').toString();
	}
	
	@Override
	public Type getType() {
		return Type.ARRAY;
	}

}
