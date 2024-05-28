<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">

	function reappUser(id){
		
		ajaxCallPop("/login/reRegUser.do", {"id" : id}, "", 460);
	}

</script>

<div class="tit">
	<h3>가입보류</h3>
	<a href="#none" class="btnLayerClose">
		<img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" />
	</a>
</div>
<div class="con">
	<p class="titTxt">승인 심사결과, 가입신청이 보류되었습니다.</p>
	<p class="reason">${cause eq null or cause eq 'null' ? '' : cause }</p>
	<p class="txt">
		<span class="block">아래 재신청버튼을 클릭하신 후 신청서를 다시 작성해 주세요.</span>
		<span class="block">기타 문의사항은 아래 연락처로 문의 바랍니다.</span>
		<%@ include file="/WEB-INF/jsp/niis/main/callGuide.jsp" %>
	</p>
	<div class="btnArea">
		<a href="#none" class="active btnLayerClose" onclick="$('#loginWrap .btnJoin a').click();">재신청</a>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
</div>