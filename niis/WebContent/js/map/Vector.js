var polygonArr = new Array();
var polygonFeature = null;

function addPolygon() {
  	var pointList = [];
    var bounds = null;
    var points = null;
    var linearRing = null;
    var polygon = null;
    var polygons = [];
    
    // 폴리곤 좌표
    pointList.push(new OpenLayers1.Geometry.Point(_Map2D.getCenter().lon, _Map2D.getCenter().lat));
    var newPoint = new OpenLayers1.Geometry.Point(_Map2D.getCenter().lon+10000, _Map2D.getCenter().lat);
    pointList.push(newPoint);
    newPoint = new OpenLayers1.Geometry.Point(_Map2D.getCenter().lon+10000, _Map2D.getCenter().lat+20000);
    pointList.push(newPoint);
    newPoint = new OpenLayers1.Geometry.Point(_Map2D.getCenter().lon+4000, _Map2D.getCenter().lat+25000);
    pointList.push(newPoint);
    newPoint = new OpenLayers1.Geometry.Point(_Map2D.getCenter().lon-5000, _Map2D.getCenter().lat+5000);
    pointList.push(newPoint);
    pointList.push(new OpenLayers1.Geometry.Point(_Map2D.getCenter().lon, _Map2D.getCenter().lat));
    
    linearRing = new OpenLayers1.Geometry.LinearRing(pointList);
    polygon = new OpenLayers1.Geometry.Polygon([linearRing]);
    polygons.push(polygon);
    var multuPolygonGeometry = new OpenLayers1.Geometry.MultiPolygon(polygons);
    this.polygonArr[this.polygonArr.length] = new OpenLayers1.Feature.Vector(multuPolygonGeometry);
    this.polygonArr[this.polygonArr.length-1].attributes = {name:"Polygon", favColor:"red"};
    vectorLayer.addFeatures([this.polygonArr[this.polygonArr.length-1]]);
}

// 폴리곤 삭제(마지막으로 추가된 폴리곤)
function removePolygon() {
	vectorLayer.removeFeatures(this.polygonArr[this.polygonArr.length-1]);
	this.polygonArr.splice(this.polygonArr.length-1, 1);
}

// 폴리곤 모두 삭제
function removeAllPolygon() {
	while(this.polygonArr.length > 0){
		vectorLayer.removeFeatures(this.polygonArr[this.polygonArr.length-1]);
		this.polygonArr.splice(this.polygonArr.length-1, 1);
	}
}