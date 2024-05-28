<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.btnTabArea {background:#fff;height:40px; border-bottom:1px solid #000; padding-top:10px; padding-left:20px; background:#eee;}
.btnTabArea ul {border-left:1px solid #000; position:relative; top:1px;}
.btnTabArea ul:after {content:""; display:block; height:0; clear:both;}
.btnTabArea li {float:left; width:145px;}
.btnTabArea li.first a {}
.btnTabArea li a {display:block; border-right:solid 1px #000; height:28px; padding-top:10px; border-bottom:1px solid #000; border-top:1px solid #000; text-align:center; background:#fff; color:#8bacb6;}
.btnTabArea li a.active {border-bottom:1px solid #000; background:#366674; color:#fff; font-weight:600;}
</style>
<script type="text/javascript">
	
</script>

<div class="tit">
	<h3>AT성과</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div>
	<div id="detailInfo_1" class="con">
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
						<th>사업지구번호</th>
						<td><c:out value="${product.zoneCode }" default="-" /></td>
						<th>사업지구명</th>
						<td><c:out value="${product.zoneNam }" default="-" /></td>
					</tr>
					<tr>
						<th>사업연도</th>
						<td><c:out value="${product.zoneYy }" default="-" /></td>
						<th>성과종류</th>
						<td><c:out value="${product.cdeNam }" default="-" /></td>
					</tr>
					<tr>
						<th>사업종류</th>
						<td><c:out value="${product.projectType }" default="-" /></td>
						<th>사업명</th>
						<td><c:out value="${product.prjNam }" default="-" /></td>
					</tr>
					<tr>
						<th>파일 수</th>
						<td><c:out value="${product.fileCnt }" default="-" /></td>
						<th>파일 사이즈</th>
						<td><c:out value="${product.fileSizeGb } GB" default="-" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>