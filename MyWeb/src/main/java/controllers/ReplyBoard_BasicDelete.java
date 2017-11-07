package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;

/*
 * 답변게시판 글삭제 처리 컨트롤러 replyboard_Delete글Controller
 */
public class ReplyBoard_BasicDelete implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoard_BasicDelete.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int bNum = Integer.parseInt(req.getParameter("bNum"));
		BoardDao_refactoring dao = BoardDao_refactoring.getInstance();
		dao.deleteBoard(bNum);
		logger.debug("[성공]{}번 글이 삭제 되었습니다.",bNum);
		
		return "redirect:/replyboard";
	}

}
