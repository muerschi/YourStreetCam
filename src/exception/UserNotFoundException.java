package exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(Long id) {
		super("Buch mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public UserNotFoundException() {
		super("B�cher k�nnen nicht aufgelistet werden!");
	}
}
