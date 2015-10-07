package moe.eairpeter.jsonutils;

import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import moe.eairpeter.jsonutils.parsed.JsonArray;
import moe.eairpeter.jsonutils.parsed.JsonBase;
import moe.eairpeter.jsonutils.parsed.JsonBool;
import moe.eairpeter.jsonutils.parsed.JsonNumber;
import moe.eairpeter.jsonutils.parsed.JsonObject;
import moe.eairpeter.jsonutils.parsed.JsonString;

/**
 * Utility to translate a JSON {@code Reader} to JSON values.
 * @author EAirPeter
 */
public final class JsonParser {

	private final ArrayList<ParserError> errors = new ArrayList<ParserError>();
	private final Reader in;
	private int cchr = -1;
	private int clne = 1;
	private int ccol = 0;

	/**
	 * Construct a JsonParser which will get all input from given reader.<br>
	 * The constructor will call {@code reader.read()}.
	 * @param reader The reader
	 */
	public JsonParser(Reader reader) {
		in = reader;
		xxNext();
	}
	
	/**
	 * @return The last operation is successful or it's just after constructed.
	 */
	public boolean succeeded() {
		return errors.isEmpty();
	}
	
	/**
	 * Get the errors generated by last operation.
	 * @return errors, or {@literal null} for no errors or just after constructed.
	 */
	public List<ParserError> errors() {
		return errors.isEmpty() ? null : Collections.unmodifiableList(errors);
	}
	
	/**
	 * @return true if the last {@code reader.read()} returns {@literal -1}.
	 */
	public boolean eof() {
		return cchr == -1;
	}
	
	/**
	 * Parse a JSON value.
	 * @see ParserResult
	 * @param construct {@literal true} for construct new objects, {@literal false} for just do syntax check.
	 * @return The result.
	 */
	public ParserResult<JsonBase> parseValue(boolean construct) {
		xPrepare();
		JsonBase res = xParseValue(construct);
		return errors.isEmpty() ? new ParserResult<JsonBase>(res, true, null) : new ParserResult<JsonBase>(null, false, errors);
	}
	
	/**
	 * Parse a JSON object.
	 * @see ParserResult
	 * @param construct {@literal true} for construct new objects, {@literal false} for just do syntax check.
	 * @return The result.
	 */
	public ParserResult<JsonObject> parseObject(boolean construct) {
		xPrepare();
		JsonObject res = xParseObject(construct);
		return errors.isEmpty() ? new ParserResult<JsonObject>(res, true, null) : new ParserResult<JsonObject>(null, false, errors);
	}
	
	/**
	 * Parse a JSON array.
	 * @see ParserResult
	 * @param construct {@literal true} for construct new objects, {@literal false} for just do syntax check.
	 * @return The result.
	 */
	public ParserResult<JsonArray> parseArray(boolean construct) {
		xPrepare();
		JsonArray res = xParseArray(construct);
		return errors.isEmpty() ? new ParserResult<JsonArray>(res, true, null) : new ParserResult<JsonArray>(null, false, errors);
	}
	
	/**
	 * Parse a JSON string.
	 * @see ParserResult
	 * @param construct {@literal true} for construct new objects, {@literal false} for just do syntax check.
	 * @return The result.
	 */
	public ParserResult<JsonString> parseString(boolean construct) {
		xPrepare();
		JsonString res = xParseString(construct);
		return errors.isEmpty() ? new ParserResult<JsonString>(res, true, null) : new ParserResult<JsonString>(null, false, errors);
	}
	
	/**
	 * Parse a JSON number.
	 * @see ParserResult
	 * @param construct {@literal true} for construct new objects, {@literal false} for just do syntax check.
	 * @return The result.
	 */
	public ParserResult<JsonNumber> parseNumber(boolean construct) {
		xPrepare();
		JsonNumber res = xParseNumber(construct);
		return errors.isEmpty() ? new ParserResult<JsonNumber>(res, true, null) : new ParserResult<JsonNumber>(null, false, errors);
	}
	
	/**
	 * Parse a JSON {@literal true} or {@literal false} }value.
	 * @see ParserResult
	 * @param construct {@literal true} for construct new objects, {@literal false} for just do syntax check.
	 * @return The result.
	 */
	public ParserResult<JsonBool> parseBool(boolean construct) {
		xPrepare();
		JsonBool res = xParseBool(construct);
		return errors.isEmpty() ? new ParserResult<JsonBool>(res, true, null) : new ParserResult<JsonBool>(null, false, errors);
	}
	
