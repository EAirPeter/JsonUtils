package moe.eairpeter.jsonutils;

import java.util.Collections;
import java.util.List;

import moe.eairpeter.jsonutils.parsed.JsonBase;

/**
 * The result of a parsing operation.
 * @author EAirPeter
 *
 * @param <JsonParsed> The result type or any when this object represents a JSON {@literal null} value.
 */
public final class ParserResult <JsonParsed extends JsonBase> {

	ParserResult(JsonParsed result_, boolean succeeded_, List<ParserError> errors_) {
		result = result_;
		succeeded = succeeded_;
		errors = errors_ == null ? null : Collections.unmodifiableList(errors_);
	}
	
	/**
	 * The result.
	 */
	public final JsonParsed result;
	/**
	 * True if the operation is succeeded.
	 */
	public final boolean succeeded;
	/**
	 * Errors encountered when parsing or {@literal null} for no error.
	 */
	public final List<ParserError> errors;
	
}
