package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

public interface MetaService {
	
	Map<String, List<Map<String, Object>>> getAirMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	Map<String, List<Map<String, Object>>> getAirLibMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	Map<String, List<Map<String, Object>>> getDemMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	Map<String, List<Map<String, Object>>> getOrtMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	Map<String, List<Map<String, Object>>> getLidMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	Map<String, List<Map<String, Object>>> getNirMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	Map<String, List<Map<String, Object>>> getTdsMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	
	String[] getAirMetaTable();
	
	String[] getDemMetaTable();
	
	String[] getOrtMetaTable();
	
	String[] getLidMetaTable();
	
	String[] getNirMetaTable();
	
	String[] getTdsMetaTable();
}
