var ssj = ssj || {};
ssj.View = ssj.View || {};
ssj.View.mention = function(options) {
	const df = {};
	$.extend(this, options, df);
	this.init();
}

ssj.View.mention.prototype = $.extend({
	init() {
		this._assignElements();
		this._attachEventHandlers();
	},
	_assignElements() {
		this.$addTarget = $('.reply_inputctn');
		this.$input = $('.reply_input');
		this.$inputSubmitBtn = this.$input.siblings('.reply_submit');
		this.$mention = this._makeMention();
		this.$mentionNickname = this.$mention.find('.reply_mention_tx');
		this.$mentionCloseBtn = this.$mention.find('.sp00.close');
		this.$mentionCommentNo = this.$mention.find('.comment_no');
	},
	_attachEventHandlers() {
		this.$mentionCloseBtn.on('click',this._onClose.bind(this));
		this.on('show', (e,{nickname, commentNo}) => this.show.call(this,nickname, commentNo));
	},
	show(nickname, commentNo) {
		this.addNickname(nickname);
		this.addCommentNo(commentNo);
		this.addMention();
		this.applyAnimation();
	},
	_onClose() {
		this.removeMention();
	},
	_makeMention(nickname) {
		return $(`
      <div class="reply_mention">
        <span class="reply_mention_tx">${nickname}</span>
        <button class="sp00 close" onclick="$(this).parent().remove()"></button>
      </div>
      `);
	},
	applyAnimation() {
		this.$mention.removeClass('reply_mention');
		void this.$mention.get(0).offsetWidth; // force repaint
		this.$mention.addClass('reply_mention');
	},
	_clearMention() { this.$mentionNickname.text(''); this.$mentionCommentNo.val(''); },
	removeMention() { this.$mention.detach()},
	addNickname(nickname) { this.$mentionNickname.text(nickname)},
	addCommentNo(commentNo) { this.nCommentNo = commentNo},
	addMention() {this.$addTarget.prepend(this.$mention)},
	getCommentNo() { return this.nCommentNo}
},$.eventEmitter);

var oMention = new ssj.View.mention();
const data = { nickname: "바지입는놀라운오리", commentNo: "1"};
oMention.emit('show', data);
console.log(oMention.getCommentNo())


