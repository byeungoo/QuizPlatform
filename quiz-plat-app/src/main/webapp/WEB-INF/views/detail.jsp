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
        <div class="header_left">
          <img src="resources/img/Back@2x.png" alt="뒤로가기버튼" width="16px" height="24px" onclick="window.history.back();">
        </div>
        <div class="header_center"></div>
        <div class="header_right"></div>
      </header>
      <input type="hidden" id="writing_no" name="writing_no" value=${writingDtlDto.writing_no}>
      <div class="swiper-container">
        <div class="swiper-wrapper">
          <c:forEach items="${detailDto.detailWritingList}" var="detailWriting">
          <div class="swiper-slide" id=${detailWriting.writing_no}>
            <section class="detail">
              <button type="button" class="detail__119">
              </button>
              <div class="card__info-wrap">
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
                  <span class="card__icon-desc font_blue">${detailWriting.sum_vote}</span>
                </div>
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
                  <span class="card__icon-desc font_yellow">${detailWriting.sum_comment}</span>
                </div>
              </div>
              <div class="detail_top card_wrap mt-2">
                <div class="card card--single">
                  <label for="before">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        ${detailWriting.fir_writ_content}
                      </span>
                    </p>
                    <span class="card_subdesc">
                      나를 포함한 141명의 선택
                    </span>
                    <span class="card__prtg">${detailWriting.fir_vote_perc}%</span>
                  </label>
                </div>
                <span class="detail_vs"></span>
                <div class="card card--single mt-1">
                  <label for="after">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        ${detailWriting.sec_writ_content}
                      </span>
                    </p>
                    <span class="card_subdesc later">
                      나를 제외한 243명의 선택
                    </span>
                    <span class="card__prtg later">${detailWriting.sec_vote_perc}%</span>
                  </label>
                </div>
              </div>
              <div class="detail_bottom">
                <button class="detail_btn">본문 펼치기</button>
                <div class="detail_txtareawrap">
                  <div class="detail_txtarea">
                    ${detailWriting.content}
                  </div>
                </div>
                <div class="detail_replyarea">
                  <ul class="detail_replylist">
                    <c:forEach items="${detailDto.detailCommentList}" var="entry" varStatus="status">
                      <c:if test="${entry.key eq detailWriting.writing_no}">
                        <li class="detail_replyitem">
						  <c:forEach items="${entry.value}" var="item" varStatus="status">
						    <span class="detail_replytit">${item.nickname}</span>
                            <span class="detail_replycont">
                              ${item.comment_content}
                            </span>
						  </c:forEach>
                        </li>
                      </c:if>
                    </c:forEach>
                  </ul>
                </div>
              </div>
            </section>
          </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
  <div class="reply_inputwrap">
    <input type="text" class="reply_input" placeholder="댓글을 입력하세요">
    <button type="button" class="reply_submit">제출</button>
  </div>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/common.js"></script>
  <script src="resources/js/swiper.js"></script>
  <script src="resources/js/jsrender.min.js"></script>
  <script src="resources/js/jquery-accordion.js"></script>
  <script id="slideTmpl" type="text/jsrender">
    <div class="swiper-slide" id={{:writing_no}}>
        <section class="detail">
          <button type="button" class="detail__119">
          </button>
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
          <div class="detail_top card_wrap mt-2 mt-2">
            <div class="card card--single">
              <label for="before">
                <p class="card__descwrap">
                  <span class="card__desc">
                    {{:fir_writ_content}}
                  </span>
                </p>
                <span class="card_subdesc">
                  나를 포함한 {{:fir_vote_no}}명의 선택
                </span>
                <span class="card__prtg">{{:card_top_prtg}}</span>
              </label>
            </div>
            <span class="detail_vs"></span>
            <div class="card card--single mt-1">
              <label for="after">
                <p class="card__descwrap">
                  <span class="card__desc">
                    {{:sec_writ_content}}
                  </span>
                </p>
                <span class="card_subdesc later">
                  나를 제외한 {{:sec_vote_no}}명의 선택
                </span>
                <span class="card__prtg later">{{:card_bottom_prtg}}</span>
              </label>
            </div>
          </div>
          <div class="detail_bottom">
            <button class="detail_btn">본문 펼치기</button>
            <div class="detail_txtareawrap">
              <div class="detail_txtarea">
                {{:detail_txtarea}}
              </div>
            </div>
            <div class="detail_replyarea">
              <ul class="detail_replylist">
                {{for detailCommentList }}
                  <li class="detail_replyitem">
                    <span class="detail_replytit">{{>nickname}}</span>
                    <span class="detail_replycont">
                      {{>comment_content}}
                    </span>
                  </li>
                {{/for}}
              </ul>
            </div>
          </div>
        </section>
      </div>
  </script>
  <script id="replyTmpl" type="text/jsrender">
    <li class="detail_replyitem">
      <span class="detail_replytit">{{:nickname}}</span>
      <span class="detail_replycont">
        {{:comment_content}}
      </span>
      <span class="detail_replyinfos">
        <span class="detail_replyup"></span>
        <span class="detail_replyuptx">{{:upcount}}</span>
        <span class="detail_replydown"></span>
        <span class="detail_replydowntx">{{:downcount}}</span>
      </span>
    </li>
  </script>
  <script type="text/javascript">

    /*카드 선택시 UI변경*/
    $('.swiper-wrapper').on('click', '.card', function (e) {
      var cardwrap = $(e.target).closest('.card_wrap');
      var cards = cardwrap.find('.card');
      var bottomInfo = cardwrap.siblings('.detail_bottom');
      var nowIdx = cards.index(this);
      cardwrap.addClass('on');
      bottomInfo.addClass('on');
      cards.removeClass('on');
      $(this).addClass('on');
    });

    /* 본문 펼치기 */
    $('.swiper-wrapper').on('click', '.detail_btn', function (e) {
      $(this).siblings('.detail_txtareawrap').toggle();
    })

    var mySwiper = new Swiper('.swiper-container', {
      direction: 'horizontal',
      slidesPerView: 1.1,
      centeredSlides: true,
      threshold: 15
    });
    
    (function () {
      var page = 2;
      var writing_no = ${writing_no};
      var paramData = { "page": page, "writing_no": writing_no};
      var isFull = false;

      /* 무한 스와이프 구현 */
      mySwiper.on('slideChange', function (e) {
        var curIdx = mySwiper.activeIndex;
        var total  = mySwiper.slides.length;
        if (curIdx === total - 2 && !isFull) {
          $.ajax({
            type : 'GET',  
            dataType : 'json',
            url: '<c:url value='/getDetailDtoList' />',
            data:  paramData,
            success: function (data) {
              var tmpl = $.templates('#slideTmpl');
              var html = tmpl.render(data);
              mySwiper.appendSlide(html);
              paramData.page += 1;
              
              if(!data.length){
            	  isFull = true;
              }
            },
            error: function (data) {
              console.log(data);
            }
          });
        } 
      });
    })();
    
    /* 댓글 비동기 입력*/
    $('.reply_submit').on('click', function (e) {
      var activeSlide = $('.swiper-slide.swiper-slide-active');
      var input = $(this).siblings('.reply_input');
      var target = activeSlide.find('.detail_replylist');
      var replytx = input.val();
      var writingNo = activeSlide.attr('id');
      var commentData = { "writingNo": writingNo, "replytx": replytx};
          
      $.ajax({
    	type : 'POST',
    	dataType : 'json',
        url: '<c:url value='/writeComment' />',
        data: commentData,
        success: function (data) {
          setTimeout(function (e) {
            $('.swiper-container').animate({ scrollTop: $(document).height() }, 0);
          }, 0);
          var tmpl = $.templates('#replyTmpl');
          var html = tmpl.render(data);
          target.append(html);  
        },
        error: function (data) {
          console.log(data);   
        }
      })
      input.val('');
    })

    //댓글 펼치기 기능
    $('.accordion').accordion({
      "transitionSpeed": 700,
      "transitionEasing": "cubic-bezier(0.23, 1, 0.32, 1)"
    });


  </script>
</body>

</html>
