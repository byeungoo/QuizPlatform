<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>상세페이지</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.css" />
  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="wrapper wrapper--white" data-role="page">
    <div class="" style="height:45px;"><h3>앱바</h3></div>
    <header data-role="header" class="header">
      <div class="header__logo">
        <a href="index.html" class="header__back" data-transition="slide" data-rel="back">
          <img src="img/Back_blue@2x.png" width="16px" height="24px" alt="뒤로가기버튼" class="header__icon">
          <span class="header__home">홈</span>
        </a>
      </div>
      <div class="header__infos">
        <div class="header__info">
          <img class="header__icon" src="img/VoteCount_BW@2x.png" width="24px" height="24px" alt="투표수">
          <div class="header__icon-desc">3,281</div>
        </div>
        <div class="header__info">
          <img class="header__icon" src="img/Comment_BW@2x.png" width="24px" height="24px" alt="댓글수" >
          <div class="header__icon-desc">78</div>
        </div>
      </div>
    </header>
    <section class="detail" data-role="content">
        <div class="card card--single">
          <a href="#">
            <span class="card__desc">조선시대 왕으로<br> 다시태어나기</span>
          </a>
        </div>
        <div class="card card--single">
          <a href="#">
            <span class="card__desc">
              <span class="card--responsive">글자수 테스트입니다. 영원히 길어질수가능</span>
            </span>
          </a>
        </div>
        <div class="detail__desc">
          <p class="detail__tit">설명</p>
          <div class="detail__txtarea">한글 테스트 한글 테스트 한글 테스트 한글의 크기는 이정도 입니다. 입니다.Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ipsam alias excepturi aspernatur magnam molestias, facilis error odio molestiae possimus voluptas impedit dolore adipisci reiciendis at harum recusandae, quia, quasi mollitia. Lorem ipsum, dolor sit amet consectetur adipisicing elit. Saepe id accusantium quo minima dolores! Deserunt velit, atque, autem, dolores iure quae vel numquam aliquam hic exercitationem quaerat rem ea sint?</div>
          <a class="detail__btn">선택해주세요</a>
          <a class="detail__btn on" href="./result.html" data-transition="slide">결과보기</a>
        </div>
    </section>
  </div>
  <script src="js/common.js"></script>
</body>
</html>
