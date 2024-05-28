package org.nii.niis.map.search.service;

import java.util.HashMap;
import java.util.List;

public interface MapSearchOracleService {
	
	/**
	 * 도로명 - 읍면 목록 조회
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> selectEmdList(HashMap<String, Object> param);
	
	
	/**
	 * 도로명 - 도로명 목록 조회
	 * @param param
	 * @return
	 */
	public List<HashMap<String, Object>> selectRnNmList(HashMap<String, Object> param);
	
	
	/**
	 * 인덱스 스케일 목록 조회
	 * @return
	 */
	public List<HashMap<String, Object>> selectIndexScale();
	
	
	/**
	 * 인덱스 초성 목록 조회
	 * @return
	 */
	public List<HashMap<String, Object>> selectIndexInitName(HashMap<String, Object> param);
	
	
	/**
	 * 인덱스 도엽명 조회
	 * @return
	 */
	public List<HashMap<String, Object>> selectIndexName(HashMap<String, Object> param);
	
	
	/**
	 * 인덱스 도엽번호 조회
	 * @return
	 */
	public List<HashMap<String, Object>> selectIndexNum(HashMap<String, Object> param);
	
	
	/**
	 * 인덱스 도엽번호 직접 조회
	 * @return
	 */
	public List<HashMap<String, Object>> selectIndexNum2(String param);
	
	
	
}
