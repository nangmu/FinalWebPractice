package main.java.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.DAO.BoardDao_refactoring;
import main.java.DAO.FileDao;
import main.java.VO.Board;
import main.java.VO.MyFile;
import main.java.utils.CommonUtils;

/*
 * 답변게시판 글 쓰기 처리 컨트롤러 - replyboard_Write글Controller
 */
public class ReplyBoard_BasicWrite2 implements Controller {
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoard_BasicWrite2.class);
	
	@Override
	public String doService(HttpServletRequest req, HttpServletResponse resp) {
		//String saveDir = req.getServletContext().getRealPath(""); 배포시 적용
		String saveDir = "C:/uploadFile";
		BoardDao_refactoring boardDao = BoardDao_refactoring.getInstance();
		FileDao fileDao = FileDao.getInstance();
			try {
		        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
		        Hashtable<String, String> map = new Hashtable<>();
		        int count =0;
		        String title=null, contents=null, id = null, writer=null ;
		        for (FileItem item : items) {
		            if (item.isFormField()) {
		                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
		            	String fieldName = item.getFieldName();
		            	if(fieldName.equals("title")){
		            		title = CommonUtils.toUTF8(item.getString());
		            	}else if(fieldName.equals("contents")){
		            		contents = CommonUtils.toUTF8(item.getString());
		            	}else if(fieldName.equals("id")){
		            		id = CommonUtils.toUTF8(item.getString());
		            	}else if(fieldName.equals("writer")){
		            		writer = CommonUtils.toUTF8(item.getString());
		            	}
		            	logger.debug("title:{} contents:{} id:{} writer:{}",title,contents,id,writer);
		                // ... (do your job here)
		            } else {
		                // Process form file field (input type="file").
		            	String fieldName = item.getFieldName();
		                String originalFileName = FilenameUtils.getName(item.getName());
		                
		                // ... (do your job here)
		                InputStream fileContent = null;
		                String storedFileName = null;
		                String extension = null;
		                if(originalFileName!=null && !("").equals(originalFileName)){
		                	storedFileName = CommonUtils.getRandomString();
		                	extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		                	fileContent = item.getInputStream();
		                	map.put(storedFileName+extension,originalFileName);

		                	byte[] buffer = new byte[1024];
			                int len;
			                File file = new File(saveDir);
			                if(file==null || !file.isDirectory()){
			                	file.mkdir();
			                }
			                File storedFile = new File(saveDir+File.separator+storedFileName+extension);
			                FileOutputStream fos = new FileOutputStream(storedFile);
			                while((len=fileContent.read(buffer))!=-1){
			                	fos.write(buffer,0,len);
			                }
			                fos.flush();
			                fos.close();
			                fileContent.close();
		                }
		                logger.debug("{}>>>{}({}) 다운 완료",++count,originalFileName,storedFileName); 
		               }
		        }
		        int bNum = boardDao.findUpdatedNum()+1;
            	Board board = new Board();
    			board.setbNum(bNum);
    			board.setbGroup(boardDao.findUpdatedGroup()+1);
    			board.setbLevel(0);
    			board.setbOrder(0);
    			board.setId(id);
    			board.setWriter(writer);
    			board.setTitle(title);
    			board.setContents(contents);
    			board.setViewCount(0);
    			boardDao.insertBoard(board);
    			
    			Enumeration<String> keys = map.keys();
    			while(keys.hasMoreElements()){
    				String key = keys.nextElement();
    				MyFile fileVO= new MyFile(bNum,key,map.get(key));
    				fileDao.insertFile(fileVO);
    			}
		    } catch (FileUploadException e) {
		    	e.printStackTrace();
		    }catch(IOException ioe){
		    	ioe.printStackTrace();
		    }
		return "redirect:/replyboard";
	}
	
}
