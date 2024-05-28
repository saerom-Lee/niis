package org.nii.niis.niim.service;

import java.io.Serializable;

import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class AddressSearchVO implements Serializable {
	private String admcd = "";
	private String ctprvn_nm = "";
	private String signgu_nm = "";
	private String dong_nm = "";
	private float xmin = -1;
	private float ymin = -1;
	private float xmax = -1;
	private float ymax = -1;
	private byte[]  wkb_geometry = null;
	private float annox = -1;
	private float annoy = -1;
	
	Geometry geom = null;

	public String getAdmcd() {
		return admcd;
	}
	public void setAdmcd(String admcd) {
		this.admcd = admcd;
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
