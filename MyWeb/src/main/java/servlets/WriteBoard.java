package main.java.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao;
import main.java.VO.Board;

public class WriteBoard extends HttpServlet{
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(WriteBoard.class);
	
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
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NamingException {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String id = req.getParameter("id");
		String writer = req.getParameter("writer");
		logger.debug("전달받은 값 >> title:{}, contents{}",title,contents);
		BoardDao dao = new BoardDao();
		int updatedGroupNum=dao.findUpdatedGroup();
		updatedGroupNum++;
		
		Board board = new Board();
		board.setbGroup(updatedGroupNum);board.setbLevel(0);board.setbOrder(0);
		board.setTitle(title);board.setContents(contents);
		board.setId(id);board.setWriter(writer); board.setViewCount(0);
		
		dao.insert(board);
		
		resp.sendRedirect("/boardPagingProcess");
	}
}
