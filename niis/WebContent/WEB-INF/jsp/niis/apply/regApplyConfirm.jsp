<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/layout.js"></script>
<script type="text/javascript">

	function regApplyPopAll(){
		var data = {		"cnt" 	  		: "${cnt}"
						,	"zoneNam" 		: "${zoneNam}"
						,   "sYear"   		: "${sYear}"
						,   "eYear"   		: "${eYear}"
						,   "mapKindCd"   	: "${mapKindCd}"
						,   "scaleCd"   	: "${scaleCd}"
		};

		ajaxCallPop("/apply/regApplyPopAll.do", data, "totalApplication", "700");	// ApplyController.java
	}

</script>

<div class="tit">
	<h3>전체 신청</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="닫기" title="닫기" /></a>
</div>
<c:choose>
	<c:when test="${cnt > 0 }">
<div class="con">
	<p class="txt">
		현재 검색된 ${cnt }건의 자료를 신청하시겠습니까?
	</p>
	<div class="btnArea">
		<a href="#none" class="active btnLayerOpen btnLayerClose" onclick="regApplyPopAll();">신청서 작성</a>
		<a href="#none" class="btnLayerClose">취소</a>
	</div>
</div>
	</c:when>
	<c:otherwise>
<div class="con">
	<p class="txt">
		현재 조건에 검색된 자료가 없습니다.
	</p>
	<div class="btnArea">
		<a href="#none" class="btnLayerClose">확인</a>
	</div>
</div>
	</c:otherwise>
</c:choose>