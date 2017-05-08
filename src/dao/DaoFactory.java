package dao;

import dao.UserDaoImpl;

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
}
