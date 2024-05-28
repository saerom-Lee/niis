<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">

	function regApplyPopAll(){
		ajaxCallPop("/apply/regApplyPopAll.do", "", "totalApplication", "700");
	}
	$("#image").attr("src","data:image/png;base64," + '${imgSrc}');
</script>

<div class="tit">
	<h3><c:out value="${imgNm}" /></h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
</div>

<div class="con" style="height:550px;">
	<img id="image" style="width:100%" src=""/>
	<div class="btnArea">
		<a href="#none" class="btnLayerClose">확인</a>
	</div> 
</div>