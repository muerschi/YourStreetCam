package dao;

import java.util.List;

import model.Camera;
import model.User;

public interface CameraDao {
	public void save(Camera camera);
	public void delete(Long id);
	public Camera get(Long id);
	public Camera get(String name);
	public List<Camera> list();
	public List<Camera> getCamerasOfUser(User user);
	public void saveCamerasForUser(User user, List<Camera> cameras);
	public void dropCamerasForUser(User user);
}
