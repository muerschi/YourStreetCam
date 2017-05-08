package dao;

import java.util.List;

import model.Camera;

public interface CameraDao {
	public void save(Camera camera);
	public void delete(Long id);
	public Camera get(Long id);
	public List<Camera> list();
}
