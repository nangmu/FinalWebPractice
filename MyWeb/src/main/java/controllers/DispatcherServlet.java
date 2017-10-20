package main.java.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * HTTP요청 중 GET,POST에 대해서만 서비스 하므로
 */
@WebServlet(urlPatterns="/", loadOnStartup=1)
public class DispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
	RequestMapping requestMapping;
	
	@Override
	public void init() throws ServletException {
		requestMapping = new RequestMapping();
		requestMapping.init();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req,resp);
	}
	
	private void doService(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String url = req.getRequestURI();
		logger.debug("Dispatcher Start!!! RequestURI:{}",url);
		logger.debug("혹시?:{}",req.getServletPath());
		Controller controller = requestMapping.getController(url);
		if(controller==null){
			logger.debug("올바르지 않은 경로입니다.");
			return;
		}
		
		String result = controller.doService(req,resp);
		//사용자에게 결과를 알려줘야 함--> error? success? ing~?
		if((result.trim()).startsWith("redirect:")){
			logger.debug("{}로 이동",result);
			resp.sendRedirect(result.substring(9));
			return;
		}
		logger.debug("paging{}",req.getAttribute("paging"));
		String path = "/WEB-INF"+result;
		logger.debug("{}로 이동",path);
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
}
