<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<table><form action="/answer_rb" method="post">
<input type="hidden" name="id" value= <%=userId %>/>
<input type="hidden" name="writer" value= <%=userName %>/>
<input type="hidden" name="parent_bGroup" value=<%=request.getParameter("bGroup") %> />
<input type="hidden" name="parent_bLevel" value=<%=request.getParameter("bLevel") %> />
<input type="hidden" name="parent_bOrder" value=<%=request.getParameter("bOrder") %> />
<tr>
<td>����:</td>
<td><input type="text" name="title" placeholder="������ �Է��ϼ���."/></td>
</tr>

<tr>
<td>����:</td>
<td><textarea rows="10" cols="50" name="contents" placeholder="������ �Է��ϼ���."></textarea></td>
</tr>

<tr>
<td></td>
<td><input type="submit" value="���"/><input type="reset" value="���"/></td>
</tr>
</form></table>
</body>
</html>