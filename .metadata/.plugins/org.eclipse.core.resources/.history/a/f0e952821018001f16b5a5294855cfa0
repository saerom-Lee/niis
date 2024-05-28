/********************************************************
파일명 : common.js
설 명 : 공통 스크립트
수정일 	수정자 	Version 	Function 명

------- -------- ---------- --------------
2016.09.08 SHW  1.0 최초 생성
*********************************************************/

//로딩바 이미지 세로 길이
var loadingBarH = 134;
var loadingBarYn = 0;

//페이징 관련 변수 선언
var _$pageDivID = "";
var _$pageFunc = "";
var _$pageUri = "";
var _$pageParam = "";
var _$pageSize = 10;

$(document).ready(function($){
	//console.log("common.js onload");
    // 페이지가 로딩될 때 'Loading 이미지'를 숨긴다.
    $('#viewLoading').hide();
    $('#viewLoading').css('position', 'absolute');
	$("#viewLoading").css('left', 0);
	$("#viewLoading").css('top', 0);
 
	$.ajaxSetup({
	    beforeSend: function(jqxhr){
	    	//console.log("beforeSend");
	    	jqxhr.setRequestHeader("AJAX", true);
	    	//console.log("############################### ajaxSetup jqxhr ###############################");
	    	//console.log(jqxhr);
	    }
	});
	
    $(document)
    .ajaxSend(function(e, jqxhr, settings){
    	//console.log("ajaxSend");
    	
    	var url = settings.url;
    	
    	if(url != null && url != "" && url != "undefined"){
	    	//if(false){
	    	if(url.indexOf("/search/") == -1 && url.indexOf("/rest/") == -1 && url.indexOf("/main/") == -1){
	    		//console.log("LoadOnUrl["+settings.url+"]");
	        	showProgress();
	        }
	    	
	    	
	    	//selectbox 시작 / 종료년도 값 처리
	    	var sendData;
	    	
	    	//multiparts가 아닐경우
	    	if(settings.processData && settings.contentType){
	    		sendData = settings.data;
	    	}
    	}
    	
    	//페이징 관련 처리
        if(_$pageDivID != "" && $("#"+_$pageDivID).length > 0 && url.indexOf(_$pageUri) != -1){
        	
        	var distc;
        	var uri;
        	
        	if(url.indexOf("?") == -1){
        		distc = "?";
        		uri = url;
        	}else{
        		distc = "&";
        		uri = url.substr(0, url.indexOf("?"));
        	}
        	
        	var _$currPage = $("#"+_$pageDivID+" #_currPage").length > 0 ? $("#"+_$pageDivID+" #_currPage").val() : "";
        	var _$pageUnit = $("#"+_$pageDivID+" #_pageUnit").length > 0 ? $("#"+_$pageDivID+" #_pageUnit").val() : "";
        	
        	var sessionData = "_$currPage";
			sessionStorage.setItem("_$currPage", _$currPage ); // 저장
			
        	
        	settings.url = url + distc +  "_$pageUri="		+ uri 
        							   + "&_$pageDivID="	+ _$pageDivID 
        							   + "&_$pageFunc="		+ _$pageFunc
        							   + "&_$pageParam="	+ _$pageParam
        							   + "&_$currPage="		+ _$currPage
        							   + "&_$pageUnit="		+ _$pageUnit
        							   + "&_$pageSize="		+ _$pageSize;
        }
    	
    })
    .ajaxSuccess(function(e, jqxhr, settings, data){
    	//console.log("ajaxSuccess");
    	hideProgress();
    	
    	if(null != data && null != data.RETURN_CD){
	    	if(data.RETURN_CD == 501){
	    		alert("시스템에 해당 요청이 존재하지 않습니다.");
	    		return;
	    	}else if(data.RETURN_CD == 521){
	    		//alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
	    		return;
	    	}else if(data.RETURN_CD == 406){
	    		alert("허용되지 않은 접근입니다.");
	    		return;
	    	}else if(data.RETURN_CD == 503){
	    		//alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
	    		return;
	    	}else if(data.RETURN_CD == 500){
	    		//alert("시스템에 연결할 수 없습니다. 잠시후 다시 시도해 주세요. \n지속적으로 문제 발생시 관리자에게 문의해 주세요.");
	    		return;
	    	}
    	}
		
		//페이징 관련 처리
        if(_$pageDivID != "" && $("#"+_$pageDivID).length > 0 && settings.url.indexOf(_$pageUri) != -1){
        	
        	$("#" + _$pageDivID + " div.paging ul").html(data._pageData_);
        	
        	initPagingInfo();
        }
	
    	var url = settings.url;
    	
    	if(url != null && url != "" && url != "undefined"){
	    	//메인 신청서 정보 및 공지사항이 아닐경우 세션시간 갱신
    		if(url.indexOf("/main/getMain") == -1 && url.indexOf("/login/") == -1){
	        	extendSession();
	        }
    	}
    })
    .ajaxError(function(e, jqxhr, settings, exception){
    	console.log("ajaxError");console.log(e);console.log(jqxhr);console.log(settings);console.log(exception);
    	hideProgress();
    	
    	if(jqxhr.status == 401){
    		alert("인증에 실패 했습니다.\n로그인 페이지로 이동합니다.");
    		logout();	
    	}else if(jqxhr.status == 403){
    		alert("세션이 만료가 되었습니다.\n로그인 페이지로 이동합니다.");
    		logout();
    	}else{
    		//alert("통신중 에러가 발생하였습니다. 잠시후 다시 시도해 주세요.<br/>지속적으로 문제 발생시 관리자에게 문의해 주세요.");
    		console.log("통신에러 stats[" + jqxhr.status + "], message[" + jqxhr.statusText + "]");
    	}
    	
    	//페이징 관련 처리
        if(_$pageDivID != "" && $("#"+_$pageDivID).length > 0 && settings.url.indexOf(_$pageUri) != -1){
        	initPagingInfo();
        }
    })
    .ajaxStop(function(){
    	//console.log("ajaxStop");
        hideProgress();
        
        if(typeof sessionTime != 'undefined' && sessionTime == 0){
        	sessionCheckStart();
        }
    });
});


