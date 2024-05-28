package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import org.nii.niis.niim.service.ManagementServiceVO;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("niisSmartMapDAO")
public class NiisSmartMapDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<String> getZoneList(Map<String, Object> sendMap) throws Exception {
		return (List<String>)list("smartMapDAO.getZoneList", sendMap);
	}
	
	public int getZoneListCnt(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("smartMapDAO.getZoneListCnt", sendMap);
	}

	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회 페이징 카운트
	 * @param maParam
	 * @return int
	 * @throws Exception
	 */
	public int selectZoneListCnt(ManagementServiceVO maParam) {
		return (Integer)selectByPk("smartMapDAO.getZoneListCnt", maParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회
	 * @param maParam
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> selectZoneList(ManagementServiceVO maParam) {
		return (List<?>) getSqlMapClientTemplate().queryForList("smartMapDAO.getZoneList", maParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/사업지구 상세 목록 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<?> getsubResultZoneList(String zoneCode) {
		return (List<?>) getSqlMapClientTemplate().queryForList("smartMapDAO.getsubResultZoneList", zoneCode);
	}
	/**
	 * 메타데이터 전송 
	 * @param sendMap
	 * @return
	 * @since 2017. 9. 20.
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("smartMapDAO.get_"+sendMap.get("queryId"), sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("smartMapDAO.getAirImgDataList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirLibImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("smartMapDAO.getAirLibImgDataList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDemImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("smartMapDAO.getDemImgDataList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrtImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("smartMapDAO.getOrtImgDataList", sendMap);
	}
	
	public void insFilePath(Map<String, Object> sendMap) throws Exception {
		insert("smartMapDAO.insFilePath", sendMap);
	}
	
	public int uploading(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("smartMapDAO.uploading", sendMap);
	}
	
	public int uploadingSupIdn(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("smartMapDAO.uploadingSupIdn", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> smartMapfileList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("smartMapDAO.smartMapfileList", sendMap);
	}
	
	public void updateSmartMap(Map<String, Object> sendMap) throws Exception {
		update("smartMapDAO.updateSmartMap", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<String> getSmartZoneList() {
		return (List<String>)list("niisSmartMapDAO.getSmartZoneList", null);
	}

}
