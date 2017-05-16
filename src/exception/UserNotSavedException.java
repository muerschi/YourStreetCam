package exception;

public class UserNotSavedException extends RuntimeException {
	
	public UserNotSavedException() {
		super("Nutzer konnte nicht gespeichert werden!");
	}
}
