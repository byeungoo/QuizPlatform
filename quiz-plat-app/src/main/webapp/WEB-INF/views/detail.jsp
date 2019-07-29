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
          <div class="swiper-slide">
            <section class="detail">
              <button type="button" class="detail__119">
              </button>
              <div class="card__info-wrap">
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
                  <span class="card__icon-desc font_blue">1,945</span>
                </div>
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
                  <span class="card__icon-desc font_yellow">1,988</span>
                </div>
              </div>
              <div class="detail_top card_wrap mt-2 mt-2">
                <div class="card card--single">
                  <label for="before">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        50글자테스트중이다50글자테스트중이다50글자테스트중이다50글자테스트중이다50글자테스트중이다
                      </span>
                    </p>
                    <span class="card_subdesc">
                      나를 포함한 141명의 선택
                    </span>
                    <span class="card__prtg">37%</span>
                  </label>
                </div>
                <span class="detail_vs"></span>
                <div class="card card--single mt-1">
                  <label for="after">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        제목이 한줄이라도 크기 유지
                      </span>
                    </p>
                    <span class="card_subdesc later">
                      나를 제외한 243명의 선택
                    </span>
                    <span class="card__prtg later">63%</span>
                  </label>
                </div>
              </div>
              <div class="detail_bottom">
                <button class="detail_btn">본문 펼치기</button>
                <div class="detail_txtareawrap">
                  <div class="detail_txtarea">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa accusamus voluptates aliquid
                    impedit,
                    fuga
                    fugit voluptatum non omnis autem ullam placeat. Deserunt enim commodi iure quisquam culpa totam
                    cupiditate
                    molestias.
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora, accusamus amet. Perspiciatis
                    vitae
                    maxime
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam possimus sunt repudiandae dolorum
                    rerum
                    vel
                    voluptatum, corporis aspernatur cum. Eos ipsam eaque iste laborum optio doloremque fugiat sint
                    praesentium
                    magnam.
                  </div>
                </div>
                <div class="detail_replyarea">
                  <ul class="detail_replylist">
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                  </ul>
                </div>
              </div>
            </section>
          </div>
          <div class="swiper-slide">
            <section class="detail">
              <button type="button" class="detail__119">
              </button>
              <div class="card__info-wrap">
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
                  <span class="card__icon-desc font_blue">1,945</span>
                </div>
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
                  <span class="card__icon-desc font_yellow">1,988</span>
                </div>
              </div>
              <div class="detail_top card_wrap mt-2 mt-2">
                <div class="card card--single">
                  <label for="before">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        50글자테스트중이다50글자테스트중이다50글자테스트중이다50글자테스트중이다50글자테스트중이다
                      </span>
                    </p>
                    <span class="card_subdesc">
                      나를 포함한 141명의 선택
                    </span>
                    <span class="card__prtg">37%</span>
                  </label>
                </div>
                <span class="detail_vs"></span>
                <div class="card card--single mt-1">
                  <label for="after">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        제목이 한줄이라도 크기 유지
                      </span>
                    </p>
                    <span class="card_subdesc later">
                      나를 제외한 243명의 선택
                    </span>
                    <span class="card__prtg later">63%</span>
                  </label>
                </div>
              </div>
              <div class="detail_bottom">
                <button class="detail_btn">본문 펼치기</button>
                <div class="detail_txtareawrap">
                  <div class="detail_txtarea">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa accusamus voluptates aliquid
                    impedit,
                    fuga
                    fugit voluptatum non omnis autem ullam placeat. Deserunt enim commodi iure quisquam culpa totam
                    cupiditate
                    molestias.
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora, accusamus amet. Perspiciatis
                    vitae
                    maxime
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam possimus sunt repudiandae dolorum
                    rerum
                    vel
                    voluptatum, corporis aspernatur cum. Eos ipsam eaque iste laborum optio doloremque fugiat sint
                    praesentium
                    magnam.
                  </div>
                </div>
                <div class="detail_replyarea">
                  <ul class="detail_replylist">
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                  </ul>
                </div>
              </div>
            </section>
          </div>
          <div class="swiper-slide">
            <section class="detail">
              <button type="button" class="detail__119">
              </button>
              <div class="card__info-wrap">
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/vote_count.png" width="16px" height="16px" alt="투표수아이콘">
                  <span class="card__icon-desc font_blue">1,945</span>
                </div>
                <div class="card__info-area">
                  <img class="card__icon" src="resources/img/comment.png" width="16px" height="16px" alt="댓글수아이콘">
                  <span class="card__icon-desc font_yellow">1,988</span>
                </div>
              </div>
              <div class="detail_top card_wrap mt-2 mt-2">
                <div class="card card--single">
                  <label for="before">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        50글자테스트중이다50글자테스트중이다50글자테스트중이다50글자테스트중이다50글자테스트중이다
                      </span>
                    </p>
                    <span class="card_subdesc">
                      나를 포함한 141명의 선택
                    </span>
                    <span class="card__prtg">37%</span>
                  </label>
                </div>
                <span class="detail_vs"></span>
                <div class="card card--single mt-1">
                  <label for="after">
                    <p class="card__descwrap">
                      <span class="card__desc">
                        제목이 한줄이라도 크기 유지
                      </span>
                    </p>
                    <span class="card_subdesc later">
                      나를 제외한 243명의 선택
                    </span>
                    <span class="card__prtg later">63%</span>
                  </label>
                </div>
              </div>
              <div class="detail_bottom">
                <button class="detail_btn">본문 펼치기</button>
                <div class="detail_txtareawrap">
                  <div class="detail_txtarea">
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa accusamus voluptates aliquid
                    impedit,
                    fuga
                    fugit voluptatum non omnis autem ullam placeat. Deserunt enim commodi iure quisquam culpa totam
                    cupiditate
                    molestias.
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Tempora, accusamus amet. Perspiciatis
                    vitae
                    maxime
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Nam possimus sunt repudiandae dolorum
                    rerum
                    vel
                    voluptatum, corporis aspernatur cum. Eos ipsam eaque iste laborum optio doloremque fugiat sint
                    praesentium
                    magnam.
                  </div>
                </div>
                <div class="detail_replyarea">
                  <ul class="detail_replylist">
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                    <li class="detail_replyitem">
                      <span class="detail_replytit">익명</span>
                      <span class="detail_replycont">
                        당연히 100억이지 조선시대 왕들 전부 단명한거 모르는 부분?
                        전자 찍으신 분들 채소 국사시간에 졸으신 분들 ㅋ
                      </span>
                    </li>
                  </ul>
                </div>
              </div>
            </section>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/common.js"></script>
  <script src="resources/js/swiper.js"></script>
  <script type="text/javascript">
    /*카드 선택시 UI변경*/
    $('.card').on('click', function (e) {
      var cardwrap = $(e.target).closest('.card_wrap');
      var cards = cardwrap.find('.card');
      var bottomInfo = cardwrap.siblings('.detail_bottom');
      var nowIdx = cards.index(this);
      cardwrap.addClass('on');
      bottomInfo.addClass('on');
      cards.removeClass('on');
      $(this).addClass('on');
    })
    $('.detail_btn').on('click', function (e) {
      $(this).siblings('.detail_txtareawrap').toggle();
    })

    var mySwiper = new Swiper('.swiper-container', {
      direction: 'horizontal',
      slidesPerView: 1.1,
      centeredSlides: true,
      threshold: 15,
      on: {
        'init': function (e) {
          this.container = $('.swiper-container');
        },
        'sliderMove': function (e) {
          $('.swiper-slide-prev .detail_bottom').css('visibility', 'visible');
          $('.swiper-slide-next .detail_bottom').css('visibility', 'visible');
        },
        'slideChangeTransitionEnd': function (e) {
          $('html, body').scrollTop(0);
          $('.swiper-slide-prev .detail_bottom').css('visibility', 'hidden');
          $('.swiper-slide-active .detail_bottom').css('visibility', 'visible');
          $('.swiper-slide-next .detail_bottom').css('visibility', 'hidden');
        }
      }
    });
  </script>
</body>

</html>