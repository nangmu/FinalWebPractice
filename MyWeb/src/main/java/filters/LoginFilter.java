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
		logger.debug("userId:{}",userId);
		String[] urls = {"/userlist","/answer_rb_Form","/write_rb_Form"};
		boolean accessFlag = false;
		
		String url = request.getRequestURI();
		for(String s: urls){
			if(url.indexOf(s)!=-1){
				accessFlag = true;
				break;
			}
		}
		//�α��� �ߴµ� �α��� �������� ���ٽ�(UI���� ��ư�� ���ֵ� ���� ������ �����ϱ⶧��.)
		if(userId!=null && url.equals("/loginForm")){
			logger.debug("[���� ����] - �̹� �α��� �Ϸ��. home.jsp�� �̵���...");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(request, response);
			return;
		}
		if(userId==null && accessFlag){
			logger.debug("[���� ����] - �α��ν� ���� ������. login.jsp�� �̵���...");
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
