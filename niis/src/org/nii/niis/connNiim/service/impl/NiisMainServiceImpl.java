package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.NiisMainService;
import org.springframework.stereotype.Service;

@Service("niisMainService")
public class NiisMainServiceImpl implements NiisMainService {

	@Resource(name="niisMainDAO")
	private NiisMainDAO niisMainDAO;
	
	
	@Override
	public Map<String, Object> getMainApply(Map<String, Object> sendMap) throws Exception {
		return niisMainDAO.getMainApply(sendMap);
	}

	@Override
	public List<Map<String, Object>> getMainBoard(Map<String, Object> sendMap) throws Exception {
		return niisMainDAO.getMainBoard(sendMap);
	}

	@Override
	public List<Map<String, Object>> getMainPopup(Map<String, Object> sendMap) throws Exception {
		return niisMainDAO.getMainPopup(sendMap);
	}
}
