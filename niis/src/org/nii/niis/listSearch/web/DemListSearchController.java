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
* @FileName : DemListSearchController.java 
* @Date : 2016. 12. 19.
* @description : 웜본DB 목록 수치표고 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/demListSearch/")
public class DemListSearchController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	
	/** 
	* <pre>
	* @Method : listSearch
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 페이지 진입
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="listSearch.do")
    public String listSearch(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		return "listSearch/demListSearch";
	}
	
/******************************************************************** 코드조회 시작 ********************************************************************/ 
	
	/** 
	* <pre>
	* @Method : getYearList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 사업년도 조회
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
		String queryId = SqlidConst.DEM_LISTSEARCH_CODE_YEAR;
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
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 사업지구 조회
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
		String queryId = SqlidConst.DEM_LISTSEARCH_CODE_ZONECODE;
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
	* @Method : getMap5000NumList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 도엽번호 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getMap5000NumList.do")
	public ModelAndView getMap5000NumList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.DEM_LISTSEARCH_CODE_MAP5000NUM;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getMap5000NumList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getGridIntList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 해상도 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getGridIntList.do")
	public ModelAndView getGridIntList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.DEM_LISTSEARCH_CODE_GRIDINT;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getGridIntList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	/******************************************************************** 코드조회 종료 ********************************************************************/
	
	/** 
	* <pre>
	* @Method : getDemProductList
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getDemProductList.do")
	public ModelAndView getDemProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/demListSearch/getDemProductList.do";
			
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("imageCde", propertiesService.getString("Globals.demZoneCode"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDemProductList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : demListSrchDetail
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 상세 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="demListSrchDetail.do")
	public String demListSrchDetail(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/demListSearch/getDemProductSubList.do";
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("product", jsonMap.get("product"));
			model.addAttribute("meta", jsonMap.get("meta"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::demListSrchDetail", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "listSearch/demListSrchDetail";
	}
	
	/** 
	* <pre>
	* @Method : demListSrchPop
	* @Date : 2016. 12. 19.
	* @description : 웜본DB 목록 수치표고 신청 내역 확인
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="demListSrchPop.do")
	public String demListSrchPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String[] imageCde = request.getParameterValues("chkImageCde");
			String[] zoneCode = request.getParameterValues("chkZoneCode");
			String[] map5000Num = request.getParameterValues("chkMap5000Num");
			String[] zoneYy = request.getParameterValues("chkZoneYy");
			String[] zoneNam = request.getParameterValues("chkZoneNam");
			String[] map5000Nam = request.getParameterValues("chkMap5000Nam");
			String[] gridInt = request.getParameterValues("chkGridInt");
			
			List<Map<String, String>> chkDemList = new ArrayList<Map<String, String>>();
			for(int i=0; i<zoneCode.length; i++){
				Map<String, String> map = new HashMap<String, String>();
				map.put("imageCde", imageCde[i]);
				map.put("zoneCode", zoneCode[i]);
				map.put("map5000Num", map5000Num[i]);
				map.put("zoneYy", zoneYy[i]);
				map.put("zoneNam", zoneNam[i]);
				map.put("map5000Nam", map5000Nam[i]);
				map.put("gridInt", gridInt[i]);
				
				chkDemList.add(map);
			}
			
			model.addAttribute("chkDemList", chkDemList);
		}catch(Exception e){
			log.error(this.getClass() + "::::demListSrchPop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "listSearch/demListSrchPop";
	}
}