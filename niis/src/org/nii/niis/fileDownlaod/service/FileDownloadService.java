package org.nii.niis.fileDownlaod.service;

import java.util.List;

import org.w3c.dom.Document;

import egovframework.rte.psl.dataaccess.util.EgovMap;

import java.util.HashMap;

public interface FileDownloadService {
	
	List<HashMap<String, Object>> getFileAirList(HashMap<String, Object> sendMap) throws Exception;
	
	List<HashMap<String, Object>> getFileOrtList(HashMap<String, Object> sendMap) throws Exception;
	
	List<HashMap<String, Object>> getFileDemList(HashMap<String, Object> sendMap) throws Exception;
	
	List<HashMap<String, Object>> getFileMapList(HashMap<String, Object> sendMap) throws Exception;
	
	List<HashMap<String, Object>> getFileAtList(HashMap<String, Object> sendMap) throws Exception;

	List<EgovMap> getMapMetaList(HashMap<String, Object> param) throws Exception;

	Document getXMLGeneration(HashMap<String, String> bsmPk) throws Exception;


	
}
