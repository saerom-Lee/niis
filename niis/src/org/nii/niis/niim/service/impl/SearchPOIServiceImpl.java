package org.nii.niis.niim.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.nii.niis.niim.service.SearchAreaVO;
import org.nii.niis.niim.service.SearchPOIService;
import org.springframework.stereotype.Service;

@Service("SearchPOIService")
public class SearchPOIServiceImpl implements SearchPOIService {
	/** SearchPOIDAO */
    @Resource(name="SearchPOIDAO")
    private SearchPOIDAO searchPOIDAO;

	public SearchAreaVO selectAreaGeometry(String areaCode) throws Exception {
		return searchPOIDAO.selectAreaGeometry(areaCode);
	}
	
	public List<SearchAreaVO> selectAreaList() throws Exception {
		return searchPOIDAO.selectAreaList();
	}
	
	public List<SearchAreaVO> selectAreaList(float x, float y) throws Exception {
		return searchPOIDAO.selectAreaList(x, y);
	}
}
