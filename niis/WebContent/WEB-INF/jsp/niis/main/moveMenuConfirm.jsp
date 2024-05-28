<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>

<div class="tit">
	<h3>알림</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="취소" title="취소" />
	</a>
</div>
<div class="con">
	<p class="txt">
		다른 메뉴로 이동시 신청하지 않은 목록이 삭제됩니다.
		<span class="block">이동하시겠습니까?</span>
	</p>
	<div class="btnArea">
		<a href="#none" id="btnMoveMenu" class="active">확인</a>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
</div>