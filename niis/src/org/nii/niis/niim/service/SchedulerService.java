package org.nii.niis.niim.service;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface SchedulerService {

	List<Map<String, Object>> getExpiredList(Map<String, Object> sendMap) throws Exception;
	
	void expiredAppUpt(Map<String, Object> sendMap) throws Exception;

	List<Map<String, Object>> getExpiredFileList(Map<String, Object> sendMap) throws Exception;

	boolean deleteDirectory(File dir) throws Exception;
}
