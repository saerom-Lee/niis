package org.nii.niis.smartMap.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.nii.niis.common.SqlidConst;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.smartMap.service.SmartMapService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
* @Project : niis 
* @Package : org.nii.niis.smartMap.web
* @FileName : SmartMapController.java 
* @Date : 2017. 08. 16.
* @description : 스마트 전자지도 관리시스템 연계
 * @author bokyeong
 */
@Controller
@RequestMapping(value="/smartMap/")
public class SmartMapController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	@Resource(name="smartMapService")
	private SmartMapService smartMapService;
	
	@Resource(name="txManager")
	protected DataSourceTransactionManager txManager;
	
	@RequestMapping(value="listSearch.do")
    public String listSearch(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "smartMap/zoneList";
	}
	
	/**
	 * 스마트 전자지도 관리시스템 연계 사업년도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 17.
	 */
	@RequestMapping(value="getYearList.do")
	public ModelAndView getYearList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.MAP_LISTSEARCH_CODE_YEAR;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getYearList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 스마트 전자지도 관리시스템 연계 사업종류 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 17.
	 */
	@RequestMapping(value="getProjectTypeList.do")
	public ModelAndView getZoneCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		/************************************** queryID 필수 **************************************/ 
		String queryId = SqlidConst.MAP_LISTSEARCH_CODE_ZONECODE;
		/************************************** queryID 필수 **************************************/
		
		try{
			Map<String, Object> jsonMap = connService.connNiimCode(queryId, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getZoneCodeList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 스마트 전자지도 관리시스템 연계 사업지구 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 17.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getZoneList.do")
	public ModelAndView getZoneList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/smartMap/getZoneList.do";
			
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.putAll(param);
			sendMap.remove("sYear");
			sendMap.remove("eYear");
			sendMap.remove("zoneType");
			sendMap.remove("zoneList");
			JSONArray jArray = new JSONArray();
			
			sendMap.put("sYear", param.get("sYear"));
			sendMap.put("eYear", param.get("eYear"));
			sendMap.put("zoneType", param.get("zoneType"));
			
			List<String> zoneList = smartMapService.getZoneList(sendMap);
			
			for(int i=0; i<zoneList.size(); i++){
				jArray.add(zoneList.get(i));
			}
			sendMap.put("zoneList", jArray);
			Map<String, Object> jsonMap = connService.connNiimToJson(action, sendMap, request, response, 60*1000, 60*1000);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getZoneList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 스마트 전자지도 관리시스템 연계 사업지구 상세조회
	 * @param model
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 17.
	 */
	@RequestMapping(value="zoneListDetail.do")
	public String zoneListDetail (Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/smartMap/zoneListDetail.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("zone", jsonMap.get("zone"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::zoneListDetail", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "smartMap/zoneListDetail";
	}
	
	/**
	 * 스마트 전자지도 관리시스템 연계 사업지구 메타데이터 전송
	 * @param param
	 * @param request
	 * @param response
	 * @param zoneCodes
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 17.
	 */
	@RequestMapping(value="metadataSend.do")
	public ModelAndView metadataSend (@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, @RequestParam(value="zoneCodes[]") List<String> zoneCodes) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			
			String action = "/smartMap/metadataSend.do";
			param.put("zoneCodes", zoneCodes);
			
			Map<String, Object> jsonMap = connService.connNiimTest(action, param, request, response);
			
			Map<String, List<Map<String, Object>>> metaMap = new HashMap<String, List<Map<String, Object>>>();
			
			metaMap = (Map<String, List<Map<String, Object>>>) jsonMap.get("metaMap");
			String insStatus = smartMapService.mataIns(metaMap);
			
			modelAndView.addObject("insStatus", insStatus);
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::metadataSend", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 스마트맵 전송대기 테이블로 인서트 (IMG_SUPPLY_SMART)
	 * @param param
	 * @param request
	 * @param response
	 * @param zoneCodes
	 * @return
	 * @throws Exception
	 * 2017. 11. 6.
	 */
	@RequestMapping(value="uploadFile.do")
	public ModelAndView uploadFile (@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, @RequestParam(value="zoneCodes[]") List<String> zoneCodes) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		modelAndView.setViewName("/main/upload");
		try{
			
			String action = "/smartMap/uploadFile.do";
			param.put("zoneCodes", zoneCodes);
			param.put("stoDrv", smartMapService.getStoDrv((String)param.get("stoIdn")));
			
			Map<String, Object> jsonMap = connService.connNiimTest(action, param, request, response);
			
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::uploadFile", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="getStoInfoPop.do")
	public String getStoInfoPop (@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "smartMap/stoInfoPop";
	}
	
	/**
	 * 드라이브 리스트 획득
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 2017. 11. 9.
	 */
	@RequestMapping(value="getStoInfo.do")
	public ModelAndView getStoInfo (@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			List<Map<String, Object>> stoInfoList = smartMapService.getStoInfo();
			
			modelAndView.addObject("list", stoInfoList);
		}catch(Exception e){
			log.error(this.getClass() + "::::getStoInfo", e);
		}
		return modelAndView;
	}
	
	/**
	 * sto테이블 등록
	 * @param param
	 * @param request
	 * @param response
	 * @param zoneCodes
	 * @return
	 * @throws Exception
	 * 2017. 11. 9.
	 */
	@RequestMapping(value="stoLocIns.do")
	public ModelAndView stoLocIns (@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, @RequestParam(value="zoneCodes[]") List<String> zoneCodes) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String action = "/smartMap/stoLocIns.do";
		param.put("zoneCodes", zoneCodes);
		
		Map<String, Object> jsonMap = connService.connNiimTest(action, param, request, response);
		List<Map<String, Object>> ortList = (List<Map<String, Object>>) jsonMap.get("ortList");
		List<Map<String, Object>> airList = (List<Map<String, Object>>) jsonMap.get("airList");
		
		if (airList.size()>0){
			String stoIdn = (String)param.get("stoIdn");
			String stoDrv = smartMapService.getStoDrv(stoIdn);
			
			// 트랜젝션 설정
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		    TransactionStatus txStatus = txManager.getTransaction(def);
		    try{
				for(int i=0; i<airList.size(); i++){
					Map<String, Object> insMap = new HashMap<String, Object>();
					Map<String, Object> insMapLss = new HashMap<String, Object>();
					String zonceCode = (String)airList.get(i).get("zoneCode");
					//nix 무손실
					insMap.put("zoneCode", zonceCode);
					insMap.put("phCourse", airList.get(i).get("phCourse"));
					insMap.put("phNum", airList.get(i).get("phNum"));
					insMap.put("zoneCde", zonceCode.substring(zonceCode.length()-4, zonceCode.length()));
					insMap.put("fileFormat", "NIX");
					insMap.put("fileSize", airList.get(i).get("fileSize")); // fileSize 파일 업로드 후 업데이트?
					insMap.put("stoIdn", stoIdn);
					insMap.put("fileExt", "O");
					insMap.put("zoneYy", airList.get(i).get("zoneYy"));
					insMap.put("fileNam", airList.get(i).get("fileNam"));
					insMap.put("stoDrv", stoDrv);
					insMap.put("storageCde", "AIR001");
					smartMapService.insertAirLoc(insMap);
					
					//nix 손실
					insMapLss.put("zoneCode", zonceCode);
					insMapLss.put("phCourse", airList.get(i).get("phCourse"));
					insMapLss.put("phNum", airList.get(i).get("phNum"));
					insMapLss.put("zoneCde", zonceCode.substring(zonceCode.length()-4, zonceCode.length()));
					insMapLss.put("fileFormat", "NIX");
					insMapLss.put("fileSize", airList.get(i).get("fileSize")); // fileSize 파일 업로드 후 업데이트?
					insMapLss.put("stoIdn", stoIdn);
					insMapLss.put("fileExt", "O");
					insMapLss.put("zoneYy", airList.get(i).get("zoneYy"));
					insMapLss.put("fileNam", airList.get(i).get("fileNam")+"s");
					insMapLss.put("stoDrv", stoDrv);
					insMapLss.put("storageCde", "AIR002"); //nix 무손실
										
					smartMapService.insertAirLoc(insMapLss);
				}
				txManager.commit(txStatus);
			}catch(Exception e){
				txManager.rollback(txStatus);
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		if (ortList.size()>0){
			String stoIdn = (String)param.get("stoIdn");
			String stoDrv = smartMapService.getStoDrv(stoIdn);
			
			// 트랜젝션 설정
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		    TransactionStatus txStatus = txManager.getTransaction(def);
		    try{
				for(int i=0; i<ortList.size(); i++){
					String map5000Num = (String)ortList.get(i).get("map5000Num");
					
					Map<String, Object> insMap = new HashMap<String, Object>();
					String zonceCode = (String)ortList.get(i).get("zoneCode");
					insMap.put("zoneCode", zonceCode);
					insMap.put("zoneCde", zonceCode.substring(zonceCode.length()-4, zonceCode.length()));
					insMap.put("map5000Num", map5000Num);
					insMap.put("fileFormat", ortList.get(i).get("fileFormat"));
					insMap.put("fileSize", ortList.get(i).get("fileSize")); // fileSize 파일 업로드 후 업데이트?
					insMap.put("stoIdn", stoIdn);
					insMap.put("mapNum", map5000Num.substring(0, 5));
					insMap.put("fileExt", "O");
					insMap.put("zoneYy", ortList.get(i).get("zoneYy"));
					insMap.put("fileNam", ortList.get(i).get("fileNam"));
					insMap.put("stoDrv", stoDrv);
					
					String gtypDst = null;
					String storageCde = null;
					if(ortList.get(i).get("gtypDst").equals("51CM")){
						gtypDst = "ORT_DATA";
						storageCde = "ORT005"; // 51cm tiff(손실)				
					}else if(ortList.get(i).get("gtypDst").equals("25CM")){
						gtypDst = "ORT_FILE";
						storageCde = "ORT004"; // 25cm tiff(원본)
					}

					insMap.put("gtypDst", gtypDst);
					insMap.put("storageCde", storageCde);

					smartMapService.insertOrtLoc(insMap);
					if(ortList.get(i).get("gtypDst").equals("51CM")){
						//손실 nix
						
						Map<String, Object> insNixMap = new HashMap<String, Object>();
						
						insNixMap.put("zoneCode", zonceCode);
						insNixMap.put("zoneCde", zonceCode.substring(zonceCode.length()-4, zonceCode.length()));
						insNixMap.put("map5000Num", map5000Num);
						insNixMap.put("fileFormat", "NIX");
						insNixMap.put("fileSize", ortList.get(i).get("fileSize")); // fileSize 파일 업로드 후 업데이트?
						insNixMap.put("stoIdn", stoIdn);
						insNixMap.put("mapNum", map5000Num.substring(0, 5));
						insNixMap.put("fileExt", "O");
						insNixMap.put("zoneYy", ortList.get(i).get("zoneYy"));
						insNixMap.put("fileNam", ortList.get(i).get("fileNam")+"s");
						insNixMap.put("stoDrv", stoDrv);
						insNixMap.put("storageCde", "ORT003");
						insNixMap.put("gtypDst", gtypDst);
						smartMapService.insertOrtLoc(insNixMap);
					}
				}
				txManager.commit(txStatus);
			}catch(Exception e){
				txManager.rollback(txStatus);
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return modelAndView;
	}
}


