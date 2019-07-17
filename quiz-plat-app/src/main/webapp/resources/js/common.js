$(function () {
  'use strict';
  $('.card--single').click(function () {
    $('.detail__btn').addClass('on');
  });

  $('.write__inparea').on('keyup', function () {
    var len = $(this).find('input, textarea').val().trim().length;
    if (len > 0) $(this).addClass('on');
    else if (len == 0) $(this).removeClass('on');
    var writeWrap = $('.write__wrap');
    var writeInpArea = $('.write__inparea');
    var writeBtn = $('.write__footbtn');
    if (isAllChecked()) {
      writeBtn.addClass('on');
      writeBtn.val('만들기');
    } else {
      writeBtn.removeClass('on');
      writeBtn.val('필수 항목을 입력해주세요');
    }
  });

  $('.write__inparea').on('focusin', '.write__inpcnt, .write__txtarea', function () {
    $(this).addClass('focus');
  });
  $('.write__inparea').on('focusout', '.write__inpcnt, .write__txtarea', function () {
    $(this).removeClass('focus');
  });

  function isAllChecked() {
    var chk = 0;
    $('.write__inparea').each(function (index, item) {
      $(item).hasClass('on') ? chk++ : chk;
    });
    return chk == 3 ? true : false;
  }

  var $page = $('#main'),
    options = {
      debug: true,
      //prefetch: true,
      //cacheLength: 2,
      onStart: {
        duration: 100, // Duration of our animation
        render: function ($container) {
          // Add your CSS animation reversing class
          //$container.addClass('is-exiting');
          // Restart your animation
          //smoothState.restartCSSAnimations();
        }
      },
      onReady: {
        duration: 0,
        render: function ($container, $newContent) {
          // Remove your CSS animation reversing class
          //$container.removeClass('is-exiting');
          // Inject the new content
          //$container.html($newContent);
        }
      }
    },
    smoothState = $page.smoothState(options).data('smoothState');

  $('.result__write').on('keyup', function () {
    if ($(this).val().trim().length > 0) {
      $(this).siblings('i').addClass('on');
    } else {
      $(this).siblings('i').removeClass('on');
    }
  });

  //상세페이지 카드 내 텍스트크기 조절하기
  var detailCard = $('.card__responsive');
  detailCard.each(function (index, item) {
    var fontSize = parseInt($(this).css('font-size'), 10);
    while ($(this)[0].scrollHeight > $(this)[0].clientHeight) {
      fontSize -= 1;
      $(this).css('font-size', (fontSize) + 'px');
      $(this).css('line-height', (fontSize) + 'px');
    }
  });

  var toast = $('.toast.on');
  if (toast.length > 0) {
    showToast(toast);
  }

});

function copyToClipboard(url) {
  showToast($('.toast'));
  var $temp = $("<input>");
  $("body").append($temp);
  $temp.val(url).select();
  document.execCommand("copy");
  $temp.remove();
}

function showToast(target) {
  target.addClass('scene_element--fadein on');
  target.removeClass('scene_element--fadeout');
  setTimeout(() => {
    target.removeClass('scene_element--fadein');
    target.addClass('scene_element--fadeout');
  }, 2000);
}