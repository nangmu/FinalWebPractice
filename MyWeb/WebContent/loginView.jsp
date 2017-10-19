<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table><form action="/login" method="post">
<tr>
<td>ID</td>
<td><input type="text" name="id"/></td>
</tr>

<tr>
<td>PASSWORD</td>
<td><input type="password" name="pw"/></td>
</tr>

<tr>
<td></td>
<td><input type="submit" value="Login"/>&nbsp<input type="reset" value="cancle"/>
<input type="button" value="메인페이지로" onclick="window.location.replace('/mainView.jsp')"/></td>
</tr>
</form></table>
</body>
</html>