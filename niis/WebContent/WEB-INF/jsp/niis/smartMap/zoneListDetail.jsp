<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

</script>

<div class="tit">
	<h3>사업지구 상세</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div>
	<div id="detailInfo_1" class="con">
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
						<th>사업명</th>
						<td colspan="3"><c:out value="${zone.prjNam }" default="-" /></td>
					</tr>
					<tr>
						<th>사업년도</th>
						<td><c:out value="${zone.zoneYy }" default="-" /></td>
						<th>사업지구코드</th>
						<td><c:out value="${zone.zoneCode }" default="-" /></td>
					</tr>
					<tr>
						<th>사업지구명</th>
						<td><c:out value="${zone.zoneYy }" default="-" /></td>
						<th>대표축척</th>
						<td><c:out value="${zone.scale }" default="-" /></td>
					</tr>
					<tr>
						<th>공간해상도</th>
						<td><c:out value="${zone.gsdVal }" default="-" /></td>
						<th>사업종류</th>
						<td><c:out value="${zone.projectCde }" default="-" /></td>
					</tr>
					<tr>
						<th>카메라구분</th>
						<td><c:out value="${zone.cameraCde }" default="-" /></td>
						<th>구축구분</th>
						<td><c:out value="${zone.imageCde }" default="-" /></td>
					</tr>
					<tr>
						<th>사업지구설명</th>
						<td colspan="3"><c:out value="${zone.remark }" default="-" /></td>
					</tr>
					<tr>
						<th>수치표고격자크기</th>
						<td><c:out value="${zone.demGrid }" default="-" /></td>
						<th>사업정보관리번호</th>
						<td><c:out value="${zone.prjInfoMgno }" default="-" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>