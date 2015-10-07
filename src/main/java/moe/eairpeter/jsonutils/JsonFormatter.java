package moe.eairpeter.jsonutils;

import java.io.IOException;
import java.io.Reader;
import java.util.Map.Entry;
import java.util.Stack;

import moe.eairpeter.jsonutils.parsed.JsonArray;
import moe.eairpeter.jsonutils.parsed.JsonBase;
import moe.eairpeter.jsonutils.parsed.JsonBool;
import moe.eairpeter.jsonutils.parsed.JsonNumber;
import moe.eairpeter.jsonutils.parsed.JsonObject;
import moe.eairpeter.jsonutils.parsed.JsonString;

/**
 * Utility to get the formatted string representation of a JSON value.
 * @author EAirPeter
 */
public final class JsonFormatter {
	
	/**
	 * Insert a new line after '{'
	 */
	public static final int LF_LBRACE	= 0x00000001;
	
	/**
	 * Insert a new line before '}'
	 */
	public static final int LF_RBRACE	= 0x00000002;
	
	/**
	 * Insert a new line between an empty '{}'
	 */
	public static final int LF_EBRACE	= 0x00000004;
	
	/**
	 * Insert a new line after '['
	 */
	public static final int LF_LSQUARE	= 0x00000008;
	
	/**
	 * Insert a new line before ']'
	 */
	public static final int LF_RSQUARE	= 0x00000010;
	
	/**
	 * Insert a new line between an empty '[]'
	 */
	public static final int LF_ESQUARE	= 0x00000020;
	
	/**
	 * Insert a new line before ':'
	 */
	public static final int LF_BCOLON	= 0x00000040;
	
	/**
	 * Insert a new line after ':'
	 */
	public static final int LF_ACOLON	= 0x00000080;
	
	/**
	 * Insert a new line before ',' in object
	 */
	public static final int LF_BOCOMMA	= 0x00000100;
	
	/**
	 * Insert a new line after ',' in object
	 */
	public static final int LF_AOCOMMA	= 0x00000200;
	
	/**
	 * Insert a new line before ',' in array
	 */
	public static final int LF_BACOMMA	= 0x00000400;
	
	/**
	 * Insert a new line after ',' in array
	 */
	public static final int LF_AACOMMA	= 0x00000800;
	
	/**
	 * Insert a new space after '{'
	 */
	public static final int WS_LBRACE	= 0x00001000;
	
	/**
	 * Insert a new space before '}'
	 */
	public static final int WS_RBRACE	= 0x00002000;
	
	/**
	 * Insert a new space between an empty '{}'
	 */
	public static final int WS_EBRACE	= 0x00004000;
	
	/**
	 * Insert a new space after '['
	 */
	public static final int WS_LSQUARE	= 0x00008000;
	
	/**
	 * Insert a new space before ']'
	 */
	public static final int WS_RSQUARE	= 0x00010000;
	
	/**
	 * Insert a new space between an empty '[]'
	 */
	public static final int WS_ESQUARE	= 0x00020000;
	
	/**
	 * Insert a new space before ':'
	 */
	public static final int WS_BCOLON	= 0x00040000;
	
	/**
	 * Insert a new space after ':'
	 */
	public static final int WS_ACOLON	= 0x00080000;
	
	/**
	 * Insert a new space before ',' in object
	 */
	public static final int WS_BOCOMMA	= 0x00100000;
	
	/**
	 * Insert a new space after ',' in object
	 */
	public static final int WS_AOCOMMA	= 0x00200000;
	
	/**
	 * Insert a new space before ',' in array
	 */
	public static final int WS_BACOMMA	= 0x00400000;
	
	/**
	 * Insert a new space after ',' in array
	 */
	public static final int WS_AACOMMA	= 0x00800000;

	/**
	 * Default format
	 */
	public static final int JF_DEFAULT	= LF_LBRACE | LF_RBRACE | LF_AOCOMMA | WS_LSQUARE | WS_RSQUARE | WS_ACOLON | WS_AACOMMA;
	
	/**
	 * Default indent sequence
	 */
	public static final String JI_DEFAULT = "    ";
	
	private int format = 0;
	private String indent = null;
	
	/**
	 * Construct a {@code JsonFormatter} with default format and default indent sequence.
	 */
	public JsonFormatter() {
		this(JF_DEFAULT, JI_DEFAULT);
	}
	
	/**
	 * Construct a {@code JsonFormatter} with given format and default indent sequence.
	 * @param format_ The format.
	 */
	public JsonFormatter(int format_) {
		this(format_, JI_DEFAULT);
	}
	
	/**
	 * Construct a {@code JsonFormatter} with default format and given indent sequence.
	 * @param indent_ The indent sequence, {@literal null} for default.
	 */
	public JsonFormatter(String indent_) {
		this(JF_DEFAULT, indent_);
	}
	
	/**
	 * Construct a {@code JsonFormatter} with default format and given indent sequence.
	 * @param format_ The format.
	 * @param indent_ The indent sequence, {@literal null} for default.
	 */
	public JsonFormatter(int format_, String indent_) {
		format = format_;
		if (indent_ == null)
			indent = JI_DEFAULT;
		else
			indent = indent_;
	}
	
	/**
	 * Disable a format option.
	 * @param option The format option.
	 * @return This object.
	 */
	public JsonFormatter disableFormat(int option) {
		format &= ~option;
		return this;
	}
	
	/**
	 * Enable a format option.
	 * @param option The format option.
	 * @return This object.
	 */
	public JsonFormatter enableFormat(int option) {
		format |= option;
		return this;
	}
	
