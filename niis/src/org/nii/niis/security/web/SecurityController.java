package org.nii.niis.security.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.security.service.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CKNB.WatermarkComponent;

import com.innorix.transfer.InnorixUpload;

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
@RequestMapping(value="/security/")
public class SecurityController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	@Resource(name="securityService")
	private SecurityService securityService;
	

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
	@RequestMapping(value="uploadPop.fu")
    public String downloadPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "security/uploadPop";
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
			System.out.println("urlInfo>>>>>>>>>>>>" + urlInfo);
			param.put("urlInfo", urlInfo);
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			System.out.println("jsonMap222222>>>>>>>>>>>" + jsonMap);
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
	 * 파일 업로드 
	 * */
	@RequestMapping(value="fileUpload.fu")
	public void downloadComplete(@RequestParam Object param,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		System.out.println("Param>>>>>>>>>>>>>>" + param);
		System.out.println("===========1111111111=================");
		String directory = "C:/upload";
		// directory += request.getParameter("param");
		// System.out.println("디렉토리-----------------"+directory);
		int maxPostSize = 2147482624; // bytes
		SecurityController securityController = new SecurityController();
		ArrayList<String> list = new ArrayList<String>();
		
		/*
		    directory
		        파일이 저장될 스토리지의 전체경로
		        디렉토리 구분은 윈도우, 유닉스 모두 "/" 문자 사용
		        윈도우 - C:/storage/path1/path2/data
		        유닉스 - /storage/path1/path2/data

		    maxPostSize
		        한번에 업로드 가능한 최대 데이터 사이즈(bytes)
		        최소값 10485760(10MB) ~ 최대값 2147482624(2GB - 1024)
		        최대값을 넘는 빅데이터 파일도 내부 처리에 의해 용량 제한없이 전송됨
		*/

		// uploader 객체선언
		InnorixUpload uploader = new InnorixUpload(request, response, maxPostSize, directory);

		/*
		    전달되는 _action Param 정보
		        speedCheck          : 전송속도 측정 (* innoex 제품에서 사용)
		        getServerInfo       : 서버정보 확인
		        getFileInfo         : 파일정보 확인
		        attachFile          : 파일전송 진행
		        attachFileCompleted : 파일전송 완료
		*/

		System.out.println("========== uploader START " + System.currentTimeMillis() + " ==========");
		long startTime = System.currentTimeMillis();
		String _action          = uploader.getParameter("_action");         // 동작 플래그
		String _orig_filename   = uploader.getParameter("_orig_filename");  // 원본 파일명
		String _new_filename    = uploader.getParameter("_new_filename");   // 저장 파일명
		String _filesize        = uploader.getParameter("_filesize");       // 파일 사이즈
		String _start_offset    = uploader.getParameter("_start_offset");   // 파일저장 시작지점
		String _end_offset      = uploader.getParameter("_end_offset");     // 파일저장 종료지점
		String _filepath        = uploader.getParameter("_filepath");       // 파일 저장경로
		String _param1          = uploader.getParameter("_param1");         // 임의정의 GET Param 값
		String _param2          = uploader.getParameter("_param2");         // 임의정의 POST Param 값
		String _subdir 			= uploader.getParameter("_subdir");			// subdir 전달
		
		// 중복 파일명 덮어쓰기
		uploader.setOverwrite(true);

		// 저장될 파일명 지정
		uploader.setFileName(_orig_filename);

		// 파일저장 실행
		String _run_retval = uploader.run();

		/*
		uploader.run(); 리턴값
		    0000 정상
		    0001 경로 없음
		    0002 쓰기권한 없음
		    0003 무결성 검사 실패
		    1001 디렉토리 생성 실패
		    1002 압축해제 실패
		*/

		// 개별파일 업로드 완료
		if (uploader.isUploadDone()) {
			
		    System.out.println("========== uploader.isUploadDone() " + System.currentTimeMillis() + " ==========");
		    System.out.println("_orig_filename \t = " + _orig_filename);
		    System.out.println("_new_filename \t = " + _new_filename);
		    System.out.println("_filesize \t = " + _filesize);
		    System.out.println("_filepath \t = " + _filepath);
		    long endTime = System.currentTimeMillis();
		    String detMark = WatermarkComponent.detecting(_filepath);
		    
		    String filename[] = _orig_filename.split("\\.");
		    Map<String,Object> map = new HashMap<String,Object>();
		    
		    if(!(String.valueOf(detMark).equals("null") || String.valueOf(detMark).equals(null) || String.valueOf(detMark).equals(""))){ 
			    if(filename.length == 2){
			    	map.put("fileNam",filename[0]);
			    	map.put("fileFormat",filename[1]);
			    }
			    map.put("markIndex",String.valueOf(detMark).substring(0,8));
			    
			    securityService.detecting(map);
	
		    }else{   
		    	File file = new File(_filepath);
			    file.delete();
		    	throw new Exception();	
		    }
		    
		    File file = new File(_filepath);
		    file.delete();
		    
		}

		
		System.out.println("========== innorix transfer " + System.currentTimeMillis() + " ==========");
		System.out.println("_action \t = " + _action);
		System.out.println("_run_retval \t = " + _run_retval);
		System.out.println("_orig_filename \t = " + _orig_filename);
		System.out.println("_new_filename \t = " + _new_filename);
		System.out.println("_filesize \t = " + _filesize);
		System.out.println("_start_offset \t = " + _start_offset);
		System.out.println("_end_offset \t = " + _end_offset);
		System.out.println("_filepath \t = " + _filepath);
		System.out.println("_param1 \t = " + _param1);
		System.out.println("_param2 \t = " + _param2);
		System.out.println("_subdir \t = " + _subdir);
		System.out.println("=================222222222222222=================");
		
	}
}
