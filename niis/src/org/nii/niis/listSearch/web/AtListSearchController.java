package org.nii.niis.listSearch.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.SqlidConst;
import org.nii.niis.common.service.ConnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.listSearch.web
* @FileName : AtListSearchController.java 
* @Date : 2023. 07. 27.
* @description : 원본DB 목록 AT성과 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/atListSearch/")
public class AtListSearchController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	
	/** 
	* <pre>
	* @Method : listSearch
	* @Date : 2023. 07. 27.
	* @description : 원본DB 목록 AT성과 페이지 진입
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="listSearch.do")
    public String listSearch(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "listSearch/atListSearch";
	}
	
	/******************************************************************** 코드조회 시작 ********************************************************************/ 
	
	/** 
	* <pre>
	* @Method : getYearList
	* @Date : 2023. 07. 27.
	* @description : 원본DB 목록 AT성과 사업년도 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getYearList.do")
	public ModelAndView getYearList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		// 조회항목 코드 검색시 사용
		// SqlidConst.java
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.AT_LISTSEARCH_CODE_YEAR;
		/************************************** queryID 필수 **************************************/
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getYearList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getZoneCodeList
	* @Date : 2023. 07. 27.
	* @description : 원본DB 목록 AT성과 사업지구 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getZoneCodeList.do")
	public ModelAndView getZoneCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.AT_LISTSEARCH_CODE_ZONECODE;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getZoneCodeList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getDtsImageNamList
	* @Date : 2023. 08. 01.
	* @description : 원본DB 목록 AT성과 성과종류 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getDtsImageNamList.do")
	public ModelAndView getDtsImageNamList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.AT_LISTSEARCH_CODE_IMAGECODE;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDtsImageNamList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/******************************************************************** 코드조회 종료 ********************************************************************/
	
	/** 
	* <pre>
	* @Method : getAtProductList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 목록 AT성과 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getAtProductList.do")
	public ModelAndView getAtProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/atListSearch/getAtProductList.do";	// NiisListSearchController.java
			
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAtProductList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : atListSrchDetail
	* @Date : 2023. 08. 02.
	* @description : 원본DB 목록 AT성과 상세 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="atListSrchDetail.do")
	public String atListSrchDetail(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/atListSearch/getAtProductSubList.do";		// NiisListSearchController.java
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("product", jsonMap.get("product"));
			model.addAttribute("eo", jsonMap.get("eo"));
			model.addAttribute("meta", jsonMap.get("meta"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::atListSrchDetail", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "listSearch/atListSrchDetail";
	}
	
	
}
