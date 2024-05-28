var searchTapType = 0;
var areaTapType = 0;

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
				
				var text = dongNm + " " + mntnYn + lnbrMnnm + ((lnbrSlno == "0") ? "" : " - " + lnbrSlno);

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
	var polygonList = [];
	$.ajax({
		type	 : "POST",
		async 	 : true,
		url 	 : "/niis/search/searchAddressArea.do",
		data	 : "admcd="+admcd,
		dataType :"html",
		success: function(result){
			removePolygon();
			$(result).find("Polygon").find("coordinates").each(function(index){
				polygonList.push($(this).text());
			});
			drawPolygon(polygonList);
		},
		error: function() {
			window.console.log('행정경계 리스트 검색 실패!');
		}
	});
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
function getZoneList(){

	var imgTypeObj = getSelecteImgType();
	//검색결과 Root 설정
	setTreeRootItemControll(imgTypeObj);

	var data = {
		"imgType"	: imgTypeObj,
		"sYear"		: getStartYear(),
		"eYear"		: getEndYear()
	}
	ajaxCallJson("/search/zoneCodeList.do", data, function(result){

		if(result != null){
			getSelectBox(result, "zoneList", "1", "zoneCode", "zoneNam", "main");
		}
	});
}

/**
 * 년도 목록
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
	
	getZoneMarker("0", data);
}
function getOrtZoneMarker(securityCde, gtypDst, zoneCode, mapNum){
	
	var data = beforeSearchParam;
	data["securityCde"] 	= securityCde;
	data["gtypDst"] 		= gtypDst;
	data["zoneCode"] 		= zoneCode;
	data["mapNum"] 			= mapNum;
	
	getZoneMarker("1", data);
}
function getDemZoneMarker(securityCde, gridInt, zoneCode, mapNum){
	
	var data = beforeSearchParam;
	data["securityCde"]		= securityCde;
	data["gridInt"] 		= gridInt;
	data["zoneCode"] 		= zoneCode;
	data["mapNum"] 			= mapNum;
	
	getZoneMarker("2", data);
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

/**
 * 마커 찍기
 * @param state : 영상종류
 * @param data : 검색조건
 */
function getZoneMarker(state, data){
	
	data["imgType"] = state;
	data["state"] 	= state;
	
	var resultBound = new ResultBound();
	removeMarker(state);
	removeImgBoundsPolygon();
	
	removeAirImgLayers();
	
	ajaxCallJson("/search/getImgMarkerList.do", data, function(result){
		
		if (result.result != null){
			
			$.map(result.result, function(node){
				var coord = tmToUtm(node.xmin, node.ymin, node.xmax, node.ymax);
				var bounds = new OpenLayers1.Bounds(coord[0], coord[1], coord[2], coord[3]);
				var centerPoint = bounds.getCenterLonLat();
				addMarkerOverlay(centerPoint.lon, centerPoint.lat, getMarkerName(state, node), getMarkerIconUrl(state), state, getMarkerKey(state, node), bounds, node.nixPath);
				resultBound.setPoi(parseFloat(centerPoint.lon), parseFloat(centerPoint.lat));
			});
			if (resultBound.getBounds() != null){
				setMapBounds(resultBound.getBounds());
			}
		}
	});
}

function addMarker(state, node){
	
	var resultBound = new ResultBound();
	removeMarker(state);
	removeImgBoundsPolygon();
	
	removeAirImgLayers();
	
	var coord = tmToUtm(node.xmin, node.ymin, node.xmax, node.ymax);
	var bounds = new OpenLayers1.Bounds(coord[0], coord[1], coord[2], coord[3]);
	var centerPoint = bounds.getCenterLonLat();
	
	var icon = "/niis/images/icon/x.png";
	//addMarkerOverlay(centerPoint.lon, centerPoint.lat, getMarkerName(state, node), getMarkerIconUrl(state), state, getMarkerKey(state, node), bounds, node.nixPath);
	addMarkerOverlay(centerPoint.lon, centerPoint.lat, getMarkerName(state, node), icon, state, getMarkerKey(state, node), bounds, node.nixPath);
	resultBound.setPoi(parseFloat(centerPoint.lon), parseFloat(centerPoint.lat));
	
	if (resultBound.getBounds() != null){
		setMapBounds(resultBound.getBounds());
	}
	
	requestImageAlone(getMarkerKey(state, node), getTypeFromState(state), node.xmin, node.ymin, node.xmax, node.ymax, node.nixPath);
}

