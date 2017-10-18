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

public class Register extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(Register.class);

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
		String path;
		User user;
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		if(id==null || id.equals("") || pw==null || pw.equals("")
				|| name==null || name.equals("")){
			path="/errorView.jsp";
			req.setAttribute("errorMessage", "가입실패");
			log.info("가입실패");
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
			return;
		}
			path="/userPagingProcess";
			UserDao dao = new UserDao();
			user = new User(id,pw,name);
			dao.insert(user);
			
			log.info("가입성공");
			resp.sendRedirect(path);
	}
	

}
