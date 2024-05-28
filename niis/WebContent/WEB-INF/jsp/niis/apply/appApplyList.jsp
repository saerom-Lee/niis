<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	var appApplyParam = "";
	
	$(document).ready(function(){
		getAppApplyList();
	});
	
	function getAppApplyList(){
		var data = {
				
		}
		
		appApplyParam = data;
		getAppApplyListPaging();
	}
	
	function getAppApplyListPaging(){
		var data = appApplyParam;
		
		setPagingInfo("mainDiv", "getAppApplyListPaging", "/apply/getAppApplyList.do");
		ajaxCallJson("/apply/getAppApplyList.do", data, function(result, data){
			if (result.list != null){
				
				$("#appApplyList").parent().parent(".scroll").mCustomScrollbar("destroy");
				
				if(result.list.length > 0){
					$("#appApplyList").empty().append($("#tmplAppApplyList").tmpl(result.list));
				}else{
					$("#appApplyList").html("<tr><td colspan=\"" + $("#appApplyHead th").length + "\">조회 내역이 없습니다.</td></tr>");
				}
				
				$("#appApplyList").parent().parent(".scroll").mCustomScrollbar();
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
	
	function appImgDownloadList(supIdn){
		
		if (innorix.api.isPluginInstalled() == false) {
			innorix.api.goToInstallPage("<%=request.getContextPath() %>/innorix/common/install/install.html");		// 컨트롤 설치안내 웹 페이지로 전환
		}
		
		
		var url = "/supply/downloadListPop.do";
		var data = {
				"supIdn" : supIdn
		}
		ajaxCallPop(url, data, "downloadPop", 600);
		
		$('#mainDiv #_currPage').val(sessionStorage.getItem("_$currPage"));
		
		getAppApplyListPaging();
		/*  
		var url = "/supply/downloadPop.do";
		var data = {
				"supIdn" : supIdn
		}
		ajaxCallPop(url, data, "", 450);
		 */
	}
	
	function applyExtension(supIdn){
		
		if(confirm("다운로드 가능 기간을 연장하시겠습니까?")){
			var data = {
					"supIdn" : supIdn
			}
			ajaxCallJson("/apply/applyExtension.do", data, function(result){
				
				if(result != null && result.state != null){
					if(result.state == "1"){
						alert("다운로드 가능 기간이 연장되었습니다.");
					}else{
						alert("다운로드 가능 기간 연장이 불가합니다.");
					}
				}
				$('#mainDiv #_currPage').val(sessionStorage.getItem("_$currPage"));
				getAppApplyListPaging();
			});
		}
	}
	
</script>

<!-- .container -->
<div class="container pageFull">
	<div class="top">
		<div class="contTit">
			<!-- <div id="btnTwoDepthMenuClose"></div> -->
			<h3>신청서 승인</h3>
		</div>
		<div class="thead">
			<table>
				<colgroup>
					<col width="80" />
					<col width="12%" />
					<col width="*%" />
					<col width="12%" />
					<col width="16%" />
					<col width="8%" />
					<col width="16%" />
					<col width="10%" />
				</colgroup>
				<thead id="appApplyHead">
					<tr>
						<th>순번</th>
						<th>신청일자</th>
						<th>신청명</th>
						<th>승인일</th>
						<th>다운로드</th>
						<th>남은 일시</th>
						<th>기간연장</th>
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
				<col width="12%" />
				<col width="*%" />
				<col width="12%" />
				<col width="16%" />
				<col width="8%" />
				<col width="16%" />
				<col width="10%" />
			</colgroup>
			<tbody id="appApplyList">
			</tbody>
		</table>
	</div>
	<div class="paging">
		<ul>
		</ul>
	</div>
</div>
<!-- /.container -->

<script id="tmplAppApplyList" type="text/x-jquery-tmpl">
	<tr>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html num}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html reqDate}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html reqName}}</a></td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html supDate}}</a></td>
		<td>
			{{if remainedDay == '-' && extensionYn == 'N'}}
				{{html (remainedDay == '0' || remainedDay == '-') && extensionYn == 'N' ? '<a class="result result_3">만료</a>' : '' }}
			{{/if}}
			{{if remainedDay != '-'}}
				{{html approvalCde == '2' || approvalCde == '50' ? '<a class="result result_1" href="#none" onclick="appImgDownloadList(\'' + supIdn + '\');">다운로드</a>' : '' }}
				{{html approvalCde == '3' ? '<a class="result result_1" href="#none" onclick="appImgDownloadList(\'' + supIdn + '\');">다운로드중</a>' : '' }}
				{{html approvalCde == '4' ? '<a class="result result_2">완료</a>' : '' }}
				{{html approvalCde == '7' ? '<a class="result result_3">만료</a>' : '' }}
			{{/if}}
		</td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html remainedDay}}</a></td>
		<td>
			{{html (approvalCde == '2' || approvalCde == '50') && extensionYn == 'Y' ? '<a class="result result_2" href="#none" onclick="applyExtension(\'' + supIdn + '\');">기간연장</a>' : '-' }}
		</td>
		<td><a href="#none" onclick="getApplyDetail('{{html supIdn}}','{{html atCnt}}','{{html airCnt}}','{{html demCnt}}','{{html ortCnt}}','{{html mapCnt}}','{{html reqName}}')">{{html status}}</a></td>
	</tr>
</script>