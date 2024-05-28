package org.nii.niis.niim.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 사업지구 entity 객체
 * @stereotype entity 
 */
@SuppressWarnings("serial")
public class ParamVO  implements Serializable {
	/**
	 * 검색어
	 */
	private String keyword 				= "";
	/**
	 * 영상종류 검색 파라미터
	 */
	private String imgType 				= "";
	/**
	 * 검색 시작 촬영년도
	 */
	private String startYear 			= "";
	/**
	 * 검색 종료 촬영년도
	 */
	private String endYear 				= "";
	/**
	 * 사업지구 코드
	 */
	private String zoneCode				= "";
	/**
	 * 코스번호
	 */
	private String phCourse				= "";
	/**
	 * 사진번호
	 */
	private String phNum				= "";
	/**
	 * 메타데이터 작성일
	 */
	private String metaDate				= "";
	
	/**
	 * 시군구 코드
	 */
	private String sigunguCode			= "";
	private String admcd				= "";
	/**
	 * 행정구역 테이블명
	 */
	private String tableNm				= "";
	
	private String airTargetName		= "";
	private String nirTargetName		= "";
	private String airImgType			= "";
	private String ortImgType			= "";
	private String demImgType			= "";
	private String larImgType			= "";
	private String nirImgType			= "";
	private String tdsImgType			= "";
	private String atImgType			= "";
	
	/**
	 * 최소 X좌표
	 */
	private float xmin					= 0;
	/**
	 * 최소 Y좌표
	 */
	private float ymin					= 0;
	/**
	 * 최대 X좌표
	 */
	private float xmax					= 0;
	/**
	 * 최대 Y좌표
	 */
	private float ymax					= 0;
	
	private int radius					= 0;
	
	private String geometry;
	
	/**
	 * 지번 - 산여부
	 */
	private String mntnYn				= "";
	/**
	 * 지번 - 본번
	 */
	private String lnbrMnnm				= "";
	/**
	 * 지번 - 부번
	 */
	private String lnbrSlno				= "";
	
	/**
	 * 지번 - 도로명코드
	 */
	private String rnCd					= "";
	
	/**
	 * 지번 - 동코드
	 */
	private String emdCd				= "";
	
	/**
	 * 보안등급
	 */
	private String securityCde			= "";
	
	/**
	 * 도엽번호(3차원 객체)
	 */
	private String utl3dMpn				= "";
	
	/**
	 * 지상표본거리(정사영상)
	 */
	private String gtypDst				= "";
	
	/**
	 * 격자간격(정사영상)
	 */
	private String gridInt				= "";
	
	/**
	 * map5000_num 앞 5자리 지역코드
	 */
	private String mapNum				= "";
	
	private String fileExt				= "";
	
	private String stoImgPath;
	
