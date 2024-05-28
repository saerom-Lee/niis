<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="nowYear"><fmt:formatDate value="${now}" pattern="yyyy" /></c:set>
<c:set var="nowMonth"><fmt:formatDate value="${now}" pattern="MM" /></c:set>
<c:set var="nowDay"><fmt:formatDate value="${now}" pattern="dd" /></c:set>

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
		var mgrPosition    = $("#applyForm #mgrPosition").val();
		var mgrName        = $("#applyForm #mgrName").val();
		var mgrTel         = $("#applyForm #mgrTel").val();
		var apPosition     = $("#applyForm #apPosition").val();
		var apName         = $("#applyForm #apName").val();
		var apTel          = $("#applyForm #apTel").val();
		var purpose        = $("#applyForm #purpose").val();
		var secDataMngPlan = $("#applyForm #secDataMngPlan").val();
		var subcontName    = $("#applyForm #subcontName").val();
		var subcontMgrName = $("#applyForm #subcontMgrName").val();
		var subcontMgrTel  = $("#applyForm #subcontMgrTel").val();
		var subcontSrName  = $("#applyForm #subcontSrName").val();
		var subcontSrTel   = $("#applyForm #subcontSrTel").val();
		var agreeYn        = $("#applyForm #agreeYn:checked").val();
		var metaYn         = $("#applyForm #metaYn").val();
		
				
		if(apPost == null || apPost == "" || apPost == "undefined"){
			alert("기관명을 입력해 주세요.");
			$("#applyForm #apPost").focus();
			return;
		}
		
		if(apDepartment == null || apDepartment == "" || apDepartment == "undefined"){
			alert("부서을 입력해 주세요.");
			$("#applyForm #apDepartment").focus();
			return;
		}
		
		if(mgrPosition == null || mgrPosition == "" || mgrPosition == "undefined"){
			alert("관리책임부서장 직위를 입력해 주세요.");
			$("#applyForm #mgrPosition").focus();
			return;
		}
		
		if(mgrName == null || mgrName == "" || mgrName == "undefined"){
			alert("관리책임부서장 성명을 입력해 주세요.");
			$("#applyForm #mgrName").focus();
			return;
		}
		
		if(mgrTel == null || mgrTel == "" || mgrTel == "undefined"){
			alert("관리책임부서장 연락처를 입력해 주세요.");
			$("#applyForm #mgrTel").focus();
			return;
		}else{
			if(checkTel(mgrTel)){
				alert("잘못된 전화번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.\n예) 010-XXXX-XXXX");
				$("#applyForm #mgrTel").focus();
				return;
			}
		}
		
		if(apPosition == null || apPosition == "" || apPosition == "undefined"){
			alert("직위를 입력해 주세요.");
			$("#applyForm #apPosition").focus();
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
			alert("사용목적을 입력해 주세요.");
			$("#applyForm #purpose").focus();
			return;
		}
		
		if(secDataMngPlan == null || secDataMngPlan == "" || secDataMngPlan == "undefined"){
			alert("보안 및 자료관리 계획을 입력해 주세요.");
			$("#applyForm #secDataMngPlan").focus();
			return;
		}
		
		if(apDepartment == null || apDepartment == "" || apDepartment == "undefined"){
			alert("부서을 입력해 주세요.");
			$("#applyForm #apDepartment").focus();
			return;
		}
		
		if(subcontMgrTel == null || subcontMgrTel == "" || subcontMgrTel == "undefined"){
		}else{
			if(checkTel(subcontMgrTel)){
				alert("잘못된 전화번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.\n예) 010-XXXX-XXXX");
				$("#applyForm #subcontMgrTel").focus();
				return;
			}
		}
		
		if(subcontSrTel == null || subcontSrTel == "" || subcontSrTel == "undefined"){
		}else{
			if(checkTel(subcontSrTel)){
				alert("잘못된 전화번호입니다. 숫자, - 를 포함한 숫자만 입력하세요.\n예) 010-XXXX-XXXX");
				$("#applyForm #subcontSrTel").focus();
				return;
			}
		}
		
		if(agreeYn != "Y"){
			alert("신청정보 확인에 체크해 주세요.");
			$("#applyForm #agreeYn").focus();
			return;
		}
		
		if(metaYn != "Y"){
			$("#applyForm #metaYn").val("N");
		}
		
		var url;
		var data;
		
	<%-- 전체신청 --%>
	<c:if test="${applyType == 'search' }">
		url = "/apply/regApplyAll.do";
		data = $("#applyForm").serializeObject();
		$.extend(data, regAllParam);
	</c:if>
	<%-- 선택신청 --%>
	<c:if test="${applyType == 'select' }">
		url = "/apply/regApply.do";	
		data = $("#applyForm").serializeArray();
	</c:if>
		ajaxCallJson(url, data, function(result, data){
			if(result != null && result.isSuccess == "1"){
				alert("신청서가 정상적으로 접수되었습니다.");
				//메인화면 신청 내역 갱신
				getMainApply();
				
				//신청목록 초기화
				$("#mainDiv form div[id$=CheckList] ul").empty();
				$("#form1 span[id$=AppTotCnt]").text("0");
				appListInit();
				
				//신청서 팝업 닫기
				$("#applyForm .btnLayerClose").click();
				
				$("thead[id$=ProductHead] input:checkbox").prop("checked", false);
				$("tbody[id$=ProductList] input:checkbox").prop("checked", false);
				initCheckbox();
			}else{
				alert("신청서 접수에 실패하였습니다.");
			}
		});
	}
	
</script>

<div class="tit">
	<h3>신청서 작성</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
