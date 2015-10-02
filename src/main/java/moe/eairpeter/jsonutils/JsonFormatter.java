package moe.eairpeter.jsonutils;

import java.util.Map.Entry;

import moe.eairpeter.jsonutils.parsed.JsonArray;
import moe.eairpeter.jsonutils.parsed.JsonBase;
import moe.eairpeter.jsonutils.parsed.JsonBool;
import moe.eairpeter.jsonutils.parsed.JsonNumber;
import moe.eairpeter.jsonutils.parsed.JsonObject;
import moe.eairpeter.jsonutils.parsed.JsonString;

public class JsonFormatter {
	
	//Insert a new line after '{'
	public static final int LF_LBRACE	= 0x00000001;
	
	//Insert a new line before '}'
	public static final int LF_RBRACE	= 0x00000002;
	
	//Insert a new line between an empty '{}'
	public static final int LF_EBRACE	= 0x00000004;
	
	//Insert a new line after '['
	public static final int LF_LSQUARE	= 0x00000008;
	
	//Insert a new line before ']'
	public static final int LF_RSQUARE	= 0x00000010;
	
	//Insert a new line between an empty '[]'
	public static final int LF_ESQUARE	= 0x00000020;
	
	//Insert a new line before ':'
	public static final int LF_BCOLON	= 0x00000040;
	
	//Insert a new line after ':'
	public static final int LF_ACOLON	= 0x00000080;
	
	//Insert a new line before ',' in object
	public static final int LF_BOCOMMA	= 0x00000100;
	
	//Insert a new line after ',' in object
	public static final int LF_AOCOMMA	= 0x00000200;
	
	//Insert a new line before ',' in array
	public static final int LF_BACOMMA	= 0x00000400;
	
	//Insert a new line after ',' in array
	public static final int LF_AACOMMA	= 0x00000800;
	
	//Insert a new space after '{'
	public static final int WS_LBRACE	= 0x00001000;
	
	//Insert a new space before '}'
	public static final int WS_RBRACE	= 0x00002000;
	
	//Insert a new space between an empty '{}'
	public static final int WS_EBRACE	= 0x00004000;
	
	//Insert a new space after '['
	public static final int WS_LSQUARE	= 0x00008000;
	
	//Insert a new space before ']'
	public static final int WS_RSQUARE	= 0x00010000;
	
	//Insert a new space between an empty '[]'
	public static final int WS_ESQUARE	= 0x00020000;
	
	//Insert a new space before ':'
	public static final int WS_BCOLON	= 0x00040000;
	
	//Insert a new space after ':'
	public static final int WS_ACOLON	= 0x00080000;
	
	//Insert a new space before ',' in object
	public static final int WS_BOCOMMA	= 0x00100000;
	
	//Insert a new space after ',' in object
	public static final int WS_AOCOMMA	= 0x00200000;
	
	//Insert a new space before ',' in array
	public static final int WS_BACOMMA	= 0x00400000;
	
	//Insert a new space after ',' in array
	public static final int WS_AACOMMA	= 0x00800000;

	//Default format
	public static final int JF_DEFAULT	= LF_LBRACE | LF_RBRACE | LF_AOCOMMA | WS_LSQUARE | WS_RSQUARE | WS_BCOLON | WS_ACOLON | WS_AACOMMA;
	
	private int format = 0;
	private String indent = null;
	
	public JsonFormatter() {
		this("\t");
	}
	
	public JsonFormatter(String indent_) {
		this(JF_DEFAULT, indent_);
	}
	
	public JsonFormatter(int format_, String indent_) {
		format = format_;
		if (indent_ == null)
			indent = "\t";
		else
			indent = indent_;
	}
	
	public JsonFormatter disableFormat(int option) {
		format &= ~option;
		return this;
	}
	
	public JsonFormatter enableFormat(int option) {
		format |= option;
		return this;
	}
	
	public JsonFormatter toggleFormat(int option) {
		format ^= option;
		return this;
	}
	
	public JsonFormatter setFormat(int format_) {
		format = format_;
		return this;
	}
	
	public int getFormat() {
		return format;
	}
	
	public JsonFormatter setIndent(String indent_) {
		indent = indent_;
		return this;
	}
	
