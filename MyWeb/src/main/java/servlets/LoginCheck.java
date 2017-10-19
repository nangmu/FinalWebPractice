package main.java.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginCheck extends HttpServlet{
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(LoginCheck.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		logger.info("로그인 검증중........");
		String id = (String)req.getSession().getAttribute("login_id");
		
		String path;
		if(id==null){
			logger.info("로그인 실패. 로그인 페이지로 이동합니다.");
			path = "/loginView.jsp";
		}else{
			path = (String)req.getParameter("path");
			logger.info("로그인 인증 되셨습니다. 요청 주소 : {}",path);
			
		}
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req,resp);
	}
}
