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

public class JsonUtils {
	
	//Don't ask me "Why don't you use reflect"
	//^=Reflect sucks, much worse than Java itself
	
	//Java sucks!
	public static ParserResult<JsonBase> parseValue(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseValue(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonObject> parseObject(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonObject> res = parser.parseObject(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonObject> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}

	public static ParserResult<JsonArray> parseArray(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonArray> res = parser.parseArray(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonArray> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonNumber> parseNumber(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonNumber> res = parser.parseNumber(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonNumber> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonBool> parseBool(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBool> res = parser.parseBool(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBool> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonBase> parseNull(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseNull(true);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	//So, Java sucks again...
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
	public static ParserResult<JsonBase> farseValue(String path) {
		return farseValue(new File(path));
	}
	
	public static ParserResult<JsonObject> farseObject(String path) {
		return farseObject(new File(path));
	}
	
	public static ParserResult<JsonArray> farseArray(String path) {
		return farseArray(new File(path));
	}
	
	public static ParserResult<JsonNumber> farseNumber(String path) {
		return farseNumber(new File(path));
	}
	
	public static ParserResult<JsonBool> farseBool(String path) {
		return farseBool(new File(path));
	}
	
	public static ParserResult<JsonBase> farseNull(String path) {
		return farseNull(new File(path));
	}
	
	//Well, let's start again
	
	//Java sucks!
	public static ParserResult<JsonBase> checkValue(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseValue(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonObject> checkObject(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonObject> res = parser.parseObject(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonObject> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}

	public static ParserResult<JsonArray> checkArray(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonArray> res = parser.parseArray(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonArray> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonNumber> checkNumber(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonNumber> res = parser.parseNumber(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonNumber> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonBool> checkBool(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBool> res = parser.parseBool(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBool> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	public static ParserResult<JsonBase> checkNull(Reader reader) {
		JsonParser parser = new JsonParser(reader);
		ParserResult<JsonBase> res = parser.parseNull(false);
		if (!res.succeeded)
			return res;
		ParserResult<JsonBase> eof = parser.parseEOF();
		return eof.succeeded ? res : eof;
	}
	
	//So, Java sucks again...
	public static ParserResult<JsonBase> checkValue(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBase> res = parseValue(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonObject> checkObject(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonObject> res = parseObject(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}	
	
	public static ParserResult<JsonArray> checkArray(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonArray> res = parseArray(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonNumber> checkNumber(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonNumber> res = parseNumber(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonBool> checkBool(String string) {
		Reader reader = new StringReader(string);
		ParserResult<JsonBool> res = parseBool(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonBase> checkNull(String string) {
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
	public static ParserResult<JsonBase> checkValue(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBase> res = parseValue(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonObject> checkObject(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonObject> res = parseObject(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonArray> checkArray(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonArray> res = parseArray(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonNumber> checkNumber(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonNumber> res = parseNumber(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonBool> checkBool(InputStream istream) {
		Reader reader = new InputStreamReader(istream);
		ParserResult<JsonBool> res = parseBool(reader);
		try {
			reader.close();
		} catch (Throwable e) {
			JsonParser.raiseUnexpected(e);
		}
		return res;
	}
	
	public static ParserResult<JsonBase> checkNull(InputStream istream) {
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
	public static ParserResult<JsonBase> fheckValue(File file) {
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
	
	public static ParserResult<JsonObject> fheckObject(File file) {
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
	
	public static ParserResult<JsonArray> fheckArray(File file) {
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
	
	public static ParserResult<JsonNumber> fheckNumber(File file) {
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
	
	public static ParserResult<JsonBool> fheckBool(File file) {
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
	
	public static ParserResult<JsonBase> fheckNull(File file) {
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
	public static ParserResult<JsonBase> fheckValue(String path) {
		return farseValue(new File(path));
	}
	
	public static ParserResult<JsonObject> fheckObject(String path) {
		return farseObject(new File(path));
	}
	
	public static ParserResult<JsonArray> fheckArray(String path) {
		return farseArray(new File(path));
	}
	
	public static ParserResult<JsonNumber> fheckNumber(String path) {
		return farseNumber(new File(path));
	}
	
	public static ParserResult<JsonBool> fheckBool(String path) {
		return farseBool(new File(path));
	}
	
	public static ParserResult<JsonBase> fheckNull(String path) {
		return farseNull(new File(path));
	}
	
	//Finally, I found that Windows Notepad is a good editor
	
	//Don't ask me "Why do you use reflect"
	//^=Although reflect sucks, I have no other choice
	
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
		
		try {
			Class<?> clazz = obj.getClass();
			Method mToJson = clazz.getMethod("toJson");
			Object res = mToJson.invoke(obj);
			if (res instanceof JsonBase)
				return (JsonBase) res;
			throw new RuntimeException("obj.toJson() must return <? extends JsonBase>");
		}
		catch (Throwable e) {
			throw new IllegalArgumentException("Unsupported type: " + obj.getClass().getName(), e);
		}
		
	}
	
	public static JsonBool toJson(boolean b) {
		return new JsonBool(b);
	}
	
	public static JsonNumber toJson(byte b) {
		return new JsonNumber(b);
	}
	
	public static JsonString toJson(char c) {
		return new JsonString(String.valueOf(c));
	}
	
	public static JsonNumber toJson(double d) {
		return new JsonNumber(d);
	}
	
	public static JsonNumber toJson(float f) {
		return new JsonNumber(f);
	}
	
	public static JsonNumber toJson(int i) {
		return new JsonNumber(i);
	}
	
	public static JsonNumber toJson(long l) {
		return new JsonNumber(l);
	}
	
	public static JsonNumber toJson(short s) {
		return new JsonNumber(s);
	}
	
	public static JsonArray toJson(boolean[] bs) {
		JsonArray res = new JsonArray();
		for (boolean b : bs)
			res.add(new JsonBool(b));
		return res;
	}
	
	public static JsonArray toJson(byte[] bs) {
		JsonArray res = new JsonArray();
		for (byte b : bs)
			res.add(new JsonNumber(b));
		return res;
	}
	
	public static JsonString toJson(char[] cs) {
		return new JsonString(String.valueOf(cs));
	}
	
	public static JsonString toJson(String str) {
		return new JsonString(str);
	}
	
	public static JsonArray toJson(double[] ds) {
		JsonArray res = new JsonArray();
		for (double d : ds)
			res.add(new JsonNumber(d));
		return res;
	}
	
	public static JsonArray toJson(float[] fs) {
		JsonArray res = new JsonArray();
		for (float f : fs)
			res.add(new JsonNumber(f));
		return res;
	}
	
	public static JsonArray toJson(int[] is) {
		JsonArray res = new JsonArray();
		for (double i : is)
			res.add(new JsonNumber(i));
		return res;
	}
	
	public static JsonArray toJson(long[] ls) {
		JsonArray res = new JsonArray();
		for (double l : ls)
			res.add(new JsonNumber(l));
		return res;
	}
	
	public static JsonArray toJson(short[] ss) {
		JsonArray res = new JsonArray();
		for (double s : ss)
			res.add(new JsonNumber(s));
		return res;
	}
	
	public static JsonArray toJson(Entry<?, ?> entry) {
		return new JsonArray(entry.getKey(), entry.getValue());
	}
	
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
	
	public static JsonArray toJson(List<?> list) {
		JsonArray res = new JsonArray();
		for (Object o : list)
			res.data.add(toJson(o));
		return res;
	}
	
	public static JsonArray toJson(Object[] objs) {
		JsonArray res = new JsonArray();
		for (Object o : objs)
			res.data.add(toJson(o));
		return res;
	}
	
	public static Object unwrap(JsonBase json) {
		return json == null ? null : json.unwrap();
	}
	
	public static Map<String, Object> unwrap(JsonObject json) {
		return json == null ? null : json.unwrap();
	}
	
	public static Object[] unwrap(JsonArray json) {
		return json == null ? null : json.unwrap();
	}
	
	public static Double unwrap(JsonNumber json) {
		return json == null ? null : json.unwrap();
	}
	
	public static Boolean unwrap(JsonBool json) {
		return json == null ? null : json.unwrap();
	}
	
}
