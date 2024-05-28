package org.nii.niis.niim.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.nii.niis.niim.service.DisasterService;
import org.nii.niis.niim.util.BlackList;
import org.nii.niis.niim.util.PagingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Board controller 객체
 * 
 * @stereotype control
 */
@Controller
@RequestMapping(value="niimFor")
public class niimDisasterController {
	/**
	 * Board controller 객체
	 * 
	 * @stereotype control
	 */
	@Resource(name="disasterService")
	private DisasterService disasterService;
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	/**
	 * 게시판 master 정보 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/disaster/disaster.do")
	public ModelAndView disaster(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
//			modelAndView.addObject("master", disasterService.getDisasterMaster(param));
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	/**
	 * 게시판 리스트 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/disaster/getDisasterList.do")
	public ModelAndView getDisasterList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			int pageUnit = 10;
			if(param.containsKey("searchCof")){
				param.put("searchCof", BlackList.getStrCnvrXss((String)param.get("searchCof")));
			}
			if(param.containsKey("searchRegion")){
				param.put("searchRegion", BlackList.getStrCnvrXss((String)param.get("searchRegion")));
			}
			if(param.containsKey("searchKeyword")){
				param.put("searchKeyword", BlackList.getStrCnvrXss((String)param.get("searchKeyword")));
			}
			if(param.containsKey("listHeight")){
				String listHeight = BlackList.getStrCnvrXss((String)param.get("listHeight"));
				if (!"".equals(listHeight)) {
					try {
//						double height = Double.parseDouble(listHeight);
//						if (height > 1300)			pageUnit = 30;
//						else if (height > 1100)	pageUnit = 25;
//						else if (height > 900)	pageUnit = 20;
//						else if (height > 700)	pageUnit = 15;
//						else if (height > 500)	pageUnit = 12;
						double height = Math.floor(Double.parseDouble(listHeight)/45);
						pageUnit = (int) height;	
					} catch (Exception e) {
						System.out.println("숫자 형변환 오류");
					}
				}
			}
			
			param.put("_$pageUnit", pageUnit);
			int listCnt = disasterService.getDisasterListCnt(param);
			PagingUtil.getInstance().setPageData(param, modelAndView, listCnt);
			
			List<Map<String, Object>> boardList = disasterService.getDisasterList(param);
			
			modelAndView.addObject("list", boardList);
			modelAndView.addObject("totalcnt", listCnt);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/disaster/getDisasterVideoList.do")
	public ModelAndView getDisasterVideoList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			sendMap.put("msfrtnId"		, param.get("msfrtnId"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			List<Map<String, Object>> list = disasterService.getDisasterVideoList(sendMap);
			Map<String, Object> dataMap = disasterService.getDisasterDetail(param);
			
			modelAndView.addObject("list", list);
			modelAndView.addObject("map", dataMap);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/disaster/disasterTifImgData.do")
	public ModelAndView disasterTifImgData(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			if(param.get("datasetId") == null) {
				sendMap.put("datasetId"		, "0");
			} else {
				String datasetId = (String) param.get("datasetId");
				if("".equals(datasetId)) {
					sendMap.put("datasetId"		, "0");
				} else {
					sendMap.put("datasetId"		, datasetId);
				}
			}
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			List<Map<String, Object>> list = disasterService.getDisasterVideoList(sendMap);
			
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/disaster/disasterFileDwn.do")
	public ModelAndView disasterFileDwn(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String usrMgno = "";
		String userId = "";
		
		//공급시스템 요청
		if(param.containsKey("connUserNo")){
			usrMgno = (String)param.get("connUserNo");
			userId = (String)param.get("connUserId");
		}
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
		    byte[] buffer = new byte[1024];
		    String filePath = ""; 
		    List<String> resultList = new ArrayList<String>();
		    List<String> dataCdList = new ArrayList<String>();
		    List<String> fileList = new ArrayList<String>();
		    String zipFileName =  "";
		    String outFileName =  "";
	        Calendar cal = Calendar.getInstance();
			String tempPath = propertiesService.getString("Globals.g119TempPath");
			Calendar calendar = Calendar.getInstance();
			java.util.Date date = calendar.getTime();
			String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
	        
			JSONArray jsonList = JSONArray.fromObject(param.get("jsonData"));
			for(int i = 0 ; i < jsonList.size() ; i++){
				JSONObject jlist = (JSONObject) jsonList.get(i);
				String datasetId = (String) jlist.get("datasetId");
				String msfrtnId = (String) jlist.get("msfrtnId");
				sendMap.put("datasetId", datasetId);
				sendMap.put("msfrtnId", msfrtnId);
				
				//xacml 파라미터 추가
				//policyUtil.addPolicy(request, sendMap);
				
				List<Map<String, Object>> list = disasterService.getDisasterVideoList(sendMap);
				
				String fileEngName = list.get(0).get("fileEngNm").toString();
				String fileTy = list.get(0).get("fileTy").toString();
				String dataCd = list.get(0).get("dataCdB").toString();
				String tempUrl = "";
				String sendUrl = propertiesService.getString("Globals.g119SendUrl");
				sendUrl += "msfrtnId="+msfrtnId;
				//TIF일떼
				if(!"shp".equals(fileTy) && !"SHP".equals(fileTy)) {
					tempUrl = sendUrl + "&fileNm=" + fileEngName + "&type="+fileTy;
				    resultList.add(tempUrl);
				    dataCdList.add(dataCd);
				} else { //SHP일때
					tempUrl = sendUrl + "&fileNm=" + fileEngName + "&type="+fileTy;
					resultList.add(tempUrl);
					dataCdList.add(dataCd);
					
					tempUrl = sendUrl + "&fileNm=" + fileEngName + "&type=dbf";
					resultList.add(tempUrl);
					dataCdList.add(dataCd);
					
					tempUrl = sendUrl + "&fileNm=" + fileEngName + "&type=prj";
					resultList.add(tempUrl);
					dataCdList.add(dataCd);
					
					tempUrl = sendUrl + "&fileNm=" + fileEngName + "&type=shx";
					resultList.add(tempUrl);
					dataCdList.add(dataCd);
				}
				
				if(i == 0) {
					sendMap.put("apiManageSn", 0);
					sendMap.put("userId", userId);
					sendMap.put("dtaReqstSn",  999);
					sendMap.put("dwldDtaSe", "DWC002");
					sendMap.put("datasetId", datasetId);
					sendMap.put("fileNm", list.get(0).get("fileNm").toString());
					sendMap.put("datasetNm", list.get(0).get("datasetNm").toString());
					disasterService.insertDisasterDownloadHist(sendMap);
					
					zipFileName = tempPath + list.get(0).get("datasetNm").toString() + cal.getTimeInMillis() + ".zip";
					outFileName = list.get(0).get("datasetNm").toString() + "_영상다운로드";
				}
			}
			
			for (int j=0; j < resultList.size(); j++) {
				System.out.println("tempUrl     >>> "+resultList.get(j));
				String query = resultList.get(j);
				int pos1 = query.indexOf("?");
				if (pos1 >= 0) {
					query = query.substring(pos1 + 1);
				}

				String[] params = query.split("&");
				Map<String, String> qmap = new HashMap<String, String>();
				for (String param_ : params) {
					String name = param_.split("=")[0];
					String value = param_.split("=")[1];
					qmap.put(name, value);
				}
				
				String tmpName = "";
				String tmpExt = "";
				if (qmap != null) {
					Set<String> keys = qmap.keySet();
					for (String key : keys) {
						if ("fileNm".equals(key))  tmpName = qmap.get(key);
						else if ("type".equals(key)) tmpExt = qmap.get(key);
					}
				}
				
				// SHP(shp,dbf,prj,shx)일 때 파일명에 데이터 구분(중) 추가 
				if ("shp".equals(tmpExt) || "dbf".equals(tmpExt) || "prj".equals(tmpExt) || "shx".equals(tmpExt)) {
					filePath = tempPath + today + File.separator + "(" + dataCdList.get(j) + ")" +tmpName + "." + tmpExt;
				} else {
					filePath = tempPath + today + File.separator + tmpName + "." + tmpExt;
				}
				
				try {
					URL url = new URL(resultList.get(j));
					File file = new File(filePath);
					FileUtils.copyURLToFile(url, file);
					
					if (file.length() > 0) fileList.add(filePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				FileOutputStream fout = new FileOutputStream(zipFileName);
		        ZipOutputStream zout = new ZipOutputStream(fout);
		        for(int k=0; k < fileList.size(); k++){
		            ZipEntry zipEntry = new ZipEntry(new File(fileList.get(k)).getName());
		            zout.putNextEntry(zipEntry);
		            FileInputStream fin = new FileInputStream(fileList.get(k));
		            int length;
		            while((length = fin.read(buffer)) > 0){
		                zout.write(buffer, 0, length);
		            }
		            zout.closeEntry();
		            fin.close();
		        }
		        zout.close();
			}catch(Exception e){
		        e.printStackTrace();
		    }finally{
				File folder = new File(tempPath + today);
				try {
					while (folder.exists()) {
						File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

						for (int j = 0; j < folder_list.length; j++) {
							folder_list[j].delete(); // 파일 삭제
						}

						if (folder_list.length == 0 && folder.isDirectory()) {
							folder.delete(); // 대상폴더 삭제
						}
					}
				} catch (Exception e) {
					e.getStackTrace();
				}
		    }

			modelAndView.addObject("zipFileName", zipFileName);
			modelAndView.addObject("outFileName", outFileName);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/disaster/getDisasterCodeList.do")
	public ModelAndView getDisasterCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			sendMap.put("cdeKnd"		, "DTC_CDE");
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			List<Map<String, Object>> codeList = disasterService.getDisasterCodeList(sendMap);
			
			modelAndView.addObject("list", codeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/disaster/getDisasterRegion1List.do")
	public ModelAndView getDisasterRegion1List(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			List<Map<String, Object>> codeList = disasterService.getDisasterRegion1List(sendMap);
			
			modelAndView.addObject("list", codeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/disaster/getDisasterRegion2List.do")
	public ModelAndView getDisasterRegion2List(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			sendMap.put("bjcd"		, param.get("bjcd"));
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, sendMap);
			
			List<Map<String, Object>> codeList = disasterService.getDisasterRegion2List(sendMap);
			
			modelAndView.addObject("list", codeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
	/**
	 * 세션 이용자 정보 저장 
	 */
	private Map<String, Object> setUsrMgno(HttpServletRequest request, Map<String, Object> param) {
		
		String usrMgno = "";
		
		//공급시스템 요청
		if(param.containsKey("connUserNo")){
			usrMgno = (String)param.get("connUserNo");
		}
		//관리시스템 요청
		else{
			HttpSession session = request.getSession();
			usrMgno = (String)session.getAttribute("sUserMgno");
		}
		param.put("createUsr", usrMgno);
		param.put("lastChangeUsr", usrMgno);
		
		return param;
	}
}
