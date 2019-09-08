
$('#_write').on('keyup change','.write_area',function(e){
    $(e.currentTarget).toggleClass('on', $(e.target).val().length > 0);
})
function createWrite(){
  var plusBtn = $('.bottom_navbaritem.plus');
  var fir_writ_content = $('#_write_front').val();
  var sec_writ_content = $('#_write_back').val();
  var content = $('#_write_textarea').val();
  if(!fir_writ_content || !sec_writ_content || !content){
    oToast.show('모두 입력해주세요');
    $('.write_submit').removeClass('on');
    return false;
  }
  plusBtn.prop('disabled', true);
  var data = { fir_writ_content, sec_writ_content, content}
  oAjax.sendRequest(URL_CREATE_VOTE,data, ID_TMPL_MAIN_CARD,'POST',null).then( html => {
    var cardList = $('.main-sec__list');
    $($('.home_header_navlist').children().eq(1)).click();
    if(cardList.children().length > 0) cardList.prepend(html);
    resetBottomNavbar();
    $('html,body').animate({scrollTop:0},0);
    $('#_write').find('.modal_close').click();
    $('.write_inp').val('').siblings('label').show();
    $('.write_submit').removeClass('on');
    $('.write_area').removeClass('on');
    plusBtn.prop('disabled', false);
  }).catch( e => {
    console.log(e);
    plusBtn.prop('disabled', false);
  })
}

function resetBottomNavbar(){
  $($('.bottom_navbar').children()).removeClass('on');
}

$('.home_header_navlist').on('click',function(){
  var section = $('.main-sec');
  resetBottomNavbar();
  $(section.eq(0)).show();
  $(section.eq(1)).hide();
});


$('.bottom_navbar').on('click','.bottom_navbaritem',function(e){
  if($(this).hasClass('person')) return;
  if($(e.currentTarget).hasClass('on')) {
    $(e.delegateTarget).removeClass('on');
    $(e.currentTarget).removeClass('on');
    return;
  }
  $(e.delegateTarget).addClass('on');
  resetBottomNavbar();
  $(e.currentTarget).addClass('on');
});

$('.bottom_navbar').on('click', '.search', function (e) {
  $().leanModal.scrollTop = $(window).scrollTop();
  $('#_searchbar_trigger').click();
  $('.home_header_navlist').hide();
  $('.main-sec__list').hide();
  $('.main-sec__searchlist').show();
  $('.main-sec.write').hide();
});

$('#_searchbar').on('click','.search',function(e){
  var $input = $(e.delegateTarget).find('input');
  var srch_word = $input.val();
  var srch_type_div_cd = 0;
  var data = {page_num : 1,srch_word,srch_type_div_cd};
  console.log(data);
  oAjax.sendRequest(URL_READ_SEARCH_CARD_DATA,data, ID_TMPL_MAIN_CARD,'GET').then( html => { 
    if(!html.length){
      oToast.show('검색 결과가 없습니다');
      return;
    }
    $(e.delegateTarget).find('.modal_close').click();
    $('.main-sec__searchlist').empty().append(html).show();
    $('.main-sec').show();
    $input.val('');
  });
})

$('.write_inp').on('blur',function(e){
  $(this).siblings('label').hide();
}).on('keyup',function(e){
  var label = $(this).siblings('label')
  !$(this).val().length ? label.show() : label.hide();
})

$('#login .modal_header_tittx').on('click',function(){
  $('#join').find('.modal_close').click();
  oAjax.sendRequest(URL_READ_NICKNAME,null,null,'GET',null).then( json => {
    console.log(json);
    $('.modal_inp.nickname').val(json.nickname).addClass('on');
    $('#join_trigger').click();
  }).catch( error => {
    console.log(error);
    console.log("서버에서 닉네임을 못받아왔습니다.");
  });
});


