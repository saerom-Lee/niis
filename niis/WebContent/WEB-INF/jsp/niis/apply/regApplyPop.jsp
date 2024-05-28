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
	
	function regApply(){
		
		var apPost         = $("#applyForm #apPost").val();
		var apDepartment   = $("#applyForm #apDepartment").val();
		var apName         = $("#applyForm #apName").val();
		var apTel          = $("#applyForm #apTel").val();
		var purpose        = $("#applyForm #purpose option:selected").val();
		var detailPurpose  = $("#applyForm #detailPurpose option:selected").val();
		var secDataMngPlan = $("#applyForm #secDataMngPlan").val();
		var useYn          = $("#applyForm #useYn:checked").val();
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
		
		if(useYn != "Y"){
			alert("공개제한 공간정보 이용 서약서에 체크해 주세요.");
			$("#applyForm #useYn").focus();
			return;
		}
		
		if(agreeYn != "Y"){
			alert("신청정보 확인에 체크해 주세요.");
			$("#applyForm #agreeYn").focus();
			return;
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
		url = "/apply/regApply.do";					// ApplyController.java
		data = $("#applyForm").serializeArray();
	</c:if>
	<%-- 반려재신청 --%>
	<c:if test="${applyType == 'reject' }">
		url = "/apply/reRegApply.do";
		data = $("#applyForm").serializeObject();
		data.supIdn = "<c:out value="${apply.supIdn }" />";
	</c:if>
	
		ajaxCallJson(url, data, function(result, data){
			if(result != null && result.isSuccess == "1"){
				alert("신청서가 정상적으로 접수되었습니다.");
				//메인화면 신청 내역 갱신
				getMainApply();
				
				//=================추가
				//목록 서치 화면일 경우
				if(typeof appListInit == "function"){
					//신청목록 초기화
					$("#mainDiv form div[id$=CheckList] ul").empty();
					$("#form1 span[id$=AppTotCnt]").text("0");
					
					appListInit();
					
					$("thead[id$=ProductHead] input:checkbox").prop("checked", false);
					$("tbody[id$=ProductList] input:checkbox").prop("checked", false);
					initCheckbox();
				
				//반려 목록
				}else if(typeof getRejApplyList == "function"){
					getRejApplyList()
				}
				//================추가
				
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
	
	$("input[name='listBtn']").click(function(){
		if($(".zoneList").css("display") == "none"){
			$(".zoneList").show();
			$("#listBtn").val("접기");
		}else{
			$(".zoneList").hide();
			$("#listBtn").val("펼치기");
		}
	});
	
	
	
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
				<col width="16%" />
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
			</tbody>
		</table>	
		<table>
			<colgroup>
				<c:if test="${applyType == 'select' }">
					<c:choose>
						<c:when test="${list[0].imageCde eq 'atCde' }">
							<col width="16%" />
							<col width="*%" />
							<col width="*%" />
							<col width="*%" />
						</c:when>
						<c:when test="${list[0].imageCde eq 'PDT001' }">
							<col width="16%" />
							<col width="*%" />
							<col width="*%" />
							<col width="*%" />
						</c:when>
						<c:when test="${list[0].imageCde eq 'PDT008' }">
							<col width="16%" />
							<col width="*%" />
							<col width="*%" />
							<col width="*%" />
							<col width="*%" />
							<col width="*%" />
						</c:when>
						<c:otherwise>
							<col width="16%" />
							<col width="*%" />
							<col width="*%" />
							<col width="*%" />
						</c:otherwise>
					</c:choose>
				</c:if>
				<c:if test="${applyType == 'search' }">
					<col width="16%" />
					<col width="*%" />
					<col width="*%" />
					<col width="*%" />
				</c:if>
			</colgroup>
			<tbody>
				
				<!-- 선택신청  -->
				<c:if test="${applyType == 'select' }">
					<tr>
						<th class="essential">신청자료 * &nbsp;&nbsp;<input type="button" id="listBtn" name="listBtn" value="펼치기"/></th>
						<c:choose>
							<c:when test="${list[0].imageCde eq 'atCde' }">
								<th colspan="3">사업지구명</th>
							</c:when>
							<c:when test="${list[0].imageCde eq 'PDT001' }">
								<th>사업지구명</th>
								<th>코스번호</th>
								<th>사진번호</th>
							</c:when>
							<c:when test="${list[0].imageCde eq 'PDT008' }">
								<th>지도종류</th>
								<th>축척</th>
								<th>도엽번호</th>
								<th>고시번호</th>
								<th>제작연도</th>
							</c:when>
							<c:otherwise>
								<th>사업지구명</th>
								<th>도엽번호</th>
								<th>해상도</th>
							</c:otherwise>
						</c:choose>
					</tr>
					<c:forEach items="${list}" var="list" varStatus="status">
						
						<tr class="zoneList" style="display: none;">
							<th><c:out value="${status.count }"/></th>
							<c:choose>
								<c:when test="${list.imageCde eq 'atCde' }">
									<td colspan="3"><input type="text" id="zoneNam" name="zoneNam" 	value="${list.zoneNam}"  readonly="readonly" 	/></td>
									
									<input type="hidden" 	 id="imageCde" name="imageCde" 	value="${list.imageCde}" />
									<input type="hidden" 	 id="keyVal1"  name="keyVal1" 	value="${list.keyVal1}"  />
								</c:when>
								<c:when test="${list.imageCde eq 'PDT008' }">
									<td><input type="text" id="mapKindNm" 	name="mapKindNm" 	value="${list.mapKindNm}"  	readonly="readonly" 	/></td>
									<td><input type="text" id="scaleNm" 	name="scaleNm" 		value="${list.scaleNm}"  	readonly="readonly" 	/></td>
									<td><input type="text" id="keyVal1" 	name="keyVal1" 		value="${list.keyVal1}"  	readonly="readonly" 	/></td>
									<td><input type="text" id="noticeNo" 	name="noticeNo" 	value="${list.noticeNo}"  	readonly="readonly" 	/></td>
									<td><input type="text" id="productYear" name="productYear" 	value="${list.productYear}" readonly="readonly" 	/></td>
									
									<input type="hidden"   id="imageCde" 	name="imageCde" 	value="${list.imageCde}" />	
									<input type="hidden"   id="keyVal2"		name="keyVal2"		value="${list.keyVal2}"/>
									<input type="hidden"   id="keyVal3"		name="keyVal3"		value="${list.keyVal3}"/>
									
								</c:when>
								<c:otherwise>
									<td><input type="text" id="zoneNam" name="zoneNam" 	value="${list.zoneNam}"  readonly="readonly" 	/></td>
									<td><input type="text" id="keyVal2" name="keyVal2" 	value="${list.keyVal2}"  readonly="readonly" 	/></td>
									<td><input type="text" id="keyVal3" name="keyVal3" 	value="${list.keyVal3}"  readonly="readonly" 	/></td>
								
									<input type="hidden" 	 id="imageCde" name="imageCde" 	value="${list.imageCde}" />
									<input type="hidden" 	 id="keyVal1"  name="keyVal1" 	value="${list.keyVal1}"  />
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:if>
				<!-- 조회항목 : 전체신청 -->
				<c:if test="${applyType == 'search' }">
					<tr>
						<th class="essential">조회 항목</th>
						<th>총</th>
						<c:choose>
							<c:when test="${not empty zoneNam}">
								<th>사업년도</th>
								<th>사업지구명</th>
							</c:when>
							<c:otherwise>
								<th colspan="2">사업년도</th>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th></th>
						<td style="background-color: white; text-align: center;"><c:out value="${cnt }"/> 건</td>
						<c:choose>
							<c:when test="${not empty zoneNam}">
								<td style="background-color: white; text-align: center;">
									<c:out value="${sYear } ~ ${eYear }"/>
								</td>
								<td colspan="1"  style="background-color: white; text-align: center;">
									<c:out value="${zoneNam }"/>
								</td>
							</c:when>
							<c:otherwise>
								<td colspan="2" style="background-color: white; text-align: center;">
									<c:out value="${sYear } ~ ${eYear }"/>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:if>
			</tbody>
		</table>
		<table>
			<colgroup>
				<col width="16%" />
				<col width="*%" />
			</colgroup>
			<tbody>
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
					<td colspan="2">
						<textarea id="secDataMngPlan" name="secDataMngPlan" readonly="readonly" style="height: 100px; width:100%;">"공간정보의 구축 및 관리 등에 관한 법률", "국토교통부 국가공간정보 보안관리규정", "국토지리정보원 공간정보 제공에 대한 규정"등 관련법령 및 규정의 준수</textarea>
					</td>
				</tr>
				<tr>
					<th>기타사항</th>
					<td colspan="2"><input type="text" id="etc" name="etc" style="height: 70px;" maxlength="50" /></td>
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
				<c:if test="${list[0].imageCde eq 'PDT001'}">
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
		<a href="#none" class="active" onclick="regApply();">신청</a>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
</form>
</div>