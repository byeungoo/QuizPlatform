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
      <li class="home_header_navitem on" value="0">인기</li>
      <li class="home_header_navitem" value="1">신규</li>
      <li class="home_header_navitem" value="2">활동</li>
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
        <a href="/detail?writing_no={{:writing_no}}">
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

    $(function () {
      //인피니티 스크롤 위치 기억
      $('.main-sec__list').on('click', 'a', function (e) {
        var nextPage = $(e.currentTarget).attr('href');
        var ifrWrapper = $('<div/>', {
          class: "iframe_wrapper",
          css: {
            '-webkit-overflow-scrolling': touch,
            "overflow": 'hidden auto'
          }
        });
        var iframe = $('<iframe/>', {
          src: nextPage,
          scrolling: 'yes',
          css: {
            height: "100vh",
            width: "100vw"
          }
        });
        ifrWrapper.append(iframe);
        $('body').children().hide();
        $('body').prepend(ifrWrapper);
        e.preventDefault();
        window.history.pushState('', '', nextPage);
      });
      window.onpopstate = function (e) {
        $('body').find('.iframe_wrapper').remove();
        $('body').children().show();
      }
    });

    //메인페이지 JS 모음
    (function () {
      var isExecuted = false;
      var mainDatas = [];
      var cateNum = 0;
      var mainCardList = $('.main-sec__list');
      var tabcount = $('.home_header_navitem').length;
      for (var i = 0; i < tabcount; i++) {
        //카테고리 별로 필요한 데이터를 배열로 관리
        mainDatas.push({
          'prevPos': 0, //스크롤 탑 위치
          'cards': null, //리스트의 카드들
          'curPage': 2, //다음에 불러와야할 페이지 번호
          'isFull': false //카테고리의 글을 모두 불러왔는지?
        });
      }

      //상단 메뉴 탭 : 탭 이동 시각적 효과 및 초기 데이터 비동기 로드
      $('.home_header_navlist').on('click', '.home_header_navitem', function (e) {
        toggleTab(e);
        savePrevState();
        setCurrentState(e);
      });

      //인피니티스크롤
      $(window).scroll(function (e) {
        var winH = $(window).height();
        var docH = $(document).height();
        var winTop = $(window).scrollTop();
        if (Math.ceil(winTop) >= docH - winH && !isExecuted && !mainDatas[cateNum].isFull) {
          loadData(cateNum);
        }
      });

      var savePrevState = function () {
        var data = {};
        data['prevPos'] = $(window).scrollTop();
        data['cards'] = mainCardList.children().detach();
        $.extend(mainDatas[cateNum], data);
      };

      var setCurrentState = function (e) {
        cateNum = $(e.target).val();
        mainDatas[cateNum] ? refreshDatas() : loadData(cateNum);
      };

      var restoreScroll = function () {
        $('html, body').animate({ scrollTop: mainDatas[cateNum].prevPos }, 0);
      }

      var refreshDatas = function () { //기존 배열에 있는 데이터를 세팅
        mainCardList.append((mainDatas[cateNum].cards));
        restoreScroll();
      };

      var loadData = function (cateNum) { //새롭게 데이터를 가져와서 세팅
        isExecuted = true;
        var sendData = { "page": mainDatas[cateNum].curPage, "mainCategory": cateNum };
        showSpinner(mainCardList);
        $.ajax({
          url: '<c:url value='/getPagingList' />',	
          type: 'GET',
          dataType: 'json',
          data: sendData,
          success: function (data) {
            hideSpinner();
            appendCardsToList(data);
            mainDatas[cateNum].curPage++;
            if (!data.length) {
              mainDatas[cateNum].isFull = true;
            }
          },
          error: function (data) {
            console.log(data);
          }
        });
      }

      var appendCardsToList = function (data) { //새롭게 데이터를 가져와서 리스트에 세팅
        var tmpl = $.templates('#cardTmpl');
        var html = tmpl.render(data);
        $(html).appendTo(mainCardList);
        isExecuted = false;
      }

    })();
  </script>
</body>

</html>
