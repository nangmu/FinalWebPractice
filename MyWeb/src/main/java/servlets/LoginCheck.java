package main.java.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.UserDao;
import main.java.VO.User;


public class LoginCheck extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(LoginCheck.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(req,resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NamingException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String path;
		UserDao userDao = new UserDao();
		User user = userDao.getUser(id);
		if(user==null){
			path="/errorView.jsp";
			req.setAttribute("errorMessage","ID����");
			log.info("ID ����ġ");
		}else{
			if(!pw.equals(user.getPw())){
				path="/errorView.jsp";
				req.setAttribute("errorMessage","PW����");
				log.info("PW ����ġ");
			}else{
				req.setAttribute("users",userDao.getAllUsers());
				path = "/userView.jsp";
				log.info("�α��� ����");
			}
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/userPagingProcess");
		rd.forward(req, resp);
	}
	
	
}
