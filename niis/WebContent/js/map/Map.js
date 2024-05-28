//********** 전역객체 **********
var GOLD_Z_INDEX = 15;

Proj4js.defs['중부'] = '+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=600000 +ellps=GRS80 +units=m +no_defs';
Proj4js.defs['UTM-K'] = '+title=UTM_K +proj=tmerc +ellps=GRS80 +datum=WGS84 +lat_0=38.0 +lon_0=127.5 +x_0=1000000.0 +y_0=2000000.0 +k=0.9996 +a=6378137.0 +b=6356752.3141403';
Proj4js.defs['EPSG:5174'] = '+proj=tmerc +lat_0=38 +lon_0=127.0028902777778 +k=1 +x_0=200000 +y_0=500000 +ellps=bessel +units=m +no_defs +towgs84=-115.80,474.99,674.11,1.16,-2.31,-1.63,6.43';

var tmm = new Proj4js.Proj('중부');
var utmk = new Proj4js.Proj('UTM-K');
var bessel = new Proj4js.Proj('EPSG:5174');


/**
 * 좌표변환 
 */
function transformLonLat(x1, y1, srcProj, dstProj) {
	var p = new Proj4js.Point(parseFloat(x1), parseFloat(y1));
	p = Proj4js.transform(srcProj, dstProj, p);
	return p;
}



function tmToUtm(xmin, ymin, xmax, ymax) {
	var minPoint = transformLonLat(xmin, ymin, tmm, utmk);
	var maxPoint = transformLonLat(xmax, ymax, tmm, utmk);

	var bound = new Array();
	bound[0] = minPoint.x;
	bound[1] = minPoint.y;
	bound[2] = maxPoint.x;
	bound[3] = maxPoint.y;

	return bound;
}

function utmToTm(xmin, ymin, xmax, ymax) {
	var minPoint = transformLonLat(xmin, ymin, utmk, tmm);
	var maxPoint = transformLonLat(xmax, ymax, utmk, tmm);

	var bound = new Array();
	bound[0] = minPoint.x;
	bound[1] = minPoint.y;
	bound[2] = maxPoint.x;
	bound[3] = maxPoint.y;

	return bound;
}

function utmToTmToString(utmBound) {
	var bound = utmBound.split(",");
	var minPoint = transformLonLat(bound[0], bound[1], utmk, tmm);
	var maxPoint = transformLonLat(bound[2], bound[3], utmk, tmm);
	var tmBound = minPoint.x + "," + minPoint.y + "," + maxPoint.x + "," + maxPoint.y;

	return tmBound;
}


/** 
 * 반경 검색 레이어 삭제
 */
function removeCircle() {
	rinoGIS.ol.map.getLayers().forEach((lyr) => {
		if (lyr instanceof ol.layer.Vector) {
			if (lyr.get('drawCircle')) {
				rinoGIS.ol.map.removeLayer(lyr);
			}
		}
	});
}
function removeRadius() {
	rinoGIS.ol.map.getLayers().forEach((lyr) => {
		if (lyr instanceof ol.layer.Vector) {
			if (lyr.get('drawRadius')) {
				rinoGIS.ol.map.removeLayer(lyr);
			}
		}
	});
}

/**
 * 반경 검색
 * (명칭, 지번, 도로명)
 */
