<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%
	String body = null;
	StringBuilder stringBuilder = new StringBuilder();
	BufferedReader bufferedReader = null;

	try {
		InputStream inputStream = request.getInputStream();
		if (inputStream != null) {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			byte[] byteBuffer = new byte[1024];
			String str ="";
			while ((str = bufferedReader.readLine()) !=null) {
				stringBuilder.append(str);
			}
		}
	} catch (IOException ex) {
		throw ex;
	} finally {
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException ex) {
				throw ex;
			}
		}
	}

	body = stringBuilder.toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=body %>
</body>
</html>