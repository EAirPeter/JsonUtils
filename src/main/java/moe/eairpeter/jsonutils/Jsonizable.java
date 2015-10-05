package moe.eairpeter.jsonutils;

import moe.eairpeter.jsonutils.parsed.JsonBase;

/**
 * Interface to mark the object can be translated to a JSON value.
 * @author EAirPeter
 */
public interface Jsonizable {

	/**
	 * @return The JSON representation of this object.
	 */
	public JsonBase toJson();
	
}
