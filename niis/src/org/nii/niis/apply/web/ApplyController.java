package org.nii.niis.apply.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.service.UserinfoService;
import org.nii.niis.send.service.SendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.woowonSoft.framework.util.StringUtil;

import egovframework.rte.fdl.property.EgovPropertyService;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.apply.web
* @FileName : ApplyController.java 
* @Date : 2016. 12. 19.
* @description : 신청서 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/apply/")
public class ApplyController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	@Resource(name="sendService")
	private SendService sendService;
	
	@Resource(name="applyService")
	private ApplyService applyService;
	
	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	
	/** 
	* <pre>
	* @Method : getApplyList
	* @Date : 2016. 12. 19.
	* @description : 신청서 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @param session
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getApplyList.do")
	public ModelAndView getApplyList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
	
		try{
			String action = "/apply/getApplyList.do";
			param.put("usrMgno", session.getAttribute("aUserMgno"));
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getApplyList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
		
	/** 
	* <pre>
	* @Method : regApplyPop
	* @Date : 2016. 12. 19.
	* @description : 원본DB 목록 선택신청 신청서 등록 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="regApplyPop.do")
	public String regApplyPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			
			List<Map<String, String>> productApplyList = new ArrayList<Map<String, String>>();
			
			String[] imageCde = request.getParameterValues("imageCde[]");
			String[] keyVal1 = request.getParameterValues("keyVal1[]");
			String[] keyVal2 = request.getParameterValues("keyVal2[]");
			String[] keyVal3 = request.getParameterValues("keyVal3[]");
			String[] zoneNam = request.getParameterValues("zoneNam[]");
			String[] mapKindNm = request.getParameterValues("mapKindNm[]");
			String[] scaleNm = request.getParameterValues("scaleNm[]");
			String[] noticeNo = request.getParameterValues("noticeNo[]");
			String[] productYear = request.getParameterValues("productYear[]");
			
			
			if(request.getParameter("imageCde") != null) {
				// at성과
				if(request.getParameter("imageCde").toString().equals("atCde")) {
					
					for(int i=0; i<keyVal1.length; i++){
						Map<String, String> map = new HashMap<String, String>();
						map.put("imageCde", request.getParameter("imageCde").toString());
						map.put("keyVal1", keyVal1[i]);
						map.put("zoneNam",  zoneNam[i]);
						
						productApplyList.add(map);
					}
				}
			}else {
				
				
				for(int i=0; i<imageCde.length; i++){
					
					// 수치지형도
					if(imageCde[i].toString().equals("PDT008")) {
						
						Map<String, String> map = new HashMap<String, String>();
						map.put("imageCde", imageCde[i]);
						map.put("keyVal1", keyVal1[i]);
						map.put("keyVal2", keyVal2[i]);
						map.put("keyVal3", keyVal3[i]);
						map.put("mapKindNm",  mapKindNm[i]);
						map.put("scaleNm",  scaleNm[i]);
						map.put("noticeNo",  noticeNo[i]);
						map.put("productYear",  productYear[i]);
						
						productApplyList.add(map);
						
						
					//항공사진, 수치표고, 정사영상
					}else {
						Map<String, String> map = new HashMap<String, String>();
						map.put("imageCde", imageCde[i]);
						map.put("keyVal1", keyVal1[i]);
						map.put("keyVal2", keyVal2[i]);
						map.put("keyVal3", keyVal3[i]);
						map.put("zoneNam",  zoneNam[i]);
						
						productApplyList.add(map);
						
					}
					
				}
			}
			
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			//코드 조회
			String action = "/apply/getApplyInfo.do";	// NiisApplyController.java
			String sendParam = "";
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response);
			
			// 신청목적 공통코드 조회(신청서 활용목적 리스트)
			action ="/apply/getPurposeList.do";			// NiisApplyController.java
			sendParam = "";
			Map<String, Object> purposeMap = connService.connNiim(action, sendParam, request, response);
			
			model.addAttribute("applyType", "select");
			model.addAttribute("list", productApplyList);
			model.addAttribute("today", today);
			model.addAttribute("userinfo", jsonMap.get("userinfo"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
			model.addAttribute("purposeList", purposeMap.get("purposeList"));
		}catch(Exception e){
			log.error(this.getClass() + "::::regApplyPop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "apply/regApplyPop";
	}
	
	/**
	 * @Method : getPurposeList
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="getPurposeList.do")
	public ModelAndView getPurposeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/apply/getPurposeList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("detailPurposeList", jsonMap.get("purposeList"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getPurposeList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : regApplyConfirm
	* @Date : 2016. 12. 19.
	* @description : 원본DB 목록 전체신청 확인 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="regApplyConfirm.do")
	public String regApplyConfirm(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			/**
			 * 전체 내역을 가져와 DIV에 넘기는 로직
			 * 속도 개선을 위해 조회 파라미터를 넘겨 자바단에서 처리토록 변경
			 */
			String imageCde = (String)param.get("imageCde");
			String action = "";
			if(imageCde.equals(propertiesService.getString("Globals.airZoneCode"))){
				action = "/apply/getAirProductCnt.do";		// NiisApplyController.java
				model.addAttribute("sYear", param.get("sYear"));
				model.addAttribute("eYear", param.get("eYear"));
				model.addAttribute("zoneNam", param.get("zoneNam"));
				//param.put("securityCde", "SEC002");
			}else if(imageCde.equals(propertiesService.getString("Globals.demZoneCode"))){
				action = "/apply/getDemProductCnt.do";		// NiisApplyController.java
				model.addAttribute("sYear", param.get("sYear"));
				model.addAttribute("eYear", param.get("eYear"));
				model.addAttribute("zoneNam", param.get("zoneNam"));
				//param.put("securityCde", "SEC002");
			}else if(imageCde.equals(propertiesService.getString("Globals.ortZoneCode"))){
				action = "/apply/getOrtProductCnt.do";		// NiisApplyController.java
				model.addAttribute("sYear", param.get("sYear"));
				model.addAttribute("eYear", param.get("eYear"));
				model.addAttribute("zoneNam", param.get("zoneNam"));
				//param.put("securityCde", "SEC002");
			}else if(imageCde.equals(propertiesService.getString("Globals.airLibZoneCode"))){
				action = "/apply/getAirLibProductCnt.do";	// NiisApplyController.java
			}else if(imageCde.equals("atCde")){
				action = "/apply/getAtProductCnt.do";		// NiisApplyController.java
				model.addAttribute("sYear", param.get("sYear"));
				model.addAttribute("eYear", param.get("eYear"));
			}else if(imageCde.equals(propertiesService.getString("Globals.mapZoneCode"))){
				action = "/apply/getMapProductCnt.do";		// NiisApplyController.java
				model.addAttribute("mapSerNo", param.get("mapSerNo"));
				model.addAttribute("mapShtNo", param.get("mapShtNo"));
			}
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("cnt", jsonMap.get("cnt"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
			System.out.println("jsonMap cnt>>>>>" + jsonMap.get("cnt"));
			
		}catch(Exception e){
			log.error(this.getClass() + "::::regApplyConfirm", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "apply/regApplyConfirm";
	}
	
	
	/** 
	* <pre>
	* @Method : regApplyPopAll
	* @Date : 2016. 12. 19.
	* @description : 원본DB 목록 전체신청 신청서 등록 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="regApplyPopAll.do")
	public String regApplyPopAll(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			/**
			 * 전체 내역을 가져와 DIV에 넘기는 로직
			 * 속도 개선을 위해 조회 파라미터를 넘겨 자바단에서 처리토록 변경
			 */
			// 신청자 정보 조회
			String action = "/apply/getApplyInfo.do";
			
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			// 신청목적 공통코드 조회
			action ="/apply/getPurposeList.do";
			String sendParam = "";
			Map<String, Object> purposeMap = connService.connNiim(action, sendParam, request, response);
			
			model.addAttribute("applyType", "search");
			model.addAttribute("today", today);
			model.addAttribute("userinfo", jsonMap.get("userinfo"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
			
			// 신청목적 리스트
			model.addAttribute("purposeList", purposeMap.get("purposeList"));
			
			// 조회 항목 선택값
			model.addAttribute("sYear", param.get("sYear"));
			model.addAttribute("eYear", param.get("eYear"));
			model.addAttribute("cnt", param.get("cnt"));
			model.addAttribute("zoneNam", param.get("zoneNam"));
		}catch(Exception e){
			log.error(this.getClass() + "::::regApplyPopAll", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "apply/regApplyPop";
	}
	
	
	/** 
	* <pre>
	* @Method : regApplyPopTree
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 신청서 등록 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="regApplyPopTree.do")
	public String regApplyPopTree(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String applyList = request.getParameter("reqName");
			
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			//코드 조회
			String action = "/apply/getApplyInfo.do";
			String sendParam = "";
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response);
			
			// 신청목적 공통코드 조회
			action ="/apply/getPurposeList.do";
			sendParam = "";
			Map<String, Object> purposeMap = connService.connNiim(action, sendParam, request, response);
			
			model.addAttribute("applyType", "tree");
			model.addAttribute("applyList", applyList);
			model.addAttribute("today", today);
			model.addAttribute("userinfo", jsonMap.get("userinfo"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
			model.addAttribute("purposeList", purposeMap.get("purposeList"));
			
		}catch(Exception e){
			log.error(this.getClass() + "::::regApplyPopTree", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "apply/regApplyPopTree";
	}
	
	
	/** 
	* <pre>
	* @Method : regApply
	* @Date : 2016. 12. 19.
	* @description : 원본DB 목록 선택신청 신청서 등록
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="regApply.do")
	public ModelAndView regApply(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/apply/regApply.do";	// NiisApplyController.java
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.putAll(param);
			sendMap.remove("imageCde");
			sendMap.remove("keyVal1");
			sendMap.remove("keyVal2");
			sendMap.remove("keyVal3");
			
			String[] imageCde = request.getParameterValues("imageCde");
			String[] keyVal1 = request.getParameterValues("keyVal1");
			String[] keyVal2 = request.getParameterValues("keyVal2");
			String[] keyVal3 = request.getParameterValues("keyVal3");
			String useAt = request.getParameter("useAt");
			
			JSONArray jArray = new JSONArray();
			for(int i=0; i<imageCde.length; i++){
				
				JSONObject jObj = new JSONObject();
				
				jObj.put("imageCde", imageCde[i]);
				jObj.put("keyVal1", keyVal1[i]);
				
				if(!imageCde[i].toString().equals("atCde")) {
						jObj.put("keyVal2", keyVal2[i]);
						jObj.put("keyVal3", keyVal3[i]);
				}
				jArray.add(jObj);
				
				//항공사진 - AT성과 추가
				if(imageCde[i].toString().equals("PDT001") && useAt.equals("Y")) {
					JSONObject jObj2 = new JSONObject();
					jObj2.put("imageCde", "atCde");
					jObj2.put("keyVal1", keyVal1[i]);
					jArray.add(jObj2);
				}
			}
			
			sendMap.put("reqDate", today);
			sendMap.put("imgList", jArray);
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, sendMap, request, response, 60*1000, 60*1000);
			
			//System.out.println(jsonMap.toString());
			
			if(jsonMap.containsKey("supIdn")){
				this.applyAppReqSms(request, response, (String)jsonMap.get("supIdn"));
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::regApply", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : regApplyAll
	* @Date : 2016. 12. 19.
	* @description : 원본DB 목록 전체신청 신청서 등록
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="regApplyAll.do")
	public ModelAndView regApplyAll(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String imageCde = (String)param.get("imageCde");
			String action = "/apply/regApplyAll.do";
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			if(imageCde.equals(propertiesService.getString("Globals.airZoneCode"))){
				//param.put("securityCde", "SEC002");
			}else if(imageCde.equals(propertiesService.getString("Globals.demZoneCode"))){
				//param.put("securityCde", "SEC002");
			}else if(imageCde.equals(propertiesService.getString("Globals.ortZoneCode"))){
				//param.put("securityCde", "SEC002");
			}
			
			param.put("reqDate", today);
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response, 60*1000, 60*1000);
			
			if(jsonMap.containsKey("supIdn")){
				this.applyAppReqSms(request, response, (String)jsonMap.get("supIdn"));
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::regApplyAll", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : regApplyTree
	* @Date : 2016. 12. 19.
	* @description : 원본DB 검색 신청서 등록
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(value="regApplyTree.do")
	public ModelAndView regApplyTree(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/apply/regApplyTree.do";
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.putAll(param);
			sendMap.remove("imageCde");
			sendMap.remove("keyVal1");
			sendMap.remove("keyVal2");
			sendMap.remove("keyVal3");
			sendMap.remove("keyVal4");
			
			String[] imageCde = request.getParameterValues("imageCde[]");
			String[] keyVal1 = request.getParameterValues("keyVal1[]");
			String[] keyVal2 = request.getParameterValues("keyVal2[]");
			String[] keyVal3 = request.getParameterValues("keyVal3[]");
			String[] keyVal4 = request.getParameterValues("keyVal4[]");
			String useAt = request.getParameter("useAt");
			
			JSONArray jArray = new JSONArray();
			for(int i=0; i<imageCde.length; i++){
				
				JSONObject jObj = new JSONObject();
				
				jObj.put("imageCde", imageCde[i]);
				jObj.put("keyVal1", keyVal1[i]);
				
				if(!imageCde[i].toString().equals("atCde")) {
					jObj.put("keyVal2", keyVal2[i]);
					jObj.put("keyVal3", keyVal3[i]);
					jObj.put("keyVal4", keyVal4[i]);
				}
				jArray.add(jObj);
				
				//항공사진 - AT성과 추가
				if(imageCde[i].toString().equals("PDT001") && useAt.equals("Y")) {
					JSONObject jObj2 = new JSONObject();
					jObj2.put("imageCde", "atCde");
					jObj2.put("keyVal1", keyVal1[i]);
					jArray.add(jObj2);
				}
			}
			
			sendMap.put("reqDate", today);
			sendMap.put("imgList", jArray);
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, sendMap, request, response, 60*1000, 60*1000);
			
			if(jsonMap.containsKey("supIdn")){
				this.applyAppReqSms(request, response, (String)jsonMap.get("supIdn"));
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::regApplyTree", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}

	/** 
	* <pre>
	* @Method : waitApply
	* @Date : 2016. 12. 19.
	* @description : 대기 신청서 목록 화면
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="waitApply.do")
	public String waitApply(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "apply/waitApplyList";
	}
	
	/** 
	* <pre>
	* @Method : appApply
	* @Date : 2016. 12. 19.
	* @description : 승인 신청서 목록 화면
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="appApply.do")
	public String appApply(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "apply/appApplyList";
	}
	
	/** 
	* <pre>
	* @Method : rejApply
	* @Date : 2016. 12. 19.
	* @description : 반려 신청서 목록 화면
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="rejApply.do")
	public String rejApply(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "apply/rejApplyList";
	}
	
	/** 
	* <pre>
	* @Method : compApply
	* @Date : 2016. 12. 19.
	* @description : 완료 신청서 목록 화면
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="compApply.do")
	public String compApply(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "apply/compApplyList";
	}
	
	
	/** 
	* <pre>
	* @Method : getUserApplyList
	* @Date : 2016. 12. 19.
	* @description : 사용자 신청서 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	public ModelAndView getUserApplyList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/apply/getUserApplyList.do";
			
			param.put("usrMgno", request.getSession().getAttribute("aUserMgno"));
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getUserApplyList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : getWaitApplyList
	* @Date : 2016. 12. 19.
	* @description : 대기 신청서 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getWaitApplyList.do")
	public ModelAndView getWaitApplyList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		param.put("type", "wait");
		return this.getUserApplyList(param, request, response);
	}
	
	/** 
	* <pre>
	* @Method : getAppApplyList
	* @Date : 2016. 12. 19.
	* @description : 승인 신청서 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getAppApplyList.do")
	public ModelAndView getAppApplyList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		param.put("type", "app");
		return this.getUserApplyList(param, request, response);
	}
	
	/** 
	* <pre>
	* @Method : getRejApplyList
	* @Date : 2016. 12. 19.
	* @description : 반려 신청서 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getRejApplyList.do")
	public ModelAndView getRejApplyList( @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		param.put("type", "rej");
		return this.getUserApplyList(param, request, response);
	}
	
	/** 
	* <pre>
	* @Method : getCompApplyList
	* @Date : 2016. 12. 19.
	* @description : 완료 신청서 목록 조회
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getCompApplyList.do")
	public ModelAndView getCompApplyList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		param.put("type", "comp");
		return this.getUserApplyList(param, request, response);
	}
	
	
	/** 
	* <pre>
	* @Method : getApplyDetail
	* @Date : 2016. 12. 19.
	* @description : 신청서 정보 상세 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getApplyDetail.do")
	public String getApplyDetail(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/apply/getApplyDetail.do";		// NiisApplyController.java
			
			String reqName = (String) param.get("reqName");
			model.addAttribute("reqName", reqName);
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("map", jsonMap.get("map"));
			model.addAttribute("applyDetailList", jsonMap.get("applyDetailList"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getApplyDetail", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "/apply/applyDetailPop";
	}
	
	
	/** 
	* <pre>
	* @Method : rejectCausePop
	* @Date : 2016. 12. 19.
	* @description : 신청서 반려 사유 확인
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="rejectCausePop.do")
	public String rejectCausePop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/apply/getRejectCause.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			//화면 상태 변경을 위한 파라미터
			if(jsonMap.containsKey("appChange")){
				model.addAttribute("appChange", jsonMap.get("appChange"));
			}
			model.addAttribute("supIdn", param.get("supIdn"));
			model.addAttribute("map", jsonMap.get("map"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::rejectCausePop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "apply/rejectCausePop";
	}
	
	/**
	 * @Method : waitApplyDelete
	 * @Date : 2023. 08. 24.
	 * @description : 신청서 대기 페이지에서 상태가 승인 요청일시 삭제 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="waitApplyDelete.do")
	public ModelAndView waitApplyDelete(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/apply/waitApplyDelete.do";
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::waitApplyDelete", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/** 
	* <pre>
	* @Method : applyExtension
	* @Date : 2016. 12. 19.
	* @description : 승인 신청서 승인 기간 연장
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="applyExtension.do")
	public ModelAndView applyExtension(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/apply/applyExtension.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("state", jsonMap.get("state"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::applyExtension", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : reRegApplyPop
	* @Date : 2018. 01. 09.
	* @description : 반려 신청서 재신청 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="reRegApplyPop.do")
	public String reRegApplyPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			String action = "/apply/reRegApplyPop.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("applyType", "reject");
			model.addAttribute("today", today);
			model.addAttribute("userinfo", jsonMap.get("userinfo"));
			model.addAttribute("apply", jsonMap.get("apply"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::reRegApplyPop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "apply/regApplyPop";
	}
	
	/** 
	* <pre>
	* @Method : reRegApply
	* @Date : 2018. 01. 09.
	* @description : 반려 신청서 재신청
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="reRegApply.do")
	public ModelAndView reRegApply(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/apply/reRegApply.do";
			String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			param.put("reqDate", today);
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			if(jsonMap.containsKey("supIdn")){
				this.applyAppReqSms(request, response, (String)jsonMap.get("supIdn"));
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::reRegApply", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : applyAppReqSms
	* @Date : 2018. 01. 10.
	* @description : 신청서 등록 알림 SMS (문자 전송)
	* </pre>
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	private void applyAppReqSms(HttpServletRequest request, HttpServletResponse response, String supIdn) {
		
		try{
			//List<Map<String, Object>> mgrList = (List<Map<String, Object>>)connService.connNiim("/sms/getMgrNum.do", "", request, response).get("mgrNumList");

			List<String> iimManagerList = userinfoService.getIimManagerNumberList();
			List<String> mapManagerList = userinfoService.getMapManagerNumberList();
			
			Map<String, Object> dbAppDetail = null;
			Map<String, Object> param = new HashMap<String, Object>();			
			param.put("supIdn", supIdn);
			dbAppDetail = applyService.getDbAppDetail(param);
			
			String apPost 		= (String)dbAppDetail.get("apPost");		//신청자 소속
			String apDepartment = (String)dbAppDetail.get("apDepartment");		//신청자 부서
			String apName 		= (String)dbAppDetail.get("apName");		//신청자명
			String apPosition 	= (String)dbAppDetail.get("apPosition");		//신청자 직급
			String apTel 		= (String)dbAppDetail.get("apTel");		//신청자 전화번호
			
			String iimApprovalCde = (String)dbAppDetail.get("iimApprovalCde");
			String mapApprovalCde = (String)dbAppDetail.get("mapApprovalCde");
			
			System.out.println("IIM_APPROVAL_CDE : " + iimApprovalCde + "MAP_APPROVAL_CDE : " + mapApprovalCde);
			
			
			String msg = new String("[공간정보행정망서비스] __apPost__/__apName__ 님께서 신청서를 등록 하셨습니다.")
					.replace("__apPost__", apPost)
					.replace("__apDepartment__", apDepartment)
					.replace("__apName__", apName);
			
			if(iimApprovalCde != null) {
				if(iimManagerList.size() > 0) {
					for(int i=0; i<iimManagerList.size(); i++) {
						String number = StringUtil.getSeedDecrypt(iimManagerList.get(i));
						
						if(number.length() > 0) {
							sendService.smsSend("031-210-2700", number, msg);
						}
					}
				}
			}
			
			if(mapApprovalCde != null) {
				if(mapManagerList.size() > 0) {
					for(int i=0; i<mapManagerList.size(); i++) {
						String number = StringUtil.getSeedDecrypt(mapManagerList.get(i));
						if(number.length() > 0) {
							sendService.smsSend("031-210-2700", number, msg);
						}
					}
				}
			}
			
			
			
		}catch(Exception e){
			log.error(this.getClass() + "::::applyAppReqSms", e);
		}
	}
}
