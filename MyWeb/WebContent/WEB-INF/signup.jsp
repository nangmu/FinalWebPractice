<%@ page language="java" contentType="text/html"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
.result{
color: red;
}
</style>
<script>
function check(){
	var id = document.getElementById("id");
}
</script>
</head>
<body>

<table><form id="form1" action="/signup" method="post">
<tr>
<td>ID</td>
<td><INPUT id="id" type="text" name="id"/></td>
<td><span class="result" id="result_id"></span></td>
</tr>

<tr>
<td>PW</td>
<td><INPUT id="password" type="password" name="pw"/></td>
<td><span class="result" id="result_pw"></span></td>
</tr>

<tr>
<td>이름</td>
<td><INPUT id="name" type="text" name="name" /></td>
<td><span class="result" id="result_name"></span></td>
</tr>

<tr>
<td></td>
<td><INPUT type="submit" value="가입"/>&nbsp<INPUT type="reset" value="취소" /></td>
</tr>
</form></table>
</body>
</html>