	/**
	 * Parse a JSON {@literal null} value.
	 * @see ParserResult
	 * @param construct {@literal true} for construct new objects, {@literal false} for just do syntax check.
	 * @return The result.
	 */
	public ParserResult<JsonBase> parseNull(boolean construct) {
		xPrepare();
		xParseNull(construct);
		return errors.isEmpty() ? new ParserResult<JsonBase>(null, true, null) : new ParserResult<JsonBase>(null, false, errors);
	}
	
	/**
	 * Expect an {@literal EOF}
	 * @see ParserResult
	 * @return The result.
	 */
	public <JsonParsed extends JsonBase> ParserResult<JsonParsed> parseEOF() {
		xPrepare();
		return xxExpect(-1) ? new ParserResult<JsonParsed>(null, true, null) : new ParserResult<JsonParsed>(null, false, errors);
	}
	
	private JsonBase xParseValue(boolean construct) {
		switch (cchr) {
		case '{':
			return xParseObject(construct);
		case '[':
			return xParseArray(construct);
		case '\"':
			return xParseString(construct);
		case 't':
		case 'f':
			return xParseBool(construct);
		case 'n':
			return xParseNull(construct);
		case '-':
			return xParseNumber(construct);
		default:
			if (Character.isDigit(cchr))
				return xParseNumber(construct);
		}
		eUnknown("value");
		return null;
	}
	
	private JsonObject xParseObject(boolean construct) {
		if (!xxExpect('{'))
			throw new RuntimeException("Failed to parse");
		JsonObject res = construct ? new JsonObject() : null;
		while (xxNext() != '}') {
			JsonString string = xParseString(construct);
			xxExpect(':');
			xxNext();
			JsonBase value = xParseValue(construct);
			if (construct && !res.put(string, value))
				eRemapping(string.data);
			if (cchr != ',')
				break;
		}
		xxExpect('}');
		xxNext();
		return res;
	}
	
	private JsonArray xParseArray(boolean construct) {
		if (!xxExpect('['))
			throw new RuntimeException("Failed to parse");
		JsonArray res = construct ? new JsonArray() : null;
		while (xxNext() != ']') {
			JsonBase value = xParseValue(construct);
			if (construct)
				res.data.add(value);
			if (cchr != ',')
				break;
		}
		xxExpect(']');
		xxNext();
		return res;
		
	}
	
	private JsonString xParseString(boolean construct) {
		if (!xxExpect('\"'))
			throw new RuntimeException("Failed to parse");
		JsonString res = construct ? new JsonString() : null;
		if (construct) {
			StringBuilder sb = new StringBuilder();
			while (xxRead() != '\"') {
				if (cchr == '\\')
					switch (xxRead()) {
					case '\"':
						sb.append("\"");
						break;
					case '\\':
						sb.append("\\");
						break;
					case '/':
						sb.append("/");
						break;
					case 'b':
						sb.append("\b");
						break;
					case 'f':
						sb.append("\f");
						break;
					case 'n':
						sb.append("\n");
						break;
					case 'r':
						sb.append("\r");
						break;
					case 't':
						sb.append("\t");
						break;
					case 'u':
						int u = xxReadHex();
						u = (u << 4) | xxReadHex();
						u = (u << 4) | xxReadHex();
						u = (u << 4) | xxReadHex();
						sb.append(byCP(u));
						break;
					default:
						eUnknown("escape sequence");
					}
				else
					sb.append(byCP(cchr));
			}
			res.data = sb.toString();
		}
		else {
			while (xxRead() != '\"')
				if (cchr == '\\')
					switch (xxRead()) {
					case '\"':
					case '\\':
					case '/':
					case 'b':
					case 'f':
					case 'n':
					case 'r':
					case 't':
						break;
					case 'u':
						int u = xxReadHex();
						u = (u << 4) | xxReadHex();
						u = (u << 4) | xxReadHex();
						u = (u << 4) | xxReadHex();
						break;
					default:
						eUnknown("escape sequence");
					}
		}
		xxExpect('\"');
		xxNext();
		return res;
	}
	
