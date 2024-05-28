function changeIndexMap() {
	if ((_Map2D == null) || (indexMap == null)) {
		return;
	}

	var featureLayer = indexMap.getLayersByName("lyr_index_box")[0];
	if ((featureLayer == null) || (featureLayer == undefined)) {
		return;
	}
	featureLayer.destroyFeatures(featureLayer.features);

	var bounds = _Map2D.getExtent();
	var boxFeature;
	if (_Map2D.zoom < 6) {
		boxFeature = new OpenLayers1.Feature.Vector(bounds.toGeometry());
	} else {
		var cen = bounds.getCenterLonLat();
		bounds.left = cen.lon - 12000;
		bounds.top = cen.lat - 12000;
		bounds.right = cen.lon + 12000;
		bounds.bottom = cen.lat + 12000;
		boxFeature = new OpenLayers1.Feature.Vector(bounds.toGeometry(), null, {
			strokeColor: "#FF0000",
			strokeOpacity: 1,
			strokeWidth: 1,
			fillColor: "#FF0000",
			fillOpacity: 1});
	}
	featureLayer.addFeatures(boxFeature);

	indexMap.panTo(_Map2D.getCenter());
}

function indexMapClick (event) {
	var lonlat = indexMap.getLonLatFromViewPortPx(event.xy);
	_Map2D.panTo(lonlat);
}