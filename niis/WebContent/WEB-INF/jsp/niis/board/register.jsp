<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	$(document).ready(function(){
		$(".bottom .btnBoard #update").hide();
		 <c:if test="${update ne null && update ne '' }">
		 	updateDetail();
		 </c:if> 
		 //첨부 파일 체크 
		 $("#boardRegForm #bFile").on("change" , function(){
			 
			 ext = $("#boardRegForm #bFile").val().split('.').pop().toLowerCase(); //확장자
			 //배열에 추출한 확장자가 존재하는지 체크
			 if ($(this).val() != ""){
				 if(jQuery.inArray(ext, ['txt','jpg','jpeg','png','bmp','gif','hwp','doc','docx','xls','xlsx','ppt','pptx','pdf','zip']) == -1) {
			    	  alert('등록 가능한 파일형식이 아닙니다.');
			    	  $(this).val("");
			          return ;
			     }
				 
				 if(this.files[0].size > (1024*1024*5)){
					 alert('첨부 파일은 최대 5MB까지 첨부하실 수 있습니다.');
					 $(this).val("");
					 return false;
				 }
				 
				 $("#exFiles").empty();
			 }
		 });
		//업데이트 유무 판단 
		
	});
	
	function updateDetail(){
		
		if($("#boardRegForm #update").val() != null){
			var data = {
					"boardCate"	: $("#boardRegForm #boardCate").val(),
					"boardSeq"	: $("#boardRegForm #boardSeq").val()
			}
			ajaxCallJson("/board/getBoardDetail.do", data,function(result, data){
				if (result.map != null){
					if(result.map.contents != null ){
						var str = result.map.contents;
						/* var replacedStr = replaceAll(str,"\n",'<br>'); 
						result.map.contents = replacedStr; */
					}
					
					$("#boardRegForm .tit").empty().append("<h3>공지사항 수정</h3>");
					$("#boardRegForm #title").val(result.map.title);
					$("#boardRegForm #contents").val(result.map.contents);
					
					//형식의 나머지 버튼 숨기기 
					$(".btnBoardViewTab a[id='2']").hide();
					$(".btnBoardViewTab a[id='3']").hide();
					
					$(".bottom .btnBoard #update").show();
					$(".bottom .btnBoard #insert").hide();
					if(result.list.length > 0){
						for( var i =0 ; i <result.list.length; i++){
							$("#exFiles").append("<p> <b>-기존파일 : "+result.list[i].fileName+"("+result.list[i].fileSize +" bytes)<b></p>");
						}
					}
					
					if(result.map.topYn == 'Y'){
						$("#boardView #boldYn").prop("checked", true);
						initCheckbox();
					}
				}
			});
		} 
	}
	
	function insertBoard(){
		
		if(!validation()){
			return;
		}
		
		if(!confirm("게시물을 등록하시겠습니까?")){
			return;
		}
		
		if(!$("#boardRegForm #boldYn").is(":checked")){
			$("#boardRegForm #boldYn").remove();
		}
		
		ajaxCallFile("/board/insertBoard.do", "boardRegForm", function(result, data){
			alert("등록되었습니다.");
			$("#noticeRegist").hide();
			
			getMainNotice();
			getBoardListPaging();
		});
	}
	
	function validation(){
		
		var title = $("#boardRegForm #title").val();
		var contents = $("#boardRegForm #contents").val();
		
		if(title == null || title == ""){
			alert("제목을 입력하세요.");
			$("#boardRegForm #title").focus();
			return false;
		}
		
		if(contents == null || contents == ""){
			alert("내용을 입력하세요.");
			$("#boardRegForm #contents").focus();
			return false;
		}
		
		return true;
	}
	
	function updateBoard(){
		
		if(!validation()){
			return;
		}
		
		if(!confirm("게시물을 수정 하시겠습니까?")){
			return;
		}
		
		if(!$("#boardRegForm #boldYn").is(":checked")){
			$("#boardRegForm #boldYn").remove();
		}
		 ajaxCallFile("/board/updateBoard.do", "boardRegForm", function(result, data){
			alert("수정 되었습니다.");
			$("#noticeRegist").hide();
			
			getMainNotice();
			//getBoardListPaging("U");
			$('#notice li[id="${boardSeq }"]').find("strong").children("a").html($("#boardRegForm #title").val());
			getBoardDetail("${master.boardCate }", "${boardSeq }");
		}); 
	}
	
</script>
<!-- 게시글  -->
<form id="boardRegForm" enctype="multipart/form-data">
	<input type="hidden" id="boardCate" name="boardCate" value="${master.boardCate }" />
	<input type="hidden" id="boardSeq" name="boardSeq" value="${boardSeq }" />
	<input type="hidden" id="parentSeq" name="parentSeq" value="${parentSeq }" />
	<input type="hidden" id="update" name="update" value="${update }" />
		<div class="top">
			<div class="tit">
				<h3>${master.boardName } 등록</h3>
			</div>
		</div>
		
		<div class="scroll mCustomScrollbar con">
			<div class="inner">
				<!-- .boardView -->
				<div id="boardView" class="boardView">
					<table>
						<colgroup>
							<col width="70" />
						</colgroup>
						<tbody>
							<tr>
								<th class="colorOr">형식 *</th>
								<td>
									<div class="btnBoardViewTab">
										<a href="#none" id="1" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/register')"</c:if> class="active">게시글</a>
										<a href="#none" id="2" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/registerPopImg')"</c:if>>팝업이미지</a>
										<a href="#none" id="3" <c:if test="${update eq null || update eq '' }">onclick="register('${master.boardCate }','board/registerPop')"</c:if>>팝업글</a>
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
								<th class="alignTop">첨부</th>
								<td>
									<div class="mgb5"><input type="file" id="bFile" name="bFile" value=""/></div>
									<p id="exFiles"></p>
									<p>- 첨부 파일은 최대 5MB까지 첨부하실 수 있습니다.</p>
									<p>- 단일 파일만 등록이 가능합니다. 파일이 여러 개일 경우 압축 후 등록해주세요.</p>
								</td>
							</tr>
							<tr>
								<th>설정</th>
								<td>
									<span class="checkbox">
										<input type="checkbox" id="boldYn" name="boldYn" value="Y" />
									</span>
									<label class="labelTxt" style="position:relative; top:2px;">중요(제목을 강조해줍니다)</label></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.boardView -->
			</div>
		</div>
		<div class="bottom">
		
			<div id="file" class="file">
			</div>
			<div class="btnBoard">
<c:if test="${aUserAuth == '00' }">
				<a href="#none" id="insert" onclick="insertBoard();">등록</a>
				<a href="#none" id="update" onclick="updateBoard();">수정</a>
</c:if>
				<a href="#none" onclick="cancleBoard();" class="gray btnNoticeRegistClose">취소</a>
			</div>
		</div>
</form>