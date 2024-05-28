package org.nii.niis.niim.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nii.niis.connNiim.service.NiisSmartMapService;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.util.BlackList;
import org.nii.niis.niim.util.PagingUtil;
import org.nii.niis.niim.util.PolicyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class niimApplyController {
	
	@Resource(name="applyService")
	private ApplyService applyService;
	
	@Resource(name="propertiesService")
    private EgovPropertyService propertyService;
	
/*	@Resource(name="watermarkService")
	private WatermarkService watermarkService;*/
	
	@Resource(name="policyUtil")
    private PolicyUtil policyUtil;
	
	@Resource(name="niisSmartMapService")
	private NiisSmartMapService niisSmartMapService;

	/**
	 * 원본DB 신청서 관리 리스트
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppList.do")
	public ModelAndView getDbAppList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(param.containsKey("srchApName")){
			param.put("srchApName", BlackList.getStrCnvrXss((String)param.get("srchApName")));
		}
		if(param.containsKey("srchConName")){
			param.put("srchConName", BlackList.getStrCnvrXss((String)param.get("srchConName")));
		}
		
		//int rowcount = applyService.getDbAppListCnt(param);
		PagingUtil.getInstance().setPageData(param, modelAndView, applyService.getDbAppListCnt(param));
		
		List<?> dbAppList = null;
		dbAppList = applyService.getDbAppList(param);
		
		modelAndView.addObject("list", dbAppList);
		return modelAndView;
	}
	
	
	/**
	 * 원본DB 신청서 관리 상세
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppDetail.do")
	public ModelAndView getDbAppDetail(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		
		Map<String, Object> dbAppDetail = null;
		dbAppDetail = applyService.getDbAppDetail(param);
		
		if(dbAppDetail.containsKey("secDataMngPlan") && null != dbAppDetail.get("secDataMngPlan")){
			dbAppDetail.put("secDataMngPlanTa", BlackList.getXssCnvrStr((String)dbAppDetail.get("secDataMngPlan")));
		}
		
		// 신청정보 리스트
		// 25건 초과시 전체신청으로 인지하고 상세정보 리스트 조회 x
		if(		Integer.parseInt(param.get("atCnt").toString())  <= 25 
			&&	Integer.parseInt(param.get("airCnt").toString()) <= 25 
			&&	Integer.parseInt(param.get("demCnt").toString()) <= 25 
			&&	Integer.parseInt(param.get("ortCnt").toString()) <= 25 
			&&	Integer.parseInt(param.get("mapCnt").toString()) <= 25 ){
			
			List<Map<String, Object>> applyDetailList = applyService.getApplyDetailList(param);
			modelAndView.addObject("applyDetailList", applyDetailList);
		}
		
		modelAndView.addObject("map", dbAppDetail);
		modelAndView.addObject("today", today);
		return modelAndView;
	}
	
	
	/**
	 * 원본DB 신청서 관리 상세 발급내역
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppReqList.do")
	public ModelAndView getDbAppReqList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<?> dbAppReqList = null;
		try{
		String selDbAppGbn = (String)param.get("selDbAppGbn");
		
		if("".equals(selDbAppGbn) || "air".equals(selDbAppGbn)){
			param.put("airGbn", "air");
		}
		if("".equals(selDbAppGbn) || "ort".equals(selDbAppGbn)){
			param.put("ortGbn", "ort");
		}
		if("".equals(selDbAppGbn) || "dem".equals(selDbAppGbn)){
			param.put("demGbn", "dem");
		}
		if("".equals(selDbAppGbn) || "lid".equals(selDbAppGbn)){
			param.put("lidGbn", "lid");
		}
		if("".equals(selDbAppGbn) || "nir".equals(selDbAppGbn)){
			param.put("nirGbn", "nir");
		}
		if("".equals(selDbAppGbn) || "tds".equals(selDbAppGbn)){
			param.put("tdsGbn", "tds");
		}
		
		dbAppReqList = applyService.getDbAppReqList(param);
		
		modelAndView.addObject("list", dbAppReqList);}catch(Exception e){e.printStackTrace();}
		return modelAndView;
	}
	
	
	/**
	 * 원본DB 신청서 관리 상세 결과보고서
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppRstReview.do")
	public ModelAndView getDbAppRstReview(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<?> dbAppRstReview = null;
		dbAppRstReview = applyService.getDbAppRstReview(param);
		
		modelAndView.addObject("list", dbAppRstReview);
		return modelAndView;
	}
	
	
	/**
	 * 원본DB 신청서 등록
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppReg.do")
	public ModelAndView regDbApp(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		
		sendMap.put("reqDate"			, param.get("reqDate"));
		sendMap.put("supDate"			, param.get("supDate"));
		sendMap.put("apPost"			, BlackList.getStrCnvrXss((String)param.get("apPost")));
		sendMap.put("apDepartment"		, BlackList.getStrCnvrXss((String)param.get("apDepartment")));
		sendMap.put("mgrPosition"		, BlackList.getStrCnvrXss((String)param.get("mgrPosition")));
		sendMap.put("mgrName"			, BlackList.getStrCnvrXss((String)param.get("mgrName")));
		sendMap.put("mgrTel"			, param.get("mgrTel"));
		sendMap.put("apPosition"		, BlackList.getStrCnvrXss((String)param.get("apPosition")));
		sendMap.put("apName"			, BlackList.getStrCnvrXss((String)param.get("apName")));
		sendMap.put("apTel"				, param.get("apTel"));
		sendMap.put("purpose"			, BlackList.getStrCnvrXss((String)param.get("purpose")));
		sendMap.put("secDataMngPlan"	, BlackList.getStrCnvrXss((String)param.get("secDataMngPlan")));
		sendMap.put("subcontName"		, BlackList.getStrCnvrXss((String)param.get("subcontName")));
		sendMap.put("subcontMgrName"	, BlackList.getStrCnvrXss((String)param.get("subcontMgrName")));
		sendMap.put("subcontMgrTel"		, param.get("subcontMgrTel"));
		sendMap.put("subcontSrName"		, BlackList.getStrCnvrXss((String)param.get("subcontSrName")));
		sendMap.put("subcontSrTel"		, param.get("subcontSrTel"));
		sendMap.put("createUsr"			, request.getSession().getAttribute("sUserMgno"));
		
		if (sendMap != null) {
			Iterator<String> iter = sendMap.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if ("".equals(sendMap.get(key)) || "-".equals(sendMap.get(key))) {
					sendMap.put(key, null);
				}
			}
		}		
		
		String dbAppRst = applyService.regDbApp(sendMap);
		modelAndView.addObject("list", dbAppRst);
		
		return modelAndView;
	}
	
	/**
	 * 원본DB 신청서 수정
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppUpt.do")
	public ModelAndView uptDbApp(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		
		sendMap.put("supIdn"			, param.get("supIdn"));
		sendMap.put("reqDate"			, param.get("reqDate"));
		sendMap.put("supDate"			, param.get("supDate"));
		sendMap.put("apPost"			, BlackList.getStrCnvrXss((String)param.get("apPost")));
		sendMap.put("apDepartment"		, BlackList.getStrCnvrXss((String)param.get("apDepartment")));
		sendMap.put("mgrPosition"		, BlackList.getStrCnvrXss((String)param.get("mgrPosition")));
		sendMap.put("mgrName"			, BlackList.getStrCnvrXss((String)param.get("mgrName")));
		sendMap.put("mgrTel"			, param.get("mgrTel"));
		sendMap.put("apPosition"		, BlackList.getStrCnvrXss((String)param.get("apPosition")));
		sendMap.put("apName"			, BlackList.getStrCnvrXss((String)param.get("apName")));
		sendMap.put("apTel"				, param.get("apTel"));
		sendMap.put("purpose"			, BlackList.getStrCnvrXss((String)param.get("purpose")));
		sendMap.put("secDataMngPlan"	, BlackList.getStrCnvrXss((String)param.get("secDataMngPlan")));
		sendMap.put("subcontName"		, BlackList.getStrCnvrXss((String)param.get("subcontName")));
		sendMap.put("subcontMgrName"	, BlackList.getStrCnvrXss((String)param.get("subcontMgrName")));
		sendMap.put("subcontMgrTel"		, param.get("subcontMgrTel"));
		sendMap.put("subcontSrName"		, BlackList.getStrCnvrXss((String)param.get("subcontSrName")));
		sendMap.put("subcontSrTel"		, param.get("subcontSrTel"));
		sendMap.put("lastChangeUsr"		, request.getSession().getAttribute("sUserMgno"));
		
		if (sendMap != null) {
			Iterator<String> iter = sendMap.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if ("".equals(sendMap.get(key)) || "-".equals(sendMap.get(key))) {
					sendMap.put(key, null);
				}
			}
		}		
		
		int dbAppRst = applyService.uptDbApp(sendMap);
		
		modelAndView.addObject("list", dbAppRst);
		return modelAndView;
	}
	
	/**
	 * 원본DB 신청서 삭제
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppDel.do")
	public ModelAndView delDbApp(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String[] dbAppList = request.getParameterValues("dbAppList[]");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		
		if(dbAppList != null && dbAppList.length > 0){
			
			for(int i=0; i<dbAppList.length; i++){
				
				sendMap.put("supIdn"	, dbAppList[i]);
				applyService.delDbApp(sendMap);
			}
		}
		return modelAndView;
	}
	
	
	/**
	 * 원본DB 신청서 관리 엑셀출력
	 * @param model
	 * @param param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppExcel.do")
	public String getDbAppExcel(Model model, @RequestParam Map<String, Object> param) throws Exception {
		
		param.put("srchApName", BlackList.getStrCnvrXss((String)param.get("srchApName")));
		param.put("srchConName", BlackList.getStrCnvrXss((String)param.get("srchConName")));
		
		model.addAttribute("fileName", "원본DB신청내역");
		model.addAttribute("name", "원본DB신청내역");
		model.addAttribute("excelList", applyService.getDbAppList(param));
		model.addAttribute("colNames", getDbAppCols());
		return "excelView";
	}
	
	/**
	 * 원본DB 신청서 관리 상세 발급내역 엑셀출력
	 * @param model
	 * @param param
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppReqExcel.do")
	public String getDbAppReqExcel(Model model, @RequestParam Map<String, Object> param) throws Exception {
		
		String selDbAppGbn = (String)param.get("selDbAppGbn");
		
		if("".equals(selDbAppGbn) || "air".equals(selDbAppGbn)){
			param.put("airGbn", "air");
		}
		if("".equals(selDbAppGbn) || "ort".equals(selDbAppGbn)){
			param.put("ortGbn", "ort");
		}
		if("".equals(selDbAppGbn) || "dem".equals(selDbAppGbn)){
			param.put("demGbn", "dem");
		}
		if("".equals(selDbAppGbn) || "lid".equals(selDbAppGbn)){
			param.put("lidGbn", "lid");
		}
		if("".equals(selDbAppGbn) || "nir".equals(selDbAppGbn)){
			param.put("nirGbn", "nir");
		}
		if("".equals(selDbAppGbn) || "tds".equals(selDbAppGbn)){
			param.put("tdsGbn", "tds");
		}
		
		model.addAttribute("fileName", "원본DB발급내역");
		model.addAttribute("name", "원본DB발급내역");
		model.addAttribute("excelList", applyService.getDbAppReqList(param));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getDbAppReqCols());
		
		return "excelView";
	}
	
	
	/**
	 * 원본DB 공급시스템 신청내역 조회
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/apply/dbAppAuthList.do")
	public ModelAndView dbAppAuthList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<?> dbAppApprovalList = null;
		dbAppApprovalList = applyService.getDbAppAuthList(param);
		
		modelAndView.addObject("list", dbAppApprovalList);
		return modelAndView;
	}
	
	
	/**
	 * 원본DB 공급시스템 승인상태 변경
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/apply/uptDbAppAuth.do")
	public ModelAndView uptDbAppAuth(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		String approvalCde = (String)param.get("approvalCde");
		String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		
		if(null == param.get("supIdn") || "".equals(param.get("supIdn"))){
			throw new Exception("SUP_IDN is not defined!!");
		}
		
		param.put("lastChangeUsr", session.getAttribute("sUserMgno"));
		param.put("supDate", today);
		param.put("conName", session.getAttribute("sUserNm"));
		
		//승인
		if("1".equals(approvalCde)){
			
			policyUtil.addPolicy(request, param);
			
			param.put("limitDay", propertyService.getInt("Globals.downloadLimitDay"));
			Map<String, Object> returnMap = applyService.approvalDbAppAuth(param);
			
			String userPath = propertyService.getString("Globals.supplyImgPath") + "/" + returnMap.get("supIdn");
			
			returnMap.put("lastChangeUsr", session.getAttribute("sUserMgno"));
			returnMap.put("userPath", userPath);
			returnMap.put("CKNBLibraryPath", propertyService.getString("Globals.CKNBLibraryPth"));
			
			watermarkService.supplyImgDownload(returnMap);
			
			modelAndView.addObject("supIdn"		, returnMap.get("supIdn"));
			//modelAndView.addObject("fileType"	, returnMap.get("fileType"));
			//modelAndView.addObject("filePath"	, returnMap.get("filePath"));
			//modelAndView.addObject("markIndex"	, returnMap.get("markIndex"));
			//modelAndView.addObject("metaPath"	, returnMap.get("metaPath"));
		}
		//반려
		else if("5".equals(approvalCde)){
			applyService.rejectDbAppAuth(param);
		}
		
		return modelAndView;
	}*/
	
	
	/**
	 * 원본DB 신청서 관리 엑셀 컬럼매핑
	 * @return List
	 * @throws Exception
	 */
	public List<String> getDbAppCols() throws Exception {
		List<String> mapper = new ArrayList<String>();
		
		mapper.add("systemNam::시스템구분");
		mapper.add("reqDate::신청일자");
		mapper.add("apPost::소속");
		mapper.add("apDepartment::부서");
		mapper.add("apPosition::직급");
		mapper.add("apName::신청자");
		mapper.add("mgrName::책임부서장");
		mapper.add("subcontName::외부용역");
		mapper.add("compDate::처리일자");
		mapper.add("compName::처리자");
		mapper.add("approvalNam::승인여부");

		return mapper;
	}
	
	/**
	 * 원본DB 신청서 관리 상세 발급내역 엑셀 컬럼매핑
	 * @return List
	 * @throws Exception
	 */
	public List<String> getDbAppReqCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("supIdn::신청관리번호");
		mapper.add("imageCde::성과관리코드");
		mapper.add("imageNam::성과관리명");
		mapper.add("itemId::신청내역일련번호");
		mapper.add("prjYy::사업년도");
		mapper.add("zoneCode::사업지구코드");
		mapper.add("zoneNam::사업지구명");
		mapper.add("phCourse::코드번호");
		mapper.add("phNum::사진번호");
		//mapper.add("phCount::수량");
		//mapper.add("phCost::금액");
		mapper.add("prodCde::성과코드");
		mapper.add("prodNam::성과종류");
		mapper.add("map5000Num::5000도엽번호");
		mapper.add("map5000Nam::5000도엽번명");
		mapper.add("markIndex::워터마크 인덱스");

		return mapper;
	}
	
	/*
	 * 엑셀병합 예제
	public List<String> getDbAppReqHead() throws Exception {
		List<String> mapper = new ArrayList<String>();

		//병합할 셀의 크기를 지정할 것
		mapper.add("신청관리번호::1::2");
		mapper.add("성과::3");
		mapper.add("사업::5");
		mapper.add("기타::6::1");

		return mapper;
	}
	*/
	
	
