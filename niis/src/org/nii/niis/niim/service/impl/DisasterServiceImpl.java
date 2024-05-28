package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.DisasterService;
import org.springframework.stereotype.Service;

@Service("disasterService")
public class DisasterServiceImpl implements DisasterService {

	@Resource(name="disasterDAO")
	private DisasterDAO disasterDAO;
	
	@Resource(name="disasterOracleDAO")
	private DisasterOracleDAO disasterOracleDAO;
	
	@Override
	public int getDisasterListCnt(Map<String, Object> param) throws Exception {
		return disasterDAO.getDisasterListCnt(param);
	}
	@Override
	public List<Map<String, Object>> getDisasterList(Map<String, Object> param) throws Exception {
		return disasterDAO.getDisasterList(param);
	}

	@Override
	public List<Map<String, Object>> getDisasterVideoList(Map<String, Object> sendMap) throws Exception {
		return disasterDAO.getDisasterVideoList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getDisasterCodeList(Map<String, Object> sendMap) throws Exception {
		return disasterOracleDAO.getDisasterCodeList(sendMap);

	}
	
	@Override
	public Map<String, Object> getDisasterDetail(Map<String, Object> sendMap) throws Exception {
		return disasterDAO.getDisasterDetail(sendMap);
	}

	@Override
	public List<Map<String, Object>> getDisasterRegion1List(Map<String, Object> sendMap) throws Exception {
		return disasterOracleDAO.getDisasterRegion1List(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDisasterRegion2List(Map<String, Object> sendMap) throws Exception {
		return disasterOracleDAO.getDisasterRegion2List(sendMap);
	}
	
	@Override
	public String insertDisasterDownloadHist(Map<String, Object> sendMap) throws Exception {
		return disasterOracleDAO.insertDisasterDownloadHist(sendMap);
	}
}
