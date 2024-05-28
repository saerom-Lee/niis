//******************************

/**
 * 마커 찍기
 * @param state : 영상종류
 * @param data : 검색조건
 */
function getZoneMarker(state, data, icon){
	
	//지도 마커, 테두리, 이미지 삭제
	rinoGIS.ol.map.removeTempOverlay();
	removeAllImages();
	rinoGIS.ol.map.getLayerById(207).setVisible(false);
	
	data["imgType"] = state;
	data["state"] 	= state;
	
	
	ajaxCallJson("/search/getImgMarkerList.do", data, function(result){
		if (result.result != null){
			var markerSource = new ol.source.Vector();
			var markerLayer = new ol.layer.Vector({
				source : markerSource
			});
			markerLayer.set("markerLayer", true);
			
			$.map(result.result, function(node, i) {

				var centerPoint = new ol.extent.getCenter([node.xmin, node.ymin, node.xmax, node.ymax]);
				
				var a = document.createElement('a');
				a.href = "javascript:onMarkerClick(" + JSON.stringify(node) + ", " + i + ", " + state + ", \"" + icon + "\")";
				
				var img = document.createElement('img');
				img.src = "/niis/images/icon/24x24/" + icon + ".png";
				img.className = "markerIcon";
				img.id = "marker_" + i;
				
				var p = document.createElement('p');
				p.innerText = getMarkerName(state, node);
				
				a.appendChild(img);
				a.appendChild(p);

				var overlayOption = {
					id: 'markerOverlay_' + i,
					element: a,
					positioning: 'center-center',
				};

				rinoGIS.ol.map.addTempOverlay(overlayOption, centerPoint, 'EPSG:5179');
				
				var markerFeature = new ol.Feature({
					geometry: new ol.geom.Point(centerPoint)
				});
				markerSource.addFeature(markerFeature);
			});
			rinoGIS.ol.map.getView().fit(markerSource.getExtent());
			rinoGIS.ol.map.removeLayer(markerLayer);
		}
	});
}

function onMarkerClick(node, idx, state, icon) {
	
	$("#marker_"+idx).toggleClass("active");

	if ($("#marker_"+idx).hasClass("active")) {
		$("#marker_"+idx).attr("src", "/niis/images/icon/x.png");
		requestImageFromMarker(state, node.xmin, node.ymin, node.xmax, node.ymax, node.nixPath, idx);
	} else {
		$("#marker_"+idx).attr("src", "/niis/images/icon/24x24/" + icon + ".png");
		removeMarkerImage(idx);
	}
	
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


/**
 * 마커 영상 이미지 제거
 */
function removeMarkerImage(idx) {
	rinoGIS.ol.map.getLayers().forEach((lyr) => {
		if (lyr instanceof ol.layer.Image) {
			if (lyr.get('marker_'+idx)) {
				rinoGIS.ol.map.removeLayer(lyr);
			}
		}
	});
}

/**
 * 마커 영상 이미지 요청
 */
function requestImageFromMarker(key, minx, miny, maxx, maxy, layer, idx){
	var path = layer;
	
	if(key == '1') {
		path = path.replace("/ort/", "/ort_jpg/");
		path = path.replace("8cm", "51cm");
		path = path.replace("25cm", "51cm");
		path = path.replace("12cm", "51cm");
		path = path.replace("TIF", "jpg");
		path = path.replace("tif", "jpg");
		
	} else if(key == '2') {
		path = path.replace("/dem/", "/dem_jpg/");
	}
	
	var imgSource = new ol.source.ImageStatic({
		url: "http://192.168.0.101:1080/iim_files" + path,
		//url: "http://10.98.25.14:9000/iim_files" + path,
        projection: rinoGIS.ol.map.getView().getProjection(),
        imageExtent: [minx, miny, maxx, maxy],
	});
	
	imgSource.on('imageloaderror', function() {
		alert("미리보기 파일이 없습니다.");
	});
	
	var imgLayer = new ol.layer.Image({
		source: imgSource
	});
	
	imgLayer.set('marker_'+idx, true);
	rinoGIS.ol.map.addLayer(imgLayer);
	rinoGIS.ol.map.getView().fit([minx, miny, maxx, maxy]);
	
	return;
}




/**
 *  마커 polygon
 */
function drawMarkerExtent(xmin, ymin, xmax, ymax, idx) {
	var markerPolygonStyle = new ol.style.Style({
		fill: new ol.style.Fill({
			color: 'rgba(213, 213, 213, 0.5)'
		}),
        stroke: new ol.style.Stroke({
			color: 'rgba(0, 84, 255, 0.5)',
			width: 2
		})
	});
	
	var tempLayer = new ol.layer.Vector({
		source : new ol.source.Vector({
			features: [new ol.Feature({
				geometry: new ol.geom.Polygon.fromExtent([xmin, ymin, xmax, ymax])
			})]
		}),
		style: markerPolygonStyle
	});
	
	tempLayer.set('markerExtent', true);
	tempLayer.set('marker_'+idx, true);
	rinoGIS.ol.map.addLayer(tempLayer);
}


//******************************