package main.java.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;
import main.java.VO.Paging;

/*
 * 답변게시판 보기 처리 컨트롤러 - replyboard_ShowBoardController + paging
 */
public class ReplyBoard_Show implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoard_Show.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		int pageSize = Paging.BOARD_DEFAULT_PAGE_SIZE; //5
		int groupSize = Paging.BOARD_DEFAULT_GROUP_SIZE; //3
		
		String reqPage = req.getParameter("reqPage");
		if(reqPage==null || reqPage.equals("")) reqPage = "1";
		int curPage = Integer.parseInt(reqPage);
		
		BoardDao_refactoring boardDao = BoardDao_refactoring.getInstance();
		//ArrayList<Board> boardList = dao.getAllBoards();
		int totalRecords = boardDao.getTotalCount();
		
		if(totalRecords==0){
			req.setAttribute("message", "글이 등록되지 않았습니다.");
			return "/replyboard.jsp";
		}
		int startingRecordNum = (curPage-1)*pageSize+1;
		ArrayList<Board> boardList = boardDao.getBoards(startingRecordNum, pageSize);
		Paging paging = new Paging(pageSize, groupSize, totalRecords, curPage);
		
		req.setAttribute("message", "전체 글: " + totalRecords + "개");
		req.setAttribute("paging", paging);
		req.setAttribute("boards", boardList);
		
		logger.debug("[답변 게시판] 화면처리에 필요한 정보를 담습니다.");
		return "/replyboard.jsp";
	}

}
