package org.nii.niis.search.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.common.util.BlackList;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.search.web
* @FileName : SpaceSearchController.java 
* @Date : 2016. 12. 19.
* @description : 원본DB 검색 행정동 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/search/")
public class SpaceSearchController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="connService")
	private ConnService connService;
    
    /** 
    * <pre>
    * @Method : selectSidoList
    * @Date : 2016. 12. 19.
    * @description : 원본DB 검색 시도 코드 조회
    * </pre>
    * @param request
    * @param response
    * @return
    * @throws Exception
    */
    @RequestMapping(value="selectSidoList.do")
    public ModelAndView selectSidoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> jsonMap = connService.connNiim("/search/selectSidoList.do", "", request, response);
			
			modelAndView.addObject("list", jsonMap.get("sidolist"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::selectSidoList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
    }

    /** 
    * <pre>
    * @Method : selectSigunguList
    * @Date : 2016. 12. 19.
    * @description : 원본DB 검색 시군구 코드 조회
    * </pre>
    * @param request
    * @param response
    * @param srch
    * @return
    * @throws Exception
    */
    @RequestMapping(value="selectSigunguList.do")
    public ModelAndView selectSigunguList(HttpServletRequest request, HttpServletResponse response, @Param("srch") String srch) throws Exception {
		
    	ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String sendParam = "srch=" + BlackList.getStrCnvrXss(srch);
			
			Map<String, Object> jsonMap = connService.connNiim("/search/selectSigunguList.do", sendParam, request, response);
			
			modelAndView.addObject("list", jsonMap.get("sigungulist"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::selectSigunguList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
    }
    
    /** 
    * <pre>
    * @Method : selectDongList
    * @Date : 2016. 12. 19.
    * @description : 원본DB 검색 동 코드 조회
    * </pre>
    * @param request
    * @param response
    * @param srch
    * @return
    * @throws Exception
    */
    @RequestMapping(value="selectDongList.do")
    public ModelAndView selectDongList(HttpServletRequest request, HttpServletResponse response, @Param("srch") String srch) throws Exception {
		
    	ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String sendParam = "srch=" + BlackList.getStrCnvrXss(srch);
			
			Map<String, Object> jsonMap = connService.connNiim("/search/selectDongList.do", sendParam, request, response);
			
			modelAndView.addObject("list", jsonMap.get("donglist"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::selectDongList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
    }
    
    
    /** 
     * <pre>
     * @Method : selectLiList
     * @Date : 2023. 08. 21.
     * @description : 원본DB 검색 리 코드 조회
     * </pre>
     * @param request
     * @param response
     * @param srch
     * @return
     * @throws Exception
     */
     @RequestMapping(value="selectLiList.do")
     public ModelAndView selectLiList(HttpServletRequest request, HttpServletResponse response, @Param("sido") String sido, @Param("sig") String sig, @Param("emd") String emd) throws Exception {
 		
     	ModelAndView modelAndView = new ModelAndView("jsonView");
 		
 		try{
 			String sendParam = "sido=" + BlackList.getStrCnvrXss(sido) + "&sig=" + BlackList.getStrCnvrXss(sig) +"&emd=" + BlackList.getStrCnvrXss(emd);
 			
 			Map<String, Object> jsonMap = connService.connNiim("/search/selectLiList.do", sendParam, request, response);
 			
 			modelAndView.addObject("list", jsonMap.get("liList"));
 			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
 		}catch(Exception e){
 			log.error(this.getClass() + "::::selectLiList", e);
 			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 		}
 		
 		return modelAndView;
     }
}