/**
 * jqGrid
 * desc   : form의 데이터를 json 형태로 변환해 준다.
 * return : 성공시에는 객체(JSON)을 리턴한다. 실패시에는 null을 리턴한다.
 */
jQuery.fn.serializeObject = function() {
	var obj = null;
	try {
		if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM"){
			var arr = this.serializeArray();
			
			if(arr){
				obj = {};
				jQuery.each(arr, function(){
					obj[this.name] = this.value;
				});
			}
 		}
	}catch(e){
		alert(e.message);
	}finally{}
	
	return obj;
};

/**
 * 셀렉트 박스 값변경 처리 함수
 * value 값 미지정시 첫번쨰 옵션 선택
 */
jQuery.fn.initSelect = function(value) {
	var obj = null;
	try {
		if(this[0].tagName && this[0].tagName.toUpperCase() == "SELECT"){
			var obj = this;
			if(value == null){
				obj.children("option:eq(0)").prop("selected", true);
			}else{
				obj.val(value);
			}
			
			obj.siblings('.selectTit').text(obj.children("option:selected").text());
 		}
	}catch(e){
		console.log(e.message);
	}finally{}
	
	return obj;
};

/**
 * 체크박스 디자인 싱크 처리
 */
function initCheckbox(){
	
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
}


/**
 * loading bar 사용자 컨트롤 
 * showProgress()	: loading bar를 보여준다
 * hideProgress()	: loading bar를 숨긴다
 */
