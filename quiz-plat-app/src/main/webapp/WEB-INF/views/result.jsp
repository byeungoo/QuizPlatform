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
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/css/animsition.min.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
  <div class="wrapper wrapper--white result">
    <div class="" style="height:45px;"><h3>앱바</h3></div>
    <header class="header">
      <div class="header__logo">
        <a href="/" class="animsition-link header__home" data-animsition-out-class="fade-in-left"
          data-animsition-out-duration="200">
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
          <div class="result__reply">
            <div class="result__reply-tit">
              익명
            </div>
            <div class="result__reply-cont">
              당연히 100억이지 조선시대 왕들 전부 단명하는거 모르는 부분? 전자 찍으신분들 채소 국사시간에 졸으신 분들 ㅋ
            </div>
          </div>
          <div class="result__reply">
            <div class="result__reply-tit">
              익명23
            </div>
            <div class="result__reply-cont">
              뭔소리야 당연히 닥후지 조선시대 왕이 짱이야
            </div>
          </div>
        </div>
    </section>
    <div class="result__footbtn">공유하기</div>
  </div>
  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/js/animsition.min.js"></script>
  <script src="resources/js/common.js"></script>
</body>
</html>