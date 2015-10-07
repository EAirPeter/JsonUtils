package moe.eairpeter.jsonutils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import moe.eairpeter.jsonutils.parsed.JsonArray;
import moe.eairpeter.jsonutils.parsed.JsonBase;
import moe.eairpeter.jsonutils.parsed.JsonBool;
import moe.eairpeter.jsonutils.parsed.JsonNumber;
import moe.eairpeter.jsonutils.parsed.JsonObject;
import moe.eairpeter.jsonutils.parsed.JsonString;

/**
 * Containing some useful JSON methods.
 * @author EAirPeter
 */
public final class JsonUtils {
	
	//Don't ask me "Why don't you use reflect"
	//^=Reflect sucks, much worse than Java itself
	
	//Java sucks!
	/**
	 * Construct a JSON value according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> parseValue(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseValue(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON object according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> parseObject(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonObject> res = parser.parseObject(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonObject> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}

	/**
	 * Construct a JSON array according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> parseArray(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonArray> res = parser.parseArray(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonArray> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON string according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> parseString(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonString> res = parser.parseString(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonString> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON number according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> parseNumber(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonNumber> res = parser.parseNumber(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonNumber> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> parseBool(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBool> res = parser.parseBool(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBool> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> parseNull(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseNull(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	//So, Java sucks again...
	/**
	 * Construct a JSON value according to given {@code String}.
	 * @param string The string.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> parseValue(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBase> res = parseValue(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON object according to given {@code String}.
	 * @param string The string.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> parseObject(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonObject> res = parseObject(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}	
	
	/**
	 * Construct a JSON array according to given {@code String}.
	 * @param string The string.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> parseArray(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonArray> res = parseArray(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON string according to given {@code String}.
	 * @param string The string.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> parseString(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonString> res = parseString(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON number according to given {@code String}.
	 * @param string The string.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> parseNumber(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonNumber> res = parseNumber(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code String}.
	 * @param string The string.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> parseBool(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBool> res = parseBool(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code String}.
	 * @param string The string.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> parseNull(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBase> res = parseNull(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	//And, Java is still sucking...
	/**
	 * Construct a JSON value according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> parseValue(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBase> res = parseValue(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON object according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> parseObject(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonObject> res = parseObject(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON array according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> parseArray(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonArray> res = parseArray(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON string according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> parseString(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonString> res = parseString(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON number according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> parseNumber(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonNumber> res = parseNumber(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> parseBool(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBool> res = parseBool(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> parseNull(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBase> res = parseNull(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	//Question, is Java savable?
	/**
	 * Construct a JSON value according to given {@code File}.
	 * @param file The file.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> farseValue(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonBase> res = parseValue(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON object according to given {@code File}.
	 * @param file The file.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> farseObject(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonObject> res = parseObject(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON array according to given {@code File}.
	 * @param file The file.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> farseArray(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonArray> res = parseArray(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON string according to given {@code File}.
	 * @param file The file.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> farseString(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonString> res = parseString(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON number according to given {@code File}.
	 * @param file The file.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> farseNumber(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonNumber> res = parseNumber(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code File}.
	 * @param file The file.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> farseBool(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonBool> res = parseBool(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code File}.
	 * @param file The file.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> farseNull(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonBase> res = parseNull(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	//Actually, Java always sucks
	/**
	 * Construct a JSON value according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> farseValue(String path) {
		return farseValue(new File(path));
	}
	
	/**
	 * Construct a JSON object according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> farseObject(String path) {
		return farseObject(new File(path));
	}
	
	/**
	 * Construct a JSON array according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> farseArray(String path) {
		return farseArray(new File(path));
	}
	
	/**
	 * Construct a JSON string according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> farseString(String path) {
		return farseString(new File(path));
	}
	
	/**
	 * Construct a JSON number according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> farseNumber(String path) {
		return farseNumber(new File(path));
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> farseBool(String path) {
		return farseBool(new File(path));
	}
	
	/**
	 * Construct a JSON {@literal null} value according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> farseNull(String path) {
		return farseNull(new File(path));
	}
	
	//Well, let's start again
	
	//Java sucks!
	/**
	 * Construct a JSON value according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> checkValue(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseValue(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON object according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> checkObject(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonObject> res = parser.parseObject(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonObject> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}

	/**
	 * Construct a JSON array according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> checkArray(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonArray> res = parser.parseArray(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonArray> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON string according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> checkString(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonString> res = parser.parseString(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonString> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON number according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> checkNumber(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonNumber> res = parser.parseNumber(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonNumber> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> checkBool(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBool> res = parser.parseBool(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBool> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code Reader}.
	 * @param reader The reader.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> checkNull(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseNull(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	//So, Java sucks again...
	/**
	 * Construct a JSON value according to given {@code String}.
	 * @param string The string.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> checkValue(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBase> res = checkValue(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON object according to given {@code String}.
	 * @param string The string.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> checkObject(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonObject> res = checkObject(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}	
	
	/**
	 * Construct a JSON array according to given {@code String}.
	 * @param string The string.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> checkArray(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonArray> res = checkArray(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON string according to given {@code String}.
	 * @param string The string.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> checkString(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonString> res = checkString(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON number according to given {@code String}.
	 * @param string The string.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> checkNumber(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonNumber> res = checkNumber(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code String}.
	 * @param string The string.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> checkBool(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBool> res = checkBool(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code String}.
	 * @param string The string.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> checkNull(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBase> res = checkNull(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	//And, Java is still sucking...
	/**
	 * Construct a JSON value according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> checkValue(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBase> res = checkValue(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON object according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> checkObject(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonObject> res = checkObject(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON array according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> checkArray(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonArray> res = checkArray(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON string according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> checkString(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonString> res = checkString(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON number according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> checkNumber(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonNumber> res = checkNumber(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> checkBool(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBool> res = checkBool(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code InputStream}.
	 * @param istream The input stream.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> checkNull(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBase> res = checkNull(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	//Question, is Java savable?
	/**
	 * Construct a JSON value according to given {@code File}.
	 * @param file The file.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> fheckValue(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonBase> res = checkValue(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON object according to given {@code File}.
	 * @param file The file.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> fheckObject(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonObject> res = checkObject(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON array according to given {@code File}.
	 * @param file The file.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> fheckArray(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonArray> res = checkArray(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON string according to given {@code File}.
	 * @param file The file.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> fheckString(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonString> res = checkString(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON number according to given {@code File}.
	 * @param file The file.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> fheckNumber(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonNumber> res = checkNumber(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to given {@code File}.
	 * @param file The file.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> fheckBool(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonBool> res = checkBool(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	/**
	 * Construct a JSON {@literal null} value according to given {@code File}.
	 * @param file The file.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> fheckNull(File file) {
		try {
			Reader reader = new FileReader(file);
			ParserResult<JsonBase> res = checkNull(reader);
			reader.close();
			return res;
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		//Never hit this
		return null;
	}
	
	//Actually, Java always sucks
	/**
	 * Construct a JSON value according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON value.
	 */
	public static ParserResult<JsonBase> fheckValue(String path) {
		return fheckValue(new File(path));
	}
	
