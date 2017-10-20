package main.java.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.BoardDao;
import main.java.VO.Board;

public class DetailReplyBoardController implements Controller{

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int parent_bNum = Integer.parseInt(req.getParameter("bNum"));

		BoardDao boardDao = new BoardDao();
		Board parent_board = boardDao.getBoard(parent_bNum);
		try {
			boardDao.upViewCount(parent_bNum);
			parent_board.setContents(boardDao.getContents(parent_bNum));
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("board", parent_board);

		return"/detail_replyboard.jsp";
	}

}