	public String getIndent() {
		return indent;
	}
	
	public String format(JsonBase json) {
		return xFormatValue(new StringBuilder(), json, "\n").toString();
	}
	
	private StringBuilder xFormatValue(StringBuilder sb, JsonBase json, String ind) {
		if (json == null)
			return sb.append("null");
		if (json instanceof JsonObject)
			return xFormatObject(sb, (JsonObject) json, ind);
		if (json instanceof JsonArray)
			return xFormatArray(sb, (JsonArray) json, ind);
		if (json instanceof JsonString)
			return xFormatString(sb, (JsonString) json, ind);
		if (json instanceof JsonNumber)
			return xFormatNumber(sb, (JsonNumber) json, ind);
		if (json instanceof JsonBool)
			return xFormatBool(sb, (JsonBool) json, ind);
		throw new IllegalArgumentException("Unsupported type: " + json.getClass().getName());
	}
	
	private StringBuilder xFormatObject(StringBuilder sb, JsonObject json, String ind) {
		sb.append('{');
		if (json.data.isEmpty()) {
			if (enabled(LF_EBRACE))
				sb.append(ind);
			else if (enabled(WS_EBRACE))
				sb.append(' ');
			return sb.append('}');
		}
		String sub = ind;
		if (enabled(LF_LBRACE))
			sb.append(sub = ind + indent);
		else if (enabled(WS_LBRACE))
			sb.append(' ');
		boolean first = true;
		for (Entry<JsonString, JsonBase> e : json.data) {
			if (first)
				first = false;
			else {
				if (enabled(LF_BOCOMMA))
					sb.append(sub);
				else if (enabled(WS_BOCOMMA))
					sb.append(' ');
				sb.append(',');
				if (enabled(LF_AOCOMMA))
					sb.append(sub);
				else if (enabled(WS_AOCOMMA))
					sb.append(' ');
			}
			xFormatString(sb, e.getKey(), ind);
			if (enabled(LF_BCOLON))
				sb.append(sub);
			else if (enabled(WS_BCOLON))
				sb.append(' ');
			sb.append(':');
			if (enabled(LF_ACOLON))
				sb.append(sub);
			else if (enabled(WS_ACOLON))
				sb.append(' ');
			xFormatValue(sb, e.getValue(), sub);
		}
		if (enabled(LF_RBRACE))
			sb.append(ind);
		else if (enabled(WS_RBRACE))
			sb.append(' ');
		return sb.append('}');
	}
	
	private StringBuilder xFormatArray(StringBuilder sb, JsonArray json, String ind) {
		sb.append('[');
		if (json.data.isEmpty()) {
			if (enabled(LF_ESQUARE))
				sb.append(ind);
			else if (enabled(WS_ESQUARE))
				sb.append(' ');
			return sb.append(']');
		}
		String sub = ind;
		if (enabled(LF_LSQUARE))
			sb.append(sub = ind + indent);
		else if (enabled(WS_LSQUARE))
			sb.append(' ');
		boolean first = true;
		for (JsonBase v : json.data) {
			if (first)
				first = false;
			else {
				if (enabled(LF_BACOMMA))
					sb.append(sub);
				else if (enabled(WS_BACOMMA))
					sb.append(' ');
				sb.append(',');
				if (enabled(LF_AACOMMA))
					sb.append(sub);
				else if (enabled(WS_AACOMMA))
					sb.append(' ');
			}
			xFormatValue(sb, v, sub);
		}
		if (enabled(LF_RSQUARE))
			sb.append(ind);
		else if (enabled(WS_RSQUARE))
			sb.append(' ');
		return sb.append(']');
	}
	
	private StringBuilder xFormatString(StringBuilder sb, JsonString json, String ind) {
		return sb.append(json.toString());
	}
	
	private StringBuilder xFormatNumber(StringBuilder sb, JsonNumber json, String ind) {
		return sb.append(json.toString());
	}
	
	private StringBuilder xFormatBool(StringBuilder sb, JsonBool json, String ind) {
		return sb.append(json.toString());
	}
	
	private boolean enabled(int option) {
		return (format & option) != 0;
	}
	
}
