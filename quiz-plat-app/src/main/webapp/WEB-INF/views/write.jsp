<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>관리자 글 작성 페이지</title>
  <link rel="stylesheet" href="resources/css/style.css" >
  <link rel="stylesheet" href="resources/css/jquery.minicolors.css">
  <style>
    html,body,form{height:100%;}
    body, .title{margin:20px;}
    .title{font-size:21px;}
    .write_tit,
    .write_cont{display:block;width:100%;padding:10px;border:1px solid black;box-sizing:border-box;margin-top:20px;}
    .write_cont{min-height:50%;}
    .write_cont.ty_summary{min-height:10%;}
    .write_tit{font-size:15px;}
    .btnarea{display:flex;align-items:center;justify-content: space-evenly;padding:10px;border:1px solid black;}
    .btnarea + .btnarea{margin-top:-1px;}
    .btnarea .btn{display:block;line-height:1;font-size:21px;color:#ff5b59;font-weight:bold;}
    .inserted_image{width:100px;height:100px;}
    .inserted_image.on{max-width:100%;max-height:100%;width:auto;height:auto;position:fixed;top:0;left:0;right:0;bottom:0;margin:auto;overflow:auto;}
    b{font-weight:bold;}
    .fontsize input{border:1px solid black; width:30px; margin-left:5px;}
    li{padding-left:5px;word-break:break-all;}
    ul li:before{content:"";display:inline-block;width:10px;height:10px;border-radius:50%;background:black;margin-right:5px;}
    ol{list-style-type:decimal;margin-left:15px;}
    .submit{display:block;margin:auto;border:1px solid black; border-radius:20px; padding: 10px 80px; margin: 50px auto;}
  </style>
</head>
<body>

  <form onsubmit="">
    <p class="title">제목</p>
    <input type="text" class="write_tit">
    <p class="title">툴팁</p>
    <div class="btnarea">
      <input type="file" id="inp_image">
    </div>
    <div class="btnarea">
      <p>폰트</p>
      <button type="button" class="btn" onclick="toggleBold.call(this);" data-bold="true">B</button>
      <button type="button" class="btn colorpicker"></button>
      <button type="button" class="btn fontsize">size<input type="text" placeholder="16"></button>
      <button type="button" class="btn underline">밑줄</button>
    </div>
    <div class="btnarea">
      <p>리스트</p>
      <button type="button" class="btn unordered">순서X</button>
      <button type="button" class="btn ordered">순서O</button>
      <button type="button" class="btn indent">들여쓰기</button>
    </div>
    <p class="title">요약</p>
    <div class="write_cont ty_summary"></div>
    <p class="title">본문</p>
    <div class="write_cont"></div>
    <button class="submit">제출</button>
  </form>

  <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
  <script src="resources/js/squire.js"></script>
  <script src="resources/js/jquery.minicolors.min.js"></script>
  <script type="text/javascript">
    function toggleBold(){
      const bold = $(this).data('bold');
      bold ? oEditor.bold() : oEditor.removeBold();
      $(this).data('bold', !bold);
    }

    function toggleUnderline() {
      const underline = $(this).data('underline');
      underline ? oEditor.underline() : oEditor.removeUnderline();
      $(this).data('underline', !underline);
    }

    function getImage(input){
      if(input.files && input.files[0]){
        if (/\.(jpe?g|png|gif)$/i.test(input.files[0].name)){
          const reader = new FileReader();
          reader.onload = function (e) {
            oEditor.insertImage(
              reader.result,
              {
                class: 'inserted_image'
              }
            );
            input.value = "";
          };
          reader.readAsDataURL(input.files[0]);
        }else {
          alert('png, gif, jpg 파일만 업로드 가능합니다');
        }
      }
    }

    $(function(){
        const $target = $('.write_cont');
        window.oEditor = window.oEditorSum;
        window.oEditorSum = new Squire($target.get(0), 
        {
          blockTag: 'P',
          blockAttributes: { style: 'font-size: 16px;', class : "summary" }
        },
        window.oEditorConts = new Squire($target.get(1),
        {
          blockTag: 'P',
          blockAttributes: { style: 'font-size: 16px;', class: "contents" }
        })
      );

      $('.write_cont').click(function(e){
        const $target = $(e.delegateTarget);
        const index = $('.write_cont').index($target);
        oEditor = index === 0 ? oEditorSum : oEditorConts;
      })

      $('.colorpicker').each(function () {
        $(this).minicolors({
          control: $(this).attr('data-control') || 'hue',
          defaultValue: $(this).attr('data-defaultValue') || '#000',
          format: $(this).attr('data-format') || 'hex',
          keywords: $(this).attr('data-keywords') || '',
          inline: $(this).attr('data-inline') === 'true',
          letterCase: $(this).attr('data-letterCase') || 'lowercase',
          opacity: $(this).attr('data-opacity'),
          position: $(this).attr('data-position') || 'bottom',
          swatches: $(this).attr('data-swatches') ? $(this).attr('data-swatches').split('|') : [],
          change: function (hex, opacity) {
            var log;
            try {
              log = hex ? hex : '#000';
              oEditor.setTextColour(log);
              if (opacity) log += ', ' + opacity;
              console.log(log);
            } catch (e) { }
          },
          theme: 'default',
          position: 'top bottom'
        });
      });
      
      $('body').on('click', '.inserted_image', function(e) {
        $(e.target).toggleClass('on');
      });

      $('.fontsize').on('blur keyup','input', function(e){
        if(e.type ==="keyup" && e.keyCode != 13) return;
        oEditor.setFontSize($(e.target).val() + 'px');
        e.preventDefault();
      });

      $('.underline').click(function(){
        toggleUnderline();
      });

      $('.ordered').click(function(){
        oEditor.makeOrderedList();
      });

      $('.unordered').click(function () {
        oEditor.makeUnorderedList();
      });

      $('.indent').click(function (){
        oEditor.increaseListLevel();
      });

    });
  </script>
</body>
</html>
