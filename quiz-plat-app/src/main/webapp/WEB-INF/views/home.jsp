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
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
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
  <footer>
    <div class="bottom_navbar">
      <button type="button" class="bottom_navbaritem sp42 search toggle_on_off">
      </button>
      <button type="button" class="bottom_navbaritem sp42 plus toggle_on_off">
      </button>
      <button type="button" class="bottom_navbaritem sp42 lock toggle_on_off">
      </button>
    </div>
  </footer>
  <!-- 회원 가입 모달창-->
  <div class="modal" id="join" style="display:none;width:85vw;">
    <form action="http://pickvs.com/login">
      <button class="modal_close"><i class="fas fa-times fa-2x"></i></button>
      <div class="modal_cont">
        <div class="modal_inparea">
          <label for="join1" class="modal_inp_tit">아이디</label>
          <input type="text" class="modal_inp" placeholder="아이디를 입력해주세요" name="user_id" id="join1">
        </div>
        <div class="modal_inparea">
          <label for="join2" class="modal_inp_tit">닉네임</label>
          <input type="text" class="modal_inp" placeholder="행복한 돼지" name="" id="join2">
        </div>
        <div class="modal_inparea">
          <label for="join3" class="modal_inp_tit">비밀번호</label>
          <input type="password" class="modal_inp" placeholder="비밀번호" id="join3">
        </div>
        <div class="modal_inparea">
          <label for="join4" class="modal_inp_tit">비밀번호 확인</label>
          <input type="password" class="modal_inp" placeholder="한번 더 입력해주세요" id="join4">
        </div>
        <button class="modal_submit modal_footbtn">회원가입</button>
      </div>
    </form>
  </div>
  <a href="#join" id="join_trigger" class="blind" rel="leanModal">회원가입창</a>

  <!-- 로그인 모달창-->
  <div class="modal" id="login" style="display:none;width:85vw;">
    <button class="modal_close"><i class="fas fa-times fa-2x"></i></button>
    <form action="http://pickvs.com/login" method="POST">
      <div class="modal_cont">
        <div class="modal_inparea">
          <label for="login1" class="modal_inp_tit">아이디</label>
          <input type="text" class="modal_inp" placeholder="아이디를 입력해주세요" name="user_id" id="login1">
        </div>
        <div class="modal_inparea">
          <label for="login2" class="modal_inp_tit">비밀번호</label>
          <input type="password" class="modal_inp" placeholder="비밀번호" name="pwd" id="login2">
        </div>
        <div class="modal_inparea">
          <input type="checkbox" class="modal_chk" id="login_chk" name="rememberId">
          <label for="login_chk" class="modal_chk_tit">자동로그인</label>
        </div>
        <button class="modal_footbtn modal_submit">로그인</button>
        <button class="modal_footbtn" onclick="$('.modal_close').click(); $('#join_trigger').click();">회원가입</button>
      </div>
    </form>
  </div>
  <a href="#login" id="login_trigger" class="blind" rel="leanModal">로그인창</a>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/jquery.leanModal.min.js"></script>
  <script src="resources/js/constant.js"></script>
  <script src="resources/js/common.js"></script>
  <script src="resources/js/jsrender.min.js"></script>
  <script id="cardTmpl" type="text/jsrender">
    <li class="main-sec__list-item">
      <div class="card">
        <a href="/detail?writing_no={{:writing_no}}">
          <span class="card__desc ellipsis">{{:fir_writ_content}}</span>
          <div class="card__vsimg">
            <span class="sp00 vs"></span>
          </div>
          <span class="card__desc ellipsis">{{:sec_writ_content}}</span>
          <div class="card__info-wrap">
            <div class="card__info-area">
              <span class="sp00 vote"></span>
              <span class="card__icon-desc font_blue">{{:sum_vote}}</span>
            </div>
            <div class="card__info-area">
              <span class="sp00 comment yellow"></span>
              <span class="card__icon-desc font_yellow">{{:sum_comment}}</span>
            </div>
          </div>
        </a>
      </div>
    </li>
  </script>
  <script>

    $('.bottom_navbaritem.lock').on('click',function(){
      $('#login_trigger').click();
      $.scrollLock(true);
    });
    $('body').on('click','.modal_close',function(){
      $.scrollLock(false);
      $('.bottom_navbar').children().removeClass('on').removeClass('off');
    });
    var oSsjViewInfinite;
    $(function () {
      $("a[rel*=leanModal]").leanModal({ overlay: 0.4, slideinUp: 'join' }); //a태그에 모달 켜기 기능 추가
      //$("#login_trigger").click();
      //인피니티 스크롤 위치 기억
      $('.main-sec__list').on('click', 'a', function (e) {
        var nextPage = $(e.currentTarget).attr('href');
        var ifrWrapper = $('<div/>', {
          class: "iframe_wrapper",
          css: {
            '-webkit-overflow-scrolling': 'touch',
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
        window.history.pushState('', '', nextPage);
        e.preventDefault();
      });
      window.onpopstate = function (e) {
        $('body').find('.iframe_wrapper').remove();
        $('body').children().show();
      }

      oSsjViewInfinite = new ssj.view.infiniteScroll();
      //인피니티 스크롤 : 탭 전환시 스크롤 위치 기억
      $('.home_header_navlist').on('click', '.home_header_navitem', function (e) {
        oSsjViewInfinite.saveCurrentState();
        toggleTab(e);
        oSsjViewInfinite.switchCategory();
        scrollByPosition(oSsjViewInfinite.getCurrentScrollTop());
      });
    });
    
  </script>
</body>

</html>
