/* 브라우져 체킹 */
(function($){$.browserTest=function(a,z){var u='unknown',x='X',m=function(r,h){for(var i=0;i<h.length;i=i+1){r=r.replace(h[i][0],h[i][1]);}return r;},c=function(i,a,b,c){var r={name:m((a.exec(i)||[u,u])[1],b)};r[r.name]=true;r.version=(c.exec(i)||[x,x,x,x])[3];if(r.name.match(/safari/)&&r.version>400){r.version='2.0';}if(r.name==='presto'){r.version=($.browser.version>9.27)?'futhark':'linear_b';}r.versionNumber=parseFloat(r.version,10)||0;r.versionX=(r.version!==x)?(r.version+'').substr(0,1):x;r.className=r.name+r.versionX;return r;};a=(a.match(/Opera|Navigator|Minefield|KHTML|Chrome/)?m(a,[[/(Firefox|MSIE|KHTML,\slike\sGecko|Konqueror)/,''],['Chrome Safari','Chrome'],['KHTML','Konqueror'],['Minefield','Firefox'],['Navigator','Netscape']]):a).toLowerCase();$.browser=$.extend((!z)?$.browser:{},c(a,/(camino|chrome|firefox|netscape|konqueror|lynx|msie|opera|safari)/,[],/(camino|chrome|firefox|netscape|netscape6|opera|version|konqueror|lynx|msie|safari)(\/|\s)([a-z0-9\.\+]*?)(\;|dev|rel|\s|$)/));$.layout=c(a,/(gecko|konqueror|msie|opera|webkit)/,[['konqueror','khtml'],['msie','trident'],['opera','presto']],/(applewebkit|rv|konqueror|msie)(\:|\/|\s)([a-z0-9\.]*?)(\;|\)|\s)/);$.os={name:(/(win|mac|linux|sunos|solaris|iphone)/.exec(navigator.platform.toLowerCase())||[u])[0].replace('sunos','solaris')};if(!z){$('html').addClass([$.os.name,$.browser.name,$.browser.className,$.layout.name,$.layout.className].join(' '));}};$.browserTest(navigator.userAgent);})(jQuery);//http://jquery.thewikies.com/browser/

