package moe.eairpeter.jsonutils;

public class ParserError {

	public int line;
	public int column;
	public String message;
	
	public ParserError(int line_, int column_, String message_) {
		line = line_;
		column = column_;
		message = message_;
	}
	
}
