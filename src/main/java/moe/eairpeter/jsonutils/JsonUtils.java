package moe.eairpeter.jsonutils;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import moe.eairpeter.jsonutils.parsed.JsonArray;
import moe.eairpeter.jsonutils.parsed.JsonBase;
import moe.eairpeter.jsonutils.parsed.JsonBool;
import moe.eairpeter.jsonutils.parsed.JsonNumber;
import moe.eairpeter.jsonutils.parsed.JsonObject;

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
	
}
