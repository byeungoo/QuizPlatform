<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="resources/css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap">
  <title>PickVS</title>
</head>

<body>
  <!-- 회원 가입 모달창-->
  <div id="lean_overlay"></div>
  <div class="join" id="join" style="display:none;width:85vw;">
    <div class="join_cont">
      <div class="join_inparea">
        <label for="join1" class="join_inp_tit">아이디</label>
        <input type="text" class="join_inp" placeholder="아이디를 입력해주세요." id="join1">
      </div>
      <div class="join_inparea">
        <label for="join1" class="join_inp_tit">닉네임</label>
        <input type="text" class="join_inp" placeholder="행복한 돼지" id="join2">
      </div>
      <div class="join_inparea">
        <label for="join3" class="join_inp_tit">비밀번호</label>
        <input type="password" class="join_inp" placeholder="비밀번호" id="join3">
      </div>
      <div class="join_inparea">
        <label for="join4" class="join_inp_tit">비밀번호 확인</label>
        <input type="password" class="join_inp" placeholder="한번더입력" id="join4">
      </div>
      <button class="join_submit">회원가입</button>
    </div>
  </div>
  <a href="#join" id="join_trigger" class="blind">회원가입창</a>

  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/common.js"></script>
  <script src="resources/js/jquery.leanModal.min.js"></script>
  <script>
    $("#join_trigger").leanModal({}); //a태그에 모달 켜기 기능 추가
    $("#join_trigger").click();
  </script>
</body>

</html>