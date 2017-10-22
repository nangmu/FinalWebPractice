package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;

public class DeleteReplyBoardController implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(DeleteReplyBoardController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int bNum = Integer.parseInt(req.getParameter("bNum"));
		BoardDao_refactoring dao = new BoardDao_refactoring();
		dao.deleteBoard(bNum);
		logger.debug("{}번 게시글이 삭제되었습니다.",bNum);
		
		return "redirect:/replyboard";
	}

}
