var myplugin;
var searchaAdmCode = "00";

$(document).ready(function(){
	
	getYearList();
	
	$("#searchCustom li").each(function(index){
		$(this).bind("click", function(){
			$("#searchCustom li").each(function(index){
				if ($(this).hasClass('active')){
					$(this).removeClass('active');	
				}
			});
			if($(this).text() != "지우기"){
				$(this).attr("class", "active");
			}
			
			//search.js
			searchCustomArea($(this).text());
			$("#search_custom_type").val($(this).text());
			
		});
	});
	
	$("#imgView li").each(function(index){
		$(this).bind("click", function(){
			if ($(this).hasClass('active')){
				$(this).removeClass('active');	
			}else{
				$(this).attr("class", "active");
			}
			getZoneList();
		});
	});
	
	$("#poiCodeBtn").click(function(){
		searchPOI();
	});
	
	$("#poiKeyword").bind("keydown", function(){
		if (event.keyCode == 13) { 
			searchPOI(); 
			return false;
		}
	});
	
	$("#areaSido").change(function() {
		var sidoCode = $("#areaSido option:selected").val();
		
		searchaAdmCode = sidoCode;
		getAreaGuList(sidoCode, "areaSigungu");
		getAreaDongList("00", "areaDong");
	});
	
	$("#areaSigungu").change(function() {
		var sigunguCode = $("#areaSigungu option:selected").val();
		
		searchaAdmCode = (sigunguCode == "00") ? $("#areaSido option:selected").val() : sigunguCode;
		getAreaDongList(sigunguCode, "areaDong");
	});
	
	$("#areaDong").change(function() {
		var dongCode = $("#areaDong option:selected").val();
		
		searchaAdmCode = (dongCode == "00") ? $("#areaSigungu option:selected").val() : dongCode;
	});
	
	$("#zipSido").change(function() {
		var sidoCode = $("#zipSido option:selected").val();
		
		getAreaGuList(sidoCode, "zipSigungu");
		getAreaDongList("00", "zipDong");
	});
	
	$("#zipSigungu").change(function() {
		var sigunguCode = $("#zipSigungu option:selected").val();
		
		getAreaDongList(sigunguCode, "zipDong");
	});
	
	
	$("#zipDong").change(function() {
		var sido = $("#zipSido option:selected").text();
		var sig = $("#zipSigungu option:selected").text();
		var emd = $("#zipDong option:selected").text();
		
		var param = { sido : sido, sig: sig, emd : emd};
		
		getAreaLiList(param, "zipLi");
	});
	
	
	$("#jusoSido").change(function() {
		var sidoCode = $("#jusoSido option:selected").val();
		getAreaGuList(sidoCode, "jusoSigungu");
	});
	
	$("#jusoSigungu").change(function() {
		var sidoNm = $("#jusoSido option:selected").text();
		var sggNm = $("#jusoSigungu option:selected").text();
		
		var param = {sidoNm : sidoNm, sggNm : sggNm};
		
		getJusoEmdList(param, "jusoEmd");
		getJusoRnNmList(param, "jusoRoad");
	});
	
	$("#jusoEmd").change(function() {
		var sidoNm = $("#jusoSido option:selected").text();
		var sggNm = $("#jusoSigungu option:selected").text();
		var emdCd = $("#jusoEmd option:selected").val();
		
		var param = {sidoNm : sidoNm, sggNm : sggNm, emdCd : emdCd};
		getJusoRnNmList(param, "jusoRoad");		
	});
	
	
	$("#indexMapScale").change(function(){
		var scale = $("#indexMapScale option:selected").val();
		var param = {scale : scale};
		selectIndexInitName(param, "indexMapInitName");
		
		switch(Number(scale)){
			case 1000:
				rinoGIS.ol.map.getLayerById(201).setVisible(true);
				rinoGIS.ol.map.getLayerById(202).setVisible(false);
				rinoGIS.ol.map.getLayerById(203).setVisible(false);
				rinoGIS.ol.map.getLayerById(204).setVisible(false);
				rinoGIS.ol.map.getLayerById(205).setVisible(false);
				break;
			case 2500:
				rinoGIS.ol.map.getLayerById(201).setVisible(false);
				rinoGIS.ol.map.getLayerById(202).setVisible(true);
				rinoGIS.ol.map.getLayerById(203).setVisible(false);
				rinoGIS.ol.map.getLayerById(204).setVisible(false);
				rinoGIS.ol.map.getLayerById(205).setVisible(false);
				break;
			case 5000:
				rinoGIS.ol.map.getLayerById(201).setVisible(false);
				rinoGIS.ol.map.getLayerById(202).setVisible(false);
				rinoGIS.ol.map.getLayerById(203).setVisible(true);
				rinoGIS.ol.map.getLayerById(204).setVisible(false);
				rinoGIS.ol.map.getLayerById(205).setVisible(false);
				break;
			case 25000:
				rinoGIS.ol.map.getLayerById(201).setVisible(false);
				rinoGIS.ol.map.getLayerById(202).setVisible(false);
				rinoGIS.ol.map.getLayerById(203).setVisible(false);
				rinoGIS.ol.map.getLayerById(204).setVisible(true);
				rinoGIS.ol.map.getLayerById(205).setVisible(false);
				break;
			case 50000:
				rinoGIS.ol.map.getLayerById(201).setVisible(false);
				rinoGIS.ol.map.getLayerById(202).setVisible(false);
				rinoGIS.ol.map.getLayerById(203).setVisible(false);
				rinoGIS.ol.map.getLayerById(204).setVisible(false);
				rinoGIS.ol.map.getLayerById(205).setVisible(true);
				break;
			case 250000:
				break;
			default:
				break;
		}
		
		//레이어카테고리 도구 컨텐츠 다시 불러오기
		rinoGIS.ol.map.getControlById(1019).content();
	});
	
	$("#indexMapInitName").change(function(){
		var scale = $("#indexMapScale option:selected").val();
		var aInit = $("#indexMapInitName option:selected").val();
		var param = {scale : scale , aInit : aInit};
		selectIndexName(param, "indexMapName");
	});
	
	$("#indexMapName").change(function(){
		selectIndexNum();
	});
	
	
	
	
	$("#indexMapNum").change(function() {
		var bounds = [];
		var minx = $("#indexMapName option:selected").attr("minx");
		var miny = $("#indexMapName option:selected").attr("miny");
		var maxx = $("#indexMapName option:selected").attr("maxx");
		var maxy = $("#indexMapName option:selected").attr("maxy");
		var minPoint = transformLonLat(minx, miny, tmm,utmk);
		var maxPoint = transformLonLat(maxx, maxy, tmm,utmk);
		bounds.push(minPoint.x);
		bounds.push(minPoint.y);
		bounds.push(maxPoint.x);
		bounds.push(maxPoint.y);
		
		inexMapGeom = bounds;
	});
	
	getZoneList();
	
	//영역버퍼 세팅
	$("#areaBuffer").slider({
		range: "min",
		min:0, //버퍼 최소값
		max:15000, //버퍼 최대값
		value:7500, //버퍼 새로고침시 값
		slide: function( event, ui ) {
			try{
				$("#amount").val( ui.value );
				changeCircleRadius("amount");
			}catch(e){
				$("#amount").val(7500);
				//throw "drawed buffer is not exist";
				return false;
			}
		}
	});
	$("#amount").val($("#areaBuffer").slider("value"));
	$("#areaBuffer2").slider({
		range: "min",
		min:0, //버퍼 최소값
		max:15000, //버퍼 최대값
		value:7500, //버퍼 새로고침시 값
		slide: function( event, ui ) {
			try{
				$("#amount2").val( ui.value );
				changeCircleRadius("amount2");
			}catch(e){
				$("#amount2").val(7500);
				//throw "drawed buffer is not exist";
				return false;
			}
		}
	});
	$("#amount2").val($("#areaBuffer2").slider("value"));
	$("#areaBuffer3").slider({
		range: "min",
		min:0, //버퍼 최소값
		max:15000, //버퍼 최대값
		value:7500, //버퍼 새로고침시 값
		slide: function( event, ui ) {
			try{
				$("#amount3").val( ui.value );
				changeCircleRadius("amount3");
			}catch(e){
				$("#amount3").val(7500);
				//throw "drawed buffer is not exist";
				return false;
			}
		}
	});
	$("#amount3").val($("#areaBuffer3").slider("value"));
	
});

