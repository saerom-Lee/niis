package org.nii.niis.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.common.service
* @FileName : ConnService.java 
* @Date : 2016. 12. 19.
* @description : 관리 시스템 연동 interface 객체
* </pre>
*/
public interface ConnService {

	Map<String, Object> connNiimTest(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception;
	/** 
	* <pre>
	* @Method : connNiim
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiim(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception;
	/** 
	* <pre>
	* @Method : connNiim
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param param
	* @param request
	* @param response
	* @param connectTimeout
	* @param readTimeout
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiim(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception;
	/** 
	* <pre>
	* @Method : connNiim
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param sendParam
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiim(String action, String sendParam, HttpServletRequest request, HttpServletResponse response) throws Exception;
	/** 
	* <pre>
	* @Method : connNiim
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param sendParam
	* @param request
	* @param response
	* @param connectTimeout
	* @param readTimeout
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiim(String action, String sendParam, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception;
	
	/** 
	* <pre>
	* @Method : connNiimToXml
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param param
	* @param request
	* @param response
	* @throws Exception
	*/
	void connNiimToXml(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception;
	/** 
	* <pre>
	* @Method : connNiimToXml
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param param
	* @param request
	* @param response
	* @param connectTimeout
	* @param readTimeout
	* @throws Exception
	*/
	void connNiimToXml(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception;
	/** 
	* <pre>
	* @Method : connNiimToXml
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param sendParam
	* @param request
	* @param response
	* @throws Exception
	*/
	void connNiimToXml(String action, String sendParam, HttpServletRequest request, HttpServletResponse response) throws Exception;
	/** 
	* <pre>
	* @Method : connNiimToXml
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param sendParam
	* @param request
	* @param response
	* @param connectTimeout
	* @param readTimeout
	* @throws Exception
	*/
	void connNiimToXml(String action, String sendParam, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception;
	
	/** 
	* <pre>
	* @Method : connNiimCode
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param queryID
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiimCode(String queryID, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception;

	/** 
	* <pre>
	* @Method : connNiimToJson
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param sendMap
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiimToJson(String action, Map<String, Object> sendMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	/** 
	* <pre>
	* @Method : connNiimToJson
	* @Date : 2016. 12. 19.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @param sendMap
	* @param request
	* @param response
	* @param connectTimeout
	* @param readTimeout
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiimToJson(String action, Map<String, Object> sendMap, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception;
	
	/** 
	* <pre>
	* @Method : connNiim
	* @Date : 2018. 01. 18.
	* @description : 관리시스템 연동
	* </pre>
	* @param action
	* @return
	* @throws Exception
	*/
	Map<String, Object> connNiim(String action) throws Exception;
}
