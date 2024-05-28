<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>

<script type="text/javascript">
	
	$(document).ready(function(){
		$(".bottom .btnBoard #updatePop").hide();
		<c:if test="${update ne null && update ne '' }">
		 	updateDetailPop();
		 </c:if> 
	});
	function updateDetailPop(){
		if($("#update").val() != null){
			var data = {
					"boardCate"	: $("#boardCate").val(),
					"boardSeq"	: $("#boardSeq").val()
			}
			ajaxCallJson("/board/getBoardDetail.do", data,function(result, data){
				if (result.map != null){
					$("#boardRegForm .tit").empty().append("<h3>공지사항 수정</h3>");
					$(".boardView #title").val(result.map.title);
					$(".boardView #contents").val(result.map.contents);
					$(".boardView .mgb5 #popBeginDt").val(result.map.popBeginDt);
					$(".boardView .mgb5 #popEndDt").val(result.map.popEndDt);
					$(".boardView .mgt10 #popPosX").val(result.map.popPosX);
					$(".boardView .mgt10 #popPosY").val(result.map.popPosY);
					
					
										
					//형식의 나머지 버튼 숨기기 
					$(".btnBoardViewTab a[id='1']").hide();
					$(".btnBoardViewTab a[id='2']").hide();
					
					$(".bottom .btnBoard #updatePop").show();
					$(".bottom .btnBoard #insertPop").hide();

					$("#bgSelect li").each(function(){
						if($(this).val() == result.map.popGbnCde){
							$("#boardRegForm #popGbnCde").val(result.map.popGbnCde);
							$(this).addClass('active');
						}
					});
				}
			});
		}
	}
	function updateBoardPop(){
		if(!validationPop()){
			return;
		}
		if(!confirm("게시물을 수정 하시겠습니까?")){
			return;
		}
		 ajaxCallFile("/board/updateBoardPop.do", "boardRegForm", function(result, data){
			if(result.RETURN_CD == "200"){
				alert("수정 되었습니다.");
				$("#noticeRegist").hide();
				
				getMainNotice();
				getBoardListPaging("U");
			}else{
				alert("수정에 실패 했습니다.")
			}
			
		}); 
	}
	function insertBoardPop(){
		
		if(!validationPop()){
			return;
		}
		
		if(!confirm("게시물을 등록하시겠습니까?")){
			return;
		}
		
		 ajaxCallFile("/board/insertBoardPop.do", "boardRegForm", function(result, data){
			alert("등록되었습니다.");
			$("#noticeRegist").hide();
			
			getMainNotice();
			getBoardListPaging();
		}); 
	}
	
	function imgPreview(){
		
		var vWidth = 0;
		var vHeight = 0; 
		//validation 
		if($("#boardRegForm .bgSelect .active").val() == null){
			alert("배경이미지가 선택되지 않았습니다.");
			return;
		}else{
			var popGbnCde = $("#boardRegForm .bgSelect .active").val();
			switch(popGbnCde){
				case 2 :
					vWidth = 475;
					vHeight = 650;
					break;
				case 3 :
					vWidth = 480;
					vHeight = 655;
					break;
				case 4 :
					vWidth = 470;
					vHeight = 350;
					break;
				case 5 :
					vWidth = 470;
					vHeight = 650;
					break;
			}
		}
		
		
		 
		//객체생성후 데이터 할당
		var Xpos = $("#boardRegForm #popPosX").val();
		var Ypos = $("#boardRegForm #popPosY").val();
		if (Xpos == ""){
			Xpos = 0;
		}
		if (Ypos == ""){
			Ypos = 0;
		}
		
		var args = new Array();	
		args["file"] = null;
		args["title"] = $(" #boardRegForm #title").val(); 
		args["contents"] = $("#boardRegForm #contents").val();
		args["popGbnCde"] = $("#boardRegForm #popGbnCde").val();
		args["argObj"] ={ "popTit"	:	$("#boardRegForm #title").val(),
					      "popCont" :	replaceAll($("#boardRegForm #contents").val(),"\n",'<br>') };
		
		window.showModalDialog("/niis/board/previewPop.do", args, "dialogWidth:auto; dialogHeight:auto"+
																  "; dialogLeft:"+Xpos+"px; dialogTop:"+Ypos+"px; scroll:no;");
	}
	//string -> date로 
	function toTimeObject(time) { //parseTime(time)
	    var year  = time.substr(0,4);
	    var month = time.substr(4,2) - 1; // 1월=0,12월=11
	    var day   = time.substr(6,2);

	    return new Date(year,month,day);
	}
	//date -> String
	function toStringObject(date){
		var year = date.getFullYear();                 //yyyy
		var month = (1 + date.getMonth());             //M
		month = month >= 10 ? month : '0' + month;     // month 두자리로 저장
		var day = date.getDate();                      //d
		day = day >= 10 ? day : '0' + day;             //day 두자리로 저장
		return  year + '' + month + '' + day;
	}

	//몇일 후
	function getDateAfter(start, i){
		var newDt = new Date(start);
		newDt.setDate( newDt.getDate() + i );
		return toStringObject(newDt);
	}
	
	function validationPop(){
		if($("#boardRegForm #title").val() == null || $("#boardRegForm #title").val() == ""){
			alert("제목을 입력하세요.");
			$("#boardRegForm #title").focus();
			return false;
		}
		if($("#boardRegForm #contents").text() == null || $("#boardRegForm #contents").text() == ""){
			alert("내용을 입력하세요.");
			$("#boardRegForm #contents").focus();
			return false;
		}

		if($(".bgSelect .active").val() == null){
			alert("배경이미지가 선택되지 않았습니다.");
			return false;
		}
		var popBeginDt  = $("#boardRegForm #popBeginDt").val();
		var popEndDt	= $("#boardRegForm #popEndDt").val();
		
		if(popBeginDt == null || popBeginDt == ""){
			$("#boardRegForm #popBeginDt").val($("#boardRegForm #todayDate").val());
		}else{
			if(popBeginDt.indexOf('-') > -1){
				var BeginDt = new Array();
				BeginDt = popBeginDt.split('-');
				$("#boardRegForm #popBeginDt").val(BeginDt[0]+BeginDt[1]+BeginDt[2]);
			}
			
		}
		
		if(popEndDt == null || popEndDt == ""){
			//만약 마지막 일이 없다면 2주후가 default
			var twoWeekAfter = getDateAfter(toTimeObject($("#boardRegForm #popBeginDt").val()), 14);
			$("#boardRegForm #popEndDt").val(twoWeekAfter);
		}else{
			if(popEndDt.indexOf('-') > -1){
				var EndDt = new Array();
				EndDt = (popEndDt).split('-');
				$("#boardRegForm #popEndDt").val(EndDt[0]+EndDt[1]+EndDt[2]);
			}
			
		}
		
		if (toTimeObject($("#boardRegForm #popBeginDt").val()) > toTimeObject($("#boardRegForm #popEndDt").val()) ){
			alert("시작일은 종료일보다 클 수 없습니다.");
			$("#boardRegForm #popEndDt").val("");
			$("#boardRegForm #popBeginDt").val("");
			return false;
		}
		return true;
	}

