package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.NiisCommonService;
import org.springframework.stereotype.Service;

@Service("niisCommonService")
public class NiisCommonServiceImpl implements NiisCommonService {

	@Resource(name="niisCommonDAO")
	private NiisCommonDAO niisCommonDAO;
	
	
	@Override
	public List<Map<String, Object>> getCommonCode(Map<String, Object> param) throws Exception {
		return niisCommonDAO.getCommonCode(param);
	}

}
