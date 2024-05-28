package org.nii.niis.rest.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.rest.web
* @FileName : CurrentPositionController.java 
* @Date : 2016. 12. 19.
* @description : 지도 위치 조회 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/rest/")
public class CurrentPositionController {	
	
	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="connService")
	private ConnService connService;
	
	
    /** 
    * <pre>
    * @Method : getCurPosition
    * @Date : 2016. 12. 19.
    * @description : 현재 위치 조회
    * </pre>
    * @param request
    * @param response
    * @param model
    * @throws Exception
    */
    @RequestMapping(value="getCurPosition.do")
    public void getCurPosition(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
    	
    	try{
	    	String sendParam = "x=" + request.getParameter("x")
	    					+ "&y=" + request.getParameter("y");
	    	
	    	connService.connNiimToXml("/rest/getCurPosition.do", sendParam, request, response);
	        
    	}catch(Exception e){
    		log.error(this.getClass() + "::::getCurPosition", e);
    	}
    }
}