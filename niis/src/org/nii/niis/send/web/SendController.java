package org.nii.niis.send.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.send.service.SendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
* <pre>
* @Project : niis 
* @Package : org.nii.niis.send.web
* @FileName : SendController.java 
* @Date : 2017. 10. 30.
* @description : 신청 및 승인 알림(sms) controller 객체
* </pre>
 */
@Controller
@RequestMapping(value="/send/")
public class SendController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	@Resource(name="sendService")
	private SendService sendService;
	/**
	 * SMS 전송
	 * @param request
	 * @param response
	 * @param session
	 * @param param
	 * @return
	 * @throws Exception
	 * 2017. 10. 31.
	 */
	@RequestMapping(value="sms.do")
	public ModelAndView smsSend(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(param.containsKey("trCallback")){ //신청
				String action = "/sms/getMgrNum.do";
				
				Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
				List<Map<String, Object>> mgrNumList = (List<Map<String, Object>>)jsonMap.get("mgrNumList");
				
				for(int i=0; i<mgrNumList.size(); i++){
					String mgrNum = (String)mgrNumList.get(i).get("usrTel");
					log.debug("SMS전송_신청_보내는 사람------------------------"+param.get("trCallback"));
					log.debug("SMS전송_신청_받는 사람 ------------------------"+mgrNum);
					map.put("trPhone",mgrNum); //받는사람 전화번호 
					map.put("trCallback", param.get("trCallback")); //보내는사람 전화번호
					map.put("trMsg", "국토영상정보 영상이 신청되었습니다. \n승인 부탁드립니다");
					sendService.smsSend(map);
				} 
			}else{ // 승인
				String action = "/sms/getApTel.do";
				
				Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
				String apTel = (String)jsonMap.get("apTel");
				
				System.out.println("SMS전송_승인------------------------");
				log.debug("SMS전송_신청_보내는 사람-----------------------" + propertiesService.getString("Globals.adminTel"));
				log.debug("SMS전송_신청_받는 사람 ------------------------" + apTel);
				map.put("trPhone", apTel); //받는사람 전화번호 (신청자)
				map.put("trCallback", propertiesService.getString("Globals.adminTel")); //보내는사람 전화번호
				map.put("trMsg", "국토영상정보 영상신청이 승인 되었습니다. \n확인부탁드립니다");
				sendService.smsSend(map);
			}
		}catch(Exception e){
			log.error(this.getClass() + "::::smsSend", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} 
		return modelAndView;
	}
	
	/**
	 * 신청서 승인 여부 SMS 전송
	 * @param request
	 * @param response
	 * @param session
	 * @param param
	 * @return
	 * @throws Exception
	 * 2018. 1. 10.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="applyAppYnSms.do")
	public void applyAppYnSms(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		JSONObject json = new JSONObject();
		try{
			if(param.containsKey("approvalCde")){
				String action = "/apply/getApplyDetail.do";
				Map<String, Object> map = (Map<String, Object>)connService.connNiim(action, param, request, response).get("map");
				
				String approvalCde = (String)param.get("approvalCde");		//승인상태
				String apName = (String)map.get("apName");		//신청자명
				String apTel = (String)map.get("apTel");		//신청자 전화번호
				
				String msgCd = "";
				//승인
				if("2".equals(approvalCde)){
					msgCd = "Globals.applyCnfmMsg";
				//반려
				}else if("5".equals(approvalCde)){
					msgCd = "Globals.applyRtrnMsg";
				}
				
				String from = propertiesService.getString("Globals.adminTel");
				String msg = new String(propertiesService.getString(msgCd).getBytes("ISO-8859-1"),"UTF-8").replace("__apName__", apName);
				
				if(!"".equals(msg) && null != apTel){
					log.debug(apTel + " --> " + from + " : " + msg);
					sendService.smsSend(apTel, from, msg);
				}
			}
			json.put("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			json.put("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			log.error(this.getClass() + "::::applyAppYnSms", e);
		} 
		
		//jsonp 처리
		PrintWriter pw = null;
		try{
			pw = response.getWriter();
			pw.write(param.get("callback") + "(" + json.toString() + ")");
			pw.flush();
		}catch(Exception e){
			log.error(this.getClass() + "::::applyAppYnSms", e);
		}finally{
			if(null != pw){try{ pw.close(); }catch(Exception e){ }}
		}
	}
	
	/**
	 * 사용자 승인 여부 SMS 전송
	 * @param request
	 * @param response
	 * @param session
	 * @param param
	 * @return
	 * @throws Exception
	 * 2018. 1. 10.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="userAppYnSms.do")
	public void userAppYnSms(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		JSONObject json = new JSONObject();
		try{
			if(param.containsKey("st")){
				String action = "/sms/getUserInfoDetail.do";
				Map<String, Object> map = (Map<String, Object>)connService.connNiim(action, param, request, response).get("map");
				
				String st = (String)param.get("st");		//승인상태
				String usrNm = (String)map.get("usrNm");		//신청자명
				String usrTel = (String)map.get("usrTel");		//신청자 전화번호
				
				String msgCd = "";
				//승인
				if("C".equals(st)){
					msgCd = "Globals.userCnfmMsg";
				//반려
				}else if("B".equals(st)){
					msgCd = "Globals.userRtrnMsg";
				}
				
				String from = propertiesService.getString("Globals.adminTel");
				String msg = new String(propertiesService.getString(msgCd).getBytes("ISO-8859-1"),"UTF-8").replace("__usrNm__", usrNm);
				
				if(!"".equals(msg)){
					log.debug(usrTel + " --> " + from + " : " + msg);
					sendService.smsSend(usrTel, from, msg);
				}
			}
			json.put("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			json.put("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			log.error(this.getClass() + "::::userAppYnSms", e);
		}
		
		//jsonp 처리
		PrintWriter pw = null;
		try{
			pw = response.getWriter();
			pw.write(param.get("callback") + "(" + json.toString() + ")");
			pw.flush();
		}catch(Exception e){
			log.error(this.getClass() + "::::userAppYnSms", e);
		}finally{
			if(null != pw){try{ pw.close(); }catch(Exception e){ }}
		}
	}
}
