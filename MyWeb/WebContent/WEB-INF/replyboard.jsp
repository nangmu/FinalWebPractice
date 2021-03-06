<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="main.java.VO.*,java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String message = (String)request.getAttribute("message");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%= message %>&nbsp&nbsp&nbsp<a href="/write_rb_Form">글쓰기</a>
&nbsp<input type="button" value="메인페이지로" onclick="window.location.replace('/')"/>
<table>
<tr>
<th>글번호</th>
<th>제목</th>
<th>작성자</th>
<th>조회수</th>
</tr>
<%

ArrayList<Board> boards = (ArrayList<Board>)request.getAttribute("boards");
if(boards==null) return;
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
<% for(int i=record_start;i<=record_end;i++){ 
Board board = boards.get(i);
%>
<tr>
<td><%= board.getbNum() %></td>
<td>
<%for(int j=0;j<board.getbLevel();j++){ out.print("-");} %>
<a href="/detail_rb?bNum=<%=board.getbNum()%>"><%= board.getTitle() %></a></td>
<td><%= board.getWriter() %></td>
<td><%= board.getViewCount() %></td>
</tr>
<%} %>
</table>
<a href="/replyboard?reqPage=1">[<<]</a>
<% int before = curPage-1; if(before<1) before=1;
   int next = curPage+1; if(next>totalPage) next=totalPage;
%>
<a href="/replyboard?reqPage=<%=before %>">[<]</a>
<% for(int i=page_nav_start;i<=page_nav_end;i++){
	String nav_page_num=String.valueOf(i);
if(i==curPage){nav_page_num= "["+nav_page_num+"]";}%>

<a href="/replyboard?reqPage=<%=i %>"><%=nav_page_num%></a>
<%} %>
 <a href="/replyboard?reqPage=<%=next %>">[>]</a>
 <a href="/replyboard?reqPage=<%=totalPage %>">[>>]</a>
</body>
</html>