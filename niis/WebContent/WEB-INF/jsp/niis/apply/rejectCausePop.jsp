<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
	<c:if test="${appChange == 'cfrm' }">
		getMainApply();
		$("#rejStatus" + "<c:out value="${supIdn }" />").text("확인");
	</c:if>
		
	});
	
</script>

<div class="tit">
	<h3>반려</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
</div>
<div class="con">
	<p class="titTxt">승인 심사결과, 승인 처리가 보류되었습니다.</p>
	<p class="reason">${map.rejectCause }</p>
	<p class="txt">
		<span class="block">문의사항은 아래 연락처로 문의 바랍니다.</span>
		<%@ include file="/WEB-INF/jsp/niis/main/callGuide.jsp" %>
	</p>
	<div class="btnArea">
		<a href="#none" class="active btnLayerClose">확인</a>
	</div>
</div>