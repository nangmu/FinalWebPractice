package main.java.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.DAO.BoardDao_refactoring;

public class ReplyBoard_UpViewCount implements Controller{

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		String bNum = req.getParameter("bNum");
		
		BoardDao_refactoring boardDao = BoardDao_refactoring.getInstance();
		boardDao.upViewCount(Integer.parseInt(bNum));
		
		return "redirect:/detail_rb?bNum="+bNum;
	}

}
