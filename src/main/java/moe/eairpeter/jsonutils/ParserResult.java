package moe.eairpeter.jsonutils;

import java.util.Collections;
import java.util.List;

import moe.eairpeter.jsonutils.parsed.JsonBase;

public class ParserResult <JsonParsed extends JsonBase> {

	public ParserResult(JsonParsed result_, boolean succeeded_, List<ParserError> errors_) {
		result = result_;
		succeeded = succeeded_;
		errors = errors_ == null ? null : Collections.unmodifiableList(errors_);
	}
	
	public final JsonParsed result;
	public final boolean succeeded;
	public final List<ParserError> errors;
	
}
