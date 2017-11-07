package main.java.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;
import main.java.VO.Paging;

public class ReplyBoard_Search implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoard_Show.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		String search_key = req.getParameter("search_key");
		String search_value = req.getParameter("search_value");
		
		if(search_key == null || search_value==null){
			search_key =""; search_value="";
		}

		int pageSize = Paging.BOARD_DEFAULT_PAGE_SIZE; // 5
		int groupSize = Paging.BOARD_DEFAULT_GROUP_SIZE; // 3

		String reqPage = req.getParameter("reqPage");
		if (reqPage == null || reqPage.equals(""))
			reqPage = "1";
		int curPage = Integer.parseInt(reqPage);

		BoardDao_refactoring dao = BoardDao_refactoring.getInstance();
		 ArrayList<Board> boardList = new ArrayList<>();
		ArrayList<Board> list = dao.searchBoards(search_key, search_value);
		logger.debug("search_key:{}, search_value:{}",search_key,search_value);
		int totalRecords = list.size();
		logger.debug("검색레코드수:{}",totalRecords);

		req.setAttribute("search_key",search_key);
		req.setAttribute("search_value", search_value);
		if (totalRecords == 0) {
			req.setAttribute("message", "글이 등록되지 않았습니다.");
			return "/replyboard.jsp";
		}
		int startRecordIdx = (curPage - 1) * pageSize;
		int endRecordIdx = curPage*pageSize -1;
		if(endRecordIdx >list.size()-1){
			endRecordIdx = list.size()-1;
		}
		for(int i=startRecordIdx;i<=endRecordIdx;i++){
			boardList.add(list.get(i));
		}
		Paging paging = new Paging(pageSize, groupSize, totalRecords, curPage);

		req.setAttribute("message", "전체 글: " + totalRecords + "개");
		req.setAttribute("paging", paging);
		req.setAttribute("boards", boardList);

		logger.debug("[답변 게시판] 화면처리에 필요한 정보를 담습니다.");
		return "/replyboard.jsp";
	}

}
