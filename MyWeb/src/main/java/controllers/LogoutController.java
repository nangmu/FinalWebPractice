package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutController implements Controller{
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		logger.debug("로그아웃 - {} ",req.getSession().getAttribute("userId"));
		req.getSession().invalidate();
		return "/home.jsp";
	}
}
