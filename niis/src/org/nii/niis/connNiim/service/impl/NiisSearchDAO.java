package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("niisSearchDAO")
public class NiisSearchDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirImgFolderList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getAirImgFolderList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getAirImgDataList", sendMap);
	}
	
	public int getAirImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("listSearchDAO.getAirImgDataListCnt", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirLibImgFolderList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getAirLibImgFolderList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAirLibImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getAirLibImgDataList", sendMap);
	}
	
	public int getAirLibImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("listSearchDAO.getAirLibImgDataListCnt", sendMap);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrtImgFolderList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getOrtImgFolderList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOrtImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getOrtImgDataList", sendMap);
	}
	
	public int getOrtImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("listSearchDAO.getOrtImgDataListCnt", sendMap);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDemImgFolderList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getDemImgFolderList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDemImgDataList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("listSearchDAO.getDemImgDataList", sendMap);
	}
	
	public int getDemImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return (Integer)selectByPk("listSearchDAO.getDemImgDataListCnt", sendMap);
	}

	public int getAtImgDataListCnt(Map<String, Object> sendMap) {
		return (Integer)selectByPk("listSearchDAO.getAtImgDataListCnt", sendMap);
	}

	public List<Map<String, Object>> getAtImgDataList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getAtImgDataList", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAtImgFolderList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getAtImgFolderList", sendMap);
	}

	@SuppressWarnings("deprecation")
	public int getMapImgDataListCnt(Map<String, Object> sendMap) {
		return (Integer)selectByPk("listSearchDAO.getMapImgDataListCnt", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapImgDataList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getMapImgDataList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSuchiFolderList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getSuchiFolderList", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSuchiDataList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getSuchiDataList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getSuchi2FolderList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getSuchi2FolderList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLandFolderList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getLandFolderList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getLandUseFolderList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getLandUseFolderList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCoastFolderList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getCoastFolderList", sendMap);
	}
	
	@SuppressWarnings("deprecation")
	public int getMapHisImgDataListCnt(Map<String, Object> sendMap) {
		return (Integer)selectByPk("listSearchDAO.getMapHisImgDataListCnt", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMapHisImgDataList(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("listSearchDAO.getMapHisImgDataList", sendMap);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMapListSrchDetail(Map<String, Object> sendMap) {
		return (Map<String, Object>) select("listSearchDAO.getMapListSrchDetail", sendMap);
	}

	public Map<String, Object> getMapHistoryListSrchDetail(Map<String, Object> sendMap) {
		return (Map<String, Object>) select("listSearchDAO.getMapHistoryListSrchDetail", sendMap);
	}

}
