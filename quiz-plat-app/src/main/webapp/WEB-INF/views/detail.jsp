<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>닥전닥후(pickvs.com)</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" href="resources/css/keyframes.css">
  <link rel="stylesheet" href="resources/css/pageTransitions.css">
  <link rel="stylesheet" href="resources/css/swiper.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700,700i&display=swap"
    rel="stylesheet">
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
  <section class="wrapper detail_wrap wrapper--white m-scene" id="main">
    <div class="page scene_element scene_element--fadeinright">
      <header class="header_wrap">
        <button class="sp24 back" type="button" onclick="window.history.back();"></button>
        <button class="sp24 complain" type="button"></button>
      </header>
      <div class="swiper-container" style="max-width:100vw;">
        <div class="swiper-wrapper">
        </div>
      </div>
    </div>
      <!-- 대댓글메뉴버튼 -->
      <div class="modal ty2" id="_system_modal" style="display:none;top:55vh;">
        <input type="hidden" class="comment_no">
        <div class="modal_ctn">
          <div class="modal_cont">
              <button type="button" class="modal_btn write">대댓글 작성</button>
              <button type="button" class="modal_btn delete">댓글 삭제</button>
            </form>
            <button type="button" class="modal_close ico_close" style="top:22px;right:25px;"></button>
          </div>
        </div>
      </div>
  </section>
  <footer>
    <div class="reply_inputwrap">
      <div class="reply_inputctn">
        <input type="text" class="reply_input"
          onkeyup="$(this).val().length? $(this).siblings('.pencil').addClass('on') : $(this).siblings('.pencil').removeClass('on');">
        <button type="button" class="sp24 pencil reply_submit"></button>
      </div>
    </div>
  </footer>
  <script id="slideTmpl" type="text/x-jsrender">
    <div class="swiper-slide" id="slide{{:writing_no}}">
      <input type="hidden" value={{:report}} class="isComplained">
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
          <input type="hidden" value="{{:vote}}" class="whereYouVote"></input>
          <div class="card card--single">
            <label for="before">
              <p class="card__descwrap">
                <span class="card__desc">
                    {{:fir_writ_content}}
                </span>
              </p>
              <p class="card_subdesc">
                <span class="prtg percentage">{{:fir_vote_perc}}</span>%, 나를 제외한 <span class="count">{{:fir_vote_no}}</span>명의 선택
              </p>
              <div class="card__prtg">
                <span class="card__prtgmain percentage">{{:fir_vote_perc}}</span>
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
                <span class="prtg percentage">{{:sec_vote_perc}}</span>%, 나를 제외한 <span class="count">{{:sec_vote_no}}</span>명의 선택
              </p>
              <div class="card__prtg">
                <span class="card__prtgmain percentage">{{:sec_vote_perc}}</span>
                <span class="card__prtgsub"><span class="count">{{:sec_vote_no}}</span>명의선택</span>
              </div>
            </label>
          </div>
        </div>
        <div class="detail_bottom">
          <button type="button" class="detail_btn">본문 펼치기</button>
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
      <li class="detail_replyitem accordion" id="comment{{:comment_no}}" data-accordion>
        <div class="detail_replyheader">
          <input type="hidden" class="ismine" value="{{:mine}}">
          <input type="hidden" class="isdeleted" value="{{:use_yn}}">
          <button type="button" class="detail_replytit" value={{:vote}}>{{:nickname}}</button>
          <span class="detail_reply_subtitarea">
            <span class="detail_replytime">{{:mod_dts}}</span>
              <span class="detail_replyinfos {{if prefer == 0}}up{{else prefer == 1}}down{{/if}}">
                {{if sum_prefer > 0 }}
                  {{include tmpl="#recomCountTx"/}}
                {{/if}}
                <button type="button" class="recommend sp00 up 
                  {{if prefer==0}} on{{/if}}"></button>
                <button type="button" class="recommend sp00 down
                  {{if prefer==1}} on{{/if}}"></button>
                <a href="#_system_modal" id="_system_modal_trigger" class="sp00 dot3" rel="leanModal"></a>
              </span>
          </span>
        </div>
        <span class="detail_replycont">
          {{if use_yn == 'N'}}
              삭제된 댓글입니다
            {{else }}
              {{:comment_content}}
          {{/if}}
        </span>
        <div class="detail_reply_subarea acdo">
          <ul class="detail_reply_subitems acdo_cont" data-content>
            {{if lowCommentDtoList}}
              {{for lowCommentDtoList }}
                {{include tmpl="#subReplyTmpl"/}}
              {{/for}}
            {{/if}}
          </ul>
          <div class="detail_reply_morewrap acdo_open">
            <button type="button" class="detail_reply_more" data-control>
              <span class="sp00 comment"></span>
              <span class="detail_reply_count">{{:low_comment_num}}</span>
            </button>
          </div>
        </div>
      </li>
    </script>
  <script id="subReplyTmpl" type="text/x-jsrender">
      <li class="detail_reply_subitem" id="lowComment{{:comment_no}}">
        <input type="hidden" class="ismine" value="{{:mine}}">
        <input type="hidden" class="isdeleted" value="{{:use_yn}}">
        <button type="button" class="detail_replytit" value={{:vote}}>{{:nickname}}</button>
        <span class="detail_reply_subtitarea">
          <span class="detail_replytime">2019.11.27 17:01:45</span>
            <span class="detail_replyinfos {{if prefer == 0}}up{{else prefer == 1}}down{{/if}}">
              {{if sum_prefer > 0 }}
                {{include tmpl="#recomCountTx"/}}
              {{/if}}
              <button type="button" class="recommend sp00 up 
                {{if prefer==0}} on{{/if}}"></button>
              <button type="button" class="recommend sp00 down
                {{if prefer==1}} on{{/if}}"></button>
              <a href="#_system_modal" id="_system_modal_trigger" class="sp00 dot3" rel="leanModal"></a>
            </span>
        </span>
        <span class="detail_replycont">
          {{if use_yn == 'N'}}
              삭제된 댓글입니다
            {{else }}
              {{:comment_content}}
          {{/if}}
        </span>
      </li>
    </script>
  <script id="recomCountTx" type="text/x-jsrender">
      <span class="detail_replyuptx">추천
        <span class="count">{{: sum_prefer}}</span>
      </span>
    </script>
  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/common.js"></script>
  <script src="resources/js/jquery.leanModal.min.js"></script>
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/swiper.js"></script>
  <script src="resources/js/jsrender.min.js"></script>
  <script src="resources/js/constant.js"></script>
  <script src="resources/js/jquery-accordion.js"></script>
  <script src="resources/js/detail.js"></script>
  <script type="text/javascript">
  </script>
</body>

</html>
