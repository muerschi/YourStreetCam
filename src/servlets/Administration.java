package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import model.User;

public class Administration extends HttpServlet{

	private static final long serialVersionUID = 1L;
	final UserDao userDao = DaoFactory.getInstance().getUserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		List<User> collection = userDao.list();
		request.setAttribute("users", collection);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/administration.jsp");
		dispatcher.forward(request, response);		
	}
	
	

}
