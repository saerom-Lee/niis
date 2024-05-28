<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/niis/css/internal_popup.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var selectedBoard = "";
	
	var boardParam = "";
	var vBoardSeq = "";
	
	$(document).ready(function(){
		$("#form1 .scroll").each(function(){
			$(this).mCustomScrollbar("destroy");
			$(this).mCustomScrollbar();
		});
	 	getBoardList();
	 	
	 	 <c:if test="${boardLink ne null && boardLink ne '' }">
	 		getBoardDetail('<c:out value="${master.boardCate }" />', '${boardLink }');
		</c:if>
		
	});
	
	function getBoardList(){
		
		var data = {
				"srchType"		: "0",		//제목
				"srchCondition"	: "",
				"boardCate"		: $("#boardCate").val()
		}
		
		boardParam = data;
		getBoardListPaging();
	}
	
	function getBoardListPaging(flag){
		
		var data = boardParam;
		
		setPagingInfo("notice", "getBoardListPaging", "/board/getBoardList.do", 5);
		ajaxCallJson("/board/getBoardList.do", data, function(result, data){
			$("#boardList").empty();
			
			if (result.list != null){		
				if(result.list.length > 0){
					$("#boardList").append($("#tmplBoardList").tmpl(result.list));
					$("#listCnt").text(result.list.length);
					
					$("#form1 #boardList li").each(function(){
						if($(this).find("strong").length > 0){
							$(this).html($(this).find("strong").html());
						}
					});
					
					var objli = $("#form1 #boardList li[id=" + selectedBoard + "]");
						
					if(objli.find("strong").length == 0){
						var child = objli.html();
						
						objli.html('<strong style="padding:0px">' + child + '</strong>');
					}
					
				}else{
					$("#boardList").append("<tr><td colspan=\"" + $("#boardHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
			}
			
			<c:if test="${boardLink eq null}">
				if (flag == null){
					$("#form1 .boardList #boardList li").first().find("a").click();
				}else{
					$("#form1 .boardList #boardList li strong").find("a").click();
				}
			</c:if>
		});
	}
	function replaceAll(str, target, replacement) {
	    return str.split(target).join(replacement);
	};
	//noticeView에 클릭한 화면을 보여줌 
	function getBoardDetail(boardCate, boardSeq){
		
		$("#noticeRegist").hide();	
		$("#noticeView").show();
		
		selectedBoard = boardSeq;
		vBoardSeq = boardSeq
		
		var data = {
				"boardCate"	: boardCate,
				"boardSeq"	: boardSeq
		}
		
		ajaxCallJson("/board/getBoardDetail.do", data,function(result, data){
			var popGbnCde2 =$("#popup.general_type1 .tit");
			
			if (result.map != null){
				$("#popInner").addClass("popInner");
				
				$("#form1 #noticeView #tit").text(result.map.title);
				$("#form1 #noticeView #date").text(result.map.lastChangeDt);
				$("#form1 #noticeView #view").text(result.map.hit);
				
				if(result.map.contents != null ){
					var str = result.map.contents;
					var replacedStr = replaceAll(str,"\n",'<br>'); 
					result.map.contents = replacedStr;
				}
				
				//팝업이냐 일반글이냐에 따라 분기 
				//alert(result.map.popGbnCde+"//"+result.map.popGbnCde)
				if( result.map.popGbnCde == null ){
					
					$("#form1 #noticeView #content #popInner").empty().append("<div style=\"padding:20px 30px; font-size:15px; line-height:1.5;\">"+replacedStr+"</div>");
					
				}else if(result.map.popGbnCde == "1"){	//팝업 이미지 
					$("#form1 #noticeView #content #popInner").empty().append("<img src=\"<%=request.getContextPath() %>"+result.map.imgUrl+"\"/><br>");
					
				}else if(result.map.popGbnCde == "2"){	//팝업글>일반형 1
					$("#form1 #noticeView #content #popInner").empty().append($("#tmpNoticePopup2").tmpl(result.map));
				}else if(result.map.popGbnCde == "3"){	//팝업글>일반형 2
					$("#form1 #noticeView #content #popInner").empty().append($("#tmpNoticePopup3").tmpl(result.map));
				}else if(result.map.popGbnCde == "4"){	//팝업글>안내형
					$("#form1 #noticeView #content #popInner").empty().append($("#tmpNoticePopup4").tmpl(result.map));
				}else if(result.map.popGbnCde == "5"){	//팝업글>긴급형
					$("#form1 #noticeView #content #popInner").empty().append($("#tmpNoticePopup5").tmpl(result.map));
				}
				//수정을 위해 flag를 심어 놓는다. 
				$("#form1 #noticeView #content #popInner").append("<input type =\"hidden\" id=\"popGbnCde\" value=\"" + result.map.popGbnCde + "\"  />");
				$("#form1 #noticeView #content #popInner").append("<input type =\"hidden\" id=\"boardSeq\" value=\"" + result.map.boardSeq + "\"  />");
				$("#form1 #boardList li").each(function(){
					if($(this).find("strong").length > 0){
						$(this).html($(this).find("strong").html());
					}
				});
				
				var objli = $('#form1 #boardList li[id="' + boardSeq + '"]');
					
				if(objli.find("strong").length == 0){
					var child = objli.html();
					
					objli.html('<strong style="padding:0px">' + child + '</strong>');
				}
				
			}
			if(result.list.length > 0){
				$("#file").show();
				for( var i =0 ; i <result.list.length; i++){

					$("#file").empty().append("<dl><dt>첨부파일</dt><dd>"+result.list[i].fileName+"("+result.list[i].fileSize+" bytes)</dd></dl>"+
					"<div class=\"btnDown\"><a href=\"#none\" onclick=\"downloadAttachFile();\"><img src=\"/niis/images/board/btn_down.gif\"/></a></div>"+
					"<input type=\"hidden\" id=\"fileSeq\" name=\"fileSeq\" value=\""+result.list[i].fileSeq+"\">");
				}
			}else{
				
				$("#file").empty();
				$("#file").hide();
			}
			
			scroll();
		});
	}
	
	function register(boardCate ,target ,flag){
		
		if(flag == null){
			var data = {
					"boardCate"	: boardCate,
					"target"	: target
			}	
		}else{
			var data = {
					"boardCate"	: boardCate,
					"target"	: target,
					"update"	: "Y",
					"boardSeq"	: flag
			}
		}
		
		ajaxCall("/board/register.do", data,"noticeRegist",function(result){
			if(result.length > 0){
				$("#noticeRegist").show();	
				scroll();
			}
		});
	}
	
	function cancleBoard(){
		if(!confirm("입력을 취소하시겠습니까?")){
			return;
		}
		getBoardList();
		$("#noticeView").show();
		$("#noticeRegist").hide();	
	}
	
	function deleteBoard(){
		
		if(!confirm("게시물을 삭제하시겠습니까?")){
			return;
		}
		
		var boardCate = $("#boardCate").val();
		var boardSeq = $("#boardSeq").val();
		

		var data ={
				"boardCate"	: boardCate,
				"boardSeq"	: vBoardSeq
		}
		
		 ajaxCallJson("/board/deleteBoard.do", data, function(result, data){
			alert("삭제되었습니다.");
			//메인화면 공지사항 갱신
			getMainNotice();
			getBoardListPaging();
			getBoardDetail( $("#boardCate").val(),$("#boardSeq").val() );
		});  
	}
	
	function preUpdate(){
		var vPopGbnCde = $("#noticeView #content #popInner #popGbnCde").val();
		var boardCate = $("#boardCate").val();
		var boardSeq = $("#noticeView #content #popInner #boardSeq").val();
		//alert("popGbnCde["+vPopGbnCde+"] boardSeq["+boardSeq+"]");
		
		if(vPopGbnCde == 1){
			register(boardCate ,'board/registerPopImg',boardSeq);
		}else if(vPopGbnCde ==2 ||vPopGbnCde ==3 || vPopGbnCde ==4 ||vPopGbnCde ==5)  {
			register(boardCate ,'board/registerPop',boardSeq);	
		}else{
			register(boardCate ,'board/register',boardSeq);
		}
	}
	
	function downloadAttachFile(){
		var boardCate	= $("#form1 .top #boardCate").val();
		var boardSeq	= $("#noticeView #content #popInner #boardSeq").val(); 
		var fileSeq 	= $("#form1 #bottom #file #fileSeq").val();
		
		//다운로드는 get 방식
		location.href = "/niis/board/downloadAttachFile.do?boardCate=" + boardCate + "&boardSeq=" + boardSeq + "&fileSeq=" + fileSeq;
	}
	
</script>

<form id="form1">
<!-- .twoDepthMenu -->
<div class="twoDepthMenu">
	<div class="top">
		<input type="hidden" id="boardCate" name="boardCate" value="${master.boardCate }">
		
		<h3 class="tit"><a href="#none" class="btnCloseOpen"></a>${master.boardName}(총<span id="listCnt">0</span>건)</h3>
<c:if test="${aUserAuth == '00' }">
		<a href="#noticeRegist" class="btnRegist" onclick="register('${master.boardCate }','board/register');">
			<img src="/niis/images/board/btn_regist.gif" width="76" height="33" alt="등록" title="등록">
		</a>
</c:if>
		<a href="#none" class="btnPageUpClose">Close</a>
	</div>
	<div class="scroll boardList">
		<ul id="boardList">
		</ul>
	</div>
	<div class="paging">
		<ul>
		</ul>
	</div>
</div>
<!-- .twoDepthMenu -->
<!-- .container -->
<div id="noticeView" class="container noticeView">
	<div class="top">
		<div class="tit">
			<h3 id="tit"></h3>
			<div>
				<p class="date" id="date"></p>
				<p class="view" id="view"></p>
			</div>

		</div>
	</div>
	<div id="content" class="scroll mCustomScrollbar con">
		<div id="popInner" class="popInner">
			
		</div>
	</div>
		<div id="bottom" class="bottom">
			<div id="file" class="file">
				
				<div class="btnDown"><a href="#none"><img src="/niis/images/board/btn_down.gif" alt=""/></a></div>
			</div>
			<!-- <div class="file popInfo">
				<dl>
					<dt>사이트 접속시 팝업창으로 확인할 수 있습니다.</dt>
					<dd>공지 기간 : 2016년 10월 20일</dd>
				</dl>
				<div class="btnDown"><a href="#none"><img src="/niis/niis/ages/board/btn_pop_view.gif" alt="" /></a></div>
			</div> -->
			<div class="btnBoard">
<c:if test="${aUserAuth == '00' }">
				<a href="#none" onclick="preUpdate();">수정</a>
				<a href="#delete" onclick="deleteBoard();">삭제</a>
</c:if>
			</div>
		</div>
	</div>
</form>		
<!-- .container -->
	<div id="noticeRegist" class="container noticeRegist">
	</div>
	<!-- /.container -->
	
<script id="tmplBoardList" type="text/x-jquery-tmpl">
	<li id={{html boardSeq}}>
		<a href="#none" class="noclick" onclick="getBoardDetail('{{html boardCate}}', '{{html boardSeq}}')" style="overflow:hidden; text-overflow:ellipsis; white-space: nowrap; width:85%;">{{html topYn == 'Y' ? '<b>' + title + '</b>' : title}}</a>
	</li>
</script>
<script id="tmpNoticePopup2" type="text/x-jquery-tmpl">
	<div id="popup" class="general_type1">
		<div>
			<p class="system"><span>국토영상정보</span><span>공급시스템</span></p>
			<p class="popTit">{{html title}}</p>
			<div class="contents">
				<p>{{html contents}}</p>
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
					<p class="popTit">{{html title}}</p>
					<p>{{html contents}}</p>			
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
			<p class="popTit">{{html title}}</p>
			<div class="contents">
				<p>{{html contents}}</p>
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
				<span>{{html title}}</span>
			</div>
			<div class="contents">
				<p>{{html contents}}</p>
			</div>
			<div class="bottom">
				<span class="checkbox"><input type="checkbox" id="popupChk" /></span>
				<label class="labelTxt">오늘 하루 이 창을 열지 않음</label>
				<button class="searchDivBtn" disabled='disabled'>닫기</button>
			</div>
		</div>
	</div>
</script>