/**
 * 통합검색 필터 이동버튼 교체
 */
function filterBtnChange(target, src, state){
	if (state == 0){
		$("#"+target).text("");
		$("#"+target).append('<img src="/niis/images/common/loadings.gif" alt="loading" title="loading" style="width:70%;position:relative;top:3px;"/>');
	}else{
		$("#"+target).empty();
		$("#"+target).text(src);
	}
}

/**
 * 임시
 * @param obj
 */
function openMetadataLayer(obj){
	if ($("#combineSearchView").css('display') == 'none'){
		$("#combineSearchView").css('top',$(window).height()/2 - $("#combineSearchView").height()/2);
		$("#combineSearchView").css('left',$(window).width()/2 - $("#combineSearchView").width()/2);
		$("#combineSearchView").fadeIn();
//		$("#combineSearchView").css('z-index',i+1);
//		i++;
	}
}

function openAirMetadataLayer(zoneCode, phCourse, phNum){
	searchAirMetaData(zoneCode, phCourse, phNum);
}

function openOrtMetadataLayer(zoneCode, map5000Num, gtypDst){
	searchOrtMetaData(zoneCode, map5000Num, gtypDst);
}

function openDemMetadataLayer(zoneCode, map5000Num, gridInt){
	searchDemMetaData(zoneCode, map5000Num, gridInt);
}

