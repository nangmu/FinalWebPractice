package main.java.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import main.java.DAO.BoardDao_refactoring;
import main.java.VO.Board;
import main.java.utils.CommonUtils;

/*
 * 답변게시판 글 쓰기 처리 컨트롤러 - replyboard_Write글Controller
 */
public class ReplyBoard_BasicWrite implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoard_BasicWrite.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		//String saveDir = req.getServletContext().getRealPath(""); 배포시 적용
		String saveDir = "C:/uploadFile";
		int maxSize = 1024*1024*100;
		String encType="UTF-8";
		try {
			MultipartRequest multipartRequest =	new MultipartRequest(req,saveDir,maxSize,encType,new DefaultFileRenamePolicy());
			String title = multipartRequest.getParameter("title");
			String contents = multipartRequest.getParameter("contents");
			String id = multipartRequest.getParameter("id");
			String writer = multipartRequest.getParameter("writer");
			
			Enumeration<String> files = multipartRequest.getFileNames();
			String origin="",stored="";
			//파일이 여러개 전송될 경우, DB구조 변경하거나 처리 방식 변경해야 됨.
			//현재는 파일 1개만 전송된다 가정.
			while(files.hasMoreElements()){
				origin=""; stored="";
				String name = files.nextElement();
				File file = multipartRequest.getFile(name);
				logger.debug("em.nextElement:{}",name);
				
				logger.debug("filesystemName:{}",multipartRequest.getFilesystemName(name));
				
				long fileSize=0;
				if (file!=null) {
					fileSize=file.length();
					origin = file.getName();
					stored = CommonUtils.getRandomString();
					if (origin.lastIndexOf(".") != -1) {
						stored = stored + origin.substring(origin.lastIndexOf("."));
					}
					File newFile = new File(saveDir+File.separator+stored);
					
					if(file.renameTo(newFile)){
						logger.debug("****[파일 업로드 성공]****");
						logger.debug("원본 파일 이름:{}", origin);
						logger.debug("저장된 파일 이름:{}", stored);
						logger.debug("파일 크기:{}",fileSize);
						logger.debug("*********************");
					}else{
						logger.debug("[SERVER ERROR] renameTo() Failed.");
						// 실패 자주하면 FileMove 이용하자.
					}
				}
			}
			
			BoardDao_refactoring dao = BoardDao_refactoring.getInstance();
			
			Board board = new Board();
			board.setbGroup(dao.findUpdatedGroup()+1);
			board.setbLevel(0);
			board.setbOrder(0);
			board.setId(id);
			board.setWriter(writer);
			board.setTitle(title);
			board.setContents(contents);
			board.setViewCount(0);
			board.setOriginalFileName(origin);
			board.setStoredFileName(stored);
			
			dao.insertBoard(board);
			
			logger.debug("새로운 글이 등록되었습니다. {} , {}",board.getbNum(),board.getTitle());
		} catch (IOException e) {
			//트랜잭션은 언제 적용할까..
			logger.debug("[SERVER ERROR] 글 등록 실패");
		}
		return "redirect:/replyboard";
	}
	
}
