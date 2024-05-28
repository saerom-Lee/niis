package org.nii.niis.search.service;

import java.util.Map;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.search.service
* @FileName : SearchService.java 
* @Date : 2016. 12. 19.
* @description : 원본DB 검색 interface 객체
* </pre>
*/
public interface SearchService {
	
	/** 
	* <pre>
	* @Method : getImgSrchCondToString
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 파라미터 제어
	* </pre>
	* @param param
	* @return
	* @throws Exception
	*/
	String getImgSrchCondToString(Map<String, Object> param) throws Exception;
}
