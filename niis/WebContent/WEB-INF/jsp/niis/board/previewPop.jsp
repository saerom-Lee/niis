<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/fancytree/skin-lion/ui.fancytree.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/map/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/internal_popup.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.mCustomScrollbar.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/default.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.fancytree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.tmpl.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<html>
	<head>
	<title>미리보기 입니다.</title>
		<base target=”_self” />
	</head>
	<body>
		<div class="tit">
		
			<a href="#none" class="btnLayerClose">
			   <img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" style="height:1px;"/> 
			</a>
		</div>
		<div class="con" id="conPreview" style="height:0px;">
			<img src="#" />
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$("title").empty().append(window.dialogArguments["title"]);            
			
			var bFile = window.dialogArguments["file"];
			if(bFile == null){
				$("#conPreview").empty().append("<div class=\"popInner\"></div>");
				
				var width = 0;
				var height = 0;
				
				var popGbnCde = window.dialogArguments["popGbnCde"];
			
				if(popGbnCde == 2){
					$("#conPreview .popInner").append($("#tmpNoticePopup2").tmpl(window.dialogArguments["argObj"]) );
					width = $("#conPreview > div > div").outerWidth();
					height = $("#conPreview > div > div").outerHeight();
					
				}else if(popGbnCde == 3){				
					$("#conPreview .popInner").append($("#tmpNoticePopup3").tmpl(window.dialogArguments["argObj"]) );
					width = $("#conPreview > div > div").outerWidth();
					height = $("#conPreview > div > div").outerHeight();
					
				}else if(popGbnCde == 4){	
					$("#conPreview .popInner").append($("#tmpNoticePopup4").tmpl(window.dialogArguments["argObj"]) );
					width = $("#conPreview > div > div > div").outerWidth();
					height = $("#conPreview > div > div > div").outerHeight();
					
				}else if(popGbnCde == 5){		
					$("#conPreview .popInner").append($("#tmpNoticePopup5").tmpl(window.dialogArguments["argObj"]) );
					width = $("#conPreview > div > div > div").outerWidth();
					height = $("#conPreview > div > div > div").outerHeight();
					
				}
				
				window.dialogWidth = width+'px';
				window.dialogHeight = height+'px';
			}else{
				file = bFile.prop("files")[0];
	            blobURL = window.URL.createObjectURL(file);
	            $('#conPreview img').attr('src', blobURL);	
			}

			$('#conPreview').slideDown(); //업로드한 이미지 미리보기 
		});
		
		</script>
	
		<script id="tmpNoticePopup2" type="text/x-jquery-tmpl">
		<div id="popup" class="general_type1">
			<div>
				<p class="system"><span>국토영상정보</span><span>공급시스템</span></p>
				<p class="popTit">{{html popTit}}</p>
				<div class="contents">
					<p>{{html popCont}}</p>
				</div>
				<div class="bottom">
					<span class="checkbox"><input type="checkbox" id="popupChk"/></span>
					<label class="labelTxt">오늘 하루 이 창을 열지 않음</label>
					<button class="searchDivBtn" disabled='disabled'>닫기</button>
				</div>
			</div>
		</div>
		</script>
		<script id="tmpNoticePopup3" type="text/x-jquery-tmpl">
		<div id="popup" class="general_type2">
			<div>					
				<div class="contents">
					<p class="system"><span>국토영상정보</span><span>공급시스템</span></p>
						<p class="popTit">{{html popTit}}</p>
						<p>{{html popCont}}</p>			
						<div class="bottom">
							<span class="checkbox"><input type="checkbox" id="popupChk"/></span>
							<label class="labelTxt">오늘 하루 이 창을 열지 않음</label>
							<button class="searchDivBtn" disabled='disabled'>닫기</button>
						</div>		
					</div>
				</div>
			</div>
		</script>
		<script id="tmpNoticePopup4" type="text/x-jquery-tmpl">
		<div id="popup" class="announcement_type">
			<div>
				<p class="system"><span>국토영상정보</span><span>공급시스템</span></p>
				<p class="popTit">{{html popTit}}</p>
				<div class="contents">
					<p>{{html popCont}}</p>
				</div>
				<div class="bottom">
					<span class="checkbox"><input type="checkbox" id="popupChk"/></span>
					<label class="labelTxt">오늘 하루 이 창을 열지 않음</label>
					<button class="searchDivBtn" disabled='disabled'>닫기</button>
				</div>
			</div>
		</div>
		</script>
		<script id="tmpNoticePopup5" type="text/x-jquery-tmpl">
		<div id="popup" class="emergency_type"><!-- 팝업글 긴급형 -->
			<div>
				<div class="popTit">
					<p class="system"><span>국토영상정보</span><span>공급시스템</span></p>
					<span>{{html popTit}}</span>
				</div>
				<div class="contents">
					<p>{{html popCont}}</p>
				</div>
				<div class="bottom">
					<span class="checkbox"><input type="checkbox" id="popupChk" /></span>
					<label class="labelTxt">오늘 하루 이 창을 열지 않음</label>
					<button class="searchDivBtn" disabled='disabled'>닫기</button>
				</div>
			</div>
		</div>
	</script>
	</body>
</html>