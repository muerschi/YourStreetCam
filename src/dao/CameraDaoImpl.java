package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CameraDao;
import exception.UserNotDeletedException;
import exception.UserNotFoundException;
import exception.UserNotSavedException;
import jndi.JndiFactory;
import model.Camera;


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
				PreparedStatement pstmt = connection.prepareStatement("insert into cameras (name, path) values (?,?)");
				pstmt.setString(1, camera.getName());
				pstmt.setString(2, camera.getPath());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = connection.prepareStatement("update cameras set name = ?, path = ? where id = ?");
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
			PreparedStatement pstmt = connection.prepareStatement("delete from cameras where id = ?");
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
			PreparedStatement pstmt = connection.prepareStatement("select id, name, path from cameras where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();							
			if (rs.next()) {
				Camera camera = new Camera();
				camera.setId(rs.getLong("id"));
				camera.setName(rs.getString("name"));
				camera.setPath(rs.getString("path"));
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
	public List<Camera> list() {
		List<Camera> cameraList = new ArrayList<Camera>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
				PreparedStatement pstmt = connection.prepareStatement("select id, name, path from cameras");				
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					Camera camera = new Camera();
					camera.setId(rs.getLong("id"));
					camera.setName(rs.getString("name"));
					camera.setPath(rs.getString("path"));
					cameraList.add(camera);
				}			
			
			return cameraList;
		} catch (Exception e) {
			throw new UserNotFoundException();
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
