package moe.eairpeter.jsonutils;

/**
 * Representing an error encountered when parsing a JSON.
 * @author EAirPeter
 */
public final class ParserError {

	/**
	 * The line number when the error encountered.
	 */
	public final int line;
	/**
	 * The column number when the error encountered.
	 */
	public final int column;
	/**
	 * The message given by the parser when the error encountered.
	 */
	public final String message;
	
	ParserError(int line_, int column_, String message_) {
		line = line_;
		column = column_;
		message = message_;
	}
	
	@Override
	public String toString() {
		return "Error at " + line + ":" + column + ", description: " + message;
	}
	
}
