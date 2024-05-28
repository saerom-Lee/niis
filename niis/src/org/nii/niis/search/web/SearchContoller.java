package org.nii.niis.search.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.common.util.BlackList;
import org.nii.niis.niim.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.search.web
* @FileName : SearchContoller.java 
* @Date : 2016. 12. 19.
* @description : 원본DB 검색 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/search/")
public class SearchContoller {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="connService")
	private ConnService connService;
	
	/**
     * 통합검색 interface 객체
     * @directed 
     */
	@Resource(name="niimSearchService")
    private SearchService searchService;
	
	
	/** 
	* <pre>
	* @Method : getYearList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 사업년도 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="yearList.do")
	public ModelAndView getYearList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> jsonMap = connService.connNiim("/search/yearList.do", "", request, response);
			
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
	* @Method : getZoneList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 사업지구 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="zoneCodeList.do")
	public ModelAndView getZoneList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/zoneCodeList.do";
			String sendParam = "";
			
			if(param != null){
				Iterator<String> iter = param.keySet().iterator();
				while(iter.hasNext()){
					String key = (String)iter.next();
					
					if (key.equals("imgType")){
						sendParam += "&imgType=" + (String)param.get("imgType");
					}
					if (key.equals("sYear")){
						sendParam += "&sYear=" + (String)param.get("sYear");
					}
					if (key.equals("eYear")){
						sendParam += "&eYear=" + (String)param.get("eYear");
					}
				}
			}
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getZoneList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : searchPOI
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 명칭검색 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="searchPOI.do")
	public ModelAndView searchPOI(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/searchPOI.do";
			String sendParam = "";
			
			if(param != null){
				Iterator<String> iter = param.keySet().iterator();
				while(iter.hasNext()){
					String key = (String)iter.next();
					
					if (key.equals("keyword")){
						sendParam += "&keyword=" + BlackList.getStrCnvrXss((String)param.get(key));
					}
				}
			}
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response, 60*1000, 60*1000);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::searchPOI", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : nowestAirZoneAreaGeometry
	* @Date : 2016. 12. 19.
	* @description : 
	* </pre>
	* @param param
	* @param request
	* @param response
	* @throws Exception
	*/
	@RequestMapping(value="nowestAirZoneAreaGeometry.do")
	public void nowestAirZoneAreaGeometry(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/search/nowestAirZoneAreaGeometry.do";
	    	
	    	connService.connNiimToXml(action, param, request, response, 60*1000, 60*1000);
	        
    	}catch(Exception e){
    		log.error(this.getClass() + "::::nowestAirZoneAreaGeometry", e);
    	}	
	}
	
	/** 
	* <pre>
	* @Method : nowestAirZone
	* @Date : 2016. 12. 19.
	* @description : 최근 연도 사업지구 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="nowestAirZone.do")
	public ModelAndView nowestAirZone(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/search/nowestAirZone.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::nowestAirZone", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/** 
	* <pre>
	* @Method : getZipCodeList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 지번 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getZipCodeList.do")
	public ModelAndView getZipCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/getZipCodeList.do";
			String sendParam = "";
			
			if(param != null){
				Iterator<String> iter = param.keySet().iterator();
				while(iter.hasNext()){
					String key = (String)iter.next();
					
					if (key.equals("mntnYn")){
						sendParam += "&mntnYn=" + param.get(key);
					}
					if (key.equals("lnbrMnnm")){
						sendParam += "&lnbrMnnm=" + param.get(key);
					}
					if (key.equals("lnbrSlno")){
						sendParam += "&lnbrSlno=" + param.get(key);
					}
					if (key.equals("emdCd")){
						sendParam += "&emdCd=" + param.get(key);
					}
					if (key.equals("sigunguCode")){
						sendParam += "&sigunguCode=" + param.get(key);
					}
				}
			}
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response, 60*1000, 60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getZipCodeList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/** 
	* <pre>
	* @Method : getJusoList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 지번 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getJusoList.do")
	public ModelAndView getJusoList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/search/getJusoList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::getJusoList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getBuldNoList
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 건물번호 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getBuldNoList.do")
	public ModelAndView getBuldNoList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/search/getBuldNoList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 60*1000, 60*1000);
			
			modelAndView.addObject("result", jsonMap.get("result"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::getBuldNoList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/** 
	* <pre>
	* @Method : selectIndexMapName
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 도엽 검색
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="selectIndexMapName.do")
	public ModelAndView selectIndexMapName(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/search/selectIndexMapName.do";
			String sendParam = "selectKey=" + (String)param.get("selectKey");
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response);
			
			modelAndView.addObject("IndexMapName", jsonMap.get("IndexMapName"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::selectIndexMapName", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
}
