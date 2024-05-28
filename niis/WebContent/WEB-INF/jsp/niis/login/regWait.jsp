<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>

<div class="tit">
	<h3>가입대기</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="con">
	<p class="txt">현재 가입 신청서가 전달되었으며 승인을 대기중입니다. <span class="block">승인심사는 최대3~5일이 소요됩니다.</span></p>
	<p class="txt">승인이 거절될 경우 로그인을 진행하시면 <span class="block">거절 사유를 확인하실 수 있습니다.</span></p>
	<p class="txt">기타 문의사항은 아래 연락처로 문의 바랍니다.<%@ include file="/WEB-INF/jsp/niis/main/callGuide.jsp" %></p>
	<div class="btnArea">
		<a href="#none" class="active btnLayerClose">확인</a>
	</div>
</div>