package moe.eairpeter.jsonutils.parsed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import moe.eairpeter.jsonutils.JsonUtils;

public class JsonArray extends JsonBase {

	public final ArrayList<JsonBase> data = new ArrayList<JsonBase>();
	
	public JsonArray() {
	}
	
	public JsonArray(JsonBase ...values) {
		for (JsonBase v : values)
			data.add(v);
	}
	
	public JsonArray(Object ...values) {
		for (Object v : values)
			data.add(JsonUtils.toJson(v));
	}
	
	public void add(JsonBase value) {
		data.add(value);
	}

	public void add(Object value) {
		data.add(JsonUtils.toJson(value));
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
