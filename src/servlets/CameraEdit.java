package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CameraDao;
import dao.CameraDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import dao.DaoFactory;
import exception.CameraNotDeletedException;
import exception.UserNotSavedException;
import model.Camera;
import model.User;



public class CameraEdit extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	final CameraDao cameraDao = DaoFactory.getInstance().getCameraDao();
	final UserDao userDao = DaoFactory.getInstance().getUserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if (action == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
				
		Long id = null;
		
		// Vorsicht! Als Parameter wurde User oder Camera mitgegeben. Nicht eindeutig!
//		if (request.getParameter("id") != null) {
//			id = Long.valueOf(request.getParameter("id"));
//		}
				
		if(action.equals("add")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/cameraadd.jsp");
			dispatcher.forward(request, response);				
		} else if(action.equals("camera")){
			String name = request.getParameter("id");
			List<Camera> collection = cameraDao.list();
			request.setAttribute("cameras", collection);
			User user = userDao.get(name);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/camera.jsp");
			dispatcher.forward(request, response);
	}else if(action.equals("delete")) {			
		
			try {
				cameraDao.delete(id);
				List<Camera> collection = cameraDao.list();
				request.setAttribute("cameras", collection);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/administration.jsp");
				dispatcher.forward(request, response);
			} catch (CameraNotDeletedException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}		
	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		//String action = request.getParameter("action");
				
		Long id = null;
		
		if (request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
		
//		if(action.equals("cameraupdate")){
//			try {
//				long id1 = 2;
//				User user = userDao.get(id1);
//				Camera camera = new Camera();
//				camera.setName("Berlin");
//				camera.setPath("blubb");
//				cameraDao.save(camera);
//				List<Camera> collection = cameraDao.getCamerasOfUser(user);
//				collection.add(camera);
//				cameraDao.saveCamerasForUser(user, collection);
//				collection = cameraDao.getCamerasOfUser(user);
//				
//				request.setAttribute("cameras", collection);
//				
//				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/camera.jsp");
//				dispatcher.forward(request, response);
//			} catch (CameraNotDeletedException e) {
//				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
//				dispatcher.forward(request, response);
//			}
//		}
//		
		
		String name = request.getParameter("name");
		String path = request.getParameter("path");
				
		Camera camera = new Camera();		
		camera.setId(id);
		camera.setName(name);
		camera.setPath(path);		
		
		try {		
			cameraDao.save(camera);
			List<Camera> collection = cameraDao.list();
			request.setAttribute("cameras", collection);
			response.sendRedirect(request.getContextPath() + "/administration");
		}  catch (UserNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
