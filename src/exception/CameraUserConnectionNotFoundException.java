package exception;


public class CameraUserConnectionNotFoundException extends RuntimeException{

	public CameraUserConnectionNotFoundException(Long id){
		super("Die Kameras des Nutzers mit der ID " + id + "konnten nicht gefunden werden!");
	}
}
