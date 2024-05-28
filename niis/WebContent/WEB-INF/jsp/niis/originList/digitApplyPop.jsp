<!-- 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	
	#divSubcont input::-webkit-input-placeholder { color:#CCC; }
	#divSubcont input::-moz-placeholder { color:#CCC; }
	#divSubcont input:-moz-placeholder { color:#CCC; }
	#divSubcont input:-ms-input-placeholder { color:#CCC; }
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">
	
	function regApply(){
		
		var apPost         = $("#applyForm #apPost").val();
		var apDepartment   = $("#applyForm #apDepartment").val();
		var apName         = $("#applyForm #apName").val();
		var apTel          = $("#applyForm #apTel").val();
		var purpose        = $("#applyForm #purpose option:selected").val();
		var detailPurpose  = $("#applyForm #detailPurpose option:selected").val();
		var secDataMngPlan = $("#applyForm #secDataMngPlan").val();
		var agreeYn        = $("#applyForm #agreeYn:checked").val();
		
		if(apPost == null || apPost == "" || apPost == "undefined"){
			alert("기관명을 입력해 주세요.");
			$("#applyForm #apPost").focus();
			return;
		}
		
		if(apDepartment == null || apDepartment == "" || apDepartment == "undefined"){
			alert("부서를 입력해 주세요.");
			$("#applyForm #apDepartment").focus();
			return;
		}
		
		
		if(apName == null || apName == "" || apName == "undefined"){
			alert("성명을 입력해 주세요.");
			$("#applyForm #apName").focus();
			return;
		}
		
		if(apTel == null || apTel == "" || apTel == "undefined"){
			alert("연락처를 입력해 주세요.");
			$("#applyForm #apTel").focus();
			return;
		}else{
			if(checkTel(apTel)){
				alert("잘못된 전화번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.\n예) 010-XXXX-XXXX");
				$("#applyForm #apTel").focus();
				return;
			}
		}
		
		if(purpose == null || purpose == "" || purpose == "undefined"){
			alert("신청목적을 선택해 주세요.");
			$("#applyForm #purpose").focus();
			return;
		}
		
		if(detailPurpose == null || detailPurpose == "" || detailPurpose == "undefined"){
			alert("상세 신청목적을 선택해 주세요.");
			$("#applyForm #detailPurpose").focus();
			return;
		}
		
		if(secDataMngPlan == null || secDataMngPlan == "" || secDataMngPlan == "undefined"){
			alert("보안 및 자료관리 계획을 입력해 주세요.");
			$("#applyForm #secDataMngPlan").focus();
			return;
		}
		
		if(agreeYn != "Y"){
			alert("신청정보 확인에 체크해 주세요.");
			$("#applyForm #agreeYn").focus();
			return;
		}
		
		
		var formData = new Object();
		var formArray = new Array();
		
		$("#form1 #mapCheckList ul li div").each(function(){
			
				formData = {
								
							"apPost"         : $("#applyForm #apPost").val()
						,	"apDepartment"   : $("#applyForm #apDepartment").val()
						,	"apName"         : $("#applyForm #apName").val()
						,	"apTel"          : $("#applyForm #apTel").val()
						,	"purpose"        : $("#applyForm #purpose option:selected").val()
						,	"detailPurpose"  : $("#applyForm #detailPurpose option:selected").val()
						,	"secDataMngPlan" : $("#applyForm #secDataMngPlan").val()
						,	"agreeYn"        : $("#applyForm #agreeYn:checked").val()
						,	"etc"          	 : $("#applyForm #etc").val()
						,	"mapSerNo"		 : $(this).children("input:hidden[id=chkMapSerNo]").val()
						,	"mapHistoryNo"	 : $(this).children("input:hidden[id=chkMapHistoryNo]").val()
						,	"mapShtNo"		 : $(this).children("input:hidden[id=chkMapShtNo]").val()
				}
				
				formArray.push(formData);
		});
			
		
			$.ajax({
				type : "POST",
				data : JSON.stringify(formArray),
				url : "/niis/originList/insertDigitApply.do",
				async : false,
				processData : false,
				contentType : "application/json",
				cache : false,
				error : function(e){
					alert("신청서 작성 조회 오류입니다. \n 관리자에게 문의하세요.")
				},		
				success : function(data){
					
					alert("신청서가 정상적으로 접수되었습니다.");
					
					$("#applyDialog").dialog("close");
					
					// 신청 완료후 신청 목록 지우기
					$("#form1 #mapCheckList ul li div").each(function(){
						var mapSerNo = $(this).children("input:hidden[id=chkMapSerNo]").val();
						var mapShtNo = $(this).children("input:hidden[id=chkMapShtNo]").val();
						remMapSelLi(mapSerNo + "_" + mapShtNo);
					});
					
					location.reload();
				}
			});
		
	}
	
	
	// 접기 펼치기 이벤트
	$("input[name='listBtn']").click(function(){
		if($(".zoneList").css("display") == "none"){
			$(".zoneList").show();
			$("#listBtn").val("접기");
		}else{
			$(".zoneList").hide();
			$("#listBtn").val("펼치기");
		}
	});
	
	
	
	// 상세목적 selectbox
	function fn_purpose(){
		
		var codeId = $("select[name='purpose'] option:selected").val();
		var formData = { 
							"codeId" : codeId
						};
		
		
			$.ajax({
				type : "POST",
				dataType : "json",
				data : JSON.stringify(formData),
				url : "/niis/originList/getDigitPurposeList.do",
				async : false,
				processData : false,
				contentType : "application/json",
				cache : false,
				error : function(e) {
					alert("등록 오류입니다. 관리자에게 문의하세요.");
				},
				success : function(data) {
					
					$("#detailPurpose").empty();
					$("#detailPurpose").append("<option value=''>선택하세요.</option>");
					$("#detailPurpose").val("").prop("selected", true).change();
					
					var detailPurposeHtml = "";
					
					$.each(data.detailPurposeList, function(index, item){
						detailPurposeHtml += "<option value='"+item.detailCodeId +"'>"+ item.detailCodeNm +"</option>";
					})
					$("#detailPurpose").append(detailPurposeHtml);
				}
				
			});
		
	}
	
	
	
	// 신청서 작성 팝업창 종료
	function closeApplyPop(){
		$("#applyDialog").dialog("close");
	}
