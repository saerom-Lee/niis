<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tit">
	<h3>수치표고</h3>
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
					<th>파일식별자</th>
					<td><c:out value="${map.fileIdentifier }" default="-" /></td>
					<th>언어</th>
					<td><c:out value="${map.language }" default="-" /></td>
				</tr>
				<tr>
					<th>책임기관명</th>
					<td><c:out value="${map.resOfficename }" default="-" /></td>
					<th>책임부서명</th>
					<td><c:out value="${map.resPostname }" default="-" /></td>
				</tr>
				<tr>
					<th>책임부서전화</th>
					<td><c:out value="${map.resPhone }" default="-" /></td>
					<th>책임부서팩스</th>
					<td><c:out value="${map.resFax }" default="-" /></td>
				</tr>
				<tr>
					<th>책임부서세부주소</th>
					<td><c:out value="${map.resAddress }" default="-" /></td>
					<th>책임부서우편번호</th>
					<td><c:out value="${map.resPostalCode }" default="-" /></td>
				</tr>
				<tr>
					<th>메타데이터작성일</th>
					<td><c:out value="${map.metaDate }" default="-" /></td>
					<th>메타데이터버전</th>
					<td><c:out value="${map.metaVersion }" default="-" /></td>
				</tr>
				<tr>
					<th>배포포맷명칭</th>
					<td><c:out value="${map.distFormatName }" default="-" /></td>
					<th>배포포맷버전</th>
					<td><c:out value="${map.distFormatVersion }" default="-" /></td>
				</tr>
				<tr>
					<th>배포기관명</th>
					<td><c:out value="${map.distOfficename }" default="-" /></td>
					<th>배포부서명</th>
					<td><c:out value="${map.distPostname }" default="-" /></td>
				</tr>
				<tr>
					<th>배포부서전화</th>
					<td><c:out value="${map.distPhone }" default="-" /></td>
					<th>품질적용대상</th>
					<td><c:out value="${map.qualityTarget }" default="-" /></td>
				</tr>
				<tr>
					<th>평가기준문서</th>
					<td><c:out value="${map.document }" default="-" /></td>
					<th>적용대상자료명칭</th>
					<td><c:out value="${map.targetName }" default="-" /></td>
				</tr>
				<tr>
					<th>공간표현방식</th>
					<td><c:out value="${map.spatialData }" default="-" /></td>
					<th>행정구역명</th>
					<td><c:out value="${map.sigunguNam }" default="-" /></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>