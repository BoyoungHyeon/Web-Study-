<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>fileSelect 페이지</title>
<script>
	function fileCheckSize(input){
		if(input.files && input.files[0].size > (20*1024*1024)){
			alert("파일의 사이즈가 20MB를 초과했습니다.");
			input.value = null;
		}
	}
</script>
</head>
<body>
	<form name="fileUploadForm" method="POST" enctype="multipart/form-data" action="viewPage.jsp">
	작성자 <br>
	<input type="test" name="name" /><br>
	
	제목 <br>
	<input type="test" name="subject" /><br>
	
	<h4><font color="red">*업로드할 파일은 최대 20MB까지 해라 진짜로*</font></h4>
	
	업로드할 파일 <br>
	<input type="file" name="uploadFile" onchange="fileCheckSize(this)" />
	<input type="submit" value="파일 올리기" />
	</form>
</body>
</html>