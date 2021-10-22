# Model1_Board

## Model 1 구조
![image](https://user-images.githubusercontent.com/49936027/138414761-5b3fd4ea-01cd-44dc-a5ab-09035d6f706b.png)


## 소스 코드

![image](https://user-images.githubusercontent.com/49936027/138413295-e3045f28-5205-467f-b026-3e5edc0a98a7.png)

http://h2database.com/html/download.html

먼저 Platform-independent.zip 파일을 다운받는다.

![image](https://user-images.githubusercontent.com/49936027/138413324-e0aec338-4b3e-42a9-973f-7304d4e820df.png)

경로대로 들어가서 h2를 실행하면 cmd 창과 함께 웹브라우저로 h2 가 실행된다.

 
### user 테이블 생성

```sql
Create table users(
	id varchar2(8) primary key,
	password varchar2(10) not null,
	name varchar2(20) not null,
	role varchar2(10)
);
```

 

### user 테이블 값 넣어주기
```sql
insert into users values('test','test1234','관리자','Admin');
insert into users values('user','user1234','손유일','User');
```

 
 
### board 테이블 생성

```sql
create table board(
	seq int(5) primary key,
	title varchar2(30),
	writer varchar2(20),
	content varchar2(200),
	regdate date default sysdate,
	cnt int(5) default 0
);
```

 

### board 테이블 값 넣어주기
```sql
insert into board(seq,title,writer,content) values(1,'가입인사','관리자','잘 운영하겠습니다.');
insert into board(seq,title,writer,content) values(2,'게시판구현','손유일','모델1구조로 구현');
insert into board(seq,title,writer,content) values(3,'MVC패턴으로구현','홍길동','등업신청이요.');
insert into board(seq,title,writer,content) values(4,'게시판프로젝트','안보현','마이네임 시청 부탁드려요');
```

 

### 커밋
```sql
commit;
```




### slect 문으로 데이터 확인하기
```sql
select * from user;
select * from board;
```
![image](https://user-images.githubusercontent.com/49936027/138413680-3f14dd9e-304f-4a5c-86b8-8e07fa2e75ae.png)

![image](https://user-images.githubusercontent.com/49936027/138413689-c3a9dc73-52b2-4205-a6ac-7c14cbc035f9.png)



### UserDO.java
```java
package user;

public class UserDO {
	
	private String id;
	private String password;
	private String name;
	private String role;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
 ```
 
### BoardDO.java
```java
package board;

import java.sql.Date;

public class BoardDO {
	
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
 ```
 
### BoardDAO.java
```java
 package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class BoardDAO {
   //DB 관련 변수 선언
   private Connection conn = null;
   private PreparedStatement pstmt = null;
   private ResultSet rs = null;
   
   //전체 게시글 목록 조회 메소드
   public List<BoardDO> getBoardList(String searchField, String searchText){
      System.out.println("==> getBoardList() 기능 처리됨!");
      List<BoardDO> boardList = new ArrayList<BoardDO>(); //가변 배열 객체 생성
      
      try {
         
         conn = JDBCUtil.getConnection();
         
         /*
          * [중요] 게시물 검색 시 => '제목' or '작성자'로 검색 조건 제시하는 SQL 문장을 어떻게 작성할 것 인가?
          * 하나의 sql 문장을 두가지 용도로 사용
          * 1. 검색 조건이 없을 때 => 전체 검색
          * 2. 검색 조건이 있을 때 => 조건 검색
          */
         String where = "";
         if( searchField != null && searchText != null ) {
            where = "where "+ searchField + " like '%" + searchText+"%'";
         }
         
         System.out.println("where: "+where);

         String Condition_SQL = "select * from board "+ where +" order by seq desc";

         pstmt = conn.prepareStatement(Condition_SQL);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            BoardDO board = new BoardDO();
            board.setSeq(rs.getInt("SEQ"));
            board.setTitle(rs.getString("TITLE"));
            board.setContent(rs.getString("CONTENT"));
            board.setWriter(rs.getString("WRITER"));
            board.setCnt(rs.getInt("CNT"));
            board.setRegdate(rs.getDate("REGDATE"));
            boardList.add(board);
         }
        
         
      } catch (Exception e) {
         // TODO: handle exception
         e.printStackTrace();
      } finally {
         JDBCUtil.close(rs, pstmt, conn);
      }
      
      return boardList;
   }
}
 ```
### UserDAO.java
```java
package user;

import java.sql.*;

import common.JDBCUtil;

public class UserDAO {
	// DB 관련 변수 선언
	private Connection			conn = null;
	private PreparedStatement	pstmt = null;
	private ResultSet			rs = null;
	
	// SQL 명령어
	private final String USER_GET = "select id, password from users where id=? and password=?";
	
	// 로그인 user 조회 (select) 메소드 구현
	public UserDO getUser(UserDO userObj) {
		UserDO user = null;
		
		try {
			System.out.println("===> JDBC로 getUser() 기능 처리됨 ! ");
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, userObj.getId());
			pstmt.setString(2, userObj.getPassword());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDO();
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;
	}
}

```

### JDBCUtil.java
```java
package common;

import java.sql.*;

public class JDBCUtil {
	// H2 DB 연동에 관련 소스
	
	static final String driver = "org.h2.Driver";
	static final String url = "jdbc:h2:tcp://localhost/~/test";
	
	public static Connection getConnection() throws Exception{
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "sa", "");
			return con;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 메소드 오버로딩 => 다형성 구현
	 */
	// DML(insert, update, delete) 작업 종료 시 호출되는 메소드
	public static void close(PreparedStatement pstmt, Connection conn) {
		if(pstmt != null) {
			try {
				if(!pstmt.isClosed()) pstmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				pstmt = null;
			}
		}if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
	}
	
	// select 작업 종료 시 호출되는 메소드
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				if(!rs.isClosed()) rs.close(); 		// 자원 해제
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				rs = null;
			}
		}if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();	// 자원 해제
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
	}
}
```

### login.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<style>
	#div_box {
		position : absolute;
		top: 10%;
		left: 40%;
	}
</style>
</head>
<body>
	<div id="div_box">
		<h1 align="center">로그인</h1>
		<form name="loginForm" method="POST" action="login_proc.jsp">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="skyblue">아이디</td>
					<td><input type="text" name="id"/></td>
				</tr>
				<tr>
					<td bgcolor="skyblue">비밀번호</td>
					<td><input type="text" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
```

### login_proc.jsp
```jsp
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
```

### getBoardList.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %> 
<%@ page import="board.BoardDO" %>
<%@ page import="board.BoardDAO" %>
<%@ page import="java.util.List" %>


<%
   request.setCharacterEncoding("UTF-8");

   String searchField = ""; //검색 대상(즉, 제목 or 작성자)
   String searchText = "";  //검색 내용
   if(request.getParameter("searchCondition")!="" && request.getParameter("searchKeyword")!=""){
      searchField= request.getParameter("searchCondition");
      searchText = request.getParameter("searchKeyword");
   }
   
   BoardDAO boardDAO = new BoardDAO();
   List<BoardDO> boardList = boardDAO.getBoardList(searchField, searchText);
   
   request.setAttribute("boardList", boardList);
	// session과 request 차이점 : request는 현재 페이지 / session은 여러 페이지에서 공유할 때 쓴다.
	
   request.setAttribute("totalList", boardList.size());
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getBoardList 페이지</title>
<style>
   #div_box {
      position: absolutel;
      top: 10%;
      left: 20%;
   }
</style>
</head>
<body>
   <div id="div_box">
      <h1>전체 게시글 목록 보기</h1>
      <h3>${IdKey}님 환영합니다.&nbsp;&nbsp;&nbsp;<a href="logout_proc.jsp">로그아웃</a></h3>
      <form name="boardListForm" method="POST" action="getBoardList.jsp">
         <p>총 게시글: ${totalList} 건 </p>
         <table border="1" cellpadding="0" cellspacing="0" width="700">
            <tr>
               <td align="right">
                  <select name="searchCondition">
                     <option value="TITLE">제목</option>
                     <option value="WRITER">작성자</option>
                     <input name="searchKeyword" type="text" />
                     <input type="submit" value="검색" />
                  </select>
               </td>
            </tr>
         </table>
      </form>
      <table border="1" cellpadding="0" cellspacing="0" width="700">
         <tr>
            <th bgcolor="skyblue" width="100">번호</th>
            <th bgcolor="skyblue" width="200">제목</th>
            <th bgcolor="skyblue" width="150">작성자</th>
            <th bgcolor="skyblue" width="150">등록일</th>
            <th bgcolor="skyblue" width="100">조회수</th>
         </tr>   
         
         <%-- 방법 1
         <%
            for(BoardDO board : boardList){
         %>
            <tr>
               <td align="center"><%= board.getSeq() %></td>
               <td align="left"><a href="getBoard.jsp?seq=<%= board.getSeq()%>"><%=board.getTitle() %></a></td>
               <!-- 제목으로 갈 때 게시글 번호를 같이 넘겨줘라. -->
               <td align="center"><%= board.getWriter() %></td>
               <td align="center"><%= board.getRegdate()%></td>
               <td align="center"><%= board.getCnt() %></td>
            </tr>
         <% } %>
         --%>
         
         <%-- 방법 2 표현 언어와 JSTL을 적용하여 소스 변경 --%>
         <c:forEach var="board" items="${boardList}">
            <tr>
               <td align="center">${board.seq}</td>
               <td align="left"><a href="getBoard.jsp?seq=${board.seq}">${board.title}</a></td>
               <td align="center">${board.writer}</td>
               <td align="center">${board.regdate}</td>
               <td align="center">${board.cnt}</td>
            </tr>      
         </c:forEach>
         
      </table>
      <br>
      <a href="insertBoard.jsp">새 게시글 등록</a>
      <a href="getBoardList.jsp">전체 게시물 목록 보기</a>&nbsp;&nbsp;&nbsp;
   </div>
</body>
</html>
```

## 구현 화면

#### 로그인 화면
![image](https://user-images.githubusercontent.com/49936027/138414289-1ec4aba9-5ef1-482b-89ae-993e37a881af.png)

#### 게시판 페이지

![image](https://user-images.githubusercontent.com/49936027/138414366-1d964afd-c6c4-40f2-aa6f-daa72bc4e09c.png)

#### 검색 후 화면
![image](https://user-images.githubusercontent.com/49936027/138414445-5bf7ca73-24ba-4d13-994c-fbdd95112975.png)