</script>

<div class="con">
<form id="applyForm">
	<div class="thead">
		<table>
			<colgroup>
				<col width="16%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
			</colgroup>
			<tbody>
				<tr>
					<th class="essential">신청인 *</th>
					<td colspan="2">
						<input type="text" id="apName" name="apName" readonly="readonly" value="" style="height: 42px; width: 100%;" maxlength="25" />
					</td>
					<th class="essential">연락처 *</th>
					<td colspan="3">
						<input type="text" id="apTel" name="apTel" readonly="readonly" value="" style="height: 42px; width: 100%;" maxlength="15" />
					</td>
				</tr>
				<tr>
					<th class="essential">기관명 *</th>
					<td colspan="6">
						<input id="apPost" name="apPost" type="text" readonly="readonly" value="" style="height: 42px; width: 100%;" maxlength="25" />
					</td>
				</tr>
				<tr>
					<th class="essential">부서명 *</th>
					<td colspan="6">
						<input id="apDepartment" name="apDepartment" type="text" readonly="readonly" value="" style="height: 42px; width: 100%;" maxlength="25" />
					</td>
				</tr>
			</tbody>
		</table>	
		<table>
			<colgroup>
				<col width="16%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
			</colgroup>
			<thead>	
				<tr>
					<th class="essential">신청자료 * &nbsp;&nbsp;<input type="button" id="listBtn" name="listBtn" value="펼치기"/></th>
					<th>지도종류</th>
					<th>축척</th>
					<th>도엽번호</th>
					<th>고시번호</th>
					<th>제작연도</th>
				</tr>
			</thead>	
			<tbody id="digitList">	
				<tr>
					<th class="essential">신청목적 * </br>(데이터의 활용)</th>
					<td colspan="2">
						<select id="purpose" name="purpose" onchange="fn_purpose()" class="select" style="height: 42px;">
							<option value="">선택하세요.</option>
						</select>
					</td>
					<td colspan="2">
						<select id="detailPurpose" name="detailPurpose" class="select" style="height: 42px;">
							<option value="">선택하세요.</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="essential">사용자 준수사항 *</th>
					<td colspan="5">
						<textarea id="secDataMngPlan" name="secDataMngPlan" readonly="readonly" style="height: 100px; width: 100%;">"공간정보의 구축 및 관리 등에 관한 법률", "국토교통부 국가공간정보 보안관리규정", "국토지리정보원 공간정보 제공에 대한 규정"등 관련법령 및 규정의 준수</textarea>
					</td>
				</tr>
				<tr>
					<th>기타사항</th>
					<td colspan="5"><input type="text" id="etc" name="etc" style="height: 70px; width: 100%;" maxlength="50" /></td>
				</tr>
				<tr>
					<th class="essential">메타데이터 *</th>
					<td>
						<select id="metaYn" name="metaYn" class="select">
							<option value="N">미포함</option>
							<option value="Y">포함</option>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	</br>
	<label class="labelTxt">* 필요시 세부내역서 첨부</label>
	</br>
	</br>
	<p>
		<span class="checkbox">
			<input type="checkbox" id="agreeYn" value="Y" />
		</span>
		<label class="labelTxt">신청 정보에 이상이 없음을 확인하며 <span id=""></span>년 <span id="mm"></span>월 <span id="dd"></span>일부로 신청서를 제출합니다.</label>
	</p>
	<div class="btnArea">
		<a href="#none" class="active" onclick="regApply();">신청</a>
		<a href="#none" class="btnLayerClose"  onclick="closeApplyPop();">취소</a>
	</div>
</form>
</div>

 -->