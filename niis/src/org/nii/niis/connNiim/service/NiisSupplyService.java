package org.nii.niis.connNiim.service;

import java.util.List;
import java.util.Map;

public interface NiisSupplyService {

	List<Map<String, Object>> getDownloadAirList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDownloadAirLibList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDownloadDemList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDownloadOrtList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDownloadAtList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDownloadMapList(Map<String, Object> sendMap) throws Exception;
	
	
	
	List<Map<String, Object>> getAirFileList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getAirLibFileList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDemFileList(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getOrtFileList(Map<String, Object> sendMap) throws Exception;

	List<Map<String, Object>> getMetaFileList(Map<String, Object> sendMap) throws Exception;

	void downloadComplete(Map<String, Object> sendMap) throws Exception;

	void updateApprvalCde(Map<String, Object> sendMap) throws Exception;
}
