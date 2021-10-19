# ErrorPage

![image](https://user-images.githubusercontent.com/49936027/137870109-4140a186-7cb1-4ddf-a8f9-e550b4bd8264.png)

#### 코드1 
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가  -->
<%@ page errorPage="error_page.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error_test.jsp 페이지</title>
</head>
<body>
	<div align="center">
		<h2>error_test.jsp 페이지</h2>
		<hr>
		<%= 10/0 %>
	</div>
</body>
</html>
```
에러 발생페이지다.
여기서 error_page.jsp로 보내주게 되서 error_page.jsp화면이 뜨게 되는 로직이다.


#### 코드2 
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 추가  -->
<%@ page isErrorPage = "true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>error_page.jsp 페이지</title>
</head>  


<!-- 추가 -->
<jsp:useBean id="now" class="java.util.Date" />
<body>
	<div>
		<h2>error_page.jsp 페이지</h2>
		<hr>
		<table>
			<tr width=100% bgcolor="orange">
				<td>
					개발자에게 문의해 주세요.<br>
					빠른 시일내로 해결하겠습니다.
				</td>
			</tr>	
			<tr>
				<td>
					${now}<p>
					요청실패 URI : ${pageContext.errorData.requestURI }
					상태코드 : ${pageContext.errorData.statusCode }
					예외유형 : ${pageContext.errorData.throwable }
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
```
