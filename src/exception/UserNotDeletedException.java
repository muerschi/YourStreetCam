package exception;

public class UserNotDeletedException extends RuntimeException {
	
	public UserNotDeletedException(Long id) {
		super("Nutzer mit der Id " + id + " konnte nicht geändert werden!");
	}
}