var joinForm = $('#join');
var joinInpGroup = joinForm.find('.modal_inp');
var pwdGroup = joinForm.find('input[type="password"].modal_inp');
var joinFootBtn = joinForm.find('.modal_footbtn');
var joinClose = joinForm.find('.modal_close');
var total = joinInpGroup.length;
var passCheck = joinForm.find('.check');
joinInpGroup.on('keyup',function(e){
  //모든 필드에 적용
  $(this).val().length ?
    $(this).addClass('on').removeClass('wrong') :
    $(this).removeClass('wrong').removeClass('on');
    
  var curIdx = $(pwdGroup).index($(this));
  if(curIdx >= 0){ //패스워드 입력시 비밀번호 확인
    var my = curIdx ? $(pwdGroup).eq(curIdx) : null;
    var other = $(pwdGroup).eq(!curIdx);
    if($(other).val().length && $(my).val().length){//패스워드1,2 둘다 입력시 비밀번호 비교 
      if($(my).val() !== $(other).val()){
        $(pwdGroup).addClass('wrong').removeClass('on');
      }else{
        $(pwdGroup).addClass('on').removeClass('wrong');
      }
    }
  }
  if(isCompleteForm(joinInpGroup)) {
    joinFootBtn.addClass('on').prop('disabled',false).text('회원가입');
  }else{
    joinFootBtn.removeClass('on').prop('disabled', true).text('필수 항목을 작성해주세요');
  }
});

var loginForm = $('#login');
var loginInpGroup = loginForm.find('.modal_inp');
var loginFootBtn = loginForm.find('.modal_footbtn');
loginInpGroup.on('keyup',function(e){
  if ($(e.target).val().length > 0) {
    $(this).addClass('on').removeClass('wrong');
  } else {
    $(this).removeClass('on').removeClass('wrong');
  }
  if (isCompleteForm(loginInpGroup)) {
    loginFootBtn.addClass('on').prop('disabled',false).text('로그인');
  } else {
    loginFootBtn.removeClass('on').removeClass('wrong').text('필수 항목을 작성해주세요');
  }
});

$('.person').on('click',function(){
  oAjax.sendRequest(URL_REMOVE_SESSION,null,null,'POST',null).then( json => {
    if(!json.login){
      oToast.show('로그아웃 되었습니다');
      setLoginIcon(json.login);
    }
  }).catch( error => {
    oToast.show('로그아웃에 실패했습니다.');
    return false;
  });
});

function isLogin(){
  return oAjax.sendRequest(URL_READ_USERINFO, null, null, 'GET', null).then(json => {
    return json.login;
  });
}

function setLoginIcon(login){
  $('.person').toggle(login);
  $('.lock').toggle(!login);
}

//회원가입 버튼 클릭 시
$("#_join_form").on('submit',function(e){
  e.preventDefault();
  var nickname = joinInpGroup.eq(0).val();
  var user_id = joinInpGroup.eq(1).val();
  var pwd = joinInpGroup.eq(2).val();
  var data = {user_id,pwd,nickname};
  createMember(data);
});

$("#_login_form").on('submit',function(e){
  e.preventDefault();
  requestLogin();
});

function requestLogin() {
  var user_id = loginInpGroup.eq(0).val();
  var pwd = loginInpGroup.eq(1).val();
  var rememberId = loginForm.find('input[type="checkbox"]').prop('checked');
  data = { user_id, pwd, rememberId };
  oAjax.sendRequest(URL_CREATE_SESSION,data,null,'POST',null).then( json => {
    if(json.login){
      console.log('로그인성공');
      console.log(json);
      oToast.show(json.nickname+"님 환영합니다");
      loginForm.find('.modal_close').click();
      isLogin().then(login => {
        setLoginIcon(login);
      })
    }else{ //로그인 실패
      loginInpGroup.removeClass('wrong').removeClass('on').val('');
      loginFootBtn.addClass('wrong').removeClass('on').text('잘 못 입력하셨습니다').prop('disabled',true);
    }
  });
}

function createMember(data) {
  oAjax.sendRequest(URL_CREATE_MEMBER,data,null,'POST',null).then( json => {
    if(json.login){
      joinClose.click();
      $("#join .modal_inp").removeClass('on').val('');
      $('#join .modal_footbtn').prop('disabled',true).text('필수 항목을 작성해주세요').removeClass('on');
      requestLogin();
    }else{
      oToast.show("이미 존재하는 ID입니다");
      $('#_join_id').val('').removeClass('on').addClass('wrong');
    }
  });
}

