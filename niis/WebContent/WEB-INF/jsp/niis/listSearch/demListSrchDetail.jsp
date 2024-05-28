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
	<h3>수치표고 상세</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="btnTabArea">
	<ul>
		<li class="first"><a href="#detailInfo_1" class="active">성과 관리</a></li>
		<li class="second"><a href="#detailInfo_2">메타데이터 관리</a></li>
	</ul>
</div>
<div>
	<div id="detailInfo_1" class="con">
		<div class="totalApplicationTit">
			<h4>수치표고 성과</h4>
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
						<th>사업지구</th>
						<td><c:out value="${product.zoneNam }" default="-" /></td>
						<th>사업년도</th>
						<td><c:out value="${product.zoneYy }" default="-" /></td>
					</tr>
					<tr>
						<th>도엽번호</th>
						<td><c:out value="${product.map5000Num }" default="-" /></td>
						<th>도엽명</th>
						<td><c:out value="${product.map5000Nam }" default="-" /></td>
					</tr>
					<tr>
						<th>원시자료</th>
						<td><c:out value="${product.originData }" default="-" /></td>
						<th>원시자료 획득방법</th>
						<td><c:out value="${product.acqOrigin }" default="-" /></td>
					</tr>
					<tr>
						<th>자료형식</th>
						<td><c:out value="${product.originForm }" default="-" /></td>
						<th>원시자료 제작년도</th>
						<td><c:out value="${product.originYear }" default="-" /></td>
					</tr>
					<tr>
						<th>격자간격</th>
						<td><c:out value="${product.gridInt }" default="-" /></td>
						<th>단위</th>
						<td><c:out value="${product.unit }" default="-" /></td>
					</tr>
					<tr>
						<th>보관방법</th>
						<td colspan="3"><c:out value="${product.interpolMethod }" default="-" /></td>
					</tr>
					<tr>
						<th>좌하단의 평면X좌표</th>
						<td><c:out value="${product.lbX }" default="-" /></td>
						<th>좌하단의 평면Y좌표</th>
						<td><c:out value="${product.lbY }" default="-" /></td>
					</tr>
					<tr>
						<th>우상단의 평면X좌표</th>
						<td><c:out value="${product.rtX }" default="-" /></td>
						<th>우상단의 평면Y좌표</th>
						<td><c:out value="${product.rtY }" default="-" /></td>
					</tr>
					<tr>
						<th>지리좌표계</th>
						<td><c:out value="${product.crsIdn }" default="-" /></td>
						<th>좌표계</th>
						<td><c:out value="${product.coordinate }" default="-" /></td>
					</tr>
					<tr>
						<th>원점구분</th>
						<td><c:out value="${product.origin }" default="-" /></td>
						<th>표고기준</th>
						<td><c:out value="${product.elevNorm }" default="-" /></td>
					</tr>
					<tr>
						<th>높이값종류</th>
						<td><c:out value="${product.heivarKind }" default="-" /></td>
						<th>최고표고</th>
						<td><c:out value="${product.maxElev }" default="-" /></td>
					</tr>
					<tr>
						<th>최저표고</th>
						<td><c:out value="${product.minElev }" default="-" /></td>
						<th>자료기록형식</th>
						<td><c:out value="${product.dateForm }" default="-" /></td>
					</tr>
					<tr>
						<th>정확도</th>
						<td><c:out value="${product.accuracy }" default="-" /></td>
						<th>제작자</th>
						<td><c:out value="${product.producer }" default="-" /></td>
					</tr>
					<tr>
						<th>발행자</th>
						<td><c:out value="${product.publisher }" default="-" /></td>
						<th>참고사항</th>
						<td><c:out value="${product.reference }" default="-" /></td>
					</tr>
					<tr>
						<th>예비</th>
						<td><c:out value="${product.prp }" default="-" /></td>
						<th>지리정보등급</th>
						<td><c:out value="${product.securityCde }" default="-" /></td>
					</tr>
					<tr>
						<th>수치지면자료 보유여부</th>
						<td><c:out value="${product.dtdOx }" default="-" /></td>
						<th>음영기복도 보유유무</th>
						<td><c:out value="${product.shadedImgOx }" default="-" /></td>
					</tr>
					<tr>
						<th>관측기록부유무</th>
						<td><c:out value="${product.obsRgsOx }" default="-" /></td>
						<th>촬영기록부유무</th>
						<td><c:out value="${product.flightPlanOx }" default="-" /></td>
					</tr>
					<tr>
						<th>GPS/INS처리 결과보고서여부</th>
						<td><c:out value="${product.gpsinsOx }" default="-" /></td>
						<th>GPS기준국 점의조서유무</th>
						<td><c:out value="${product.gpsBpOx }" default="-" /></td>
					</tr>
					<tr>
						<th>원시좌하단 평면X좌표</th>
						<td><c:out value="${product.pLbX }" default="-" /></td>
						<th>원시좌하단 평면Y좌표</th>
						<td><c:out value="${product.pLbY }" default="-" /></td>
					</tr>
					<tr>
						<th>원시우상단 평면X좌표</th>
						<td><c:out value="${product.pRtX }" default="-" /></td>
						<th>원시우상단 평면Y좌표</th>
						<td><c:out value="${product.pRtY }" default="-" /></td>
					</tr>
					<tr>
						<th>원시지리좌표계</th>
						<td><c:out value="${product.pCrsIdn }" default="-" /></td>
						<th>구축구분코드</th>
						<td><c:out value="${product.imageCde }" default="-" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="detailInfo_2" class="con" style="display:none">
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
						<td><c:out value="${meta.fileIdentifier }" default="-" /></td>
						<th>언어</th>
						<td><c:out value="${meta.language }" default="-" /></td>
					</tr>
					<tr>
						<th>책임기관명</th>
						<td><c:out value="${meta.resOfficename }" default="-" /></td>
						<th>책임부서명</th>
						<td><c:out value="${meta.resPostname }" default="-" /></td>
					</tr>
					<tr>
						<th>책임부서전화</th>
						<td><c:out value="${meta.resPhone }" default="-" /></td>
						<th>책임부서팩스</th>
						<td><c:out value="${meta.resFax }" default="-" /></td>
					</tr>
					<tr>
						<th>책임부서 세부주소</th>
						<td><c:out value="${meta.resAddress }" default="-" /></td>
						<th>책임부서 우편번호</th>
						<td><c:out value="${meta.resPostalCode }" default="-" /></td>
					</tr>
					<tr>
						<th>메타데이터 작성일</th>
						<td><c:out value="${meta.metaDate }" default="-" /></td>
						<th>메타데이터 버전</th>
						<td><c:out value="${meta.metaVersion }" default="-" /></td>
					</tr>
					<tr>
						<th>배포포맷명칭</th>
						<td><c:out value="${meta.distFormatName }" default="-" /></td>
						<th>배포포맷버전</th>
						<td><c:out value="${meta.distFormatVersion }" default="-" /></td>
					</tr>
					<tr>
						<th>배포기관명</th>
						<td><c:out value="${meta.distOfficename }" default="-" /></td>
						<th>배포부서명</th>
						<td><c:out value="${meta.distPostname }" default="-" /></td>
					</tr>
					<tr>
						<th>배포부서전화</th>
						<td><c:out value="${meta.distPhone }" default="-" /></td>
						<th>품질적용대상</th>
						<td><c:out value="${meta.qualityTarget }" default="-" /></td>
					</tr>
					<tr>
						<th>평가기준문서</th>
						<td><c:out value="${meta.document }" default="-" /></td>
						<th>적용대상자료명칭</th>
						<td><c:out value="${meta.targetName }" default="-" /></td>
					</tr>
					<tr>
						<th>공간표현방식</th>
						<td><c:out value="${meta.spatialData }" default="-" /></td>
						<th>행정구역명</th>
						<td><c:out value="${meta.sigunguNam }" default="-" /></td>
					</tr>
					<tr>
						<th>행정구역코드</th>
						<td colspan="3"><c:out value="${meta.sigunguCde }" default="-" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>