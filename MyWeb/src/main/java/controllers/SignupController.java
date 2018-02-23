package main.java.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.UserDao_refactoring;
import main.java.VO.User;

/*
 * 회원가입 처리 컨트롤러 SignupController- javascript 검증 필요
 */
public class SignupController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(SignupController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		String userId = req.getParameter("id");
		String password= req.getParameter("pw");
		String userName = req.getParameter("name");
		logger.debug("입력받은 이름:{}",userName);
		
		if(userId==null || password==null || userName==null ||
				userId.equals("") || password.equals("")|| userName.equals("")){
			logger.debug("[입력에러] - 입력하세요");
			return "/signup.jsp";
		}
		UserDao_refactoring dao = new UserDao_refactoring();
		User check = dao.getUser(userId);
		
		if (check != null) {
			logger.debug("[아이디 에러] - 중복");
			return "/signup.jsp";
		}

		if(password.length()<4){
			logger.debug("[비밀번호 에러] - 4자리 이상 입력해주세요");
			return "/signup.jsp";
		}
		User user = new User(userId,password,userName);
		dao.insertUser(user);
		
		req.getSession().setAttribute("userId", userId);
		req.getSession().setAttribute("userName", userName);
		
		logger.debug("회원가입 성공 - {} , {}",userId, userName);
		return "redirect:/";
	}

}