$(document).ready(function(){
	/* 페이지이동 */
	/*$('.pageMove').click(function(){*/
	/* main.js 메뉴이동 공통처리
	$('body').on('click', '.pageMove', function(){	
		var $this = $(this);
		href = $(this).attr('href');
		$('#pageUp > div').hide();
		$('.pageMove').removeClass('active');
		$this.addClass('active');
		$('#pageWrap > div').hide().removeClass('active');
		$(href).show().addClass('active');
		if ($this.parents('.iden').hasClass('iden')){
			$('.gnb > li').removeClass('active');
		}
		scroll();
		return false;
	});
	*/
	
	/* GNB 클릭시 */
	/*$('.gnb a').click(function(){*/
	$('body').on('click', '.gnb a', function(){
		$('#pageUp > div').hide();
		$(this).parent().addClass('active').siblings().removeClass('active');
		if ($(this).attr('href') == '#none'){
			/*
			$('#pageWrap > div').hide().removeClass('active');
			$($(this).siblings().find('.active a').attr('href')).addClass('active').show();
			*/
			$('.iden a').removeClass('active');
			
		}
		scroll();
		return false;
	});
	
	
	/* 페이지 올리기 */
	$('body').on('click', '.btnPageUp', function(){
		var $this = $(this);
		href = $(this).attr('href');
		$this.addClass('active');
		$('#pageWrap > div').hide();
		$(href).show().addClass('active').siblings().hide().removeClass('active');
		$('.noticeRegist').hide();
		
		//이용아내 스크롤 초기화
		if(href == "#serviceGuide"){
			$(href).find(".scroll").each(function(){
				$(this).mCustomScrollbar("destroy");
				$(this).mCustomScrollbar();
			});
		}
		
		scroll();
		return false;
	});

	/* 올린페이지 닫기 */
	$('body').on('click', '.btnPageUpClose',function(){
		$('#pageUp > div').hide();
		$('#pageWrap > div.active').show();
		//$('.noticeRegist').hide();
		scroll();
		return false;
	});

	/*
	$('.btnRegist').click(function(){
		//$('.noticeView').hide();
		$('.noticeRegist').eq('0').show();
		scroll();
		return false;
	});
	*/
	
	/*
	$('.btnBoardViewTab a').click(function(){
		var $index = $(this).index();
		//$('.btnBoardViewTab a').removeClass();
		//$('.noticeRegist').eq($index).find('a').eq($index).addClass('active');
		$('.noticeRegist').hide().eq($index).show();
		scroll();
		return false;
	});
	*/
	
	/*
	$('.btnNoticeRegistClose').click(function(){
		$('.noticeRegist').hide();
	});
	*/
	
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
		//if ($this.find('.thead').find(':checkbox').is(':checked')){
			//$this.find('.tbody').find('.checkbox').addClass('active').find(':checkbox').prop('checked',true);
		//}
		/*
		$this.find('.thead').find('.checkbox').click(function(){
			if ($(this).find(':checkbox').is(':checked')){
				$this.find('.tbody .checkbox').removeClass('active').find(':checkbox').prop('checked',false);
				$this.find('.tbody tr ').removeClass('active');
			} else {
				$this.find('.tbody .checkbox').addClass('active').find(':checkbox').prop('checked',true);
				$this.find('.tbody tr ').addClass('active');
			}
		});
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
	});
	//$('.selectTab').find('.selectWrap').eq('0').addClass('selectBtn');
	$('.selectTab').each(function() {
		$(this).find('.selectWrap').eq('0').addClass('selectBtn');
	});
	/* 체크박스 */
	$('.checkbox').each(function(){
		var $this = $(this);
		if ($this.find(':checkbox').is(':checked')){
			$this.addClass('active');
			if ($this.parents('tr').is('tr')){
				$this.parents('tr').addClass('active');
			}
		}else{
			$this.removeClass('active');
			if ($this.parents('tr').is('tr')){
				$this.parents('tr').removeClass('active');
			}
		}
	});
	$('body').on('click', '.checkbox', function(){
		
		$(this).find(':checkbox').click();
		var isChecked = $(this).find(':checkbox').is(':checked');
		
		if (isChecked){
			$(this).addClass('active');
			if ($(this).parents('tr').is('tr')){
				$(this).parents('tr').addClass('active');
			}
		} else {
			$(this).removeClass('active');
			if ($(this).parents('tr').is('tr')){
				$(this).parents('tr').removeClass('active');
			}
		}
	});
	$('body').on('mouseenter', '.checkbox', function(){
		$(this).siblings('.labelTxt').addClass('hover');
	});
	$('body').on('mouseleave', '.checkbox', function(){
		$(this).siblings('.labelTxt').removeClass('hover');
	});
	
	$('body').on('click', '.labelTxt', function(){
		
		$(this).siblings('.checkbox').find(':checkbox').click();
		var isChecked = $(this).siblings('.checkbox').find(':checkbox').is(":checked");
		
		if (isChecked){
			$(this).siblings('.checkbox').addClass('active');
		} else {
			$(this).siblings('.checkbox').removeClass('active');
		}
	});
	$('body').on('mouseenter', '.labelTxt', function(){
		$(this).siblings('.checkbox').addClass('hover');
	});
	$('body').on('mouseleave', '.labelTxt', function(){
		$(this).siblings('.checkbox').removeClass('hover');
	});
	
	/*** IMG HOVER ***/
	$('.over img').click(function(){
		$('.siblingsToggle').each(function(){
			var imgSrc = $(this).find('img').attr('src').replace('_on.gif','.gif');
			$(this).find('img').attr('src',imgSrc);
		});
	}).mouseenter(function(){
		function chk_str(org_str, searchStr){
			return org_str.indexOf(searchStr)
		}
		var strFirstIndex = chk_str($(this).attr('src'),".gif" );
		if(strFirstIndex > 0) {
			var imgSrc = $(this).attr('src').replace('.gif','_on.gif');
			$(this).attr('src',imgSrc);
		} else {
			var imgSrc = $(this).attr('src').replace('.png','_on.png');
			$(this).attr('src',imgSrc);
		}
	}).mouseleave(function(){
		function chk_str(org_str, searchStr){
			return org_str.indexOf(searchStr)
		}
		var strFirstIndex = chk_str($(this).attr('src'),".gif" );
		if(strFirstIndex > 0) {
			var imgSrc = $(this).attr('src').replace('_on.gif','.gif');
			$(this).attr('src',imgSrc);
		} else {
			var imgSrc = $(this).attr('src').replace('_on.png','.png');
			$(this).attr('src',imgSrc);
		}
	});
	/*** TOGGLE ***/
	$('.toggle').click(function(){
		function chk_str(org_str, searchStr){
			return org_str.indexOf(searchStr)
		}
		var strFirstIndex = chk_str($(this).find('img').attr('src'),"_on.gif" );
		if(strFirstIndex > 0) {
			var imgSrc = $(this).find('img').attr('src').replace('_on.gif','.gif');
			$(this).find('img').attr('src',imgSrc);
		} else {
			var imgSrc = $(this).find('img').attr('src').replace('.gif','_on.gif');
			$(this).find('img').attr('src',imgSrc);
		}
		return false;
	});
	/*** SIBLINGSTOGGLE ***/
	/*
	$('.siblingsToggle').click(function(){
		function chk_str(org_str, searchStr){
			return org_str.indexOf(searchStr)
		}
		var strFirstIndex = chk_str($(this).find('img').attr('src'),"_on.gif" );
		if(strFirstIndex > 0) {
			var imgSrc = $(this).find('img').attr('src').replace('_on.gif','.gif');
			$(this).find('img').attr('src',imgSrc);
		} else {
			var imgSrc = $(this).find('img').attr('src').replace('.gif','_on.gif');
			$(this).find('img').attr('src',imgSrc);
		}
		$(this).siblings('.siblingsToggle').each(function(){
			var imgSrc = $(this).find('img').attr('src').replace('_on.gif','.gif');
			$(this).find('img').attr('src',imgSrc);
		});
		return false;
	});
	 */
	
	/* 원본DB검색 검색구분 탭 */
	var index = $('.selectTab .select:eq("0") option:selected').index();
	$('.tab > div').hide().eq(index).show();
	$('.btnServiceGuideWrap > a').hide().eq(index).show();
//	$('.selectTab .select:eq("0") option').click(function(){
	$('#searchTabSelect').change(function(){
		var idx = $(this).val(); //index();
		$('.tab > div').hide().eq(idx).show();
		$('.btnServiceGuideWrap > a').hide().eq(idx).show();
		scroll();
	});
	
	/* 원본DB검색 성과구분 탭 */
	var index = $('#mapTab .select:eq("0") option:selected').index();
	$('.tab2 > div').hide().eq(index).show();
//	$('.btnServiceGuideWrap > a').hide().eq(index).show();
	$('#mapTabSelect').change(function(){
		var idx = $(this).val(); //index();
		$('.tab2 > div').hide().eq(idx).show();
//		$('.btnServiceGuideWrap > a').hide().eq(idx).show();
		scroll();
	});

	/* 검색버튼 클릭시 부모 숨김 */
	/*
	$('.btnParentHide').click(function(){
		$(this).parents('.top').find('.parentWrap').fadeOut(600,function(){
		//$('.parentWrap').fadeOut(600,function(){
			scroll();
		});
		$('.btnCloseOpen').addClass('active');
	});
	*/

	/* 검색 오픈 클로즈 */
	$('body').on('click', '.btnCloseOpen', function(){
		if (!$(this).hasClass('active')){
			$(this).addClass('active');
			$(this).parent().next('.parentWrap').fadeOut(600,function(){
				scroll();
			});
		} else {
			$(this).removeClass('active');
			$(this).parent().next('.parentWrap').fadeIn(600);
			scroll();
		}
	});
	/*
	 영상종류 토글 
	$('.sortToggle li').click(function(){
		if ($(this).hasClass('active')){
			$(this).removeClass('active');
		} else {
			$(this).addClass('active');
		}
	});
	*/
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
	$("body").on("click", '.btnLayerClose', function(){
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
	$("body").on("click", '.btnLayerOpen', function(){
		href = $(this).attr('href');
		$(href).css('top',($('html,body').scrollTop()+$(window).height()/2)-($(href).height()/2));
		$('#mask').fadeTo(600,0.4,function(){
			$(href).fadeIn({queue: false, duration:600});
			$(href).animate({marginTop:30},600);
			$(href).animate({marginTop:0},400);
		});
		return false;
	});

	$('#pageWrap > div').hide().filter('.active').show();
	//$('#pageWrap > div').eq('0').siblings('div').hide();
	$('#pageUp > div').hide();
	/* 검색결과 스크롤 가변 */
	scroll();
	$(window).resize(scroll);
	
	$('body').on('click', '.ServiceGuideWrap .close a', function(){
		$(this).parents('.ServiceGuideWrap').fadeOut();
	});
	
	//이용안내 페이지 추가
	$("#serviceGuide").on("click", ".twoDepthMenu li a", function(){
		
		var index = $(this).parent().index();
		
		$("#serviceGuide .twoDepthMenu li").each(function(){
			if($(this).find("strong").length > 0){
				$(this).html('<a href="#none">' + $(this).find("strong").html() + '</a>');
			}
		});
		
		$(this).parent("li").html('<strong>' + $(this).html() + '</strong>');
		
		var $ul = $("#serviceGuide div.noticeView")
		
		$ul.hide().eq(index).show();
	});
});

function scroll(){
	$('.scroll').each(function(){
		var wrapHeight = $('#wrap').height();
		var prevHeight = $(this).prev().outerHeight(true);
		var nextHeight = $(this).next().outerHeight(true);
		$(this).outerHeight(wrapHeight - prevHeight - nextHeight);
	});
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