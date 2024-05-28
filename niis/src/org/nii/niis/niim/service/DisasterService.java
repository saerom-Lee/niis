package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

public interface DisasterService {

	int getDisasterListCnt(Map<String, Object> param) throws Exception;
	
	List<Map<String, Object>> getDisasterList(Map<String, Object> param) throws Exception;

	Map<String, Object> getDisasterDetail(Map<String, Object> param) throws Exception;

	List<Map<String, Object>> getDisasterVideoList(Map<String, Object> sendMap) throws Exception;

	List<Map<String, Object>> getDisasterCodeList(Map<String, Object> sendMap) throws Exception;

	List<Map<String, Object>> getDisasterRegion1List(Map<String, Object> sendMap) throws Exception;
	
	List<Map<String, Object>> getDisasterRegion2List(Map<String, Object> sendMap) throws Exception;
	
	String insertDisasterDownloadHist(Map<String, Object> sendMap) throws Exception;
}
