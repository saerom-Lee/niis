package org.nii.niis.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rte.fdl.property.EgovPropertyService;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.common.interceptor
* @FileName : SessionCheckInterceptor.java 
* @Date : 2016. 12. 19.
* @description : 세션 체크 interceptor 객체
* </pre>
*/
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

	Logger log = LogManager.getLogger(this.getClass());
	
	private String ajaxHaeder = "AJAX";
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.debug("##############################################");
		log.debug("SessionCheckInterceptor preHandle");
		log.debug("##############################################");
		
		if(request.getRequestURI().indexOf("/login/") == -1 && request.getRequestURI().indexOf("/security") != 5){
			/*System.out.println("requestURI>>>" + request.getRequestURI());
			System.out.println("requestURI indexOf>>> " + request.getRequestURI().indexOf("/niis/niim/"));
			System.out.println("requestURI indexOf>>> " + ((Integer) request.getRequestURI().indexOf("/niis/niim/") > -1) ); 
			if((Integer) request.getRequestURI().indexOf("/niis/niim/") > -1){
				System.out.println("===========/niis/niim 들어옴=========");
				if(!propertiesService.getString("Globals.niisIp").equals(getClientIP(request))){
					//허용되지 않은 IP
					System.out.println("===========/niis/niim 허용되지 않은 IPS=========");
					response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
					return false;
				}
				
				request.setAttribute("niisForwarding", propertiesService.getString("Globals.niisAcsKey"));
			}
			
			System.out.println("/login/===========확인=========");
			*/
			if(request.getRequestURI().indexOf("/send/") > -1){
				return true;
			}else if(request.getRequestURI().indexOf("/niis/niim/") > -1 || request.getRequestURI().indexOf("/apply/") > -1
					|| request.getRequestURI().indexOf("/board/") > -1 || request.getRequestURI().indexOf("/rest/") > -1
					|| request.getRequestURI().indexOf("/management/") > -1 || request.getRequestURI().indexOf("/search/") > -1
					|| request.getRequestURI().indexOf("/niimFor/") > -1 || request.getRequestURI().indexOf("/disaster/") > -1){
				return true;
			}else if(request.getSession().getAttribute("aUserMgno") == null){
				
				if(isAjaxRequest(request)){
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}else{
					response.sendRedirect("/niis/login/gpkiLogin.do");
					response.flushBuffer();
				}
				return false;
			}
		}
		return true;
	}
    
    
    /**
	 * ajax 여부 반환
	 * @param request
	 * @return boolean
	 */
	private boolean isAjaxRequest(HttpServletRequest request) {
		return request.getHeader(ajaxHaeder) != null && request.getHeader(ajaxHaeder).equals(Boolean.TRUE.toString());
	}
	
	/**
	 * common interceptor postHandle
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

	
	/**
	 * common interceptor afterCompletion
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
	
	
	
	
	/*private String getClientIP(HttpServletRequest request) {
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		
		if (ip == null || ip.length() == 0){
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0){
			ip = request.getRemoteAddr() ;
		}

		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}*/
}
