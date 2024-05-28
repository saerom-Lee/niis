package org.nii.niis.common.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.nii.niis.common.JSONUtil;
import org.nii.niis.common.service.ConnService;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.common.service.impl
* @FileName : ConnServiceImpl.java 
* @Date : 2016. 12. 19.
* @description : 관리 시스템 연동 implement 객체
* </pre>
*/
@Service("connService")
public class ConnServiceImpl implements ConnService {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;

	@Override
	public Map<String, Object> connNiimTest(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return connNiim(action, param, request, response, 60*60*1000, 60*60*1000);
	}
	@Override
	public Map<String, Object> connNiim(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return connNiim(action, param, request, response, propertiesService.getInt("Globals.connectTimeout"), propertiesService.getInt("Globals.readTimeout"));
	}
	@Override
	public Map<String, Object> connNiim(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception {
		
		String sendParam = "";
		
		if(param != null){
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){
				String key = (String)iter.next();
				sendParam += "&" + key + "=" + param.get(key);
			}
		}
		
		return connNiim(action, sendParam, request, response, connectTimeout, readTimeout);
	}
	
	@Override
	public Map<String, Object> connNiim(String action, String sendParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return connNiim(action, sendParam, request, response, propertiesService.getInt("Globals.connectTimeout"), propertiesService.getInt("Globals.readTimeout"));
	}
	@Override
	public Map<String, Object> connNiim(String action, String sendParam, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception {

		URL url = null;
		URLConnection connection = null;
		
		OutputStream os 		= null;		
		InputStream is 			= null;
		InputStreamReader isr 	= null;
		BufferedReader br 		= null;
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try{
			sendParam = validateSendParam(request, sendParam);
			
			String baseUrl = propertiesService.getString("Globals.niimUrl");
			
			if("/".equals(action.substring(0, 1))){
				action = action.substring(1);
			}
			
			url = new URL(baseUrl + action);
			System.out.println("=========================");
			System.out.println(url);
			System.out.println("=========================");
			if("disaster/disasterFileDwn.do".equals(action)) {
				connectTimeout = propertiesService.getInt("Globals.g119ConnectTimeout");
				readTimeout = propertiesService.getInt("Globals.g119ReadTimeout");
			}
			
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			
			connection.connect();
			
			os = (OutputStream)connection.getOutputStream();
			os.write(sendParam.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			String headerType = connection.getContentType().toUpperCase();
			
			is = connection.getInputStream();
			
			if(headerType.indexOf("UTF-8") != -1){
				isr = new InputStreamReader(is, "UTF-8");
			}else{
				isr = new InputStreamReader(is, "EUC-KR");
			}
			
			br = new BufferedReader(isr);
			
			StringBuffer sb = new StringBuffer();
			String line;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			String strText = sb.toString();
			//log.debug("strText[" + strText + "]");
			
			jsonMap = JSONUtil.jsonToMap(strText);
			
			if(jsonMap.containsKey("rtnCd") && 500 == ((Long)jsonMap.get("rtnCd")).intValue()){
				throw new Exception();
			}
			
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_OK);
		}catch(FileNotFoundException e){
			//영상정보 관리시스템에 action이 없습니다.
			log.error(this.getClass() + "::::connNiim connection not found method", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_NOT_IMPLEMENTED);
		}catch(SocketException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiim connection refused", e);
			jsonMap.put("RETURN_CD", 521);
		}catch(SocketTimeoutException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiim connection timeout", e);
			jsonMap.put("RETURN_CD", 521);
		}catch(IOException e){
			//허용되지 않은 접근
			log.error(this.getClass() + "::::connNiim not allowed access", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_NOT_ACCEPTABLE);
		}catch(Exception e){
			log.error(this.getClass() + "::::connNiim connection error", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		}finally{
			if(null != os){ try{ os.close(); }catch(Exception e){} }
			if(null != br){ try{ br.close(); }catch(Exception e){} }
			if(null != isr){ try{ isr.close(); }catch(Exception e){} }
			if(null != is){ try{ is.close(); }catch(Exception e){} }
		}
		return jsonMap;
	}

	@Override
	public void connNiimToXml(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		connNiimToXml(action, param, request, response, propertiesService.getInt("Globals.connectTimeout"), propertiesService.getInt("Globals.readTimeout"));
	}
	
	@Override
	public void connNiimToXml(String action, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception {
		
		String sendParam = "";
		
		if(param != null){
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){
				String key = (String)iter.next();
				sendParam += "&" + key + "=" + (String)param.get(key);
			}
		}
		
		connNiimToXml(action, sendParam, request, response, connectTimeout, readTimeout);
	}
	
	@Override
	public void connNiimToXml(String action, String sendParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		connNiimToXml(action, sendParam, request, response, propertiesService.getInt("Globals.connectTimeout"), propertiesService.getInt("Globals.readTimeout"));
	}
	
	@Override
	public void connNiimToXml(String action, String sendParam, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception {
		
		URL url = null;
		URLConnection connection = null;
		
		OutputStream os 		= null;		
		InputStream is 			= null;
		
		try{
			sendParam = validateSendParam(request, sendParam);
			
			String baseUrl = propertiesService.getString("Globals.niimUrl");
			
			if("/".equals(action.substring(0, 1))){
				action = action.substring(1);
			}
			
			url = new URL(baseUrl + action);
			
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			
			connection.connect();
			
			os = (OutputStream)connection.getOutputStream();
			os.write(sendParam.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			is = connection.getInputStream();
			
			byte[] xml = new byte[4096];
			int n = - 1;
			
			os = response.getOutputStream();
			
			while((n = is.read(xml)) != -1){
				os.write(xml, 0, n);
			}
			os.flush();
			os.close();
		}catch(FileNotFoundException e){
			//영상정보 관리시스템에 action이 없습니다.
			log.error(this.getClass() + "::::connNiimToXml connection not found method", e);
		}catch(SocketException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiimToXml connection refused", e);
		}catch(SocketTimeoutException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiimToXml connection timeout", e);
		}catch(IOException e){
			//허용되지 않은 접근
			log.error(this.getClass() + "::::connNiimToXml not allowed access", e);
		}catch(Exception e){
			log.error(this.getClass() + "::::connNiimToXml connection error", e);
		}finally{
			if(null != os){ try{ os.close(); }catch(Exception e){} }
			if(null != is){ try{ is.close(); }catch(Exception e){} }
		}
	}
	
	@Override
	public Map<String, Object> connNiimCode(String queryID, Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String action = "/common/getCommonCode.do";
		/************************************** queryID 필수 **************************************/ 
		String sendParam = "queryID=" + queryID;
		/************************************** queryID 필수 **************************************/
		
		if(param != null){
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){
				String key = (String)iter.next();
				sendParam += "&" + key + "=" + (String)param.get(key);
			}
		}
		
		Map<String, Object> jsonMap = null;
		
		try{
			jsonMap = this.connNiim(action, sendParam, request, response);
		}catch(Exception e){
			log.error(this.getClass() + "::::connNiimCode", e);
			throw e;
		}
		
		return jsonMap;
	}
	
	@Override
	public Map<String, Object> connNiimToJson(String action, Map<String, Object> sendMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return connNiimToJson(action, sendMap, request, response, propertiesService.getInt("Globals.connectTimeout"), propertiesService.getInt("Globals.readTimeout"));
	}
	@Override
	public Map<String, Object> connNiimToJson(String action, Map<String, Object> sendMap, HttpServletRequest request, HttpServletResponse response, int connectTimeout, int readTimeout) throws Exception {
		
		URL url = null;
		URLConnection connection = null;
		
		OutputStream os 		= null;		
		InputStream is 			= null;
		InputStreamReader isr 	= null;
		BufferedReader br 		= null;
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try{
			JSONObject data = validateSendMap(request, sendMap);
			
			String baseUrl = propertiesService.getString("Globals.niimUrl");
			
			if("/".equals(action.substring(0, 1))){
				action = action.substring(1);
			}
			
			url = new URL(baseUrl + action);
			
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type", "application/json");
			//connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			connection.setConnectTimeout(propertiesService.getInt("Globals.connectTimeout"));
			connection.setReadTimeout(propertiesService.getInt("Globals.readTimeout"));
			
			connection.connect();
			
			os = (OutputStream)connection.getOutputStream();
			os.write(data.toJSONString().getBytes("UTF-8"));
			os.flush();
			os.close();
			
			String headerType = connection.getContentType().toUpperCase();
			
			is = connection.getInputStream();
			
			if(headerType.indexOf("UTF-8") != -1){
				isr = new InputStreamReader(is, "UTF-8");
			}else{
				isr = new InputStreamReader(is, "EUC-KR");
			}
			
			br = new BufferedReader(isr);
			
			StringBuffer sb = new StringBuffer();
			String line;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			String strText = sb.toString();
			//log.debug("strText[" + strText + "]");
			
			jsonMap = JSONUtil.jsonToMap(strText);
			
			if(jsonMap.containsKey("rtnCd") && 500 == ((Long)jsonMap.get("rtnCd")).intValue()){
				throw new Exception();
			}
			
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_OK);
		}catch(FileNotFoundException e){
			//영상정보 관리시스템에 action이 없습니다.
			log.error(this.getClass() + "::::connNiimToJson connection not found method", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_NOT_IMPLEMENTED);
		}catch(SocketException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiimToJson connection refused", e);
			jsonMap.put("RETURN_CD", 521);
		}catch(SocketTimeoutException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiimToJson connection timeout", e);
			jsonMap.put("RETURN_CD", 521);
		}catch(IOException e){
			//허용되지 않은 접근
			log.error(this.getClass() + "::::connNiimToJson not allowed access", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_NOT_ACCEPTABLE);
		}catch(Exception e){
			log.error(this.getClass() + "::::connNiimToJson connection error", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}finally{
			if(null != os){ try{ os.close(); }catch(Exception e){} }
			if(null != br){ try{ br.close(); }catch(Exception e){} }
			if(null != isr){ try{ isr.close(); }catch(Exception e){} }
			if(null != is){ try{ is.close(); }catch(Exception e){} }
		}
		return jsonMap;
	}
	
	@Override
	public Map<String, Object> connNiim(String action) throws Exception {

		URL url = null;
		URLConnection connection = null;
		
		OutputStream os 		= null;		
		InputStream is 			= null;
		InputStreamReader isr 	= null;
		BufferedReader br 		= null;
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try{
			String baseUrl = propertiesService.getString("Globals.niimUrl");
			
			if("/".equals(action.substring(0, 1))){
				action = action.substring(1);
			}
			
			url = new URL(baseUrl + action);
			
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setConnectTimeout(propertiesService.getInt("Globals.connectTimeout"));
			connection.setReadTimeout(propertiesService.getInt("Globals.readTimeout"));
			
			connection.connect();
	
			os = (OutputStream)connection.getOutputStream();
			os.flush();
			os.close();
	
			String headerType = connection.getContentType().toUpperCase();
			
			is = connection.getInputStream();
			
			if(headerType.indexOf("UTF-8") != -1){
				isr = new InputStreamReader(is, "UTF-8");
			}else{
				isr = new InputStreamReader(is, "EUC-KR");
			}
			
			br = new BufferedReader(isr);
			
			StringBuffer sb = new StringBuffer();
			String line;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			String strText = sb.toString();
			//log.debug("strText[" + strText + "]");
			
			jsonMap = JSONUtil.jsonToMap(strText);
			
			if(jsonMap.containsKey("rtnCd") && 500 == ((Long)jsonMap.get("rtnCd")).intValue()){
				throw new Exception();
			}
			
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_OK);
		}catch(FileNotFoundException e){
			//영상정보 관리시스템에 action이 없습니다.
			log.error(this.getClass() + "::::connNiim connection not found method", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_NOT_IMPLEMENTED);
		}catch(SocketException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiim connection refused", e);
			jsonMap.put("RETURN_CD", 521);
		}catch(SocketTimeoutException e){
			//영상정보 관리시스템 연결에 실패하였습니다.
			log.error(this.getClass() + "::::connNiim connection timeout", e);
			jsonMap.put("RETURN_CD", 521);
		}catch(IOException e){
			//허용되지 않은 접근
			log.error(this.getClass() + "::::connNiim not allowed access", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_NOT_ACCEPTABLE);
		}catch(Exception e){
			log.error(this.getClass() + "::::connNiim connection error", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		}finally{
			if(null != os){ try{ os.close(); }catch(Exception e){} }
			if(null != br){ try{ br.close(); }catch(Exception e){} }
			if(null != isr){ try{ isr.close(); }catch(Exception e){} }
			if(null != is){ try{ is.close(); }catch(Exception e){} }
		}
		return jsonMap;
	}
	
	private String validateSendParam(HttpServletRequest request, String sendParam) throws Exception {
		
		HttpSession session = request.getSession(false);
		StringBuffer sb = new StringBuffer();
		
		if(null == session || null == session.getAttribute("aUserMgno")){
			//throw new Exception("세션만료");
		}else{
			sb.append("connUserNo=").append(session.getAttribute("aUserMgno"));
			sb.append("&connUserId=").append(session.getAttribute("aUserId"));
			sb.append("&connUserAuth=").append(session.getAttribute("aUserAuth"));
		}
		sb.append("&connAcsIp=").append(getClientIP(request));
		
		if(null == sendParam || "".equals(sendParam)){
			sendParam = "";
		}else{
			if(!"&".equals(sendParam.substring(0, 1))){
				sendParam = "&" + sendParam;
			}
		}
		log.debug("sendParam[" + sb.toString() + sendParam + "]");
		return sb.toString() + sendParam;
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject validateSendMap(HttpServletRequest request, Map<String, Object> sendMap) throws Exception {
		
		HttpSession session = request.getSession(false);
		JSONObject returnMap = new JSONObject();
		
		if(null == session || null == session.getAttribute("aUserMgno")){
			//throw new Exception("세션만료");
		}else{
			returnMap.put("connUserNo", session.getAttribute("aUserMgno"));
			returnMap.put("connUserId", session.getAttribute("aUserId"));
			returnMap.put("connUserAuth", session.getAttribute("aUserAuth"));
		}
		returnMap.put("connAcsIp", getClientIP(request));
		
		if(null != sendMap){
			Iterator<String> iter = sendMap.keySet().iterator();
			while(iter.hasNext()){
				String key = (String)iter.next();
				returnMap.put(key, sendMap.get(key));
			}
		}
		log.debug(returnMap.toString());
		return returnMap;
	}
	
	private String getClientIP(HttpServletRequest request) {
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		
		if(ip == null || ip.length() == 0){
			ip = request.getHeader("Proxy-Client-IP");
		}
		
		if(ip == null || ip.length() == 0){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if(ip == null || ip.length() == 0){
			ip = request.getRemoteAddr() ;
		}

		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
	}
}