function isCompleteForm(inpGroup) {
  for (var i = 0; i < inpGroup.length; i++) {
    if (!inpGroup.eq(i).hasClass('on')) return false;
  }
  return true;
}

$('.bottom_navbaritem.lock').on('click',function(){
  $('#login_trigger').click();
  $.scrollLock(true);
});
$('#join_trigger').on('click',function(){
  $.scrollLock(true);
})
$('body').on('click','.modal_close',function(){
  $.scrollLock(false);
  $('.bottom_navbar').children().removeClass('on').removeClass('off');
});
var oSsjViewInfinite;
$(function () {
  $("a[rel*=leanModal]").leanModal({ overlay: 0.4, slideinUp: 'join', topfix:['#_searchbar','#_write']}); //a태그에 모달 켜기 기능 추가
  //$("#login_trigger").click();
  //인피니티 스크롤 위치 기억
  $('.main-sec__list').on('click', 'a', function (e) {
    var nextPage = $(e.currentTarget).attr('href');
    var ifrWrapper = $('<div/>', {
      class: "iframe_wrapper",
      css: {
        '-webkit-overflow-scrolling': 'touch',
        "overflow": 'hidden auto'
      }
    });
    var iframe = $('<iframe/>', {
      src: nextPage,
      scrolling: 'yes',
      css: {
        height: "100vh",
        width: "100vw"
      },
      allowfullscreen
    });
    ifrWrapper.append(iframe);
    $('body').children().hide();
    $('body').prepend(ifrWrapper);
    window.history.pushState('', '', nextPage);
    e.preventDefault();
  });

  window.onpopstate = function (e) {
    var $body = $('body');
    $body.find('.iframe_wrapper').remove();
    $body.find('header').show();
    $body.find('.wrapper').show();
    $body.find('footer').show();
    requestLogin();
  }

  oSsjViewInfinite = new ssj.view.infiniteScroll();
  //인피니티 스크롤 : 탭 전환시 스크롤 위치 기억
  $('.home_header_navlist').on('click', '.home_header_navitem', function (e) {
    $('.main-sec__list').show();
    $('.main-sec__searchlist').hide();
    var mypage = $('.mypage');
    var nowCateNum = $(e.delegateTarget).children('.on').val();
    var nextCateNum = $(e.currentTarget).val();
    if (nowCateNum<2 && nextCateNum == 2) { //인기,신규에서 활동 탭을 눌렀을때
      oSsjViewInfinite.saveCurrentState();
      toggleTab(nextCateNum);
      mypage.show().children('button').eq(0).click();
      scrollToTop();
      return false;
    } else if(nowCateNum==2 && nextCateNum <2) { //활동에서 인기,신규를 눌렀을때
      toggleTab(nextCateNum);
      $('.main-sec__list').empty();
      oSsjViewInfinite.switchCategory();
      scrollByPosition(oSsjViewInfinite.getCurrentScrollTop());
      mypage.hide();
    }else{ // 인기, 신규 간 전환
      oSsjViewInfinite.saveCurrentState();
      toggleTab(nextCateNum);
      oSsjViewInfinite.switchCategory();
      scrollByPosition(oSsjViewInfinite.getCurrentScrollTop());
    }
  });

  $('.mypage').on('click','button',function(e){
    var $subList = $(e.currentTarget);
    var cateNum = $subList.val();
    var data = {page : 1, mainCategory: cateNum};
    console.log(data);
    var cardList = $('.main-sec__list');
    oAjax.sendRequest(URL_READ_MAIN_CARD_DATA,data,ID_TMPL_MAIN_CARD,'GET').then( html => {
      cardList.empty().append(html);
    })
  });

  $('.modal_chk_wrap').on('click','input',function(e){
    $(e.currentTarget).toggleClass('on');
  })

  isLogin().then( login => {
    setLoginIcon(login);
  })

});