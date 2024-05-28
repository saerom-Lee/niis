package org.nii.niis.connNiim.service;

import java.util.List;
import java.util.Map;

public interface NiisCommonService {

	List<Map<String, Object>> getCommonCode(Map<String, Object> param) throws Exception;

}
