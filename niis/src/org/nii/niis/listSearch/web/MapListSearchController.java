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
* @FileName : MapListSearchController.java 
* @Date : 2023. 08. 22.
* @description : 원본DB 목록 수치지형도 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/mapListSearch/")
public class MapListSearchController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	
	/** 
	* <pre>
	* @Method : listSearch
	* @Date : 2023. 08. 22.
	* @description : 원본DB 목록 수치지형도 페이지 진입
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="listSearch.do")
    public String listSearch(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "listSearch/mapListSearch";
	}
	
	
	/** 
	* <pre>
	* @Method : getMapProductList
	* @Date : 2023. 08. 23.
	* @description : 원본DB 목록 수치지형도 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getMapProductList.do")
	public ModelAndView getMapProductList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/mapListSearch/getMapProductList.do";
			
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("imageCde", propertiesService.getString("Globals.mapZoneCode"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getMapProductList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/** 
	 * <pre>
	 * @Method : getMapHisImgDataList
	 * @Date : 2023. 08. 24.
	 * @description : 원본DB 목록 수치지형도 성과 이력 조회
	 * </pre>
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getMapHisImgDataList.do")
	public ModelAndView getMapHisImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/mapListSearch/getMapHisImgDataList.do";
			
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("imageCde", propertiesService.getString("Globals.mapZoneCode"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getMapProductList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	 * <pre>
	 * @Method : mapListSrchDetail
	 * @Date : 2023. 08. 28.
	 * @description : 원본DB 목록 수치지형도 지도성과 목록 상세 조회
	 * </pre>
	 * @param model
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="mapListSrchDetail.do")
	public String mapListSrchDetail(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/mapListSearch/getMapListSrchDetail.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("product", jsonMap.get("product"));
			model.addAttribute("eo", jsonMap.get("eo"));
			model.addAttribute("meta", jsonMap.get("meta"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::mapListSrchDetail", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "listSearch/mapListSrchDetail";
	}
	
	
	/** 
	* <pre>
	* @Method : mapListSrchDetail
	* @Date : 2023. 08. 29.
	* @description : 원본DB 목록 수치지형도 지도성과이력 목록 상세 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="mapHistoryListSrchDetail.do")
	public String mapHistoryListSrchDetail(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/mapListSearch/getMapHistoryListSrchDetail.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("product", jsonMap.get("product"));
			model.addAttribute("eo", jsonMap.get("eo"));
			model.addAttribute("meta", jsonMap.get("meta"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::mapListSrchDetail", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "listSearch/mapHistoryListSrchDetail";
	}
	
	
}
