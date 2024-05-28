var markerArr = new Array();
var infowindowArr = new Array();
var infoWindow = null;

// 마커 추가
function addMarker() {
	var size = new OpenLayers1.Size(27,39);
	var markerPoint = new OpenLayers1.Geometry.Point(_Map2D.getCenter().lon, _Map2D.getCenter().lat);
	
	// 정보창 컨텐츠 html 구성
	var html = '';
	html += '	<div class="layerPosition">';
	html += '		<h3>정보창 샘플</h3>';
	html += '		<div class="btnClose"><a href="#none" onclick="javascript:closeInfowin(); return false;"><img src="/OpenlayersTest/images/btn_designation_close.gif" alt="닫기" /></a></div>';
	html += '	</div>';
	
	this.markerArr[this.markerArr.length] = new OpenLayers1.Feature.Vector(
		markerPoint, 
	    {description:html}, // 마커 클릭시 정보창 컨텐츠 구성을 위한 html 저장
	    {
			externalGraphic: "/niis/images/ic_b_1.png", 
			graphicHeight: 39, 
			graphicWidth: 27, 
			graphicXOffset: -(size.w/2), 
			graphicYOffset: -size.h,
			label: "marker",
			fontSize: "13px",
			fontFamily: "굴림, 굴림체",
			labelOutlineColor: "#FFFFFF",
			labelOutlineWidth: 2,
			labelYOffset: -10,
			fontColor: "#0054FF"
	    }
	);    
	markersLayer.addFeatures(this.markerArr[this.markerArr.length-1]);
}

//마커 삭제(마지막으로 추가된 마커)
function removeMarker() {
	this.markerArr[this.markerArr.length-1].destroy();
	this.markerArr.splice(this.markerArr.length-1, 1);
}

//마커 모두 삭제
function removeAllMarker() {
	while(this.markerArr.length > 0){
		this.markerArr[this.markerArr.length-1].destroy();
		this.markerArr.splice(this.markerArr.length-1, 1);
	}
}

// 정보창 닫기버튼
function closeInfowin(){
	if(infoWindow)
	{
		_Map2D.removePopup(infoWindow); 
		//infoWindow.destroy(); 
		infoWindow = null; 
	}
}

function onFeatureSelect1(feature) {
	// 마커 index를 통한 정보창 인덱스 조절
	infoWindow = new OpenLayers1.Popup("info_", feature.geometry.getBounds().getCenterLonLat(), new OpenLayers1.Size(440,180),feature.attributes.description, false);
	infoWindow.closeOnMove = true;
	infoWindow.setBackgroundColor("");
	_Map2D.addPopup(infoWindow);
} 

function onFeatureUnselect1(feature) {

	if(infoWindow)
	{
		_Map2D.removePopup(infoWindow); 
		//infoWindow.destroy(); 
		infoWindow = null; 
	}
	
} 