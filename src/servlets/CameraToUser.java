package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CameraDao;
import dao.DaoFactory;
import dao.UserDao;
import exception.UserNotFoundException;
import exception.UserNotSavedException;
import model.Camera;
import model.PasswordHash;
import model.User;

public class CameraToUser extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final CameraDao cameraDao = DaoFactory.getInstance().getCameraDao();
	final UserDao userDao = DaoFactory.getInstance().getUserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try{
				List<Camera> collection = cameraDao.list();
				request.setAttribute("cameras", collection);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/camera.jsp");
				dispatcher.forward(request, response);
			} catch (UserNotFoundException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		Long id = null;
		
		
		
		String name = request.getParameter("name");
		User user = userDao.get(name);	
		String[] cameraNames = request.getParameterValues("cameraName");
		List<Camera> cameras =new ArrayList<Camera>();;
		for(int i = 0; i< cameraNames.length; i++){
			Camera camera = cameraDao.get(cameraNames[i]);
			cameras.add(camera);
		}
		
		//List<Camera> oldCameras = cameraDao.getCamerasOfUser(user);
	
			ListIterator<Camera> iterator = cameras.listIterator();
//			while (iterator.hasNext()) {
//					if(oldCameras.contains(iterator.next()))
//					{
//						iterator.remove();
//					}
//			}
		
		try {	
			cameraDao.dropCamerasForUser(user);
			cameraDao.saveCamerasForUser(user, cameras);
			List<User> collection = userDao.list();
			request.setAttribute("users", collection);
			response.sendRedirect(request.getContextPath() + "/administration");
		}  catch (UserNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
