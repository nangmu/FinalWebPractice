package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController implements Controller{

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		return "/home.jsp";
	}
}
