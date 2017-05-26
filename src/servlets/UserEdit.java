/*
 * Created on 22.11.2004
 */
package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.CameraDao;
import dao.DaoFactory;
import exception.UserNotDeletedException;
import exception.UserNotFoundException;
import exception.UserNotSavedException;
import model.Camera;
import model.PasswordHash;
import model.User;

public class UserEdit extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;

	final UserDao userDao = DaoFactory.getInstance().getUserDao();
	final CameraDao cameraDao = DaoFactory.getInstance().getCameraDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = request.getParameter("action");
		
		if (action == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
				
		Long id = null;
		
		// userDao.getbyId funktioniert nicht...
		if (request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
				
		if(action.equals("add")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/add.jsp");
			dispatcher.forward(request, response);		
		} else if(action.equals("edit")) {			
			try {
				User user = userDao.get(id);
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/edit.jsp");
				dispatcher.forward(request, response);
			} catch (UserNotFoundException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}				
		} else if(action.equals("delete")) {			
			try {
				userDao.delete(id);
				response.sendRedirect(request.getContextPath() + "/administration");
			} catch (UserNotDeletedException e) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
				dispatcher.forward(request, response);
			}
		}		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		Long id = null;
		
		if(request.getParameter("id") != null) {
			id = Long.valueOf(request.getParameter("id"));
		}
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
				
		User user = new User();		
		user.setId(id);
		user.setName(name);
		String hash;
		
		try {
			hash = PasswordHash.createHash(password);
			user.setPassword(hash);	
		} catch (NoSuchAlgorithmException e1) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
			e1.printStackTrace();
		}
			
		user.setRole(role);
		
		try {		
			userDao.save(user);
			List<Camera> collection = cameraDao.list();
			request.setAttribute("cameras", collection);
			response.sendRedirect(request.getContextPath() + "/administration");
		}  catch (UserNotSavedException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}
}
