package org.nii.niis.map.search.service;

import java.util.HashMap;
import java.util.List;

public interface MapSearchPostgresService {
	
	/**
	 * 지번 주소 검색
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> selectJibunList(HashMap<String, Object> param);
	
	
	/**
	 * 도로명 주소 검색
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> selectJusoList(HashMap<String, Object> param);
	

}
