<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>test login</title>
</head>
<body>
쿠키: ${Cookie}
<script>
	var c =  ${Cookie};
	alert(c);
</script>
<form action="/login" method="post">
    <p>아이디 : <input  name="user_id" id="user_id"></p>
    <p>비밀번호 : <input type="password" name="pwd" id="pwd"></p>
    <input type="checkbox" name="rememberId" id="rememberId"> 아이디 기억  </input>
	<input type="submit">
</form>

</body>
</html>