function showProgress(){
	//console.log("showProgress");
	// 로딩이미지의 위치 및 크기조절 
    $('#viewLoading').css('width', $(document).width());
    $('#viewLoading').css('height', $(document).height());
    $('#viewLoading img').css('margin', ($(window).height()/2 - (loadingBarH/2 + 30)) + "px 0 0 0");
	$("#viewLoading").css('z-index', 99999);
    
	$("#viewLoading").show();
	//$("#viewLoading").fadeIn();
}
function hideProgress(){
	//console.log("hideProgress");
	$("#viewLoading").hide();
}


/**
 * ajax통신
 * jsp페이지 rendering
 * param	url			: url
 * 			data		: 보낼 파라미터
 * 			id			: jsp를 rendering할 elemenet id
 * 			callback	: ajax 통신성공시 호출할 callback함수
 * 			errCallback : ajax 통신실패시 호출할 callback함수
 */
function ajaxCall(url, data, id, callback, errCallback){
	
	if(url == null || url == "" || url == undefined){
		return;
	}
	
	if(id == null || id == "" || id == undefined){
		id = "mainDiv";
	}else{
		//if($("#container #" + id).length == 0){
		if($("#" + id).length == 0){
			$("#pageWrap").append('<div id="' + id + '"></div>')
		}
	}
	jQuery.ajax({
		type	: "POST",
		async	: true,
		url		: "/niis" + url,
		data	: data,
		success : function(result){
			hideProgress();
			
			$("#" + id).html(result);
			
			if(typeof callback == "function"){
				callback(result, data);
			}
		},
		error : function(result){
			hideProgress();
			if(typeof errCallback == "function"){
				errCallback(result, data);
			}
		}
	});
}

/**
 * ajax통신
 * json형태의 통신만 취급
 * param	url			: url
 * 			data		: 보낼 파라미터
 * 			callback	: ajax 통신성공시 호출할 callback함수
 * 			errCallback : ajax 통신실패시 호출할 callback함수
 */
function ajaxCallJson(url, data, callback, errCallback){
	
	if(url == null || url == "" || url == "undefined"){
		return;
	}
	
	jQuery.ajax({
		type		: "POST",
		async		: true,
		url			: "/niis" + url,
		dataType	: "json",
		data		: data,
		success : function(result){
			hideProgress();
			if(typeof callback == "function"){
				callback(result, data);
			}
		},
		error : function(result){
			hideProgress();
			if(typeof errCallback == "function"){
				errCallback(result, data);
			}
		}
	});
}

/**
 * ajax통신
 * param	url			: url
 * 			formId		: 보낼 파라미터
 * 			callback	: ajax 통신성공시 호출할 callback함수
 * 			errCallback : ajax 통신실패시 호출할 callback함수
 */
function ajaxCallFile(url, formId, callback, errCallback){
	
	var data = new FormData($("#" + formId)[0]);
	
	jQuery.ajax({
		type		: "POST",
		async		: true,
		url			: "/niis" + url,
		dataType	: "json",
		data		: data,
		processData	: false,
		contentType	: false,
		success : function(result){
			hideProgress();
			if(typeof callback == "function"){
				callback(result, data);
			}
		},
		error : function(result){
			hideProgress();
			if(typeof errCallback == "function"){
				errCallback(result, data);
			}
		}
	});
}

/**
 * ajax통신
 * popup jsp페이지 rendering
 * param	url			: url
 * 			data		: 보낼 파라미터
 * 			id			: jsp를 rendering할 elemenet id
 * 			width		: 팝업창 width
 * 			callback	: ajax 통신성공시 호출할 callback함수
 * 			errCallback : ajax 통신실패시 호출할 callback함수
 */
function ajaxCallPop(url, data, id, width, callback, errCallback){
	
	if(id == null || id == "" || id == undefined){
		id = "commonPop";
	}else{
		if($("#" + id).length == 0){
			$("body").append('<div id="' + id + '" class="layer" style="display:none"></div>');
		}
	}
	
	if(id == "commonPop" && (width == null || width == "" || width == undefined)){
		width = 800;
	}
	
	if(width != null && width != "" && width != undefined){
		$("#" + id).css("width", width + "px");
	}
	
	ajaxCall(url, data, id, function(result, data){
		
		openLayerPop(id);
		
		if(typeof callback == "function"){
			callback(result, data);
		}
	}, 
	function(result, data){
		if(typeof errCallback == "function"){
			errCallback(result, data);
		}
	});
}

