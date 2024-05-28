package org.nii.niis.smartMap.service;

import java.util.List;
import java.util.Map;

public interface SmartMapService {

	public String mataIns (Map<String, List<Map<String, Object>>> metaMap) throws Exception;
	public  List<String> getZoneList (Map<String, Object> sendMap) throws Exception; 
	public  List<Map<String, Object>> getStoInfo () throws Exception; 
	public String getStoDrv(String stoIdn) throws Exception;
	public String getFolderNam(String storageCde) throws Exception;
	public void insertOrtLoc(Map<String, Object> sendMap) throws Exception;
	public void insertAirLoc(Map<String, Object> sendMap) throws Exception;
}
