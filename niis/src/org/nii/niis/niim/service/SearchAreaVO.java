package org.nii.niis.niim.service;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class SearchAreaVO implements Serializable {
	int gid = -1;
	String ctprvn_nm = "";
	String signgu_nm = "";
	String dong_nm = "";
	String year = "";
	String admcd = "";
	float xmin = -1;
	float ymin = -1;
	float xmax = -1;
	float ymax = -1;
	byte[]  wkb_geometry = null;
	float annox = -1;
	float annoy = -1;
	int gcode = -1;
	String ctime = null;
	
	Geometry geom = null;
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getCtprvn_nm() {
		return ctprvn_nm;
	}
	public void setCtprvn_nm(String ctprvn_nm) {
		this.ctprvn_nm = ctprvn_nm;
	}
	public String getSigngu_nm() {
		return signgu_nm;
	}
	public void setSigngu_nm(String signgu_nm) {
		this.signgu_nm = signgu_nm;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getAdmcd() {
		return admcd;
	}
	public void setAdmcd(String admcd) {
		this.admcd = admcd;
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
	public byte[]  getWkb_geometry() {
		return wkb_geometry;
	}
	public void setWkb_geometry(byte[]  wkb_geometry) {
		this.wkb_geometry = wkb_geometry;
	}
	public float getAnnox() {
		return annox;
	}
	public void setAnnox(float annox) {
		this.annox = annox;
	}
	public float getAnnoy() {
		return annoy;
	}
	public void setAnnoy(float annoy) {
		this.annoy = annoy;
	}
	public int getGcode() {
		return gcode;
	}
	public void setGcode(int gcode) {
		this.gcode = gcode;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public Geometry getGeom() {
		return geom;
	}
	public void setGeom(Geometry geom) {
		this.geom = geom;
	}
	public String getDong_nm() {
		return dong_nm;
	}
	public void setDong_nm(String dong_nm) {
		this.dong_nm = dong_nm;
	}
}
