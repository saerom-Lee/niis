package org.nii.niis.search.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.common.util.BlackList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.search.web
* @FileName : AddressSearchController.java 
* @Date : 2016. 12. 19.
* @description : 행정명 검색 controller 객체
* </pre>
*/
@Controller
public class AddressSearchController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="connService")
	private ConnService connService;
	
	
	/** 
	* <pre>
	* @Method : getAddressArea
	* @Date : 2016. 12. 19.
	* @description : 행정경계를 가져온다
	* </pre>
	* @param request
	* @param response
	* @throws Exception
	*/
	@RequestMapping(value="/search/searchAddressArea.do")
	public void getAddressArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
	    	String sendParam = "admcd=" + BlackList.getStrCnvrXss(request.getParameter("admcd"));
	    	
	    	connService.connNiimToXml("/search/searchAddressArea.do", sendParam, request, response);
	        
    	}catch(Exception e){
    		log.error(this.getClass() + "::::getAddressArea", e);
    	}	
	}
	
	/** 
	* <pre>
	* @Method : getIndexMapArea
	* @Date : 2016. 12. 19.
	* @description : 행정경계를 가져온다
	* </pre>
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	*/
	@RequestMapping(value="/search/searchIndexMapArea.do")
	public void getIndexMapArea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
	    	String sendParam = "selectKey=" + BlackList.getStrCnvrXss(request.getParameter("selectKey"));
	    	
	    	connService.connNiimToXml("/search/searchIndexMapArea.do", sendParam, request, response);
	        
    	}catch(Exception e){
    		log.error(this.getClass() + "::::getIndexMapArea", e);
    	}
	}
}