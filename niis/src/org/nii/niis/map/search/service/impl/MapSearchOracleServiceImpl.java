package org.nii.niis.map.search.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.nii.niis.map.search.service.MapSearchOracleService;
import org.springframework.stereotype.Service;

@Service("mapSearchOracleService")
public class MapSearchOracleServiceImpl implements MapSearchOracleService {

	@Resource(name="mapSearchOracleDAO")
    private MapSearchOracleDAO mapSearchOracleDAO;
	
	@Override
	public List<HashMap<String, Object>> selectEmdList(HashMap<String, Object> param) {
		return mapSearchOracleDAO.selectEmdList(param);
	}

	@Override
	public List<HashMap<String, Object>> selectRnNmList(HashMap<String, Object> param) {
		return mapSearchOracleDAO.selectRnNmList(param);
	}

	@Override
	public List<HashMap<String, Object>> selectIndexScale() {
		return mapSearchOracleDAO.selectIndexScale();
	}

	@Override
	public List<HashMap<String, Object>> selectIndexInitName(HashMap<String, Object> param) {
		return mapSearchOracleDAO.selectIndexInitName(param);
	}

	@Override
	public List<HashMap<String, Object>> selectIndexName(HashMap<String, Object> param) {
		return mapSearchOracleDAO.selectIndexName(param);
	}

	@Override
	public List<HashMap<String, Object>> selectIndexNum(HashMap<String, Object> param) {
		return mapSearchOracleDAO.selectIndexNum(param);
	}

	@Override
	public List<HashMap<String, Object>> selectIndexNum2(String param) {
		return mapSearchOracleDAO.selectIndexNum2(param);
	}
	
	
	
}
