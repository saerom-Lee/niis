<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="/niis/css/style.css" />
<script type="text/javascript">
//console.log("테스트");
</script>

	<!-- 이용안내 가이드 9 긴급공간정보 -->
	
		<dl style="left:640px; top:70px;">
			<dt style="width:55px">재난 유형 </dt>
			<dd>재난 유형(산불, 지진 등)을 선택하여 검색할 수 있습니다.</dd>
		</dl>
		<dl style="left:640px; top:110px;">
			<dt style="width:55px">재난 지역</dt>
			<dd>재난 지역(행정구역)을 선택하여 검색할 수 있습니다.</dd>
		</dl>
		<dl style="left:640px; top:190px;">
			<dt style="width:55px">재난 명</dt>
			<dd>재난 명(발생년월일, 지역(동), 재난유형) 키워드를 입력하여 검색할 수 있습니다.<br/><font style="color:#F3F781;">( 입력 예시 : 230413 / 난곡동 / 산불 )</font></dd>
		</dl>
		
		<dl style="left:640px; top:250px;">
			<dt style="width:55px">조회</dt>
			<dd>검색 조건(재난 유형, 재난 지역, 재난 명)에 해당하는 재난 목록을 조회합니다.</dd>
		</dl>
		<dl style="left:640px; top:310px;">
			<dt style="width:55px">재난 목록</dt>
			<dd>조회된 재난 목록에서 재난명 선택 시 재난 정보 및 자료 목록을 확인할 수 있습니다.</dd>
		</dl>
		<dl style="right:20px; bottom:80px">
			<dt>선택 다운로드 </dt>
			<dd>선택하신 재난 자료를 압축 파일로 다운로드 받을 수 있습니다.</dd>
		</dl>
		<div class="close">
			<a href="#none"><img src="../images/common/btn_service_guide_close.png" alt="X" /> 닫기</a>
			<!-- <p><input type="checkbox" id="chClose_7" /><label for="chClose_8">다시 보지 않기</label></p> -->
		</div>

	<!-- 이용안내 가이드 8 수치표고 -->