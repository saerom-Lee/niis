var searchTapType = 0;
var areaTapType = 0;
var indexMapGeom;
//영역버퍼 오브젝트
var amountObj;

var beforeSearchParam = {};

/**
 * 통합검색 - 검색위치 기준 / 지도영역 기준 설정
 * @param type
 */
function setSearchTapType(type){
	searchTapType = type;
}

/**
 * 통합검색 - 검색 기준 설정
 * @param type
 */
function setAreaTabType(){
	areaTapType = $("#searchTabSelect option:selected").index();
	
	//반경 검색 레이어 삭제
	removeCircle();
	removeRadius();
	//사용자역역 삭제
	removeDrawArea();
	//행정구역 삭제
	rinoGIS.ol.map.getLayerById(301).setVisible(false);
	rinoGIS.ol.map.getLayerById(302).setVisible(false);
	rinoGIS.ol.map.getLayerById(303).setVisible(false);
	rinoGIS.ol.map.getLayerById(311).setVisible(false);
	rinoGIS.ol.map.getLayerById(312).setVisible(false);
	rinoGIS.ol.map.getLayerById(313).setVisible(false);
	//인덱스 삭제
	rinoGIS.ol.map.getLayerById(201).setVisible(false);
	rinoGIS.ol.map.getLayerById(202).setVisible(false);
	rinoGIS.ol.map.getLayerById(203).setVisible(false);
	rinoGIS.ol.map.getLayerById(204).setVisible(false);
	rinoGIS.ol.map.getLayerById(205).setVisible(false);
	rinoGIS.ol.map.getLayerById(207).setVisible(false);
	//마커, 영상 삭제
	rinoGIS.ol.map.removeTempOverlay();
	removeAllImages();
	
	
	switch(areaTapType){
	case 1 : 
		amountObj = $("#amount");
		break;
	case 2 :
		amountObj = $("#amount2");
		break;
	case 3 :
		amountObj = $("#amount3");
		break;
	}
}

/**
 * 통합검색 - 행정구역 시도 리스트
 */
function getAreaSidoList(target){

	var tarArr = target.split(",");

	ajaxCallJson("/search/selectSidoList.do", "", function(result){
		
		if(result != null){
			for(var i=0; i<tarArr.length; i++){
				getSelectBox(result, tarArr[i], "3", "admcd", "ctprvn_nm");
			}
		}
	});
}

/**
 * 통합검색 - 행정구역 구 리스트
 * @param sidoCode
 */
function getAreaGuList(sidoCode, target){

	var data = "&srch=" + sidoCode;

	ajaxCallJson("/search/selectSigunguList.do", data, function(result){

		if(result != null){
			getSelectBox(result, target, "3", "admcd", "signgu_nm");
		}
	});
}

/**
 * 통합검색 - 행정구역 동 리스트
 * @param guCode
 */
function getAreaDongList(guCode, target){

	var data = "&srch=" + guCode;

	ajaxCallJson("/search/selectDongList.do", data, function(result){

		if(result != null){
			getSelectBox(result, target, "3", "admcd", "dong_nm");
		}
	});
}

/**
 * 통합검색 - 행정구역 리 리스트
 * @param sido, sig, emd (이름)
 */
function getAreaLiList(param, target){

	ajaxCallJson("/search/selectLiList.do", param, function(result){

		if(result != null){
			getSelectBox(result, target, "3", "BJD_CD", "LI_NM");
			
			setTimeout(function(){
				if($("#zipLi option").length == 2){
					$("#zipLi option:eq(1)").prop("selected",true);
				}
			},2000);
		}
	});
}

/**
	통합검색 - 지번 검색 (2023년 신규)
 */
function getJibunList(){
	var mntnYn = "1";
	var dongCode = "";
	
	var zipSido    = $("#zipSido").val();
	var zipSigungu = $("#zipSigungu").val();
	var zipDong    = $("#zipDong").val();
	var zipLi    = $("#zipLi").val();
	
	if(zipSido == "00" || zipSido.length < 2){
		alert("시도를 선택해주세요!");
		$("#zipSido").focus();
		return;
	}
	
	if(zipSigungu == "00" || zipSigungu.length < 5){
		alert("시군구를 선택해주세요!");
		$("#zipSigungu").focus();
		return;
	}
	
	if(zipDong == "00" || zipDong.length < 8){
		alert("읍면동을 선택해주세요!");
		$("#zipDong").focus();
		return;
	}
	
	if(zipLi == "00" || zipLi.length < 10){
		alert("리가 없으면 없음 선택해주세요.");
		$("#zipDong").focus();
		return;
	}

	if($("#mountYn").is(":checked")){
		mntnYn = "2";
	}
	filterBtnChange("zipCodeBtn", "", 0);
	
	
	var data = {
		"sido" 			: zipSido,
		"liCd"			: zipLi,
		"mntnYn"		: mntnYn,
		"lnbrMnnm"		: $("#lnbrMnnm").val(),
		"lnbrSlno"		: $("#lnbrSlno").val(),
	};
	
	ajaxCallJson("/search/map/selectJibunList.do", data, function(result){
		
		if(result != null){
			$("#zipCodeList").mCustomScrollbar("destroy");
			$("#zipCodeList").empty();
			$("#zipCodeCnt").text(result.length);
			for(var i=0; i<result.length; i++){

				//var zip      = result.result[i]["ZIP"];
				var pointX   = result[i]["point_x"];
				var pointY   = result[i]["point_y"];
				var JIBUN 	 = result[i]["jibun"];
				var text = JIBUN;
				
				//좌표 변환 (utmk -> 경위도)
				var point = rinoGIS.ol.map.transform([pointX, pointY], 'EPSG:5179','EPSG:4326');
				
				//$("#zipCodeList").append("<li><a href=\"#none\" onclick=\"searchOfDistanse('"+lnbrMnnm+"','"+point.x+"','"+point.y+"', 0, 'amount2')\">"+text+"</a></li>");
				$("#zipCodeList").append("<li><a href=\"#none\" onclick=\"searchOfDistanse('"+text+"','"+point[0]+"','"+point[1]+"', 0, 'amount2')\">"+text+"</a></li>");
			}
			$("#zipCodeList").children(":last").addClass("last");
			$("#zipCodeList").mCustomScrollbar();
		}
		filterBtnChange("zipCodeBtn", "검색", 1);
	}, function(result) {
		window.console.log('지번 리스트 검색 실패!');
		filterBtnChange("zipCodeBtn", "검색", 1);
	});
	
}


/**
 * 통합검색 - 지번 검색
 */
function getZipCodeList(){
	
	var mntnYn = "0";
	var dongCode = "";
	
	var zipSido    = $("#zipSido").val();
	var zipSigungu = $("#zipSigungu").val();
	var zipDong    = $("#zipDong").val()
	
	if(zipSido == "00" || zipSido.length < 2){
		alert("행정구역을 선택해주세요!");
		$("#zipSido").focus();
		return;
	}
	
	if(zipSigungu == "00" || zipSigungu.length < 5){
		alert("행정구역을 선택해주세요!");
		$("#zipSigungu").focus();
		return;
	}
	
	if(zipDong == "00" || zipDong.length < 8){
		alert("행정구역을 선택해주세요!");
		$("#zipDong").focus();
		return;
	}

	dongCode = zipDong.substring(5, 8);

	if($("#mountYn").is(":checked")){
		mntnYn = "1";
	}
	filterBtnChange("zipCodeBtn", "", 0);

	var data = {
		"sigunguCode"	: zipSigungu,
		"mntnYn"		: mntnYn,
		"lnbrMnnm"		: $("#lnbrMnnm").val(),
		"lnbrSlno"		: $("#lnbrSlno").val(),
		"emdCd"			: dongCode
	}

	ajaxCallJson("/search/getZipCodeList.do", data, function(result){
		
		if(result != null){
			$("#zipCodeList").mCustomScrollbar("destroy");
			$("#zipCodeList").empty();
			$("#zipCodeCnt").text(result.result.length);
			for(var i=0; i<result.result.length; i++){

				//var zip      = result.result[i]["ZIP"];
				var pointX   = result.result[i]["POINT_X"];
				var pointY   = result.result[i]["POINT_Y"];
				var lnbrMnnm = result.result[i]["LNBR_MNNM"];
				var lnbrSlno = result.result[i]["LNBR_SLNO"];
				var dongNm   = result.result[i]["DONG_NM"];
				var mntnYn   = result.result[i]["MNTN_YN"];
				var JIBUN 	 = result.result[i]["JIBUN"];
				
				var text = dongNm + " " + JIBUN + " " + mntnYn + lnbrMnnm + ((lnbrSlno == "0") ? "" : " - " + lnbrSlno);
				var point = transformLonLat(pointX, pointY, bessel, utmk);

				
				//$("#zipCodeList").append("<li><a href=\"#none\" onclick=\"searchOfDistanse('"+lnbrMnnm+"','"+point.x+"','"+point.y+"', 0, 'amount2')\">"+text+"</a></li>");
				$("#zipCodeList").append("<li><a href=\"#none\" onclick=\"searchOfDistanse('"+lnbrMnnm+"','"+pointX+"','"+pointY+"', 0, 'amount2')\">"+text+"</a></li>");
			}
			$("#zipCodeList").children(":last").addClass("last");
			$("#zipCodeList").mCustomScrollbar();
		}
		filterBtnChange("zipCodeBtn", "검색", 1);
	}, function(result) {
		window.console.log('지번 리스트 검색 실패!');
		filterBtnChange("zipCodeBtn", "검색", 1);
	});
}

