<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0"/>
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>상세페이지</title>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" href="resources/css/keyframes.css">
  <link rel="stylesheet" href="resources/css/pageTransitions.css">
  <link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700|Roboto:400,500,700&display=swap" rel="stylesheet">
  
</head>
<body>
  <div class="wrapper wrapper--white m-scene" id="main">
    <div class="scene_element scene_element--fadeinright">
    	<header class="header">
          <a href="/" class="header__home">
            <img src="resources/img/Back_blue@2x.png" width="16px" height="24px" alt="뒤로가기버튼" class="header__icon">
            <span class="header__tx">홈</span>
          </a>
          <div class="header__infos">
            <div class="header__info">
              <img class="header__icon" src="resources/img/VoteCount_BW@2x.png" width="24px" height="24px" alt="투표수">
              <div class="header__icon-desc">${writingDtlDto.sum_vote}</div>
            </div>
            <div class="header__info">
              <img class="header__icon" src="resources/img/Comment_BW@2x.png" width="24px" height="24px" alt="댓글수" >
              <div class="header__icon-desc">${writingDtlDto.sum_comment}</div>
            </div>
          </div>
        </header>
        <form action="/result" id="result" method="post" id="detail_form">
        <input type="hidden" id="writing_no" name="writing_no" value= ${writingDtlDto.writing_no} > 
        <section class="detail" >
          <div class="card card--single">
            <input type="radio" name="inputState" id="before" class="blind" value="before">
            <label for="before">
              <span class="card__desc">
              	<span class="card__responsive two-line">
              		${writingDtlDto.fir_writ_content}
              	</span>
              </span>
            </label>
          </div>
          <div class="card card--single">
              <input type="radio" name="inputState" id="after" class="blind" value="after">
              <label for="after">
                <span class="card__desc">
                  <span class="card__responsive two-line">${writingDtlDto.sec_writ_content}</span>
                </span>
              </label>
          </div>
          <div class="detail__desc">
            <p class="detail__tit">설명</p>
            <div class="detail__txtarea">${writingDtlDto.content}</div>
            <a class="detail__btn" onclick="document.getElementById('result').submit();"></a>
          </div>
        </section>
        </form>
      </div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-2.2.4.js" ></script>  
  <script src="resources/js/smoothState.js"></script>
  <script src="resources/js/common.js"></script>
</body>
</html>