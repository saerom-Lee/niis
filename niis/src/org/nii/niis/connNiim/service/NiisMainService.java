package org.nii.niis.connNiim.service;

import java.util.List;
import java.util.Map;

public interface NiisMainService {

	Map<String, Object> getMainApply(Map<String, Object> sendMap) throws Exception;

	List<Map<String, Object>> getMainBoard(Map<String, Object> sendMap) throws Exception;

	List<Map<String, Object>> getMainPopup(Map<String, Object> sendMap) throws Exception;

}
