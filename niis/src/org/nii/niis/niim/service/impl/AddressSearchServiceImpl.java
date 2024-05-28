package org.nii.niis.niim.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.nii.niis.niim.service.AddressSearchService;
import org.nii.niis.niim.service.AddressSearchVO;
import org.nii.niis.niim.service.IndexMapVO;
import org.springframework.stereotype.Service;

/**
 * 행정명 검색 implements 객체 
 */
@Service("addressSearchService")
public class AddressSearchServiceImpl implements AddressSearchService {

	/** AddressSearchDAO */
    @Resource(name="addressSearchDAO")
    private AddressSearchDAO addressSearchDAO;
	
	public List<AddressSearchVO> getAddressArea(String admcd) throws Exception {
		return addressSearchDAO.getAddressArea(admcd);
	}

	public List<IndexMapVO> getIndexMapArea(String selectKey) throws Exception {
		return addressSearchDAO.getIndexMapArea(selectKey);
	}
	

    /**
     * 1:5000도엽을 조회한다.
     * @exception
     */
	@Override
	public List<IndexMapVO> selectIndexMap5000List(String minx, String miny, String maxx, String maxy) throws Exception {
		// TODO Auto-generated method stub
		return addressSearchDAO.selectIndexMap5000List(minx, miny, maxx, maxy);
	}
}