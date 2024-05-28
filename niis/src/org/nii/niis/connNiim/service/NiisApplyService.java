package org.nii.niis.connNiim.service;

import java.util.List;
import java.util.Map;

public interface NiisApplyService {

	Map<String, Object> regApply(Map<String, Object> sendMap) throws Exception;
	
	Map<String, Object> regApplyAll(Map<String, Object> sendMap) throws Exception;

	int getUserApplyListCnt(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getUserApplyList(Map<String, Object> sendMap) throws Exception;

	int applyExtension(Map<String, Object> param) throws Exception;

	Map<String, Object> reRegApply(Map<String, Object> sendMap) throws Exception;

	List<Map<String, Object>> getPurposeList(String codeId) throws Exception;

}
