package main.java.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao;
import main.java.DAO.UserDao;
import main.java.VO.Board;
import main.java.VO.Paging;
import main.java.VO.User;

public class BoardPagingProcess extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(BoardPagingProcess.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NamingException{
		//int pageSize = Paging.BOARD_DEFAULT_PAGE_SIZE; //5
		//int groupSize = Paging.BOARD_DEFAULT_GROUP_SIZE; //3
		int pageSize = 20;
		int  groupSize = 10;
		String reqPage = req.getParameter("reqPage");
		if(reqPage==null || reqPage.equals("")) reqPage = "1";
		int curPage = Integer.parseInt(reqPage);
		BoardDao dao = new BoardDao();
		ArrayList<Board> boardList = dao.getAllBoards();
		int totalRecords = boardList.size();
		if(totalRecords==0){
			req.setAttribute("message", "등록된 글이 없습니다.");
		}else{
			req.setAttribute("message", "전체 글 수: "+totalRecords+"개");
			Paging paging = new Paging(pageSize,groupSize,totalRecords,curPage);
			req.setAttribute("paging", paging);
			req.setAttribute("boards", boardList);
			log.info("paging초기화 작업 완료\n{}",paging.toString());
		}
		RequestDispatcher rd = req.getRequestDispatcher("/boardView.jsp");
		rd.forward(req, resp);
	}
	
}