function drawCircle(x, y, target) {
	var radius = Number($("#"+target).val());
	
	//반경 검색 레이어 삭제
	removeCircle();
	removeRadius();
	
	//반경 원 스타일
	var circleStyle = new ol.style.Style({
		fill: new ol.style.Fill({
			color: 'rgba(252, 161, 1, 0.4)'
		}),
        stroke: new ol.style.Stroke({
			color: 'rgba(252, 161, 1, 1)',
			width: 2
		})
	});
	
	//반지름 라인 스타일
	var radiusStyle = new ol.style.Style({
		stroke: new ol.style.Stroke({
			color: 'blue', // Stroke color for the radius line
			width: 2 // Stroke width in pixels
		}),
		text: new ol.style.Text({
			text: radius + " m",
			fill: new ol.style.Fill({
				color: '#0500bd'
			}),
			stroke: new ol.style.Stroke({
				width: 2,
				color: '#ffffff'
			}),
			placement: 'point',
			textAlign: 'start',
			textBaseline: 'bottom',
			font: 'bold 15px NotoSansCJKB'
		}),
	});
	
	
//	var coordinate = rinoGIS.ol.map.getView().getCenter(); //EPSG:5179
	var coordinate = rinoGIS.ol.map.transform([x,y], "EPSG:4326", "EPSG:5179");
	
	//반경 원
	var circle = new ol.geom.Circle(coordinate, radius);
	var circleFeature = new ol.Feature(circle);
	
	//원을 wkt 형식으로 변환한다.
	var circlePolygon = new ol.geom.Polygon.fromCircle(circle);
	var wkt = new ol.format.WKT();
	var wktTxt = wkt.writeGeometry(circlePolygon);
	$("#amount_wkt").val(wktTxt);
	
    var circleSource = new ol.source.Vector({
		features: [circleFeature]
	});
	
	var pointStyle = new ol.style.Style({
		image: new ol.style.Circle({
			radius: 5,
            fill: new ol.style.Fill({
				color: 'red'
            })
		}),
		geometry: function() {
			return new ol.geom.Point(coordinate);
		}
	});

    var circleLayer = new ol.layer.Vector({
		source: circleSource,
		style: [circleStyle, pointStyle]
    });
	
	circleLayer.set("drawCircle", true);
	rinoGIS.ol.map.addLayer(circleLayer);
	
	//반지름 라인
	var radiusCoordinates = [
		coordinate,
		[coordinate[0] + radius, coordinate[1]] // Endpoint of the radius line
	];		

    var radiusLineFeature = new ol.Feature({
		geometry: new ol.geom.LineString(radiusCoordinates)
    });

	var radiusSource = new ol.source.Vector({
		features: [radiusLineFeature]
	})

	var radiusLayer = new ol.layer.Vector({
		source: radiusSource,
		style: radiusStyle
    });
	
	radiusLayer.set("drawRadius", true);
	rinoGIS.ol.map.addLayer(radiusLayer);
	
}

/**
 * 영역버퍼 변경
 */
function changeCircleRadius(target) {

	//반경 검색 레이어 삭제
	removeCircle();
	removeRadius();
	
	var radius = Number($("#"+target).val());
	
	//반경 원 스타일
	var circleStyle = new ol.style.Style({
		fill: new ol.style.Fill({
			color: 'rgba(252, 161, 1, 0.4)'
		}),
        stroke: new ol.style.Stroke({
			color: 'rgba(252, 161, 1, 1)',
			width: 2
		})
	});
	
	//반지름 라인 스타일
	var radiusStyle = new ol.style.Style({
		stroke: new ol.style.Stroke({
			color: 'blue', // Stroke color for the radius line
			width: 2 // Stroke width in pixels
		}),
		text: new ol.style.Text({
			text: radius + " m",
			fill: new ol.style.Fill({
				color: '#0500bd'
			}),
			stroke: new ol.style.Stroke({
				width: 2,
				color: '#ffffff'
			}),
			placement: 'point',
			textAlign: 'start',
			textBaseline: 'bottom',
			font: 'bold 15px NotoSansCJKB'
		})
	});
	
	var coordinate = rinoGIS.ol.map.getView().getCenter(); //EPSG:5179
	
	//반경 원
	var circle = new ol.geom.Circle(coordinate, radius);
	var circleFeature = new ol.Feature(circle);
	
	var circleSource = new ol.source.Vector({
		features:  [circleFeature]
	});
	
	var pointStyle = new ol.style.Style({
		image: new ol.style.Circle({
			radius: 5,
            fill: new ol.style.Fill({
				color: 'red'
            })
		}),
		geometry: function() {
			return new ol.geom.Point(coordinate);
		}
	});

    var circleLayer = new ol.layer.Vector({
		source: circleSource,
		style: [circleStyle, pointStyle]
    });
	
	circleLayer.set("drawCircle", true);
	rinoGIS.ol.map.addLayer(circleLayer);
	
	//반지름 라인
	var radiusCoordinates = [
		coordinate,
		[coordinate[0] + radius, coordinate[1]] // Endpoint of the radius line
	];		

    var radiusLineFeature = new ol.Feature({
		geometry: new ol.geom.LineString(radiusCoordinates)
    });
	
	var radiusSource = new ol.source.Vector({
		features: [radiusLineFeature]
	})

	var radiusLayer = new ol.layer.Vector({
		source: radiusSource,
		style: radiusStyle
    });
	
	radiusLayer.set("drawRadius", true);
	rinoGIS.ol.map.addLayer(radiusLayer);
}


