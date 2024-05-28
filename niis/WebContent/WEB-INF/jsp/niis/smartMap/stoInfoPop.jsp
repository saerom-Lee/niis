<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
a.btnDownload {
	display:inline-block;
	padding:5px 10px;
	color:#fff;
	background:#2a8095;
}
</style>
<script type="text/javascript">

$(document).ready(function(){
	stoInfoList();
});

function stoInfoList(){
	ajaxCallJson("/smartMap/getStoInfo.do", "", function(result){
		getStoInfoSelectBox(result, "stoInfo", "0", "stoIdn", "stoDrv", "stoNam");
	});
}

/**
 * selectBox 렌더링
 * ajax로 받아온 데이터를 selectBox에 렌더링한다
 * param	result		: ajax return value
 * 			selId		: rendering할 selectbox의 id값
 * 			kind		: selectbox default값 선택 인자 - 0[없음], 1[전체], 2[선택]
 * 			cdeId		: 쿼리에서 가져온 code alias - 미지정시[cdeCde] 
 * 			cdeNm		: 쿼리에서 가져온 name alias - 미지정시[cdeNam]
 * 			parentId	:  
 */
function getStoInfoSelectBox(result, selId, kind, cdeId, cdeNm, stoNm, parentId){
	
	if(result == null || result == "undefined") return;
	if(selId == null || selId == "") return;
	
	if(kind == null || kind == "") kind = "0";
	
	if(cdeId == null || cdeId == "") cdeId = "cdeCde";
	if(cdeNm == null || cdeNm == "") cdeNm = "cdeNam";
	
	var obj;
	if(parentId != null && parentId != ""){
		obj = $("#" + parentId +" #" + selId);
	}else{
		obj = $("#" + selId);
	}
	
	if (result.list != null){
		obj.empty();
		
		if(kind == "1"){
			obj.append('<option value="">전체</option>');
		}else if(kind == "2"){
			obj.append('<option value="">선택</option>');
		}else if(kind == "3"){
			obj.append('<option value="00">선택</option>');
		}
		
		for (var i = 0; i < result.list.length; i++){
			obj.append('<option value="' + result.list[i][cdeId] + '">' + result.list[i][cdeNm]  + "(" + result.list[i][stoNm] + ")" + '</option>');
		}
		//obj.siblings('.selectTit').text(obj.children("option:selected").text());
		
		if(kind == "0" && result.list.length == 0){
			obj.append('<option value="">선택</option>');
		}
		
		obj.initSelect();
	}
}
 
 
function stoInfoSelect(){
	var stoIdn = $('#stoInfo').val();
	$("#stoIdn").val(stoIdn);
	$('.btnLayerClose').click();
	stoDts();
}
</script>

<div class="tit" >
	<h3>드라이브 선택</h3>
	<a href="#none" class="btnLayerClose"><img src="/niis/images/popup/btn_layer_close.png" alt="" /></a>
</div>
<div id="stoList" style="max-height:600px; background:#fff; overflow-y:auto">
	<div class="tableStyle" style="padding:20px;">
		<table>
			<colgroup>
				<col width="*" />
				<col width="15%" />
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">드라이브 종류</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><select id="stoInfo" class="select" style="width:100%" onchange=""></select></td>
					<td><a href="#none" class="btnDownload" style="text-align:center;" onclick="stoInfoSelect();">확인</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>