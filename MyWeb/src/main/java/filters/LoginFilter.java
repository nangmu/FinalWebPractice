package main.java.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.controllers.RequestMapping;

public class LoginFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		
		String userId = (String)session.getAttribute("userId");
		String[] urls = {"/userlist","/answer_rb_Form","/write_rb_Form","/write_comment"};
		boolean accessFlag = false;
		
		String url = request.getRequestURI();
		for(String s: urls){
			if(url.indexOf(s)!=-1){
				accessFlag = true;
				break;
			}
		}
		if(userId!=null && (url.equals("/loginForm")||url.equals("/login"))){
			logger.debug("[접근에러] - Already logined. Go > home.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
			rd.forward(request, response);
			return;
		}
		if(userId==null && accessFlag){
			logger.debug("[접근에러] - Need to be logined. Go > login.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(request, response);
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
	}
}