function getTypeFromState(state){
	var name = "";
	if("0" == state || "4" == state){
		name = "air";
	}else if("1" == state){
		name = "ort";
	}else if("2" == state){
		name = "dem";
	}else if("3" == state){
		name = "lid";
	}else if("5" == state){
		name = "3ds";
	}
	
	
	return name;
}

function getMarkerName(state, node){
	var name = "";
	if("0" == state || "4" == state){
		name = node.phCourse+"("+node.phNum+")";
	}else if("1" == state){
		name = node.map5000Num + "("+node.gtypDst+")";
	}else if("2" == state){
		name = node.map5000Num + "("+node.gridInt+")";
	}else if("3" == state){
		name = node.map5000Num + "";
	}else if("5" == state){
		name = node.tdsIdn + "";
	}
	
	return name;
}

function getMarkerIconUrl(state){
	var url;
	switch(state){
	case "0" :
		url = "/niis/images/icon/24x24/a.png";
		break;
	case "1" :
		url = "/niis/images/icon/24x24/o.png";
		break;
	case "2" :
		url = "/niis/images/icon/24x24/d.png";
		break;
	case "3" :
		url = "/niis/images/icon/24x24/l.png";
		break;
	case "4" :
		url = "/niis/images/icon/24x24/n.png";
		break;
	case "5" :
		url = "/niis/images/icon/24x24/3d.png";
		break;
	}
	return url;
}

function getMarkerKey(state, node){
	var key = "";
	
	switch(state){
	case "0" :
		key = node.zoneCode + "|" + node.phCourse + "|" + node.phNum;
		break;
	case "1" :
		key = node.zoneCode + "|" + node.map5000Num + "|" + node.gtypDst;
		break;
	case "2" :
		key = node.zoneCode + "|" + node.map5000Num + "|" + node.gridInt;
		break;
	case "3" :
		key = node.zoneCode + "|" + node.lidarIdn;
		break;
	case "4" :
		key = node.zoneCode + "|" + node.phCourse + "|" + node.phNum;
		break;
	case "5" :
		key = node.zoneCode + "|" + node.tdsIdn;
		break;
	}
	return key;
}

var airnode = null;
var airLibnode = null;
var ortnode = null;
var demnode = null;
var lidnode = null;
var nirnode = null;
var tdsnode = null;
/**
 * 영상종류별 트리 생성
 * @param state
 */
