<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html>
<!--  자바 클래스 임포트  -->
<%@ page import="board.BoardDO" %>
<%@ page import="board.BoardDAO" %>
<%@ page import="java.util.List" %>

<html>
<head>
<meta charset="UTF-8">
<title>insertBoard.jsp => 게시글 등록 페이지</title>
</head>
<body>
	<h1>새 게시글 등록</h1>
	<a href="logout_proc.jsp">로그아웃</a>
	<hr>
	<form name="insertForm" method="POST" action="insertBoard_proc.jsp">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left"><input type="text" name="title" /></td>
			</tr>
			
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left"><input type="text" name="writer" /></td>
			</tr>
			
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left">
					<textarea name="content" rows="10" cols="40"></textarea>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글 등록" />
				</td>
			</tr>
		</table>
	</form>
	<hr>
	<a href="getBoardList.jsp">전체 게시글 목록 보기</a>
</body>
</html>