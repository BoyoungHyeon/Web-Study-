<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가 -->
<%  // 자바 코드 기술
	// 1. 기존의 세션 데잍를 모두 삭제 (즉, 세션 무효)
	session.invalidate(); // 세션 무효화
	
	// 2. 로그인 페이지로 이동 
	response.sendRedirect("loginForm.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 페이지</title>
</head>
<body>

</body>
</html>