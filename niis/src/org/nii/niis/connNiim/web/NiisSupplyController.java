package org.nii.niis.connNiim.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.connNiim.service.NiisSupplyService;
import org.nii.niis.fileDownlaod.service.FileDownloadService;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.service.SchedulerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
@RequestMapping(value="/niim")
public class NiisSupplyController {

	@Resource(name="propertiesService")
	private EgovPropertyService propertyService;
	
	@Resource(name="applyService")
	private ApplyService applyService;
	
	@Resource(name="niisSupplyService")
	private NiisSupplyService niisSupplyService;
	
	@Resource(name="schedulerService")
	private SchedulerService schedulerService;
	
	@Resource(name="fileDownloadService")
	private FileDownloadService fileDownloadService;
	
	@RequestMapping(value="/supply/getDownloadList.do")
	public ModelAndView getDownloadList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("supIdn", param.get("supIdn"));
			
			
			/** 신청서 승인 페이지에서 다운로드 버튼 클릭시 USR_IMG_SUPPLY_APP_NIIM 테이블에서
				APPROVAL_CDE = '50'으로 UPDATE 해주기
				이때 남은일시 : 확인가능, 상태 : '확인' 이됨 
			 */
			niisSupplyService.updateApprvalCde(sendMap);
			
			
			List<Map<String, Object>> metaList = new ArrayList<Map<String, Object>>();
			
			
			
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyAirImgLimit"));
			List<Map<String, Object>> downloadAirList = niisSupplyService.getDownloadAirList(sendMap);
			
						
			List<Map<String, Object>> downloadAirLibList = niisSupplyService.getDownloadAirLibList(sendMap);
			
						
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyDemImgLimit"));
			List<Map<String, Object>> downloadDemList = niisSupplyService.getDownloadDemList(sendMap);
			
						
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyOrtImgLimit"));
			List<Map<String, Object>> downloadOrtList = niisSupplyService.getDownloadOrtList(sendMap);
						
			
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyAtImgLimit"));
			List<Map<String, Object>> downloadAtList = niisSupplyService.getDownloadAtList(sendMap);
			
			
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyMapImgLimit"));
			List<Map<String, Object>> downloadMapList = niisSupplyService.getDownloadMapList(sendMap);
			
			
			if((null != downloadAirList && downloadAirList.size() > 0 && "Y".equals(downloadAirList.get(0).get("metaYn"))) || (null != downloadDemList && downloadDemList.size() > 0 && "Y".equals(downloadDemList.get(0).get("metaYn"))) || (null != downloadOrtList && downloadOrtList.size() > 0 && "Y".equals(downloadOrtList.get(0).get("metaYn")))){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("imageCde"		, "");
				map.put("supIdn"		, param.get("supIdn"));
				map.put("zoneNam"		, "메타데이터");
				map.put("groupNum"		, "meta");
				map.put("cnt"			, 0);
				map.put("downloadApp"	, "Y");
				
				metaList.add(0, map);
			}
			
