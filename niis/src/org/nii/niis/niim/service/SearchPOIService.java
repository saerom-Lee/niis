package org.nii.niis.niim.service;

import java.util.List;

public interface SearchPOIService {
	SearchAreaVO selectAreaGeometry(String areaCode) throws Exception;
	List<SearchAreaVO> selectAreaList() throws Exception;
	List<SearchAreaVO> selectAreaList(float x, float y) throws Exception;
}
