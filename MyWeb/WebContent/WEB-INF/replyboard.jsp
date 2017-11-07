<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="main.java.VO.*,java.util.ArrayList" %>
<!DOCTYPE html>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Cache-Control","no-cache");

String message = (String)request.getAttribute("message");
String search_key =(String) request.getAttribute("search_key");
String search_value =(String) request.getAttribute("search_value");
if(search_key==null) search_key="";
if(search_value==null) search_value="";

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<th>작성날짜</th>
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
//int record_start = paging.getStartingRecordIdx();
//int record_end = paging.getEndingRecordIdx();
%>
<% for(int i=0;i<=boards.size()-1;i++){ 
Board board = boards.get(i);
%>
<tr>
<td><%= board.getbNum() %></td>
<td>
<%for(int j=0;j<board.getbLevel();j++){ out.print("[-]");} %>
<a href="/upviewcount?bNum=<%=board.getbNum()%>"><%= board.getTitle() %></a></td>
<td><%= board.getWriter() %></td>
<td><%= board.getViewCount() %></td>
<td><%= board.getTime() %></td>
</tr>
<%} %>
</table>
 
<form action="/search_board" method="post">
<select name="search_key">
	<option value="title" <%if(search_key.equals("title")) out.print("selected"); %>>제목</option>
	<option value="writer" <%if(search_key.equals("writer")) out.print("selected"); %>>작성자</option>
	<option value="contents" <%if(search_key.equals("contents")) out.print("selected"); %>>내용</option>
</select>
<input type="text" name="search_value" value=<%=search_value %> >
<input type="submit" value="검색">
</form>


<br>
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