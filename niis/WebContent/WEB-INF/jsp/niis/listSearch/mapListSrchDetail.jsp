<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tit">
	<h3>수치지형도</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div id="detailInfo_1" class="con">
	<div class="totalApplicationTit">
		<h4>성과정보 상세보기</h4>
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
					<th>도엽명</th>
					<td><c:out value="${product.mapShtNm }" default="-" /></td>
					<th>도엽번호</th>
					<td><c:out value="${product.mapShtNo }" default="-" /></td>
				</tr>
				<tr>
					<th>지도종류</th>
					<td><c:out value="${product.mapKindNm }" default="-" /></td>
					<th>지도이력번호</th>
					<td><c:out value="${product.mapHistoryNo }" default="-" /></td>
				</tr>
				<tr>
					<th>축척</th>
					<td><c:out value="${product.scaleNm }" default="-" /></td>
					<th>좌표계</th>
					<td><c:out value="${product.coordDvsnNm }" default="-" /></td>
				</tr>
				<tr>
					<th>공개여부</th>
					<td colspan="3"><c:out value="${product.openDvsnNm }" default="-" /></td>
				</tr>
				<tr>
					<th>고시번호</th>
					<td><c:out value="${product.noticeNo }" default="-" /></td>
					<th>고시일자</th>
					<td><c:out value="${product.noticeDate }" default="-" /></td>
				</tr>
				<tr>
					<th>조사연도</th>
					<td><c:out value="${product.surveyYear }" default="-" /></td>
					<th>제작연도</th>
					<td><c:out value="${product.productYear }" default="-" /></td>
				</tr>
				<tr>
					<th>촬영연도</th>
					<td><c:out value="${product.photoYear }" default="-" /></td>
					<th>매체번호</th>
					<td><c:out value="${product.mediaNo }" default="-" /></td>
				</tr>
				<tr>
					<th>등록일자</th>
					<td><c:out value="${product.regDate }" default="-" /></td>
					<th></th>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>