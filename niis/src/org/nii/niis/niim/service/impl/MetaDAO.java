package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository(value="metaDAO")
public class MetaDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("metaDAO."+sendMap.get("queryId"), sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirLibMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("metaDAO."+sendMap.get("queryId"), sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDemMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("metaDAO."+sendMap.get("queryId"), sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrtMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("metaDAO."+sendMap.get("queryId"), sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLidMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("metaDAO."+sendMap.get("queryId"), sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getNirMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("metaDAO."+sendMap.get("queryId"), sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTdsMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("metaDAO."+sendMap.get("queryId"), sendMap);
	}
}