/**
 * selectBox 렌더링
 * ajax로 받아온 데이터를 selectBox에 렌더링한다
 * param	result		: ajax return value
 * 			selId		: rendering할 selectbox의 id값
 * 			kind		: selectbox default값 선택 인자 - 0[없음], 1[전체], 2[선택]
 * 			cdeId		: 쿼리에서 가져온 code alias - 미지정시[cdeCde] 
 * 			cdeNm		: 쿼리에서 가져온 name alias - 미지정시[cdeNam]
 * 			parentId	:  
 */
function getSelectBox(result, selId, kind, cdeId, cdeNm, parentId){
	
	if(result == null || result == "undefined") return;
	if(selId == null || selId == "") return;
	
	if(kind == null || kind == "") kind = "0";
	
	if(cdeId == null || cdeId == "") cdeId = "cdeCde";
	if(cdeNm == null || cdeNm == "") cdeNm = "cdeNam";
	
	var obj;
	if(parentId != null && parentId != ""){
		obj = $("#" + parentId +" #" + selId);
	}else{
		obj = $("#" + selId);
	}
	
	if (result.list != null){
		obj.empty();
		
		if(kind == "1"){
			obj.append('<option value="">전체</option>');
		}else if(kind == "2"){
			obj.append('<option value="">선택</option>');
		}else if(kind == "3"){
			obj.append('<option value="00">선택</option>');
		}

		for (var i = 0; i < result.list.length; i++){
			var name = result.list[i][cdeNm];
			obj.append('<option value="' + result.list[i][cdeId] + '" title="' + result.list[i][cdeNm] + '">' + (result.list[i][cdeNm] ? result.list[i][cdeNm] : "없음") + '</option>');
		}
		//obj.siblings('.selectTit').text(obj.children("option:selected").text());
		
		if(kind == "0" && result.list.length == 0){
			obj.append('<option value="">선택</option>');
		}
		
		obj.initSelect();
	}
}

/**
 * 공통 팝업 오픈 함수
 */
function openLayerPop(id){
	
	if(id == null || id == "" || id == undefined){
		id = "commonPop";
	}
	
	var div = $("#" + id);
	
	if(div.css("display") == "none"){
		
		var top = ($('html,body').scrollTop()+$(window).height()/2)-($(div).height()/2);
		//var top = ($('html,body').scrollTop()+$('html').height()/2)-($(div).height()/2);
		
		if(top < 0){
			top = 0;
		}
		
		$(div).css('top',top);
		$('#mask').fadeTo(60,0.4,function(){
			$(div).fadeIn({queue: false, duration:60});
			$(div).animate({marginTop:30},600);
			$(div).animate({marginTop:0},400);
		});
	}
	return false;
}

/**
 * 공통 팝업 클로즈 함수
 */
function closeLayerPop(id){
	
	if(id == null || id == "" || id == undefined){
		id = "commonPop";
	}
	
	var div = $("#" + id);
	var btnLayer = div.children(".btnLayerClose");
	
	if(div.css("display") != "none"){
		if (btnLayer.hasClass('btnLayerOpen')){
			div.fadeOut({queue: false, duration:80});
			div.animate({marginTop:-40},600);
		} else {
			div.fadeOut({queue: false, duration:80});
			div.animate({marginTop:-40},600);
			$('#mask').delay(200).fadeOut(600);
		}
	}
	return false;
}
/*
 * 가이드 팝업 
 * */
function openGuidePopup(guideId){
	var data = { 
			"guideId" : guideId
	}
	ajaxCall("/guide/guideOpen.do", data, "serviceGuidePop", function(result){
		$("#serviceGuidePop").fadeIn();
	});
}

