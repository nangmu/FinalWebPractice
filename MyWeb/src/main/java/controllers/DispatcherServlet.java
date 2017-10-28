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
		logger.debug("[[Dispatcher Start]] RequestURI:{}",url);
		Controller controller = requestMapping.getController(url);
		if(controller==null){
			logger.debug("[[잘못된 url로 접근하셨습니다. {}]]",url);
			return;
		}
		
		String result = controller.doService(req,resp);
		
		if(result==null){
			return;
		}
		if((result.trim()).startsWith("redirect:")){
			logger.debug("[[Dispatcher End]] Redirect:{}",result.substring(9));
			resp.sendRedirect(result.substring(9));
			return;
		}
		String path = "/WEB-INF"+result;
		logger.debug("[[Dispatcher End]] Forward:{}",result);
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}
}
