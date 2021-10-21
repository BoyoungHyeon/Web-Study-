# Mybatis 사원 테이블  웹 페이지 실습
 
### 자바에 Mybatis 프레임워크 적용하여 사원(Emp) 테이블에서 사원 ‘목록 보기’ 및 ‘사원 추가’ Web 개발 순서

![image](https://user-images.githubusercontent.com/49936027/138280882-b26dacb8-2daf-430f-850f-358e122b1381.png)

<EMP_MyBatis 전체 설계도>
 

 

 

### 💻 실습 코드

![image](https://user-images.githubusercontent.com/49936027/138281234-7d53fc6f-c530-4570-9738-1d27d08a5afd.png)

#### [WebContent] - [WEB-INF] - [lib] 폴더 안에 jar파일 두 개를 넣어준다.

 

 
### 테이블 생성

```sql
create table emp_table(
empno number(4),
ename varchar2(20),
position varchar2(10),
constraint emp_tab_pk primary key(empno));
```

 

### 값 추가
```sql
insert into emp_table values(1001,'박태호','부장');
insert into emp_table values(1002,'손유일','과장');
insert into emp_table values(1003,'오수철','대리');
insert into emp_table values(1004,'안재홍','사원');
```

 

### config.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
 <configuration>
 	<environments default="">
 		<environment id="">
 			<transactionManager type="JDBC" />
 			<dataSource type="POOLED">
 				<property name="driver" value="oracle.jdbc.OracleDriver"/>
 				<property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"/>
 				<property name="username" value="testdb"/>
 				<property name="password" value="testdb1234"/>
 			</dataSource>
 		</environment>
 	</environments>
 	
 	<mappers>
 		<mapper resource="mybatis/sqlmapper/empMapper.xml" />
 	</mappers>
 	
 </configuration>
```
 - oracle database와 프로젝트를 연결
 - mybatis 연결

 

 

### EmpVO.java
```java
package mybatis.vo;

public class EmpVO {
	// 맴버 변수
	private int empno;			// 사원번호
	private String ename;		// 사원명
	private String position;	// 직책
	
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}
```
 - VO에서 맴버 변수를 생성하고 getter / setter 

 

 

### EmpDAO.java
```java
package mybatis.dao;

import java.util.*;
import javax.print.attribute.HashAttributeSet;
import org.apache.ibatis.session.SqlSession;
import mybatis.service.FactoryService;
import mybatis.vo.EmpVO;

public class EmpDAO {
	public static List<EmpVO> getTotal(){
		/* 이미 생성되어있는 factory를 이용하여 Sqlsession을 얻어낸다. 
		 * select 작업은 auto commit을 하지 않아도 되기 때문에 openSession()메소드 호출 시 true를 인자값으로 주지 않아도 된다.
		 */
	
		SqlSession ss = FactoryService.getFactory().openSession();
		
		List<EmpVO> list = ss.selectList("emp.total");
		ss.close();
	
		return list;
	}
	
	public static int add(String empno, String ename, String position) {
		// mapper를 호출할 때 Map 자료구조로 전달해야 하므로 Map 계열의 객체 생성
		Map<String, String> map = new Hashtable<String, String>();
		
		map.put("empno", empno);
		map.put("ename", ename);
		map.put("position", position);
		
		/* SqlSession 객체를 얻을 때 openSession(true)와 같이 호출하면 
		 * DML(insert,update,delete) 실행 시 auto commit을 수행하는 
		 * SqlSession 객체를 얻을 수 있다. 꼭 true를 인자값으로 준다.
		 */
		SqlSession ss = FactoryService.getFactory().openSession(true);
		
		int cnt = ss.insert("emp.add", map);
		ss.close();
		return cnt;
		
	}
}
```
 - Mybatis 사용 목적 중 하나가 Data Accept Object DAO로 부터 SQL문을 분리하는 것이다. 분리된 SQL문은 SQL mapper 파일에 작성하며 DAO에서는 SqlSession 객체가 SQL mapper 파일을 참조하게 된다.
 - SqlSession 객체를 얻을 때 openSession(true)와 같이 호출하면 DML(insert,update,delete) 실행 시 auto commit을 수행하는  SqlSession 객체를 얻을 수 있다. 꼭 true를 인자 값으로 준다.

 

 

### FactoryService.java
```java
package mybatis.service;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FactoryService {
	private static SqlSessionFactory factory;
	
	static {
		try {
			// 먼저 Mybatis 설정 파일인 config.xml 파일로부터 설정 정보를 읽어들이기 위한 입력 스트림을 생성한다.
			Reader reader = Resources.getResourceAsReader("mybatis/config/config.xml");
			// 그 후 입력 스트림을 통해 config.xml 파일을 읽어 SqlSessionFactory 객체를 생성한다.
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getFactory() {
		return factory;
		
	}
}
```
 - 먼저 Mybatis 설정 파일인 config.xml파일로부터 설정 정보를 읽어들이기 위한 스트림을 생성한 후, 스트림을 통해 config.xml 파일을 읽어 SqlSessionFactory 객체를 생성

 

 

### empMapper.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="emp">
	<select id="total" resultType="mybatis.vo.EmpVO">
		select empno, ename, position from emp_table
	</select>
	
	<insert id="add" parameterType="java.util.Map">
		INSERT INTO emp_table(empno, ename, position) 
		VALUES(#{empno}, #{ename}, #{position})
	</insert>
</mapper>
```
 

 

### add.jsp
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바 클래스 임포트 -->
<%@ page import="mybatis.vo.EmpVO"%>
<%@ page import="mybatis.dao.EmpDAO"%>
<%@ page import="java.util.*"%>
<% 	
	// 넘어오는 파라미터 받기 (empno, ename, position)
	request.setCharacterEncoding("UTF-8");

	String empno = request.getParameter("empno");
	String ename = request.getParameter("ename");
	String position = request.getParameter("position");
	
	int cnt = EmpDAO.add(empno, ename, position);
	
	// 클라이언트에 응답
	response.sendRedirect("total.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add Page</title>
</head>
<body>

</body>
</html>
```

### total.jsp
```jsp
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 자바 클래스 임포트 -->
<%@ page import="mybatis.vo.EmpVO"%>
<%@ page import="mybatis.dao.EmpDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>total 페이지</title>
<style>
table {
	width: 500px;
	border-collapse: collapse;
}

table, th, table>tbody td {
	border: 1px solid black;
	padding: 4px;
}

table thead>tr:first-child>th {
	border: 0px
}

#add_win {
	width: 200px;
	border: 1px solid black;
	background-color: #fff;
	position: absolute;
	top: 100px;
	left: 580px;
	display: none;
}

#add_win input[type=text] {
	width: 90px;
	padding: 3px;
	border: 1px solid black;
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<header>
		<h1>사원 목록</h1>
	</header>
	<article>
		<table>
			<caption>사원들의 목록을 위한 테이블</caption>
			<thead>
				<tr>
					<th colspan="3" style="text-align: right;"><input
						type="button" value="사원추가" id="add_btn" onclick="addFun()" /></th>
				</tr>

				<tr>
					<th bgcolor="orange">사원번호</th>
					<th bgcolor="orange">사원명</th>
					<th bgcolor="orange">직책</th>
				</tr>
			</thead>
			<tbody>
				<%
					List<EmpVO> list = EmpDAO.getTotal();

					if (list != null && list.size() > 0) {
						for (EmpVO vo : list) {
				%>
				<tr>
					<td><%=vo.getEmpno()%></td>
					<td><%=vo.getEname()%></td>
					<td><%=vo.getPosition()%></td>
				</tr>
				<%
					}
					}
				%>
			</tbody>
		</table>
	</article>

	   <div id="add_win">
      <header>
         <h2>사원 추가</h2>
      </header>
      <div id="body">
         <form method="POST" action="add.jsp">
            <label for="empno">사원번호</label>
            <input type="text" id="empno" name="empno"/>
            <br/>
            
            <label for="ename">사원명</label>
            <input type="text" id="ename" name="ename"/>
            <br/>
            
            <label for="position">직책</label>
            <input type="text" id="position" name="position" />
            <br>
            
            <input type="button" value="추가" id="append_btn" onclick="sendData()"/>
            <input type="button" value="취소" id="cancel_btn" onclick="closeWin()"/>
         </form>
         <br/>
      </div>
   </div>
	<script>
		function addFun() {
			var addWin = document.getElementById("add_win");
			addWin.style.display = "block"
		}
		function sendData() {
			var empno = document.getElementById("empno").value;
			var ename = document.getElementById("ename").value;
			var position = document.getElementById("position").value;

			if (empno.trim().length < 1) {
				// 한자도 입력하지 않은 경우
				document.getElementById("empno").value = "";
				alert("사원 번호를 입력 해 주세요.")

				document.getgetElementById("ename").focus();
				return;
			}
			document.forms[0].submit(); // submit 객체를 누른 경우와 똑같은 효과를 줌.

		}
		function closeWin() {
			var addWin = document.getElementById("add_win");
			addWin.style.display = "none"
		}
	</script>
</body>
</html>
 ```

 

### 테이블 사원추가 구현 화면

![image](https://user-images.githubusercontent.com/49936027/138236420-d3716ce6-3500-4b2d-964b-9d4243b6ed98.png)

![image](https://user-images.githubusercontent.com/49936027/138236484-18082427-ad15-4af2-bffc-ba09123c6428.png)

![image](https://user-images.githubusercontent.com/49936027/138236506-fbffc577-2da4-42f0-ac75-e8e0395f85ad.png)

