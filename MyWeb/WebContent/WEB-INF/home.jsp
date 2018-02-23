
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<%
String userId = (String)request.getSession().getAttribute("userId");
String userName = (String)request.getSession().getAttribute("userName");
%>
<body>
<%if(userId!=null){%>
	<h5><%=userName %>님 환영합니다.</h5>	
<%}%>
	<ul>
	<%if(userId==null){ %>
		<li><a href="/loginForm">로그인</a></li>
		<li><a href="/signupForm">회원가입</a></li>
	<%}else{ %>
		<li><a href="/logout">로그아웃</a></li>	
	<%} %>
		<li><a href="/userlist">사용자목록</a></li>
		<li><a href="/replyboard">게시판</a></li>
	</ul>
</body>
</html>