/*	@Resource(name="mainService")
    private MainService mainService;
	
	@RequestMapping(value = "/apply/managementImgDownloadTest.do")
	public ModelAndView managementImgDownloadTest(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			//if(!mainService.isThreadRunning("managementTaskExecutor")){
			if(!mainService.isThreadRunning("taskExecutor")){
				watermarkService.managementImgDownloadTest();
				modelAndView.addObject("state", "1");
			}else{
				modelAndView.addObject("state", "2");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelAndView;
	}*/
	
	/*@RequestMapping(value = "/apply/supplyImgDownloadTest.do")
	public ModelAndView supplyImgDownloadTest(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			watermarkService.supplyImgDownloadTest();
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelAndView;
	}
	*/
	/**
	 * 승인준비중 상태의 파일 업로드
	 * @param request
	 * @param response
	 * @param param
	 * @param session
	 * @return
	 * @throws Exception
	 * @since 2017. 10. 12.
	 */
	@RequestMapping(value = "/apply/uploadFile.do")
	public ModelAndView uploadFile(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param, HttpSession session) throws Exception {
		
		System.out.println("ApplyController/uploadFile==================================");
		ModelAndView modelAndView = new ModelAndView("jsonView");
		ArrayList<String> fileArray= new ArrayList<String>();
		
		ArrayList<String> fileList = new ArrayList<String>();
		try{
			// 승인 버튼 누른 후, 업로드 준비중인 신청번호
			String supIdn = request.getParameter("supIdn");
			
			modelAndView.setViewName("/main/upload");
			System.out.println("업로드 준비중인 파일 확인--------------------"+supIdn);
				
			// 신청번호에 해당하는 파일리스트
			String filePath = propertyService.getString("Globals.supplyImgPath") + "/" + supIdn;
			filePath = filePath.replaceAll("\\\\", "/");
			
			String supIdnParam = ("'"+supIdn+"'");
			
			// supIdn 하위 디렉토리
			File f = new File(filePath);
			ArrayList<File> subFiles= new ArrayList<File>();
			
			try{
				 if(!f.exists()) { 
			           System.out.println("디렉토리가 존재하지 않습니다"); 
			           modelAndView.addObject("rtnCd", "Not Found Directory");
//					           return; 
			        } 
				 findSubFiles(f, subFiles);
				 
				 for(File file : subFiles) { 
					 if(file.isFile()) {
						 try{ 
//					                 System.out.println("파일 경로 : "+file.getCanonicalPath());
							 String path = file.getCanonicalPath();
							 path = path.replaceAll("\\\\", "/");fileList.add(path);
							 fileArray.add("'"+path+"'");
			                }catch(Exception e){ 
			                    e.printStackTrace(); 
			                } 
					 }
		        }
				 System.out.println("savedPath : "+fileArray);
				 
			}catch(Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
				
			modelAndView.addObject("fileArray", fileArray);
			modelAndView.addObject("fileList", fileList);
			modelAndView.addObject("supIdnParam", supIdn);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/apply/uploadFileCnt.do")
	public ModelAndView uploadFileCnt(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		List<Map<String, Object>> supIdnList = applyService.getSupIdnList();
		int uploadingCnt = niisSmartMapService.uploading(param);
		
		modelAndView.addObject("supIdnList", supIdnList);
		modelAndView.addObject("uploadingCnt", uploadingCnt);
		return modelAndView;
	}
	
	public static void findSubFiles(File parentFile, ArrayList<File> subFiles) {
		if (parentFile.isFile()) {
			subFiles.add(parentFile);
		} else if (parentFile.isDirectory()) {
			subFiles.add(parentFile);
			File[] childFiles = parentFile.listFiles();
			for (File childFile : childFiles) {
				findSubFiles(childFile, subFiles);
			}
		}
	}
	
	@RequestMapping(value = "/apply/uploadFileUpt.do")
	public ModelAndView applyUpt(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			System.out.println("업데이트 후 승인상태 변경---------------");
			
			Map<String, Object> sendMap =  new HashMap<String, Object>();
			sendMap.put("approvalCde", param.get("approvalCde")); //2 : 승인
			sendMap.put("lastChangeUsr"	, "");
			
			sendMap.put("supIdn", param.get("supIdn"));
			System.out.println("변경 supIdn==========>"+ param.get("supIdn") + "/ 승인코드 : "+param.get("approvalCde"));
				
			//신청서 상태 변경
			applyService.uptDbAppAuth(sendMap);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}
	
}