/**
 * 통합검색 - 도로명 읍면 리스트
 * @param sido, sig (이름)
 */
function getJusoEmdList(param, target){

	ajaxCallJson("/search/map/juso/selectEmdList.do", param, function(result){

		if(result != null){
			getSelectBox(result, target, "2", "EMD_CD", "EMD_NM");
		}
	});
}


/**
 * 통합검색 - 도로명 읍면 리스트
 * @param sido, sig (이름)
 */
function getJusoRnNmList(param, target){

	ajaxCallJson("/search/map/juso/selectRnNmList.do", param, function(result){

		if(result != null){
			getSelectBox(result, target, "2", "RN_CD", "RN_NM");
		}
	});
}


/**
	통합검색 - 도로명 검색 (2023년 신규)
 */
function getJusoList(){
	var sidoCode = $("#jusoSido option:selected").val();
	var sigunguCode = $("#jusoSigungu option:selected").val();
	
	var sidoNm = $("#jusoSido option:selected").text();
	var sggNm = $("#jusoSigungu option:selected").text();
	//alert(sggNm);
	//sggNm = sggNm.split(" ")[0];
	
//	alert(sggNm);
	var rnNm = $("#jusoRoad option:selected").text();
	var rnCd = $("#jusoRoad option:selected").val();
	
	if(sidoCode == "00" || sidoCode.length < 2){
		alert("시도 선택해주세요!");
		$("#jusoSido").focus();
		return;
	}
	
	if(sigunguCode == "00" || sigunguCode.length < 5){
		alert("시군구를 선택해주세요!");
		$("#jusoSigungu").focus();
		return;
	}
	
	if(rnCd == "" || rnCd == "00" || rnCd.length < 7){
		alert("도로명을 선택해주세요!");
		$("#jusoRoad").focus();
		return;
	}

	filterBtnChange("buldCodeBtn", "", 0);
	
	var data = {
			"rnCd"			: rnCd,
			"sidoNm"		: sidoNm,
			"sggNm"			: sggNm,
			"rnNm"			: rnNm,
			"buldMnnm"		: $("#buldMnnm").val(),
			"buldSlno"		: $("#buldSlno").val()
	};
	

	ajaxCallJson("/search/map/selectJusoList.do", data, function(result){
		
		if(result != null){
			$("#buldCodeList").mCustomScrollbar("destroy");
			$("#buldCodeList").empty();
			$("#buldCodeCnt").text(result.length);
			for(var i=0; i<result.length; i++){
				
				var buldNm = result[i]["buld_nm"];
				var pointX   = result[i]["point_x"];
				var pointY   = result[i]["point_y"];
				var buldMnnm = result[i]["buld_mnnm"];
				var buldSlno = result[i]["buld_slno"];
				var emdNm   = result[i]["emd_nm"];
				
				var text = buldMnnm + ((buldSlno == "0") ? "" : " - " + buldSlno) + (emdNm ? "( " + emdNm + (buldNm ? "," + buldNm : "") + ")" : "");
				
				//좌표 변환 (utmk -> 경위도)
				var point = rinoGIS.ol.map.transform([pointX, pointY], 'EPSG:5179','EPSG:4326');
				
				$("#buldCodeList").append("<li><a href=\"#none\" onclick=\"searchOfDistanse('"+buldMnnm+"','"+point[0]+"','"+point[1]+"', 0, 'amount3')\">"+text+"</a></li>");
			}
			$("#buldCodeList").children(":last").addClass("last");
			$("#buldCodeList").mCustomScrollbar();
		}
		filterBtnChange("buldCodeBtn", "검색", 1);
	}, function(result) {
		window.console.log('도로명 리스트 검색 실패!');
		filterBtnChange("buldCodeBtn", "검색", 1);
	});
	
}


/**
 * 통합검색 - 새주소 검색
 */
function getJusoCodeList(guCode, target){

	var data = {
			"sigunguCode"	: guCode
	}

	ajaxCallJson("/search/getJusoList.do", data, function(result){
		
		if(result != null){
			getSelectBox(result, target, "2", "RN_CD", "RN");
		}
	});
}

/**
 * 통합검색 - 새주소(건물번호)
 */
function getBludCodeList(){
	
	var jusoSido    = $("#jusoSido").val();
	var sigunguCode = $("#jusoSigungu").val();
	var juso_nm     = $("#juso_nm").val()
	
	if(jusoSido == "00" || jusoSido.length < 2){
		alert("행정구역을 선택해주세요!");
		$("#jusoSido").focus();
		return;
	}
	
	if(sigunguCode == "00" || sigunguCode.length < 5){
		alert("행정구역을 선택해주세요!");
		$("#jusoSigungu").focus();
		return;
	}
	
	if(juso_nm == "00" || juso_nm.length < 7){
		alert("행정구역을 선택해주세요!");
		$("#juso_nm").focus();
		return;
	}

	filterBtnChange("buldCodeBtn", "", 0);
	
	var data = {
			"sigunguCode"	: sigunguCode,
			"rnCd"			: juso_nm,
			"lnbrMnnm"		: $("#buldMnnm").val(),
			"lnbrSlno"		: $("#buldSlno").val()
	}

	ajaxCallJson("/search/getBuldNoList.do", data, function(result){
		
		if(result != null){
			$("#buldCodeList").mCustomScrollbar("destroy");
			$("#buldCodeList").empty();
			$("#buldCodeCnt").text(result.result.length);
			for(var i=0; i<result.result.length; i++){
				
				var buldMnnm = result.result[i]["BULD_MNNM"];
				var pointX   = result.result[i]["POINT_X"];
				var pointY   = result.result[i]["POINT_Y"];
				var buldMnnm = result.result[i]["BULD_MNNM"];
				var buldSlno = result.result[i]["BULD_SLNO"];
				var dongNm   = result.result[i]["DONG_NM"];
				
				var text = buldMnnm + ((buldSlno == "0") ? "" : " - " + buldSlno) + " (" + dongNm + ")";
				
				$("#buldCodeList").append("<li><a href=\"#none\" onclick=\"searchOfDistanse('"+buldMnnm+"','"+pointX+"','"+pointY+"', 0, 'amount3')\">"+text+"</a></li>");
			}
			$("#buldCodeList").children(":last").addClass("last");
			$("#buldCodeList").mCustomScrollbar();
		}
		filterBtnChange("buldCodeBtn", "검색", 1);
	}, function(result) {
		window.console.log('지번 리스트 검색 실패!');
		filterBtnChange("buldCodeBtn", "검색", 1);
	});
}

/**
 * 통합검색 - 행정구역 경계
 * @param admcd
 */
