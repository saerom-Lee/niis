<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="nowYear"><fmt:formatDate value="${now}" pattern="yyyy" /></c:set>
<c:set var="nowMonth"><fmt:formatDate value="${now}" pattern="MM" /></c:set>
<c:set var="nowDay"><fmt:formatDate value="${now}" pattern="dd" /></c:set>

<style>
	
	#divSubcont input::-webkit-input-placeholder { color:#CCC; }
	#divSubcont input::-moz-placeholder { color:#CCC; }
	#divSubcont input:-moz-placeholder { color:#CCC; }
	#divSubcont input:-ms-input-placeholder { color:#CCC; }
	
/* form Style */
input[type="text"] {
    height: 42px;
    line-height: 34px;
    vertical-align: middle;
    padding: 0 10px;
    border: solid 1px #c2ccd9;
    border-radius: 3px;
    color: #777;
    transition: box-shadow 0.3s;
    box-sizing: border-box;
    box-shadow: 0px 2px 0px rgba(0,0,0,0.07);
    width:100%;
}

</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">

	$(document).on("change","select[name='purpose']",function(){
		var url = "/apply/getPurposeList.do";
		var data = { 
						"codeId" : $("#purpose option:selected").val()
					};
		ajaxCallJson(url, data, function(result){
			
			$("#detailPurpose").empty();
			$("#detailPurpose").append("<option value=''>선택하세요.</option>");
			$("#detailPurpose").val("").prop("selected", true).change();
			
			$.each(result.detailPurposeList, function(index, item){
				$("#detailPurpose").append("<option value='"+item.detailCodeId +"'>"+ item.detailCodeNm +"</option>");
				
			})
			
		});
	});
	
	// 공개제한 공간정보 이용 서약서 체크시 팝업창 띄우기
	$("#useYn").click(function(){
		$("#usePop").css("display", "block");
	})
	
	function closeUseYnPop(){
		$("#usePop").css("display", "none");
	}
</script>

<!-- 공개제한 공간정보 이용 서약서 팝업 레이어 -->
<div id="usePop" class="useLayer" style="display:none">
	<div class="tit">
		<h3>공개제한 공간정보 이용 서약서</h3>
	</div>
	<div class="con">
		<p class="txt">
			본 기관(또는 인) 은 국토지리정보원에서 공급받은 공개제한 공간정보를 사용함에
			있어 다음 사항을 준수하고 영리 목적 사용 및 무단복제, 불법 유출, 해외반출을
			하지 않고 활용목적으로만 사용할 것이며, 만약 이를 위반하였을 경우에는 보안업무
			등 제반 관련 규정에 따라 처벌받음은 물론, 어떠한 제재나 조치도 이의 없이 감수
			할 것을 서약하고 본 서약서를 제출합니다.<br/>
			<br/>
		</p>
		<p class="importantTxt">	
			1. 공급받는 공개제한 공간정보를 당초 목적 외에 다른 목적으로 사용하지 않으며, 다른
			목적으로 사용할 경우 반드시 국토지리정보원장의 사전승인'을 받는다.<br/>
			* 공급 신청과 동일한 방법으로 전자문서로 시행.<br/>
			<br/>
			2 공급받는 공개제한 공간정보를 사용함에 있어 다음 규정을 준수하고 무단복제, 불법
			유출 및 해외반출을 방지하기 위하여 보안관리책임자를 지정 관리한다.<br/>
			. 가 「공간정보의 구축 및 관리 등에 관한 법률」제14조 내지 제21조, 제108조, 제109조 준수<br/>
			. 나 「국가공간정보 기본법」제37조 내지 제41조 준수<br/>
		</p>	
		<p class="importantTxt" style="color: red;">
			＊ 제40조제1호 : 제37조제1항을 위반하여 공간정보 또는 공간정보데이터베이스를
			관리기관의 승인 없이 무단으로 열람·복제·유출한 자는 1년 이하의 징역 또는 1 천만원 이하의 벌금에 처함.<br/>
		</p>
		<p class="importantTxt">	
			. 다 「 국토교통부 국가공간정보 보안관리규정」준수<br/>
			. 라 「 국토지리정보원 공간정보 제공 및 관리에 관한 규정」준수<br/>
		</p>
		<div class="btnArea">
			<a href="#none" class="active" onclick="closeUseYnPop()">확인</a>
		</div>
	</div>
