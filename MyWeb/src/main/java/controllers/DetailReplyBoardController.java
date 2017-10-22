package main.java.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;

public class DetailReplyBoardController implements Controller{

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int parent_bNum = Integer.parseInt(req.getParameter("bNum"));

		BoardDao_refactoring boardDao = new BoardDao_refactoring();
		Board parent_board = boardDao.getBoard(parent_bNum);
		boardDao.upViewCount(parent_bNum);
		parent_board.setContents(boardDao.getContents(parent_bNum));
		req.setAttribute("board", parent_board);

		return"/detail_replyboard.jsp";
	}

}
