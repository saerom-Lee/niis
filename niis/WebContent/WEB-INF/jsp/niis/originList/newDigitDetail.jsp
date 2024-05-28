<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery-ui.min.css" /> --%>

<script type="text/javascript">
	
</script>
<style type="text/css">
	.btnTabArea {background:#fff;height:40px; border-bottom:1px solid #000; padding-top:10px; padding-left:20px; background:#eee;}
	.btnTabArea ul {border-left:1px solid #000; position:relative; top:1px;}
	.btnTabArea ul:after {content:""; display:block; height:0; clear:both;}
	.btnTabArea li {float:left; width:145px;}
	.btnTabArea li.first a {}
	.btnTabArea li a {display:block; border-right:solid 1px #000; height:28px; padding-top:10px; border-bottom:1px solid #000; border-top:1px solid #000; text-align:center; background:#fff; color:#8bacb6;}
	.btnTabArea li a.active {border-bottom:1px solid #000; background:#366674; color:#fff; font-weight:600;}
</style>

<div>
	<div class="btnTabArea">
		<ul>
			<li class="first"><a href="#detailInfo_1" class="active">성과 관리</a></li>
		</ul>
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
							<th>지도분류</th>
							<td colspan="3"  id="dialog_kindNm"></td>
						</tr>
						<tr>
							<th>도엽번호</th>
							<td id="dialog_mapShtNo"></td>
							<th>도엽명</th>
							<td id="dialog_mapShtNm"></td>
						</tr>
						<tr>
							<th>지도유형</th>
							<td id="dialog_mapFormDvsnNm"></td>
							<th>지도종류</th>
							<td id="dialog_mapKindNm"></td>
						</tr>
						<tr>
							<th>축척</th>
							<td id="dialog_scaleNm"></td>
							<th>좌표계</th>
							<td id="dialog_coordDvsnNm"></td>
						</tr>
						<tr>
							<th>공개여부</th>
							<td colspan="3" id="dialog_openDvsnNm"></td>
						</tr>
						<tr>
							<th>고시번호</th>
							<td id="dialog_noticeNo"></td>
							<th>고시일자</th>
							<td id="dialog_noticeDate"></td>
						</tr>
						<tr>
							<th>조사연도</th>
							<td id="dialog_surveyYear"></td>
							<th>제작연도</th>
							<td id="dialog_productYear"></td>
						</tr>
						<tr>
							<th>촬영연도</th>
							<td id="dialog_photoYear"></td>
							<th>매체번호</th>
							<td id="dialog_mediaNo"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>