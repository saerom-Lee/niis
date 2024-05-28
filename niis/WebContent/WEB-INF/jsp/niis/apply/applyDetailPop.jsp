<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<style>

	td{
		text-align: center;	
	}
	
</style>
<script type="text/javascript">


	$("input[name='listBtn']").click(function(){
		if($(".zoneList").css("display") == "none"){
			$(".zoneList").show();
			$("#listBtn").val("접기");
		}else{
			$(".zoneList").hide();
			$("#listBtn").val("펼치기");
		}
	});
	
	
</script>

<div class="tit">
	<h3>신청서 상세</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="con">
	 
	<div class="thead">
		<table>
			<tbody>
				<tr>
					<th class="essential">신청인 *</th>
					<td><c:out value="${map.apName }"/></td>
					<th class="essential">연락처 *</th>
					<td><c:out value="${map.apTel }"/></td>
				</tr>
				<tr>
					<th class="essential">기관명 *</th>
					<td colspan="3"><c:out value="${map.apPost }"/></td>
				</tr>
				<tr>
					<th class="essential">부서명 *</th>
					<td colspan="3"><c:out value="${map.apDepartment }"/></td>
				</tr>
				
				<c:set var = "reqName" value = "${reqName}"/>
				<c:choose>
					<%-- 여러종류 신청 --%>
					<%-- 문자열에 ',' 들어갈시 여러종류 신청  --%>
					<c:when test = "${fn:contains(reqName, ',')}">
						<tr>
							<th class="essential">신청자료 *</th>
							<td colspan="3"><c:out value="${reqName }"/></td>
						</tr>
					</c:when>
					
					<c:otherwise>
						<!-- 선택신청  -->
						<c:if test="${not empty applyDetailList }">
							<tr>
								<th class="essential">신청자료 * &nbsp;&nbsp;<input type="button" id="listBtn" name="listBtn" value="펼치기"/></th>
								<c:choose>
									<c:when test="${applyDetailList[0].imageCde eq 'atCde' }">
										<th colspan="3">사업지구명</th>
									</c:when>
									<c:when test="${applyDetailList[0].imageCde eq 'PDT001' }">
										<th>사업지구명</th>
										<th>코스번호</th>
										<th>사진번호</th>
									</c:when>
									<c:when test="${applyDetailList[0].imageCde eq 'PDT008' }">
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
							<c:forEach items="${applyDetailList}" var="list" varStatus="status">
								<tr class="zoneList" style="display: none;">
									<th><c:out value="${status.count }"/></th>
									<c:choose>
										<c:when test="${list.imageCde eq 'atCde' }">
											<td colspan="3"><c:out value="${list.zoneNam}"/></td>
										</c:when>
										<c:when test="${list.imageCde eq 'PDT001' }">
											<td><c:out value="${list.zoneNam}"/></td>
											<td><c:out value="${list.phCourse}"/></td>
											<td><c:out value="${list.phNum}"/></td>
										</c:when>
										<c:when test="${list.imageCde eq 'PDT002' }">
											<td><c:out value="${list.zoneNam}"/></td>
											<td><c:out value="${list.map5000Num}"/></td> 
											<td><c:out value="${list.gridInt}"/></td> 
										</c:when>
										<c:when test="${list.imageCde eq 'PDT004' }">
											<td><c:out value="${list.zoneNam}"/></td>  
											<td><c:out value="${list.map5000Num}"/></td>  
											<td><c:out value="${list.gtypDst}"/></td>  
										</c:when>
										<c:when test="${list.imageCde eq 'PDT008' }">
											<c:choose>
												<c:when test="${list.mapHistoryNo eq '0' }">
													<td><c:out value="${list.infoMapKindNm}"/></td>  
													<td><c:out value="${list.infoScaleNm}"/></td>  
													<td><c:out value="${list.mapShtNo}"/></td>  
													<td><c:out value="${list.infoNoticeNo}"/></td>  
													<td><c:out value="${list.infoProductYear}"/></td>  
												</c:when>
												<c:otherwise>
													<td><c:out value="${list.historyMapKindNm}"/></td>  
													<td><c:out value="${list.historyScaleNm}"/></td>  
													<td><c:out value="${list.mapShtNo}"/></td>  
													<td><c:out value="${list.historyNoticeNo}"/></td>  
													<td><c:out value="${list.historyProductYear}"/></td>  
												</c:otherwise>
											</c:choose>
										</c:when>
									</c:choose>
								</tr>
							</c:forEach>
						</c:if>
					</c:otherwise>
				</c:choose>
				
				<tr>
					<th>신청목적 (상세)</th>
					<td colspan="3">
						<c:out value="${map.purpose } ( ${map.detailPurpose } )"/> 
					
				</tr>
				<tr>
					<th class="essential">사용자 준수사항 *</th>
					<c:choose>
						<c:when test = "${fn:contains(reqName, ',')}">
							<td colspan="3">
						</c:when>
						
						<c:otherwise>
							<c:if test="${not empty applyDetailList }">
								<c:choose>
										<c:when test="${applyDetailList[0].imageCde eq 'atCde' }">
											<td colspan="3">
										</c:when>
										<c:when test="${applyDetailList[0].imageCde eq 'PDT001' }">
											<td colspan="3">
										</c:when>
										<c:when test="${applyDetailList[0].imageCde eq 'PDT008' }">
											<td colspan="5">
										</c:when>
										<c:otherwise>
											<td colspan="3">
										</c:otherwise>
								</c:choose>
							</c:if>		
						</c:otherwise>
					</c:choose>
						<textarea readonly="readonly" style="height: 100px; width: 100%;"><c:out value="${map.secDataMngPlan }"/></textarea>
					</td>
				</tr>
				<tr>
					<th>기타사항</th>
					<td colspan="3"><c:out value="${map.etc }"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- /.boardStyle_1 -->

</div>