function addressArea(admcd){
	var bjcd = admcd;
	
	var filterParams = {
		'FILTER': null,
		'CQL_FILTER': null,
		'FEATUREID': null
	};
	
			
	if (admcd.length == 2){
		filterParams["CQL_FILTER"] = "ctprvn_cd like '" + bjcd + "%'";
		rinoGIS.ol.map.getLayerById(301).getSource().updateParams(filterParams);
		rinoGIS.ol.map.getLayerById(301).setVisible(true);
		rinoGIS.ol.map.getLayerById(302).setVisible(false);
		rinoGIS.ol.map.getLayerById(303).setVisible(false);
		rinoGIS.ol.map.getLayerById(311).setVisible(false);
		rinoGIS.ol.map.getLayerById(312).setVisible(false);
		rinoGIS.ol.map.getLayerById(313).setVisible(false);
		
		rinoGIS.ol.map.getLayerById(311).query({
			viewParams: {
				ctprvnCd : bjcd
			}
		}).then(function(ext) {
			rinoGIS.ol.map.getLayerById(311).setVisible(true);
			rinoGIS.ol.map.move(ext, 1.5);
		});
	}else if (admcd.length == 5){
		filterParams["CQL_FILTER"] = "sig_cd like '" + bjcd + "%'";
		rinoGIS.ol.map.getLayerById(302).getSource().updateParams(filterParams);
		rinoGIS.ol.map.getLayerById(301).setVisible(false);
		rinoGIS.ol.map.getLayerById(302).setVisible(true);
		rinoGIS.ol.map.getLayerById(303).setVisible(false);
		rinoGIS.ol.map.getLayerById(311).setVisible(false);
		rinoGIS.ol.map.getLayerById(312).setVisible(false);
		rinoGIS.ol.map.getLayerById(313).setVisible(false);
		
		rinoGIS.ol.map.getLayerById(312).query({
			viewParams: {
				sigCd : bjcd
			}
		}).then(function(ext) {
			rinoGIS.ol.map.getLayerById(312).setVisible(true);
			rinoGIS.ol.map.move(ext, 1.5);
		});
	}else{
		filterParams["CQL_FILTER"] = "emd_cd like '" + bjcd + "%'";
		rinoGIS.ol.map.getLayerById(303).getSource().updateParams(filterParams);
		rinoGIS.ol.map.getLayerById(301).setVisible(false);
		rinoGIS.ol.map.getLayerById(302).setVisible(false);
		rinoGIS.ol.map.getLayerById(303).setVisible(true);
		rinoGIS.ol.map.getLayerById(311).setVisible(false);
		rinoGIS.ol.map.getLayerById(312).setVisible(false);
		rinoGIS.ol.map.getLayerById(313).setVisible(false);
		
		rinoGIS.ol.map.getLayerById(313).query({
			viewParams: {
				emdCd : bjcd
			}
		}).then(function(ext) {
			rinoGIS.ol.map.getLayerById(313).setVisible(true);
			rinoGIS.ol.map.move(ext, 1.5);
		});
	}
	
}

/**
 * 통합검색 행정구역 이동
 */
function moveToArea(){
	if (searchaAdmCode == "00"){
		alert("행정구역을 선택해주세요!");
		return;
	}else{
		addressArea(searchaAdmCode);
	}
}


function selectIndexScale(){
	
	ajaxCallJson("/search/map/selectIndexScale.do", null, function(result){
		if(result != null){
			getSelectBox(result, "indexMapScale", "3", "SCALE", "SCALE_NM");
		}
	});
}


function selectIndexInitName(param, target){
	ajaxCallJson("/search/map/selectIndexInitName.do", param, function(result){
		if(result != null){
			getSelectBox(result, target, "3", "A_INIT", "INIT");
		}
	});
}


function selectIndexName(param, target){
	ajaxCallJson("/search/map/selectIndexName.do", param, function(result){
		if(result != null){
			getSelectBox(result, target, "3", "NAME", "NAME");
		}
	});
}

function selectIndexNum(){
	$("#indexMapNum").empty();
	$("#indexMapNum").append("<option value=\"00\">선택</option>");
	$("#indexMapNum2").val("");
	
	var scale = $("#indexMapScale option:selected").val();
	var name = $("#indexMapName option:selected").val();
	
	if(scale != "00" && name != "00"){
		$("#indexMapNum").empty();
		var num = "<option value=\"00\">선택하세요</option>";
		
		$.getJSON("/niis/search/map/selectIndexNum.do",{scale: scale, name : name}, function(data){
			$.each(data.list, function (i, item){
				num += "<option value=\""+item.XMIN+","+item.YMIN+","+item.XMAX+","+item.YMAX+"\">"+item.NUM+"</option>";
			});
			$("#indexMapNum").append(num);
		});	
	}
}

