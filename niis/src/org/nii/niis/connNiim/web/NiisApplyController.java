package org.nii.niis.connNiim.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.connNiim.service.NiisApplyService;
import org.nii.niis.connNiim.service.NiisCommonService;
import org.nii.niis.connNiim.service.NiisSearchService;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.service.LoginService;
import org.nii.niis.niim.service.ManagementService;
import org.nii.niis.niim.service.ParamVO;
import org.nii.niis.niim.service.SearchService;
import org.nii.niis.niim.service.UserVO;
import org.nii.niis.niim.util.BlackList;
import org.nii.niis.niim.util.JSONUtil;
import org.nii.niis.niim.util.PagingUtil;
import org.nii.niis.niim.util.PolicyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import CKNB.WatermarkComponent;
import egovframework.rte.fdl.property.EgovPropertyService;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;


@Controller
@RequestMapping(value="/niim")
public class NiisApplyController {

	@Resource(name="propertiesService")
	private EgovPropertyService propertyService;
	
	@Resource(name="applyService")
	private ApplyService applyService;
	
	@Resource(name="niimLoginService")
	private LoginService loginService;
	
	@Resource(name="managementService")
	private ManagementService managementService;
	
	@Resource(name="niisApplyService")
	private NiisApplyService niisApplyService;
	
	@Resource(name="niisCommonService")
	private NiisCommonService niisCommonService;
	
	@Resource(name="niisSearchService")
	private NiisSearchService niisSearchService;
	
	@Resource(name="niimSearchService")
	private SearchService searchService;
	
	@Resource(name="policyUtil")
	private PolicyUtil policyUtil;
	
	@Autowired
	private WatermarkComponent watermartComponent;
	
