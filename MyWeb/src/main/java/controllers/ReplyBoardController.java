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

/*
 * 답변게시판 보기 처리 컨트롤러 - replyboard_ShowBoardController + paging
 */
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
		//상위 Num개만 가져오게 할 수도 있다. dao.getTopBoards(int Num);
		ArrayList<Board> boardList = dao.getAllBoards();
		if(boardList==null){
			req.setAttribute("message", "글이 등록되지 않았습니다.");
		}else{
			int totalRecords = boardList.size();
			req.setAttribute("message", "전체 글: "+totalRecords+"개");
			Paging paging = new Paging(pageSize,groupSize,totalRecords,curPage);
			req.setAttribute("paging", paging);
			req.setAttribute("boards", boardList);
		}
		logger.debug("[답변 게시판] 화면처리에 필요한 정보를 담습니다.");
		return "/replyboard.jsp";
	}

}
