var measurePopup = "measurePopup";
var measureSymbolizers = {
	"Point" : {
		pointRadius : 5,
		graphicName : "circle",
		fillColor : "#FF9933",
		fillOpacity : 1,
		strokeWidth : 1,
		strokeOpacity : 1,
		strokeColor : "#FF9933"
	},
	"Line" : {
		strokeWidth : 2,
		strokeOpacity : 1,
		strokeColor : "#FF9933",
		strokeDashstyle : ""
	},
	"Polygon" : {
		strokeWidth : 2,
		strokeOpacity : 1,
		strokeColor : "#cc9999",
		fillColor : "#ee9900",
		fillOpacity : 0.2
	}
};

var measureStyle = new OpenLayers1.Style();
measureStyle.addRules([ new OpenLayers1.Rule({
	symbolizer : measureSymbolizers
}) ]);
var renderer = OpenLayers1.Util.getParameters(window.location.href).renderer;
renderer = (renderer) ? [ renderer ]
		: OpenLayers1.Layer.Vector.prototype.renderers;

var measureOption = {
	handlerOptions : {
		style : "default",
		layerOptions : {
			styleMap : new OpenLayers1.StyleMap({
				"default" : measureStyle
			})
		},
		renderers : renderer
	},
	persist : true,
	displaySystemUnits : {
		metric: ['m'],
	}
};
function handleMeasurements_start(event) {
	if(event.measure==0){
		clearPopup();
	}
}

//거리, 면적 재기 종료시 마커
function handleMeasurements_end(event) {
	
	var geometry = event.geometry;
	var units = event.units;
	var order = event.order;
	var measure = event.measure;
	var out = "";
	var result=dotGubun(measure.toFixed(1));
	var widthSize=result.length*10;
	if (order == 1) {
		out += "<table height='33px' style='width:100%;background-color:#1A2A51; background-repeat:no-repeat;'> ";
		out += "<tr><td style='padding:0px 8px 0px 4px;'>";
		out += "<table cellspacing='0' cellpadding='0' border='0' width='100%' align='center'>";
		out += "<tr><td style='text-align: right;'><font face='\"Open Sans\", sans-serif' size='2' color='white'>"
				+ result
				+ " "
				+ units
				+ "</font>"
				+ "</td>";
		out += "</tr></table>";
		out += "</tr></table>";
	} else {
		out += "<table height='33px' style='width:100%;background-color:#1A2A51; background-repeat:no-repeat;'> ";
		out += "<tr><td style='padding:0px 8px 0px 4px;'>";
		out += "<table cellspacing='0' cellpadding='0' border='0' width='100%' align='center'>";
		out += "<tr><td style='text-align: right;'><font face='\"Open Sans\", sans-serif' size='2' color='white'>"
				+ result
				+ " "
				+ units
				+ "<sup>2</font></td>";
		out += "</tr></table>";
		out += "</tr></table>";
	}
	
	// 거리/면적재기 닫기 버튼
	out +='<div id="measurePopup_close" class="olPopupCloseBox" style="width: 17px; height: 17px; position: absolute; right: 15px; top: 1px;"></div>';
	
	
	popup = new OpenLayers1.Popup(measurePopup, new OpenLayers1.LonLat(lonLatPosition.lon, lonLatPosition.lat), 
			new OpenLayers1.Size(widthSize, 24), out, true);

	if (controlsNow == 'area') {
		toggleControl('area');
		// popup 및 선택영역 삭제
		clearPopup();
	} else if (controlsNow == 'distance') {
		toggleControl('distance');
		// popup 및 선택영역 삭제
		clearPopup();
	} else if (controlsNow == 'navi' || controlsNow == 'zoombox') {
		control.deactivate();
	}

	_Map2D.addPopup(popup);
	
	
	//$(measurePopup).name = 'measurePopup';
	// 크롬에서 스크롤 생길때.. 이걸 해주면 없어진다.
	$('#measurePopup_contentDiv').attr('style', 'whidth:'+widthSize+'px; overflow:hidden; background-color:none;');
	$('#measurePopup_GroupDiv').attr('style', 'overflow:hidden; background-color:none;');
	$('#measurePopup_GroupDiv').html(out);
	
//	$('#measurePopup_contentDiv table').attr('style', 'background-color:none;');
	$('#measurePopup_close').attr('style', 'width: 20px; height: 20px; position: absolute; right: -10px; top: -10px;overflow:visible; ');
	$('#measurePopup').css('background', 'none');
	$('#measurePopup').css('overflow', 'visible');
	
	$('#measurePopup_close').click(function(){clearPopup();});
	
	
	popupState = false;
}
function clearPopup() {
	try {
		for ( var i = _Map2D.popups.length - 1; i >= 0; --i) {
			_Map2D.removePopup(_Map2D.popups[i]);
			_Map2D.popups[i].destroy;
		}
	} catch (e) {
	}
}
// 거리 면적 재기시 마커 숫자 계산 (11.5 *1000)
function dotGubun(value) {
	var dotStr = value.split(".");
	if (dotStr.length == 2) {
		var commaVal = dotStr[0].setComma();
		commaVal = commaVal + "." + dotStr[1];
	} else {
		commaVal = dotStr[0].setComma();
	}

	return commaVal;
}

String.prototype.setComma = function setComma() { 
	var temp_str = String(this);

	for(var i = 0 , retValue = String() , stop = temp_str.length; i < stop ; i++)
		retValue = ((i%3) == 0) && i != 0 ? temp_str.charAt((stop - i) -1) + "," + retValue : temp_str.charAt((stop - i) -1) + retValue; 

	return retValue; 
};