function setInitTreeItem(state){
	var bounds = null;
	switch(state){
	case "0" :
		$("#combineTreeMenu > ul").append("<li id=\"airTreeRoot\"></li>");
	    $("#airTreeRoot").fancytree({
	    	extensions: ["childcounter"],
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false},
	    	checkbox: true,
	    	imagePath: '/niis/images/sub/',
	    	autoscroll: true,
	    	selectMode: 3,
	        click: function(event, data) {
                var node = data.node;
                if (!node.folder){
                	addMarker(state, node.data);
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
	    	imagePath: '/niis/images/sub/',
	    	autoscroll: true,
	    	selectMode: 3,
	        click: function(event, data) {
                var node = data.node;
                if (!node.folder){
                	addMarker(state, node.data);
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
	case "1" :
		$("#combineTreeMenu > ul").append("<li id=\"ortTreeRoot\"></li>");
	    $("#ortTreeRoot").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false,hideZeros: true,hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '/niis/images/sub/', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
                var node = data.node;
                if (!node.folder){
	            	addMarker(state, node.data);
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
	case "2" :
		$("#combineTreeMenu > ul").append("<li id=\"demTreeRoot\"></li>");
	    $("#demTreeRoot").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false,hideZeros: true,hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '/niis/images/sub/', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
	        	var node = data.node;
	            if (!node.folder){
	        		addMarker(state, node.data);
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
	case "3" :
		$("#combineTreeMenu > ul").append("<li id=\"lidTreeRoot\"></li>");
	    $("#lidTreeRoot").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '/niis/images/sub/', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
				var node = data.node;
				if (!node.folder){
                	addMarker(state, node.data);
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
		        	if(node.extraClasses.indexOf("lid_") > -1 && node.expanded){
			        	var zoneCode = node.data.zoneCode;
	    				var securityCde = node.data.securityCde;
	    				node.extraClasses = "";
	    				
	    				searchLidImgDataList(node, zoneCode, securityCde);
		        	}
		        }
		    },
		    source: [{"title": "라이다","key": state,'folder': true}]
		});
	    lidnode = $("#lidTreeRoot").fancytree("getTree").getNodeByKey(state);
		break;
	case "4" :
		$("#combineTreeMenu > ul").append("<li id=\"nirTreeRoot\"></li>");
	    $("#nirTreeRoot").fancytree({
	    	extensions: ["childcounter"],
	    	childcounter: {deep: false, hideZeros: true, hideExpanded: false},
	    	checkbox: true,
	    	imagePath: '/niis/images/sub/',
	    	autoscroll: true,
	    	selectMode: 3,
	        click: function(event, data) {
                var node = data.node;
                if (!node.folder){
                	addMarker(state, node.data);
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
		        	if(node.extraClasses.indexOf("nir_") > -1 && node.expanded){
			        	var zoneCode = node.data.zoneCode;
	    				var phCourse = node.data.phCourse;
	    				var securityCde = node.data.securityCde;
	    				node.extraClasses = "";
	    				
	    				searchNirImgDataList(node, zoneCode, phCourse, securityCde);
		        	}
		        }
		    },
		    source: [{"title": "NIR","key": state,'folder': true}]
	    });
	    nirnode = $("#nirTreeRoot").fancytree("getTree").getNodeByKey(state);
		break;
	case "5" :
		$("#combineTreeMenu > ul").append("<li id=\"tdsTreeRoot\"></li>");
	    $("#tdsTreeRoot").fancytree({
	    	extensions: ["childcounter"], 
	    	childcounter: {deep: false,hideZeros: true,hideExpanded: false}, 
	    	checkbox: true, 
	    	imagePath: '/niis/images/sub/', 
	    	autoscroll: true, 
	    	selectMode: 3,
	        click: function(event, data) {
	        	var node = data.node;
	        	if (!node.folder){
	        		addMarker(state, node.data);
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
		        	if(node.extraClasses.indexOf("tds_") > -1 && node.expanded){
			        	var zoneCode = node.data.zoneCode;
	    				var utl3dMpn = node.data.utl3dMpn;
	    				node.extraClasses = "";
	    				
	    				searchTdsImgDataList(node, zoneCode, utl3dMpn);
		        	}
		        }
		    },
			source: [{"title": "3차원객체","key": state,'folder': true}]
		});
	    tdsnode = $("#tdsTreeRoot").fancytree("getTree").getNodeByKey(state);
		break;
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
	
	if (lidnode != null){
		lidnode.visit(function(node){
			node.setSelected(false);
		});	
	}
	
	if (nirnode != null){
		nirnode.visit(function(node){
			node.setSelected(false);
		});	
	}
	
	if (tdsnode != null){
		tdsnode.visit(function(node){
			node.setSelected(false);
		});	
	}
}

/**
 * 검색 타입별 Data Object 반환
 */
function getAreaTapTypeData(){
	var imgTypeObj = getSelecteImgType();
	var data = null;
	
	if (parseInt(areaTapType) == 0){
		if (searchaAdmCode == "00"){
			alert("행정구역을 선택해주세요!");
			return false;
		}else{
			data = {"imgType" : imgTypeObj, "sYear" : getStartYear(), "eYear" : getEndYear(), "zoneCode" : $("#zoneList option:selected").attr("id"), "sigunguCode" : searchaAdmCode};
		}
	}else if (parseInt(areaTapType) == 4){
		if (inexMapGeom != null){
			data = {"imgType" : imgTypeObj, "sYear" : getStartYear(), "eYear" : getEndYear(), "zoneCode" : $("#zoneList option:selected").attr("id"), "bounds" : utmToTmToString(inexMapGeom.toString())};
		}else{
			alert('도엽을 선택해주세요!');
			$('indexMapName').focus();
			return false;
		}
	}else{
		var bbox = [];
		var bounds = circleLayer.getDataExtent();
		if (bounds != null){
			bbox.push(bounds.left);
			bbox.push(bounds.bottom);
			bbox.push(bounds.right);
			bbox.push(bounds.top);
			data = {"imgType" : imgTypeObj, "sYear" : getStartYear(), "eYear" : getEndYear(), "zoneCode" : $("#zoneList option:selected").attr("id"), "bounds" : utmToTmToString(bbox.toString()), "radius" : amountObj.val()};
		}else{
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
	clearPopup();
	removeAllMarker();
	removeImgPolygon();
	removeIndexPolygon();
	removeImgBoundsPolygon();
	removeZoneAreaPolygon();
	removeAirImgLayers();
	unSelectedItem();
	
	var imgTypeObj = getSelecteImgType();
	var paramData = getAreaTapTypeData();
	
	if(paramData == false){
		return;
	}
	
	if (imgTypeObj.indexOf(",") > 0){
		var temp = imgTypeObj.split(",");
		for (var i = 0; i < temp.length; i++){
			searchImage(temp[i], paramData);
		}
	}else{
		searchImage(imgTypeObj, paramData);
	}
	
	$('.btnParentHide').parents('.top').find('.parentWrap').fadeOut(600,function(){
		scroll();
	});
	$('.btnCloseOpen').addClass('active');
}

/**
 * 영상종류 선택 확인
 * @param type
 */
function searchImage(type, paramData){
	switch(type){
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
		//searchLidImgList();
		searchLidImgFolderList(paramData);
		break;
	case "4" :
		//searchNirImgList();
		searchNirImgFolderList(paramData);
		break;
	case "5" :
		//searchTdsImgList();
		searchTdsImgFolderList(paramData);
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
 * 통합검색 - 근적외선
 */
function searchNirImgFolderList(paramData){
    
    if(paramData != false){
    	
        $(nirnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/nirImgFolderList.do", paramData, function(result){
        	
        	nirnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						nirnode.addChildren(value);
					}
				});
			}
			$(nirnode.li).children().removeClass('fancytree-loading');
			nirnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(nirnode.li).children().removeClass('fancytree-loading');
        });
    }
}

function searchNirImgDataList(node, zoneCode, phCourse, securityCde){
	
	var data = beforeSearchParam;
	data["imgType"] 	= "4";
	data["zoneCode"] 	= zoneCode;
	data["phCourse"] 	= phCourse;
	data["securityCde"] = securityCde;
	
	searchImgDataList("/search/nirImgDataList.do", node, data);
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

/**
 * 통합검색 - 라이다
 */
function searchLidImgFolderList(paramData){
    
    if(paramData != false){
    	
        $(lidnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/lidImgFolderList.do", paramData, function(result){
        	
        	lidnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						lidnode.addChildren(value);
					}
				});
			}
			$(lidnode.li).children().removeClass('fancytree-loading');
			lidnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(lidnode.li).children().removeClass('fancytree-loading');
        });
    }
}

