<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="main.java.VO.*,java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String userName = (String)request.getSession().getAttribute("userName"); %>
<% String userId = (String)request.getSession().getAttribute("userId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table><form action="/write_rb" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value= <%=userId %>/>
<input type="hidden" name="writer" value= <%=userName %>/>

<tr>
<td>제목:</td>
<td><input type="text" name="title" placeholder="제목을 입력하세요."/></td>
</tr>

<tr>
<td>내용:</td>
<td><textarea rows="10" cols="50" name="contents" placeholder="내용을 입력하세요."></textarea></td>
</tr>

<tr>
<td>파일:</td>
<td><input type="file" name="file"></td>
</tr>

<tr>
<td></td>
<td><input type="submit" value="등록"/><input type="reset" value="취소"/>
<input type="button" value="목록으로" onclick="window.location.replace('/replyboard')"/></td>
</tr>
</form></table>
</body>
</html>