function getIndexMap(){
	var numVal = $("#indexMapNum").val();
	var numVal2 = $("#indexMapNum2").val();
	var coord = (numVal != "00" ? numVal.split(",") : null);
	
	
	//select에서 도엽을 선택 했을 경우
	if(coord != null){
		$("#search_index_result_num").val(numVal);
		rinoGIS.ol.map.move([Number(coord[0]),Number(coord[1]),Number(coord[2]),Number(coord[3])]);
		
		//minx, miny, maxx, maxy
		var bounds = [Number(coord[0]),Number(coord[1]),Number(coord[2]),Number(coord[3])];	
		indexMapGeom = bounds;
	}
	//직접 도엽번호를 입력 했을 경우
	if(numVal2 != null && numVal2 != "" && numVal2 != undefined){
		$("#search_index_result_num").val(numVal2);
		$.getJSON("/niis/search/map/selectIndexNum2.do",{num : numVal2}, function(data){
			if(data.list.length > 0){
				$.each(data.list, function (i, item){
					var scale = Number(item.SCALE);
					switch(scale){
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
					
					rinoGIS.ol.map.move([Number(item.XMIN),Number(item.YMIN),Number(item.XMAX),Number(item.YMAX)]);
					
					var bounds = [Number(item.XMIN),Number(item.YMIN),Number(item.XMAX),Number(item.YMAX)];	
					indexMapGeom = bounds;
				});	
				
				
			}
			else{
				alert('검색 결과가 없습니다.');
			}
						
			
		});
		
	}
}

function searchCustomArea(type){
	
	if (type == "원") {
		rinoGIS.ol.map.addInteractionDraw('Circle', false);
		//dispatch는 initialize에 정의
	}
	else if (type == "사각형") {
		rinoGIS.ol.map.addInteractionDraw('Circle', true);
	}
	else if (type == "다각형") {
		rinoGIS.ol.map.addInteractionDraw('Polygon', false);
	}
	else if (type == "지우기") {
		removeDrawArea();
	}
}

function completeDrawArea(){
	//그리기가 완료 되면 wkt 형식으로 변환한다.
	rinoGIS.EventBus.on(rinoGIS.ol.type.DRAW_GEOMETRY, function(e) {

		if ($("#search_custom_type").val() != "원" && $("#search_custom_type").val() != "지우기") {
			var wktTxt = e.wkt.writeGeometry(e.feature.getGeometry());
			$("#search_custom_wkt").val(wktTxt);
		}
		else {
			//원을 wkt 형식으로 변환한다.
			var circlePolygon = new ol.geom.Polygon.fromCircle(e.feature.getGeometry());
			var wkt = new ol.format.WKT()
			var wktTxt = wkt.writeGeometry(circlePolygon);

			$("#search_custom_wkt").val(wktTxt);

		}

		var ext = e.extent;
		rinoGIS.ol.map.getView().fit(ext, 2);
	});
}

function removeDrawArea(){
	rinoGIS.ol.map.getLayers().forEach((lyr) => {			
		if(lyr.get("layerId") && lyr.get("layerId") == "drawBoxLayer"){
			rinoGIS.ol.map.removeLayer(lyr);
		}
	});
	$("#search_custom_wkt").val("");
	$("#search_custom_type").val("");
}

/**
 * 영상종류 선택 정보 반환
 * @returns {String}
 */
function getSelecteImgType(){
	var temp = [];
	var tempSrc = "";
	var i = 0;
	$("#imgView li").each(function(index){
		if($(this).hasClass('active')){
			temp[i++] = index;
		}
	});
	
	if(temp.length > 0){
		for (var a=0; a<temp.length; a++){
			if(a == temp.length - 1){
				tempSrc += temp[a];
			}else{
				tempSrc += temp[a] + ",";
			}
		}
	}

	return tempSrc;
}

/**
 * 촬영년도 반환 (검색범위-시작)
 * @returns {String}
 */
function getStartYear(){
	return $("#sSearchYear option:selected").val();
}

/**
 * 촬영년도 반환 (검색범위-종료)
 * @returns {String}
 */
function getEndYear(){
	return $("#eSearchYear option:selected").val();	
}

/**
 * 사업지구 리스트 검색
 * @param 영상타입
 */
function getZoneList() {

	var imgTypeObj = getSelecteImgType();
	
	//수치지형도만 선택할 경우 검색년도,사업지구 선택 disabled
	if(imgTypeObj == '4') {
		$("#sSearchYear").attr("disabled", true);
		$("#eSearchYear").attr("disabled", true);
		$("#zoneList").attr("disabled", true);
		
		//.selectWrap .selectTit.disabled
		$("#sSearchYear").prev().addClass("disabled");
		$("#eSearchYear").prev().addClass("disabled");
		$("#zoneList").prev().addClass("disabled");
		
	} else {
		$("#sSearchYear").attr("disabled", false);
		$("#eSearchYear").attr("disabled", false);
		$("#zoneList").attr("disabled", false);
		
		//.selectWrap .selectTit
		$("#sSearchYear").prev().removeClass("disabled");
		$("#eSearchYear").prev().removeClass("disabled");
		$("#zoneList").prev().removeClass("disabled");
	}
	
	//검색결과 Root 설정
	setTreeRootItemControll(imgTypeObj);
	
	var data = {
		"imgType"	: imgTypeObj,
		"sYear"		: getStartYear(),
		"eYear"		: getEndYear()
	}
	
	ajaxCallJson("/search/zoneCodeList.do", data, function(result) {
		if(result != null) {
			getSelectBox(result, "zoneList", "1", "zoneCode", "zoneNam", "main");
		}
	});
}

/**
 * 촬영년도 목록
 * @param 영상타입
 */
function getYearList(){

	ajaxCallJson("/search/yearList.do", "", function(result){

		if(result.list != null){
			getSelectBox(result, "sSearchYear", "0", "zoneYy", "zoneYy", "main");
			getSelectBox(result, "eSearchYear", "0", "zoneYy", "zoneYy", "main");
		}
	});
}
function onChangeSrchYear(flag){

	var sYear = $("#main #sSearchYear").val();
	var eYear = $("#main #eSearchYear").val();

	if(sYear > eYear){
		if("s" == flag){
			$("#main #sSearchYear").val(eYear);
		}else{
			$("#main #eSearchYear").val(sYear);
		}
	}
	getZoneList();
}

/**
 * 마커 찍기
 * @param state : 영상종류
 * @param beforeSearchParam : 이전검색조건
 * @param securityCde : 보안등급
 * @param zoneCode : 사업지구일련번호
 * @param phCourse : 코스번호
 */
function getAirZoneMarker(securityCde, zoneCode, phCourse){
	
	var data = beforeSearchParam;
	data["securityCde"] 	= securityCde;
	data["zoneCode"] 		= zoneCode;
	data["phCourse"] 		= phCourse;
	
	getZoneMarker("0", data, "a");
}
function getOrtZoneMarker(securityCde, gtypDst, zoneCode, mapNum){
	
	var data = beforeSearchParam;
	data["securityCde"] 	= securityCde;
	data["gtypDst"] 		= gtypDst;
	data["zoneCode"] 		= zoneCode;
	data["mapNum"] 			= mapNum;
	
	getZoneMarker("1", data, "o");
}
function getDemZoneMarker(securityCde, gridInt, zoneCode, mapNum){
	
	var data = beforeSearchParam;
	data["securityCde"]		= securityCde;
	data["gridInt"] 		= gridInt;
	data["zoneCode"] 		= zoneCode;
	data["mapNum"] 			= mapNum;
	
	getZoneMarker("2", data, "d");
}
function getLidZoneMarker(securityCde, zoneCode){
	
	var data = beforeSearchParam;
	data["securityCde"] 	= securityCde;
	data["zoneCode"] 		= zoneCode;
	
	getZoneMarker("3", data);
}
function getNirZoneMarker(securityCde, zoneCode, phCourse){
	
	var data = beforeSearchParam;
	data["securityCde"] 	= securityCde;
	data["zoneCode"] 		= zoneCode;
	data["phCourse"] 		= phCourse;
	
	getZoneMarker("4", data);
}
function getTdsZoneMarker(zoneCode, utl3dMpn){
	
	var data = beforeSearchParam;
	data["zoneCode"] 	= zoneCode;
	data["utl3dMpn"] 	= utl3dMpn;
	
	getZoneMarker("5", data);
}


var airnode = null;
var airLibnode = null;
var ortnode = null;
var demnode = null;
var atnode = null;
var suchinode = null;
var suchi2node = null;
var landnode = null;
var landUsenode = null;
var coastnode = null;
/**
 * 영상종류별 트리 생성
 * @param state
 */
function setInitTreeItem(state){
	switch(state) {
	
	//air	
	case "0" :
		$("#combineTreeMenu > ul").append("<li id=\"airTreeRoot\"></li>");
	    $("#airTreeRoot").fancytree({
	    	extensions: ["childcounter"],
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false},
	    	checkbox: true,
	    	imagePath: '',
	    	autoscroll: true,
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//미리보기
					if(event.originalEvent.target.className.indexOf("preview") > -1){
						if ($(event.originalEvent.target).hasClass("active")) {
							removeImage(result);
							$(event.originalEvent.target).removeClass("active");
						} else {
							$(event.originalEvent.target).addClass("active");
							requestImage("air", result);
						}
					//메타데이터 보기
					} else if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openAirMetadataLayer(result.zoneCode, result.phCourse, result.phNum);
					}
				}
            },
			select: function(event, data) {
			},
			focus: function(event, data){
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
			
		        var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses){
		        	if(node.extraClasses.indexOf("air_") > -1 && node.expanded){
			        	var zoneCode = node.data.zoneCode;
	    				var phCourse = node.data.phCourse;
	    				var securityCde = node.data.securityCde;
	    				node.extraClasses = "";
	    				
	    				searchAirImgDataList(node, zoneCode, phCourse, securityCde);
		        	}
		        }
		    },
	        source: [{"title": "항공사진","key": state,'folder': true}]
	    });
	    airnode = $("#airTreeRoot").fancytree("getTree").getNodeByKey(state);
	    
	    $("#combineTreeMenu > ul").append("<li id=\"airLibTreeRoot\"></li>");
	    $("#airLibTreeRoot").fancytree({
	    	extensions: ["childcounter"],
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false},
	    	checkbox: true,
	    	imagePath: '',
	    	autoscroll: true,
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//미리보기
					if(event.originalEvent.target.className.indexOf("preview") > -1){
						if ($(event.originalEvent.target).hasClass("active")) {
							removeImage(result);
							$(event.originalEvent.target).removeClass("active");
						} else {
							$(event.originalEvent.target).addClass("active");
							requestImage("air", result);
						}
					//메타데이터 보기
					} else if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openAirMetadataLayer(result.zoneCode, result.phCourse, result.phNum);
					}
				}
            },
			select: function(event, data) {
			},
			focus: function(event, data){
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
			
		        var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses){
		        	if(node.extraClasses.indexOf("air_") > -1 && node.expanded){
			        	var zoneCode = node.data.zoneCode;
	    				var phCourse = node.data.phCourse;
	    				var securityCde = node.data.securityCde;
	    				node.extraClasses = "";
	    				
	    				searchAirLibImgDataList(node, zoneCode, phCourse, securityCde);
		        	}
		        }
		    },
	        source: [{"title": "항공사진(해방전후)","key": state,'folder': true}]
	    });
	    airLibnode = $("#airLibTreeRoot").fancytree("getTree").getNodeByKey(state);
		break;
	
	//ort	
	case "1" :
		$("#combineTreeMenu > ul").append("<li id=\"ortTreeRoot\"></li>");
	    $("#ortTreeRoot").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false,hideZeros: true,hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//미리보기
					if(event.originalEvent.target.className.indexOf("preview") > -1){
						if ($(event.originalEvent.target).hasClass("active")) {
							removeImage(result);
							$(event.originalEvent.target).removeClass("active");
						} else {
							$(event.originalEvent.target).addClass("active");
							requestImage("ort", result);
						}
					//메타데이터 보기
					} else if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openOrtMetadataLayer(result.zoneCode, result.map5000Num, result.gtypDst);
					}
				}
            },
			select: function(event, data) {
			},
			focus: function(event, data){ 
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
				
		        var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses){
		        	if(node.extraClasses.indexOf("ort_") > -1 && node.expanded){
			        	var zoneCode = node.data.zoneCode;
			        	var gtypDst = node.data.gtypDst;
			        	var securityCde = node.data.securityCde;
	    				var mapNum = node.data.mapNum;
	    				node.extraClasses = "";
	    				
	    				searchOrtImgDataList(node, zoneCode, gtypDst, securityCde, mapNum);
		        	}
		        }
		    },
	        source: [{"title": "정사영상","key": state,'folder': true}]
	    });
	    ortnode = $("#ortTreeRoot").fancytree("getTree").getNodeByKey(state);
		break;
	
	//dem	
	case "2" :
		$("#combineTreeMenu > ul").append("<li id=\"demTreeRoot\"></li>");
	    $("#demTreeRoot").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false,hideZeros: true,hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//미리보기
					if(event.originalEvent.target.className.indexOf("preview") > -1){
						if ($(event.originalEvent.target).hasClass("active")) {
							removeImage(result);
							$(event.originalEvent.target).removeClass("active");
						} else {
							$(event.originalEvent.target).addClass("active");
							requestImage("dem", result);
						}
					//메타데이터 보기
					} else if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openDemMetadataLayer(result.zoneCode, result.map5000Num, result.gridInt);
					}
				}
	        },
			select: function(event, data) {
			},
			focus: function(event, data){ 
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
				
		        var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses){
		        	if(node.extraClasses.indexOf("dem_") > -1 && node.expanded){
			        	var zoneCode = node.data.zoneCode;
			        	var gridInt = node.data.gridInt;
			        	var securityCde = node.data.securityCde;
	    				var mapNum = node.data.mapNum;
	    				node.extraClasses = "";
	    				
	    				searchDemImgDataList(node, zoneCode, gridInt, securityCde, mapNum);
		        	}
		        }
		    },
			source: [{"title": "수치표고","key": state,'folder': true}]
		});
	    demnode = $("#demTreeRoot").fancytree("getTree").getNodeByKey(state);
		break;
	
	//at성과
	case "3" :
		$("#combineTreeMenu > ul").append("<li id=\"atTreeRoot\"></li>");
	    $("#atTreeRoot").fancytree({
	    	extensions: ["childcounter"],
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false},
	    	checkbox: true,
	    	imagePath: '',
	    	autoscroll: true,
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//메타데이터 보기
					if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openAtMetadataLayer(result.zoneCode);
					}
				}
            },
			select: function(event, data) {
			},
			focus: function(event, data){
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
		    },
		    source: [{"title": "AT성과", "key": state, 'folder': true}]
	    });
	    atnode = $("#atTreeRoot").fancytree("getTree").getNodeByKey(state);
		break;
	
	//수치지형도
	case "4" :
		$("#combineTreeMenu > ul").append("<li id=\"suchiTR\"></li>");
	    $("#suchiTR").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//도엽 위치보기
					queryIndex(node.data.mapShtNo, node.data.scaleCd);
					
					//메타데이터 보기
					if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openMapDetail(result.mapShtNo, result.mapSerNo, result.mapHistoryNo);
					}
				}
                
			},
			select: function(event, data) {
			},
			focus: function(event, data) { 
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data) {
				var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses) {
		        	if(node.extraClasses.indexOf("suchi_") > -1 && node.expanded) {
			        	var mapShtNo = node.data.mapShtNo;
	    				var mapSerNo = node.data.mapSerNo;
	    				node.extraClasses = "";
	
	    				searchSuchiDataList(node, mapShtNo, mapSerNo);
		        	}
		        }
		    },
		    source: [{"title": "수치지형도","key": state,'folder': true}]
		});
	    suchinode = $("#suchiTR").fancytree("getTree").getNodeByKey(state);
		
		$("#combineTreeMenu > ul").append("<li id=\"suchi2TR\"></li>");
	    $("#suchi2TR").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
                var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//도엽 위치보기
					queryIndex(node.data.mapShtNo, node.data.scaleCd);
					
					//메타데이터 보기
					if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openMapDetail(result.mapShtNo, result.mapSerNo, result.mapHistoryNo);
					}
				}
			},
			select: function(event, data) {
			},
			focus: function(event, data){ 
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
				var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses) {
		        	if(node.extraClasses.indexOf("suchi_") > -1 && node.expanded) {
			        	var mapShtNo = node.data.mapShtNo;
	    				var mapSerNo = node.data.mapSerNo;
	    				node.extraClasses = "";
	
	    				searchSuchiDataList(node, mapShtNo, mapSerNo);
		        	}
		        }
		    },
		    source: [{"title": "수치지형도V2","key": state,'folder': true}]
		});
	    suchi2node = $("#suchi2TR").fancytree("getTree").getNodeByKey(state);

		$("#combineTreeMenu > ul").append("<li id=\"landTR\"></li>");
	    $("#landTR").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//도엽 위치보기
					queryIndex(node.data.mapShtNo, node.data.scaleCd);
					
					//메타데이터 보기
					if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openMapDetail(result.mapShtNo, result.mapSerNo, result.mapHistoryNo);
					}
				}
			},
			select: function(event, data) {
			},
			focus: function(event, data){ 
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
				var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses) {
		        	if(node.extraClasses.indexOf("suchi_") > -1 && node.expanded) {
			        	var mapShtNo = node.data.mapShtNo;
	    				var mapSerNo = node.data.mapSerNo;
	    				node.extraClasses = "";
	
	    				searchSuchiDataList(node, mapShtNo, mapSerNo);
		        	}
		        }
		    },
		    source: [{"title": "토지특성도","key": state,'folder': true}]
		});
	    landnode = $("#landTR").fancytree("getTree").getNodeByKey(state);

		$("#combineTreeMenu > ul").append("<li id=\"landUseTR\"></li>");
	    $("#landUseTR").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//도엽 위치보기
					queryIndex(node.data.mapShtNo, node.data.scaleCd);
					
					//메타데이터 보기
					if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openMapDetail(result.mapShtNo, result.mapSerNo, result.mapHistoryNo);
					}
				}
			},
			select: function(event, data) {
			},
			focus: function(event, data){ 
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
				var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses) {
		        	if(node.extraClasses.indexOf("suchi_") > -1 && node.expanded) {
			        	var mapShtNo = node.data.mapShtNo;
	    				var mapSerNo = node.data.mapSerNo;
	    				node.extraClasses = "";
	
	    				searchSuchiDataList(node, mapShtNo, mapSerNo);
		        	}
		        }
		    },
		    source: [{"title": "토지이용현황도","key": state,'folder': true}]
		});
	    landUsenode = $("#landUseTR").fancytree("getTree").getNodeByKey(state);

		$("#combineTreeMenu > ul").append("<li id=\"coastTR\"></li>");
	    $("#coastTR").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				var result  = node.data;
				
				if (data.targetType == 'title' && !node.isFolder() && !node.hasChildren() && node.extraClasses.indexOf('fancytree-disable') == -1) {
					//도엽 위치보기
					queryIndex(node.data.mapShtNo, node.data.scaleCd);
					
					//메타데이터 보기
					if(event.originalEvent.target.className.indexOf("metaInfo") > -1){
						openMapDetail(result.mapShtNo, result.mapSerNo, result.mapHistoryNo);
					}
				}
			},
			select: function(event, data) {
			},
			focus: function(event, data){ 
				data.node.scrollIntoView(true); 
			},
			expand: function(event, data){
				var node = data.node;
		        
		        if(null != node.extraClasses && "" != node.extraClasses) {
		        	if(node.extraClasses.indexOf("suchi_") > -1 && node.expanded) {
			        	var mapShtNo = node.data.mapShtNo;
	    				var mapSerNo = node.data.mapSerNo;
	    				node.extraClasses = "";
	
	    				searchSuchiDataList(node, mapShtNo, mapSerNo);
		        	}
		        }
		    },
		    source: [{"title": "연안해역도","key": state,'folder': true}]
		});
	    coastnode = $("#coastTR").fancytree("getTree").getNodeByKey(state);
	}	
}

