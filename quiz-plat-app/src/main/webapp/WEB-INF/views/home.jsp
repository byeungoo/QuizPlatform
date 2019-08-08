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

<body>
  <header class="home_header">
    <ul class="home_header_navlist">
      <li class="home_header_navitem on" val="1">인기</li>
      <li class="home_header_navitem" val="2">신규</li>
      <li class="home_header_navitem" val="3">활동</li>
    </ul>
  </header>
  <div>
    <c:choose>
      <c:when test="${empty login }">
        <li>
          <a href="#">로그인</a>
        </li>
        <li>
          <a href="#">회원가입</a>
        </li>
        <li>
          ${login}
        </li>
      </c:when>
      <c:otherwise>
        <p>${login.user_id}님, 반갑습니다!</p>
        <p>${cookie.remember.value}님, 반갑습니다!</p>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="wrapper m-scene">
    <section class="main-sec">
      <ul class="main-sec__list">
        <c:forEach items="${writingPopulDtoList}" var="writingPopulDto">
          <li class="main-sec__list-item">
            <div class="card">
              <a href="/detail?writing_no=${writingPopulDto.writing_no}">
                <span class="card__desc">${writingPopulDto.fir_writ_content}</span>
                <div class="card__vsimg">
                  <img src="resources/img/vs.png" alt="vs이미지">
                </div>
                <span class="card__desc ellipsis">${writingPopulDto.sec_writ_content}</span>
                <div class="card__info-wrap">
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
                    <span class="card__icon-desc font_blue">${writingPopulDto.sum_vote}</span>
                  </div>
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
                    <span class="card__icon-desc font_yellow">${writingPopulDto.sum_comment}</span>
                  </div>
                </div>
              </a>
            </div>
          </li>
        </c:forEach>
      </ul>
      <ul class="main-sec__list" style="display:none">
        <c:forEach items="${writingDtlDtoList}" var="writingDtlDto">
          <li class="main-sec__list-item">
            <div class="card">
              <a href="/detail?writing_no=${writingDtlDto.writing_no}">
                <span class="card__desc">${writingDtlDto.fir_writ_content}</span>
                <div class="card__vsimg">
                  <img src="resources/img/vs.png" alt="vs이미지">
                </div>
                <span class="card__desc ellipsis">${writingDtlDto.sec_writ_content}</span>
                <div class="card__info-wrap">
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
                    <span class="card__icon-desc font_blue">${writingDtlDto.sum_vote}</span>
                  </div>
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
                    <span class="card__icon-desc font_yellow">${writingDtlDto.sum_comment}</span>
                  </div>
                </div>
              </a>
            </div>
          </li>
        </c:forEach>
      </ul>
      <ul class="main-sec__list" style="display:none">
        <c:forEach items="${writingMyVoteDtoList}" var="writingMyVoteDto">
          <li class="main-sec__list-item">
            <div class="card">
              <a href="/detail?writing_no=${writingMyVoteDto.writing_no}">
                <span class="card__desc">${writingMyVoteDto.fir_writ_content}</span>
                <div class="card__vsimg">
                  <img src="resources/img/vs.png" alt="vs이미지">
                </div>
                <span class="card__desc ellipsis">${writingMyVoteDto.sec_writ_content}</span>
                <div class="card__info-wrap">
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
                    <span class="card__icon-desc font_blue">${writingMyVoteDto.sum_vote}</span>
                  </div>
                  <div class="card__info-area">
                    <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
                    <span class="card__icon-desc font_yellow">${writingMyVoteDto.sum_comment}</span>
                  </div>
                </div>
              </a>
            </div>
          </li>
        </c:forEach>
      </ul>
    </section>
    <c:if test="${toastOn == 'Y'}">
      <div class="toast scene_element">새로운 투표가 만들어졌습니다</div>
    </c:if>
    <a href="/write.html" class="fab">
      투표 만들기
    </a>
  </div>

  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/common.js"></script>
  <script src="resources/js/jsrender.min.js"></script>
  <script id="cardTmpl" type="text/jsrender">
    <li class="main-sec__list-item">
      <div class="card">
        <a href="/detail">
          <span class="card__desc ellipsis">{{:fir_writ_content}}</span>
          <div class="card__vsimg">
            <img src="resources/img/vs.png" alt="vs이미지">
          </div>
          <span class="card__desc ellipsis">{{:sec_writ_content}}</span>
          <div class="card__info-wrap">
            <div class="card__info-area">
              <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
              <span class="card__icon-desc font_blue">{{:sum_vote}}</span>
            </div>
            <div class="card__info-area">
              <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
              <span class="card__icon-desc font_yellow">{{:sum_comment}}</span>
            </div>
          </div>
        </a>
      </div>
    </li>
  </script>
  <script>
    //메인페이지 인피니티스크롤
    (function () {
      var isExecuted = false;
      var page = 2;
      var mainCategory = 1;
      var isFull = false;

      // [D] 여기에 카테고리별 리스트 비동기 처리
      $('.home_header_navlist').on('click', '.home_header_navitem', function (e) {
        //page = 2;
        mainCategory = $(this).val();
      })
      
      var allData = { "page": page, "mainCategory": mainCategory};
      
      $(window).scroll(function (e) {
        var winH = $(window).height();
        var docH = $(document).height();
        var winTop = $(window).scrollTop();

        if (Math.ceil(winTop) >= docH - winH && !isExecuted && !isFull) {
          isExecuted = true;
          showSpinner($('.main-sec__list'));
          $.ajax({
        	  type : 'GET',  
              dataType : 'json', 
              data : allData,
              url: '<c:url value='/getPaigingList' />',
              success: function (data) {
              hideSpinner();
              var tmpl = $.templates('#cardTmpl');
              var html = tmpl.render(data);
              $(html).appendTo($('.main-sec__list'));
              isExecuted = false;
              allData.page +=1; 	  
              
              if(!data.length){
            	  isFull = true;
              }
            },
            error: function (data) {
              console.log(data);
            }
          })
        }
      });
    })();
  </script>
</body>

</html>