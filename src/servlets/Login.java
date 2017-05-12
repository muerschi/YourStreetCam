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

import dao.DaoFactory;
import dao.UserDao;
import model.PasswordHash;
import model.User;



public class Login extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	final UserDao userDao = DaoFactory.getInstance().getUserDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
		dispatcher.forward(request, response);		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
						
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		
		User user = userDao.get(username);
		
		
		try {		
			if(user != null && user.passwordIsValid(password)){
				request.setAttribute("user", user);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/piclist.jsp");
				dispatcher.forward(request, response);	
			}else{
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/wrongCredentials.jsp");
				dispatcher.forward(request, response);
			}
			}  catch (Exception e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
