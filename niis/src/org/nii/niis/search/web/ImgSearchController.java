package org.nii.niis.search.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.search.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.search.web
* @FileName : ImgSearchController.java 
* @Date : 2016. 12. 19.
* @description : 웜본DB 검색 영상 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/search/")
public class ImgSearchController {
	
	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="connService")
	private ConnService connService;
	
	@Resource(name="searchService")
	private SearchService searchService;
	
	
	/** 
	* <pre>
	* @Method : getAirImgFolderList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 항공사진 폴더 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="airImgFolderList.do")
	public ModelAndView getAirImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/airImgFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirImgFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getAirImgDataList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 항공사진 영상 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="airImgDataList.do")
	public ModelAndView getAirImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/airImgDataList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirImgDataList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	/**
	 * @Method : getAirLibImgFolderList
	 * @description : 원본DB 검색 항공사진(해방전후) 폴더 검색
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 23.
	 */
	@RequestMapping(value="airLibImgFolderList.do")
	public ModelAndView getAirLibImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/airLibImgFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirLibImgFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * @Method : getAirLibImgDataList
	 * @description : 원본DB 검색 항공사진 영상 검색
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 23.
	 */
	@RequestMapping(value="airLibImgDataList.do")
	public ModelAndView getAirLibImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/airLibImgDataList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirLibImgDataList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getOrtImgFolderList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 정사영상 폴더 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="ortImgFolderList.do")
	public ModelAndView getOrtImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/ortImgFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getOrtImgFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getOrtImgDataList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 정사영상 영상 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="ortImgDataList.do")
	public ModelAndView getOrtImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/ortImgDataList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getOrtImgDataList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/** 
	* <pre>
	* @Method : getDemImgFolderList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 수치표고 폴더 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="demImgFolderList.do")
	public ModelAndView getDemImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/demImgFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDemImgFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getDemImgDataList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 수치표고 영상 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="demImgDataList.do")
	public ModelAndView getDemImgDataList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/demImgDataList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDemImgDataList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getImgMarkerList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 마커 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getImgMarkerList.do")
	public ModelAndView getImgMarkerList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/getImgMarkerList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getImgMarkerList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getAirMetaInfo
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 항공사진 메타정보 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getAirMetaInfo.do")
	public String getAirMetaInfo(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/search/getAirMetaInfo.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("map", ((List<Map<String, Object>>)jsonMap.get("list")).get(0));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirMetaInfo", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "/search/airMetaDetail";
	}
	
	/** 
	* <pre>
	* @Method : getDemMetaInfo
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 수치표고 메타정보 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getDemMetaInfo.do")
	public String getDemMetaInfo(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/search/getDemMetaInfo.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("map", ((List<Map<String, Object>>)jsonMap.get("list")).get(0));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDemMetaInfo", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "/search/demMetaDetail";
	}
	
	/** 
	* <pre>
	* @Method : getOrtMetaInfo
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 정사영상 메타정보 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getOrtMetaInfo.do")
	public String getOrtMetaInfo(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/search/getOrtMetaInfo.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("map", ((List<Map<String, Object>>)jsonMap.get("list")).get(0));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getOrtMetaInfo", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "/search/ortMetaDetail";
	}
	
	/** 
	* <pre>
	* @Method : getAtDataList
	* @Date : 2023. 08. 08.
	* @description : 원본DB 검색 AT성과 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="atImgFolderList.do")
	public ModelAndView getAtImgFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/atImgFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAtImgFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getAtMetaInfo
	* @Date : 2023. 08. 08
	* @description : 원본DB 검색 AT성과 메타정보 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getAtMetaInfo.do")
	public String getAtMetaInfo(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/search/getAtMetaInfo.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("map", ((List<Map<String, Object>>)jsonMap.get("list")).get(0));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirMetaInfo", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "/search/atMetaDetail";
	}
	
	/** 
	* <pre>
	* @Method : getSuchiFolderList
	* @Date : 2023. 09. 05.
	* @description : 원본DB 검색 수치지형도v1 검색
	* </pre>
	*/
	@RequestMapping(value="suchiFolderList.do")
	public ModelAndView getSuchiFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/suchiFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::suchiFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}

	/** 
	* <pre>
	* @Method : getSuchiDataList
	* @Date : 2023. 09. 13.
	* @description : 원본DB 검색 수치지형도 이력 리스트 조회
	* </pre>
	*/
	@RequestMapping(value="suchiDataList.do")
	public ModelAndView getSuchiDataList(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/suchiDataList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("result", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getSuchiDataList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}

	/** 
	 * <pre>
	 * @Method : getSuchi2FolderList
	 * @Date : 2023. 09. 18.
	 * @description : 원본DB 검색 수치지형도v2 검색
	 * </pre>
	 */
	@RequestMapping(value="suchi2FolderList.do")
	public ModelAndView getSuchi2FolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/suchi2FolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getSuchi2FolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	 * <pre>
	 * @Method : getLandFolderList
	 * @Date : 2023. 09. 21.
	 * @description : 원본DB 검색 토지특성도 검색
	 * </pre>
	 */
	@RequestMapping(value="landFolderList.do")
	public ModelAndView getLandFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/landFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getLandFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	 * <pre>
	 * @Method : getLandUseFolderList
	 * @Date : 2023. 09. 18.
	 * @description : 원본DB 검색 토지이용현황도 검색
	 * </pre>
	 */
	@RequestMapping(value="landUseFolderList.do")
	public ModelAndView getLandUseFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/landUseFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getLandUseFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	 * <pre>
	 * @Method : getCoastFolderList
	 * @Date : 2023. 09. 18.
	 * @description : 원본DB 검색 연안해역도 검색
	 * </pre>
	 */
	@RequestMapping(value="coastFolderList.do")
	public ModelAndView getCoastFolderList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/coastFolderList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 3*60*1000, 3*60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getCoastFolderList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
}
