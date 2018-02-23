<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import= "main.java.VO.Board" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Board board = (Board)request.getAttribute("board");
if(board==null) System.out.println("null이네..................");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>게시판 상세 보기</title>
</head>
<body>
<input type="button" name="button" value="목록으로" onclick="window.location.replace('/replyboard')"/>
<input type="button" name="button" value="답변하기" onclick="window.location.replace('/answer_rb_Form?bGroup=<%=board.getbGroup()%>&bLevel=<%=board.getbLevel()%>&bOrder=<%=board.getbOrder()%>')"/>
<input type="button" name="button" value="삭제하기" onclick="window.location.replace('/deleteReplyBoard?bNum=<%=board.getbNum()%>')"/>
<%if(!board.getOriginalFileName().equals("")){ %>
<input type="button" name="button" value="<%=board.getOriginalFileName()%>" onclick="window.location.replace('/filedown?filename=<%=board.getStoredFileName() %>')"/>
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
</body>
</html>