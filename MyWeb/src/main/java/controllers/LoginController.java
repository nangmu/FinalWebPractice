package main.java.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.UserDao_refactoring;
import main.java.VO.User;

/*
 * 로그인 처리 게시판 LoginController - javascript로 검증 처리 할 것.
 */
public class LoginController implements Controller {
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userId = req.getParameter("id");
		String password = req.getParameter("pw");
		
		if(userId==null || password ==null || userId.equals("")|| password.equals("")){
			logger.debug("[입력에러] - 입력하세요.");
			return "/login.jsp";
		}

		UserDao_refactoring dao = new UserDao_refactoring();
		User user = null;
		user = dao.getUser(userId);
		if(user==null || !password.equals(user.getPw())){
			logger.debug("[입력에러] - ID 또는 PASSWORD를 잘못 입력하셨습니다.");
			return "/login.jsp";
		}
		
		logger.debug("로그인 성공 - 아이디:{} 이름:{} ",userId,user.getName());
		req.getSession().setAttribute("userId", userId);
		req.getSession().setAttribute("userName", user.getName());
		
		return "/home.jsp";
	}
}
