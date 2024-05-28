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

<body id="popup" class="imagePop" style="min-width:300px;max-width:800px;">
	<!-- #popup -->
	<div style="padding:0px;">
		<img src="<%=request.getContextPath() %>${imgUrl }" id="pop${boardSeq }" style="max-width:100%;height:auto;"/>
		
		<div class="bottom">&nbsp;&nbsp;
			<span class="checkbox"><input type="checkbox" id="popupChk" onclick="closeWin();" /></span>
			<label class="labelTxt">오늘 하루 이 창을 열지 않음</label>
			<button onclick="javascript:window.close();" class="searchDivBtn">닫기</button>
		</div>
		<!-- /.bottom -->
	</div>
	<!-- /#popup -->

<script type="text/javascript">
	
	$(document).ready(function(){
		
		//$("#popup .bottom").css("width", $(window).width());
	});
	
	$("img[id='pop${boardSeq }']").on("load", function(){
		
		var width = this.naturalWidth;
		//var height = this.naturalHeight + 130;	//오늘하루 안보기 height
		var height = this.naturalHeight + 100;	//오늘하루 안보기 height
		
		if(width > $(this).parent().width()){
			width = $(this).parent().width();
			height = $(this).parent().height() + 68;
		}
		//alert("width1["+width+"] width2["+$(this).width()+"] height1["+height+"] height2["+$(this).height()+"]"+$(this).parent().width()+"/"+$(this).parent().height());
		
		$("#popup .bottom").css("width", $(this).width());
		resizeTo(width, height);
	}).each(function(){
		if(this.complete){
			$(this).load();
		}
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