package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.CommentDao;
import main.java.VO.Comment;

public class ReplyBoard_CommentWrite implements Controller{

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int bNum = Integer.parseInt(req.getParameter("bNum"));
		String contents = req.getParameter("contents");
		String writer = (String)req.getSession().getAttribute("userName");
		
		Comment comment = new Comment(bNum, contents, writer);
		CommentDao dao = CommentDao.getInstance();
		dao.insertComment(comment);
		return "redirect:/detail_rb?bNum="+bNum;
	}

}
