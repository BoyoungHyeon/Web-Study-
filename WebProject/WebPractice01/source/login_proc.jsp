<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp" %>
<!-- 추가 -->
<%@ page import="user.UserDO" %>
<%@ page import="user.UserDAO" %>

<%
	// 사용자 입력 정보 추출
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	// 2. UserDO 클래스 객체 생성 후 맴버변수에 값 초기화
	UserDO userDO = new UserDO();
	userDO.setId(id);
	userDO.setPassword(password);
	
	// 3. UserDO 클래스 객체 생성 후 getUser() 메소드 호출하면서 userDO 객체를 넘겨준다
	UserDAO userDAO = new UserDAO();
	UserDO user = userDAO.getUser(userDO);
	
	// 화면 네비게이션
	if(user != null){
		session.setAttribute("IdKey", id);
		response.sendRedirect("getBoardList.jsp");
		/* out.println("<script>alert('로그인 성공');</script>"); */
	}else{
		/* out.println("<script>alert('로그인 실패');</script>"); */
		response.sendRedirect("login.jsp");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>