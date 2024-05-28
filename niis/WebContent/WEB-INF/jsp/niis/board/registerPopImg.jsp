<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
var img = new Image();
	
	$(document).ready(function(){
		$(".bottom .btnBoard #updatePopImg").hide();
		<c:if test="${update ne null && update ne '' }">
		 	updateDetailPopImg();
		 </c:if> 
		
		$("#boardRegForm #bFile").on('change',function(){
			
			ext = $("#boardRegForm #bFile").val().split('.').pop().toLowerCase(); //확장자
			//배열에 추출한 확장자가 존재하는지 체크
			 if ($(this).val() != ""){
				 if(jQuery.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
			    	  alert('이미지 파일이 아닙니다! (gif, png, jpg, jpeg 만 업로드 가능)');
			    	  $(this).val("");
			          return ;
			     }
				 
				 if(this.files[0].size > (1024*1024*5)){
					 alert('첨부 파일은 최대 5MB까지 첨부하실 수 있습니다.');
					 $(this).val("");
					 return false;
				 }
				 
				 var file = $("#boardRegForm #bFile").prop("files")[0];
				 var _url = window.URL || window.webkitURL;
				 
				 img.src = _url.createObjectURL(file);
				 
				 $("#exFiles").empty();
			 }
		});
	});
	
	function updateDetailPopImg(){
		if($("#update").val() != null){
			var data = {
					"boardCate"	: $("#boardCate").val(),
					"boardSeq"	: $("#boardSeq").val()
			}
			ajaxCallJson("/board/getBoardDetail.do", data,function(result, data){
				if (result.map != null){
					$("#boardRegForm .tit").empty().append("<h3>공지사항 수정</h3>");
					$(".boardView #title").val(result.map.title);
					$(".boardView .mgb5 #popBeginDt").val(result.map.popBeginDt);
					$(".boardView .mgb5 #popEndDt").val(result.map.popEndDt);
					$(".boardView .mgt10 #popPosX").val(result.map.popPosX);
					$(".boardView .mgt10 #popPosY").val(result.map.popPosY);
					
					
					//형식의 나머지 버튼 숨기기 
					$(".btnBoardViewTab a[id='1']").hide();
					$(".btnBoardViewTab a[id='3']").hide();
					
					$(".bottom .btnBoard #updatePopImg").show();
					$(".bottom .btnBoard #insertPopImg").hide();
					if(result.list.length > 0){
						for( var i =0 ; i <result.list.length; i++){
							$("#exFiles").append("<p> <b>-기존파일 : "+result.list[i].fileName+"("+result.list[i].fileSize +" bytes)<b></p>");
						}
					}
					
					
				}
			});
		} 
	}
	function updateBoardPopImg(){
		
		if(!validationPopImg()){
			return;
		}
		/* 
		if($("#bFile").val() == ""){
			alert("첨부파일은 필수 사항입니다.");
			return;
		}
		 */
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
	
	function insertBoardPopImg(){
		
		if($("#boardRegForm #bFile").val() == ""){
			alert("첨부파일을 등록해 주세요.");
			return;
		}
		
		if(!validationPopImg()){
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
	 //미리보기 클릭이벤트 
	function imgPreviewPopImg(){
		 
		if ($("#bFile").val() == ""){
			alert("첨부파일이 등록되지 않았습니다.");
			return;
		}
		 
		var vWidth = 0;
		var vHeight = 0; 
		
		 
		//객체생성후 데이터 할당
		var Xpos = $("#popPosX").val();
		var Ypos = $("#popPosY").val();
		if (Xpos == ""){
			Xpos = 0;
		}
		if (Ypos == ""){
			Ypos = 0;
		}
		
		var args = new Array();	
		args["file"] = $("#boardRegForm #bFile");
		args["title"] = $("#title").val(); 
		args["contents"] = null;
		args["popGbnCde"] = null;
		
		window.showModalDialog("/niis/board/previewPop.do", args, "dialogWidth:"+ img.width+"px; dialogHeight:"+img.height+
																  "px; dialogLeft:"+Xpos+"px; dialogTop:"+Ypos+"px; scroll:no;");
	}
	
	function validationPopImg(){
		
		if($("#boardRegForm #title").val() == null || $("#boardRegForm #title").val() == ""){
			alert("제목을 입력하세요.");
			$("#boardRegForm #title").focus();
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
	 
	 
</script>
<!--  팝업 이미지 -->
<form id="boardRegForm" enctype="multipart/form-data">
	<input type="hidden" id="boardCate" name="boardCate" value="${master.boardCate }" />
	<input type="hidden" id="parentSeq" name="parentSeq" value="${parentSeq }" />
	<input type="hidden" id="boardSeq" name="boardSeq" value="${boardSeq }" />
	<input type="hidden" id="update" name="update" value="${update }" />
	<input type="hidden" id="popGbnCde" name="popGbnCde" value="1" /><!-- 팝업이미지:1 ,팝업글:2,3,4,5 -->
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
									<a href="#none" id="2" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/registerPopImg')"</c:if> class="active">팝업이미지</a>
									<a href="#none" id="3" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/registerPop')"</c:if>>팝업글</a>
								</div>
							</td>
						</tr>
						<tr>
							<th class="colorOr">제목 *</th>
							<td><input type="text" id="title" name="title" style="width:100%;" /></td>
						</tr>
						<tr>
							<th class="alignTop colorOr">첨부 *</th>
							<td>
								<div class="mgb5"><input type="file" id="bFile" name="bFile" value=""/></div>
								<p id="exFiles"></p>
								<p>- 첨부 파일은 최대 5MB까지 첨부하실 수 있습니다.</p>
								<p>- 단일 파일만 등록이 가능합니다. 파일이 여러 개일 경우 압축 후 등록해주세요.</p>
							</td>
						</tr>
						<tr>
							<th class="alignTop">설정</th>
							<td>
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
			<a href="#none" class="white" id="imgPreview" onclick="imgPreviewPopImg();">미리보기</a>
			<a href="#none" id="insertPopImg" onclick="insertBoardPopImg();">등록</a>
			<a href="#none" id="updatePopImg" onclick="updateBoardPopImg();">수정</a>
</c:if>
			<a href="#none" class="gray btnNoticeRegistClose" onclick="cancleBoard();">취소</a>
		</div>
	</div>
</form>
