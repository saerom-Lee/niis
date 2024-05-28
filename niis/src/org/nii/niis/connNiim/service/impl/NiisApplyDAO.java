package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("niisApplyDAO")
public class NiisApplyDAO extends EgovAbstractDAO {

	public Object regUserDbApp(Map<String, Object> appMap) {
		Object obj = insert("applyDAO.regUserDbApp", appMap);
		insert("applyDAO.regUserDbAppHist", appMap);
		return obj;
	}

	public Object regUserDbAppAir(Map<String, Object> appSupMap) {
		return insert("applyDAO.regUserDbAppAir", appSupMap);
	}

	public Object regUserDbAppDem(Map<String, Object> appSupMap) {
		return insert("applyDAO.regUserDbAppDem", appSupMap);
	}

	public Object regUserDbAppOrt(Map<String, Object> appSupMap) {
		return insert("applyDAO.regUserDbAppOrt", appSupMap);
	}
	
	public int uptUserDbAppMap(Map<String, Object> appMap) {
		return update("applyDAO.uptUserDbAppMap", appMap);
	}
	
	public int uptUserDbAppIim(Map<String, Object> appMap) {
		return update("applyDAO.uptUserDbAppIim", appMap);
	}

	public int getUserApplyListCnt(Map<String, Object> sendMap) {
		return (Integer)selectByPk("applyDAO.getUserApplyListCnt", sendMap);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getUserApplyList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("applyDAO.getUserApplyList", sendMap);
	}

	public int applyExtension(Map<String, Object> sendMap) {
		return update("applyDAO.applyExtension", sendMap);
	}

	public Object regUserDbAppAt(Map<String, Object> appSupMap) {
		return insert("applyDAO.regUserDbAppAt", appSupMap);
	}
	
	/**
	 * 신청서 작성 - 신청서 활용목적 리스트
	 * @return
	 */
	public List<Map<String, Object>> getPurposeList(String codeId) {
		return (List<Map<String, Object>>)list("applyDAO.getPurposeList", codeId);
	}

	public Object regUserDbAppMap(Map<String, Object> appSupMap) {
		return insert("applyDAO.regUserDbAppMap", appSupMap);
	}

}