</div>
<div class="con">
<form id="applyForm">
	<%-- 선택신청 --%>
	<c:if test="${applyType == 'select' }">
		<c:forEach items="${list}" var="list">
			<input type="hidden" id="imageCde" name="imageCde" value="${list.imageCde}" />
			<input type="hidden" id="keyVal1" name="keyVal1" value="${list.keyVal1}" />
			<input type="hidden" id="keyVal2" name="keyVal2" value="${list.keyVal2}" />
			<input type="hidden" id="keyVal3" name="keyVal3" value="${list.keyVal3}" />
		</c:forEach>
	</c:if>
	
	<div class="totalApplicationTit">
		<h4>기관명 *
			<input id="apPost" name="apPost" type="text" style="ime-mode:active;" value="${userinfo.apPost }" maxlength="25" />
			<span>부서명 *</span>
			<input id="apDepartment" name="apDepartment" type="text" style="ime-mode:active;" value="${userinfo.apDepartment }" maxlength="25" />
		</h4>
		<!-- <p>서울특별시 XX구 XXX길 342 </p> -->
	</div>

	<!-- .boardStyle_1 -->
	<div class="boardStyle_1 mgb15">
		<table>
			<colgroup>
				<!-- <col width="115px" />
				<col width="*" /> -->
			</colgroup>
			<thead>
				<tr>
					<th></th>
					<th>직위(직급)</th>
					<th>성명</th>
					<th>연락처</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="essential">관리책임부서장 *</th>
					<td><input type="text" id="mgrPosition" name="mgrPosition" style="width:150px;ime-mode:active;" maxlength="25" /></td>
					<td><input type="text" id="mgrName" name="mgrName" style="width:150px;ime-mode:active;" maxlength="10" /></td>
					<td><input type="text" id="mgrTel" name="mgrTel" style="width:150px;ime-mode:disabled;" maxlength="15" /></td>
				</tr>
				<tr>
					<th class="essential">관리담당자 *</th>
					<td><input type="text" id="apPosition" name="apPosition" style="width:150px;ime-mode:active;" value="${userinfo.apPosition }" maxlength="25" /></td>
					<td><input type="text" id="apName" name="apName" style="width:150px;ime-mode:active;"  value="${aUserName }" maxlength="10" /></td>
					<td><input type="text" id="apTel" name="apTel" style="width:150px;ime-mode:disabled;" value="${userinfo.apTel }" maxlength="15" /></td>
				</tr>
				<tr>
					<th class="essential">사용목적 *</th>
					<td colspan="3"><input type="text" id="purpose" name="purpose" style="width:480px;ime-mode:active;" maxlength="50" /></td>
				</tr>
				<!--
				<tr>
					<th>신청지역(주소)</th>
					<td colspan="3">서울시 용산구 이태원 2동 315-1 102호</td>
				</tr>
				<tr>
					<th>사진번호(코스 및 수량)</th>
					<td colspan="3">사진번호(코스 및 수량)</td>
				</tr>
				-->
				<tr>
					<th class="essential">보안 및 자료관리 계획 *</th>
					<td colspan="3">
						<textarea id="secDataMngPlan" name="secDataMngPlan" style="width:470px;height:60px;margin:2px 0px 2px 0px">"국토교통부 국가공간정보 보안관리 규정" 을 준수하여 무단배포, 유출 또는 분실이 없도록 2중 철제 케비넷에 보안관리, 불용 처리시 원본자료 파기'</textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- /.boardStyle_1 -->

	<!-- .boardStyle_1 -->
	<div id="divSubcont" class="boardStyle_1 mgb25">
		<table>
			<colgroup>
				<!-- <col width="115px" />
				<col width="*" /> -->
			</colgroup>
			<thead>
				<tr>
					<th></th>
					<th>용역기관명</th>
					<th>관리책임자 (연락처)</th>
					<th>관리담당자 (연락처)</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>외부용역인 경우 자료관리</th>
					<td><input type="text" id="subcontName" name="subcontName" style="width:150px;ime-mode:active;" maxlength="25" /></td>
					<td><input type="text" id="subcontMgrName" name="subcontMgrName" placeholder="성명" style="width:150px;ime-mode:active;" maxlength="10" /></td>
					<td><input type="text" id="subcontSrName" name="subcontSrName" placeholder="성명" style="width:150px;ime-mode:active;" maxlength="10" /></td>
				</tr>
				<tr>
					<th></th>
					<td></td>
					<td><input type="text" id="subcontMgrTel" name="subcontMgrTel" placeholder="연락처" style="width:150px;ime-mode:disabled;" maxlength="15" /></td>
					<td><input type="text" id="subcontSrTel" name="subcontSrTel" placeholder="연락처" style="width:150px;ime-mode:disabled;" maxlength="15" /></td>
				</tr>
				<tr>
					<th class="essential">메타데이터 *</th>
					<td>
						<select id="metaYn" name="metaYn" class="select">
							<option value="N">미포함</option>
							<option value="Y">포함</option>
						</select>
					</td>
					<td></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- /.boardStyle_1 -->
	<p>
		<span class="checkbox">
			<input type="checkbox" id="agreeYn" value="Y" />
		</span>
		<label class="labelTxt">신청 정보에 이상이 없음을 확인하며 ${nowYear }년 ${nowMonth }월 ${nowDay }일부로 신청서를 제출합니다.</label>
	</p>
	<div class="btnArea">
<c:choose>
	<c:when test="${applyType == 'tree' }">
		<a href="#none" class="active" onclick="regApplyTree();">신청</a>
	</c:when>
	<c:otherwise>
		<a href="#none" class="active" onclick="regApply();">신청</a>
	</c:otherwise>
</c:choose>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
</form>
</div>