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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/css/animsition.min.css">
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="wrapper">
    <header>
      <h3 style="height:44px;">헤더 영역</h3>
    </header>
    <section class="main-sec">
      <ul class="main-sec__list" >
        <li class="main-sec__list-item">
        <c:forEach items="${writingDtlDtoList}" var="writingDtlDto">
          <div class="card">
            <a href="detail.html" class="animsition-link" data-animsition-out-class="fade-in-right"
                  data-animsition-out-duration="200">
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

  <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/js/animsition.min.js"></script>
  <script src="js/common.js"></script>
</body>
</html>
