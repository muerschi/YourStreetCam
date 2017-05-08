package exception;

public class UserNotDeletedException extends RuntimeException {
	
	public UserNotDeletedException(Long id) {
		super("Buch mit der Id " + id + " konnte nicht geändert werden!");
	}
}
