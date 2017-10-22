package main.java.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.UserDao_refactoring;
import main.java.VO.User;

public class SignupController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(SignupController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("id");
		String password= req.getParameter("pw");
		String userName = req.getParameter("name");
		
		if(userId==null || password==null || userName==null ||
				userId.equals("") || password.equals("")|| userName.equals("")){
			logger.debug("[가입에러] - 입력하세요");
			return "/signup.jsp";
		}
		UserDao_refactoring dao = new UserDao_refactoring();
		User check = null;
		check = dao.getUser(userId);
		if (check != null) {
			logger.debug("[가입에러] - 아이디존재");
			return "/signup.jsp";
		}

		if(password.length()<6){
			logger.debug("[가입에러] - 패스워드를 6자리 이상 입력하세요.");
			return "/signup.jsp";
		}
		User user = new User(userId,password,userName);
		dao.insertUser(user);
		
		req.getSession().setAttribute("userId", userId);
		req.getSession().setAttribute("userName", userName);
		
		logger.debug("[가입성공] - {} , {}",userId, userName);
		return "redirect:/";
	}

}
