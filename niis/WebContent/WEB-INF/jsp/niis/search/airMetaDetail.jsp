<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tit">
	<h3>항공사진</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="con">
	<div class="totalApplicationTit">
		<h4>메타데이터</h4>
	</div>
	
	<div class="boardStyle_1 mgb15">
		<table>
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
				<tr>
					<th>파일 식별자</th>
					<td><c:out value="${map.fileIdentifier }" default="-" /></td>
					<th>언어</th>
					<td><c:out value="${map.language }" default="-" /></td>
				</tr>
				<tr>
					<th>적용 대상명</th>
					<td colspan="3"><c:out value="${map.targetName }" default="-" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="totalApplicationTit">
		<h4>메타데이터 연락정보</h4>
	</div>
	
	<div class="boardStyle_1 mgb15">
		<table>
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
				<tr>
					<th>책임기관명</th>
					<td><c:out value="${map.resOfficename }" default="-" /></td>
					<th>책임부서명</th>
					<td><c:out value="${map.resPostname }" default="-" /></td>
				</tr>
				<tr>
					<th>전화</th>
					<td><c:out value="${map.resPhone }" default="-" /></td>
					<th>팩스</th>
					<td><c:out value="${map.resFax }" default="-" /></td>
				</tr>
				<tr>
					<th>주소</th>
					<td colspan="3"><c:out value="${map.resAddress }" default="-" /></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td><c:out value="${map.resPostalCode }" default="-" /></td>
					<th>작성일</th>
					<td><c:out value="${map.metaDate }" default="-" /></td>
				</tr>
				<tr>
					<th>버전</th>
					<td colspan="3"><c:out value="${map.metaVersion }" default="-" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="totalApplicationTit">
		<h4>식별</h4>
	</div>
	
	<div class="boardStyle_1 mgb15">
		<table>
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
				<tr>
					<th>사업코드</th>
					<td><c:out value="${map.zoneCode }" default="-" /></td>
					<th>촬영코스번호</th>
					<td><c:out value="${map.phCourse }" default="-" /></td>
				</tr>
				<tr>
					<th>사진번호</th>
					<td><c:out value="${map.phNum }" default="-" /></td>
					<th>촬영일자</th>
					<td><c:out value="${map.phDate }" default="-" /></td>
				</tr>
				<tr>
					<th>촬영축척</th>
					<td><c:out value="${map.scale }" default="-" /></td>
					<th>공간표현방식</th>
					<td><c:out value="${map.spatialData }" default="-" /></td>
				</tr>
				<tr>
					<th>지리정보등급</th>
					<td colspan="3"><c:out value="${map.securityCde }" default="-" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="totalApplicationTit">
		<h4>배포</h4>
	</div>
	
	<div class="boardStyle_1 mgb15">
		<table>
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
				<tr>
					<th>포맷명칭</th>
					<td><c:out value="${map.distFormatName }" default="-" /></td>
					<th>포맷버전</th>
					<td><c:out value="${map.distFormatVersion }" default="-" /></td>
				</tr>
				<tr>
					<th>배포기관명</th>
					<td><c:out value="${map.distOfficename }" default="-" /></td>
					<th>배포부서명</th>
					<td><c:out value="${map.distPostname }" default="-" /></td>
				</tr>
				<tr>
					<th>배포자연락처</th>
					<td colspan="3"><c:out value="${map.distPhone }" default="-" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="totalApplicationTit">
		<h4>연혁</h4>
	</div>
	
	<div class="boardStyle_1 mgb15">
		<table>
			<colgroup>
				<col width="20%">
				<col width="30%">
				<col width="20%">
				<col width="30%">
			</colgroup>
			<tbody>
				<tr>
					<th>해상도</th>
					<td><c:out value="${map.resolution }" default="-" /></td>
					<th>품질적용대상</th>
					<td><c:out value="${map.qualityTarget }" default="-" /></td>
				</tr>
				<tr>
					<th>독취기명</th>
					<td><c:out value="${map.scan }" default="-" /></td>
					<th>매질종류</th>
					<td><c:out value="${map.film }" default="-" /></td>
				</tr>
				<tr>
					<th>평가기준문서</th>
					<td colspan="3"><c:out value="${map.document }" default="-" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>