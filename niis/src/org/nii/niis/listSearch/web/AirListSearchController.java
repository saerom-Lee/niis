package org.nii.niis.listSearch.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
* @FileName : AirListSearchController.java 
* @Date : 2016. 12. 19.
* @description : 웜본DB 목록 항공사진 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/airListSearch/")
public class AirListSearchController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	
	/** 
	* <pre>
	* @Method : listSearch
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 페이지 진입
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="listSearch.do")
    public String listSearch(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "listSearch/airListSearch";
	}
	
	/******************************************************************** 코드조회 시작 ********************************************************************/ 
	
	/** 
	* <pre>
	* @Method : getYearList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 사업년도 조회
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
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.AIR_LISTSEARCH_CODE_YEAR;
		/************************************** queryID 필수 **************************************/
		System.out.println("queryId>>>>" + queryId);
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			System.out.println("========여기000============");
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
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 사업지구 조회
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
		String queryId = SqlidConst.AIR_LISTSEARCH_CODE_ZONECODE;
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
	* @Method : getPhCourseList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 코스번호 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getPhCourseList.do")
	public ModelAndView getPhCourseList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.AIR_LISTSEARCH_CODE_PHCOURSE;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getPhCourseList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getPhNumList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 사진번호 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getPhNumList.do")
	public ModelAndView getPhNumList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.AIR_LISTSEARCH_CODE_PHNUM;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getPhNumList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getSecurityCodeList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 지리정보등급 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getSecurityCodeList.do")
	public ModelAndView getSecurityCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.COMMON_DETAIL_CODE;
		/************************************** queryID 필수 **************************************/
		
		try{
			param.put("cdeCde", "SECURITY_CDE");
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getSecurityCodeList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getCameraCodeList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 카메라구분 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getCameraCodeList.do")
	public ModelAndView getCameraCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.COMMON_DETAIL_CODE;
		/************************************** queryID 필수 **************************************/
		
		try{
			param.put("cdeCde", "CAMERA_CDE");
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getCameraCodeList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getResolutionList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 해상도 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getResolutionList.do")
	public ModelAndView getResolutionList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.AIR_LISTSEARCH_CODE_RESOLUTION;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getResolutionList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	/******************************************************************** 코드조회 종료 ********************************************************************/
	
	/** 
	* <pre>
	* @Method : getAirProductList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getAirProductList.do")
	public ModelAndView getAirProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/airListSearch/getAirProductList.do";
			
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("imageCde", propertiesService.getString("Globals.airZoneCode"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirProductList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : airListSrchDetail
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 상세 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="airListSrchDetail.do")
	public String airListSrchDetail(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/airListSearch/getAirProductSubList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("product", jsonMap.get("product"));
			model.addAttribute("eo", jsonMap.get("eo"));
			model.addAttribute("meta", jsonMap.get("meta"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::airListSrchDetail", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "listSearch/airListSrchDetail";
	}
	
	/** 
	* <pre>
	* @Method : airListSrchPop
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 항공사진 신청 내역 확인
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="airListSrchPop.do")
	public String airListSrchPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String[] imageCde = request.getParameterValues("chkImageCde");
			String[] zoneCode = request.getParameterValues("chkZoneCode");
			String[] phCourse = request.getParameterValues("chkPhCourse");
			String[] phNum = request.getParameterValues("chkPhNum");
			String[] zoneYy = request.getParameterValues("chkZoneYy");
			String[] zoneNam = request.getParameterValues("chkZoneNam");
			String[] cameraCde = request.getParameterValues("chkCameraCde");
			String[] resolution = request.getParameterValues("chkResolution");
			
			List<Map<String, String>> chkAirList = new ArrayList<Map<String, String>>();
			for(int i=0; i<zoneCode.length; i++){
				Map<String, String> map = new HashMap<String, String>();
				map.put("imageCde", imageCde[i]);
				map.put("zoneCode", zoneCode[i]);
				map.put("phCourse", phCourse[i]);
				map.put("phNum", phNum[i]);
				map.put("zoneYy", zoneYy[i]);
				map.put("zoneNam", zoneNam[i]);
				map.put("cameraCde", cameraCde[i]);
				map.put("resolution", resolution[i]);
				
				chkAirList.add(map);
			}
			
			model.addAttribute("chkAirList", chkAirList);
		}catch(Exception e){
			log.error(this.getClass() + "::::airListSrchPop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return "listSearch/airListSrchPop";
	}
	
}
