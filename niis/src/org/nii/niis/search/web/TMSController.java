package org.nii.niis.search.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * <pre>
 * @Project : niis 
 * @Package : org.nii.niis.search.web
 * @FileName : TMSController.java 
 * @Date : 2017. 10. 31.
 * @description : 지도 이미지 controller 객체
 * </pre>
 */
@Controller
@RequestMapping(value="/tms/")
public class TMSController {
	
	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;
	
	public void output(HttpServletRequest request, HttpServletResponse response, HttpSession session, String baseUrl, String path){
		OutputStream os 		= null;		
		InputStream is 			= null;
		InputStreamReader isr 	= null;
		BufferedReader br 		= null;
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		try{
			java.awt.image.BufferedImage img = javax.imageio.ImageIO.read(new URL(baseUrl + path));
			java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
			javax.imageio.ImageIO.write(img, "png", baos);
	
			byte[] imgByte = baos.toByteArray();
	
		    javax.servlet.ServletOutputStream responseOutputStream = response.getOutputStream();
		    responseOutputStream.write(imgByte);
		    responseOutputStream.flush();
		    responseOutputStream.close();
			
		}catch(Exception e){
			log.error(this.getClass() + "::::connNiim connection error", e);
			jsonMap.put("RETURN_CD", HttpServletResponse.SC_SERVICE_UNAVAILABLE);
		}finally{
			if(null != os){ try{ os.close(); }catch(Exception e){} }
			if(null != br){ try{ br.close(); }catch(Exception e){} }
			if(null != isr){ try{ isr.close(); }catch(Exception e){} }
			if(null != is){ try{ is.close(); }catch(Exception e){} }
		}
	}

	@RequestMapping(value="etms.do")
	public void ETMS(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session){		
//		log.debug(param);
		
		String baseUrl = propertiesService.getString("Globals.etmsurl");
		String path = param.get("url").toString();
		
		output(request, response, session, baseUrl, path);
	}
	
	@RequestMapping(value="tms.do")
	public void TMS(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session){		
//		log.debug(param);
		
		String baseUrl = propertiesService.getString("Globals.tmsurl");
		String path = param.get("url").toString();
		
		output(request, response, session, baseUrl, path);
	}
	
	@RequestMapping(value="nix/airs.do")
	public void NIX_airs(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session){		
		
		String baseUrl = propertiesService.getString("Globals.nixurl");
		String path = "airs";
		output(request, response, session, baseUrl, path);
	}
	
	@RequestMapping(value="nix/orts.do")
	public void NIX_orts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session){		
		
		String baseUrl = propertiesService.getString("Globals.nixurl");
		Enumeration<String> en = request.getParameterNames();
		int i = 0;
		String path = "orts";
		while(en.hasMoreElements()){
			String key = en.nextElement();
			if(i == 0){
				path += "?" + key + "=" + request.getParameter(key);
			}else{
				path += "&" + key + "=" + request.getParameter(key);
			}
			i++;
		}
		
		output(request, response, session, baseUrl, path);
	}
	
	@RequestMapping(value="nix/dems.do")
	public void NIX_dems(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session){		
		
		String baseUrl = propertiesService.getString("Globals.nixurl");
		Enumeration<String> en = request.getParameterNames();
		int i = 0;
		String path = "dems";
		while(en.hasMoreElements()){
			String key = en.nextElement();
			if(i == 0){
				path += "?" + key + "=" + request.getParameter(key);
			}else{
				path += "&" + key + "=" + request.getParameter(key);
			}
			i++;
		}
		
		output(request, response, session, baseUrl, path);
	}
	
	@RequestMapping(value="nix/nirs.do")
	public void NIX_nirs(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session){		
		
		String baseUrl = propertiesService.getString("Globals.nixurl");
		String path = "dems";
		
		output(request, response, session, baseUrl, path);
	}

}