</div>

<div class="tit">
	<h3>공간정보 제공 신청서 (제14조 관련)</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
</div>
<div class="con">
<form id="applyForm">
	<div class="thead">
		<table>
			<colgroup>
				<col width="25%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
			</colgroup>
			<tbody>
				<tr>
					<th class="essential">신청인 *</th>
					<td><input type="text" id="apName" name="apName" readonly="readonly" value="${userinfo.apName }" /></td>
					<th class="essential">연락처 *</th>
					<td><input type="text" id="apTel" name="apTel" readonly="readonly" value="${userinfo.apTel }"  /></td>
				</tr>
				<tr>
					<th class="essential">기관명 *</th>
					<td colspan="3"><input id="apPost" name="apPost" type="text" readonly="readonly" value="${userinfo.apPost }"/></td>
				</tr>
				<tr>
					<th class="essential">부서명 *</th>
					<td colspan="3"><input id="apDepartment" name="apDepartment" type="text" readonly="readonly" value="${userinfo.apDepartment }" /></td>
				</tr>
				<tr>
					<th class="essential">신청자료 *</th>
					<td colspan="3" style="text-align: center;"><c:out value="${applyList }"/></td>
				</tr>
				<tr>
					<th class="essential">신청목적 * (데이터의 활용)</th>
					<td style="width: 160px;">
						<select id="purpose" name="purpose"  class="select" style="height: 42px; width: 150px !important;">
							<option value="">선택하세요.</option>
							<c:forEach items="${purposeList }" var="purposeList">
								<option value="${purposeList.codeId }"><c:out value="${purposeList.cnDc }"/></option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select id="detailPurpose" name="detailPurpose" class="select" style="height: 42px; width: 300px;">
							<option value="">선택하세요.</option>
						</select>
					</td>
				</tr>
				<tr>
					<th class="essential">사용자 준수사항 *</th>
					<td colspan="3">
						<textarea id="secDataMngPlan" name="secDataMngPlan" readonly="readonly" style="height: 100px; width:100%;">"공간정보의 구축 및 관리 등에 관한 법률", "국토교통부 국가공간정보 보안관리규정", "국토지리정보원 공간정보 제공에 대한 규정"등 관련법령 및 규정의 준수</textarea>
					</td>
				</tr>
				<tr>
					<th>기타사항</th>
					<td colspan="3"><input type="text" id="etc" name="etc" style="height: 70px;" maxlength="50" /></td>
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
				<c:if test="${fn:indexOf(applyList, '항공사진') != -1}">
					<tr>
						<th class="essential">AT성과 * (항공사진)</th>
						<td>
							<select id="useAt" name="useAt" class="select">
								<option value="N">미포함</option>
								<option value="Y">포함</option>
							</select>
						</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	</br>
	<label class="labelTxt">* 필요시 세부내역서 첨부</label>
	</br>
	</br>
	<p>
		<span class="checkbox">
			<input type="checkbox" id="useYn" value="Y" />
		</span>
		<label class="labelTxt">공개제한 공간정보 이용 서약서에 동의합니다.</label>
	</p>
	<p>
		<span class="checkbox">
			<input type="checkbox" id="agreeYn" value="Y" />
		</span>
		<label class="labelTxt">신청 정보에 이상이 없음을 확인하며 ${nowYear }년 ${nowMonth }월 ${nowDay }일부로 신청서를 제출합니다.</label>
	</p>
	<div class="btnArea">
		<a href="#none" class="active" onclick="regApplyTree();">신청</a>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
</form>
</div>