	/**
	 * Construct a JSON object according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON object.
	 */
	public static ParserResult<JsonObject> fheckObject(String path) {
		return fheckObject(new File(path));
	}
	
	/**
	 * Construct a JSON array according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON array.
	 */
	public static ParserResult<JsonArray> fheckArray(String path) {
		return fheckArray(new File(path));
	}
	
	/**
	 * Construct a JSON string according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON string.
	 */
	public static ParserResult<JsonString> fheckString(String path) {
		return fheckString(new File(path));
	}
	
	/**
	 * Construct a JSON number according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON number.
	 */
	public static ParserResult<JsonNumber> fheckNumber(String path) {
		return fheckNumber(new File(path));
	}
	
	/**
	 * Construct a JSON {@literal true} or {@literal false} value according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON {@literal true} or {@literal false} value.
	 */
	public static ParserResult<JsonBool> fheckBool(String path) {
		return fheckBool(new File(path));
	}
	
	/**
	 * Construct a JSON {@literal null} value according to the {@code File} at given path.
	 * @param path The path to the file.
	 * @return The JSON {@literal null} value.
	 */
	public static ParserResult<JsonBase> fheckNull(String path) {
		return fheckNull(new File(path));
	}
	
	//Finally, I found that Windows Notepad is a good editor
	
