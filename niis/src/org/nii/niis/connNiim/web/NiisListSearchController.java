package org.nii.niis.connNiim.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.connNiim.service.NiisSearchService;
import org.nii.niis.niim.service.ManageAirService;
import org.nii.niis.niim.service.ManagementService;
import org.nii.niis.niim.util.PagingUtil;
import org.nii.niis.niim.util.PolicyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/niim")
public class NiisListSearchController {
	
	@Resource(name="manageAirService")
	private ManageAirService ManageAirService;
	
	@Resource(name="niisSearchService")
	private NiisSearchService niisSearchService;
	
	@Resource(name="managementService")
	private ManagementService managementService;
	
	@Resource(name="policyUtil")
	private PolicyUtil policyUtil;
	
	
	@RequestMapping(value="/airListSearch/getAirProductList.do")
	public ModelAndView getAirProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("phCourse"		, param.get("phCourse"));
			sendMap.put("phNum"			, param.get("phNum"));
			sendMap.put("sYear"			, param.get("sYear"));
			sendMap.put("eYear"			, param.get("eYear"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("cameraCde"		, param.get("cameraCde"));
			sendMap.put("resolution"	, param.get("resolution"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(param, sendMap, modelAndView, niisSearchService.getAirImgDataListCnt(sendMap));
			
			List<Map<String, Object>> list = niisSearchService.getAirImgDataList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/airListSearch/getAirProductSubList.do")
	public ModelAndView getAirProductSubList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		sendMap.put("zoneCode", param.get("zoneCode"));
		sendMap.put("phCourse", param.get("phCourse"));
		sendMap.put("phNum", param.get("phNum"));
	
		try{
			modelAndView.addObject("product", ManageAirService.getSubProductAirList(sendMap).get(0));
			modelAndView.addObject("eo", ManageAirService.getSubProductEOAirList(sendMap).get(0));
			modelAndView.addObject("meta", ManageAirService.getSubMetaAirList(sendMap).get(0));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/airLibListSearch/getAirLibProductList.do")
	public ModelAndView getAirLibProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("phCourse"		, param.get("phCourse"));
			sendMap.put("phNum"			, param.get("phNum"));
			sendMap.put("sYear"			, param.get("sYear"));
			sendMap.put("eYear"			, param.get("eYear"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("cameraCde"		, param.get("cameraCde"));
			sendMap.put("resolution"	, param.get("resolution"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(param, sendMap, modelAndView, niisSearchService.getAirLibImgDataListCnt(sendMap));
			
			List<Map<String, Object>> list = niisSearchService.getAirLibImgDataList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/airLibListSearch/getAirLibProductSubList.do")
	public ModelAndView getAirLibProductSubList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		sendMap.put("zoneCode", param.get("zoneCode"));
		sendMap.put("phCourse", param.get("phCourse"));
		sendMap.put("phNum", param.get("phNum"));
	
		try{
			modelAndView.addObject("product", ManageAirService.getSubProductAirList(sendMap).get(0));
			modelAndView.addObject("eo", ManageAirService.getSubProductEOAirList(sendMap).get(0));
			modelAndView.addObject("meta", ManageAirService.getSubMetaAirList(sendMap).get(0));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/demListSearch/getDemProductList.do")
	public ModelAndView getDemProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("map5000Num"	, param.get("map5000Num"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("resolutionDem"	, param.get("resolutionDem"));
//			sendMap.put("gridInt"		, param.get("gridInt"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(param, sendMap, modelAndView, niisSearchService.getDemImgDataListCnt(sendMap));
			
			List<Map<String, Object>> list = niisSearchService.getDemImgDataList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/demListSearch/getDemProductSubList.do")
	public ModelAndView getDemProductSubList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		sendMap.put("zoneCode", param.get("zoneCode"));
		sendMap.put("map5000Num", param.get("map5000Num"));
		sendMap.put("gridInt", param.get("gridInt"));
	
		try{
			modelAndView.addObject("product", managementService.getSubMap5000NumList(sendMap).get(0));
			modelAndView.addObject("meta", managementService.getSubMap5000BaseMeta(sendMap).get(0));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/ortListSearch/getOrtProductList.do")
	public ModelAndView getOrtProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("map5000Num"	, param.get("map5000Num"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("gtypDst"		, param.get("gtypDst"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(param, sendMap, modelAndView, niisSearchService.getOrtImgDataListCnt(sendMap));
			
			List<Map<String, Object>> list = niisSearchService.getOrtImgDataList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/ortListSearch/getOrtProductSubList.do")
	public ModelAndView getOrtProductSubList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		sendMap.put("zoneCode", param.get("zoneCode"));
		sendMap.put("map5000Num", param.get("map5000Num"));
		sendMap.put("gtypDst", param.get("gtypDst"));
	
		try{
			modelAndView.addObject("product", managementService.getSubMap5000NumOrtList(sendMap).get(0));
			modelAndView.addObject("meta", managementService.getSubMetaOrtList(sendMap).get(0));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/atListSearch/getAtProductList.do")
	public ModelAndView getAtProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("sYear"			, param.get("sYear"));
			sendMap.put("eYear"			, param.get("eYear"));
			sendMap.put("dtsImageCde"	, param.get("dtsImageCde"));
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(param, sendMap, modelAndView, niisSearchService.getAtImgDataListCnt(sendMap));
			
			List<Map<String, Object>> list = niisSearchService.getAtImgDataList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/atListSearch/getAtProductSubList.do")
	public ModelAndView getAtProductSubList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		sendMap.put("zoneCode", param.get("zoneCode"));
	
		try{
			// at성과는 항공사진에서 파생
			modelAndView.addObject("product", managementService.getSubProductAtList(sendMap).get(0));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/mapListSearch/getMapProductList.do")
	public ModelAndView getMapProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("mapKindCd"		, param.get("mapKindCd"));
			sendMap.put("scaleCd"		, param.get("scaleCd"));
			sendMap.put("coordDvsnCd"	, param.get("coordDvsnCd"));
			sendMap.put("openDvsnCd"	, param.get("openDvsnCd"));
			sendMap.put("searchNum"		, param.get("searchNum"));
			sendMap.put("searchWrd"		, param.get("searchWrd"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(param, sendMap, modelAndView, niisSearchService.getMapImgDataListCnt(sendMap));
			
			List<Map<String, Object>> list = niisSearchService.getMapImgDataList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/mapListSearch/getMapHisImgDataList.do")
	public ModelAndView getMapHisImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("mapSerNo"		, param.get("mapSerNo"));
			sendMap.put("mapShtNo"		, param.get("mapShtNo"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(param, sendMap, modelAndView, niisSearchService.getMapHisImgDataListCnt(sendMap));
			
			List<Map<String, Object>> list = niisSearchService.getMapHisImgDataList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/mapListSearch/getMapListSrchDetail.do")
	public ModelAndView getMapListSrchDetail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			Map<String, Object> sendMap = new HashMap<String, Object>();
			
			sendMap.put("mapSerNo", 	param.get("mapSerNo"));
			sendMap.put("mapShtNo", 	param.get("mapShtNo"));
			sendMap.put("mapHistoryNo", param.get("mapHistoryNo"));
			
			
			Map<String, Object> product = niisSearchService.getMapListSrchDetail(sendMap);
			modelAndView.addObject("product", product);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/mapListSearch/getMapHistoryListSrchDetail.do")
	public ModelAndView getMapHistoryListSrchDetail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			Map<String, Object> sendMap = new HashMap<String, Object>();
			
			sendMap.put("mapSerNo", 	param.get("mapSerNo"));
			sendMap.put("mapShtNo", 	param.get("mapShtNo"));
			sendMap.put("mapHistoryNo", param.get("mapHistoryNo"));
			
			
			Map<String, Object> product = niisSearchService.getMapHistoryListSrchDetail(sendMap);
			modelAndView.addObject("product", product);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}
}
