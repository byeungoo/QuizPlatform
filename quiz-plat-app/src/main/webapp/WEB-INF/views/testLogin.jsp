<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>test login</title>
</head>
<body>
��Ű: ${Cookie}
<script>
	var c =  ${Cookie};
	alert(c);
</script>
<form action="/login" method="post">
    <p>���̵� : <input  name="user_id" id="user_id"></p>
    <p>��й�ȣ : <input type="password" name="pwd" id="pwd"></p>
    <input type="checkbox" name="rememberId" id="rememberId"> ���̵� ���  </input>
	<input type="submit">
</form>

</body>
</html>