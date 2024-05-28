<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var waitApplyParam = "";
	
	$(document).ready(function(){
		getWaitApplyList();
	});
	
	function getWaitApplyList(){
		var data = {
				
		}
		
		waitApplyParam = data;
		getApplyListPaging();
	}
	
	function getApplyListPaging(){
		
		var data = waitApplyParam;
		
		setPagingInfo("mainDiv", "getApplyListPaging", "/apply/getWaitApplyList.do");
		ajaxCallJson("/apply/getWaitApplyList.do", data, function(result, data){
			if (result.list != null){
				
				$("#waitApplyList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				if(result.list.length > 0){
					$("#waitApplyList").empty().append($("#tmplWaitApplyList").tmpl(result.list));
				}else{
					$("#waitApplyList").html("<tr><td colspan='6'\"" + $("#waitApplyHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				
				$("#waitApplyList").parent().parent(".scroll").mCustomScrollbar();
			}
		});
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
	

	function deleteBtn(supIdn){
		
		var data = {
						"supIdn" : supIdn
		};
		
		if(confirm("삭제하시겠습니까?")){
			
			ajaxCallJson("/apply/waitApplyDelete.do", data, function(result, data){
	
				alert("삭제 완료");
				
				//메인화면 신청 내역 갱신
				getMainApply();
				$('#mainDiv #_currPage').val(sessionStorage.getItem("_$currPage"));
				getApplyListPaging();
				
			});
			
		}
		
	}
	
</script>

<!-- .container -->
<div class="container pageFull">
	<div class="top">
		<div class="contTit">
			<!-- <div id="btnTwoDepthMenuClose"></div> -->
			<h3>신청서 대기</h3>
		</div>
		<div class="thead">
			<table>
				<colgroup>
				<col width="80" />
				<col width="23%" />
				<col width="23%" />
				<col width="23%" />
				<col width="*" />
				<col width="100" />
				</colgroup>
				<thead>
					<tr>
						<th>순번</th>
						<th>신청일자</th>
						<th>신청명</th>
						<th>사용 목적</th>
						<th>상태</th>
						<th class="last">삭제</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div class="scroll tbody">
		<table>
			<colgroup>
				<col width="80" />
				<col width="23%" />
				<col width="23%" />
				<col width="23%" />
				<col width="*" />
				<col width="100" />
			</colgroup>
			<tbody id="waitApplyList">
			</tbody>
		</table>
	</div>
	<div class="paging">
		<ul>
		</ul>
	</div>
</div>
<!-- /.container -->

<script id="tmplWaitApplyList" type="text/x-jquery-tmpl">
	<tr>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html num}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html reqDate}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html reqName}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html purpose}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html status}}</a></td>
		<td>
			{{if approvalCde == '0' }}
				<input type="button" id="deleteBtn" onclick="deleteBtn('{{html supIdn}}')" value="삭제"/>
			{{/if}}
		</td>
	</tr>
</script>