function moveTreeFocus(treeType, key){
//	airnode.activateKey(key);
	$("#"+treeType+"TreeRoot").fancytree("getTree").activateKey(key);
//	$("#"+treeType).fancytree("getTree").getNodeByKey(key).setActive();
//	$("#"+treeType).fancytree("getTree").getNodeByKey(key).setFocus(true);
	
//    var position = $("#"+treeType).fancytree("getTree").activateKey(key);
//    alert(position.top);
//    $('html, body').animate({scrollTop : position.top}, 2000);
//	var node = $("#"+treeType).fancytree("getActiveNode");
//    node.makeVisible({scrollIntoView: true}); // nodeSetFocus will scroll
}

function unSelectedItem(){
	if (airnode != null){
		airnode.visit(function(node){
			node.setSelected(false);
		});	
	}
	
	if (ortnode != null){
		ortnode.visit(function(node){
			node.setSelected(false);
		});	
	}
	
	if (demnode != null){
		demnode.visit(function(node){
			node.setSelected(false);
		});	
	}
	
	if (atnode != null){
		atnode.visit(function(node){
			node.setSelected(false);
		});	
	}
	
	if (suchinode != null){
		suchinode.visit(function(node){
			node.setSelected(false);
		});	
	}
}

/**
 * 검색 타입별 Data Object 반환
 */
