<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공간정보 행정망 서비스</title>
<link rel="stylesheet" type="text/css" href="/niis/css/style.css" />
<link rel="stylesheet" type="text/css" href="/niis/css/external_popup.css" />
<script type="text/javascript" src="/niis/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/niis/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/niis/js/jquery.mCustomScrollbar.min.js"></script>
<script type="text/javascript" src="/niis/js/default.js"></script>
</head>

<body id="popup" class="general_type2"><!-- 팝업글 일반형2 -->
	<!-- #popup -->
	<div>
		<!-- .contents -->
		<div class="contents">
			<p class="system"><span>국토영상정보</span><span>공급시스템</span></p>
			<p class="tit">${title }</p>
			<p>${contents }</p>
			
			<!-- .bottom -->
			<div class="bottom">
				<span class="checkbox"><input type="checkbox" id="popupChk" onclick="closeWin();" /></span>
				<label class="labelTxt">오늘 하루 이 창을 열지 않음</label>
				<button onclick="javascript:window.close();" class="searchDivBtn">닫기</button>
			</div>
			<!-- /.bottom -->
		</div>
		<!-- /.contents -->
	</div>
	<!-- /#popup -->
	
<script type="text/javascript">
	
	$(document).ready(function(){
		
		var width = $("body > div").outerWidth() + 15;
		//var height = $("body > div").outerHeight() + 60;
		var height = $("body > div").outerHeight() + 38;
		resizeTo(width, height);
	});

	function setCookie(name, value, expiredays){
		
		var todayDate = new Date();
		todayDate.setDate( todayDate.getDate() + expiredays );
		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	}

	function closeWin(){
		
		if($("#popupChk").is(":checked")){
			setCookie("pop${boardSeq }", "no" , 1);
		}
		window.close();
	}

</script>
</body>
</html>