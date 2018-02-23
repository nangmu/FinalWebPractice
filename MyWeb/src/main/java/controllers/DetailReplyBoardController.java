package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;

/*
 * 답변게시판 글 상세보기 처리 게시판([전] replyboard.jsp에서 제목클릭시) replyboard_ShowDetailController
 */
public class DetailReplyBoardController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(DetailReplyBoardController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int parent_bNum = Integer.parseInt(req.getParameter("bNum"));

		BoardDao_refactoring boardDao = new BoardDao_refactoring();
		Board parent_board = boardDao.getBoard(parent_bNum);
		parent_board.setContents(boardDao.getContents(parent_bNum));
		
		boardDao.upViewCount(parent_bNum);
		logger.debug("parent_bNum:{}",parent_bNum);
		req.setAttribute("board", parent_board);
		return"/detail_replyboard.jsp";
	}

}