function getAreaTapTypeData() {
	var imgTypeObj = getSelecteImgType();
	var data = null;
	
	//행정구역
	if (parseInt(areaTapType) == 0) { 
		if (searchaAdmCode == "00") {
			alert("행정구역을 선택해주세요!");
			return false;
		} else {
			data = {
				"imgType" : imgTypeObj, 
				"sYear" : getStartYear(), 
				"eYear" : getEndYear(), 
				"zoneCode" : $("#zoneList").val(),
				"admcd" : searchaAdmCode
			};
		}
	//도엽
	} else if (parseInt(areaTapType) == 4) {
		if (indexMapGeom != null) {
			data = {
				"imgType" : imgTypeObj, 
				"sYear" : getStartYear(), 
				"eYear" : getEndYear(), 
				"zoneCode" : $("#zoneList").val(),
				"bounds" : indexMapGeom.toString()
			};
		} else {
			alert('도엽을 선택해주세요!');
			$('indexMapName').focus();
			return false;
		}
	//사용자영역
	} else if (parseInt(areaTapType) == 5) {
		if ($("#search_custom_wkt").val() != "") {
			data = {
				"imgType" : imgTypeObj, 
				"sYear" : getStartYear(), 
				"eYear" : getEndYear(), 
				"zoneCode" : $("#zoneList").val(),
				"geometry" : $("#search_custom_wkt").val()
			};
		} else {
			alert('사용자 영역을 선택해주세요!');
			return false;
		}
	//명칭, 지번, 새주소
	} else {
		var circleLayer = null; 
		rinoGIS.ol.map.getLayers().forEach((lyr) => {
			if (lyr instanceof ol.layer.Vector && lyr.get('drawCircle')) {
				circleLayer = lyr;
			}
		});
		
		if (circleLayer != null) {
			var bounds = circleLayer.getSource().getExtent();

			data = {
				"imgType" : imgTypeObj, 
				"sYear" : getStartYear(), 
				"eYear" : getEndYear(), 
				"zoneCode" : $("#zoneList").val(),
				"bounds" : bounds.toString(),
				"radius" : amountObj.val()
			};
		} else {
			alert('영역버퍼를 설정해주세요!');
			$('poiKeyword').focus();
			return false;
		}
	}
	
	return data;
}

/**
 * 영상 종류별 최상위 트리 컨트롤 
 * @param imgTypeObj
 */
function setTreeRootItemControll(imgTypeObj){
	$("#combineTreeMenu > ul").empty();
	
	if (imgTypeObj.indexOf(",") > 0){
		var temp = imgTypeObj.split(",");
		for (var i = 0; i < temp.length; i++){
			setInitTreeItem(temp[i]);
		}
	}else{
		setInitTreeItem(imgTypeObj);
	}
}


/**
 * 통합검색 실행
 */
function searchList(){
	var sYear = $("#main #sSearchYear").val();
	var eYear = $("#main #eSearchYear").val();

	if((eYear-sYear) > 5){
		alert("최대 5년까지 조회가능합니다.");
		return;
	}
	
	//마커 삭제
	rinoGIS.ol.map.removeTempOverlay();
	removeAllImages();
	//인덱스 쿼리 삭제
	rinoGIS.ol.map.getLayerById(207).setVisible(false);
	
	unSelectedItem();
	
	var imgTypeObj = getSelecteImgType();
	var paramData = getAreaTapTypeData();
	
	if(paramData == false) {
		return;
	}
	
	if (imgTypeObj.indexOf(",") > 0) {
		var temp = imgTypeObj.split(",");
		for (var i = 0; i < temp.length; i++) {
			searchImage(temp[i], paramData);
		}
	} else {
		searchImage(imgTypeObj, paramData);
	}
	
	$('.btnParentHide').parents('.top').find('.parentWrap').fadeOut(600, function() {
		scroll();
	});
	$('.btnCloseOpen').addClass('active');
}

/**
 * 영상종류 선택 확인
 * @param type
 */
function searchImage(type, paramData) {
	switch(type) {
		case "0" :
			//searchAirImgList();
			searchAirImgFolderList(paramData);
			searchAirLibImgFolderList(paramData);
			break;
		case "1" :
			//searchOrtImgList();
			searchOrtImgFolderList(paramData);
			break;
		case "2" :
			//searchDemImgList();
			searchDemImgFolderList(paramData);
			break;
		case "3" :
			searchAtImgFolderList(paramData);
			break;
		case "4" :
			searchSuchiFolderList(paramData);	//수치지도1
			searchSuchi2FolderList(paramData);	//수치지도2
			searchLandFolderList(paramData);	//토지특성도
			searchLandUseFolderList(paramData);	//토지이용현황도
			searchCoastFolderList(paramData);	//연안해역도
			break;
	}
}

/**
 * 통합검색 - 항공사진
 */
function searchAirImgFolderList(paramData){
    
    if(paramData != false){
    	
        $(airnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/airImgFolderList.do", paramData, function(result){
        	
        	airnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						airnode.addChildren(value);
					}
				});
			}
			$(airnode.li).children().removeClass('fancytree-loading');
			airnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(airnode.li).children().removeClass('fancytree-loading');
        });
    }
}

function searchAirImgDataList(node, zoneCode, phCourse, securityCde){
	
	var data = beforeSearchParam;
	data["imgType"] 	= "0";
	data["zoneCode"] 	= zoneCode;
	data["phCourse"] 	= phCourse;
	data["securityCde"] = securityCde;
	
	searchImgDataList("/search/airImgDataList.do", node, data);
}

/**
 * 통합검색 - 항공사진(해방전후)
 */
function searchAirLibImgFolderList(paramData){
    
    if(paramData != false){
    	
        $(airLibnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/airLibImgFolderList.do", paramData, function(result){
        	
        	airLibnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						airLibnode.addChildren(value);
					}
				});
			}
			$(airLibnode.li).children().removeClass('fancytree-loading');
			airLibnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(airLibnode.li).children().removeClass('fancytree-loading');
        });
    }
}

