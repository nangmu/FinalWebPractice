package main.java.controllers;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;
import main.java.VO.Paging;

public class ReplyBoardController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoardController.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int pageSize = Paging.BOARD_DEFAULT_PAGE_SIZE; //5
		int groupSize = Paging.BOARD_DEFAULT_GROUP_SIZE; //3
		String reqPage = req.getParameter("reqPage");
		if(reqPage==null || reqPage.equals("")) reqPage = "1";
		int curPage = Integer.parseInt(reqPage);
		BoardDao_refactoring dao = new BoardDao_refactoring();
		ArrayList<Board> boardList = dao.getAllBoards();
		int totalRecords = boardList.size();
		if(totalRecords==0){
			req.setAttribute("message", "��ϵ� ���� �����ϴ�.");
		}else{
			req.setAttribute("message", "��ü �� ��: "+totalRecords+"��");
			Paging paging = new Paging(pageSize,groupSize,totalRecords,curPage);
			req.setAttribute("paging", paging);
			req.setAttribute("boards", boardList);
			logger.info("paging�ʱ�ȭ �۾� �Ϸ�\n{}",paging.toString());
		}
		return "/replyboard.jsp";
	}

}