	/**
	 * 신청서 리스트 조회
	 * @param request
	 * @param response
	 * @param param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/apply/getApplyList.do")
	public String getApplyList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/apply/dbAppList.do";
	}
	
	/**
	 * 신청자 정보 조회
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/apply/getApplyInfo.do")
	public ModelAndView getApplyInfo(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			modelAndView.addObject("userinfo", this.getUserInfo((String)param.get("connUserId")));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/getPurposeList.do")
	public ModelAndView getPurposeList(@RequestParam Map<String, Object> param) throws Exception{
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String codeId = (String) param.get("codeId");
		
		try {
			modelAndView.addObject("purposeList", niisApplyService.getPurposeList(codeId));
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/getAirProductCnt.do")
	public ModelAndView getAirProductCnt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("phCourse"		, param.get("phCourse"));
			sendMap.put("phNum"			, param.get("phNum"));
			sendMap.put("sYear"			, param.get("sYear"));
			sendMap.put("eYear"			, param.get("eYear"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("cameraCde"		, param.get("cameraCde"));
			sendMap.put("resolution"	, param.get("resolution"));
			//sendMap.put("fileExt"		, "O");
			//policyUtil.addPolicy(request, sendMap);
			
			modelAndView.addObject("cnt", niisSearchService.getAirImgDataListCnt(sendMap));
			
			//modelAndView.addObject("userinfo", this.getUserInfo((String)param.get("connUserId")));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/apply/getAirLibProductCnt.do")
	public ModelAndView getAirLidProductCnt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("phCourse"		, param.get("phCourse"));
			sendMap.put("phNum"			, param.get("phNum"));
			sendMap.put("sYear"			, param.get("sYear"));
			sendMap.put("eYear"			, param.get("eYear"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("cameraCde"		, param.get("cameraCde"));
			sendMap.put("resolution"	, param.get("resolution"));
			sendMap.put("fileExt"		, "O");
			System.out.println("param>>>>>>>>>>>" + param);
			//policyUtil.addPolicy(request, sendMap);
			
			modelAndView.addObject("cnt", niisSearchService.getAirLibImgDataListCnt(sendMap));
			System.out.println("=======airLib>>>>>>>  " + niisSearchService.getAirLibImgDataListCnt(sendMap));
			//modelAndView.addObject("userinfo", this.getUserInfo((String)param.get("connUserId")));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/getDemProductCnt.do")
	public ModelAndView getDemProductCnt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("map5000Num"	, param.get("map5000Num"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("gridInt"		, param.get("gridInt"));
			
			//policyUtil.addPolicy(request, sendMap);
			
			modelAndView.addObject("cnt", niisSearchService.getDemImgDataListCnt(sendMap));
			
			//modelAndView.addObject("userinfo", this.getUserInfo((String)param.get("connUserId")));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/getOrtProductCnt.do")
	public ModelAndView getOrtProductCnt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();

			sendMap.put("map5000Num"	, param.get("map5000Num"));
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("securityCde"	, param.get("securityCde"));
			sendMap.put("gtypDst"		, param.get("gtypDst"));
			
			//policyUtil.addPolicy(request, sendMap);
			
			modelAndView.addObject("cnt", niisSearchService.getOrtImgDataListCnt(sendMap));
			
			//modelAndView.addObject("userinfo", this.getUserInfo((String)param.get("connUserId")));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/getAtProductCnt.do")
	public ModelAndView getAtProductCnt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			//전체신청 팝업 (조회된 전체항목 신청)
			sendMap.put("sYear"			, param.get("sYear"));
			sendMap.put("eYear"			, param.get("eYear"));
			sendMap.put("prjCde"		, param.get("prjCde"));
			sendMap.put("zoneCode"		, param.get("zoneCode"));
			sendMap.put("pdtYn"			, param.get("pdtYn"));
			
			modelAndView.addObject("cnt", niisSearchService.getAtImgDataListCnt(sendMap));
			
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/apply/getMapProductCnt.do")
	public ModelAndView getMapProductCnt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			//전체신청 팝업 (지도성과 목록에서 radio박스로 선택된 지도성과이력 목록 전체)
			sendMap.put("mapSerNo"			, param.get("mapSerNo"));
			sendMap.put("mapShtNo"			, param.get("mapShtNo"));
			
			modelAndView.addObject("cnt", niisSearchService.getMapHisImgDataListCnt(sendMap));
			
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/regApply.do")
	public ModelAndView regApply(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			
			//신청서 작성
			Map<String, Object> resultMap = niisApplyService.regApply(sendMap);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> imgList = (List<Map<String, Object>>)sendMap.get("imgList");
			//메타데이터 생성
			if(sendMap.get("metaYn").equals("Y") && !imgList.get(0).get("imageCde").toString().equals("PDT008") && !imgList.get(0).get("imageCde").toString().equals("atCde")) {
				watermartComponent.metaExport(propertyService.getString("Globals.airImageCode"), resultMap);
				watermartComponent.metaExport(propertyService.getString("Globals.demImageCode"), resultMap);
				watermartComponent.metaExport(propertyService.getString("Globals.ortImageCode"), resultMap);
				
				File file = new File("D:/METADATA/"+resultMap.get("supIdn"));
				
				if(file.exists()) {
					ZipFile zipFile = new ZipFile("D:\\METADATA\\"+resultMap.get("supIdn")+".zip");
					ZipParameters parameters = new ZipParameters();
					parameters.setCompressionMethod(CompressionMethod.DEFLATE);
					parameters.setCompressionLevel(CompressionLevel.NORMAL);
					zipFile.addFolder(new File("D:\\METADATA\\"+resultMap.get("supIdn")), parameters);
				}
				
			}
			
			modelAndView.addObject("supIdn", resultMap.get("supIdn"));
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/waitApplyDelete.do")
	public ModelAndView waitApplyDelete(@RequestBody String body, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			//신청 요청 삭제
			applyService.delDbApp(sendMap);
			
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		
		
		return modelAndView;
	}
	
	
	@RequestMapping(value="/apply/regApplyAll.do")
	public ModelAndView regApplyAll(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			List<?> list = null;
			
			Map<String, Object> sendMap = new HashMap<String, Object>();
			String imageCde = (String)param.get("imageCde");
			
			//policyUtil.addPolicy(request, sendMap);
			
			if(imageCde.equals(propertyService.getString("Globals.airImageCode"))){
				
				sendMap.put("zoneCode"		, param.get("zoneCode"));
				sendMap.put("phCourse"		, param.get("phCourse"));
				sendMap.put("phNum"			, param.get("phNum"));
				sendMap.put("sYearAir"		, param.get("sYearAir"));
				sendMap.put("eYearAir"		, param.get("eYearAir"));
				sendMap.put("securityCde"	, param.get("securityCde"));
				sendMap.put("cameraCde"		, param.get("cameraCde"));
				sendMap.put("resolution"	, param.get("resolution"));
				sendMap.put("fileExt"		, "O");

				list = niisSearchService.getAirImgDataList(sendMap);
				
			}else if(imageCde.equals(propertyService.getString("Globals.airLibImageCode"))){
				
				sendMap.put("zoneCode"		, param.get("zoneCode"));
				sendMap.put("phCourse"		, param.get("phCourse"));
				sendMap.put("phNum"			, param.get("phNum"));
				//공급 수정
				sendMap.put("sYear"			, param.get("sYear"));
				sendMap.put("eYear"			, param.get("eYear"));
				//공급 수정
				sendMap.put("securityCde"	, param.get("securityCde"));
				sendMap.put("cameraCde"		, param.get("cameraCde"));
				sendMap.put("resolution"	, param.get("resolution"));
				sendMap.put("fileExt"		, "O");
				
				
				System.out.println("zoneCode>>>>" + param.get("zoneCode") +"," + param.get("phCourse") + "," + param.get("phNum"));
				
				list = niisSearchService.getAirLibImgDataList(sendMap);
				
			}else if(imageCde.equals(propertyService.getString("Globals.demImageCode"))){
				
				sendMap.put("zoneCode"		, param.get("zoneCode"));
				sendMap.put("map5000Num"	, param.get("map5000Num"));
				sendMap.put("securityCde"	, param.get("securityCde"));
				sendMap.put("gridInt"		, param.get("gridInt"));
				sendMap.put("fileExt"		, "O");
				
				list = niisSearchService.getDemImgDataList(sendMap);
				
			}else if(imageCde.equals(propertyService.getString("Globals.ortImageCode"))){
				
				sendMap.put("zoneCode"		, param.get("zoneCode"));
				sendMap.put("map5000Num"	, param.get("map5000Num"));
				sendMap.put("zoneCode"		, param.get("zoneCode"));
				sendMap.put("securityCde"	, param.get("securityCde"));
				sendMap.put("gtypDst"		, param.get("gtypDst"));
				sendMap.put("fileExt"		, "O");
				
				list = niisSearchService.getOrtImgDataList(sendMap);
			}else if(imageCde.equals("atCde")){
				
				sendMap.put("sYear"			, param.get("sYear"));
				sendMap.put("eYear"			, param.get("eYear"));
				sendMap.put("prjCde"		, param.get("prjCde"));
				sendMap.put("zoneCode"		, param.get("zoneCode"));
				sendMap.put("pdtYn"			, param.get("pdtYn"));
				list = niisSearchService.getAtImgDataList(sendMap);
			}
			
			sendMap = new HashMap<String, Object>();
			sendMap.putAll(param);
			sendMap.put("imgList", list);
			//신청서 작성
			Map<String, Object> resultMap = niisApplyService.regApplyAll(sendMap);
			
			modelAndView.addObject("supIdn", resultMap.get("supIdn"));
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();		
		}
		return modelAndView;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/apply/regApplyTree.do")
	public ModelAndView regApplyTree(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			
			//화면에서 로딩되지 않은 데이터 처리
			//기존 검색조건 세팅
			String radius 		= "";
			//String useAt 		= "";
			
//			if (sendMap.containsKey("radius")){
//				radius = (String)sendMap.get("radius");
//			}
			if (sendMap.containsKey("useAt")){
				//useAt = (String)sendMap.get("useAt");
			}
			
			// 중복제거
			List<Map<String, Object>> tempList = (List<Map<String, Object>>)sendMap.get("imgList");
			Set<Map<String, Object>> set = new HashSet<Map<String, Object>>(tempList);
			List<Map<String, Object>> imgList = new ArrayList<Map<String, Object>>(set);
			
			
			//기존 list 길이 선언
			int k = imgList.size();
			
			for(int i=0; i<k; i++){
				
				Map<String, Object> imgMap = imgList.get(i);
				
				String imageCde = (String)imgMap.get("imageCde");
				String keyVal1 	= (String)imgMap.get("keyVal1");
				String keyVal2 	= (String)imgMap.get("keyVal2");
				String keyVal3 	= (String)imgMap.get("keyVal3");
				String keyVal4 	= (String)imgMap.get("keyVal4");
				
				if("loadedYet".equals(keyVal3)){
					
					//policyUtil.addPolicy(request, sendMap);
					
					//이전 검색 조건 조회
					ParamVO vo = searchService.getBeforeSearchCondtion(sendMap);
					vo.setFileExt("O");
					
					Map<String, ArrayList<String>> nodeDataMap = searchService.getUnloadedData(vo, radius, imageCde, keyVal1, keyVal2, keyVal3);
					
					ArrayList<String> fileType = nodeDataMap.get("fileType");
					ArrayList<String> zoneCode = nodeDataMap.get("zoneCode");
					ArrayList<String> pathOne = nodeDataMap.get("pathOne");
					ArrayList<String> pathTwo = nodeDataMap.get("pathTwo");
					ArrayList<String> fileName = nodeDataMap.get("fileName");
					
					for(int j=0; j<fileType.size(); j++){
						
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("imageCde", fileType.get(j));
						map.put("keyVal1", zoneCode.get(j));
						map.put("keyVal2", pathOne.get(j));
						map.put("keyVal3", pathTwo.get(j));
						map.put("keyVal4", fileName.get(j));
						imgList.add(map);
					}
					
					imgList.remove(i--);
					k--;
				}
			}
			
			sendMap.put("imgList", imgList);
			
			//신청서 작성
			Map<String, Object> resultMap = niisApplyService.regApply(sendMap);
			
			
			//메타데이터 생성
			if(sendMap.get("metaYn").equals("Y")) {
				watermartComponent.metaExport(propertyService.getString("Globals.airImageCode"), resultMap);
				watermartComponent.metaExport(propertyService.getString("Globals.demImageCode"), resultMap);
				watermartComponent.metaExport(propertyService.getString("Globals.ortImageCode"), resultMap);
				
				File file = new File("D:/METADATA/"+resultMap.get("supIdn"));
				
				if(file.exists()) {
					ZipFile zipFile = new ZipFile("D:\\METADATA\\"+resultMap.get("supIdn")+".zip");
					ZipParameters parameters = new ZipParameters();
					parameters.setCompressionMethod(CompressionMethod.DEFLATE);
					parameters.setCompressionLevel(CompressionLevel.NORMAL);
					zipFile.addFolder(new File("D:\\METADATA\\"+resultMap.get("supIdn")), parameters);
				}
			}
			
			modelAndView.addObject("supIdn", resultMap.get("supIdn"));
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/apply/getUserApplyList.do")
	public ModelAndView getUserApplyList(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			sendMap.put("limitDay", propertyService.getInt("Globals.downloadLimitDay"));
			
			PagingUtil.getInstance().setPageData(sendMap, modelAndView, niisApplyService.getUserApplyListCnt(sendMap));
			
			//신청서 작성
			List<Map<String, Object>> resultlist = niisApplyService.getUserApplyList(sendMap);
			
			modelAndView.addObject("list", resultlist);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/apply/getApplyDetail.do")
	public String getApplyDetail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/apply/dbAppDetail.do";		// niimApplyController.java
	}
	
	
	@RequestMapping(value="/apply/getRejectCause.do")
	public ModelAndView getRejectCause(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> resultMap = (Map<String, Object>)applyService.getDbAppDetail(param);
			
			if("5".equals(resultMap.get("approvalCde"))){
				Map<String, Object> sendMap = new HashMap<String, Object>();
				sendMap.put("supIdn", param.get("supIdn"));
				sendMap.put("approvalCde", "6");
				sendMap.put("lastChangeUsr", param.get("connUserNo"));
				
				applyService.uptDbAppAuth(sendMap);
				modelAndView.addObject("appChange", "cfrm");
			}
			
			modelAndView.addObject("map", resultMap);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/apply/applyExtension.do")
	public ModelAndView applyExtension(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String state = "2";
			
			param.put("limitDay", propertyService.getInt("Globals.downloadLimitDay"));
			
			int cnt = niisApplyService.applyExtension(param);
			
			if(cnt == 1){
				state = "1";
			}
			
			modelAndView.addObject("state", state);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value="/apply/reRegApplyPop.do")
	public ModelAndView reRegApplyPop(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> applyDetail = applyService.getDbAppDetail(param);
			
			if(applyDetail.containsKey("secDataMngPlan") && null != applyDetail.get("secDataMngPlan")){
				applyDetail.put("secDataMngPlanTa", BlackList.getXssCnvrStr((String)applyDetail.get("secDataMngPlan")));
			}
			modelAndView.addObject("apply", applyDetail);
			modelAndView.addObject("userinfo", this.getUserInfo((String)param.get("connUserId")));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/apply/reRegApply.do")
	public ModelAndView reRegApply(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> resultMap = niisApplyService.reRegApply(param);
			
			modelAndView.addObject("supIdn", param.get("supIdn"));
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	private Map<String, Object> getUserInfo(String id) throws Exception {
		
		UserVO userVO = loginService.getLoginCheck(id, null).get(0);
		
		Map<String, Object> userinfo = new HashMap<String, Object>();
		userinfo.put("apPost", userVO.getPost());
		userinfo.put("apDepartment", userVO.getDepartment());
		userinfo.put("apPosition", userVO.getPosition());
		userinfo.put("apName", userVO.getName());
		userinfo.put("apTel", userVO.getUsrTel());
		
		return userinfo;
	}
	
	/*
	private Map<String, Object> getCodeList() throws Exception {
		Map<String, Object> codeMap = new HashMap<String, Object>();
		codeMap.put("queryID", "mainDAO.getCommonCode");
		
		codeMap.put("cdeCde", "ORGN_CDE");
		codeMap.put("orgnCde", niisCommonService.getCommonCode(codeMap));
		
		codeMap.put("cdeCde", "PURPOSE_CDE");
		codeMap.put("purposeCde", niisCommonService.getCommonCode(codeMap));

		return codeMap;
	}
	*/
}
