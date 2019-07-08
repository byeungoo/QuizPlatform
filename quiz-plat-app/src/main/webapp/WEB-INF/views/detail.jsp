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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/css/animsition.min.css">
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="wrapper wrapper--white">
    <div class="" style="height:45px;"><h3>앱바</h3></div>
    <header class="header">
      <a href="index.html" class="animsition-link header__home" data-animsition-out-class="fade-in-left"
      data-animsition-out-duration="200">
        <img src="img/Back_blue@2x.png" width="16px" height="24px" alt="뒤로가기버튼" class="header__icon">
        <span class="header__tx">홈</span>
      </a>
      <div class="header__infos">
        <div class="header__info">
          <img class="header__icon" src="resources/img/VoteCount_BW@2x.png" width="24px" height="24px" alt="투표수">
          <div class="header__icon-desc">${writingDtlDto.sum_vote}</div>
        </div>
        <div class="header__info">
          <img class="header__icon" src="resources/img/Comment_BW@2x.png" width="24px" height="24px" alt="댓글수" >
          <div class="header__icon-desc">${writingDtlDto.sum_comment}</div>
        </div>
      </div>
    </header>
    <section class="detail" >
        <div class="card card--single">
          <input type="radio" name="versus" id="before" class="blind">
          <label for="before">
            <span class="card__desc">조선시대 왕으로<br> 다시태어나기</span>
          </label>
        </div>
        <div class="card card--single">
            <input type="radio" name="versus" id="after" class="blind">
            <label for="after">
              <span class="card__desc">
                <span class="card--responsive">글자수 테스트입니다. 영원히 길어질수가능</span>
              </span>
            </label>
          </a>
        </div>
        <div class="detail__desc">
          <p class="detail__tit">설명</p>
          <div class="detail__txtarea">${writingDtlDto.content}</div>
          <a class="detail__btn">선택해주세요</a>
          <a class="detail__btn on" href="/result?writing_no=${writingDtlDto.writing_no}" class="animsition-link">결과보기</a>
        </div>
    </section>
  </div>
  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/js/animsition.min.js"></script>
  <script src="js/common.js"></script>
</body>
</html>
