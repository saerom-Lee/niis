package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("niisSupplyDAO")
public class NiisSupplyDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDownloadAirList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDownloadAirList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDownloadAirLibList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDownloadAirLibList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDownloadDemList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDownloadDemList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDownloadOrtList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDownloadOrtList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDownloadAtList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDownloadAtList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDownloadMapList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDownloadMapList", sendMap);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirFileList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getAirFileList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirLibFileList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getAirLibFileList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDemFileList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDemFileList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrtFileList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getOrtFileList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirMetaList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getAirMetaList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDemMetaList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getDemMetaList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrtMetaList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>) list("supplyDAO.getOrtMetaList", sendMap);
	}

	public void uptAirImgDownState(Map<String, Object> sendMap) throws Exception {
		update("supplyDAO.uptAirImgDownState", sendMap);
	}
	
	public void uptDemImgDownState(Map<String, Object> sendMap) throws Exception {
		update("supplyDAO.uptDemImgDownState", sendMap);
	}
	
	public void uptOrtImgDownState(Map<String, Object> sendMap) throws Exception {
		update("supplyDAO.uptOrtImgDownState", sendMap);
	}

	public int getDownloadAppCnt(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("supplyDAO.getDownloadAppCnt", sendMap);
	}

	public void updateApprvalCde(Map<String, Object> sendMap) throws Exception {
		update("supplyDAO.updateApprvalCde", sendMap);
	}
}
