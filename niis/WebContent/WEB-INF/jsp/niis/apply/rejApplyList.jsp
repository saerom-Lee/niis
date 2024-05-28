<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var rejApplyParam = "";
	
	$(document).ready(function(){
		getRejApplyList();
	});
	
	function getRejApplyList(){
		var data = {
				
		}
		
		rejApplyParam = data;
		getRejApplyListPaging();
	}
	
	function getRejApplyListPaging(){
		
		var data = rejApplyParam;
		
		setPagingInfo("mainDiv", "getRejApplyListPaging", "/apply/getRejApplyList.do");
		ajaxCallJson("/apply/getRejApplyList.do", data, function(result, data){
			if (result.list != null){
				
				$("#rejApplyList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				if(result.list.length > 0){
					$("#rejApplyList").empty().append($("#tmplRejApplyList").tmpl(result.list));
				}else{
					$("#rejApplyList").html("<tr><td colspan=\"" + $("#rejApplyHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				
				$("#rejApplyList").parent().parent(".scroll").mCustomScrollbar();
			}
		});
	}
	
	function getApplyDetail(supIdn){
		
		var data = {
				"supIdn"	: supIdn
		}
		ajaxCallPop("/apply/getApplyDetail.do", data);
	}
	
	function getApplyDetail(supIdn, atCnt, airCnt, demCnt, ortCnt, mapCnt, reqName){
		var data = {
						"supIdn"	: supIdn
					,	"atCnt"		: atCnt
					, 	"airCnt"	: airCnt
					, 	"demCnt"	: demCnt
					, 	"ortCnt"	: ortCnt
					, 	"mapCnt"	: mapCnt
					, 	"reqName"	: reqName
		}
		
		// ApplyController.java
		ajaxCallPop("/apply/getApplyDetail.do", data);	
	}
	
	
	function checkRejectCause(supIdn){
		
		var data = {
				"supIdn"	:	supIdn
		};
		
		ajaxCallPop("/apply/rejectCausePop.do", data, "returnReason");
	}
	
</script>

<!-- .container -->
<div class="container pageFull">
	<div class="top">
		<div class="contTit">
			<!-- <div id="btnTwoDepthMenuClose"></div> -->
			<h3>신청서 반려</h3>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="80" />
					<col width="16%" />
					<col width="16%" />
					<col width="16%" />
					<col width="16%" />
					<col width="*" />
				</colgroup>
				<thead id="rejApplyHead">
					<tr>
						<th>순번</th>
						<th>신청일자</th>
						<th>신청명</th>
						<th>반려일</th>
						<th>반려사유</th>
						<th class="last">상태</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="scroll tbody">
		<table>
			<colgroup>
				<col width="80" />
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="16%" />
				<col width="*" />
			</colgroup>
			<tbody id="rejApplyList">
			</tbody>
		</table>
	</div>
	<div class="paging">
		<ul>
		</ul>
	</div>
</div>
<!-- /.container -->

<script id="tmplRejApplyList" type="text/x-jquery-tmpl">
	<tr>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html num}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html reqDate}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html reqName}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html compDate}}</a></td>
		<td><a href="#none" class="result result_3 btnLayerOpen" onclick="checkRejectCause('{{html supIdn}}')">반려사유 확인</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')" id="rejStatus{{html supIdn}}">{{html status}}</a></td>
	</tr>
</script>