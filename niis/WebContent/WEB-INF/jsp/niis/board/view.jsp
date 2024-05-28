<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	$(document).ready(function(){
		
	});
	
	function updateBoard(){
		
		if(!confirm("게시물을 수정하시겠습니까?")){
			return;
		}
		
		ajaxCallFile("/board/updateBoard.do", "boardViewForm", function(result, data){
			alert("수정되었습니다.");
			$("#boardViewForm .btnLayerClose").click();
			//메인화면 공지사항 갱신
			getMainNotice();
			getBoardListPaging();
		});
	}
	
	function deleteBoard(){
		
		if(!confirm("게시물을 삭제하시겠습니까?")){
			return;
		}
		
		var data = $("#boardViewForm").serializeObject();
		
		ajaxCallJson("/board/deleteBoard.do", data, function(result, data){
			alert("삭제되었습니다..");
			$("#boardViewForm .btnLayerClose").click();
			//메인화면 공지사항 갱신
			getMainNotice();
			getBoardListPaging();
		});
	}
	
	function reply(){
		
		var data = $("#boardViewForm").serializeObject();
		data["parentSeq"] = "${map.boardSeq }";
		
		ajaxCallPop("/board/register.do", data);
	}
	
	function downloadAttachFile(boardCate, boardSeq, fileSeq){
		
		location.href = "/niis/board/downloadAttachFile.do?boardCate=" + boardCate + "&boardSeq=" + boardSeq + "&fileSeq=" + fileSeq;
	}
	
</script>
<form id="boardViewForm" enctype="multipart/form-data">
<div class="tit">
	<h3>${master.boardName } 상세보기</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="창 닫기" /></a>
</div>
<div class="layerCont">
	<!-- .boardStyle -->
	<div class="boardStyle">
		<input type="hidden" id="boardCate" name="boardCate" value="${master.boardCate }" />
		<input type="hidden" id="boardSeq" name="boardSeq" value="${map.boardSeq }" />
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<colgroup>
				<col width="105"/>
				<col width=""/>
				<col width="105"/>
				<col width=""/>
			</colgroup>
			<tbody>
				<tr>
					<th>작성자</th>
					<td>${map.createUsrNm }</td>
					<th>등록일</th>
					<td>${map.createDt }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3">
					<c:if test="${aUserMgno != map.createUsr && aUserAuth != '00' }">
						<input type="text" id="title" name="title" value="${map.title }" style="width:500px" readonly />
					</c:if>
					<c:if test="${aUserMgno == map.createUsr || aUserAuth == '00' }">
						<input type="text" id="title" name="title" value="${map.title }" style="width:420px" />
						&nbsp;&nbsp;<input type="checkbox" id="topYn" name="topYn" value="Y" <c:if test="${map.topYn == 'Y' }">checked</c:if> />&nbsp;상단게시
					</c:if>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea id="contents" name="contents" cols="82" rows="20" <c:if test="${aUserMgno != map.createUsr && aUserAuth != '00' }">readonly</c:if>>${map.contents }</textarea>
					</td>
				</tr>
				<c:if test="${map.fileCnt > 0 }">
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<c:forEach items="${list }" var="list">
							<a href="#none" onclick="downloadAttachFile('${list.boardCate }', '${list.boardSeq }', '${list.fileSeq }');">${list.fileName } (${list.fileSize }byte)</a><br/>
						</c:forEach>
					</td>
				</tr>
				</c:if>
				<c:if test="${aUserMgno == map.createUsr || aUserAuth == '00' }">
				<tr>
					<th>새첨부파일</th>
					<td colspan="3">
						<input type="file" id="bFile" name="bFile" value="" style="width:500px" />
					</td>
				</tr>
				</c:if>
				<tr>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- /.boardStyle -->
</div>
<div class="btnArea">
	<c:if test="${aUserMgno == map.createUsr || aUserAuth == '00' }">
		<a href="#none" onclick="deleteBoard();">삭제</a>
		<a href="#none" onclick="updateBoard();">수정</a>
	</c:if>
	<c:if test="${master.replyYn == 'Y' }">
		<a href="#none" onclick="reply();">답글</a>
	</c:if>
	<a href="#none" class="btnLayerClose">닫기</a>
</div>
<!-- /.btnArea -->
</form>