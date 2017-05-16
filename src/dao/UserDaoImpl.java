package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import jndi.JndiFactory;

import model.User;
import model.Camera;
import exception.UserNotDeletedException;
import exception.UserNotSavedException;
import exception.UserNotFoundException;

public class UserDaoImpl implements UserDao {
	
	private static final boolean User = false;
	final JndiFactory jndi = JndiFactory.getInstance();

	@Override
	public void delete(Long id) {
		
		if (id == null)
			throw new IllegalArgumentException("id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");		
			PreparedStatement pstmt = connection.prepareStatement("delete from users where user_id = ?");
			pstmt.setLong(1, id);
			pstmt.executeUpdate();			
		} catch (Exception e) {
			throw new UserNotDeletedException(id);
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public User get(Long id) {
		
		if (id == null)
			throw new IllegalArgumentException("user_id can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			PreparedStatement pstmt = connection.prepareStatement("select user_id, user, password, role from users where user_id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();							
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("user_id"));
				user.setName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				return user;
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
	public void save(User user) {
		
		if (user == null)
			throw new IllegalArgumentException("user can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			if (user.getId() == null) {
				PreparedStatement pstmt = connection.prepareStatement("insert into users (username, password, role) values (?,?,?)");
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getRole());
				pstmt.executeUpdate();
			} else {
				PreparedStatement pstmt = connection.prepareStatement("update users set username = ?, password = ?, role = ? where user_id = ?");
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getRole());
				pstmt.setLong(4, user.getId());
				pstmt.executeUpdate();
			}			
		} catch (Exception e) {
			throw new UserNotSavedException();
		} finally {
			closeConnection(connection);
		}
	}	

	@Override
	public List<User> list() {
		
		List<User> userList = new ArrayList<User>();
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
				PreparedStatement pstmt = connection.prepareStatement("select user_id, username, password, role from users");				
				ResultSet rs = pstmt.executeQuery();
								
				while (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("user_id"));
					user.setName(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					userList.add(user);
				}			
			
			return userList;
		} catch (Exception e) {
			throw new UserNotFoundException();
		} finally {	
			closeConnection(connection);
		}
	}
	
	public User get(String name){
		
		if (name == null)
			throw new IllegalArgumentException("name can not be null");
		
		Connection connection = null;		
		try {
		List<User> users = this.list();
		
		for (ListIterator<User> iter = users.listIterator(); iter.hasNext(); ) {
			User user = iter.next();
			if(user.getName().equals(name)){
				return user;
			}

		}
		return null;
		} catch (Exception e) {
			throw new UserNotFoundException(null);
		} finally {	
			closeConnection(connection);
		}
		
		
	}
	
	public void setUserCameraConnection(User user, Camera camera){
		
		if (user == null || camera == null)
			throw new IllegalArgumentException("user can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			

			PreparedStatement pstmt = connection.prepareStatement("insert into user_camera (user_id, camera_id) values (?, ?)");
			pstmt.setLong(1, user.getId());
			pstmt.setLong(2, camera.getId());
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			throw new UserNotSavedException();
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
