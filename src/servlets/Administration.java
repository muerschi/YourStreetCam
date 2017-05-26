package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;

import dao.UserDao;
import dao.CameraDao;

import dao.ImageDao;
import model.User;

import model.Image;
import model.Camera;

public class Administration extends HttpServlet{

	private static final long serialVersionUID = 1L;
	final UserDao userDao = DaoFactory.getInstance().getUserDao();
	final ImageDao imageDao = DaoFactory.getInstance().getImageDao();
	final CameraDao cameraDao = DaoFactory.getInstance().getCameraDao();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		// Testweises speichern von Bildern
		
//		Image image = new Image();
//		image.setCameraId(new Long(0));
//		image.setDate(new Date());
//		image.setPath("C:/test123/2017/Wen_May_23/WDF_1982495.jpg");
//		imageDao.save(image);
//		image.setPath("C:/test123/2017/Wen_May_24/WDF_1982448.jpg");
//		imageDao.save(image);
//		image.setPath("C:/test123/2017/Wen_May_24/thumb-1920-669633.jpg");
//		imageDao.save(image);
//		image.setPath("C:/test123/2017/Tue_May_23/IMG_46959.jpg");
//		image.setCameraId(new Long(6));
//		imageDao.save(image);
//		image.setDate(new Date());
//		image.setPath("C:/test123/2017/Tue_May_23/IMG_46934.jpg");
//		imageDao.save(image);
//		image.setPath("C:/test123/2017/Tue_May_23/IMG_46931.jpg");
//		imageDao.save(image);
//		image.setDate(new Date());
//		image.setPath("C:/test123/2017/Tue_May_23/18_08_00.jpg");
//		imageDao.save(image);
		
		
		
		List<User> collection = userDao.list();
		request.setAttribute("users", collection);
		List<Camera> collection2 = cameraDao.list();
		request.setAttribute("cameras", collection2);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/administration.jsp");
		dispatcher.forward(request, response);
	}
	
	

}
