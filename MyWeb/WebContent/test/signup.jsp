<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<form action="/signup" method="post">
<table>
<tr>
<th>ID</th>
<td><INPUT type="text" name="id"/></td>
<td><span class="result" id="result_id"></span></td>
</tr>

<tr>
<th>PW</th>
<td><INPUT type="password" name="pw"/></td>
<td><span class="result" id="result_pw"></span></td>
</tr>

<tr>
<th>이름</th>
<td><INPUT type="text" name="name" /></td>
<td><span class="result" id="result_name"></span></td>
</tr>

<tr>
<td></td>
<td><INPUT type="submit" value="가입"/>&nbsp<INPUT type="reset" value="취소" /></td>
<td></td>
</tr>
</table></form>

</body>
</html>