	/**
	 * xacml 정책 정보
	 */
	private Map<String, List<String>> policyInfo = new HashMap<String, List<String>>();
	private List<String> _IMG_AIR_POLICY_ = new ArrayList<String>();
	private List<String> _IMG_DEM_POLICY_ = new ArrayList<String>();
	private List<String> _IMG_ORT_POLICY_ = new ArrayList<String>();
	private List<String> _IMG_LID_POLICY_ = new ArrayList<String>();
	private List<String> _IMG_NIR_POLICY_ = new ArrayList<String>();
	private List<String> _IMG_TDS_POLICY_ = new ArrayList<String>();
	
	
	public String getStoImgPath() {
		return stoImgPath;
	}
	public void setStoImgPath(String stoImgPath) {
		this.stoImgPath = stoImgPath;
	}
	public String getImgType() {
		return imgType;
	}
	public void setImgType(String imgType) {
		this.imgType = imgType;
	}
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getPhCourse() {
		return phCourse;
	}
	public void setPhCourse(String phCourse) {
		this.phCourse = phCourse;
	}
	public String getPhNum() {
		return phNum;
	}
	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}
	public String getMetaDate() {
		return metaDate;
	}
	public void setMetaDate(String metaDate) {
		this.metaDate = metaDate;
	}
	public String getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
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
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getAirTargetName() {
		return airTargetName;
	}
	public void setAirTargetName(String airTargetName) {
		this.airTargetName = airTargetName;
	}
	public String getNirTargetName() {
		return nirTargetName;
	}
	public void setNirTargetName(String nirTargetName) {
		this.nirTargetName = nirTargetName;
	}
	public String getAirImgType() {
		return airImgType;
	}
	public void setAirImgType(String airImgType) {
		this.airImgType = airImgType;
	}
	public String getOrtImgType() {
		return ortImgType;
	}
	public void setOrtImgType(String ortImgType) {
		this.ortImgType = ortImgType;
	}
	public String getDemImgType() {
		return demImgType;
	}
	public void setDemImgType(String demImgType) {
		this.demImgType = demImgType;
	}
	public String getLarImgType() {
		return larImgType;
	}
	public void setLarImgType(String larImgType) {
		this.larImgType = larImgType;
	}
	public String getNirImgType() {
		return nirImgType;
	}
	public void setNirImgType(String nirImgType) {
		this.nirImgType = nirImgType;
	}
	public String getTdsImgType() {
		return tdsImgType;
	}
	public void setTdsImgType(String tdsImgType) {
		this.tdsImgType = tdsImgType;
	}
	public String getMntnYn() {
		return mntnYn;
	}
	public void setMntnYn(String mntnYn) {
		this.mntnYn = mntnYn;
	}
	public String getLnbrMnnm() {
		return lnbrMnnm;
	}
	public void setLnbrMnnm(String lnbrMnnm) {
		this.lnbrMnnm = lnbrMnnm;
	}
	public String getLnbrSlno() {
		return lnbrSlno;
	}
	public void setLnbrSlno(String lnbrSlno) {
		this.lnbrSlno = lnbrSlno;
	}
	public String getEmdCd() {
		return emdCd;
	}
	public void setEmdCd(String emdCd) {
		this.emdCd = emdCd;
	}
	public String getRnCd() {
		return rnCd;
	}
	public void setRnCd(String rnCd) {
		this.rnCd = rnCd;
	}
	
	public String getSecurityCde() {
		return securityCde;
	}
	public void setSecurityCde(String securityCde) {
		this.securityCde = securityCde;
	}
	
	public String getUtl3dMpn() {
		return utl3dMpn;
	}
	public void setUtl3dMpn(String utl3dMpn) {
		this.utl3dMpn = utl3dMpn;
	}
	
	public String getGtypDst() {
		return gtypDst;
	}
	public void setGtypDst(String gtypDst) {
		this.gtypDst = gtypDst;
	}
	
	public String getGridInt() {
		return gridInt;
	}
	public void setGridInt(String gridInt) {
		this.gridInt = gridInt;
	}
	
	public String getMapNum() {
		return mapNum;
	}
	public void setMapNum(String mapNum) {
		this.mapNum = mapNum;
	}
	
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	
	public Map<String, List<String>> getPolicyInfo() {
		return policyInfo;
	}
	public void setPolicyInfo(Map<String, List<String>> policyInfo) {
		this.policyInfo = policyInfo;
	}
	
	public List<String> get_IMG_AIR_POLICY_() {
		return _IMG_AIR_POLICY_;
	}
	public void set_IMG_AIR_POLICY_(List<String> _IMG_AIR_POLICY_) {
		this._IMG_AIR_POLICY_ = _IMG_AIR_POLICY_;
	}
	public List<String> get_IMG_DEM_POLICY_() {
		return _IMG_DEM_POLICY_;
	}
	public void set_IMG_DEM_POLICY_(List<String> _IMG_DEM_POLICY_) {
		this._IMG_DEM_POLICY_ = _IMG_DEM_POLICY_;
	}
	public List<String> get_IMG_ORT_POLICY_() {
		return _IMG_ORT_POLICY_;
	}
	public void set_IMG_ORT_POLICY_(List<String> _IMG_ORT_POLICY_) {
		this._IMG_ORT_POLICY_ = _IMG_ORT_POLICY_;
	}
	public List<String> get_IMG_LID_POLICY_() {
		return _IMG_LID_POLICY_;
	}
	public void set_IMG_LID_POLICY_(List<String> _IMG_LID_POLICY_) {
		this._IMG_LID_POLICY_ = _IMG_LID_POLICY_;
	}
	public List<String> get_IMG_NIR_POLICY_() {
		return _IMG_NIR_POLICY_;
	}
	public void set_IMG_NIR_POLICY_(List<String> _IMG_NIR_POLICY_) {
		this._IMG_NIR_POLICY_ = _IMG_NIR_POLICY_;
	}
	public List<String> get_IMG_TDS_POLICY_() {
		return _IMG_TDS_POLICY_;
	}
	public void set_IMG_TDS_POLICY_(List<String> _IMG_TDS_POLICY_) {
		this._IMG_TDS_POLICY_ = _IMG_TDS_POLICY_;
	}
	public String getAtImgType() {
		return atImgType;
	}
	public void setAtImgType(String atImgType) {
		this.atImgType = atImgType;
	}
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	public String getAdmcd() {
		return admcd;
	}
	public void setAdmcd(String admcd) {
		this.admcd = admcd;
	}
	public String getTableNm() {
		return tableNm;
	}
	public void setTableNm(String tableNm) {
		this.tableNm = tableNm;
	}
	
	
}