package org.nii.niis.supply.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.common.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.supply.web
* @FileName : SupplyConroller.java 
* @Date : 2016. 12. 19.
* @description : 영상 다운로드 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/supply/")
public class SupplyController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	/**
	 * fileUtil controller 객체
	 * 
	 * @directed
	 */
	@Resource(name="fileUtil")
    private FileUtil fileUtil;
	
	
	/** 
	* <pre>
	* @Method : downloadListPop
	* @Date : 2017. 1. 19.
	* @description : 영상 다운로드 선택 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="downloadListPop.do")
    public String downloadListPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("param>>>>>>>1111" + param);
		try{
			String action = "/supply/getDownloadList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("supIdn", param.get("supIdn"));
			model.addAttribute("airList", jsonMap.get("airList"));
			model.addAttribute("airLibList", jsonMap.get("airLibList"));
			model.addAttribute("demList", jsonMap.get("demList"));
			model.addAttribute("ortList", jsonMap.get("ortList"));
			model.addAttribute("atList", jsonMap.get("atList"));
			model.addAttribute("mapList", jsonMap.get("mapList"));
			model.addAttribute("metaList", jsonMap.get("metaList"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::downloadListPop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "supply/downloadListPop";
	}
	
	/**
	 * 수치지형도 표준메타데이터
	 * @param model
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="downloadMapMetaListPop.do")
    public String downloadMapMetaListPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String action = "/supply/downloadMapMetaList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			model.addAttribute("getMapMetaList", jsonMap.get("getMapMetaList"));
		}catch(Exception e){
			log.error(this.getClass() + "::::downloadMapMetaListPop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "supply/downloadMapMetaListPop";
	}
	
	/** 
	* <pre>
	* @Method : downloadPop
	* @Date : 2016. 12. 19.
	* @description : 영상 다운로드 확인 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="downloadPop.do")
    public String downloadPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		model.addAttribute("supIdn", param.get("supIdn"));
		model.addAttribute("imageCde", param.get("imageCde"));
		model.addAttribute("group", param.get("group"));
		
		return "supply/downloadPop";
	}
	
	/** 
	* <pre>
	* @Method : getFileList
	* @Date : 2016. 12. 19.
	* @description : 다운로드 영상 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getFileList.do")
    public ModelAndView getFileList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/supply/getFileList.do";
			
			String urlInfo = request.getScheme() + "://"
						+    request.getServerName() + ":" 
						+    request.getServerPort() 
						+    request.getContextPath()
						+    propertiesService.getString("Globals.innorixUrl");

			param.put("urlInfo", urlInfo);
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getFileList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : downloadComplete
	* @Date : 2016. 12. 19.
	* @description : 다운로드 완료 처리
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="downloadComplete.do")
    public ModelAndView downloadComplete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/supply/downloadComplete.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::downloadComplete", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/** 
	* <pre>
	* @Method : downloadPop
	* @Date : 2016. 12. 19.
	* @description : 지오프라 영상 다운로드 확인 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="geoDownloadPop.do")
    public String geoDownloadPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("param>>>>>>>>>>>" + param);
		model.addAttribute("supIdn", param.get("supIdn"));
		model.addAttribute("imageCde", param.get("imageCde"));
		model.addAttribute("group", param.get("group"));
		
		return "supply/geoDownloadPop";
	}
	
	/**
	 * 공지사항 파일 다운로드
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="downloadMetaFile.do")
	public void downloadMetaFile(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{			
			Map<String, Object> map = new HashMap<String, Object>();
			
			HashMap<String, String> fileMap = new HashMap<String, String>();
			fileMap.put("userAgent"			, request.getHeader("User-Agent"));
			fileMap.put("realFileName"		, (String)param.get("supIdn")+".zip");
			fileMap.put("originalFileName"	, (String)param.get("supIdn")+".zip");
			
			System.out.println(fileMap.toString());
			//{realFileName=/upload/board/101/2021112215_Sese-4f21050711021.pdf}
			fileUtil.downloadMetaFile(request, response, fileMap);
			
		}catch(Exception e){
			log.error(this.getClass() + "::::downloadAttachFile", e);
		}
	}
	
	/** 
	* <pre>
	* @Method : metaFileCheck
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="metaFileCheck.do")
    public ModelAndView metaFileCheck(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			String filename = (String)param.get("supIdn")+".zip";
			
			String serverpath = "D:/METADATA/";
			
			File file = new File(serverpath + filename);
			
			if (file == null || !file.exists() || file.length() <= 0 || file.isDirectory()){
				throw new FileNotFoundException(file.getPath());
			}
			modelAndView.addObject("status", true);
		}catch(Exception e){
			modelAndView.addObject("status", false);
		}
		
		return modelAndView;
	}
}
