<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/board.js"></script>
<script type="text/javascript">
	
	var compApplyParam = "";
	
	$(document).ready(function(){
		getCompApplyList();
	});
	
	function getCompApplyList(){
		var data = {
				
		}
		
		compApplyParam = data;
		getCompApplyListPaging();
	}
	
	function getCompApplyListPaging(){
		
		var data = compApplyParam;
		
		setPagingInfo("mainDiv", "getCompApplyListPaging", "/apply/getCompApplyList.do");
		ajaxCallJson("/apply/getCompApplyList.do", data, function(result, data){
			if (result.list != null){
				if(result.list.length > 0){
					$("#compApplyList").empty().append($("#tmplCompApplyList").tmpl(result.list));
				}else{
					$("#compApplyList").html("<tr><td colspan=\"" + $("#compApplyHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
			}
		});
	}
	
	function getApplyDetail(supIdn){
		
		var data = {
				"supIdn"	: supIdn
		}
		ajaxCallPop("/apply/getApplyDetail.do", data);
	}
	
</script>

<!-- .topFix -->
<div class="topFix">
	<h2>완료 내역</h2>
	<a href="#main" class="btnPageClose"><img src="/niis/images/common/btn_page_close.png" alt="닫기" /></a>
	<div class="search">
		
	</div>
</div>
<!-- /.topFix -->
<!-- .bottomFix -->
<div class="bottomFix">
	<h3>조회 결과 <a href="#none" class="btnFulllScreen"><span class="skip">확대</span></a></h3>
	<!-- .centerVariable -->
	<div class="centerVariable">
		<!-- .boardStyle_2 -->
		<div class="boardStyle_2">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<colgroup>
					<col width="50px" />
				</colgroup>
				<tbody id="compApplyHead">
					<tr>
						<th>순번</th>
						<th>신청일자</th>
						<th>신청명</th>
						<th>반려일</th>
						<th>반려사유</th>
						<th>상태</th>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- /.boardStyle_2 -->
		<!-- .boardStyle_2 -->
		<div class="boardStyle_2">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<colgroup>
					<col width="50px" />
				</colgroup>
				<tbody id="compApplyList">
					<tr>
						<td colspan="8">검색조건을 선택 후 조회 버튼을 눌러주세요.</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- /.boardStyle_2 -->
	</div>
	<!-- /.centerVariable -->
	<!-- .bottom -->
	<div class="bottom">
		<div class="paging on">
			<a href="#none" class="btnFirst"><img src="/niis/images/board/btn_first.gif" alt="처음" /></a>
			<a href="#none" class="btnPrev"><img src="/niis/images/board/btn_prev.gif" alt="이전" /></a>
			paging &nbsp;&nbsp;<input type="text" value="1" /> <span>/ 1  페이지</span>
			<a href="#none" class="btnNext"><img src="/niis/images/board/btn_next.gif" alt="다음" /></a>
			<a href="#none" class="btnLast"><img src="/niis/images/board/btn_last.gif" alt="마지막" /></a>
			<select>
				<option>10</option>
			</select>
		</div>
		<ul class="btnArea on">
			
		</ul>
	</div>
	<!-- /.bottom -->
</div>
<!-- /.bottomFix -->

<script id="tmplCompApplyList" type="text/x-jquery-tmpl">
	<tr>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html num}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html reqDate}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html proDate}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html metaNam}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html approvalNam}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html supDate}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html purposeNam}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}')">{{html conName}}</a></td>
	</tr>
</script>