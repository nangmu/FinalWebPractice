package main.java.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.BoardDao;
import main.java.VO.Board;

public class WriteReplyBoardController implements Controller{

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String contents = req.getParameter("contents");
		String id = req.getParameter("id");
		String writer = req.getParameter("writer");
		BoardDao dao = new BoardDao();
		
		int updatedGroupNum = 0;
		try {
			updatedGroupNum = dao.findUpdatedGroup();
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updatedGroupNum++;
		
		Board board = new Board();
		board.setbGroup(updatedGroupNum);board.setbLevel(0);board.setbOrder(0);
		board.setTitle(title);board.setContents(contents);
		board.setId(id);board.setWriter(writer); board.setViewCount(0);
		
		dao.insert(board);
		return "redirect:/replyboard";
	}
}
