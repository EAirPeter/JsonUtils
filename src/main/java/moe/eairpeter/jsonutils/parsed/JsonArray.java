package moe.eairpeter.jsonutils.parsed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import moe.eairpeter.jsonutils.JsonUtils;

/**
 * Represents a JSON array.
 * @author EAirPeter
 */
public class JsonArray extends JsonBase {

	/**
	 * The data.
	 */
	public final ArrayList<JsonBase> data = new ArrayList<JsonBase>();
	
	/**
	 * Construct an empty JSON array.
	 */
	public JsonArray() {
	}
	
	/**
	 * Construct a JSON array consisting given JSON values
	 * @param values The JSON values.
	 */
	public JsonArray(JsonBase ...values) {
		for (JsonBase v : values)
			data.add(v);
	}
	
	/**
	 * Construct a JSON array consisting JSON values translated from given objects.
	 * @param values The objects.
	 */
	public JsonArray(Object ...values) {
		for (Object v : values)
			data.add(JsonUtils.toJson(v));
	}
	
	/**
	 * Add a JSON value.
	 * @param value The JSON value.
	 */
	public void add(JsonBase value) {
		data.add(value);
	}

	/**
	 * Add a JSON value translated from given object.
	 * @param value The object.
	 */
	public void add(Object value) {
		data.add(JsonUtils.toJson(value));
	}
	
	
	@Override
	public Object[] unwrap() {
		Object[] res = new Object[data.size()];
		int i = 0;
		for (JsonBase v : data)
			res[i++] = v.unwrap();
		return res;
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
