package moe.eairpeter.jsonutils;

public class ParserError {

	public final int line;
	public final int column;
	public final String message;
	
	public ParserError(int line_, int column_, String message_) {
		line = line_;
		column = column_;
		message = message_;
	}
	
	@Override
	public String toString() {
		return "Error at " + line + ":" + column + ", description: " + message;
	}
	
}
