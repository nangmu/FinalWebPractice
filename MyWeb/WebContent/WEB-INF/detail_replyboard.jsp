<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
<%@ page import= "main.java.VO.MyFile,main.java.VO.Board,main.java.VO.Comment, java.util.*" %>
<!DOCTYPE html>
<%
Board board = (Board)request.getAttribute("board");
ArrayList<Comment> commentList = (ArrayList)request.getAttribute("comment");
ArrayList<MyFile> fileList = (ArrayList)request.getAttribute("file");
%>

<html>
<head>
<title>게시판 상세 보기</title>
</head>
<body>
<input type="button" name="button" value="목록으로" onclick="window.location.replace('/replyboard')"/>
<input type="button" name="button" value="답변하기" onclick="window.location.replace('/answer_rb_Form?bGroup=<%=board.getbGroup()%>&bLevel=<%=board.getbLevel()%>&bOrder=<%=board.getbOrder()%>')"/>
<input type="button" name="button" value="삭제하기" onclick="window.location.replace('/deleteReplyBoard?bNum=<%=board.getbNum()%>')"/>
<%for(int i=0;i<fileList.size();i++){ %>
<input type="button" name="button" value="<%= fileList.get(i).getOriginalFileName() %>" onclick="window.location.replace('/filedown?filename=<%=fileList.get(i).getStoredFileName() %>')"/>
<%}%>

<table>
<tr>
	<td>제목:</td>
	<td><%=board.getTitle() %></td>
</tr>
<tr>
	<td>내용:</td>
	<td><%=board.getContents() %></td>
</tr>
</table>

<form action="/write_comment" method="post">
<textarea rows="5" cols="5" name="contents"></textarea>
<input type="hidden" name="bNum" value="<%=board.getbNum() %>">
<input type="hidden" name="writer" value="<%=board.getWriter() %>">
<input type="submit" value="댓글쓰기">&nbsp;<input type="reset" value="취소">
</form>

<table>
<%for(int i=0;i<commentList.size();i++){ 
	Comment comment = commentList.get(i);
%>
<tr>
	<td>작성자:<%=comment.getWriter() %></td>
	<td><%=comment.getContents() %></td>
</tr>	
<%	
}
%>
</table>
</body>
</html>