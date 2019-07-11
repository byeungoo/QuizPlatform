$(function() {
  'use strict';
    $('.card--single').click(function(){
      $('.detail__btn').addClass('on');
    });
    
    $('.write__inpcnt, .write__txtarea').on('keyup',function(){
      var len = $(this).val().trim().length;
      if(len > 0) $(this).addClass('on');
      else if(len == 0) $(this).removeClass('on');
      if(isAllChecked.call($(this))){
        $('.write__footbtn').hide();
        $('.write__footbtn.on').show();
      }else{
        $('.write__footbtn').show();
        $('.write__footbtn.on').hide();
      }
    });

    function isAllChecked(){
      var chk = 0;
      $('.write__inpcnt, .write__txtarea').each(function(index,item){
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
      
      //토스트 메세지
      var toast = $('.toast.on').addClass('scene_element--fadein');
      setTimeout(() => {
        toast.removeClass('scene_element--fadein');
        toast.addClass('scene_element--fadeout');
      }, 2000);

      $('.result__write').on('keydown', function() {
        if($(this).val().trim().length >0){
          $(this).siblings('i').addClass('on');
        }else{
          $(this).siblings('i').removeClass('on');
        }
     });
});
