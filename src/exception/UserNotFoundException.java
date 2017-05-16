package exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(Long id) {
		super("Nutzer mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public UserNotFoundException() {
		super("Nutzer können nicht aufgelistet werden!");
	}
}
