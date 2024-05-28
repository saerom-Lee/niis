<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
a.btnDownload {
	display:inline-block;
	padding:5px 10px;
	color:#fff;
	background:#2a8095;
}
</style>
<script type="text/javascript">
	
	
	function mapMetaDownload(idx, sn, pdtCd, mapKindNm, scaleNm, mapShtNo, openDvsnNm){
		location.href = "/niis/download/getFileMapMetaList.do?sn="+sn+"&pdtCd="+pdtCd+"&mapKindNm="+encodeURI(mapKindNm,"UTF8")+"&scaleNm="+scaleNm+"&mapShtNo="+mapShtNo+"&openDvsnNm="+encodeURI(openDvsnNm,"UTF8");
		var mapMetaDownload = "mapMetaDownload" + idx
		$("#"+mapMetaDownload).empty();
		$("#"+mapMetaDownload).text("-");
	}
</script>
<form name="downloadFormAir">
	<input type="hidden" name="AIR001" value="" />
</form>
<form name="downloadFormOrt">
</form>
<form name="downloadFormDem">
</form>
<form name="downloadFormAt">
</form>
<form name="downloadFormMap">
</form>
<div class="tit">
	<h3>다운로드 선택</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="" /></a>
</div>
<div id="downloadList" style="max-height:600px; background:#fff; overflow-y:auto">
	<!-- .tableStyle -->
	<div class="tableStyle" style="padding:20px;">
		<table>
			<colgroup>
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="*%" />
				<col width="15%" />
			</colgroup>
			<thead>
				<tr>
					<th>종류</th>
					<th>축척</th>
					<th>도엽번호</th>
					<th>공개여부</th>
					<th>다운</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${!empty getMapMetaList }">
				<c:forEach items="${getMapMetaList }" var="list" varStatus="status">
					<tr id="mapTb">
						<td style="text-align:center"><c:out value="${list.mapKindNm }"/></td>
						<td style="text-align:center"><c:out value="${list.scaleNm }"/></td>
						<td style="text-align:center"><c:out value="${list.mapShtNo }"/></td>
						<td style="text-align:center"><c:out value="${list.openDvsnNm }"/></td>
						<td style="text-align:center" id="mapMetaDownload${status.count }"><a href="#none" class="btnDownload" onclick="mapMetaDownload('${status.count}','${list.sn }', '${list.pdtCd }', '${list.mapKindNm }', '${list.scaleNm }', '${list.mapShtNo }', '${list.openDvsnNm }');">다운로드</a></td> 
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty getMapMetaList }">
				<tr>
					<td style="text-align:center" colspan="5">등록된 표준메타데이터가 없습니다.</td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div>
	<!-- /.tableStyle -->
</div>