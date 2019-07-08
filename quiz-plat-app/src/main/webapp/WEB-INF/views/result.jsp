<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>결과페이지</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.css" />
  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="wrapper wrapper--white result" data-role="page">
    <div class="" style="height:45px;"><h3>앱바</h3></div>
    <header class="header">
      <div class="header__logo">
        <a href="detail.html" class="header__back" data-transition="slide" data-rel="back">
          <img src="img/Back_blue@2x.png" width="16px" height="24px" alt="뒤로가기버튼" class="header__icon">
          <span class="header__home">홈</span>
        </a>
      </div>
      <div class="header__title">결과보기</div>
    </header>
    <section class="result__contwrap">
      <div class="result__cont">
        <div class="result__toptx">엥;</div>
        <div class="result__area">
          <div class="result__left-area">
            <div class="result__badges">
              <img src="./img/lose.png" width="56px" height="21px" alt="lose" class="badge">
              <img src="./img/mypick.png" width="66px" height="21px" alt="mypick" class="badge">
            </div>
            <div class="result__infoarea">
              <p class="result__info">41.8%</p>
              <p class="result__infosub">나와 1,371명의 선택</p>
              <p class="result__footinfo">조선시대 왕으로 다시 태어나기</p>
            </div>
          </div>
          <div class="result__right-area win">
            <div class="result__badges">
              <img src="./img/win.png" width="56px" height="21px" alt="win" class="badge">
            </div>
            <div class="result__infoarea">
                <p class="result__info">58.2%</p>
                <p class="result__infosub">1,910명의 선택</p>
                <p class="result__footinfo">글자는 이런식으로 길어집니다. 물론 영원히 길어질 수는 없죠. 50자가 한계죠.</p>              
            </div>
          </div>
        </div>
        <div class="result__reply-area">
          <div class="result__reply">
            <div class="result__reply-tit">
              익명
            </div>
            <div class="result__reply-cont">
              당연히 100억이지 조선시대 왕들 전부 단명하는거 모르는 부분? 전자 찍으신분들 채소 국사시간에 졸으신 분들 ㅋ
            </div>
          </div>
          <div class="result__reply">
            <div class="result__reply-tit">
              익명23
            </div>
            <div class="result__reply-cont">
              뭔소리야 당연히 닥후지 조선시대 왕이 짱이야
            </div>
          </div>
        </div>
    </section>
    <div class="result__footbtn">공유하기</div>
  </div>
  <script src="js/common.js"></script>
</body>
</html>
