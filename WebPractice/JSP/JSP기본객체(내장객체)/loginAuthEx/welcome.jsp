<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가 -->
<%  // 자바 코드 기술
	// 인증된 세션이 없는 경우 해당 페이지를 볼 수 없게 함
	if(session.getAttribute("signedUser") == null ){
		response.sendRedirect("logout.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%= session.getAttribute("signedUser") %>님 환영합니다.</h1>
	<a href="logout.jsp">로그아웃</a>
</body>
</html>