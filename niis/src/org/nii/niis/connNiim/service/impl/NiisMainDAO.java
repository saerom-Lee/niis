package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("niisMainDAO")
public class NiisMainDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMainApply(Map<String, Object> sendMap) throws Exception {
		return (Map<String, Object>)selectByPk("mainDAO.getMainApply", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMainBoard(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("mainDAO.getMainBoard", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMainPopup(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("mainDAO.getMainPopup", sendMap);
	}
}
