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
@Repository("mapSearchPostgresDAO")
public class MapSearchPostgresDAO extends EgovAbstractDAO{
	
	@Override
	@Resource(name ="sqlMapClientPostgres")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient){
		super.setSuperSqlMapClient(sqlMapClient);
	}
	
	/**
	 * 지번 주소 검색
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectJibunList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("mapSearchDAO.selectJibunList", param);
		
	}
	
	/**
	 * 도로명 주소 검색
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> selectJusoList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("mapSearchDAO.selectJusoList", param);
		
	}

}
