package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

/**
 * 메인 interface 객체 
 */
public interface MainService {
	
	List<?> getCommonCode(Map<String, Object> param) throws Exception;

	Map<String, Object> getMainReqCnt(Map<String, Object> param) throws Exception;
	
	boolean isThreadRunning(String threadName) throws Exception;
}