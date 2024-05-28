package org.nii.niis.map.search.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * PostgreSQL (PostGIS) 정보 내
 * 도로명 주소, 지번 주소 검색 DAO
 * 
 * @author 송지혜(ESE)
 * @since 2023.08.21
 */
@Repository("mapSearchOracleDAO")
public class MapSearchOracleDAO extends EgovAbstractDAO{
	
	/**
	 * 도로명 주소 - 읍면 목록 조회
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectEmdList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("jusoSearchDAO.selectEmdList", param);
		
	}

	/**
	 * 도로명 주소 - 도로명 목록 조회
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectRnNmList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("jusoSearchDAO.selectRnNmList", param);
	}
	
	
	/**
	 * 인덱스 - 축척 조회
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectIndexScale() {
		return (List<HashMap<String, Object>>)list("indexSearchDAO.selectIndexScale");
	}
	
	
	/**
	 * 인덱스 - 도엽명 초성 조회
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectIndexInitName(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("indexSearchDAO.selectIndexInitName", param);
	}
	
	
	/**
	 * 인덱스 - 도엽명 조회
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectIndexName(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("indexSearchDAO.selectIndexName", param);
	}
	
	/**
	 * 인덱스 - 도엽번호 조회
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectIndexNum(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("indexSearchDAO.selectIndexNum", param);
	}
	
	/**
	 * 인덱스 - 도엽번호 직접조회
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectIndexNum2(String param) {
		return (List<HashMap<String, Object>>)list("indexSearchDAO.selectIndexNum2", param);
	}
	
	
}
