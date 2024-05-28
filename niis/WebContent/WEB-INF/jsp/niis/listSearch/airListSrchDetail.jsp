<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.btnTabArea {background:#fff;height:40px; border-bottom:1px solid #000; padding-top:10px; padding-left:20px; background:#eee;}
.btnTabArea ul {border-left:1px solid #000; position:relative; top:1px;}
.btnTabArea ul:after {content:""; display:block; height:0; clear:both;}
.btnTabArea li {float:left; width:145px;}
.btnTabArea li.first a {}
.btnTabArea li a {display:block; border-right:solid 1px #000; height:28px; padding-top:10px; border-bottom:1px solid #000; border-top:1px solid #000; text-align:center; background:#fff; color:#2b5665;}
.btnTabArea li a.active {border-bottom:1px solid #000; background:#366674; color:#fff; font-weight:600;}
</style>
<script type="text/javascript">
	
	$(function(){
		$('.btnTabArea li a').click(function(){
			var href = $(this).attr('href');
			$(href).show();
			$(href).siblings().hide();
			$(this).addClass('active').parent().siblings().find('a').removeClass('active');
			
			var div = $("#commonPop");
			
			var top = ($('html,body').scrollTop()+$(window).height()/2)-($(div).height()/2);
			//var top = ($('html,body').scrollTop()+$('html').height()/2)-($(div).height()/2);
			
			if(top < 0){
				top = 0;
			}
			
			$(div).css('top',top);
			/* 
			$('#mask').fadeTo(60,0.4,function(){
				$(div).fadeIn({queue: false, duration:60});
				$(div).animate({marginTop:30},600);
				$(div).animate({marginTop:0},400);
			});
			 */
			return false;
		});
	});
	
</script>

<div class="tit">
	<h3>항공사진 상세</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="btnTabArea">
	<ul>
		<li class="first"><a href="#detailInfo_1" class="active">성과 관리</a></li>
		<li class="second"><a href="#detailInfo_2">외부표정요소 관리</a></li>
		<li class="last"><a href="#detailInfo_3">메타데이터 관리</a></li>
	</ul>
</div>
<div>
	<div id="detailInfo_1" class="con">
		<div class="totalApplicationTit">
			<h4>항공사진 성과</h4>
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
						<td><c:out value="${product.zoneCode }" default="-" /></td>
						<th>코스번호</th>
						<td><c:out value="${product.phCourse }" default="-" /></td>
					</tr>
					<tr>
						<th>사진번호</th>
						<td><c:out value="${product.phNum }" default="-" /></td>
						<th>사업지구명</th>
						<td><c:out value="${product.zoneNam }" default="-" /></td>
					</tr>
					<tr>
						<th>항공사진 수량</th>
						<td><c:out value="${product.aphNum }" default="-" /></td>
						<th>항공사진 현재보유수량</th>
						<td><c:out value="${product.aphNow }" default="-" /></td>
					</tr>
					<tr>
						<th>양화필름 수량</th>
						<td><c:out value="${product.filmNum }" default="-" /></td>
						<th>양화필름 현재보유 수량</th>
						<td><c:out value="${product.filmNow }" default="-" /></td>
					</tr>
					<tr>
						<th>성과사진 수량</th>
						<td><c:out value="${product.rphNum }" default="-" /></td>
						<th>성과사진 현재보유수량</th>
						<td><c:out value="${product.rphNow }" default="-" /></td>
					</tr>
					<tr>
						<th>미디어1(DVD)</th>
						<td><c:out value="${product.md1Num }" default="-" /></td>
						<th>미디어2(HDD)</th>
						<td><c:out value="${product.md2Num }" default="-" /></td>
					</tr>
					<tr>
						<th>비고</th>
						<td colspan="3"><c:out value="${product.remark }" default="-" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="detailInfo_2" class="con" style="display:none">
		<div class="totalApplicationTit">
			<h4>항공사진 외부표정요소</h4>
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
						<th>X</th>
						<td><c:out value="${eo.eoX }" default="-" /></td>
						<th>Y</th>
						<td><c:out value="${eo.eoY }" default="-" /></td>
					</tr>
					<tr>
						<th>Z</th>
						<td colspan="3"><c:out value="${eo.eoZ }" default="-" /></td>
					</tr>
					<tr>
						<th>Omega</th>
						<td><c:out value="${eo.eoOmega }" default="-" /></td>
						<th>Phi</th>
						<td><c:out value="${eo.eoPhi }" default="-" /></td>
					</tr>
					<tr>
						<th>Kappa</th>
						<td><c:out value="${eo.eoKappa }" default="-" /></td>
						<th>지리좌표계</th>
						<td><c:out value="${eo.crsIdn }" default="-" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="detailInfo_3" class="con" style="display:none">
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
						<td><c:out value="${meta.fileIdentifier }" default="-" /></td>
						<th>언어</th>
						<td><c:out value="${meta.language }" default="-" /></td>
					</tr>
					<tr>
						<th>적용 대상명</th>
						<td colspan="3"><c:out value="${meta.targetName }" default="-" /></td>
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
						<td><c:out value="${meta.resOfficename }" default="-" /></td>
						<th>책임부서명</th>
						<td><c:out value="${meta.resPostname }" default="-" /></td>
					</tr>
					<tr>
						<th>전화</th>
						<td><c:out value="${meta.resPhone }" default="-" /></td>
						<th>팩스</th>
						<td><c:out value="${meta.resFax }" default="-" /></td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3"><c:out value="${meta.resAddress }" default="-" /></td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td><c:out value="${meta.resPostalCode }" default="-" /></td>
						<th>작성일</th>
						<td><c:out value="${meta.metaDate }" default="-" /></td>
					</tr>
					<tr>
						<th>버전</th>
						<td colspan="3"><c:out value="${meta.metaVersion }" default="-" /></td>
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
						<td><c:out value="${meta.zoneCode }" default="-" /></td>
						<th>촬영코스번호</th>
						<td><c:out value="${meta.phCourse }" default="-" /></td>
					</tr>
					<tr>
						<th>사진번호</th>
						<td><c:out value="${meta.phNum }" default="-" /></td>
						<th>촬영일자</th>
						<td><c:out value="${meta.phDate }" default="-" /></td>
					</tr>
					<tr>
						<th>촬영축척</th>
						<td><c:out value="${meta.scale }" default="-" /></td>
						<th>공간표현방식</th>
						<td><c:out value="${meta.spatialData }" default="-" /></td>
					</tr>
					<tr>
						<th>지리정보등급</th>
						<td colspan="3"><c:out value="${meta.securityCde }" default="-" /></td>
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
						<td><c:out value="${meta.distFormatName }" default="-" /></td>
						<th>포맷버전</th>
						<td><c:out value="${meta.distFormatVersion }" default="-" /></td>
					</tr>
					<tr>
						<th>배포기관명</th>
						<td><c:out value="${meta.distOfficename }" default="-" /></td>
						<th>배포부서명</th>
						<td><c:out value="${meta.distPostname }" default="-" /></td>
					</tr>
					<tr>
						<th>배포자연락처</th>
						<td colspan="3"><c:out value="${meta.distPhone }" default="-" /></td>
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
						<td><c:out value="${meta.resolution }" default="-" /></td>
						<th>품질적용대상</th>
						<td><c:out value="${meta.qualityTarget }" default="-" /></td>
					</tr>
					<tr>
						<th>독취기명</th>
						<td><c:out value="${meta.scan }" default="-" /></td>
						<th>매질종류</th>
						<td><c:out value="${meta.film }" default="-" /></td>
					</tr>
					<tr>
						<th>평가기준문서</th>
						<td colspan="3"><c:out value="${meta.document }" default="-" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>