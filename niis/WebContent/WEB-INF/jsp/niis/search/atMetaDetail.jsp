<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="tit">
	<h3>AT성과</h3>
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
					<th>사업지구코드</th>
					<td><c:out value="${map.zoneCode }" default="-" /></td>
					<th>사업지구명</th>
					<td><c:out value="${map.zoneNam }" default="-" /></td>
				</tr>
				<tr>
					<th>사업연도</th>
					<td><c:out value="${map.zoneYy }" default="-" /></td>
					<th>성과종류</th>
					<td><c:out value="${map.cdeNam }" default="-" /></td>
				</tr>
				<tr>
					<th>프로젝트 종류</th>
					<td><c:out value="${map.projectType }" default="-" /></td>
					<th>프로젝트명</th>
					<td><c:out value="${map.prjNam }" default="-" /></td>
				</tr>
				<tr>
					<th>파일 수</th>
					<td><c:out value="${map.fileCnt }" default="-" /></td>
					<th>파일 사이즈</th>
					<td><c:out value="${map.fileSizeGb } GB" default="-" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
</div>