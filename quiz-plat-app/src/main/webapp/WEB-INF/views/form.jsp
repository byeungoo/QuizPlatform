<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<body>
<h1>���� ���ε� ����</h1>
	<form method="post" action="writePost" enctype="multipart/form-data">
	         <label>email:</label>
	         <input type="text" name="title">
	         <input type="text" name="summary">
	         <input type="text" name="fir_writ_content">
	         <input type="text" name="sec_writ_content">
	         <input type="text" name="content">
	         <br><br>
	         
	         <label>����:</label>
	         <input type="file" name="title_img_file">
	         <br><br>
	         
	         <input type="submit" value="upload">
	</form>
</body>