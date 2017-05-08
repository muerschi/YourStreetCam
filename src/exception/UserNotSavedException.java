package exception;

public class UserNotSavedException extends RuntimeException {
	
	public UserNotSavedException() {
		super("Buch konnte nicht gespeichert werden!");
	}
}
