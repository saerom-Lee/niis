package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("applyDAO")
public class ApplyDAO extends EgovAbstractDAO {

	/**
	 * 원본DB 신청서 관리 리스트카운트
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	public int getDbAppListCnt(Map<String, Object> param) {
		return (Integer)selectByPk("applyDAO.getDbAppListCnt", param);
	}
	/**
	 * 원본DB 신청서 관리 리스트
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<?> getDbAppList(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("applyDAO.getDbAppList", param);
	}

	
	/**
	 * 원본DB 신청서 관리 상세
	 * @param param
	 * @return Map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getDbAppDetail(Map<String, Object> param) {
		return (Map<String, Object>)selectByPk("applyDAO.getDbAppDetail", param);
	}
	
	
	/**
	 * 원본DB 신청서 관리 상세 발급내역
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<?> getDbAppReqList(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("applyDAO.getDbAppReqList", param);
	}
	
	
	/**
	 * 원본DB 신청서 관리 상세 결과보고서
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<?> getDbAppRstReview(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("applyDAO.getDbAppRstReview", param);
	}

	
	/**
	 * 원본DB 신청서 등록
	 * @param param
	 * @return String
	 * @throws Exception
	 */
	public String regDbApp(Map<String, Object> sendMap) {
		return (String)insert("applyDAO.regDbApp", sendMap);
	}

	
	/**
	 * 원본DB 신청서 수정
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	public int uptDbApp(Map<String, Object> sendMap) {
		return update("applyDAO.uptDbApp", sendMap);
	}

	
	/**
	 * 원본DB 신청서 삭제(삭제여부 업데이트)
	 * @param param
	 * @throws Exception
	 */
	public void delDbApp(Map<String, Object> sendMap) {
		//delete("applyDAO.delDbApp", sendMap);
		update("applyDAO.delDbApp", sendMap);
	}
	/* IMG_SUPPLY_APP_NIIM 삭제여부 컬럼추가로 사용안함
	public void delDbAppDts(Map<String, Object> sendMap) {
		delete("applyDAO.delDbAppDts", sendMap);
	}
	public void delDbAppAirSupply(Map<String, Object> sendMap) {
		delete("applyDAO.delDbAppAirSupply", sendMap);
	}
	public void delDbAppDemSupply(Map<String, Object> sendMap) {
		delete("applyDAO.delDbAppDemSupply", sendMap);
	}
	public void delDbAppOrtSupply(Map<String, Object> sendMap) {
		delete("applyDAO.delDbAppOrtSupply", sendMap);
	}
	*/
	
	@SuppressWarnings("deprecation")
	public List<?> getDbAppAuthList(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("applyDAO.getDbAppAuthList", param);
	}
	
	public void uptDbAppAuth(Map<String, Object> param) {
		update("applyDAO.uptDbAppAuth", param);
		try{
			insert("applyDAO.regUserDbAppHist", param);
		}catch(DuplicateKeyException e){
			//e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Map<String, Object>> getUsrAirSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("applyDAO.getUsrAirSupplyItem", param);
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Map<String, Object>> getUsrDemSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("applyDAO.getUsrDemSupplyItem", param);
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Map<String, Object>> getUsrOrtSupplyItem(Map<String, Object> param) {
		return getSqlMapClientTemplate().queryForList("applyDAO.getUsrOrtSupplyItem", param);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Map<String, Object>> getSupIdnList() throws Exception {
		return getSqlMapClientTemplate().queryForList("applyDAO.getSupIdnList");
	}
	
	
	public void uptDbApproval(Map<String,Object> map) throws Exception{
		update("applyDAO.uptDbApproval",map);
		
		try{
			insert("applyDAO.regUserDbAppHist", map);
		}catch(DuplicateKeyException e){
			e.printStackTrace();
		}
	}
	
	public void UptExpiredDt(Map<String, Object> param) {
		update("applyDAO.updateExpiredDt", param);
	}
	
	public String metaCheck(Map<String,Object> param){
		return (String) selectByPk("applyDAO.selectMetaCheck",param);
	}
	
	/**
	 * 신청정보 상세 list
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getApplyDetailList(Map<String, Object> param) {
		
		String queryId = null;
		
		if(Integer.parseInt(param.get("atCnt").toString()) > 0) { 		 // at성과
			queryId = "applyDAO.getAtDetailList";
		}else if(Integer.parseInt(param.get("airCnt").toString()) > 0) { // air
			queryId = "applyDAO.getAirDetailList";
		}else if(Integer.parseInt(param.get("demCnt").toString()) > 0) { // dem
			queryId = "applyDAO.getDemDetailList";
		}else if(Integer.parseInt(param.get("ortCnt").toString()) > 0) { // ort
			queryId = "applyDAO.getOrtDetailList";
		}else if(Integer.parseInt(param.get("mapCnt").toString()) > 0) { // 수치지형도
			queryId = "applyDAO.getMapDetailList";
		}
		
		// nii_apply_oracle.xml
		return (List<Map<String, Object>>)list(queryId, param);
	}
	
}
