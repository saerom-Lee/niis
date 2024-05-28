package org.nii.niis.connNiim.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.nii.niis.niim.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/niim")
public class NiisSearchController {

	@Resource(name="niimSearchService")
    private SearchService searchService;
	
	
	/* SearchController */
	@RequestMapping(value="/search/yearList.do")
	public String getYearList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/yearList.do";
	}
	
	@RequestMapping(value="/search/zoneCodeList.do")
	public String getZoneList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/zoneCodeList.do";
	}
	
	
	@RequestMapping(value="/search/searchPOI.do")
	public String searchPOI(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/searchPOI.do";
	}
	
	@RequestMapping(value="/search/nowestAirZoneAreaGeometry.do")
	public String nowestAirZoneAreaGeometry(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/nowestAirZoneAreaGeometry.do";
	}
	
	@RequestMapping(value="/search/nowestAirZone.do")
	public String nowestAirZone(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/nowestAirZone.do";
	}
	
	@RequestMapping(value="/search/getZipCodeList.do")
	public String getZipCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/getZipCodeList.do";
	}
	
	@RequestMapping(value="/search/getJusoList.do")
	public String getJusoList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/getJusoList.do";
	}
	
	@RequestMapping(value="/search/getBuldNoList.do")
	public String getBuldNoList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/getBuldNoList.do";
	}
	
	@RequestMapping(value="/search/selectIndexMapName.do")
	public String selectIndexMapName(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/selectIndexMapName.do";
	}
	
	
	/* SpaceSearchController */
	@RequestMapping(value="/search/selectSidoList.do")
    public String selectSidoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/selectSidoList.do";
    }

    @RequestMapping(value="/search/selectSigunguList.do")
    public String selectSigunguList(HttpServletRequest request, HttpServletResponse response, @Param("srch") String srch) throws Exception {
    	return "forward:/niimFor/search/selectSigunguList.do";
    }
    
    @RequestMapping(value="/search/selectDongList.do")
    public String selectDongList(HttpServletRequest request, HttpServletResponse response, @Param("srch") String srch) throws Exception {
    	return "forward:/niimFor/search/selectDongList.do";
    }
    
    @RequestMapping(value="/search/selectLiList.do")
    public String selectLiList(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> param) throws Exception {
    	return "forward:/niimFor/search/selectLiList.do";
    }
    
    
	/* AddressSearchController */
    @RequestMapping(value="/search/searchAddressArea.do")
	public String getAddressArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	return "forward:/niimFor/search/searchAddressArea.do";
	}
    
    @RequestMapping(value="/search/searchIndexMapArea.do")
	public String getIndexMapArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/search/searchIndexMapArea.do";
	}
}
