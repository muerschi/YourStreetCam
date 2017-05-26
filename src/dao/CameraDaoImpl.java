package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import dao.CameraDao;
import exception.CameraUserInsertException;
import exception.CameraUserConnectionNotDeleted;
import exception.CameraUserConnectionNotFoundException;
import exception.UserNotDeletedException;
import exception.UserNotFoundException;
import exception.UserNotSavedException;
import jndi.JndiFactory;
import model.Camera;
import model.User;


public class CameraDaoImpl implements CameraDao {

	private static final boolean Camera = false;
	final JndiFactory jndi = JndiFactory.getInstance();
	
	@Override
	public void save(Camera camera) {
		if (camera == null)
			throw new IllegalArgumentException("camera can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			if (camera.getId() == null) {
				PreparedStatement pstmt = connection.prepareStatement("insert into cameras (camera, pictures) values (?,?)");
				pstmt.setString(1, camera.getName());
				pstmt.setString(2, camera.getPath());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = connection.prepareStatement("update cameras set camera = ?, pictures = ? where camera_id = ?");
				pstmt.setString(1, camera.getName());
				pstmt.setString(2, camera.getPath());
				pstmt.setLong(3, camera.getId());
				pstmt.executeUpdate();
			}			
		} catch (Exception e) {
			throw new UserNotSavedException();
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public void delete(Long id) {
		if (id == null)
			throw new IllegalArgumentException("id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
			PreparedStatement pstmt = connection.prepareStatement("delete from cameras where camera_id = ?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();			
		} catch (Exception e) {
			throw new UserNotDeletedException(id);
		} finally {
			closeConnection(connection);
		}
		
	}

	@Override
	public Camera get(Long id) {
		if (id == null)
			throw new IllegalArgumentException("id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			PreparedStatement pstmt = connection.prepareStatement("select camera_id, camera, pictures from cameras where camera_id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();							
			if (rs.next()) {
				Camera camera = new Camera();
				camera.setId(rs.getLong("camera_id"));
				camera.setName(rs.getString("camera"));
				camera.setPath(rs.getString("pictures"));
				return camera;
			} else {
				throw new UserNotFoundException(id);
			}			
		} catch (Exception e) {
			throw new UserNotFoundException(id);
		} finally {	
			closeConnection(connection);
		}
	}
	@Override
	public Camera get(String name) {
		if (name == null)
			throw new IllegalArgumentException("id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			PreparedStatement pstmt = connection.prepareStatement("select camera_id, camera, pictures from cameras where camera = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();							
			if (rs.next()) {
				Camera camera = new Camera();
				camera.setId(rs.getLong("camera_id"));
				camera.setName(rs.getString("camera"));
				camera.setPath(rs.getString("pictures"));
				return camera;
			} else {
				//throw new UserNotFoundException(id);
			}			
		} catch (Exception e) {
			//throw new UserNotFoundException(id);
		} finally {	
			closeConnection(connection);
		}
		return null;
	}
	@Override
	public List<Camera> list() {
		List<Camera> cameraList = new ArrayList<Camera>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
				PreparedStatement pstmt = connection.prepareStatement("select camera_id, camera, pictures from cameras");				
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					Camera camera = new Camera();
					camera.setId(rs.getLong("camera_id"));
					camera.setName(rs.getString("camera"));
					camera.setPath(rs.getString("pictures"));
					cameraList.add(camera);
				}			
			
			return cameraList;
		} catch (Exception e) {
			throw new UserNotFoundException();
		} finally {	
			closeConnection(connection);
		}
	}

	@Override
	public List<Camera> getCamerasOfUser(User user){
		List<Camera> cameraList = new ArrayList<Camera>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
				PreparedStatement pstmt = connection.prepareStatement("select cameras.camera_id, cameras.camera from cameras, user_camera where cameras.camera_id = user_camera.camera_id and user_camera.user_id = ?;");				
				pstmt.setLong(1, user.getId());
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					Camera camera = new Camera();
					camera.setId(rs.getLong("camera_id"));
					camera.setName(rs.getString("camera"));
					cameraList.add(camera);
				}			
			
			return cameraList;
		} catch (Exception e) {
			throw new CameraUserConnectionNotFoundException(user.getId());
		} finally {	
			closeConnection(connection);
		}
		
	}
	
	@Override
	public void saveCamerasForUser(User user, List<Camera> cameras){
		
		//List<Camera> oldCameras = this.getCamerasOfUser(user);
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
			ListIterator<Camera> iterator = cameras.listIterator();
			
			// Füge die neuen Einträge für den aktuellen Nutzer ein
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user_camera (user_id, camera_id) VALUES (?, ?)");				
				while (iterator.hasNext()) {
					Camera camera = iterator.next();
					//if(!oldCameras.contains(camera)){
					pstmt.setLong(1, user.getId());
					pstmt.setLong(2, camera.getId());	
					pstmt.executeUpdate();}
				//}	
		} catch (Exception e) {
			throw new CameraUserInsertException(user.getId());
		} finally {	
			closeConnection(connection);
		}
	}
	
	@Override
	public void dropCamerasForUser(User user){
		Connection connection = null;	
		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");	
			PreparedStatement pstmt = connection.prepareStatement("delete from user_camera where user_camera.user_id = ?;");
			pstmt.setLong(1, user.getId());
			pstmt.executeQuery();
					
		} catch (Exception e) {
			//throw new CameraUserConnectionNotDeleted(user.getId());
		} finally {	
			closeConnection(connection);
		}
	}
	
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				// nothing to do
				e.printStackTrace();
			}				
		}
	}
	
}
