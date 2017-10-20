package main.java.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.BoardDao;
import main.java.VO.Board;

public class AnswerReplyBoardController implements Controller {

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String id = req.getParameter("id");
		String writer = req.getParameter("writer");
		
		Board board = new Board();
		board.setbGroup(Integer.parseInt(req.getParameter("parent_bGroup")));
		board.setbLevel((Integer.parseInt(req.getParameter("parent_bLevel"))+1));
		board.setbOrder((Integer.parseInt(req.getParameter("parent_bOrder"))+1));
		board.setTitle(title);board.setContents(contents);
		board.setId(id);board.setWriter(writer);
		
		BoardDao dao = new BoardDao();
		try {
			dao.changeOrder((Integer.parseInt(req.getParameter("parent_bGroup"))),
					(Integer.parseInt(req.getParameter("parent_bOrder"))));
		} catch (NumberFormatException | SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(board);
		
		return "redirect:/replyboard";
	}

}