/**
 * 통합검색 - 항공사진(해방전후)
 */
function searchAirLibImgDataList(node, zoneCode, phCourse, securityCde){
	
	var data = beforeSearchParam;
	data["imgType"] 	= "0";
	data["zoneCode"] 	= zoneCode;
	data["phCourse"] 	= phCourse;
	data["securityCde"] = securityCde;
	
	searchImgDataList("/search/airLibImgDataList.do", node, data);
}


/**
 * 통합검색 - 정사영상
 */
function searchOrtImgFolderList(paramData){
    
    if(paramData != false){
    	
        $(ortnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/ortImgFolderList.do", paramData, function(result){
        	
        	ortnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						ortnode.addChildren(value);
					}
				});
			}
			$(ortnode.li).children().removeClass('fancytree-loading');
			ortnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(ortnode.li).children().removeClass('fancytree-loading');
        });
    }
}

function searchOrtImgDataList(node, zoneCode, gtypDst, securityCde, mapNum){
	
	var data = beforeSearchParam;
	data["imgType"] 	= "1";
	data["zoneCode"] 	= zoneCode;
	data["gtypDst"] 	= gtypDst;
	data["securityCde"] = securityCde;
	data["mapNum"] 		= mapNum;
	
	searchImgDataList("/search/ortImgDataList.do", node, data);
}

/**
 * 통합검색 - 수치표고
 */
function searchDemImgFolderList(paramData){
    
    if(paramData != false){
    	
        $(demnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/demImgFolderList.do", paramData, function(result){
        	
        	demnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						demnode.addChildren(value);
					}
				});
			}
			$(demnode.li).children().removeClass('fancytree-loading');
			demnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(demnode.li).children().removeClass('fancytree-loading');
        });
    }
}

function searchDemImgDataList(node, zoneCode, gridInt, securityCde, mapNum){
	
	var data = beforeSearchParam;
	data["imgType"] 	= "2";
	data["zoneCode"] 	= zoneCode;
	data["gridInt"] 	= gridInt;
	data["securityCde"] = securityCde;
	data["mapNum"] 		= mapNum;
	
	searchImgDataList("/search/demImgDataList.do", node, data);
}


function searchImgDataList(url, node, data){
	
	$(node.li).children().addClass('fancytree-loading');
	
	ajaxCallJson(url, data, function(result){
		
		node.removeChildren();
		
		if (result != null){
			$.each(result.result, function(index, value){

				if (value != null){
					//새로운 노드를 붙여 넣고 기존 노드를 삭제한다.
					var target = node.appendSibling(value);
					target.setFocus(node.hasFocus());
					target.setSelected(node.selected);
					target.setExpanded(node.expanded);
					
					$(node.li).children().removeClass('fancytree-loading');
					node.remove();
					
					target.getParent().setExpanded(true);
				}
			});
		}
		
	}, function(result){
		$(node.li).children().removeClass('fancytree-loading');
	});
}


/**
 * 항공사진 상세정보 검색
 */
function airDetailInfo(type){
	window.console.log('항공사진 상세정보 검색 실패!' + result);
}



/**
 * 통합검색 - AT
 */
function searchAtImgFolderList(paramData) {
	if(paramData != false) {
    	
        $(atnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/atImgFolderList.do", paramData, function(result) {
        	
        	atnode.removeChildren();

			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						atnode.addChildren(value);
					}
				});
			}
			$(atnode.li).children().removeClass('fancytree-loading');
			atnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(atnode.li).children().removeClass('fancytree-loading');
        });
    }
}

/**
 * 통합검색 - 수치지형도
 */
function searchSuchiFolderList(paramData) {
	if(paramData != false) {
		
		$(suchinode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/suchiFolderList.do", paramData, function(result){
        	
        	suchinode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						suchinode.addChildren(value);
					}
				});
			}
			$(suchinode.li).children().removeClass('fancytree-loading');
			suchinode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(suchinode.li).children().removeClass('fancytree-loading');
        });
	}
}

/**
 * 수치지형도 이력 조회
 */
function searchSuchiDataList(node, mapShtNo, mapSerNo) {
	
	var data = {
			"mapShtNo"	: mapShtNo, 
			"mapSerNo"	: mapSerNo
	}
	
	searchImgDataList("/search/suchiDataList.do", node, data);
}

/**
 * 통합검색 - 수치지형도 v2.0
 */
function searchSuchi2FolderList(paramData) {
	if(paramData != false) {
		paramData["mapType"] = "suchi2";
		$(suchi2node.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/suchi2FolderList.do", paramData, function(result){
        	
        	suchi2node.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						suchi2node.addChildren(value);
					}
				});
			}
			$(suchi2node.li).children().removeClass('fancytree-loading');
			suchi2node.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(suchi2node.li).children().removeClass('fancytree-loading');
        });
	}
}

/**
 * 통합검색 - 토지특성도
 */
function searchLandFolderList(paramData) {
	if(paramData != false) {
		
		$(landnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/landFolderList.do", paramData, function(result){
        	
        	landnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						landnode.addChildren(value);
					}
				});
			}
			$(landnode.li).children().removeClass('fancytree-loading');
			landnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(landnode.li).children().removeClass('fancytree-loading');
        });
	}
}

/**
 * 통합검색 - 토지이용현황도
 */
function searchLandUseFolderList(paramData) {
	if(paramData != false) {
		
		$(landUsenode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/landUseFolderList.do", paramData, function(result){
        	
        	landUsenode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						landUsenode.addChildren(value);
					}
				});
			}
			$(landUsenode.li).children().removeClass('fancytree-loading');
			landUsenode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(landUsenode.li).children().removeClass('fancytree-loading');
        });
	}
}

/**
 * 통합검색 - 연안해역도
 */
function searchCoastFolderList(paramData) {
	if(paramData != false) {
		
		$(coastnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/coastFolderList.do", paramData, function(result){
        	
        	coastnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						coastnode.addChildren(value);
					}
				});
			}
			$(coastnode.li).children().removeClass('fancytree-loading');
			coastnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(coastnode.li).children().removeClass('fancytree-loading');
        });
	}
}


/**
 * 명칭 검색
 */
function searchPOI(){
	var searchKeywordValue = $("#poiKeyword").val();

	if(searchKeywordValue == '') {
		alert("검색어를 입력해주세요.");
		return false;
	}
	
	$.ajax({
		url: '/niis/kakao.jsp',
		type: 'GET',
		data: { type: "keyword", page: 1, size: 45, query: searchKeywordValue },
		dataType: 'jsonp',
		cache: false,
		success: function(data) {
			$("#poiList").mCustomScrollbar("destroy");
			$("#poiList").empty();
			$("#poiCnt").text(data.documents.length);
			
			for(var i=0; i<data.documents.length; ++i) {
				var item = data.documents[i];
				$("#poiList").append("<li><a href=\"#none\" onclick=\"javascript:searchOfDistanse('"+item.place_name+ "','"+item.x+ "','"+item.y+ "', 1, 'amount')\">"+item.place_name+"</a></li>");
			}
			
			$("#poiList").children(":last").addClass("last");
			$("#poiList").mCustomScrollbar();
				
			filterBtnChange("poiCodeBtn", "검색", 1);
		}
	});
}

function searchOfDistanse(name, x, y, state, target) {
	
	var x = Number(x);
	var y = Number(y);
	
	//줌인 (경위도 좌표로)
	rinoGIS.ol.map.move([x, y], 32.64, false);

	drawCircle(x, y, target);
}



function selectIndexMapName(){
	
	var data = {
			"selectKey"	: $("#indexMapScale").val()
	}
	
	ajaxCallJson("/search/selectIndexMapName.do", data, function(result){
		
		if (result.IndexMapName != null){
			$("#indexMapName").empty();
			$("#indexMapName").append("<option>선택</option>");
			
			for (var i=0; i<result.IndexMapName.length; i++){
				
				var xmin   = result.IndexMapName[i]["xmin"];
				var ymin   = result.IndexMapName[i]["ymin"];
				var xmax   = result.IndexMapName[i]["xmax"];
				var ymax   = result.IndexMapName[i]["ymax"];
				var mapNam = result.IndexMapName[i]["mapNam"];
				
				$("#indexMapName").append("<option minx="+xmin+" miny="+ymin+" maxx="+xmax+" maxy="+ymax+">"+mapNam+"</option>");
			}
			$("#indexMapName").initSelect();
		}
	});
}

