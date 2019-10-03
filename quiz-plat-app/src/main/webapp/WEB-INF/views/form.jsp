<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<body>
<h1>파일 업로드 예제</h1>
	<form method="post" action="upload" enctype="multipart/form-data">
	         <label>email:</label>
	         <input type="text" name="email">
	         <br><br>
	         
	         <label>파일:</label>
	         <input type="file" name="file1">
	         <br><br>
	         
	         <input type="submit" value="upload">
	</form>
</body>