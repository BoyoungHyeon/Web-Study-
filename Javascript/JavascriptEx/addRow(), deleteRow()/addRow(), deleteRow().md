# 테이블 행 추가/삭제하기

## addRow(), insertRow(), insertCell, deleteRow()
|함수|내용|
|------|---|
|insertRow()|
addRow()
insertCell|	테이블에 행 추가하기|
|deleteRow()|	테이블에 행 삭제하기|



## 예제
#### dynamicMultiForm.jsp
```javascript
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>dynamicMultiForm 페이지</title>
<script>
   var count = 1; // 전역변수
   
   function addRow() {
      var dynamicTable = document.getElementById('dynamic_table');
      var newRow = dynamicTable.insertRow();
      var cell1 = newRow.insertCell();
      var cell2 = newRow.insertCell();
      
      cell1.innerHTML = '<input type="checkbox" name="checkboxObj"/>';
      cell2.innerHTML = '<input type="file" name="fileupload'+count+'" size="70" onchange="checkSize(this)">';
      count++;
   }
   
   function checkSize(input) {
      if (input.files && input.files[0].size > (20*1024*1024)) {
         alert("파일 사이즈가 20MB 초과되었습니다.");
      }
   }
   
   function deleteRow() {
      var table = document.getElementById('dynamic_table');
      var checkboxArray = document.getElementsByName('checkboxObj');
      
      for (var i = checkboxArray.length-1; i>=0; i--) {
         var check = checkboxArray[i].checked;
         
         if (check) {
            table.deleteRow(i);
         }
      }
   }
</script>
</head>
<body>
   <h2>동적 다중 파일 업로드 구현하기</h2>
   <form name="dynamicForm" method="POST" enctype="multipart/form-data"
                                 action="register.jsp">
   <table border="1" cellpadding="0" cellspacing="0">
      <tr>
         <td>작성자</td>
         <td><input type="text" name="name"/></td>
      </tr>
      <tr>
         <td>제   목</td>
         <td><input type="text" name="subject"/></td>
      </tr>
   </table>
   <br>
   <input type="button" value="행추가" onClick="addRow();"/>&nbsp;&nbsp;&nbsp;
   <input type="button" value="행삭제" onClick="deleteRow();"/><br><br>
   Check&nbsp;&nbsp;&nbsp;&nbsp;업로드할 파일 이름
   <br>
   <table id="dynamic_table" border="1" cellpadding="0" cellspacing="0"></table>
      <h4><font color="red">업로드할 파일은 최대 20MB까지 업로드 가능</font></h4>
      <input type="submit" value="파일 올리기"/>
   </form>
</body>
</html>
```

## 자바스크립트에서 HTML문서의 객체를 가져올 때 사용되는 함수들

|함수|내용|
|------|---|
|getElementById()|문서 객체 중 id값을 가져오는 함수|
|getElementByName()|문서 객체 중 Name값을 가져오는 함수|
 

## enctype="multipart"
- 모든 문자를 인코딩하지 않음을 명시함. 이 방식은 <form> 요소가 파일이나 이미지를 서버로 전송할 때 주로 사용함

 
#### register.jsp
```javascript
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest,
				com.oreilly.servlet.multipart.DefaultFileRenamePolicy,
				java.util.*,
				java.io.*" 
%>  
<% request.setCharacterEncoding("UTF-8");%>
<%
	String savePath = "C:/FileUpload/filestorage";
	String encType = "UTF-8";
	int maxSize = 20*1024*1024;
	
	try{
		MultipartRequest multi = null;
		multi = new MultipartRequest(request,savePath,maxSize,encType, new DefaultFileRenamePolicy());
		
		String name = multi.getParameter("name");
		String subject = multi.getParameter("subject");
		
		out.println("작성자 : "+name+"<br>");
		out.println("제목 : "+subject+"<br>");
		out.println("<br>");
		
		Enumeration files = multi.getFileNames();
		
		while(files.hasMoreElements()){
			String fname = (String)files.nextElement();
			String filename = multi.getFilesystemName(fname);
			out.println("업로드된 파일 이름 : " + filename + "<br>");
		}
	}catch(Exception e){
		System.out.println(e);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register 페이지</title>
</head>
<body>

</body>
</html>
```

## 구현화면
![image](https://user-images.githubusercontent.com/49936027/138213350-8a48514f-a795-494e-9e1b-73a7e3dc8e3e.png)
  
![image](https://user-images.githubusercontent.com/49936027/138213359-9c58ce4b-96d9-4c47-9737-3f61122f3628.png)
  
![image](https://user-images.githubusercontent.com/49936027/138213385-8fff16cb-ae20-4ca5-b598-c43b43830989.png)


