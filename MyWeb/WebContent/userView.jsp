<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="main.java.VO.*,java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String message = (String)request.getAttribute("message");
ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
Paging paging = (Paging)request.getAttribute("paging");
int curPage = paging.getCurPage();
int pSize = paging.getPageSize();
int gSize = paging.getGroupSize();
int totalPage = paging.getTotalPage();
int curGNum = paging.getCurGroupNum();
int page_nav_start = paging.getStartPageNum();
int page_nav_end = paging.getEndPageNum();
int record_start = paging.getStartingRecordIdx();
int record_end = paging.getEndingRecordIdx();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%= message %><br><br>
<table>
<tr>
<th>아이디</th>
<th>이름</th>
</tr>

<% for(int i=record_start;i<=record_end;i++){ %>
<tr>
<td><%= users.get(i).getId() %></td>
<td><%= users.get(i).getName() %></td>
</tr>
<%} %>
</table>
<a href="/userPagingProcess?reqPage=1">[<<]</a>
<% int before = curPage-1; if(before<1) before=1;
   int next = curPage+1; if(next>totalPage) next=totalPage;
%>
<a href="/userPagingProcess?reqPage=<%=before %>">[<]</a>
<% for(int i=page_nav_start;i<=page_nav_end;i++){
	String nav_page_num=String.valueOf(i);
if(i==curPage){nav_page_num= "["+nav_page_num+"]";}%>

<a href="/userPagingProcess?reqPage=<%=i %>"><%=nav_page_num%></a>
<%} %>
 <a href="/userPagingProcess?reqPage=<%=next %>">[>]</a>
 <a href="/userPagingProcess?reqPage=<%=totalPage %>">[>>]</a>
</body>
</html>