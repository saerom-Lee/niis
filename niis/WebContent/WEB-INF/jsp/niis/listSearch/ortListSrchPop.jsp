<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	
	$(document).ready(function(){
		
		//상단 체크박스 클릭 이벤트
		$("#ortCfrmHead").on("click", "input[type=checkbox]", function(){
			
			var isChecked = $(this).is(":checked");
			
			$("#ortCfrmList input[type=checkbox]").each(function(i){
				if(isChecked != $(this).is(":checked")){
					$(this).click();
				}
			});
		});
	});
	
	//신청서 작성
	function regApplyPop(){
		
		if($("input[id=cfrmOrt]:checked").length < 1){
			alert("영상을 선택해 주세요.");
			return;
		}
		var imageCde = new Array();
		var zoneCode = new Array();
		var map5000Num = new Array();
		var keyVal3 = new Array();
		
		$("input[id=cfrmOrt]").each(function(){
			if($(this).is(":checked")){
				imageCde.push($(this).parent().children("input:hidden[id=cfrmImageCde]").val());
				zoneCode.push($(this).parent().children("input:hidden[id=cfrmZoneCode]").val());
				map5000Num.push($(this).parent().children("input:hidden[id=cfrmMap5000Num]").val());
				keyVal3.push("");
			}
		});
		
		var data = {
				"imageCde"	: imageCde,
				"keyVal1"	: zoneCode,
				"keyVal2"	: map5000Num,
				"keyVal3"	: keyVal3
		};
		ajaxCallPop("/apply/regApplyPop.do", data, "", "600");
	}
	
</script>
<div class="tit">
	<h3>신청 항목 확인</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="창 닫기" /></a>
</div>
<div class="boardStyle_2">
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<colgroup>
			<col width="45px" />
			<col width="120px" />
			<col width="220px" />
			<col width="120px" />
			<col width="120px" />
			<col width="*" />
		</colgroup>
		<tbody id="ortCfrmHead">
			<tr>
				<th><input type="checkbox" checked/></th>
				<th>사업년도</th>
				<th>사업지구명</th>
				<th>도엽명</th>
				<th>도엽번호</th>
				<th>해상도</th>
			</tr>
		</tbody>
	</table>
</div>
<!-- /.boardStyle_2 -->
<!-- .boardStyle_2 -->
<div class="boardStyle_2" style="max-height: 319px; overflow: auto;">
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<colgroup>
			<col width="45px" />
		<col width="120px" />
		<col width="220px" />
		<col width="120px" />
		<col width="120px" />
		<col width="*" />
		</colgroup>
		<tbody id="ortCfrmList">
			<c:forEach items="${chkOrtList}" var="list">
			<tr>
				<td>
					<input type="checkbox" id="cfrmOrt" name="cfrmOrt" checked />
					<input type="hidden" id="cfrmImageCde" name="cfrmImageCde" value="${list.imageCde}" />
					<input type="hidden" id="cfrmZoneCode" name="cfrmZoneCode" value="${list.zoneCode}" />
					<input type="hidden" id="cfrmMap5000Num" name="cfrmMap5000Num" value="${list.map5000Num}" />
				</td>
				<td>${list.zoneYy}</td>
				<td>${list.zoneNam}</td>
				<td>${list.map5000Nam}</td>
				<td>${list.map5000Num}</td>
				<td>${list.gtypDst}</td> 
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- /.boardStyle_2 -->
<!-- .btnArea -->
<div class="btnArea">
	<a href="#none" class="floatRight btnLayerClose">취소</a>
	<a href="#none" class="floatRight" onclick="regApplyPop();">신청서작성</a>
</div>
<!-- /.btnArea -->