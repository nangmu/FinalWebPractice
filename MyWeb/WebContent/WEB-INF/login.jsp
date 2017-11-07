<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>

</script>
</head>
<body>
<table>
<form action="/login" method="post">
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
<input type="button" value="메인페이지로" onclick="window.location.replace('/')"/></td>
</tr>
</form></table>
</body>
</html>