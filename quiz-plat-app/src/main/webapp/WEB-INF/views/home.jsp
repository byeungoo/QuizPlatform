<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>PickVS</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" href="resources/css/keyframes.css">
  <link rel="stylesheet" href="resources/css/pageTransitions.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap"
    rel="stylesheet">
</head>

<body style="background:#c8c8c8;">
  <div class="wrapper m-scene">
    <section class="main-sec">
      <ul class="main-sec__list">
        <c:forEach items="${writingDtlDtoList}" var="writingDtlDto">
          <li class="main-sec__list-item">
            <div class="card">
              <a href="/detail?writing_no=${writingDtlDto.writing_no}">
                <span class="card__desc">${writingDtlDto.fir_writ_content}</span>
                <div class="card__hr">
                  <img src="resources/img/CenterBar@2x.png" width="320px" height="25px" alt="VS"
                    style="display:block;margin:auto;">
                </div>
                <span class="card__desc">${writingDtlDto.sec_writ_content}</span>
                <div class="card__foot">
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/VoteCount@2x.png" width="24px" height="24px"
                      alt="투표수아이콘">
                    <span class="card__icon-desc">${writingDtlDto.sum_vote}</span>
                  </div>
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/CommentIcon@2x.png" width="24px" height="24px"
                      alt="댓글수아이콘">
                    <span class="card__icon-desc">${writingDtlDto.sum_comment}</span>
                  </div>
                </div>
              </a>
            </div>
          </li>
        </c:forEach>
      </ul>
    </section>
 
    <a href="write" class="fab">
      투표 만들기
    </a>
    <c:if test="${toastOn == 'Y'}">
      <p class="toast on scene_element">새로운 투표가 만들어졌습니다</p>
    </c:if>
  </div>

  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/common.js"></script>
</body>

</html>