function moveToIndexMap(){
	
	var selectKey = $("#indexMapName option:selected").text();
	
	if(selectKey == "선택"){
		alert("도엽을 선택해주세요!");
		$("#indexMapName").focus();
		return;
	}else{
		indexMapArea(selectKey);
	}
}	



/**
 * 마커 오버레이 영역 설정
 * @param indexPolygonList
 */
function setSelectBounds(indexPolygonList){
	//minx, miny, maxx, maxy
	var bounds = [];
	
	var maxx = 0;
	var maxy = 0;
	var minx = null;
	var miny = null;
	
	for (var i = 0; i < indexPolygonList.length; i++){
		var points = indexPolygonList[i].split(" ");
		for (var j = 0; j < points.length; j++){
			var point = points[j].trim().split(",");
			if(maxx < point[0]){
				maxx = point[0];
			}
			if (maxy < point[1]){
				maxy = point[1];
			}
			
			if (minx != null){
				if(minx > point[0]){
					minx = point[0];
				}	
			}else{
				minx = point[0];
			}
			
			if (miny != null){
				if(miny > point[1]){
					miny = point[1];
				}	
			}else{
				miny = point[1];
			}
		}
	}
	
	//좌표계 통일 시 삭제 (항공사진 영역정보 : TM중부)
//	var minxy = transformLonLat(minx, miny, utmk,tmm);
//	var maxxy = transformLonLat(maxx, maxy, utmk,tmm);
	
	bounds.push(minx);
	bounds.push(miny);
	bounds.push(maxx);
	bounds.push(maxy);
	
	indexMapGeom = bounds;
}
/**
 * 통합검색 - 도엽
 * @param selectKey
 */
function indexMapArea(selectKey){
	var indexPolygonList = [];
	$.ajax({
		type	 : "POST",
		async 	 : true,
		url 	 : "/niis/search/searchIndexMapArea.do",
		data	 : "selectKey="+selectKey,
		dataType : "html",
		success: function(result){
			removePolygon();
			$(result).find("Polygon").find("coordinates").each(function(index){
				indexPolygonList.push($(this).text());
			});
			drawPolygon(indexPolygonList);
			setSelectBounds(indexPolygonList);
		},
		error: function() {
			window.console.log('도엽 리스트 검색 실패!');
		}	
	});
}


/**
 * 수치지형도 미리보기 (도엽)
 */
function queryIndex(mapShtNo, scaleCd) {
	
	//마커, 영상 삭제
	rinoGIS.ol.map.removeTempOverlay();
	removeAllImages();
	
	//축척에 따른 layerId
	var scale 	= { '01': '001000', '09': '002500', '02': '005000', '03': '025000', '05': '250000' };
	var layerId = { '01': 201, '09': 202, '02': 203, '03': 204, '05': 206 };
	var onLayer = layerId[scaleCd];
	var scaleNm = scale[scaleCd];
	
	for (key in layerId) {
		rinoGIS.ol.map.getLayerById(layerId[key]).setVisible(false);
	}
	
	setTimeout(function() {
		rinoGIS.ol.map.getLayerById(207).query({
			viewParams: {
				scale: scaleNm,
				map_sht_no: mapShtNo,
				type: 'v1'
			}
		}).then(function(ext) {
//			rinoGIS.ol.map.move(ext);
			rinoGIS.ol.map.getView().fit(ext);
			rinoGIS.ol.map.getLayerById(207).setVisible(true);
//			rinoGIS.ol.map.getLayerById(onLayer).setVisible(true);
		});
	}, 500);
}

/**
 * 수치지형도 성과상세
 */
function openMapDetail(mapShtNo, mapSerNo, mapHistoryNo) {
	
	var data = {
			"mapShtNo"	: mapShtNo, 
			"mapSerNo"	: mapSerNo,
			"mapHistoryNo" : mapHistoryNo
	}
	
	if(mapHistoryNo == 0) {
		ajaxCallPop("/mapListSearch/mapListSrchDetail.do", data);
	} else { 
		ajaxCallPop("/mapListSearch/mapHistoryListSrchDetail.do", data);
	}
}


/**
 * 영상 이미지 요청 (tree)
 * @param type
 * @param minx
 * @param miny
 * @param maxx
 * @param maxy
 * @param layer
 */
function requestImage(type, result) {
	
	rinoGIS.ol.map.removeTempOverlay();
	removeAllImages();
	removeAllImages();
	rinoGIS.ol.map.getLayerById(207).setVisible(false);
	
	var path = result.nixPath;
	
	if(type == 'ort') { 
		path = path.replace("/ort/", "/ort_jpg/");
		path = path.replace("8cm", "51cm");
		path = path.replace("25cm", "51cm");
		path = path.replace("12cm", "51cm");
		path = path.replace("TIF", "jpg");
		path = path.replace("tif", "jpg");
		
	} else if(type == 'dem') {
		path = path.replace("/dem/", "/dem_jpg/");
	}
	
	var imgSource = new ol.source.ImageStatic({
		url: "http://192.168.0.101:1080/iim_files" + path,
		//url: "http://10.98.25.14:9000/iim_files" + path,
        projection: rinoGIS.ol.map.getView().getProjection(),
        imageExtent: [result.xmin, result.ymin, result.xmax, result.ymax]
	});
	
	imgSource.on('imageloaderror', function() {
		alert("미리보기 파일이 없습니다.");
	});
	
	var imgLayer = new ol.layer.Image({
		source: imgSource,
		id: result.fileNam
	});
	
	rinoGIS.ol.map.addLayer(imgLayer);
	rinoGIS.ol.map.getView().fit([result.xmin, result.ymin, result.xmax, result.ymax]);
	
	return;
}

/**
 * 모든 영상 이미지 제거
 */
function removeAllImages() {
	rinoGIS.ol.map.getLayers().forEach((lyr) => {
		if (lyr instanceof ol.layer.Image) {
			rinoGIS.ol.map.removeLayer(lyr);
		}
	});
}

function removeImage(result) {
	rinoGIS.ol.map.getLayers().forEach((lyr) => {
		if (lyr instanceof ol.layer.Image) {
			if (lyr.get('id').indexOf(result.fileNam) >= 0) {
				rinoGIS.ol.map.removeLayer(lyr);
			}
		}
	});
}



/**
 * 항공사진 메타데이터 검색
 * @param zoneCode
 * @param phCourse
 * @param phNum
 */
function searchAirMetaData(zoneCode, phCourse, phNum, zoneNam){
	
	var data = {
			"zoneCode"	: zoneCode, 
			"phCourse"	: phCourse, 
			"phNum"		: phNum
	}
	
	ajaxCallPop("/search/getAirMetaInfo.do", data);
}

/**
 * 정사영상 메타데이터 검색
 * @param map5000Num
 * @param zoneCode
 */
function searchOrtMetaData(zoneCode, map5000Num, gtypDst){
	
	var data = {
			"zoneCode"		: zoneCode, 
			"map5000Num"	: map5000Num,
			"gtypDst"		: gtypDst
	}
	
	ajaxCallPop("/search/getOrtMetaInfo.do", data);
}

/**
 * 수치표고 메타데이터 검색
 * @param map5000Num
 * @param zoneCode
 */
function searchDemMetaData(zoneCode, map5000Num, gridInt){
	
	var data = {
			"zoneCode"		: zoneCode, 
			"map5000Num"	: map5000Num,
			"gridInt"		: gridInt
	}
	
	ajaxCallPop("/search/getDemMetaInfo.do", data);
}

/**
 * AT성과 메타데이터 검색
 * @param zoneCode
 */
function searchAtMetaData(zoneCode) {
	
	var data = {
			"zoneCode"		: zoneCode, 
	}
	
	ajaxCallPop("/search/getAtMetaInfo.do", data);

}