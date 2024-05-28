package org.nii.niis.watermark.service;

import java.util.List;
import java.util.Map;


public interface WatermarkService {

	public List<?> selectApproval() throws Exception;
	
	public Map<String,Object> itemList(Map<String,Object> map) throws Exception;
	
}
