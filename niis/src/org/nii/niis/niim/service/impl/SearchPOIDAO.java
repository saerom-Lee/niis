package org.nii.niis.niim.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nii.niis.niim.service.SearchAreaVO;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("SearchPOIDAO")
public class SearchPOIDAO extends EgovAbstractDAO {
	
	/**
	 * 북한 행정경계 반환
	 * @param areaCode
	 * @return
	 * @throws Exception
	 */
	public SearchAreaVO selectAreaGeometry(String areaCode) throws Exception {
		return (SearchAreaVO)getSqlMapClientTemplate().queryForObject("searchPOIDAO.selectAreaGeometry", areaCode);
	}
	
	public List<SearchAreaVO> selectAreaList() throws Exception {
		return (List<SearchAreaVO>) list("searchPOIDAO.selectAreaList", "");
	}
	public List<SearchAreaVO> selectAreaList(float x, float y) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("x", x);
		param.put("y", y);
		return (List<SearchAreaVO>) list("searchPOIDAO.selectAreaOfPointList", param);
	}
}
