package dao;

import java.sql.Date;
import java.util.List;
import model.Image;
import model.Camera;

public interface ImageDao{
	public void save(Image image);
	public List<Image> list(Camera camera);
	public List<Image> list(Date date);
	public List<Image> list(Camera camera, Date date);		
}
