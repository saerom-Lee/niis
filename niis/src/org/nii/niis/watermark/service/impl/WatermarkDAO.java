package org.nii.niis.watermark.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("watermarkDAO")
public class WatermarkDAO extends EgovAbstractDAO {

	public List<?> selectApproval() throws Exception{
		return getSqlMapClientTemplate().queryForList("watermarkDAO.selectApproval");
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getAirImgDataList", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirLibImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getAirLibImgDataList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDemImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getDemImgDataList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrtImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getOrtImgDataList", sendMap);
	}
}
