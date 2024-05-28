$(document).ready(function(){
	
	//로드시 통합검색 사이드 메뉴 숨김처리
	$("#main .twoDepthMenu").css("display", "none");
	$("#main .container").css("padding-left", "280px");
	$("#main .btnTwoDepthMenuClose").addClass('active');
	
	//메인로고 클릭시 초기화면으로....
	/*
	$("#btnMain").click(function(){
		if($("#main .twoDepthMenu").css("display") != "none"){
			$("#main .twoDepthMenu").css("display", "none");
			$("#main .container").css("padding-left", "280px");
			$("#main .btnTwoDepthMenuClose").addClass('active');
			
			_Map2D.updateSize();
		}
	});
	*/
	
	//로드시 통합검색 사이드 메뉴 액션
	$("#main #mapAreaBtn.btnTwoDepthMenuClose").click(function(){
		
		if($("#main .twoDepthMenu").css("display") == "none"){
			$("#main .twoDepthMenu").css("display", "");
			$("#main .container").css("padding-left", "658px");
			$(this).removeClass('active');
		}else{
			$("#main .twoDepthMenu").css("display", "none");
			$("#main .container").css("padding-left", "280px");
			$(this).addClass('active');
		}
		
	});
	
	//통합 검색 메뉴 버튼 액션
	/*
	$("#btnCombineSearch").click(function(){
		if($("#main .twoDepthMenu").css("display") == "none"){
			$("#main .twoDepthMenu").css("display", "");
			$("#main .container").css("padding-left", "658px");
			$("#main .btnTwoDepthMenuClose").removeClass('active');
			
		}
		_Map2D.updateSize();
	});
	*/
	
	$(".gnb > li").click(function(){
		if(!$(this).hasClass("active")){
			$(".gnb > li").each(function(){
				$(this).removeClass("active");
			});
			$(this).addClass("active");
		}
	});
	
	/**
	 * 메뉴 이동시 목록서비스 검색내역 존재여부 확인
	 */
	$("#header").on('click', '.pageMove', function(){
		
		var $this = $(this);
		var url = $this.attr("menuId");
		
		var selLen = $("#mainDiv form div[id$=CheckList] ul li div").length;
		
		if($("#mainDiv").css("display") != "none" && selLen > 0){
			ajaxCallPop("/main/moveMenuConfirm.do", "", "", 460, function(){
				$("#btnMoveMenu").click(function(){
					getMainApply();
					getMainNotice();
					ajaxCall(url);
					closeLayerPop("commonPop");
					
					pageMove($this);
				});
			});
		}else{
			getMainApply();
			getMainNotice();
			ajaxCall(url);
			pageMove($this);
		}
	});
	
	getAreaSidoList("areaSido,zipSido,jusoSido");
	
	
	selectIndexScale();
	
	$('body').on('click', '.btnServiceGuide', function(){
		
		//통합검색
		if($(this).parent().hasClass("btnServiceGuideWrap")){
			$("#main .btnCloseOpen").each(function(){
				if($(this).hasClass('active')){
					$(this).removeClass('active');
					$(this).parent().next('.parentWrap').fadeIn(600);
					scroll();
				}
			});
		}
		//목록서비스
		else if($(this).parents("#mainDiv").length > 0){
			if($(this).siblings(".btnCloseOpen").hasClass('active')){
				$(this).removeClass('active');
				$(this).parent().next('.parentWrap').fadeIn(600);
				scroll();
			}
		}
		
		var guideId = $(this).prop("id");
		
		openGuidePopup(guideId);
	});
});


function pageMove($this){
	
	var href = $this.attr('href');
	
	$('#pageUp > div').hide();
	$('.pageMove').removeClass('active');
	$this.addClass('active');
	$('#pageWrap > div').hide().removeClass('active');
	$(href).show().addClass('active');
	if ($this.parents('.iden').hasClass('iden')){
		$('.gnb > li').removeClass('active');
	}
	
	if(href == "#main"){
		
		$(href).find(".scroll").mCustomScrollbar("destroy");
		
		if($("#main .twoDepthMenu").css("display") == "none"){
			$("#main .twoDepthMenu").css("display", "");
			$("#main .container").css("padding-left", "658px");
			$("#main .btnTwoDepthMenuClose").removeClass('active');
			
			//_Map2D.updateSize();
		}
		$(href).find(".scroll").mCustomScrollbar();
	}
	scroll();
	return false;
}


/**
 * 지도 중심
 */
function eventListenerCallback(){
	var center = rinoGIS.ol.map.getView().getCenter();
	$.ajax({
		type: "GET",
		url : "/niis/rest/getCurPosition.do",
		data: "x=" + center[0] + "&y=" + center[1],
		async: true,
		dataType:"xml",
		success: function(result){
			$(result).find("position").each(function(index){
				var str = $(this).text();
				if(str.indexOf("null") == -1){
					$("#curPosition > h3").html('<img src="/niis/images/sub/ic_map_tit.png" width="14" height="20" alt="현재위치" title="현재위치" />' + str);
				}
			});
			//추후 추가
//			dawIndexMap();
		},
		error: function() {
			window.console.log('화면위치 정보 검색 실패!');
		}	
	});
}


/**
 * 메인화면 대기중 신청서 정보 조회
 */
function waitApply(){
	moveMenu("/apply/waitApply.do");
}


/**
 * 메인화면 승인된 신청서 정보 조회
 */
function appApply(){
	moveMenu("/apply/appApply.do");
}


/**
 * 메인화면 반려된 신청서 정보 조회
 */
function rejApply(){
	moveMenu("/apply/rejApply.do");
}


/**
 * 메인화면 완료된 신청서 정보 조회
 */
function compApply(){
	moveMenu("/apply/compApply.do");
}


function logout(){
	location.href="/niis/login/logout.do";	
}

function myInfoDetail(){
	ajaxCallPop("/userinfo/myInfoPop.do", "", "", "460");
}