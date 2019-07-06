$('.detail .card').click(function(e){
  $(this).siblings('.card').removeClass('on');
  $(this).addClass('on');
  $(this).parents('.detail').find('.detail__btn').addClass('on');
  $(this).parents('.detail').find('.detail__btn').text("결과보기");
});