$(document).ready(function(){
	/*** 트리메뉴 ****/
	$('#combineTreeMenu a.tree').each(function(){
		$this = $(this);
		if ($this.nextAll().is('ul')){
			$this.addClass('treeMi');
		}
	});
	$('#combineTreeMenu a.treeMi').click(function(){
		if ($(this).nextAll('ul').css('display') == 'none'){
			$(this).removeClass('treePl');
			$(this).nextAll('ul').slideDown(200);
		} else {
			$(this).addClass('treePl');
			$(this).nextAll('ul').slideUp(200);
		}
		return false;
	});
	/* 트리 체크박스 */
	$('#combineTreeMenu input:checkbox').click(function(){
		if ($(this).is(":checked")){
			$(this).nextAll('ul').find('input:checkbox').prop("checked", true) ;
		} else {
			$(this).nextAll('ul').find('input:checkbox').prop("checked", false) ;
		}
	});
	/*** 1뎁스 탭 ***/
	$('.tab').each(function(){
		var $this = $(this);
		$this.find('.tabBtn a').click(function(){
			var $index = $(this).parent().index();
			$(this).addClass('active');
			$(this).parent().siblings().find('> a').removeClass('active');
			$this.find('.tabArea > div').removeClass('active').eq($index).addClass('active');
			combineTreeMenuHeight();
		});
	});
	/*** 2뎁스 탭 ***/
	$('.tabBtn2Depth a').click(function(){
		var $index = $(this).parent().index();
		$(this).addClass('active');
		$(this).parent().siblings().find('> a').removeClass('active');
		$('.tabArea2Depth > div').removeClass('active').eq($index).addClass('active');
		combineTreeMenuHeight();
	});
	/*** 원본DB 통합검색 트리 화면 늘임 ***/
	combineTreeMenuHeight();
	$(window).resize(function(){
		if ($('#combineSearchEx').hasClass('active')){
			combineTreeMenuHeightAuto();
		} else {
			combineTreeMenuHeight();
		}
	});
	$('#combineSearchEx').click(function(){
		if ($(this).hasClass('active') == false){
			combineTreeMenuHeightAuto();
		} else {
			combineTreeMenuHeight();
		}
	});
});
/* 원본DB 통합검색 트리 높이 기본값 */
function combineTreeMenuHeight(){
	var $wrapHeight = $('#wrap').height();
	var $combineSearchTopHeight = $('.selectTab').innerHeight();
	var $combineSearchCenterHeight = $('.parentWrap').innerHeight();
	$('#combineSearchEx').removeClass('active');
	$('#combineTreeMenu').height($wrapHeight - $combineSearchTopHeight - $combineSearchCenterHeight - 161);
}
/* 원본DB 통합검색 트리 높이 늘임값 */
function combineTreeMenuHeightAuto(){
	var $combineTreeMenuHeight = $('#wrap').height()-151;
	$('#combineSearchEx').addClass('active');
	$('#combineTreeMenu').height($combineTreeMenuHeight);
	//$('#combineTreeMenu').animate({height:$combineTreeMenuHeight});
}


