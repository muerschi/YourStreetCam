package exception;

public class CameraNotSavedException extends RuntimeException {
	
	public CameraNotSavedException() {
		super("Buch konnte nicht gespeichert werden!");
	}
}
