/* 브라우져 체킹 */
$(document).ready(function(){
	
	/* 페이지공통 */
	$('#pageWrap > div').each(function(){
		var $this = $(this);
		/* 화면 크게 보기 */
		$this.find('.btnTwoDepthMenuClose').click(function(){
			var $btnTwoDepthMenuClose = $(this);
			
			if($btnTwoDepthMenuClose.attr("id") != "mapAreaBtn"){
			
				if ($(this).hasClass('active') == false){
					$this.find('.container').animate({paddingLeft:280});
					$this.find('.twoDepthMenu').animate({left:-98},function(){
						$btnTwoDepthMenuClose.addClass('active');
					});
				} else {
					$this.find('.container').animate({paddingLeft:658});
					$this.find('.twoDepthMenu').animate({left:280},function(){
						$btnTwoDepthMenuClose.removeClass('active');
					});
				}
			}
		});
		
		/* 체크박스 전체선택 */
		/*
		if ($this.find('.thead').find(':checkbox').is(':checked')){
			$this.find('.tbody').find('.checkbox').addClass('active').find(':checkbox').prop('checked',true);
		}
		*/
	});

	/*** 인풋 클래스 ***/
	$('input:text').addClass('text');
	$('input:password').addClass('text');
	/*** 인풋 텍스트 ***/
	$('.placeholder').each(function(){
		var $this = $(this);
		var attrId = $(this).attr("id");
		if (!$this.val() == "") {
			$("label[for='"+attrId+"']").hide();
		}
		$(this).focus(function(){
			$("label[for='"+$(this).attr("id")+"']").fadeOut(400);
		}).blur(function(){
			if ($(this).val() == ""){
				$("label[for='"+$(this).attr("id")+"']").fadeIn(400);
				//$(this).css('color','#10c1b7');
			} else {
				//$(this).css('color','#10c1b7');
			}
		});
	});

	/* 셀렉트 디자인 */
	$('.select').each(function(){
		
		var $this = $(this);
		
		if(!$this.parent("div").hasClass("selectWrap")){
		
			$this.animate({opacity:0},0);
			$this.wrap('<div class="selectWrap">');
			var $selectWrap = $this.parent('.selectWrap');
			$this.before("<span class=\"selectTit\"></span>");
			var $tit = $this.siblings('.selectTit');
			/*var $val = $this.val();*/
			var $val = $this.children("option:selected").text();
			$selectWrap.width($this.width()+8);
			$selectWrap.css('visibility','visible');
			$tit.text($val);
			$this.mouseenter(function(){
				$tit.addClass('hover');
			}).mouseleave(function(){
				$tit.removeClass('hover');
			}).focus(function(){
				$tit.addClass('focus');
				$selectWrap.addClass('focus');
			}).blur(function(){
				$tit.removeClass('focus');
				$selectWrap.removeClass('focus');
			});
			$this.change(function(){
				/*$val = $(this).val();*/
				$val = $(this).children("option:selected").text();
				$tit.text($val);
			});
		}
	});
	$('.selectTab').find('.selectWrap').eq('0').addClass('selectBtn');
	/* 체크박스 */
	$('.checkbox').each(function(){
		var $this = $(this);
		if ($this.find(':checkbox').is(':checked')){
			$this.addClass('active');
			if ($this.parents('tr').is('tr')){
				$this.parents('tr').addClass('active');
			}else{
				$this.removeClass('active');
				if ($this.parents('tr').is('tr')){
					$this.parents('tr').removeClass('active');
				}
			}
		}
	});
	
	/* 레이어팝업 */
	$('.layer').each(function(){
		var width = $(this).width();
		$(this).css('margin-left',-width/2);
		$(this).css('top',($('html,body').scrollTop()+$(window).height()/2)-($(this).height()/2));
		if ($(this).css('display') == 'block'){
			$(this).css('margin-top','0px');
		} else {
			$(this).css('margin-top','-40px');
		}
	});
	$('.maskShow').fadeTo(0,0.4);
	/*
	$('.btnLayerClose').click(function(){
		var $layer = $(this).parents('.layer');
		if ($(this).hasClass('btnLayerOpen')){
			$layer.fadeOut({queue: false, duration:800});
			$layer.animate({marginTop:-40},600);
		} else {
			$layer.fadeOut({queue: false, duration:800});
			$layer.animate({marginTop:-40},600);
			$('#mask').delay(200).fadeOut(600);
		}
		return false;
	});
	$('.btnLayerOpen').click(function(){
		href = $(this).attr('href');
		$(href).css('top',($('html,body').scrollTop()+$(window).height()/2)-($(href).height()/2));
		$('#mask').fadeTo(600,0.4,function(){
			$(href).fadeIn({queue: false, duration:600});
			$(href).animate({marginTop:30},600);
			$(href).animate({marginTop:0},400);
		});
		return false;
	});
	*/
	//$('#pageWrap > div').eq('0').siblings('div').hide();
	/* 검색결과 스크롤 가변 */
	scroll();
	$(window).resize(scroll);
});

function scroll(){
	//setTimeout(function(){
		$('.scroll').each(function(){
			var wrapHeight = $('#wrap').height();
			var prevHeight = $(this).prev().outerHeight(true);
			var nextHeight = $(this).next().outerHeight(true);
			$(this).outerHeight(wrapHeight - prevHeight - nextHeight);
		});
	//}, 200);
}

$(function(){
	$(".date").datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '2013:2018',
		showOn: "button",
		buttonImage: "/niis/images/board/btn_datepicker.png",
		buttonImageOnly: true,
		buttonText: "선택",
		inline: true,
		duration: 300,
		showAnim: "slideDown"
	});
	$(".dateSearch").datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '2013:2018',
		showOn: "button",
		buttonImage: "/niis/images/board/btn_datepicker_2.png",
		buttonImageOnly: true,
		buttonText: "선택",
		inline: true,
		duration: 300,
		showAnim: "slideDown"
	});
	$.datepicker.regional['ko'] = {
		closeText: '닫기',
		prevText: '이전달',
		nextText: '다음달',
		currentText: '오늘',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		dateFormat: 'yy-mm-dd', firstDay: 0,
		isRTL: false};
	$.datepicker.setDefaults($.datepicker.regional['ko']);


});