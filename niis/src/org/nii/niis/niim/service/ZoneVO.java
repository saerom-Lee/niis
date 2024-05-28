package org.nii.niis.niim.service;

import java.io.Serializable;

/**
 * 사업지구 entity 객체
 * @stereotype entity 
 */
@SuppressWarnings("serial")
public class ZoneVO  implements Serializable {
	/**
	 * 사업지구 코드
	 */
	private String zone_code 			= "";
	/**
	 * 지구명
	 */
	private String zone_nam 			= "";
	/**
	 * 사업명
	 */
	private String prj_nam 				= "";
	/**
	 * 사업년도
	 */
	private String zone_yy 				= "";
	/**
	 * 대표축척
	 */
	private int scale					= 0;
	/**
	 * 공간해상도
	 */
	private int gsd_val					= 0;
	/**
	 * 사업종류
	 */
	private String projecttype_cde 		= "";
	/**
	 * 카메라구분
	 */
	private String camera_cde 			= "";
	/**
	 * 구축구분 코드
	 */
	private String image_cde 			= "";
	/**
	 * 수치표고격자 크기
	 */
	private int dem_grid				= 0;
	/**
	 * 사업지구 설명
	 */
	private String remark 				= "";
	/**
	 * 사업정보 관리번호
	 */
	private String prj_info_mgno  		= "";
	public String getZone_code() {
		return zone_code;
	}
	public void setZone_code(String zone_code) {
		this.zone_code = zone_code;
	}
	public String getZone_nam() {
		return zone_nam;
	}
	public void setZone_nam(String zone_nam) {
		this.zone_nam = zone_nam;
	}
	public String getPrj_nam() {
		return prj_nam;
	}
	public void setPrj_nam(String prj_nam) {
		this.prj_nam = prj_nam;
	}
	public String getZone_yy() {
		return zone_yy;
	}
	public void setZone_yy(String zone_yy) {
		this.zone_yy = zone_yy;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getGsd_val() {
		return gsd_val;
	}
	public void setGsd_val(int gsd_val) {
		this.gsd_val = gsd_val;
	}
	public String getProjecttype_cde() {
		return projecttype_cde;
	}
	public void setProjecttype_cde(String projecttype_cde) {
		this.projecttype_cde = projecttype_cde;
	}
	public String getCamera_cde() {
		return camera_cde;
	}
	public void setCamera_cde(String camera_cde) {
		this.camera_cde = camera_cde;
	}
	public String getImage_cde() {
		return image_cde;
	}
	public void setImage_cde(String image_cde) {
		this.image_cde = image_cde;
	}
	public int getDem_grid() {
		return dem_grid;
	}
	public void setDem_grid(int dem_grid) {
		this.dem_grid = dem_grid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPrj_info_mgno() {
		return prj_info_mgno;
	}
	public void setPrj_info_mgno(String prj_info_mgno) {
		this.prj_info_mgno = prj_info_mgno;
	}
}