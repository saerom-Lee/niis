package org.nii.niis.connNiim.service;

import java.util.List;
import java.util.Map;

public interface NiisSearchService {

	List<Map<String, Object>> getAirImgFolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getAirImgDataList(Map<String, Object> sendMap) throws Exception;
	int getAirImgDataListCnt(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getAirLibImgFolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getAirLibImgDataList(Map<String, Object> sendMap) throws Exception;
	int getAirLibImgDataListCnt(Map<String, Object> sendMap) throws Exception;
	
	
	List<Map<String, Object>> getOrtImgFolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getOrtImgDataList(Map<String, Object> sendMap) throws Exception;
	int getOrtImgDataListCnt(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDemImgFolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getDemImgDataList(Map<String, Object> sendMap) throws Exception;
	int getDemImgDataListCnt(Map<String, Object> sendMap) throws Exception;
	
	Map<String, Object> getBeforeSearchCondtion(Map<String, Object> param) throws Exception;
	
	int getAtImgDataListCnt(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getAtImgDataList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getAtImgFolderList(Map<String, Object> sendMap) throws Exception;
	
	int getMapImgDataListCnt(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getMapImgDataList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getSuchiFolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getSuchiDataList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getSuchi2FolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getLandFolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getLandUseFolderList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getCoastFolderList(Map<String, Object> sendMap) throws Exception;
	
	int getMapHisImgDataListCnt(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getMapHisImgDataList(Map<String, Object> sendMap) throws Exception;
	
	Map<String, Object> getMapListSrchDetail(Map<String, Object> sendMap) throws Exception;
	
	Map<String, Object> getMapHistoryListSrchDetail(Map<String, Object> sendMap) throws Exception;
}
