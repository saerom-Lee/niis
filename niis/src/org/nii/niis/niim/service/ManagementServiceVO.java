package org.nii.niis.niim.service;

import java.io.Serializable;

/**
 * 사업지구 entity 객체
 * 
 * @stereotype entity
 */
@SuppressWarnings("serial")
public class ManagementServiceVO extends CommonVO implements Serializable {
	/**
	 * 사업종류
	 */
	private String zoneType			= "";
	/**
	 * 검색 시작 촬영년도
	 */
	private String sYear 			= "";
	/**
	 * 검색 종료 촬영년도
	 */
	private String eYear 			= "";
	
	public String getZoneType() {
		return zoneType;
	}
	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}
	public String getsYear() {
		return sYear;
	}
	public void setsYear(String sYear) {
		this.sYear = sYear;
	}
	public String geteYear() {
		return eYear;
	}
	public void seteYear(String eYear) {
		this.eYear = eYear;
	}
}