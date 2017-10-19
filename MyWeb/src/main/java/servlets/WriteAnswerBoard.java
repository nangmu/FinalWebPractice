package main.java.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao;
import main.java.VO.Board;

@WebServlet(urlPatterns="/writeAnswerBoard")
public class WriteAnswerBoard extends HttpServlet{
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(WriteBoard.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			process(req,resp);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NamingException {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String id = req.getParameter("id");
		String writer = req.getParameter("writer");
		logger.debug("전달받은 값 >> title:{}, contents{}",title,contents);
		
		Board board = new Board();
		board.setbGroup(Integer.parseInt(req.getParameter("parent_bGroup")));
		board.setbLevel((Integer.parseInt(req.getParameter("parent_bLevel"))+1));
		board.setbOrder((Integer.parseInt(req.getParameter("parent_bOrder"))+1));
		board.setTitle(title);board.setContents(contents);
		board.setId(id);board.setWriter(writer);
		
		BoardDao dao = new BoardDao();
		dao.changeOrder((Integer.parseInt(req.getParameter("parent_bGroup"))),
				(Integer.parseInt(req.getParameter("parent_bOrder"))));
		dao.insert(board);
		
		resp.sendRedirect("/boardPagingProcess");
	}
}
