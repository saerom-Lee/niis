package org.nii.niis.niim.service;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class PoiVO implements Serializable {
	private String poitype_cd = "";
	private String poi_nam = "";
	private String coor_x = "";
	private String coor_y = "";
	private byte[]  wkb_geometry = null;
	
	Geometry geom = null;
	
	public byte[] getWkb_geometry() {
		return wkb_geometry;
	}

	public void setWkb_geometry(byte[] wkb_geometry) {
		this.wkb_geometry = wkb_geometry;
	}

	public Geometry getGeom() {
		return geom;
	}

	public void setGeom(Geometry geom) {
		this.geom = geom;
	}

	public String getPoitype_cd() {
		return poitype_cd;
	}

	public void setPoitype_cd(String poitype_cd) {
		this.poitype_cd = poitype_cd;
	}

	public String getPoi_nam() {
		return poi_nam;
	}

	public void setPoi_nam(String poi_nam) {
		this.poi_nam = poi_nam;
	}

	public String getCoor_x() {
		return coor_x;
	}

	public void setCoor_x(String coor_x) {
		this.coor_x = coor_x;
	}

	public String getCoor_y() {
		return coor_y;
	}

	public void setCoor_y(String coor_y) {
		this.coor_y = coor_y;
	}



}
