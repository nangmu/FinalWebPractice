package main.java.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.UserDao_refactoring;
import main.java.VO.User;

public class LoginController implements Controller {
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("id");
		String password = req.getParameter("pw");
		
		if(userId==null || password ==null || userId.equals("")|| password.equals("")){
			logger.debug("[�α��� ����] - �Է��ϼ���.");
			return "/login.jsp";
		}

		UserDao_refactoring dao = new UserDao_refactoring();
		User user = null;
		user = dao.getUser(userId);
		if(user==null || !password.equals(user.getPw())){
			logger.debug("[�α��� ����] - ID�� �������� �ʰų� Password�� ��ġ���� �ʽ��ϴ�.");
			logger.debug("�Է����� : {},{}",userId,password);
			return "/login.jsp";
		}
		
		logger.debug("{} �α��μ���",userId);
		req.getSession().setAttribute("userId", userId);
		req.getSession().setAttribute("userName", user.getName());
		
		return "/home.jsp";
	}
}
