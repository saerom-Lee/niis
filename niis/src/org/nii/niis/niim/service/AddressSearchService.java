package org.nii.niis.niim.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 행정명 검색 interface 객체 
 */
public interface AddressSearchService {
	List<AddressSearchVO> getAddressArea(String admcd) throws Exception;

	List<IndexMapVO> getIndexMapArea(String selectKey)throws Exception;
    
    /**
     * 1:5000도엽을 조회한다.
     * @exception
     */
    List<IndexMapVO> selectIndexMap5000List(String minx, String miny, String maxx, String maxy) throws Exception;
}