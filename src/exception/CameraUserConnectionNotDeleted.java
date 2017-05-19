package exception;

public class CameraUserConnectionNotDeleted extends RuntimeException {
	
	public CameraUserConnectionNotDeleted(Long id)
	{
		super("Die Kameras des Nutzers mit der id " + id + "konnten nicht gelöscht werden!");
	}

}
