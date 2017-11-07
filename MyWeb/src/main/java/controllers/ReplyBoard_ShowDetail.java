package main.java.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;
import main.java.DAO.CommentDao;
import main.java.DAO.FileDao;
import main.java.VO.Board;
import main.java.VO.Comment;
import main.java.VO.MyFile;

/*
 * 답변게시판 글 상세보기 처리 게시판([전] replyboard.jsp에서 제목클릭시) replyboard_ShowDetailController
 */
public class ReplyBoard_ShowDetail implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoard_ShowDetail.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int parent_bNum = Integer.parseInt(req.getParameter("bNum"));

		BoardDao_refactoring boardDao = BoardDao_refactoring.getInstance();
		Board parent_board = boardDao.getBoard(parent_bNum);
		parent_board.setContents(boardDao.getContents(parent_bNum));
		
		CommentDao commentDao = CommentDao.getInstance();
		ArrayList<Comment> commentList = commentDao.getComments(parent_bNum);
		
		FileDao fileDao = FileDao.getInstance();
		ArrayList<MyFile> fileList = fileDao.getFiles(parent_bNum);
		
		req.setAttribute("board", parent_board);
		req.setAttribute("comment", commentList);
		req.setAttribute("file", fileList);
		
		logger.debug("parent_bNum:{}",parent_bNum);
		return"/detail_replyboard.jsp";
	}

}
