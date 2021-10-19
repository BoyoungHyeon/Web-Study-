#Session

## Session
![image](https://user-images.githubusercontent.com/49936027/137866467-5baad017-6b90-4434-9e34-d332b0b5e67f.png)
![image](https://user-images.githubusercontent.com/49936027/137866476-3a07e9ad-c09b-437e-8ba1-14e73f0c959e.png)
Session과 웹 브라우저의 관계

## Session 주요 메서드
|메서드|내용|
|------|---|
|setAttribute(String name, Object value)|name 값에 값을 저장한다|
|getAttribute(String name)|name 값에 대한 세팅된 값을 반환한다,  이 메소드는 리턴타입이 Object 타입이므로 사용시 실제 할당된 객체 타입으로 형변환(casting)을 해야한다|
|getId()|각 접속에 대한 세션 고유의 ID를 문자열 형태로 리턴한다. (즉 16진수 32자리)|
|invalidate()|현재 세션을 종료한다. 세션과 관련된 값들은 모두 지워진다.|

## 세션 생성 방법
세션 생성 방법


(1) 방법 page 지시어의 session 속성을 “true”로 지정해준다.
|<%@ page session = “true” %>|

(2) 방법 request.getSession()을 이용한 세션 생성

   <사용 예>
|<%
     HttpSession session = request.getSession();
     session.setAttribute(“boardList”, boardList);
     response.sendRedirect(“getBoardList.jsp”);
 %>|

### 예제 - SessionEx.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>session 내장 객체 예제</title>
</head>
<body>
	<h2>session 내장 객체</h2>
	<hr>
	<% // 자바 코드 기술
		if(session.isNew()){
			out.println("<script> alert('세션이 해제되어 다시 설정합니다.')</script>");
			session.setAttribute("login","ok");
		}
	%>
	1. 세션 ID : <%= session.getId() %><br>
	2. 세션 유지 기간 : <%= session.getMaxInactiveInterval() %><br>
	3. login 세션의 설정값 : <%= session.getAttribute("login") %>
</body>
</html>
```
