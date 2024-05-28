package org.nii.niis.connNiim.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nii.niis.connNiim.service.NiisSearchService;
import org.nii.niis.niim.service.SearchService;
import org.nii.niis.niim.util.PolicyUtil;
import org.nii.niis.niim.util.fancytree.FancyNodeData;
import org.nii.niis.niim.util.fancytree.FancyTreeAirConverter;
import org.nii.niis.niim.util.fancytree.FancyTreeAirLibConverter;
import org.nii.niis.niim.util.fancytree.FancyTreeAtConverter;
import org.nii.niis.niim.util.fancytree.FancyTreeDemConverter;
import org.nii.niis.niim.util.fancytree.FancyTreeOrtConverter;
import org.nii.niis.niim.util.fancytree.FancyTreeSuchiConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/niim")
public class NiisImgSearchController {

	@Resource(name="niimSearchService")
    private SearchService searchService;
	
	@Resource(name="niisSearchService")
    private NiisSearchService niisSearchService;
	
	@Resource(name="policyUtil")
	private PolicyUtil policyUtil;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/airImgFolderList.do")
	public ModelAndView getAirImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getAirImgFolderList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeAirConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/airImgDataList.do")
	public ModelAndView getAirImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getAirImgDataList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeAirConverter().getFancyDataNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);	
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/airLibImgFolderList.do")
	public ModelAndView getAirLibImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getAirLibImgFolderList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeAirLibConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/airLibImgDataList.do")
	public ModelAndView getAirLibImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getAirLibImgDataList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeAirLibConverter().getFancyDataNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);	
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/ortImgFolderList.do")
	public ModelAndView getOrtImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getOrtImgFolderList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeOrtConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/ortImgDataList.do")
	public ModelAndView getOrtImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getOrtImgDataList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeOrtConverter().getFancyDataNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);	
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/demImgFolderList.do")
	public ModelAndView getDemImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getDemImgFolderList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeDemConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/demImgDataList.do")
	public ModelAndView getDemImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String radius = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getDemImgDataList(sendMap);
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			List<FancyNodeData> treeList = new FancyTreeDemConverter().getFancyDataNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);	
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/search/getImgMarkerList.do")
	public ModelAndView getImgMarkerList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String radius = "";
		String state = "";
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		
		//xacml 파라미터 추가
		//policyUtil.addPolicy(request, sendMap);
		
//		if (param.containsKey("radius")){
//			radius = (String)param.get("radius");
//		}
		if (param.containsKey("imgType")){
			state = (String)param.get("imgType");
		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			List<?> list = null;
			if("0".equals(state)){
				list = niisSearchService.getAirImgDataList(sendMap);
			}else if("1".equals(state)){
				list = niisSearchService.getOrtImgDataList(sendMap);
			}else if("2".equals(state)){
				list = niisSearchService.getDemImgDataList(sendMap);
			}
			
			if (!radius.equals("")){
				if (list != null){
					double xmin = Double.parseDouble(String.valueOf(sendMap.get("xmin")));
					double ymin = Double.parseDouble(String.valueOf(sendMap.get("ymin")));
					double xmax = Double.parseDouble(String.valueOf(sendMap.get("xmax")));
					double ymax = Double.parseDouble(String.valueOf(sendMap.get("ymax")));
					
					list = searchService.getContainsList(xmin, ymin, xmax, ymax, list, radius);
				}
			}
			
			modelAndView.addObject("result", list);	
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
		return modelAndView;
	}
	
	@RequestMapping(value = "/search/getAirMetaInfo.do")
	public String getAirMetaInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "forward:/management/subMetaAirList.do";
	}
	
	@RequestMapping(value = "/search/getDemMetaInfo.do")
	public String getDemMetaInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "forward:/management/subMap5000BaseMeta.do";
	}
	
	@RequestMapping(value = "/search/getOrtMetaInfo.do")
	public String getOrtMetaInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "forward:/management/subMetaOrtList.do";
	}
	
	@RequestMapping(value = "/search/getAtMetaInfo.do")
	public String getAtMetaInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "forward:/management/subMetaAtList.do";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/search/atImgFolderList.do")
	public ModelAndView getAtImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<?> list = niisSearchService.getAtImgFolderList(sendMap);
			
			List<FancyNodeData> treeList = new FancyTreeAtConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/search/suchiFolderList.do")
	public ModelAndView getSuchiFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<Map<String, Object>> list = niisSearchService.getSuchiFolderList(sendMap);
			
			List<FancyNodeData> treeList = new FancyTreeSuchiConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/search/suchiDataList.do")
	public ModelAndView getSuchiDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		Map<String, Object> mapParam =  new HashMap<String, Object>();
		
		try {
			String useAt = (String)param.get("connUserAuth");
			
			mapParam.put("mapShtNo", (String)param.get("mapShtNo"));
			mapParam.put("mapSerNo", (String)param.get("mapSerNo"));
			
			List<Map<String, Object>> list = niisSearchService.getSuchiDataList(mapParam);
			
			List<FancyNodeData> treeList = new FancyTreeSuchiConverter().getFancyDataNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("list", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/search/suchi2FolderList.do")
	public ModelAndView getSuchi2FolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<Map<String, Object>> list = niisSearchService.getSuchi2FolderList(sendMap);
			
			List<FancyNodeData> treeList = new FancyTreeSuchiConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/search/landFolderList.do")
	public ModelAndView getLandFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<Map<String, Object>> list = niisSearchService.getLandFolderList(sendMap);
			
			List<FancyNodeData> treeList = new FancyTreeSuchiConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/search/landUseFolderList.do")
	public ModelAndView getLandUseFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<Map<String, Object>> list = niisSearchService.getLandUseFolderList(sendMap);
			
			List<FancyNodeData> treeList = new FancyTreeSuchiConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/search/coastFolderList.do")
	public ModelAndView getCoastFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Map<String, Object> sendMap = niisSearchService.getBeforeSearchCondtion(param);
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String useAt = (String)param.get("connUserAuth");
			List<Map<String, Object>> list = niisSearchService.getCoastFolderList(sendMap);
			
			List<FancyNodeData> treeList = new FancyTreeSuchiConverter().getFancyFolderNode((List<Map<String, Object>>)list, useAt, "/niis/images");
			modelAndView.addObject("result", treeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
}
