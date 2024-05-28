package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import org.nii.niis.niim.service.ParamVO;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 사용자 정보 관리 DAO
 * 
 * 로그인, 회원가입, 개인정보 입력 기능을 제공
 */
@Repository("searchDAO")
public class SearchDAO extends EgovAbstractDAO {	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectZoneList(ParamVO param){
		return (List<?>) getSqlMapClientTemplate().queryForList("searchDAO.selectZoneList", param);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectAirImgList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectAirImgList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectAirImgFolderList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectAirImgFolderList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectAirImgDataList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectAirImgDataList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectAirLibImgFolderList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectAirLibImgFolderList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectAirLibImgDataList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectAirLibImgDataList", param);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectNirImgList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectNirImgList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectNirImgFolderList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectNirImgFolderList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectNirImgDataList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectNirImgDataList", param);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectAirImgYear(ParamVO param){
		return (List<?>) getSqlMapClientTemplate().queryForList("searchDAO.selectAirImgYear", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectAirImgCourse(ParamVO param){
		return (List<?>) getSqlMapClientTemplate().queryForList("searchDAO.selectAirImgCourse", param);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectOrtImgList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectOrtImgList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectOrtImgFolderList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectOrtImgFolderList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectOrtImgDataList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectOrtImgDataList", param);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectDemImgList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectDemImgList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectDemImgFolderList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectDemImgFolderList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectDemImgDataList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectDemImgDataList", param);
	}
	
	@SuppressWarnings("deprecation")
	public List<?> selectSuchiList(Map<String, Object> param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectSuchiList", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectLidImgList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectLidImgList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectLidImgFolderList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectLidImgFolderList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectLidImgDataList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectLidImgDataList", param);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectTdsImgList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectTdsImgList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectTdsImgFolderList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectTdsImgFolderList", param);
	}
	@SuppressWarnings("deprecation")
	public List<?> selectTdsImgDataList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectTdsImgDataList", param);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectNowestAirZoneAreaGeometry(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectNowestAirZoneAreaGeometry", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectNowestAirZone(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectNowestAirZone", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectZipCodeList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectZipList", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectJusoList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectJusoList", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectBuldNoList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectBuldNoList", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectPOIList(ParamVO param){
		return getSqlMapClientTemplate().queryForList("searchDAO.selectPOIList", param);
	}

	/**
	 * 통합검색 - 신청서목록
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectApplicationList() {
		return getSqlMapClientTemplate().queryForList("searchDAO.selectApplicationList");
	}

	/**
	 * 통합검색 - 워터마크 인덱스 생성
	 * @param idxParam
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Object insertIndex(Map<String, Object> idxParam) {
		return getSqlMapClientTemplate().insert("searchDAO.insertIndex", idxParam);
	}

	/**
	 * 통합검색 - 신청서 생성
	 * @param usrParam
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Object insertSupIdn(Map<String, Object> usrParam) {
		return getSqlMapClientTemplate().insert("searchDAO.insertSupIdn", usrParam);
	}

	/**
	 * 통합검색 - 1:50000 도엽 검색
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectIndexMapName() {
		return getSqlMapClientTemplate().queryForList("searchDAO.selectIndexMapName");
	}

	/**
	 * 통합검색 - 영상 사업지구 촬영년도 목록
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectYearList() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("searchDAO.selectYearList");
	}
	
	/**
	 * 통합검색 - 수치지형도 제작년도 목록
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectMapYearList() {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("searchDAO.selectMapYearList");
	}

	/**
	 * 통합검색 - 항공사진 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public Object insertAirSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().insert("searchDAO.insertAirSupplyItem", param);
	
	}
	
	/**
	 * 통합검색 - 수치표고 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public Object insertDemSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().insert("searchDAO.insertDemSupplyItem", param);
	
	}
	
	/**
	 * 통합검색 - 수치표고 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public Object insertOrtSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().insert("searchDAO.insertOrtSupplyItem", param);
	
	}
	
	/**
	 * 통합검색 - 라이다 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public Object insertLidSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().insert("searchDAO.insertLidSupplyItem", param);
	
	}
	
	/**
	 * 통합검색 - NIR 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public Object insertNirSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().insert("searchDAO.insertNirSupplyItem", param);
	
	}
	
	/**
	 * 통합검색 - 3차원 객체 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation" })
	public Object insertTdsSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().insert("searchDAO.insertTdsSupplyItem", param);
	
	}
}