/**
 * 
 */

/******************************** 로그인 시간 만료 팝업 ********************************/
var sessionTime = 0;
var sessMin;
var sessSec;
var sessEnd;

var applyTime  = 0;
var noticeTime = 0;

$(document).ready(function(){
	
	//메인화면 신청서 정보 조회
	getMainApply();
	//메인화면 곶지사항 정보 조회
	getMainNotice();
	
	sessionCheckStart();
});


/**
 * 메인화면 신청서 정보 조회
 */
function getMainApply(){
	getMainApplyAjax();
	
	clearInterval(applyTime);
	applyTime = setInterval(getMainApplyAjax, 1000*60*7);
}
function getMainApplyAjax(){
	ajaxCall("/main/getMainApply.do", "", "mainApply");
}

/**
 * 메인화면 공지사항 정보 조회
 */
function getMainNotice(){
	getMainNoticeAjax();
	
	clearInterval(noticeTime);
	noticeTime = setInterval(getMainNoticeAjax, 1000*60*10);
}
function getMainNoticeAjax(){
	ajaxCall("/main/getMainNotice.do", "", "mainNotice");
}


function sessionCheckStart(){
	
	sessMin = 20;
	sessSec = 1;
	sessEnd = 0;
	
	sessionTimer();
	sessionTime = setInterval(sessionTimer, 1000);
}

function sessionTimer(){
	
	if(sessMin > 20){
		sessMin = 20;
	}
	if(sessSec > 59){
		sessSec = 59;
	}
	
	if(sessSec <= 0){
		sessSec = 59;
		sessMin -= 1;
	}else{
		sessSec -= 1;
	}
	
	$("#sessMin").html(sessMin);
	if(sessSec < 10){
		if(sessMin >= 0){
			$("#sessSec").html(0 + sessSec.toString());
		}
	}else{
		$("#sessSec").html(sessSec);
	}
	
	if(sessMin <= 0 && sessSec <= 15 && sessEnd == 0){
		$("#extendLoginPop #remainSec").html(sessSec);
		openLayerPop("extendLoginPop");
	}else{
		closeLayerPop("extendLoginPop");
	}
	
	if(sessMin <= 0 && sessSec <= 0 && sessEnd == 0){
		sessEnd = 1;
		sessionCheckStop();
		logout();
	}
}

function sessionCheckStop(){
	
	clearInterval(sessionTime);
	closeLayerPop("extendLoginPop");
	sessionTime = 0;
}

function extendSession(){
	
	sessionCheckStop();
	sessionCheckStart();
}
/******************************** 로그인 시간 만료 팝업 ********************************/

/******************************** 로그아웃 확인 팝업 ********************************/
var logoutTime;
var logoutSec;

function logoutCheckStart(){
	
	logoutSec = 7;
	logoutTimer();
	logoutTime = setInterval(logoutTimer, 1000);
}

function logoutTimer(){
	
	if(logoutSec > 0){
		logoutSec -= 1;
	}
	
	$("#logoutCheckPop #remainSec").html(logoutSec);
	
	if(logoutSec <= 0){
		logoutCheckEnd();
		logout();
	}
}

function logoutCheckEnd(){
	clearInterval(logoutTime);
}
/******************************** 로그아웃 확인 팝업 ********************************/