			modelAndView.addObject("airList", downloadAirList);
			modelAndView.addObject("airLibList", downloadAirLibList);
			modelAndView.addObject("demList", downloadDemList);
			modelAndView.addObject("ortList", downloadOrtList);
			modelAndView.addObject("atList", downloadAtList);
			modelAndView.addObject("mapList", downloadMapList);
			modelAndView.addObject("metaList", metaList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 수치지형도 표준메타데이터 다운로드 리스트
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/supply/downloadMapMetaList.do")
	public ModelAndView downloadMapMetaList(@RequestParam HashMap<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			
			List<EgovMap> getMapMetaList = fileDownloadService.getMapMetaList(param);
			
			modelAndView.addObject("getMapMetaList", getMapMetaList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/supply/getFileList.do")
	public ModelAndView getFileList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
		
		try{
			String imageCde = (String)param.get("imageCde");
			String group = (String)param.get("group");
			
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("supIdn"	, param.get("supIdn"));
			sendMap.put("urlInfo"	, param.get("urlInfo"));
			sendMap.put("imageCde"	, param.get("imageCde"));
			sendMap.put("group"		, param.get("group"));
			sendMap.put("supplyImgPath", propertyService.getString("Globals.uploadedImgPath"));
			
			//메타데이터 다운로드
			if("meta".equals(group)){
				
				sendMap.put("airMetaPath", propertyService.getString("Globals.airMetaPath"));
				sendMap.put("airLibMetaPath", propertyService.getString("Globals.airLibMetaPath"));
				sendMap.put("demMetaPath", propertyService.getString("Globals.demMetaPath"));
				sendMap.put("ortMetaPath", propertyService.getString("Globals.ortMetaPath"));
				
				fileList = niisSupplyService.getMetaFileList(sendMap);
			
			//항공사진 다운로드
			}else if(imageCde.equals(propertyService.getString("Globals.airImageCode"))){
				
				sendMap.put("airImageFormat", propertyService.getString("Globals.airImageFormat"));
				sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyAirImgLimit"));
				
				fileList = niisSupplyService.getAirFileList(sendMap);
				
			//항공사진(해방전후) 다운로드
			}else if(imageCde.equals(propertyService.getString("Globals.airLibImageCode"))){
				
				sendMap.put("airImageFormat", propertyService.getString("Globals.airImageFormat"));
				sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyAirImgLimit"));
				
				fileList = niisSupplyService.getAirLibFileList(sendMap);
				
			//수치표고 다운로드
			}else if(imageCde.equals(propertyService.getString("Globals.demImageCode"))){
				
				sendMap.put("demImageFormat", propertyService.getString("Globals.demImageFormat"));
				sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyDemImgLimit"));
				
				fileList = niisSupplyService.getDemFileList(sendMap);
				
			//정사영상 다운로드
			}else if(imageCde.equals(propertyService.getString("Globals.ortImageCode"))){
				
				sendMap.put("ortImageFormat", propertyService.getString("Globals.ortImageFormat"));
				sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyOrtImgLimit"));
				
				fileList = niisSupplyService.getOrtFileList(sendMap);
			//AT 다운로드
			}else if(imageCde.equals(propertyService.getString("Globals.atImageCode"))){
				
				//sendMap.put("ortImageFormat", propertyService.getString("Globals.ortImageFormat"));
				//sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyOrtImgLimit"));
				
				//fileList = niisSupplyService.getATFileList(sendMap);
				System.out.println("================= AT");
			//수치지형도 다운로드
			}else if(imageCde.equals(propertyService.getString("Globals.mapImageCode"))){
				
				//sendMap.put("ortImageFormat", propertyService.getString("Globals.ortImageFormat"));
				//sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyOrtImgLimit"));
				
				//fileList = niisSupplyService.getOrtFileList(sendMap);
				System.out.println("===================== MAP");
			}
			
			modelAndView.addObject("list", fileList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/supply/downloadComplete.do")
	public ModelAndView downloadComplete(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("supIdn", param.get("supIdn"));
			sendMap.put("imageCde", param.get("imageCde"));
			sendMap.put("group", param.get("group"));
			
			sendMap.put("approvalCde", "4");
			sendMap.put("lastChangeUsr", param.get("connUserNo"));
			
			niisSupplyService.downloadComplete(sendMap);	//그룹별
			//applyService.uptDbAppAuth(sendMap);	//전체
			
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/supply/getExpiredApllyList.do")
	public ModelAndView getExpiredApllyList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("fileLimitDay", propertyService.getInt("Globals.fileKeepDay"));
			
			//승인 후 기간 만료 신청서 조회
			List<Map<String, Object>> expiredFileList = schedulerService.getExpiredFileList(sendMap);
			
			modelAndView.addObject("expiredFileList", expiredFileList);
			modelAndView.addObject("supplyImgPath", propertyService.getString("Globals.uploadedImgPath"));	//���ε� ���� ���� ���
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
}
