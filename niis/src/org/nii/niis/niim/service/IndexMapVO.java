package org.nii.niis.niim.service;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class IndexMapVO implements Serializable {
	
	private String map_nam = "";
	private String map_num = "";
	private String objectid = "";
	private String shape_area = "";
	private String shape_len = "";
	private float xmin = -1;
	private float ymin = -1;
	private float xmax = -1;
	private float ymax = -1;
	private byte[]  wkb_geometry = null;
	
	Geometry geom = null;
	
	public String getMap_nam() {
		return map_nam;
	}

	public void setMap_nam(String map_nam) {
		this.map_nam = map_nam;
	}

	public String getMap_num() {
		return map_num;
	}

	public void setMap_num(String map_num) {
		this.map_num = map_num;
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getShape_area() {
		return shape_area;
	}

	public void setShape_area(String shape_area) {
		this.shape_area = shape_area;
	}

	public String getShape_len() {
		return shape_len;
	}

	public void setShape_len(String shape_len) {
		this.shape_len = shape_len;
	}

	public float getXmin() {
		return xmin;
	}

	public void setXmin(float xmin) {
		this.xmin = xmin;
	}

	public float getYmin() {
		return ymin;
	}

	public void setYmin(float ymin) {
		this.ymin = ymin;
	}

	public float getXmax() {
		return xmax;
	}

	public void setXmax(float xmax) {
		this.xmax = xmax;
	}

	public float getYmax() {
		return ymax;
	}

	public void setYmax(float ymax) {
		this.ymax = ymax;
	}

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



}
