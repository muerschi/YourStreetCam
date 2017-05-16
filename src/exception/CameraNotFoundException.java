package exception;

public class CameraNotFoundException extends RuntimeException {
	
	public CameraNotFoundException(Long id) {
		super("Kamera mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public CameraNotFoundException() {
		super("Kameras k�nnen nicht aufgelistet werden!");
	}
}