function searchLidImgDataList(node, zoneCode, securityCde){
	
	var data = beforeSearchParam;
	data["imgType"] 	= "4";
	data["zoneCode"] 	= zoneCode;
	data["securityCde"] = securityCde;
	
	searchImgDataList("/search/lidImgDataList.do", node, data);
}

/**
 * 통합검색 - 3차원객체
 */
function searchTdsImgFolderList(paramData){
    
    if(paramData != false){
    	
        $(tdsnode.li).children().addClass('fancytree-loading');
        
        ajaxCallJson("/search/tdsImgFolderList.do", paramData, function(result){
        	
        	tdsnode.removeChildren();
			if (result != null){
				$.each(result, function( index, value ) {
					if (value != null){
						tdsnode.addChildren(value);
					}
				});
			}
			$(tdsnode.li).children().removeClass('fancytree-loading');
			tdsnode.setExpanded(true);
			
			beforeSearchParam = paramData;
			
        }, function(result){
        	$(tdsnode.li).children().removeClass('fancytree-loading');
        });
    }
}

function searchTdsImgDataList(node, zoneCode, utl3dMpn){
	
	var data = beforeSearchParam;
	data["imgType"] 	= "5";
	data["zoneCode"] 	= zoneCode;
	data["utl3dMpn"] 	= utl3dMpn;
	
	searchImgDataList("/search/tdsImgDataList.do", node, data);
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
 * 명칭 검색
 */
function searchPOI(){
	
	var keyword = $("#poiKeyword").val().trim();
	
	if (keyword == "") {
		alert("검색어를 입력해 주세요!");
		$("#poiKeyword").focus();
		return;
	}
	filterBtnChange("poiCodeBtn", "", 0);
	
	var data = {
			"keyword"	: keyword
	}

	ajaxCallJson("/search/searchPOI.do", data, function(result){
		
		if(result != null){
			$("#poiList").mCustomScrollbar("destroy");
			$("#poiList").empty();
			$("#poiCnt").text(result.list.length);
			for(var i=0; i<result.list.length; i++){
				
				var poiNam = result.list[i]["poi_nam"];
				var coorX  = result.list[i]["coor_x"];
				var coorY  = result.list[i]["coor_y"];
				
				//$("#poiList").append("<tr><td onclick=\"searchOfDistanse('"+poiNam+ "','"+coorX+ "','"+coorY+ "',1 , 'amount');\">"+poiNam+"</td></tr>");
				$("#poiList").append("<li><a href=\"#none\" onclick=\"javascript:searchOfDistanse('"+poiNam+ "','"+coorX+ "','"+coorY+ "',1 , 'amount')\">"+poiNam+"</a></li>");
			}
			$("#poiList").children(":last").addClass("last");
			$("#poiList").mCustomScrollbar();
		}
		filterBtnChange("poiCodeBtn", "검색", 1);
	}, function(result) {
		window.console.log('POI 검색 실패!');
		filterBtnChange("poiCodeBtn", "검색", 1);
	});
}

function searchOfDistanse(name, x, y, state, target){
	var point;
	if (state == 1){
		point = transformLonLat(x, y, tmm, utmk);
//		x = point.x;
//		y = point.y;
	}
	_Map2D.setCenter(new OpenLayers1.LonLat(x, y), 6);
	drawCircle(x, y, $("#"+target).val());
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

var inexMapGeom;

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
	
	inexMapGeom = bounds;
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


var airImgLayers = [];
/**
 * 영상 이미지 요청
 * @param type
 * @param minx
 * @param miny
 * @param maxx
 * @param maxy
 * @param layer
 */
function requestImage(type, minx, miny, maxx, maxy, layer){
	return;
}
function requestImageAlone(key, type, minx, miny, maxx, maxy, layer){
	var obj = [];
	
	if (layer.indexOf("s") == (layer.length-1)){
		layer = layer.substring(0, layer.length-1);
	}
	
	var bound = tmToUtm(minx, miny, maxx, maxy);
	minx = bound[0];
	miny = bound[1];
	maxx = bound[2];
	maxy = bound[3];
	
	var extent = minx+","+miny+","+maxx+","+maxy;
	var str = minx + "," + miny + " " + maxx + "," + miny + " " + maxx + "," + maxy + " " + minx + "," + maxy + " " + minx + "," + miny;
	
	obj.push(str);
	imgBoundsPolygon(obj, "");
	removeAirImgLayers();
	
	airImgLayer = new OpenLayers1.Layer.WMS("PicLayer_Air", 
			"/niis/tms/nix/"+type+"s.do",
			{
				maxExtent:extent,
				TRANSPARENT: true, 
				layers: layer,
				transparent: true,
				version: "1.1.0",
				format : "image/png",
				isBaseLayer : false,
				transitionEffect: null,
				maxExtent:extent,
				singleTile: false
			  });
	airImgLayer.isBaseLayer = false;
	airImgLayer.setVisibility(true);
	airImgLayer.name = key;
	airImgLayers.push(airImgLayer);
    var renderer = OpenLayers1.Util.getParameters(window.location.href).renderer;
    renderer = (renderer) ? [renderer] : OpenLayers1.Layer.Vector.prototype.renderers;
	_Map2D.addLayers([airImgLayer]);
	_Map2D.setLayerZIndex(airImgLayer, 7);
}

function requestImageFromMarker(key, type, minx, miny, maxx, maxy, layer){
	
	for(var i=airImgLayers.length-1; i>=0; i--){
		
		if(key == airImgLayers[i].name){
			
			for(var j=_Map2D.layers.length-1; j>=0; j--){
				if(key == _Map2D.layers[j].name){
					_Map2D.layers.splice(j, 1);
				}
			}
			
			airImgLayers[i].setVisibility(false);
			airImgLayers.splice(i, 1);
			changeMarkerIcon(type, key, false);
			removeImgBoundsPolygon();
			return;
		}
	}
	
	/* 마커 생성시 변환좌표를 가지고 있음
	var bound = tmToUtm(minx, miny, maxx, maxy);
	minx = bound[0];
	miny = bound[1];
	maxx = bound[2];
	maxy = bound[3];
	*/
	
	var obj = [];
	var extent = minx+","+miny+","+maxx+","+maxy;
	var str = minx + "," + miny + " " + maxx + "," + miny + " " + maxx + "," + maxy + " " + minx + "," + maxy + " " + minx + "," + miny;
	
	if (layer.indexOf("s") == (layer.length-1)){
		layer = layer.substring(0, layer.length-1);
	}
	
	obj.push(str);
	imgBoundsPolygonNotZoom(obj, key);
	
	airImgLayer = new OpenLayers1.Layer.WMS(key, 
//			"http://192.168.0.118:8033/ngii/wms"+type+"s",
			"/niis/tms/nix/"+type+"s.do",
			{
				maxExtent:extent,
				TRANSPARENT: true, 
				layers: layer,
				transparent: true,
				version: "1.1.0",
				format : "image/png",
				isBaseLayer : false,
				transitionEffect: null,
				maxExtent:extent,
				singleTile: false
			});
	airImgLayer.isBaseLayer = false;
	airImgLayer.setVisibility(true);
	airImgLayers.push(airImgLayer);
	var renderer = OpenLayers1.Util.getParameters(window.location.href).renderer;
	renderer = (renderer) ? [renderer] : OpenLayers1.Layer.Vector.prototype.renderers;
	_Map2D.addLayers([airImgLayer]);
	_Map2D.setLayerZIndex(airImgLayer, 7);
}

/**
 * 영상 이미지 오버레이 레이어 삭제
 */
function removeAirImgLayers(){
	if(airImgLayers.length > 0) {
		for(var i = 0; i < airImgLayers.length; i++) {
			_Map2D.removeLayer(airImgLayers[i]);
		}
		airImgLayers = [];
	}
}

/**
 * 검색결과 마커 오버레이
 * @param poiX
 * @param poiY
 * @param sj
 * @param iconUrl
 * @param imgW
 * @param imH
 * @param html
 */
function addMarkerOverlay(poiX, poiY, sj, iconUrl, type, key, bounds, nixPath){
	if(!oMarkerManager){
		oMarkerManager = new MarkerManager();
	}
	
	var html = '';
	html += '<div class="mapPop" style="top:0px; left:0px; z-index:10000;">';
	html += '	<h3>항공사진/2012/1</h3>';
	html += '	<a href="#none" class="close"><img src="/niis/images/popup/btn_close_2.gif" alt="" /></a>';
	html += '	<div>';
	html += '		<a href="#none"><img src="/niis/images/sub/ic_1.png" alt="" /></a>';
	html += '		<a href="#none"><img src="/niis/images/sub/ic_2.png" alt="" /></a>';
	html += '		<a href="#none"><img src="/niis/images/sub/ic_3.png" alt="" /></a>';
	html += '	</div>';
	html += '</div>';
	
	switch(type){
	case "0" :
		oMarkerManager.airAddMarkerAndInfowindow(poiX, poiY, sj, iconUrl, 24, 24, html, key, bounds, nixPath);
		break;
	case "1" :
		oMarkerManager.ortAddMarkerAndInfowindow(poiX, poiY, sj, iconUrl, 24, 24, html, key, bounds, nixPath);
		break;
	case "2" :
		oMarkerManager.demAddMarkerAndInfowindow(poiX, poiY, sj, iconUrl, 24, 24, html, key, bounds, nixPath);
		break;
	case "3" :
		oMarkerManager.lidAddMarkerAndInfowindow(poiX, poiY, sj, iconUrl, 24, 24, html, key, bounds, nixPath);
		break;
	case "4" :
		oMarkerManager.nirAddMarkerAndInfowindow(poiX, poiY, sj, iconUrl, 24, 24, html, key, bounds, nixPath);
		break;
	case "5" :
		oMarkerManager.tdsAddMarkerAndInfowindow(poiX, poiY, sj, iconUrl, 24, 24, html, key, bounds, nixPath);
		break;
	}
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
function searchOrtMetaData(zoneCode, map5000Num, gtypDst, zoneNam){
	
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
function searchDemMetaData(zoneCode, map5000Num, gridInt, zoneNam){
	
	var data = {
			"zoneCode"		: zoneCode, 
			"map5000Num"	: map5000Num,
			"gridInt"		: gridInt
	}
	
	ajaxCallPop("/search/getDemMetaInfo.do", data);
}