function openAtMetadataLayer(zoneCode) {
	searchAtMetaData(zoneCode);
}

function clearAreaSelecter(){
	$("#areaSido").val("00");
	$("#areaSigungu").val("00");
	$("#areaDong").val("00");
}

function clearZipAreaSelecter(){
	$("#zipSido").val("00");
	$("#zipSigungu").val("00");
	$("#zipDong").val("00");
}

function clearJusoAreaSelecter(){
	$("#jusoSido").val("00");
	$("#jusoSigungu").val("00");
}

function treeEvent(){
	/*** 트리메뉴 ****/
	$('#combineTreeMenu a.tree').each(function(){
		$this = $(this);
		if ($this.nextAll().is('ul')){
			$this.addClass('treeMi');
		}
	});
	$('#combineTreeMenu a.treeMi').click(function(){
		if ($(this).nextAll('ul').css('display') == 'none'){
			$(this).removeClass('treePl');
			$(this).nextAll('ul').slideDown(200);
		} else {
			$(this).addClass('treePl');
			$(this).nextAll('ul').slideUp(200);
		}
		return false;
	});
	/* 트리 체크박스 */
	$('#combineTreeMenu input:checkbox').click(function(){
		if ($(this).is(":checked")){
			$(this).nextAll('ul').find('input:checkbox').prop("checked", true) ;
		} else {
			$(this).nextAll('ul').find('input:checkbox').prop("checked", false) ;
		}
	});
	/*** 1뎁스 탭 ***/
	$('.tab').each(function(){
		var $this = $(this);
		$this.find('.tabBtn a').click(function(){
			var $index = $(this).parent().index();
			$(this).addClass('active');
			$(this).parent().siblings().find('> a').removeClass('active');
			$this.find('.tabArea > div').removeClass('active').eq($index).addClass('active');
			combineTreeMenuHeight();
		});
	});
	/*** 2뎁스 탭 ***/
	$('.tabBtn2Depth a').click(function(){
		var $index = $(this).parent().index();
		$(this).addClass('active');
		$(this).parent().siblings().find('> a').removeClass('active');
		$('.tabArea2Depth > div').removeClass('active').eq($index).addClass('active');
		combineTreeMenuHeight();
	});
}
