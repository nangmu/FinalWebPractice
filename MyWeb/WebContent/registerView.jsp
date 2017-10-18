<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<table><form action="/register" method="post">
<tr>
<td>ID</td>
<td><INPUT type="text" name="id"/></td>
</tr>

<tr>
<td>PW</td>
<td><INPUT type="password" name="pw"/></td>
</tr>

<tr>
<td>이름</td>
<td><INPUT type="text" name="name" /></td>
</tr>

<tr>
<td></td>
<td><INPUT type="submit" value="가입"/>&nbsp<INPUT type="reset" value="취소" /></td>
</tr>
</form></table>
</body>
</html>