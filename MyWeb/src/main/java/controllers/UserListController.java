package main.java.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.UserDao;
import main.java.VO.Paging;
import main.java.VO.User;

public class UserListController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(UserListController.class);

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int pageSize = Paging.USER_DEFAULT_PAGE_SIZE; //5
		int groupSize = Paging.USER_DEFAULT_GROUP_SIZE; //3
		String reqPage = req.getParameter("reqPage");
		if(reqPage==null || reqPage.equals("")) reqPage = "1";
		int curPage = Integer.parseInt(reqPage);
		
		UserDao dao = new UserDao();
		ArrayList<User> userList = dao.getAllUsers();
		int totalRecords = userList.size();
		
		if(totalRecords==0){
			req.setAttribute("message", "사용자가 존재하지 않습니다.");
		}else{
			req.setAttribute("message", "전체 사용자: "+totalRecords+"명");
			Paging paging = new Paging(pageSize,groupSize,totalRecords,curPage);
			req.setAttribute("paging", paging);
			req.setAttribute("users", userList);
			logger.info("paging 초기화 작업 완료\n{}",paging.toString());
		}
		return "/userlist.jsp";
	}
}
