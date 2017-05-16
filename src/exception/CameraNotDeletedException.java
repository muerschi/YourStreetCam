package exception;

public class CameraNotDeletedException extends RuntimeException {
	
	public CameraNotDeletedException(Long id) {
		super("Kamera mit der Id " + id + " konnte nicht geändert werden!");
	}
}