	/**
	 * Toggle a format option.
	 * @param option The format option.
	 * @return This object.
	 */
	public JsonFormatter toggleFormat(int option) {
		format ^= option;
		return this;
	}
	
	/**
	 * Set the format.
	 * @param format_ The new format.
	 * @return This object.
	 */
	public JsonFormatter setFormat(int format_) {
		format = format_;
		return this;
	}
	
	/**
	 * @return The current format.
	 */
	public int getFormat() {
		return format;
	}
	
	/**
	 * Set the indent sequence.
	 * @param indent_ The new indent sequence.
	 * @return This object.
	 */
	public JsonFormatter setIndent(String indent_) {
		indent = indent_;
		return this;
	}
	
	/**
	 * @return The current indent sequence.
	 */
	public String getIndent() {
		return indent;
	}
	
	/**
	 * Construct a formatted string according to given JSON {@code Reader}.<br>
	 * Assuming the input is valid.<br>
	 * Invalid input may cause {@literal null} or unexpected result.
	 * @param reader The reader.
	 * @return The formatted string.
	 */
	public String formatUnsafe(Reader reader) {
		StringBuilder sb = new StringBuilder();
		Stack<Character> sc = new Stack<Character>();
		Stack<String> si = new Stack<String>();
		si.push("\n");
		int cu = xNext(reader);
		while (cu != -1) {
			switch (cu) {
			case '{':
				sb.append('{');
				cu = xNext(reader);
				if (cu == '}') {
					if (enabled(LF_EBRACE))
						sb.append(si.peek());
					else if (enabled(WS_EBRACE))
						sb.append(' ');
					sb.append('}');
					cu = xNext(reader);
				}
				else {
					sc.push('{');
					si.push(si.peek() + indent);
					if (enabled(LF_LBRACE))
						sb.append(si.peek());
					else if (enabled(WS_LBRACE))
						sb.append(' ');
				}
				break;
			case '}':
				if (sc.empty() || sc.peek() != '{')
					return null;
				sc.pop();
				si.pop();
				if (enabled(LF_RBRACE))
					sb.append(si.peek());
				else if (enabled(WS_RBRACE))
					sb.append(' ');
				sb.append('}');
				break;
			case '[':
				sb.append('[');
				cu = xNext(reader);
				if (cu == ']') {
					if (enabled(LF_ESQUARE))
						sb.append(si.peek());
					else if (enabled(WS_ESQUARE))
						sb.append(' ');
					sb.append(']');
					cu = xNext(reader);
				}
				else {
					sc.push('[');
					si.push(si.peek() + indent);
					if (enabled(LF_LSQUARE))
						sb.append(si.peek());
					else if (enabled(WS_LSQUARE))
						sb.append(' ');
				}
				break;
			case ']':
				if (sc.empty() || sc.peek() != ']')
					return null;
				sc.pop();
				si.pop();
				if (enabled(LF_RSQUARE))
					sb.append(si.peek());
				else if (enabled(WS_RSQUARE))
					sb.append(' ');
				sb.append(']');
				break;
			case ':':
				if (sc.empty() || sc.peek() != '{')
					return null;
				if (enabled(LF_BCOLON))
					sb.append(si.peek());
				else if (enabled(WS_BCOLON))
					sb.append(' ');
				sb.append(':');
				if (enabled(LF_ACOLON))
					sb.append(si.peek());
				else if (enabled(WS_ACOLON))
					sb.append(' ');
				cu = xNext(reader);
				break;
			case ',':
				if (sc.empty())
					return null;
				switch (sc.peek()) {
				case '{':
					if (enabled(LF_BOCOMMA))
						sb.append(si.peek());
					else if (enabled(WS_BOCOMMA))
						sb.append(' ');
					sb.append(',');
					if (enabled(LF_AOCOMMA))
						sb.append(si.peek());
					else if (enabled(WS_AOCOMMA))
						sb.append(' ');
					break;
				case '[':
					if (enabled(LF_BACOMMA))
						sb.append(si.peek());
					else if (enabled(WS_BACOMMA))
						sb.append(' ');
					sb.append(',');
					if (enabled(LF_AACOMMA))
						sb.append(si.peek());
					else if (enabled(WS_AACOMMA))
						sb.append(' ');
					break;
				default:
					return null;
				}
				cu = xNext(reader);
				break;
			case '\"':
				while ((cu = xRead(reader)) != '\"') {
					if (cu == -1)
						return null;
					sb.append(JsonParser.byCP(cu));
					if (cu == '\\')
						sb.append(JsonParser.byCP(xRead(reader)));
				}
				cu = xNext(reader);
				break;
			default:
				while (cu != -1 && !JsonUtils.isWhitespace(cu) && !JsonUtils.isStructural(cu)) {
					sb.append(cu);
					cu = xRead(reader);
				}
				if (JsonUtils.isWhitespace(cu))
					cu = xNext(reader);
				break;
			}
		}
		return sb.toString();
	}
	
	private static int xNext(Reader reader) {
		int cu = -1;
		try {
			do {
				cu = reader.read();
			}
			while (JsonUtils.isWhitespace(cu));
		}
		catch (IOException e) {
			cu = -1;
		}
		return cu;
	}
	
	private static int xRead(Reader reader) {
		int cu = -1;
		try {
			cu = reader.read();
		}
		catch (IOException e) {
			cu = -1;
		}
		return cu;
	}
	
	/**
	 * Get the formatted string representation of given JSON value according to the format and indent sequence.
	 * @param json The JSON value to be formatted.
	 * @return The formatted string.
	 */
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
