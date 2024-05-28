<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tit">
	<h3>정사영상</h3>
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
					<th>책임기관명</th>
					<td><c:out value="${map.resOfficename }" default="-" /></td>
					<th>메타데이터버전</th>
					<td><c:out value="${map.metaVersion }" default="-" /></td>
				</tr>
				<tr>
					<th>배포기관명</th>
					<td><c:out value="${map.distOfficename }" default="-" /></td>
					<th>배포부서명</th>
					<td><c:out value="${map.distPostname }" default="-" /></td>
				</tr>
				<tr>
					<th>배포부서전화</th>
					<td colspan="3"><c:out value="${map.distPhone }" default="-" /></td>
				</tr>
				<tr>
					<th>품질적용대상</th>
					<td><c:out value="${map.qualityTarget }" default="-" /></td>
					<th>배포부서명</th>
					<td><c:out value="${map.document }" default="-" /></td>
				</tr>
				<tr>
					<th>형정구역명</th>
					<td><c:out value="${map.sigunguNam }" default="-" /></td>
					<th>형정구역코드</th>
					<td><c:out value="${map.sigunguCde }" default="-" /></td>
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
					<th>파일식별자</th>
					<td><c:out value="${map.fileIdentifier }" default="-" /></td>
					<th>언어</th>
					<td><c:out value="${map.language }" default="-" /></td>
				</tr>
				<tr>
					<th>축척</th>
					<td><c:out value="${map.scale }" default="-" /></td>
					<th>작업기관</th>
					<td><c:out value="${map.inputCo }" default="-" /></td>
				</tr>
				<tr>
					<th>적용대상명칭</th>
					<td><c:out value="${map.targetName }" default="-" /></td>
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
			</tbody>
		</table>
	</div>
</div>