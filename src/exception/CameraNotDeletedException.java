package exception;

public class CameraNotDeletedException extends RuntimeException {
	
	public CameraNotDeletedException(Long id) {
		super("Buch mit der Id " + id + " konnte nicht geändert werden!");
	}
}
