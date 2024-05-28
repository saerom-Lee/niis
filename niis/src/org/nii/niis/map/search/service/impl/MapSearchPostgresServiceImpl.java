package org.nii.niis.map.search.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.nii.niis.map.search.service.MapSearchPostgresService;
import org.springframework.stereotype.Service;

@Service("mapSearchPostgresService")
public class MapSearchPostgresServiceImpl implements MapSearchPostgresService {

	@Resource(name="mapSearchPostgresDAO")
    private MapSearchPostgresDAO mapSearchPostgresDAO;
	
	@Override
	public List<HashMap<String, Object>> selectJibunList(HashMap<String, Object> param) {
		return mapSearchPostgresDAO.selectJibunList(param);
	}

	@Override
	public List<HashMap<String, Object>> selectJusoList(HashMap<String, Object> param) {
		return mapSearchPostgresDAO.selectJusoList(param);
	}


}
