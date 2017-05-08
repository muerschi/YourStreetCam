package exception;

public class CameraNotFoundException extends RuntimeException {
	
	public CameraNotFoundException(Long id) {
		super("Buch mit der Id " + id + " wurde nicht gefunden!");
	}
	
	public CameraNotFoundException() {
		super("Bücher können nicht aufgelistet werden!");
	}
}
