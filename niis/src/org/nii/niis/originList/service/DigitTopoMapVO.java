package org.nii.niis.originList.service;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @Date : 2023. 09. 04.
 * @description : 원본DB 목록 수치지형도 VO
 *
 */
@JsonIgnoreProperties(ignoreUnknown =true) 
public class DigitTopoMapVO{

	/** MAP_INFO_TBL, MAP_HiSTORY_TBL */
	// 이미지_코드
	private String imageCde;
	// 지도_순번
	private String mapSerNo;
	// 도엽_번호
	private String mapShtNo;
	// 도엽_명
	private String mapShtNm;
	// 지도_형태_구분_명(COMMON_CD_TBL@LK_MMS)
	private String mapFormDvsnNm;
	// 지도_형태_구분_코드 
	private String mapFormDvsnCd;
	// 지도_종류_명(COMMON_CD_TBL@LK_MMS)
	private String mapKindNm;
	// 지도_종류_코드
	private String mapKindCd;
	// 축척_명(COMMON_CD_TBL@LK_MMS)
	private String scaleNm;
	// 축척_코드
	private String scaleCd;
	// 좌표계_구분_명(COMMON_CD_TBL@LK_MMS)
	private String coordDvsnNm;
	// 좌표계_구분_코드
	private String coordDvsnCd;
	// 공개_구분_명(COMMON_CD_TBL@LK_MMS)
	private String openDvsnNm;
	// 공개_구분_코드
	private String openDvsnCd;
	// 사업_번호
	private String projectNo;
	// 제작_구분
	private String productDvsn;
	// 고시_번호
	private String noticeNo;
	// 고시_일자
	private String noticeDate;
	// 지도_이력_번호
	private String mapHistoryNo;
	// 제작_연도
	private String productYear;
	// 체크인_사용자_ID
	private String checkInUserId;
	// 체크인_일자
	private String checkInDate;
	// 체크인_사유
	private String checkInReason;
	// 조사_연도
	private String surveyYear;
	// 촬영_연도
	private String photoYear;
	// 매체_번호
	private String mediaNo;
	// 체크인_사유
	private String updateUserId;
	// 체크인_사유
	private String regDate;
	
	
	
	/** 수치지형도 신청서 작성 form*/
	// 기관명
	private String apPost;
	// 부서
	private String apDepartment;
	// 성명
	private String apName;
	// 연락처
	private String apTel;
	// 신청목적
	private String purpose;
	// 상세 신청목적
	private String detailPurpose;
	// 보안 및 자료관리 계획
	private String secDataMngPlan;
	// 신청정보 확인
	private String agreeYn;
	// etc
	private String etc;
	// 사용자 관리번호
	private String usrMgno;
	// 신청관리번호
	private String supIdn;
	// 신청목적코드
	private String codeId;
	