function setPagingInfo(divId, func, uri, pageSize){
	
	if($("#"+divId).length == 0){
		console.log("존재하지 않는 Object Id!!");
		_$pageDivID = "";
	}else{
		_$pageDivID = divId;
	}
	
	if(func == null || func == "" || func == "undefined"){
		_$pageFunc = "";
	}else{
		_$pageFunc = func;
	}
	
	if(uri == null || uri == "" || uri == "undefined"){
		_$pageUri = "";
	}else{
		_$pageUri = uri;
	}
	
	if(pageSize == null || pageSize == "" || pageSize == "undefined"){
		_$pageSize = 10;
	}else{
		_$pageSize = pageSize;
	}
}

function initPagingInfo(){
	_$pageDivID = "";
	_$pageFunc  = "";
	_$pageUri   = "";
	_$pageSize  = 10;
}

/* 세션제거 테스트용 함수 */
function sessionInvalidate(){
	ajaxCallJson("/login/logoutSession.do", "", function(result, data){
		alert("세션 제거 성공", "");
	});
}




/********************************************************
	페이징 처리 산술식 함수
	fn_page_btn("페이징 버튼 id div", pageIndex, 총데이터수)
	
	*********************************************************/
	function fn_page_btn(pageDiv, pageUnit, pageIndex, totalCnt){
	
		// 나오는 부분 지움
		var pageSize = pageUnit;
		var pageListSize = 5; // page bar count 1 2 3 4 5 6 7 8
		var pageNum = pageIndex;
		var prevNum = pageNum-1;
		var nextNum = pageNum+1;
		var totalCount = totalCnt;

		var totalPage = Math.ceil(totalCount / pageSize); // 총 페이지 수 
		var totalPageList = Math.ceil(totalPage / pageListSize);  // 전체페이지/10
		
		// (페이지의 페이지수)
		var pageList = Math.ceil(pageNum / pageListSize);

		if (pageList < 1) pageList = 1;
		if (pageList > totalPageList) pageList = totalPageList;

		var startPageList = (pageList - 1) * pageListSize + 1;
		var endPageList = startPageList + pageListSize - 1;

		if (startPageList < 1) startPageList = 1;
		if (endPageList > totalPage) endPageList = totalPage;
		if (endPageList < 1) endPageList = 1;

		var html = "";

		/********************************************************
			페이징 이동 함수(페이징 처리 event를 사용하는 jsp에서 호출)
			fn_"페이징 버튼 id div"(pageIndex) 를 생성
		*********************************************************/
		if (pageNum >= 1) {
			if (pageNum == 1) {
				html += "";
			}
			else {
				html += "<li class='btnPaging btnPagingFirst'><a href='#none' onclick='fn_"+ pageDiv +"(" + 1 + ");'>처음</a></li>";
				html += "<li class='btnPaging btnPagingPrev'><a href='#none'  onclick='fn_"+ pageDiv +"(" + prevNum + ");'>이전</a></li>";
			}
			for (var i = startPageList; i <= endPageList; i++) {
				if (i == pageNum) {
					html += "<li><a href='javascript:void(0);' class='btnPaging btnPaging active'>" + i + "</a></li>";
				}
				else {
					html += "&#160;&#160;<strong><a href='#none' class='btnPaging btnPaging' onclick='fn_"+ pageDiv +"(" + i + ");' return false;'>" + i + "</a></strong>&#160;&#160;";
				}
			}
			if (pageNum == totalPage) {
				html += "";
			}
			else {
				html += "<li class='btnPaging btnPagingNext'><a href='#none' onclick='fn_"+ pageDiv +"(" + nextNum + ");'>다음</a></li>";
				html += "<li class='btnPaging btnPagingLast'><a href='#none' onclick='fn_"+ pageDiv +"(" + totalPage + ");'>마지막</a></li>";
			}
		}
		
	
		$("#"+pageDiv).append(html);
	
	}

	
	
	
	