	private JsonNumber xParseNumber(boolean construct) {
		if (construct) {
			StringBuilder sb = new StringBuilder();
			if (cchr == '-') {
				sb.append(byCP(cchr));
				xxNext();
			}
			if (Character.isDigit(cchr)) {
				sb.append(byCP(cchr));
				if (cchr == '0')
					xxNext();
				else
					while (Character.isDigit(xxNext()))
						sb.append(byCP(cchr));
				if (cchr == '.') {
					sb.append(byCP(cchr));
					while (Character.isDigit(xxNext()))
						sb.append(byCP(cchr));
				}
				if (cchr == 'E' || cchr == 'e') {
					sb.append(byCP(cchr));
					xxNext();
					if (cchr == '+' || cchr == '-') {
						sb.append(byCP(cchr));
						xxNext();
					}
					while (Character.isDigit(xxNext()))
						sb.append(byCP(cchr));
				}
			}
			else
				eUnknown("number");
			return new JsonNumber(Double.valueOf(sb.toString()));
		}
		if (cchr == '-')
			xxNext();
		if (Character.isDigit(cchr)) {
			if (cchr != '0')
				while (Character.isDigit(xxNext()));
			if (cchr == '.')
				while (Character.isDigit(xxNext()));
			if (cchr == 'E' || cchr == 'e') {
				xxNext();
				if (cchr == '+' || cchr == '-')
					xxNext();
				while (Character.isDigit(xxNext()));
			}
		}
		else
			eUnknown("number");
		return null;
	}
	
	private JsonBool xParseBool(boolean construct) {
		switch (cchr) {
		case 't':
			if (xParseMatch("true") && xParseToEnd())
				return construct ? new JsonBool(true) : null;
		case 'f':
			if (xParseMatch("false") && xParseToEnd())
				return construct ? new JsonBool(false) : null;
		default:
			break;
		}
		eUnknown("value");
		xParseToEnd();
		return null;
	}
	
	private JsonBase xParseNull(boolean construct) {
		if (xParseMatch("null") && xParseToEnd())
			return null;
		eUnknown("value");
		return null;
	}
	
	private boolean xParseMatch(String string) {
		for (int i = 0; i < string.length(); ++i, xxNext())
			if (string.charAt(i) != cchr)
				return false;
		return true;
	}
	
	private boolean xParseToEnd() {
		boolean res = true;
		while (Character.isJavaIdentifierPart(cchr)) {
			xxNext();
			res = false;
		}
		return res;
	}
	
	private void xPrepare() {
		errors.clear();
		//xxNext();
	}
	
	private boolean xxExpect(int chr) {
		if (cchr == chr)
			return true;
		eExpected(chr);
		return false;
	}
	
	private int xxNext() {
		try {
			do {
				cchr = in.read();
				if (cchr == '\n') {
					++clne;
					ccol = 0;
				}
				else
					++ccol;
			}
			while (JsonUtils.isWhitespace(cchr));
		}
		catch (IOException e) {
			raiseUnexpected(e);
		}
		return cchr;
	}
	
	private int xxReadHex() {
		xxRead();
		if (cchr >= '0' && cchr <= '9')
			return cchr & 0x10;
		if (cchr >= 'A' && cchr <= 'Z')
			return cchr - 'A';
		if (cchr >= 'a' && cchr <= 'z')
			return cchr - 'a';
		eUnknown("hexadecimal digit");
		return 0;
	}
	
	private int xxRead() {
		try {
			cchr = in.read();
			if (cchr == '\n') {
				++clne;
				ccol = 0;
			}
			else
				++ccol;
		}
		catch (IOException e) {
			raiseUnexpected(e);
		}
		return cchr;
	}

	private void error(String message) {
		errors.add(new ParserError(clne, ccol, message));
	}
	
	private void eUnexpected() {
		error("Unexpected \'" + byCP(cchr) + "\'");
	}
	
	private void eExpected(char chr) {
		error("\'" + chr + "\' expected, but \'" + byCP(cchr) + "\' found");
	}
	
	private void eExpected(int chr) {
		error("\'" + byCP(chr) + "\' expected, but \'" + byCP(cchr) + "\' found");
	}
	
	private void eRemapping(String key) {
		error("Remapping of \'" + key + "\' found");
	}
	
	private void eUnknown(String what) {
		error("Unknown " + what + " found");
	}
	
	static String byCP(int codepoint) {
		if (codepoint == -1)
			return "(EOF)";
		else
			return String.valueOf(Character.toChars(codepoint));
	}
	
	static void raiseUnexpected(Throwable cause) {
		raise("Caught unexpected " + cause.getClass().getSimpleName(), cause);
	}
	
	static void raise(String message, Throwable cause) {
		throw new RuntimeException("Failed to parse, message :" + message, cause);
	}
	
}
