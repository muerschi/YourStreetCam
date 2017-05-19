package exception;

public class CameraUserInsertException extends RuntimeException {

	public CameraUserInsertException(Long id) {
		super("Dem Nutzer mit der Id " + id + " konnte keine Kamera zugewiesen werden!");
	}
}
