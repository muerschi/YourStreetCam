package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exception.UserNotSavedException;
import jndi.JndiFactory;
import model.Camera;
import model.Image;

public class ImageDaoImpl implements ImageDao{
	
	final JndiFactory jndi = JndiFactory.getInstance();
	
	@Override
	public void save(Image image){
		if (image == null)
			throw new IllegalArgumentException("image can not be null");
		
		Connection connection = null;		
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			if (image.getId() == null) {
				PreparedStatement pstmt = connection.prepareStatement("insert into pictures (date, path, camera_id) values (?,?,?)");
				pstmt.setTimestamp(1, new Timestamp(image.getDate().getTime()));
				pstmt.setString(2, image.getPath());
				pstmt.setLong(3, image.getCameraId());
				pstmt.executeUpdate();
			}		
		} catch (Exception e) {
			throw new UserNotSavedException();
		} finally {
			closeConnection(connection);
		}
	}
	
	
	@Override
	public List<Image> list(Camera camera){
		Connection connection = null;
		
		List<Image> imageList = new ArrayList<Image>();
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
				PreparedStatement pstmt = connection.prepareStatement("select pictures.picture_id, pictures.date, pictures.path, pictures.camera_id from pictures,cameras where pictures.camera_id = cameras.camera_id and cameras.camera_id = ?;");
				pstmt.setLong(1, camera.getId());
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Image image = new Image();
					image.setId(rs.getLong("picture_id"));
					image.setDate(new Date(rs.getTimestamp("date").getTime()));
					image.setPath(rs.getString("path"));
					image.setCameraId(rs.getLong("camera_id"));
					imageList.add(image);
				}	
			
				return imageList;
		} catch (Exception e) {
			throw new UserNotSavedException();
		} finally {
			closeConnection(connection);
		}}
	
	@Override
	public List<Image> list(Date date){
		Connection connection = null;
		
		List<Image> imageList = new ArrayList<Image>();
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
				PreparedStatement pstmt = connection.prepareStatement("select pictures.picture_id, pictures.date, pictures.path, pictures.camera_id from pictures where pictures.date = ?;");
				pstmt.setTimestamp(1, new Timestamp(date.getTime()));
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Image image = new Image();
					image.setId(rs.getLong("picture_id"));
					image.setDate(new Date(rs.getTimestamp("date").getTime()));
					image.setPath(rs.getString("path"));
					image.setCameraId(rs.getLong("camera_id"));
					imageList.add(image);
				}	
			
				return imageList;
		} catch (Exception e) {
			throw new UserNotSavedException();
		} finally {
			closeConnection(connection);
		}}
	
	@Override
	public List<Image> list(Camera camera, Date date){
		Connection connection = null;
		
		List<Image> imageList = new ArrayList<Image>();
		try {
			connection = jndi.getConnection("jdbc/libraryDB");			
			
			// TODO: list only top 50 ? 
				PreparedStatement pstmt = connection.prepareStatement("select top 50 pictures.picture_id, pictures.date, pictures.path, pictures.camera_id from pictures,cameras where pictures.camera_id = cameras.camera_id and cameras.camera_id = ? AND pictures.date = ?;");
				pstmt.setLong(1, camera.getId());
				pstmt.setTimestamp(2, new Timestamp(date.getTime()));
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					Image image = new Image();
					image.setId(rs.getLong("picture_id"));
					image.setDate(new Date(rs.getTimestamp("date").getTime()));
					image.setPath(rs.getString("path"));
					image.setCameraId(rs.getLong("camera_id"));
					imageList.add(image);
				}	
			
				return imageList;
		} catch (Exception e) {
			throw new UserNotSavedException();
		} finally {
			closeConnection(connection);
		}}
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
