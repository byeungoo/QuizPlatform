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
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" href="resources/css/keyframes.css">
  <link rel="stylesheet" href="resources/css/pageTransitions.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
  <!-- Global site tag (gtag.js) - Google Analytics -->
  <script async src="https://www.googletagmanager.com/gtag/js?id=UA-146761641-1"></script>
  <script>
    window.dataLayer = window.dataLayer || [];
    function gtag() { dataLayer.push(arguments); }
    gtag('js', new Date());

    gtag('config', 'UA-146761641-1');
  </script>
  <script async src="https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
  <script>
    (adsbygoogle = window.adsbygoogle || []).push({
      google_ad_client: "ca-pub-3747891131925558",
      enable_page_level_ads: true
    });
  </script>
</head>

<body>
  <header class="home_header">
    <ul class="home_header_navlist">
      <li class="home_header_navitem on" value="0">인기</li>
      <li class="home_header_navitem" value="1">신규</li>
      <li class="home_header_navitem" value="2">활동</li>
    </ul>
    <div class="row_scroll">
      <div class="mypage" style="display:none;">
        <button type="button" class="mypage_myvote rdo_toggle" value="2">내가 투표한 글</button>
        <button type="button" class="mypage_mycomment rdo_toggle" value="3">내가 댓글단 글</button>
        <button type="button" class="mypage_mycomment rdo_toggle" value="4">내 게시글</button>
      </div>
    </div>
  </header>
  <div class="wrapper m-scene">
    <section class="main-sec">
      <ul class="main-sec__list">
      </ul>
      <ul class="main-sec__searchlist">
        
      </ul>
    </section>
  </div>
  <footer>
    <div class="bottom_navbar">
      <button type="button" class="bottom_navbaritem sp42 search ">
      </button>
      <button type="button" class="bottom_navbaritem sp42 plus" onclick="$('#_write_trigger').click();">
      </button>
      <button type="button" class="bottom_navbaritem sp42 lock ">
      </button>
      <button type="button" class="bottom_navbaritem sp42 person" style="display:none;">
      </button>
    </div>
  </footer>

  <!-- 회원 가입 모달창-->
  <div class="modal" id="join" style="display:none;width:95vw;">
    <form id="_join_form">
      <div class="modal_ctn">
        <div class="modal_header">
          <h1 class="modal_header_tit">회원가입</h1>
        </div>
        <div class="modal_cont">
            <div class="modal_inp_wrap">
              <input type="text" class="modal_inp nickname" placeholder="닉네임" name="nickname" value="">
              <span class="sp00 check"></span>
            </div>
            <div class="modal_inp_wrap">
              <input type="text" class="modal_inp" placeholder="아이디" name="user_id">
              <span class="sp00 check"></span>
            </div>
            <div class="modal_inp_wrap">
              <input type="password" class="modal_inp" placeholder="비밀번호" name="pwd" autocomplete="new-password">
              <span class="sp00 check"></span>
            </div>
            <div class="modal_inp_wrap">
              <input type="password" class="modal_inp" placeholder="비밀번호 확인" autocomplete="new-password">
              <span class="sp00 check"></span>
            </div>
        </div>
        <div class="modal_footer">
            <button class="modal_submit modal_footbtn" disabled="true">필수 항목을 작성해주세요</button>
        </div>
      </div>
      <button class="modal_close ico_close"></button>
    </form>
  </div>
  <a href="#join" id="join_trigger" class="blind" rel="leanModal">회원가입창</a>

  <!-- 로그인 모달창-->
  <div class="modal" id="login" style="display:none;width:95vw;">
    <form id="_login_form">
      <div class="modal_ctn">
        <div class="modal_header">
          <h1 class="modal_header_tit">로그인</h1>
          <button type="button" class="modal_header_tittx">회원가입<span class="sp00 arrow_right_blue"></span></button>
        </div>
        <div class="modal_cont">
          <div class="modal_inp_wrap">
            <input type="text" class="modal_inp" placeholder="아이디" name="user_id">
            <span class="sp00 check"></span>
          </div>
          <div class="modal_inp_wrap">
            <input type="password" class="modal_inp" placeholder="비밀번호" name="pwd" autocomplete="new-password">
            <span class="sp00 check"></span>
          </div>
          <div class="modal_chk_wrap">
            <input type="checkbox" id="_chk_auto_login" name="rememberId" class="sp00 check_fill">
            <label for="_chk_auto_login">자동로그인</label>
          </div>
        </div>
        <div class="modal_footer">
          <button class="modal_submit modal_footbtn">필수 항목을 작성해주세요</button>
        </div>
      </div>
      <button class="modal_close ico_close"></button>
    </form>
  </div>

  <!-- 검색창 -->
  <a href="#_searchbar" id="_searchbar_trigger" class="blind" rel="leanModal">검색창</a>
  <div class="modal ty_search" id="_searchbar" style="display:none;width:100vw;">
    <div class="modal_ctn">
      <div class="modal_cont">
        <input type="text" class="modal_inp">
        <span class="sp42 search"></span>
      </div>
    </div>
    <button class="modal_close ico_close blind"></button>
  </div>

  <!-- 글작성창-->
  <a href="#_write" id="_write_trigger" class="blind" rel="leanModal">글작성창</a>
  <div class="modal ty_write" id="_write" style="display:none;">
    <div class="modal_ctn">
      <div class="modal_cont">
        <form onsubmit="createWrite();return false;">
          <div class="write_area">
            <input type="text" class="write_inp" id="_write_front" maxlength="50">
            <label for="_write_front" class="write_tit">닥전 : 최대 50자</label>
          </div>
          <div class="write_area">
            <input type="text" class="write_inp" id="_write_back" maxlength="50">
            <label for="_write_back" class="write_tit">닥후 : 최대 50자</label>
          </div>
          <div class="write_area ty_wide">
            <textarea class="write_inp " id="_write_textarea"></textarea>
            <label for="_write_textarea">
              <h2 class="write_tit">설명</h2>
              <span class="write_cont">타인에게 불쾌감을 주는 글은 작성자의 동의 없이 삭제될 수 있으며 작성자는
                서비스 이용이 정지될 수 있습니다.
              </span>
            </label>
          </div>
          <button class="write_submit" onclick="$(this).toggleClass('on');">만들기</button>
        </form>
        <button class="modal_close ico_close" style="top:18px;right:11px;"></button>
      </div>
    </div>
    <button class="modal_close ico_close blind"></button>
  </div>


  <script id="cardTmpl" type="text/jsrender">
    <li class="main-sec__list-item">
      <a href="/detail.html?writing_no={{:writing_no}}">
        <div class="card">
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
            <span class="card__desc ellipsis">{{:fir_writ_content}}</span>
            <div class="card__vsimg">
              <span class="sp00 vs"></span>
            </div>
            <span class="card__desc ellipsis">{{:sec_writ_content}}</span>
        </div>
      </a>
    </li>
  </script>
  
  <a href="#login" id="login_trigger" class="blind" rel="leanModal">로그인창</a>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/jquery.leanModal.min.js"></script>
  <script src="resources/js/constant.js"></script>
  <script src="resources/js/common.js"></script>
  <script src="resources/js/jsrender.min.js"></script>
  <script src="resources/js/home.js"></script>
</body>

</html>
