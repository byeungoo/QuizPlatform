(function($) {
  // 'use strict';
  // var $body = $('html, body'),
  //     content = $('#main').smoothState({
  //       debug : true,
  //       onStart: {
  //         duration: 0,
  //         render: function (url,$container) {
  //           $("html, body").animate({ scrollTop: "0px" });
  //           //make a duplicate container for animation...
  //           var $newContainer = $container.clone();
  //           $newContainer.attr("id", "main");
  //           $newContainer.css({position:'absolute', top:$container.offset().top, width:$container.css("width")});
  //           $newContainer.css({height:$container.css("height")});
  //           //$container.css({height:$container.css("height")});
  //           $container.empty(); //empty the old content so that it takes up 0 space
  //           $container.before($newContainer); //and immediately add the duplicate back on
  //           $('.transition-area').get(1).remove();
  //           var element =  $('.anim_element');
  //           element.removeClass('anim_element--slideInRight'); // just in case we have the class still
  //           setTimeout(callAnimation(element, true), 0); //start the animation
  //         }
  //       },
  //       onReady: {
  //         duration: 0,
  //         render: function ($container, $newContent) {
  //           // Inject the new content
  //           debugger;
  //           $container.html($newContent);
        
  //           // do animations
  //           var element = document.getElementById($container[0].id).getElementsByClassName('row')[0];
        
  //           callAnimation(element);
  //         }
  //       },
  //       // Runs when a link has been activated
  //       onAfter: {
  //         duration: 250, // Duration of our animation
  //         render: function (url, $container) {
  //           // toggleAnimationClass() is a public method
  //           // for restarting css animations with a class
  //           content.toggleAnimationClass('is-exiting');
  //           // Scroll user to the top
  //           $body.animate({scrollTop: 0});
  //         }
  //       }
  //     }).data('smoothState');
      
      // function callAnimation(element, exiting) {
      //   if (!exiting) {
      //       $(element).addClass("anim_element--slideInRight");
      //   } else {
      //       $(element).addClass('anim_element--slideInRight');
      //   }
      // }
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
      
})(jQuery);