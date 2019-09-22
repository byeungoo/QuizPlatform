var ssj = ssj || {};
ssj.view = ssj.view || {};

$(function(){

	// define common function
	function getActiveSlideId() {
		return getNumberInStr($(oSwiper.getActiveSlide()).attr('id'));
	}
	function getActiveCommentId(e) {
		var commentWrap = $(e.target).closest('.detail_reply_subitem').get(0) || $(e.target).closest('.detail_replyitem').get(0);
		return getNumberInStr($(commentWrap).attr('id'));
	}
	function Complain() {
		var slide = $(oSwiper.getActiveSlide());
		var writing_no = getNumberInStr(slide.attr('id'));
		var $btn = $('.complain');

		oAjax.sendRequest(URL_CREATE_COMPLAIN, { writing_no }, null, 'POST')
			.then(isSuccess => {
				const message = isSuccess ? '신고되었습니다' : '이미 신고된 게시글입니다';

				$btn.toggleClass('on', isSuccess).prop('disabled', isSuccess);
				oToast.show(message);
			}).catch(e => {
				oToast.show('신고가 처리되지 못했습니다.');
				console.log(e);
			}
			);
	}
	function isMentionExist() {
		return $('.reply_mention').length > 0;
	}
	function resetAddress() {
		var id = getNumberInStr($(oSwiper.getActiveSlide()).attr('id'));
		var url = window.location.origin + window.location.pathname + '?writing_no=' + id;
		window.history.replaceState(null, null, url);
	}
	function toggleFooter() {
		isAlreadyVoted() ? Footer.show() : Footer.hide();
	}
	function makeFirstItem(writingNo) {
		var requestData = {
			writingNo
		};
		return oAjax.sendRequest(URL_READ_FIRST_SLIDE_DATA, requestData, ID_TMPL_SLIDE, 'GET');
	}
	function makeSlideItems() {
		var page = oAjax.getCurrentPageNum(PAGE_NAME_SWIPE);
		var requestData = {
			page,
			writing_no: mainWritingNo
		};
		return oAjax.sendRequest(URL_READ_SLIDE_DATA, requestData, ID_TMPL_SLIDE, 'GET', PAGE_NAME_SWIPE);
	}
	function isAlreadyVoted() {
		var slide = oSwiper.getActiveSlide();
		return $(slide)
			.find('.card_wrap')
			.hasClass('on');
	}
	function setInitialData(mainWritingNo) {
		makeFirstItem(mainWritingNo)
			.then(item => {
				oSwiper.appendItem(item);
				setInitialVoteState();
				setHeaderState();
				return makeSlideItems();
			})
			.then(item => {
				oSwiper.appendItem(item);
			});
	}
	function setInitialVoteState() {
		var slide = oSwiper.getActiveSlide();
		var vote = parseInt($(slide).find('.whereYouVote').val(), 10) - 1;
		var cards = $(slide).find('.card');
		if (vote >= 0) {
			$(cards.get(vote)).trigger('update');
		}
	}
	function setHeaderState() {
		var slide = $(oSwiper.getActiveSlide());
		var header = $('.header_wrap');
		var firstBtnGroup = header.find('.forfistpage');
		var isComplained = slide.find('.isComplained').val() == 'true';
		if (isComplained) {
			$('.complain').addClass('on').prop('disabled', true);
		} else {
			$('.complain').removeClass('on').prop('disabled', false);
		}
		!oSwiper.getActiveIndex() ? firstBtnGroup.show() : firstBtnGroup.hide();
	}
	function activateModal() {
		$('a[rel*=leanModal]').leanModal({ overlay: 0.4, slideinUp: '_system_modal' });
	}
	function updateCardsData(cards, json) {
		cards.map(function (index, item) {
			var prtg = $(item).find('.percentage');
			var count = $(item).find('.count');
			prtg.text(json.votePerc[index]);
			count.text(json.voteNoArr[index]);
		});
		console.log(json);
		cards
			.closest('.swiper-slide')
			.find('.vote')
			.next('.card__icon-desc')
			.text(json.totalVoteNum);
	}
	function changeDetailUI(e) {
		var cardwrap = $(e.target).closest('.card_wrap');
		var card = $(e.target).closest('.card');
		var cards = cardwrap.find('.card');
		var bottomInfo = cardwrap.siblings('.detail_bottom');
		var slide = $(oSwiper.getActiveSlide());
		cardwrap.addClass('on');
		cardwrap.data('vote', 1);
		bottomInfo.addClass('on');
		cards.removeClass('on');
		card.addClass('on');
		toggleFooter();
		$.observer.notify('comment.setTeam', $('.detail_replyitem'));
		activateModal();
	}


	// attach event handler
	$('body').on('click', '.complain', Complain);
	$('body').on('click', '.airplain', e => { 
		e.preventDefault();
		oToast.show('URL이 복사되었습니다'); 
		copyToClipboard(window.location.href); 
	});
	$('body').on('click', '.back', e => {
		document.referrer === '' ? window.location.href = 'http://pickvs.com' : window.history.back();
	});
	// 본문 펼치기 
	$('body').on('click', '.detail_btn', function (e) {
		$(this).siblings('.detail_txtareawrap').toggle();
		$.observer.notify('swiper.slide.refresh');
	});

	// 대댓글 작성 버튼 클릭 시
	$('#_system_modal').on('click', '.modal_btn', function (e) {
		var writing_no = oSwiper.getActiveSlideId();
		var slide = $(oSwiper.getActiveSlide());
		var modal = $(e.delegateTarget);
		var comment_no = modal.find('.comment_no').val();
		var comment = slide.find('#' + comment_no);
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

	/* 투표 비동기 처리 */
	$('.wrapper')
		.on('click', '.card', function (e) {
			var cardWrap = $(e.currentTarget).closest('.card_wrap');
			if (cardWrap.hasClass('on')) return;
			changeDetailUI(e);
			var cards = cardWrap.find('.card');
			var voteNum = cards.index($(e.currentTarget)) + 1;
			var writingNo = getActiveSlideId();
			$.ajax({
				url: URL_UPDATE_VOTECOUNT,
				method: 'POST',
				data: {
					voteNum,
					writingNo
				},
				dataType: 'json',
				success: function (data) {
					updateCardsData(cards, data);
					$.observer.notify('swiper.slide.refresh');
				},
				error: function (request, status, error) {
					console.log(request, status, error);
				}
			});
		})
		.on('update', '.card', function (e) {
			changeDetailUI(e);
		});

	// define Module
	ssj.view.comment = function (options) {
		const df = {};
		$.extend(this, df, options);
		this.init();
	}
	ssj.view.comment.prototype = $.extend({
		init() {
			this._assignElements();
			this._attachEventHandlers();
		},
		_assignElements() {
			this.$body = $('body');
			this.$hdVote = $('.whereYouVote');
		},
		_attachEventHandlers() {
			this.$body.on('click', '.detail_reply_more', e => this._onExpand.call(this, e));
			this.register('comment.refresh.lowcomment', target => this.refreshLowComment(target));
			this.register('comment.refresh.comment', target => this.refreshComment(target));
			this.register('comment.setTeam', target => this.setTeam(target));
		},
		_onExpand(e) {
			const $acdo = $(e.currentTarget).closest('.accordion');
			this.activateAccordion($acdo);
			this.clickAcdoControl($acdo);
			$.wait(300).then( () => this.refreshSwiper() );
		},
		clickAcdoControl($acdo){
			const $acdoCtrl = $acdo.find('[data-control]');
			$acdoCtrl.trigger('click');
		},
		refreshSwiper() {
			this.notify('swiper.slide.refresh');
		},
		activateAccordion(accordion) {
			$(accordion).accordion({
				transitionSpeed: 700,
				transitionEasing: 'cubic-bezier(0.23, 1, 0.32, 1)'
			});
		},
		refreshLowComment(target) {
			const $acdo = $(target).closest('.accordion');
			const $acdoCtrl = $acdo.find('[data-control]');
			const $replyCount = $acdo.find('.detail_reply_count');
			// TODO : 서버에서 대댓글 불러와서 업데이트 해주기
			$replyCount.text( parseInt($replyCount.text(), 10) + 1 );
			$acdoCtrl.trigger('accordion.refresh');
			$.wait(300)
				.then(() => this.refreshSwiper())
				.then(() => {
					$acdoCtrl.trigger('accordion.scrollToLastChild', target);
				});
		},
		refreshComment(target){
			const $acdo = $(target).closest('.accordion');
			const $acdoCtrl = $acdo.find('[data-control]');
			this.refreshSwiper();
			scrollToBottom();
		},
		setTeam(target) {
			$(target).each((index, item) => {
				const nMyVoted = this.checkWhereVote();
				const $nickName = $(target).find('.detail_replytit');
				const nOtherVoted = $nickName.val();
				$nickName.toggleClass('on', nMyVoted === nOtherVoted);
				// TODO : 아군 표시 작업 마무리하기
			});
		},
		checkWhereVote() {
			return this.$hdVote.val() + 1;
		},
	}, $.observer);

	ssj.view.comment.optionBar = function( options ){
		const df = {};
		$.extend(this, df, options);
		this.init();
	}

	ssj.view.comment.optionBar.prototype = $.extend({
		init() {
			this._initVar();
			this._assignElements();
			this._attachEventHandlers();
		},
		_initVar() {
			this.TYPE_COMMENT = 0;
			this.TYPE_LOWCOMMENT = 1;
		},
		_assignElements() {
			this.$body = $('body');
			this.$optModal = $('#_system_modal');
			this.$hdCommentNo = this.$optModal.find('.comment_no');
		},
		_attachEventHandlers() {
			this.$body.on('click', '.recommend', e => this._onClickRecommend.call(this, e));
			this.$body.on('click', '.dot3', e => this._onClickOptions.call(this, e));
		},
		_onClickOptions(e) {
			this.$comment = $(e.target).closest('.detail_replyitem');
			this.initModalVar();
			this.setCommentNoToModal( this.getCommentNo() );

			if(this.bType == this.TYPE_COMMENT){
				this.$delBtn.text('댓글 삭제');
				this.$writeBtn.text('대댓글 작성').show();
			}
			else if(this.bType == this.TYPE_LOWCOMMENT){
				this.$delBtn.text('대댓글 삭제');
				this.$writeBtn.hide();
			}

			this.notify('mention.show',{
				nickname : this.getNickName(),
				commentNo : this.getCommentNo()
			});

		},
		_onClickRecommend(e) {
			const $btn = $(e.target);
			const $btnWrap = $btn.closest('.detail_replyinfos');
			if (this.isVoted($btnWrap)) {
				return false;
			}
			$btn.prop('disabled', true);
			const requestData = this.getRecomReqData(e);
			this.sendRecomReq(e, requestData);
		},
		initModalVar(){
			this.$delBtn = this.$optModal.find('.delete');
			this.$writeBtn = this.$optModal.find('.write');
			this.bType = this.getCommentType();
		},
		getRecomReqData(e) {
			return {
				prefer: $(e.target).hasClass('up') ? 0 : 1,
				writing_no: getActiveSlideId(),
				comment_no: getActiveCommentId(e)
			}
		},
		getNickName() {
			return this.$comment.find('.detail_replytit').eq(0).text();
		},
		getCommentNo() {
			return getNumberInStr(this.getCommentId());
		},
		getCommentId() {
			return this.$comment.prop('id');
		},
		getCommentType() {
			return this.getCommentNo().slice(0, 3) !== 'low'? this.TYPE_COMMENT : this.TYPE_LOWCOMMENT;
		},
		setCommentNoToModal(comment_no) {
			this.$hdCommentNo.val(comment_no);
		},
		disabledModalBtn() {
			delBtn.prop('disabled', this.isCommentDeleted() || !this.isCommentMine() );
		},
		isCommentMine() {
			return this.$comment.find('.ismine').val() == 'true';
		},
		isCommentDeleted() {
			return this.$comment.find('.isdeleted').val() == 'N';
		},
		sendRecomReq(e, requestData) {
			const $btn = $(e.target);
			oAjax
				.sendUpdateRequest(URL_UPDATE_RECOMCOUNT, requestData, 'POST')
				.then(data => {
					this.updateRecommendConts(e, data);
					this.updateRecommendUI(e);
				})
				.catch(error => {
					console.log(error);
				});
		},
		updateRecommendConts(e, responseData) {
			const $recomParent = $(e.target).parent();
			const $recomCount = $recomParent.find('.count');
			const nTotalRecomCount = parseInt(responseData.sum_prefer, 10);
			const bExistDesc = $recomParent.find('.detail_replyuptx').length > 0;
			const bShowDesc = responseData.sum_prefer >= 1;

			if (bExistDesc) {
				$recomCount.text(nTotalRecomCount);
			}
			else if (bShowDesc) {
				$recomParent.prepend($.templates(ID_TMPL_RECOMCOUNTTX).render(responseData));
			}

		},
		updateRecommendUI(e) {
			const $btn = $(e.target);
			const $btnWrap = $btn.parent();

			if ($btn.hasClass('on')) {
				return false;
			}

			$btn.hasClass('up') ? $btnWrap.addClass('up') : $btnWrap.addClass('down');
			$btn.addClass('on').prop('disabled', false);
		},
		isVoted($btnWrap) {
			return $btnWrap.hasClass('up') || $btnWrap.hasClass('down');
		}
	}, $.observer);

	ssj.view.replyInput = function (options) {
		const df = {};
		$.extend(this, df, options);
		this.init();
	}
	ssj.view.replyInput.prototype = $.extend({
		init() {
			this._initVar();
			this._assignElements();
			this._attachEventHandlers();
		},
		_initVar() {
			this.TYPE_COMMENT = 0;
			this.TYPE_LOWCOMMENT = 1;
		},
		_assignElements() {
			this.$input = $('.reply_input');
			this.$inputSubmitBtn = $('.reply_submit');
		},
		_attachEventHandlers() {
			this.register('clearReplyInput', this.clear);
			this.$inputSubmitBtn.on('click', this._onSubmit.bind(this));
		},
		_onSubmit() {
			if(this.isInputEmpty()){
				oToast.show('댓글을 입력해주세요', 2000);
				return false;
			}

			const writingNo = getActiveSlideId();
			const replytx = this.getInputText();
			const depth = this.getCommentType();
			const slide = oSwiper.getActiveSlide();

			if( depth === this.TYPE_COMMENT ){
				const $commentList = slide.find('.detail_replylist');
				const requestData = { writingNo, replytx, depth, parent: null };

				this.addComment($commentList, requestData);
			}
			else if( depth === this.TYPE_LOWCOMMENT ){
				const parent = oMention.getCommentNo();
				const comment = slide.find(`#comment${parent}`);
				const $lowcommentList = comment.find('.detail_reply_subitems');

				requestData = { writingNo, replytx, depth, parent };
				this.addLowComment($lowcommentList, requestData);
			}

		},
		getCommentType() {
			return isMentionExist()? this.TYPE_LOWCOMMENT : this.TYPE_COMMENT;
		},
		clear() {
			this.$input.val('');
			this.$inputSubmitBtn.removeClass('on');
			this.notify('mention.hide');
		},
		addComment($commentList, requestData) {
			oAjax.sendRequest(URL_CREATE_COMMENT, requestData, ID_TMPL_REPLY, 'POST').then(comment => {
				$commentList.append(comment);
				comment = $commentList.children(':last-child');
				this.notify('comment.refresh.comment', comment);
				this.afterAddComment($commentList);
			});
		},
		addLowComment($commentList, requestData) {
			oAjax.sendRequest(URL_CREATE_COMMENT, requestData, ID_TMPL_SUBREPLY, 'POST').then(comment => {
				$commentList.append(comment);
				const accordion = $($commentList).closest('.accordion');
				const moreBtn = accordion.find('.detail_reply_more');
				moreBtn.click();
				const $comment = $commentList.children(':last-child');
				this.notify('comment.refresh.lowcomment', $comment);
				this.afterAddComment($comment);
			});
		},
		afterAddComment($comment) {
			this.notify('comment.setTeam', $comment);
			this.clear();
			activateModal();
		},
		isInputEmpty() {
			return !this.$input.val().length;
		},
		getInputText() {
			return this.$input.val();
		}
	}, $.observer);

	ssj.util.swiper = function (options) {
		this.metaData = options;
		this.init();
	};
	ssj.util.swiper.prototype = $.extend({
		init() {
			this.initVar();
			this._attachEventHandler();
		},
		initVar() {
			this.swiper = new Swiper('.swiper-container', this.metaData);
		},
		_attachEventHandler() {
			this.register('swiper.slide.refresh', this.refreshHeight);
		},
		appendItem(slide) {
			this.swiper.appendSlide(slide);
		},
		getActiveSlide() {
			return $(this.swiper.slides[this.getActiveIndex()]);
		},
		getActiveIndex() {
			return this.swiper.activeIndex;
		},
		getTotalCount() {
			return this.swiper.slides.length;
		},
		getActiveSlideId() {
			return getNumberInStr($(this.getActiveSlide()).attr('id'));
		},
		refreshHeight() {
			const footHeight = $('.reply_inputwrap').innerHeight();
			const slideHeight = $(this.getActiveSlide()).find('section').innerHeight();
			$('.swiper-container').css({
				'height': slideHeight + footHeight * 2,
				'overflow': 'hidden'
			});
		}
	}, $.observer);

	ssj.view.mention = function (options) {
		const df = {};
		$.extend(this, df, options);
		this.init();
	}
	ssj.view.mention.prototype = $.extend({
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
			this.$mentionCloseBtn.on('click', this._onClose.bind(this));
			this.register('mention.show', ({ nickname, commentNo }) => this.show.call(this, nickname, commentNo));
			this.register('mention.hide', this._onClose);
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
				<div class='reply_mention'>
					<span class='reply_mention_tx'>${nickname}</span>
					<button class='sp00 close' onclick='$(this).parent().remove()'></button>
				</div>
				`);
		},
		applyAnimation() {
			this.$mention.removeClass('reply_mention');
			void this.$mention.get(0).offsetWidth; // force repaint
			this.$mention.addClass('reply_mention');
		},
		_clearMention() { this.$mentionNickname.text(''); this.$mentionCommentNo.val(''); },
		removeMention() { this.$mention.detach() },
		addNickname(nickname) { this.$mentionNickname.text(nickname) },
		addCommentNo(commentNo) { this.$mention.data('id', commentNo) },
		addMention() { this.$addTarget.prepend(this.$mention) },
		getCommentNo() { return this.$mention.data('id'); },
	}, $.observer);

	// Initialize Module
	window.oMention = new ssj.view.mention();
	window.oComment = new ssj.view.comment();
	window.oReplyInput = new ssj.view.replyInput();
	window.oCommentOptionBar = new ssj.view.comment.optionBar();


	//TODO : Swiper 모듈 리팩토링 하기
	var mainWritingNo = getWritingNoFromURL();
	var Footer = $('.reply_inputwrap');
	oSpinner.setTarget($('.swiper-container'));
	oSpinner.show(); //페이지 처음 진입시 스피너

	window.oSwiper = $.extend(new ssj.util.swiper({
		direction: 'horizontal',
		slidesPerview: 1.1,
		centeredSlides: true,
		preventClicksPropagation: false,
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
				this.notify('swiper.slide.refresh');
				toggleFooter();
				scrollToTop();
				setHeaderState();
				resetAddress();
				resetMention();
			}
		}
	}, $.observer));

	toggleFooter();
});