$(function () {
	//뒤로가기
	$('.header_wrap').on('click','.back',function(e){
		var prevPage = window.location.href;
		if(document.referrer === ""){
			window.location.href = "http://pickvs.com";
		}else{
			window.history.back();
		}
	});	
	var mainWritingNo = getNumberInStr(window.location.search);
	var Footer = $(".reply_inputwrap");
	oSpinner.setTarget($(".swiper-container"));
	oSpinner.show(); //페이지 처음 진입시 스피너
	//무한 스와이프
	var oSwiper = new ssj.util.swiper({
		direction: "horizontal",
		slidesPerView: 1.1,
		centeredSlides: true,
		threshold: 10,
		on: {
			init: function () {
				setInitialData(mainWritingNo);
				oSpinner.hide();
			},
			slideChange: function () {
				if (oSwiper.getActiveIndex() === oSwiper.getTotalCount() - 2) {
					makeSlideItems().then(items => {
						oSwiper.appendItem(items);
					});
				}
				activateAccordion($(oSwiper.getActiveSlide()).find(".accordion"));
				oSwiper.refreshSlideHeight();
				toggleFooter();
				scrollToTop();
				setHeaderState();
				resetAddress();
				resetMention();
			}
		}
	});
	
	function resetAddress(){
		var id = getNumberInStr($(oSwiper.getActiveSlide()).attr('id'));
		var url = window.location.origin + window.location.pathname + "?writing_no=" + id;
		window.history.replaceState(null,null,url);
	}
	function toggleFooter() {
		isAlreadyVoted() ? Footer.show() : Footer.hide();
	}
	toggleFooter();

	//한 페이지 내에서 카드 정보 업데이트
	function updateCardsData(cards, json) {
		cards.map(function (index, item) {
			var prtg = $(item).find(".percentage");
			var count = $(item).find(".count");
			prtg.text(json.votePerc[index]);
			count.text(json.voteNoArr[index]);
		});
		console.log(json);
		cards
			.closest(".swiper-slide")
			.find(".vote")
			.next(".card__icon-desc")
			.text(json.totalVoteNum);
	}

	/*카드 선택시 UI변경*/
	function changeDetailUI(e) {
		var cardwrap = $(e.target).closest(".card_wrap");
		var card = $(e.target).closest(".card");
		var cards = cardwrap.find(".card");
		var bottomInfo = cardwrap.siblings(".detail_bottom");
		var slide = $(oSwiper.getActiveSlide());
		cardwrap.addClass("on");
		bottomInfo.addClass("on");
		cards.removeClass("on");
		card.addClass("on");
		toggleFooter();
		checkMyteam(slide);
		activateModal();
	}

	function activateModal(){
		$("a[rel*=leanModal]").leanModal({ overlay: 0.4, slideinUp: '_system_modal'});
	}

	//아군 표시
	function checkMyteam(slide) {
		var cards = $(slide).find('.card');
		var currentCard = $(slide).find('.card.on');
		var whereYouVote = $(cards).index(currentCard) + 1;
		$(slide).find('.detail_replytit').map((index, item) => {
			if ($(item).val() == whereYouVote) $(item).addClass('on');
		});
	}

	//대댓글 펼침 기능 활성화
	function activateAccordion(target) {
		$(target).accordion({
			transitionSpeed: 700,
			transitionEasing: "cubic-bezier(0.23, 1, 0.32, 1)"
		});
	}

	//업버튼, 다운버튼 UI변경
	function changeUpDownBtn(e) {
		var btn = $(e.target);
		if (btn.hasClass("on")) return false;
		var btnWrap = btn.parent();
		btn.hasClass("up") ? btnWrap.addClass("up") : btnWrap.addClass("down");
		btn.addClass("on");
	}

	function setInitialData(mainWritingNo) {
		makeFirstItem(mainWritingNo)
			.then(item => {
				oSwiper.appendItem(item);
				activateAccordion($(oSwiper.getActiveSlide()).find(".accordion"));
				setInitialVoteState();
				setHeaderState();
				return makeSlideItems();
			})
			.then(item => {
				oSwiper.appendItem(item);
			});
	}

	$("body").on("click", ".detail_replyheader > .detail_replytit", function (e) {
		var moreBtn = $(e.target)
			.closest(".detail_replyitem")
			.find(".detail_reply_more");
		moreBtn.click();
	});
	//댓글 언급 기능


	function makeFirstItem(writingNo) {
		var requestData = {
			writingNo
		};
		return oAjax.sendRequest(URL_READ_FIRST_SLIDE_DATA, requestData, ID_TMPL_SLIDE, "GET");
	}

	function makeSlideItems() {
		var page = oAjax.getCurrentPageNum(PAGE_NAME_SWIPE);
		var requestData = {
			page,
			writing_no: mainWritingNo
		};
		return oAjax.sendRequest(URL_READ_SLIDE_DATA, requestData, ID_TMPL_SLIDE, "GET", PAGE_NAME_SWIPE);
	}

	function isAlreadyVoted() {
		var slide = oSwiper.getActiveSlide();
		return $(slide)
			.find(".card_wrap")
			.hasClass("on");
	}

	function setInitialVoteState() {
		var slide = oSwiper.getActiveSlide();
		var vote = parseInt($(slide).find(".whereYouVote").val(), 10) - 1;
		var cards = $(slide).find(".card");
		if (vote >= 0) {
			$(cards.get(vote)).trigger("update");
		}
	}

	function setHeaderState() {
		var slide = $(oSwiper.getActiveSlide());
		var header = $('.header_wrap');
		var firstBtnGroup = header.find('.forfistpage');
		var isComplained = slide.find('.isComplained').val() == 'true';
		if (isComplained) {
			$('.complain').addClass('on').prop('disabled',true);
		}else{
			$('.complain').removeClass('on').prop('disabled',false);
		}
		!oSwiper.getActiveIndex() ? firstBtnGroup.show() : firstBtnGroup.hide();
	}

	function clearComment() {
		$(".reply_input").val("");
		$('.reply_mention').remove();
		$('.reply_submit').removeClass('on');
	}

	function getActiveWritingId() {
		return getNumberInStr($(oSwiper.getActiveSlide()).attr("id"));
	}

	function getActiveCommentId(e) {
		var commentWrap =
			$(e.target)
			.closest(".detail_reply_subitem")
			.get(0) ||
			$(e.target)
			.closest(".detail_replyitem")
			.get(0);
		return getNumberInStr($(commentWrap).attr("id"));
	}

	/* 댓글 비동기 작성*/
	$("body").on("click", ".reply_submit", function (e) {
		var replytx = $(e.currentTarget).siblings(".reply_input").val();
		if (!replytx.length) {
			oToast.show("댓글을 입력해주세요", 2000);
			return false;
		}
		var requestData;
		var mention = $(e.currentTarget).siblings(".reply_mention");
		var slide = $(oSwiper.getActiveSlide());
		var depth = mention.length;
		var writingNo = oSwiper.getActiveSlideId();
		var requestData;
		if (!depth) {
			//댓글 입력
			var commentList = slide.find(".detail_replylist");
			requestData = {writingNo,replytx,depth,parent: null};
			addComment(requestData, commentList);
		} else {
			//대댓글 입력
			var parent = mention.find('input[type="hidden"]').val();
			var comment = slide.find(`#comment${parent}`);
			var lowCommentList = comment.find(".detail_reply_subitems");
			requestData = {writingNo,replytx,depth,parent};
			addLowComment(requestData, lowCommentList);
		}
	});

	//댓글 추가
	function addComment(requestData, commentList) {
		oAjax.sendRequest(URL_CREATE_COMMENT, requestData, ID_TMPL_REPLY, "POST").then(comment => {
			commentList.append(comment);
			oSwiper.refreshSlideHeight();
			scrollToBottom();
			clearComment();
			checkMyteam(oSwiper.getActiveSlide());
			activateModal();
		});
	}

	//대댓글 추가
	function addLowComment(requestData, commentList) {
		oAjax.sendRequest(URL_CREATE_COMMENT, requestData, ID_TMPL_SUBREPLY, "POST").then(comment => {
			commentList.append(comment);
			var accordion = $(commentList).closest(".accordion");
			var moreBtn = accordion.find(".detail_reply_more");
			refreshLowCommentList(commentList);
			scrollToCommentTarget(commentList.children().last());
			moreBtn.click();
			oSwiper.refreshSlideHeight();
			clearComment();
			checkMyteam(oSwiper.getActiveSlide());
			activateModal();
		});
	}

	function refreshLowCommentList(commentList) {
		var accordion = $(commentList).closest(".accordion");
		var replyCount = accordion.find(".detail_reply_count");
		replyCount.text(parseInt(replyCount.text(), 10) + 1);
		activateAccordion(accordion);
		accordion.trigger("accordion.refresh");
	}

	// 대댓글 아코디언탭 높이 재계산
	$("body").on("accordion.refresh", ".accordion", function () {
		$(this).find(".acdo_cont").css("max-height",$(this).height() +
				$(this).find(".detail_reply_subitems").children().last().height());
	});

	//	댓글, 대댓글
	//좋아요, 싫어요 비동기처리
	$("body").on("click", ".recommend", function (e) {
		var btnWrap = $(e.target).closest(".detail_replyinfos");
		var isVoted = btnWrap.hasClass("up") || btnWrap.hasClass("down");
		if (isVoted) return false;
		$(e.target).prop("disabled", true);
		var writing_no = getActiveWritingId();
		var comment_no = getActiveCommentId(e);
		var prefer = $(e.target).hasClass("up") ? 0 : 1;
		var recomWrap = $(e.target).parent();
		var recomCount = recomWrap.find(".count");
		var requestData = {
			writing_no,
			comment_no,
			prefer
		};
		oAjax
			.sendUpdateRequest(URL_UPDATE_RECOMCOUNT, requestData, "POST")
			.then(data => {
				console.log(data);
				var txtarea = recomWrap.find(".detail_replyuptx");
				var sum_prefer = parseInt(data.sum_prefer, 10);
				if (txtarea.length) {
					recomCount.text(sum_prefer);
				} else if (sum_prefer >= 1) {
					var tmpl = $.templates(ID_TMPL_RECOMCOUNTTX);
					var html = tmpl.render(data);
					recomWrap.prepend(html);
				}
				changeUpDownBtn(e);
				$(e.target).prop("disabled", false);
			})
			.catch(error => {
				console.log(error);
			});
	});

	// 대댓글 시스템 메뉴
	//시스템 모달 안으로 댓글 아이디 가져오기 및 내 댓글인지 여부에 따라 삭제버튼 disable
	$(document).on('click','.dot3',function(){
		var modal = $('#_system_modal');
		var low_comment = $(this).closest('.detail_reply_subitem');
		var comment = low_comment.length > 0 ? low_comment : $(this).closest('.detail_replyitem');
		var comment_no = comment.prop('id');
		var isCommentWrite = comment_no.slice(0, 3) != 'low';
		var delBtn = modal.find('.delete');
		var writeBtn = modal.find('.write');
		var isMine = comment.find('.ismine').val() == "true";
		var isDeleted = comment.find('.isdeleted').val() == 'N';
		modal.find('.comment_no').val(comment_no);
		delBtn.prop('disabled', isDeleted || !isMine);
		if (isCommentWrite){ //댓글에서 햄버거 버튼 클릭시
			delBtn.text('댓글 삭제');
			writeBtn.text('대댓글 작성').show();
		}else { //대댓글에서 햄버거 버튼 클릭시
			delBtn.text('대댓글 삭제');
			writeBtn.hide();
		}
	});

	$('#_system_modal').on('click', '.modal_btn', function (e) {
		var writing_no = oSwiper.getActiveSlideId();
		var slide = $(oSwiper.getActiveSlide());
		var modal = $(e.delegateTarget);
		var comment_no = modal.find('.comment_no').val();
		var comment = slide.find("#" + comment_no);
		var modalClose = modal.find('.modal_close');
		comment_no = getNumberInStr(comment_no);
		if ($(this).hasClass('write')) { //대댓글 작성
			comment.find('.detail_reply_more').click();
		} else if ($(this).hasClass('delete')) { //댓글 삭제
			oAjax.sendRequest(URL_REMOVE_COMMENT, { writing_no, comment_no }, null, 'POST', null).then(json => {
				if (json.success) {
					var contents = comment.find('.detail_replycont');
					contents.eq(0).text('삭제된 댓글입니다');
					comment.find('.isdeleted:first-child').val('N');
				} else {
					oToast.show('댓글삭제에 실패했습니다')
				}
			});
		}
		modalClose.click();
	});

	//대댓글 펼치기 : 버튼 UI 변경
	$("body").on("click", "button.detail_reply_more", function (e) {
		var btn = $(e.currentTarget);
		var accordion = btn.closest(".accordion");
		activateAccordion(accordion);
		addMention(e);
		setTimeout( () => {
			oSwiper.refreshSlideHeight();
		},200);
	});
	/* 본문 펼치기 */
	$(".swiper-wrapper").on("click", ".detail_btn ", function (e) {
		$(this).siblings(".detail_txtareawrap").toggle();
		oSwiper.refreshSlideHeight();
	});

	/* 투표 비동기 처리 */
	$(".wrapper")
		.on("click", ".card", function (e) {
			var cardWrap = $(e.currentTarget).closest(".card_wrap");
			if (cardWrap.hasClass("on")) return;
			changeDetailUI(e);
			var cards = cardWrap.find(".card");
			var voteNum = cards.index($(e.currentTarget)) + 1;
			var writingNo = getActiveWritingId();
			$.ajax({
				url: URL_UPDATE_VOTECOUNT,
				method: "POST",
				data: {
					voteNum,
					writingNo
				},
				dataType: "json",
				success: function (data) {
					updateCardsData(cards, data);
					oSwiper.refreshSlideHeight();
				},
				error: function (request, status, error) {
					console.log(request, status, error);
				}
			});
		})
		.on("update", ".card", function (e) {
			changeDetailUI(e);
		});

	/* 상단 토스트메세지 */
	$(".header_wrap").on("click", ".airplain", function (e) {
		var str = "URL이 복사되었습니다";
		oToast.show(str);
	});

	// 신고
	$('body').on('click', '.complain', function () {
		Complain();
	});

	function Complain(){
		var slide = $(oSwiper.getActiveSlide());
		var writing_no = getNumberInStr(slide.attr('id'));
		var btn = $('.complain');
		oAjax.sendRequest(URL_CREATE_COMPLAIN, {
			writing_no
		}, null, 'POST').then((data) => {
			if (data === true) {
				btn.addClass('on').prop('disabled', true);
				oToast.show("신고되었습니다");
			} else {
				oToast.show("이미 신고된 게시글입니다.");
			}
		}).catch(e => {
			oToast.show("신고가 처리되지 못했습니다.");
			console.log(e);
		});
	}

	$('.airplain').on('click',function(e){
		e.preventDefault();
		copyToClipboard(window.location.href);
	})
});