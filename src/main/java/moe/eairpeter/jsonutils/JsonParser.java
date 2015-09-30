package moe.eairpeter.jsonutils;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import moe.eairpeter.jsonutils.parsed.JsonArray;
import moe.eairpeter.jsonutils.parsed.JsonBase;
import moe.eairpeter.jsonutils.parsed.JsonBool;
import moe.eairpeter.jsonutils.parsed.JsonNumber;
import moe.eairpeter.jsonutils.parsed.JsonObject;
import moe.eairpeter.jsonutils.parsed.JsonString;

public final class JsonParser {

	private final ArrayList<ParserError> errors = new ArrayList<ParserError>();
	private final Reader in;
	private int cchr = -1;
	private int clne = 1;
	private int ccol = 0;

	public JsonParser(Reader reader) {
		in = reader;
	}
	
	public JsonBase parseValue(boolean construct) {
		xPrepare();
		JsonBase res = xParseValue(construct);
		return errors.isEmpty() ? res : null;
	}
	
	public JsonObject parseObject(boolean construct) {
		xPrepare();
		JsonObject res = xParseObject(construct);
		return errors.isEmpty() ? res : null;
	}
	
	public JsonArray parseArray(boolean construct) {
		xPrepare();
		JsonArray res = xParseArray(construct);
		return errors.isEmpty() ? res : null;
	}
	
	public JsonString parseString(boolean construct) {
		xPrepare();
		JsonString res = xParseString(construct);
		return errors.isEmpty() ? res : null;
	}
	
	public JsonNumber parseNumber(boolean construct) {
		xPrepare();
		JsonNumber res = xParseNumber(construct);
		return errors.isEmpty() ? res : null;
	}
	
	public JsonBool parseBool(boolean construct) {
		xPrepare();
		JsonBool res = xParseBool(construct);
		return errors.isEmpty() ? res : null;
	}
	
	public boolean parseNull(boolean construct) {
		xPrepare();
		xParseNull(construct);
		return errors.isEmpty();
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
		xxExpect('{');
		JsonObject res = construct ? new JsonObject() : null;
		while (xxNext() != '}') {
			JsonString string = xParseString(construct);
			xxNext();
			xxExpect(':');
			xxNext();
			JsonBase value = xParseValue(construct);
			if (construct) {
				if (res.data.containsKey(string))
					eRemapping(string.data);
				else
					res.data.put(string, value);
			}
			if (cchr != ',')
				break;
		}
		xxExpect('}');
		xxNext();
		return res;
	}
	
	private JsonArray xParseArray(boolean construct) {
		xxExpect('[');
		JsonArray res = construct ? new JsonArray() : null;
		while (xxNext() != ']') {
			JsonBase value = xParseValue(construct);
			if (construct)
				res.data.add(value);
			if (cchr != ',')
				break;
		}
		xxExpect('}');
		xxNext();
		return res;
		
	}
	
	private JsonString xParseString(boolean construct) {
		xxExpect('\"');
		JsonString res = construct ? new JsonString() : null;
		if (construct) {
			StringBuilder sb = new StringBuilder();
			while (xxNext() != '\"') {
				if (cchr == '\\')
					switch (xxNext()) {
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
						int u = xxNextHex();
						u = (u << 4) | xxNextHex();
						u = (u << 4) | xxNextHex();
						u = (u << 4) | xxNextHex();
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
			while (xxNext() != '\"')
				if (cchr == '\\')
					switch (xxNext()) {
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
						int u = xxNextHex();
						u = (u << 4) | xxNextHex();
						u = (u << 4) | xxNextHex();
						u = (u << 4) | xxNextHex();
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
		//TODO implement
		return null;
	}
	
	private JsonBool xParseBool(boolean construct) {
		//TODO implement
		return null;
	}
	
	private JsonBase xParseNull(boolean construct) {
		//TODO implement
		return null;
	}
	
	private void xPrepare() {
		errors.clear();
		xxNext();
	}
	
	private void xxExpect(int chr) {
		if (cchr != chr)
			eExpected(chr);
	}
	
	private int xxNextHex() {
		xxNext();
		if (cchr >= '0' && cchr <= '9')
			return cchr & 0x10;
		if (cchr >= 'A' && cchr <= 'Z')
			return cchr - 'A';
		if (cchr >= 'a' && cchr <= 'z')
			return cchr - 'a';
		eUnknown("hexadecimal digit");
		return 0;
	}
	
	private int xxNext() {
		try {
			do {
				cchr = in.read();
				if (cchr == '\n') {
					++clne;
					ccol = 0;
				}
				++ccol;
			}
			while (Character.isWhitespace(cchr));
		}
		catch (IOException e) {
			throw new RuntimeException("Unexpected IOException caught during JSON parsing", e);
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
		error("\'" + byCP(cchr) + "\' expected, but \'" + byCP(cchr) + "\' found");
	}
	
	private void eRemapping(String key) {
		error("Remapping of \'" + key + "\' found");
	}
	
	private void eUnknown(String what) {
		error("Unknown " + what + " found");
	}
	
	private String byCP(int codepoint) {
		if (codepoint == -1)
			return "(EOF)";
		else
			return String.valueOf(Character.toChars(cchr));
	}
	
}
