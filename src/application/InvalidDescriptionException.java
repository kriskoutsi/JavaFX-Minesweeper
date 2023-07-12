package application;

// This exception is called, if the description
// contains less or more than 4 lines
public class InvalidDescriptionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDescriptionException(String message) {
		super(message);
	}
}