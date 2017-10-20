<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import= "main.java.VO.Board" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Board board = (Board)request.getAttribute("board");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판 상세 보기</title>
</head>
<body>
<input type="button" name="button" value="목록으로" onclick="window.location.replace('/replyboard')"/>
<input type="button" name="button" value="답변하기" onclick="window.location.replace('/answer_rb_Form?bGroup=<%=board.getbGroup()%>&bLevel=<%=board.getbLevel()%>&bOrder=<%=board.getbOrder()%>')"/>

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