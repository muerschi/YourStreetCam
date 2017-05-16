package dao;

import dao.UserDaoImpl;
import dao.ImageDao;

public class DaoFactory {
	
	private static DaoFactory instance = new DaoFactory();
	
	private DaoFactory() {		
	}
	
	public static DaoFactory getInstance() {
		return instance;
	}
	
	public UserDao getUserDao() {
		return new UserDaoImpl();
	}
	
	public CameraDao getCameraDao(){
		return new CameraDaoImpl();
	}
	
	public ImageDao getImageDao(){
		return new ImageDaoImpl();
	}
}
