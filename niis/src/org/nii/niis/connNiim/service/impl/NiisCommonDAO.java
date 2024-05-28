package org.nii.niis.connNiim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("niisCommonDAO")
public class NiisCommonDAO extends EgovAbstractDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Map<String, Object>> getCommonCode(Map<String, Object> param) {
		if(param.containsKey("key")){
			return getSqlMapClientTemplate().queryForList(String.valueOf(param.get("queryID")), param.get(param.get("key")));
		}else{
			return getSqlMapClientTemplate().queryForList(String.valueOf(param.get("queryID")), param);
		}
	}
}