	//Don't ask me "Why do you use reflect"
	//^=Although reflect sucks, I have no other choice
	
	/**
	 * Translate an {@code Object} to a JSON value.<br>
	 * If the {@code obj} is not supported by "specialization", it can implement {@link Jsonizable}
	 * @param obj The object.
	 * @return The JSON value.
	 */
	public static JsonBase toJson(Object obj) {
		if (obj == null)
			return null;
		if (obj instanceof JsonBase)
			return (JsonBase) obj;
		
		if (obj instanceof Boolean)
			return toJson((boolean) obj);
		if (obj instanceof Byte)
			return toJson((byte) obj);
		if (obj instanceof Character)
			return toJson((char) obj);
		if (obj instanceof Double)
			return toJson((double) obj);
		if (obj instanceof Float)
			return toJson((float) obj);
		if (obj instanceof Integer)
			return toJson((int) obj);
		if (obj instanceof Long)
			return toJson((long) obj);
		if (obj instanceof Short)
			return toJson((short) obj);
		
		if (obj instanceof boolean[])
			return toJson((boolean[]) obj);
		if (obj instanceof byte[])
			return toJson((byte[]) obj);
		if (obj instanceof char[])
			return toJson((char[]) obj);
		if (obj instanceof double[])
			return toJson((double[]) obj);
		if (obj instanceof float[])
			return toJson((float[]) obj);
		if (obj instanceof int[])
			return toJson((int[]) obj);
		if (obj instanceof long[])
			return toJson((long[]) obj);
		if (obj instanceof short[])
			return toJson((short[]) obj);
		
		if (obj instanceof String)
			return toJson((String) obj);
		
		if (obj instanceof Entry)
			return toJson((Entry<?, ?>) obj);
		if (obj instanceof Map)
			return toJson((Map<?, ?>) obj);
		if (obj instanceof List)
			return toJson((List<?>) obj);
		if (obj instanceof Object[])
			return toJson((Object[]) obj);
		
		if (obj instanceof Jsonizable)
			return ((Jsonizable) obj).toJson();
		
		throw new IllegalArgumentException("Unsupported type: " + obj.getClass().getName());		
	}
	
	/**
	 * @return A {@code JsonBool}.
	 */
	public static JsonBool toJson(boolean b) {
		return new JsonBool(b);
	}
	
	/**
	 * @return A {@code JsonNumber}.
	 */
	public static JsonNumber toJson(byte b) {
		return new JsonNumber(b);
	}
	
	/**
	 * @return A {@code JsonString}.
	 */
	public static JsonString toJson(char c) {
		return new JsonString(String.valueOf(c));
	}
	
	/**
	 * @return A {@code JsonNumber}.
	 */
	public static JsonNumber toJson(double d) {
		return new JsonNumber(d);
	}
	
	/**
	 * @return A {@code JsonNumber}.
	 */
	public static JsonNumber toJson(float f) {
		return new JsonNumber(f);
	}
	
	/**
	 * @return A {@code JsonNumber}.
	 */
	public static JsonNumber toJson(int i) {
		return new JsonNumber(i);
	}
	
	/**
	 * @return A {@code JsonNumber}.
	 */
	public static JsonNumber toJson(long l) {
		return new JsonNumber(l);
	}
	
	/**
	 * @return A {@code JsonNumber}.
	 */
	public static JsonNumber toJson(short s) {
		return new JsonNumber(s);
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonBool}.
	 */
	public static JsonArray toJson(boolean[] bs) {
		JsonArray res = new JsonArray();
		for (boolean b : bs)
			res.add(new JsonBool(b));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonNumber}.
	 */
	public static JsonArray toJson(byte[] bs) {
		JsonArray res = new JsonArray();
		for (byte b : bs)
			res.add(new JsonNumber(b));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonString}.
	 */
	public static JsonArray toJson(char[] cs) {
		JsonArray res = new JsonArray();
		for (char c: cs)
			res.add(new JsonString(String.valueOf(c)));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonNumber}.
	 */
	public static JsonArray toJson(double[] ds) {
		JsonArray res = new JsonArray();
		for (double d : ds)
			res.add(new JsonNumber(d));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonNumber}.
	 */
	public static JsonArray toJson(float[] fs) {
		JsonArray res = new JsonArray();
		for (float f : fs)
			res.add(new JsonNumber(f));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonNumber}.
	 */
	public static JsonArray toJson(int[] is) {
		JsonArray res = new JsonArray();
		for (int i : is)
			res.add(new JsonNumber(i));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonNumber}.
	 */
	public static JsonArray toJson(long[] ls) {
		JsonArray res = new JsonArray();
		for (long l : ls)
			res.add(new JsonNumber(l));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} consisting of {@code JsonNumber}.
	 */
	public static JsonArray toJson(short[] ss) {
		JsonArray res = new JsonArray();
		for (short s : ss)
			res.add(new JsonNumber(s));
		return res;
	}
	
