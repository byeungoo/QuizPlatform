$(".animsition").animsition({
  inClass: "fade-in-right",
  outClass: "fade-out-right",
  inDuration: 200,
  outDuration: 200,
});

$('.card--single').click(function(){
  $('.detail__btn').hide();
  $('.detail__btn').siblings('a').show();
});