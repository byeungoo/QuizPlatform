<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>결과페이지</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" href="resources/css/keyframes.css">
  <link rel="stylesheet" href="resources/css/pageTransitions.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="wrapper wrapper--white m-scene">
    <div class="scene_element scene_element--fadeinright">
    <div class="" style="height:45px;"><h3>앱바</h3></div>
    <header class="header">
      <div class="header__logo">
        <a href="/" class="header__home">
            <img src="resources/img/Back_blue@2x.png" width="16px" height="24px" alt="뒤로가기버튼" class="header__icon">
            <span class="header__tx">홈</span>
        </a>
      </div>
      <div class="header__title">결과보기</div>
    </header>
    <section class="result__contwrap">
      <div class="result__cont">
        <div class="result__toptx">엥;</div>
        <div class="result__area">
          <div class="result__left-area">
            <div class="result__badges">
              <c:if test="${writingVoteDto.fir_content_vote == 'Y'}">
                <img src="resources/img/mypick.png" width="66px" height="21px" alt="mypick" class="badge">
			  </c:if>
              <c:choose>
			    <c:when test="${writingDtlDto.fir_vote_perc > writingDtlDto.sec_vote_perc}">
					<img src="resources/img/win.png" width="56px" height="21px" alt="win" class="badge">
			    </c:when>
			    <c:when test="${writingDtlDto.fir_vote_perc == writingDtlDto.sec_vote_perc}">
					
			    </c:when>
    	 	    <c:otherwise>
					<img src="resources/img/lose.png" width="56px" height="21px" alt="lose" class="badge">
    	      	</c:otherwise>
		      </c:choose>
            </div>
            <div class="result__infoarea">
              <p class="result__info">${writingDtlDto.fir_vote_perc}%</p>
			  <c:choose>
			    <c:when test="${writingVoteDto.fir_content_vote == 'Y'}">
			      <p class="result__infosub">나와 ${writingDtlDto.fir_vote_no}명의 선택</p>
			    </c:when>
    	 	    <c:otherwise>
        	    	<p class="result__infosub">${writingDtlDto.fir_vote_no}명의 선택</p>
    	      	</c:otherwise>
		      </c:choose>
              <p class="result__footinfo">${writingDtlDto.fir_writ_content}</p>
            </div>
          </div>
          <div class="result__right-area win">
            <div class="result__badges">
              <c:if test="${writingVoteDto.sec_content_vote == 'Y'}">
                <img src="resources/img/mypick.png" width="66px" height="21px" alt="mypick" class="badge">
			  </c:if>
              <c:choose>
			    <c:when test="${writingDtlDto.sec_vote_perc > writingDtlDto.fir_vote_perc}">
					<img src="resources/img/win.png" width="56px" height="21px" alt="win" class="badge">
			    </c:when>
			    <c:when test="${writingDtlDto.sec_vote_perc == writingDtlDto.fir_vote_perc}">
					
			    </c:when>
    	 	    <c:otherwise>
					<img src="resources/img/lose.png" width="56px" height="21px" alt="lose" class="badge">
    	      	</c:otherwise>
		      </c:choose>
            </div>
            <div class="result__infoarea">
                <p class="result__info">${writingDtlDto.sec_vote_perc}%</p>
			    <c:choose>
			      <c:when test="${writingVoteDto.sec_content_vote == 'Y'}">
			        <p class="result__infosub">나와 ${writingDtlDto.sec_vote_no}명의 선택</p>
			      </c:when>
    	 	      <c:otherwise>
        	    	<p class="result__infosub">${writingDtlDto.sec_vote_no}명의 선택</p>
    	      	  </c:otherwise>
		        </c:choose>
                <p class="result__footinfo">${writingDtlDto.sec_writ_content}</p>              
            </div>
          </div>
        </div>
        <div class="result__reply-area">
          <c:forEach items="${commentDtoList}" var="commentDto">
            <div class="result__reply">
              <div class="result__reply-tit">
                ${commentDto.regpe_id}
              </div>
              <div class="result__reply-cont">
                ${commentDto.comment_content}
              </div>
            </div>
          </c:forEach>
        </div>
        <form action = "writeComment" id="writeComment" method="post">
          <input type="hidden" id="writing_no" name="writing_no" value=${writingDtlDto.writing_no}>
          <div class="result__write-wrap">
            <textarea name="comment_content" id="comment_content" class="result__write" placeholder="댓글을 입력해주세요"></textarea>
            <i class="fas fa-pen"></i>
            <input type="submit" value="댓글작성"/>
          </div>
        </form>
    </section>
    <div class="result__footbtn">공유하기</div>
  </div>
  <script src="https://code.jquery.com/jquery-2.2.4.js" ></script>  
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/common.js"></script>
</body>
</html>
