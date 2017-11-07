package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;

/*
 * 답변게시판 답변등록 처리 컨트롤러 replyboard_AnswerRegistController
 */
public class ReplyBoard_AnswerWrite implements Controller {
	/** The usual Logger.*/
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoard_AnswerWrite.class);
	
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
		board.setOriginalFileName("");
		board.setStoredFileName("");
		BoardDao_refactoring dao = BoardDao_refactoring.getInstance();
		dao.upOrder((Integer.parseInt(req.getParameter("parent_bGroup"))),
						(Integer.parseInt(req.getParameter("parent_bOrder"))));
		
		dao.insertBoard(board);
		logger.debug("[성공] 답변 등록");
		return "redirect:/replyboard";
	}

}