	//Paging
	/** 현재페이지 */
    private int pageIndex = 1;
    /** 페이지갯수 */
    private int pageUnit = 10;
    /** 페이지 사이즈 */
    private int pageSize = 10;
    /** firstIndex */
    private int firstIndex = 1;
    /** lastIndex */
    private int lastIndex = 1;
    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    
	
	
	public String getImageCde() {
		return imageCde;
	}
	public void setImageCde(String imageCde) {
		this.imageCde = imageCde;
	}
	public String getMapSerNo() {
		return mapSerNo;
	}
	public void setMapSerNo(String mapSerNo) {
		this.mapSerNo = mapSerNo;
	}
	public String getMapShtNo() {
		return mapShtNo;
	}
	public void setMapShtNo(String mapShtNo) {
		this.mapShtNo = mapShtNo;
	}
	public String getMapShtNm() {
		return mapShtNm;
	}
	public void setMapShtNm(String mapShtNm) {
		this.mapShtNm = mapShtNm;
	}
	public String getMapFormDvsnNm() {
		return mapFormDvsnNm;
	}
	public void setMapFormDvsnNm(String mapFormDvsnNm) {
		this.mapFormDvsnNm = mapFormDvsnNm;
	}
	public String getMapFormDvsnCd() {
		return mapFormDvsnCd;
	}
	public void setMapFormDvsnCd(String mapFormDvsnCd) {
		this.mapFormDvsnCd = mapFormDvsnCd;
	}
	public String getMapKindNm() {
		return mapKindNm;
	}
	public void setMapKindNm(String mapKindNm) {
		this.mapKindNm = mapKindNm;
	}
	public String getMapKindCd() {
		return mapKindCd;
	}
	public void setMapKindCd(String mapKindCd) {
		this.mapKindCd = mapKindCd;
	}
	public String getScaleNm() {
		return scaleNm;
	}
	public void setScaleNm(String scaleNm) {
		this.scaleNm = scaleNm;
	}
	public String getScaleCd() {
		return scaleCd;
	}
	public void setScaleCd(String scaleCd) {
		this.scaleCd = scaleCd;
	}
	public String getCoordDvsnNm() {
		return coordDvsnNm;
	}
	public void setCoordDvsnNm(String coordDvsnNm) {
		this.coordDvsnNm = coordDvsnNm;
	}
	public String getCoordDvsnCd() {
		return coordDvsnCd;
	}
	public void setCoordDvsnCd(String coordDvsnCd) {
		this.coordDvsnCd = coordDvsnCd;
	}
	public String getOpenDvsnNm() {
		return openDvsnNm;
	}
	public void setOpenDvsnNm(String openDvsnNm) {
		this.openDvsnNm = openDvsnNm;
	}
	public String getOpenDvsnCd() {
		return openDvsnCd;
	}
	public void setOpenDvsnCd(String openDvsnCd) {
		this.openDvsnCd = openDvsnCd;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getProductDvsn() {
		return productDvsn;
	}
	public void setProductDvsn(String productDvsn) {
		this.productDvsn = productDvsn;
	}
	public String getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getMapHistoryNo() {
		return mapHistoryNo;
	}
	public void setMapHistoryNo(String mapHistoryNo) {
		this.mapHistoryNo = mapHistoryNo;
	}
	public String getProductYear() {
		return productYear;
	}
	public void setProductYear(String productYear) {
		this.productYear = productYear;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public String getCheckInUserId() {
		return checkInUserId;
	}
	public void setCheckInUserId(String checkInUserId) {
		this.checkInUserId = checkInUserId;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckInReason() {
		return checkInReason;
	}
	public void setCheckInReason(String checkInReason) {
		this.checkInReason = checkInReason;
	}
	public String getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(String surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getPhotoYear() {
		return photoYear;
	}
	public void setPhotoYear(String photoYear) {
		this.photoYear = photoYear;
	}
	public String getMediaNo() {
		return mediaNo;
	}
	public void setMediaNo(String mediaNo) {
		this.mediaNo = mediaNo;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getApPost() {
		return apPost;
	}
	public void setApPost(String apPost) {
		this.apPost = apPost;
	}
	public String getApDepartment() {
		return apDepartment;
	}
	public void setApDepartment(String apDepartment) {
		this.apDepartment = apDepartment;
	}
	public String getApName() {
		return apName;
	}
	public void setApName(String apName) {
		this.apName = apName;
	}
	public String getApTel() {
		return apTel;
	}
	public void setApTel(String apTel) {
		this.apTel = apTel;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDetailPurpose() {
		return detailPurpose;
	}
	public void setDetailPurpose(String detailPurpose) {
		this.detailPurpose = detailPurpose;
	}
	public String getSecDataMngPlan() {
		return secDataMngPlan;
	}
	public void setSecDataMngPlan(String secDataMngPlan) {
		this.secDataMngPlan = secDataMngPlan;
	}
	public String getAgreeYn() {
		return agreeYn;
	}
	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getUsrMgno() {
		return usrMgno;
	}
	public void setUsrMgno(String usrMgno) {
		this.usrMgno = usrMgno;
	}
	public String getSupIdn() {
		return supIdn;
	}
	public void setSupIdn(String supIdn) {
		this.supIdn = supIdn;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	
	
	
}