	/**
	 * @return A {@code JsonString}.
	 */
	public static JsonString toJson(String str) {
		return new JsonString(str);
	}
	
	/**
	 * Convert an {@code Entry} to a two-value JSON array {@code [KEY, VALUE]}.
	 * @return A {@code JsonArray} consisting two values.
	 */
	public static JsonArray toJson(Entry<?, ?> entry) {
		return new JsonArray(entry.getKey(), entry.getValue());
	}
	
	/**
	 * Convert an {@code Map} to a JSON object.<br>
	 * The string part of each pair will be obtaind by {@code KEY.toString()}.
	 * {@literal null} KEY is not allowed.
	 * @return A {@code JsonObject} or {@literal null}.
	 */
	public static JsonObject toJson(Map<?, ?> map) {
		JsonObject res = new JsonObject();
		for (Entry<?, ?> e : map.entrySet()) {
			Object string = e.getKey();
			Object value = e.getValue();
			if (string == null)
				return null;
			res.put(new JsonString(string.toString()), toJson(value));
			
		}
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} representing the given {@code List}.
	 */
	public static JsonArray toJson(List<?> list) {
		JsonArray res = new JsonArray();
		for (Object o : list)
			res.data.add(toJson(o));
		return res;
	}
	
	/**
	 * @return A {@code JsonArray} representing the given {@code Array}.
	 */
	public static JsonArray toJson(Object[] objs) {
		JsonArray res = new JsonArray();
		for (Object o : objs)
			res.data.add(toJson(o));
		return res;
	}
	
	/**
	 * @return The {@code Object} unwrapped from given JSON value, {@literal null} for {@literal null} argument.
	 */
	public static Object unwrap(JsonBase json) {
		return json == null ? null : json.unwrap();
	}
	
	/**
	 * @return The {@code Map} unwrapped from given JSON object, {@literal null} for {@literal null} argument.
	 */
	public static Map<String, Object> unwrap(JsonObject json) {
		return json == null ? null : json.unwrap();
	}
	
	/**
	 * @return The {@code Array} unwrapped from given JSON array, {@literal null} for {@literal null} argument.
	 */
	public static Object[] unwrap(JsonArray json) {
		return json == null ? null : json.unwrap();
	}
	
	/**
	 * @return The {@code String} unwrapped from given JSON string, {@literal null} for {@literal null} argument.
	 */
	public static String unwrap(JsonString json) {
		return json == null ? null : json.unwrap();
	}
	
	/**
	 * @return The {@code Double} unwrapped from given JSON number, {@literal null} for {@literal null} argument.
	 */
	public static Double unwrap(JsonNumber json) {
		return json == null ? null : json.unwrap();
	}
	
	/**
	 * @return The {@code Boolean} unwrapped from given JSON {@literal true} or {@literal false} value, {@literal null} for {@literal null} argument.
	 */
	public static Boolean unwrap(JsonBool json) {
		return json == null ? null : json.unwrap();
	}
	
	/**
	 * @return {@literal true} if {@code codepoint} is a valid JSON whitespace.
	 */
	public static boolean isWhitespace(int codepoint) {
		return codepoint == ' ' || codepoint == '\t' || codepoint == '\n' || codepoint == '\r';
	}
	
	/**
	 * @return {@literal true} if {@code codepoint} is a valid JSON structural token.
	 */
	public static boolean isStructural(int codepoint) {
		return codepoint == '[' || codepoint == '{' || codepoint == ']' || codepoint == '}' || codepoint == ':' || codepoint == ',';
	}
	
}
