
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<%
String userId = (String)request.getSession().getAttribute("userId");
String userName = (String)request.getSession().getAttribute("userName");
%>
<body>
<%if(userId!=null){%>
	<h5><%=userName %>�� ȯ���մϴ�.</h5>	
<%}%>
	<ul>
	<%if(userId==null){ %>
		<li><a href="/loginForm">�α���</a></li>
		<li><a href="/signupForm">ȸ������</a></li>
	<%}else{ %>
		<li><a href="/logout">�α׾ƿ�</a></li>	
	<%} %>
		<li><a href="/userlist">����ڸ��</a></li>
		<li><a href="/replyboard">�Խ���</a></li>
	</ul>
</body>
</html>