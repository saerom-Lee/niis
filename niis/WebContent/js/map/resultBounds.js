var pointBoundary = 5000;	// 한 포인트만 입력했을 때에 대한 bound 설정

/**
 * 3d 지도에서 setbound 구현을 위한 데이터 객체..
 * 입력 좌표는 google_m으로 받도록 한다!!!
 * @returns {ResultBound}
 */
ResultBound = function() {
	this.poiX = new Array();
	this.poiY = new Array();
	this.poiZ = new Array();
	this.boundX = false;
	this.boundY = false;
	this.boundZ = false;
	this.makedBound = false;
	this.minX = 9999999999;
	this.minY = 9999999999;
	this.maxX = -9999999999;
	this.maxY = -9999999999;
	this.maxZ = -9999999999;
};

ResultBound.prototype.getPoiX = function(index){ return this.poiX[index];};
ResultBound.prototype.getPoiY = function(index){ return this.poiY[index];};
ResultBound.prototype.getMaxPoiZ = function(){ return this.maxZ;};
ResultBound.prototype.getPoiCount = function(){ return this.poiX.length;};
ResultBound.prototype.getBounds = function(){
	if(this.makedBound){
		return new OpenLayers1.Bounds(this.minX, this.minY, this.maxX, this.maxY);
	} else {
		//alert("경계가 아직 계산되지 않았습니다. 계산후 호출하시기 바랍니다.");
		
		// 데이터가 하나만 들어왔을 때에도 getBound에 리턴은 해주어야 한다!!!
		if(this.minX < 9999999999){
			return new OpenLayers1.Bounds(this.minX - pointBoundary, this.minY - pointBoundary, this.maxX + pointBoundary, this.maxY + pointBoundary);
			
		}
	}
};

ResultBound.prototype.setPoiX = function(poiX){
	this.poiX[this.poiX.length] = poiX;
	if(this.minX > poiX){ this.minX = poiX; }
	if(this.maxX < poiX){ this.maxX = poiX; }
	
	if(this.minX > -9999999999 && this.maxX < 9999999999 && this.minX < this.maxX){ this.boundX = true; }
	if(this.boundX && this.boundY){ this.makedBound = true; }
};
ResultBound.prototype.setPoiY = function(poiY){
	this.poiY[this.poiY.length] = poiY;
	if(this.minY > poiY){ this.minY = poiY; }
	if(this.maxY < poiY){ this.maxY = poiY; }
	
	if(this.minY > -9999999999 && this.maxY < 9999999999 && this.minY < this.maxY){ this.boundY = true; }
	if(this.boundX && this.boundY){ this.makedBound = true; }
};
ResultBound.prototype.setPoiZ = function(poiZ){
	this.poiZ[this.poiZ.length] = poiZ;
	if(this.maxZ < poiZ){ this.maxZ = poiZ; }
	
	if(this.minY > -9999999999 && this.maxY < 9999999999 && this.minY < this.maxY){ this.boundY = true; }
	if(this.boundX && this.boundY){ this.makedBound = true; }
};
ResultBound.prototype.setPoi = function(poiX, poiY){
	this.setPoiX(poiX);
	this.setPoiY(poiY);

//	if(_Mode == 3){
//		this.setPoiZ(SOPPlugin.getView().getTerrHeight(poiX, poiY));
//	}
};

