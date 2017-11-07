package main.java.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.FileDao;

/*
 * 답변게시판 파일다운 처리 게시판 - replyboard_FileDownController
 */
public class FileDownController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(FileDownController.class);

	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		//C:/uploadFile/(오늘날짜(년월일)-나중에)/랜덤파일이름/업로드파일들
		String storedFileName = req.getParameter("filename");
		FileDao dao = FileDao.getInstance();
		String originalFileName = dao.getOriginalFileName(storedFileName);
		int BUFSIZE = 4096;
		String filepath = "C:\\uploadFile" + File.separator + storedFileName;
		File file = new File(filepath);
		
		if (!file.exists()) {
			logger.debug("파일이 존재하지 않습니다. {}", filepath);
			return null;
		}
		int length = 0;
		byte[] byteBuffer = new byte[BUFSIZE];
		resp.setContentType("application/x-msdownload");
		String encodingFileName = null;
		//??????????????????????????????
		try {
			encodingFileName = URLEncoder.encode(originalFileName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + encodingFileName + "\"");
		try(FileInputStream fs = new FileInputStream(file);
			   OutputStream os = resp.getOutputStream()){
			
			while ((length = fs.read(byteBuffer)) != -1) {
				os.write(byteBuffer, 0, length);
			}
			logger.debug("파일 전송 성공 - {}",originalFileName);
		} catch (IOException ioe) {
			logger.debug("[SERVER ERROR] 파일 전송 실패");
		}
		return null;
	}
}
