package main.java.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;

public class WriteReplyBoardController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(WriteReplyBoardController.class);

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		
		String saveDir= new File("").getAbsolutePath();
		//String saveDir = req.getServletContext().getRealPath(""); 실제 배포할때 이 경로 쓸 것.
		int maxSize = 1024*1024*100;
		String encType="utf-8";
		MultipartRequest multipartRequest=null;
		String title = null; String contents=null;
		String id=null; String writer=null;	File file=null;
		try {
			multipartRequest = new MultipartRequest(req,saveDir,maxSize,encType, new DefaultFileRenamePolicy());
			
			file = multipartRequest.getFile("file");
			title = multipartRequest.getParameter("title");
			contents = multipartRequest.getParameter("contents");
			id = multipartRequest.getParameter("id");
			writer = multipartRequest.getParameter("writer");
			logger.debug("title:{}\ncontents:{}\nid:{}\nwriter:{}",title,contents,id,writer);
			if(file!=null){
			logger.debug("파일업로드 : {}",file.getName());
			//....
			}
			logger.debug("저장경로: {}", saveDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BoardDao_refactoring dao = new BoardDao_refactoring();
		
		int updatedGroupNum = dao.findUpdatedGroup();
		updatedGroupNum++;
		
		Board board = new Board();
		board.setbGroup(updatedGroupNum);board.setbLevel(0);board.setbOrder(0);
		board.setTitle(title);board.setContents(contents);
		board.setId(id);board.setWriter(writer); board.setViewCount(0);
		
		dao.insertBoard(board);
		logger.debug("새로운 글을 작성하셨습니다.");
		return "redirect:/replyboard";
	}
}
