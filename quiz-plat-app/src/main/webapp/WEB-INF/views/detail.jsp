<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>상세페이지</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" href="resources/css/keyframes.css">
  <link rel="stylesheet" href="resources/css/pageTransitions.css">
  <link rel="stylesheet" href="resources/css/swiper.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700,700i&display=swap"
    rel="stylesheet">
</head>

<body>
  <div>
    <c:choose>
      <c:when test="${cookie.remember.value != null}">
        <ul>
          ${cookie.remember.value}
        </ul>
      </c:when>
      <c:otherwise>
        쿠키 없음
      </c:otherwise>
    </c:choose>
  </div>

  <div class="wrapper detail_wrap wrapper--white m-scene" id="main">
    <div class="page scene_element scene_element--fadeinright">
      <header class="header_wrap">
        <button class="sp24 back" type="button" onclick="window.history.back(-1);"></button>
        <button class="sp24 complain" type="button" onclick="$(this).addClass('on');"></button>
        <button class="sp24 airplain" type="button"></button>
      </header>
      <div class="swiper-container">
        <div class="swiper-wrapper">
        </div>
      </div>
    </div>
  </div>
  <div class="reply_inputwrap">
    <div class="reply_inputctn">
      <input type="text" class="reply_input"
        onkeyup="$(this).val().length? $(this).siblings('.pencil').addClass('on') : $(this).siblings('.pencil').removeClass('on');">
      <button type="button" class="sp24 pencil"></button>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/swiper.js"></script>
  <script src="resources/js/jsrender.min.js"></script>
  <script src="resources/js/jquery-accordion.js"></script>
  <script src="resources/js/common.js"></script>
  <script id="slideTmpl" type="text/x-jsrender">
  <div class="swiper-slide" id="slide{{:writing_no}}">
    <section class="detail">
      <div class="card__info-wrap">
        <div class="card__info-left">
          <span class="card__info-writer">{{:nickname}}</span>
          <span class="card__info-time">{{:mod_dts}}</span>
        </div>
        <div class="card__info-right">
          <div class="card__info-area">
            <span class="sp00 vote"></span>
            <span class="card__icon-desc font_blue">{{:sum_vote}}</span>
          </div>
          <div class="card__info-area">
            <span class="sp00 comment yellow"></span>
            <span class="card__icon-desc font_yellow">{{:sum_comment}}</span>
          </div>
        </div>
      </div>
      <div class="detail_top card_wrap mt-2">
        <div class="card card--single">
          <label for="before">
            <p class="card__descwrap">
              <span class="card__desc">
                  {{:fir_writ_content}}
              </span>
            </p>
            <p class="card_subdesc">
              <span class="prtg">{{:fir_vote_perc}}%</span>, 나를 제외한 <span class="count">{{:fir_vote_no}}</span>명의 선택
            </p>
            <div class="card__prtg">
              <span class="card__prtgmain">{{:fir_vote_perc}}</span>
              <span class="card__prtgsub"><span class="count">{{:fir_vote_no}}</span>명의선택</span>
            </div>
          </label>
        </div>
        <span class="sp00 vs"></span>
        <div class="card card--single later mt-1">
          <label for="after">
            <p class="card__descwrap">
              <span class="card__desc">
                  {{:sec_writ_content}}
              </span>
            </p>
            <p class="card_subdesc">
              <span class="prtg">{{:sec_vote_perc}}%</span>, 나를 제외한 <span class="count">{{:sec_vote_no}}</span>명의 선택
            </p>
            <div class="card__prtg">
              <span class="card__prtgmain">{{:sec_vote_perc}}</span>
              <span class="card__prtgsub"><span class="count">{{:sec_vote_no}}</span>명의선택</span>
            </div>
          </label>
        </div>
      </div>
      <div class="detail_bottom">
        <button class="detail_btn">본문 펼치기</button>
        <div class="detail_txtareawrap">
          <div class="detail_txtarea">
            {{:content}}
          </div>
        </div>
        <div class="detail_replyarea">
          <ul class="detail_replylist">
            {{for detailCommentList}}
              {{include tmpl="#replyTmpl"/}}
            {{/for}}
          </ul>
        </div>
      </div>
    </section>
  </div>
  </script>
  <script id="replyTmpl" type="text/x-jsrender">
    <li class="detail_replyitem" id="comment{{:comment_no}}">
      <div class="detail_replyheader">
        <button type="button" class="detail_replytit my">{{:nickname}}</button>
        <span class="detail_reply_subtitarea">
          <span class="detail_replytime">{{:mod_dts}}</span>
          {{if vote}}
            {{if vote==1}}
              <span class="detail_replyinfos up">
                <span class="detail_replyuptx">추천 <span class="count">{{:recom_no}}</span></span>
                <button type="button" class="sp00 up on"></button>
                <button type="button" class="sp00 down"></button>
              </span>
            {{/if}}
            {{if vote==2}}
              <span class="detail_replyinfos down">
                <span class="detail_replyuptx">추천 <span class="count">{{:recom_no}}</span></span>
                <button type="button" class="sp00 up"></button>
                <button type="button" class="sp00 down on"></button>
              </span>
            {{/if}}
            {{else}}
            <span class="detail_replyinfos">
              <span class="detail_replyuptx">추천 <span class="count">{{:recom_no}}</span></span>
              <button type="button" class="sp00 up"></button>
              <button type="button" class="sp00 down"></button>
            </span>
          {{/if}}
        </span>
      </div>
      <span class="detail_replycont">
        {{:comment_content}}
      </span>
      <div class="detail_reply_subarea acdo accordion" data-accordion>
        <ul class="detail_reply_subitems acdo_cont" data-content>
          {{for lowCommentDtoList }}
            <li class="detail_reply_subitem" id="lowComment{{:comment_no}}">
              <button type="button" class="detail_replytit">{{:nickname}}</button>
              <span class="detail_reply_subtitarea">
                <span class="detail_replytime">2019.11.27 17:01:45</span>
                {{if vote}}
                  {{if vote==1}}
                    <span class="detail_replyinfos up">
                      <span class="detail_replyuptx">추천 <span class="count">{{:recom_no}}</span></span>
                      <button type="button" class="sp00 up on"></button>
                      <button type="button" class="sp00 down"></button>
                    </span>
                  {{/if}}
                  {{if vote==2}}
                    <span class="detail_replyinfos down">
                      <span class="detail_replyuptx">추천 <span class="count">{{:recom_no}}</span></span>
                      <button type="button" class="sp00 up"></button>
                      <button type="button" class="sp00 down on"></button>
                    </span>
                  {{/if}}
                  {{else}}
                    <span class="detail_replyinfos">
                      <span class="detail_replyuptx">추천 <span class="count">{{:recom_no}}</span></span>
                      <button type="button" class="sp00 up"></button>
                      <button type="button" class="sp00 down"></button>
                    </span>
                {{/if}}
              </span>
              <span class="detail_replycont">
                {{:comment_content}}
              </span>
            </li>
          {{/for}}
        </ul>
        <div class="detail_reply_morewrap acdo_open" data-control>
          <button type="button" class="detail_reply_more">
            <span class="sp00 comment yellow"></span>
            <span class="detail_reply_count">{{:low_comment_num}}</span>
          </button>
        </div>
      </div>
    </li>
  </script>
  <script type="text/javascript">

    //한 페이지 내에서 카드 정보 업데이트
    var updateCardsData = function (cards, json) {
      cards.map(function (index, item) {
        var prtg = $(item).find('.card__prtgmain');
        var count = $(item).find('.count');
        prtg.text(json.votePerc[index]);
        count.text(json.voteNoArr[index]);
      });
      cards.closest('.swiper-slide').find('.vote').next('.card__icon-desc').text(json.totalVoteNum);
    }

    /*카드 선택시 UI변경*/
    var changeDetailUI = function (e) {
      var cardwrap = $(e.target).closest('.card_wrap');
      var card = $(e.target).closest('.card');
      var cards = cardwrap.find('.card');
      var bottomInfo = cardwrap.siblings('.detail_bottom');
      cardwrap.addClass('on');
      bottomInfo.addClass('on');
      cards.removeClass('on');
      card.addClass('on');
    };

    /* 투표 비동기 처리 */
    $('.wrapper').on('click', '.card', function (e) {
      var cardWrap = $(e.currentTarget).closest('.card_wrap');
      if (cardWrap.hasClass('on')) return;
      changeDetailUI(e);
      var cards = cardWrap.find('.card');
      var voteNum = cards.index($(e.currentTarget)) + 1;
      var slideId = $(e.currentTarget).closest('.swiper-slide').attr('id');
      var writingNo = getNumberInStr(slideId);
      $.ajax({
        url: URL_UPDATE_VOTECOUNT,
        method: 'POST',
        data: {
          voteNum,
          writingNo
        },
        dataType: "json",
        success: function (data) {
          updateCardsData(cards, data);
        },
        error: function (request, status, error) {
          console.log(request, status, error);
        }
      })
    });

    $(function () {
      var oAjax = new ssj.util.ajax();
      var oSpinner = new ssj.util.spinner();
      oSpinner.setTarget($('.swiper-container'));
      oSpinner.show(); //페이지 처음 진입시 스피너
      //무한 스와이프
      var oSwiper = new ssj.util.swiper({
        direction: 'horizontal',
        slidesPerView: 1.1,
        centeredSlides: true,
        threshold: 10,
        on: {
          init: function () {
            setInitialData();
            oSpinner.hide();
          },
          slideChange: function () {
            if (oSwiper.getActiveIndex() === oSwiper.getTotalCount() - 2) {
              makeSlideItems().then((items) => {
                oSwiper.appendItem(items);
              });
            }
            activateAccordionInSlide(oSwiper.getActiveSlide());
          }
        }
      });

      function setInitialData() {
        makeFirstItem()
          .then((item) => {
            oSwiper.appendItem(item);
            activateAccordionInSlide(oSwiper.getActiveSlide());
            return makeSlideItems();
          })
          .then((item) => {
            oSwiper.appendItem(item);
          });
      }


      function makeFirstItem() {
        var mainWritingNo = getNumberInStr(window.location.search);
        var requestData = { writingNo: mainWritingNo }
        return oAjax.sendRequest(URL_READ_FIRST_SLIDE_DATA, requestData, ID_TMPL_SLIDE);
      }

      function makeSlideItems() {
        var writing_no = getNumberInStr(window.location.search);
        var page = oAjax.getCurrentPageNum(PAGE_NAME_SWIPE);
        var requestData = { page, writing_no };
        return oAjax.sendRequest(URL_READ_SLIDE_DATA, requestData, ID_TMPL_SLIDE, PAGE_NAME_SWIPE);
      }

      //현재 슬라이드 내 대댓글 펼침 기능 활성화
      function activateAccordionInSlide(slide) {
        $(slide).find('.accordion').accordion({
          "transitionSpeed": 700,
          "transitionEasing": "cubic-bezier(0.23, 1, 0.32, 1)"
        });
      }

    });


    /* 본문 펼치기 */
    $('.swiper-wrapper').on('click', '.detail_btn', function (e) {
      $(this).siblings('.detail_txtareawrap').toggle();
    })

    /* 댓글 비동기 입력*/
    $('.reply_submit').on('click', function (e) {
      var input = $(this).siblings('.reply_input');
      var target = $('.detail_replylist');
      var replytx = input.val();
      loadData()
      $.ajax({
        url: URL_CREATE_COMMENT,
        data: replytx,
        success: function (data) {
          var tmpl = $.templates('#replyTmpl');
          var html = tmpl.render(data);
          target.append(html);
        },
        error: function (data) {
          console.log(data);
        }
      })

      scrollToBottom($('.swiper-container'));
      input.val('');
    })

    //댓글 펼치기 : UI변경
    $('body').on('click', 'button.detail_reply_more', function (e) {
      if ($(e.currentTarget).children('.detail_reply_count').text() == 0) { return false; }
      $(e.currentTarget).toggleClass('on');
      $(e.currentTarget).children('.comment').toggleClass('yellow');
    });

    //업버튼, 다운버튼 UI변경
    $('body').on('click', '.detail_replyinfos', function (e) {
      if ($(e.currentTarget).hasClass('up') || $(e.currentTarget).hasClass('down')) return false;
      var btn = $(e.target);
      var btnWrap = $(e.currentTarget);
      btn.hasClass('up') ? btnWrap.addClass('up') : btnWrap.addClass('down');
      btn.addClass('on');
    });

  </script>
</body>

</html>
