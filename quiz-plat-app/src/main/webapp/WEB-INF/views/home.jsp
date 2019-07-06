<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>퀴즈플랫폼</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.css" />
  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
  <link rel="stylesheet" href="resources/css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="wrapper" data-role="page">
    <header data-role="header">
      <h3 style="height:44px;">헤더 영역</h3>
    </header>
    <section class="main-sec">
      <ul class="main-sec__list" data-role="content">
        <li class="main-sec__list-item">
        <c:forEach items="${writingDtlDtoList}" var="writingDtlDto">
          <div class="card">
            <a href="#page2" data-transition="slidefade">
              <span class="card__desc">${writingDtlDto.fir_writ_content}</span>
              <img src="resources/img/CenterBar@2x.png" width="320px" height="25px" alt="VS" style="display:block;margin:auto;">
              <span class="card__desc">${writingDtlDto.sec_writ_content}</span>
              <div class="card__foot">
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/VoteCount@2x.png" width="24px" height="24px" alt="투표수아이콘">
                    <span class="card__icon-desc">${writingDtlDto.sum_vote}</span>
                  </div>
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/CommentIcon@2x.png" width="24px" height="24px" alt="댓글수아이콘">
                    <span class="card__icon-desc">${writingDtlDto.sum_comment}</span>
                  </div>
                </div>
              </a>
          </div>
          </c:forEach>
        </li>
      </ul>
    </section>
  </div>
  <div class="wrapper--white" id="page2" data-role="page">
    <div class="" style="height:45px;"><h3>앱바</h3></div>
    <header data-role="header" class="header">
      <div class="header__back">
        <img src="resources/img/Back_blue@2x.png" width="16px" height="24px" alt="뒤로가기버튼" class="header__icon">
        <span class="header__home">홈</span>
      </div>
      <div class="header__infos">
        <div class="header__info">
          <img class="header__icon" src="resources/img/VoteCount@2x.png" width="24px" height="24px" alt="투표수">
          <div class="header__icon-desc">3,281</div>
        </div>
        <div class="header__info">
          <img class="header__icon" src="resources/img/CommentIcon@2x.png" width="24px" height="24px" alt="댓글수" >
          <div class="header__icon-desc">78</div>
        </div>
      </div>
    </header>
    <section class="main-sec" data-role="content">
        <div class="card card--single">
          <a href="#">
            <span class="card__desc">조선시대 왕으로<br> 다시태어나기</span>
          </a>
        </div>
        <div class="card card--single">
          <a href="#">
            <span class="card__desc">글자수 테스트입니다. 영원히 길어질수는 없지만 세줄까지 가능</span>
          </a>
        </div>
        <div class="card__cont">
          content  
        </div>
    </section>
  </div>
  <script src="resources/js/common.js"></script>
</body>
</html>