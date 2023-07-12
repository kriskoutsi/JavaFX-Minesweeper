package application;

// This exception is called if a line isn't numeric
// or if a line contains out of range value 
public class InvalidValueException extends Exception {
	public InvalidValueException(String message) {
		super(message);
	}
}