</script>
<!-- 팝업글  -->
<form id="boardRegForm" enctype="multipart/form-data">
	<input type="hidden" id="boardCate" name="boardCate" value="${master.boardCate }" />
	<input type="hidden" id="parentSeq" name="parentSeq" value="${parentSeq }" />
	<input type="hidden" id="boardSeq" name="boardSeq" value="${boardSeq }" />
	<input type="hidden" id="update" name="update" value="${update }" />
	<input type="hidden" id="popGbnCde" name="popGbnCde" value="" /><!-- 팝업이미지:1 ,팝업글:2,3,4,5 -->
	<input type="hidden" id="todayDate" name="todayDate" value="${todayDate }" /> 
	<div class="top">
		<div class="tit">
			<h3>공지사항 등록</h3>
		</div>
	</div>
	<div class="scroll mCustomScrollbar con">
		<div class="inner">
			<!-- .boardView -->
			<div class="boardView">
				<table>
					<colgroup>
						<col width="70" />
					</colgroup>
					<tbody>
						<tr>
							<th class="colorOr">형식 *</th>
							<td>
								<div class="btnBoardViewTab">
									<a href="#none" id="1" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/register')"</c:if>>게시글</a>
									<a href="#none" id="2" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/registerPopImg')"</c:if>>팝업이미지</a>
									<a href="#none" id="3" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/registerPop')"</c:if> class="active">팝업글</a>
								</div>
							</td>
						</tr>
						<tr>
							<th class="colorOr">제목 *</th>
							<td><input id="title" name="title" type="text" style="width:100%;" /></td>
						</tr>
						<tr>
							<th class="alignTop colorOr">내용 *</th>
							<td><textarea name="contents" id="contents" cols="0" rows="0" style="width:100%; height:200px;"></textarea></td>
						</tr>
						<tr>
							<th class="alignTop">설정</th>
							<td>
								<div class="mgb15">
									<strong class="middle" style="color:#133f4a;">배경이미지 선택</strong>
								</div>
								<ul class="bgSelect" id="bgSelect">
									<li value="2">
										<img src="/niis/images/popup/bg_popup_general_01.jpg" width="118" height="118" alt="" />
										<span class="span txt">일반형1</span>
									</li>
									<li value="3">
										<img src="/niis/images/popup/bg_popup_general_02.jpg" width="118" height="118" alt="" />
										<span class="span txt">일반형2</span>
									</li>
									<li value="4">
										<img src="/niis/images/popup/bg_popup_announcement.jpg" width="118" height="118" alt="" />
										<span class="span txt">안내형</span>
									</li>
									<li value="5">
										<img src="/niis/images/popup/bg_popup_emergency.png" width="118" height="118" alt="" />
										<span class="span txt" style="color:white">긴급형</span>
									</li>
								</ul>
								<script type="text/javascript">
									$(function(){
										$('.bgSelect li').click(function(){
											$(this).addClass('active').siblings().removeClass('active');
											$("#boardRegForm #popGbnCde").val($(this).val());
										});
									});
								</script>
								<div class="mgb5">
									<span class="middle">공지기간 시작일</span> <input type="text" id="popBeginDt" name="popBeginDt" class="date" /> &nbsp;&nbsp;&nbsp;&nbsp; 
									<span class="middle">공지기간 종료일</span> <input type="text" id="popEndDt" name="popEndDt" class="date" />
								</div>
								<p>- 게시물 등록일자와 상관없이 공지사항 팝업은 설정된 시작일부터 나타납니다.</p>
								<p>- 종료일을 설정하시면 선택한 날짜 이후 자동으로 게시가 중단됩니다.</p>
								<p>- 시작 및 종료일을 설정하지 않을 경우 등록 순간부터 게시물이 나타납니다.(기본 2주간 게시됩니다.)</p>
								<div class="mgt10">
									<strong class="middle" style="color:#133f4a;">팝업위치</strong>
									<span class="middle">TOP</span> <input type="text" id="popPosX" name="popPosX" style="width:70px; text-align:right;" /> <span class="middle">PX</span> &nbsp;&nbsp;&nbsp;&nbsp;
									<span class="middle">LEFT</span> <input type="text" id="popPosY" name="popPosY" style="width:70px; text-align:right;" /> <span class="middle">PX</span>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /.boardView -->
		</div>
	</div>
	<div class="bottom">
		<div class="btnBoard">
<c:if test="${aUserAuth == '00' }">
			<a href="#none" class="white" id="imgPreview" onclick="imgPreview();">미리보기</a>
			<a href="#none" id="insertPop" onclick="insertBoardPop();">등록</a>
			<a href="#none" id="updatePop" onclick="updateBoardPop();">수정</a>
</c:if>
			<a href="#none" class="gray btnNoticeRegistClose" onclick="cancleBoard();">취소</a>
		</div>
	</div>
</form>
