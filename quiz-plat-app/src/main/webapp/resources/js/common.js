$('.detail .card').click(function(e){
  var footBtn = $(this).parents('.detail').find('.detail__btn');
  var footResultBtn = footBtn.siblings('.detail__btn.on');
  $(this).siblings('.card').removeClass('on');
  $(this).addClass('on');
  footBtn.hide();
  footResultBtn.show();
});