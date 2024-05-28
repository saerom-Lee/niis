package org.nii.niis.login.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.egov.EgovFileScrty;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.send.service.SendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * <pre>
 * @Project : niis 
 * @Package : org.nii.niis.login.web
 * @FileName : LoginController.java 
 * @Date : 2016. 12. 19.
 * @description : 로그인, 회원가입 controller 객체
 * </pre>
 */
@Controller
@RequestMapping(value = "/login/")
public class LoginController {
	
	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name = "connService")
	private ConnService connService;
	
	@Resource(name="sendService")
	private SendService sendService;
	
	/**
	 * <pre>
	 * @Method : login
	 * @Date : 2016. 12. 19.
	 * @description : 로그인 화면 진입
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "login.do")
	public String login(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		if(session.getAttribute("aUserMgno") != null){
			response.sendRedirect("/niis/main/main.do");
		}
		return "login/install";
	}
	
	@RequestMapping(value = "login2.do")
	public String login2(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "login/login_old";
	}
	
	@RequestMapping(value = "checkUser.do")
	public String loginGpkiUpdate(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "login/checkUser";
	}
	
	@RequestMapping(value = "gpkiLogin.do")
	public String gpkiLogin(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		if(session.getAttribute("aUserMgno") != null){
			response.sendRedirect("/niis/main/main.do");
		}
		
		if(param.containsKey("state")){
			model.addAttribute("state", param.get("state"));
		}
		if(param.containsKey("userId")){
			model.addAttribute("userId", param.get("userId"));
		}
		if(param.containsKey("cause")){
			model.addAttribute("cause", param.get("cause"));
		}
		return "login/login";
	}
	
	/**
	 * <pre>
	 * @Method : loginCheck
	 * @Date : 2016. 12. 19.
	 * @description : 사용자 로그인
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "loginCheck.do", method = RequestMethod.POST, produces = "application/json; charset=euc-kr")
	public ModelAndView loginCheck(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/login/loginCheck.do";
			
			String sendParam = "userId=" + (String) param.get("userId") 
							+ "&userpw=" + EgovFileScrty.encryptPassword((String) param.get("userpw"));
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response);
			
			String state = (String) jsonMap.get("state");
			
			if ("1".equals(state)) {
				Map<String, Object> result = (HashMap<String, Object>) jsonMap.get("result");
				
				session.setAttribute("aUserMgno", result.get("userMgno"));
				session.setAttribute("aUserId", result.get("userId"));
				session.setAttribute("aUserName", result.get("userNm"));
				session.setAttribute("aUserEmail", result.get("email"));
				session.setAttribute("aUserAuth", result.get("userAuth"));
				session.setAttribute("aModMonth", result.get("modMonth"));
				session.setAttribute("aHelpYn", result.get("helpYn"));
			}
			
			modelAndView.addObject("userId", param.get("userId"));
			modelAndView.addObject("state", state);
			modelAndView.addObject("cause", jsonMap.get("cause"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::loginCheck", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * @Method : loginCheckGpki
	 * @Date : 2016. 12. 19.
	 * @description : 사용자 로그인
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "loginCheckGpki.do", method = RequestMethod.POST, produces = "application/json; charset=euc-kr")
	public ModelAndView loginCheckGpki(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/login/loginCheckGpki.do";
			param.put("userpw", EgovFileScrty.encryptPassword((String) param.get("userpw")));
			//String sendParam = "&userpw=" + EgovFileScrty.encryptPassword((String) param.get("userpw"));
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			String state = (String) jsonMap.get("state");
			
			if ("1".equals(state)) {
				Map<String, Object> result = (HashMap<String, Object>) jsonMap.get("result");
				
				session.setAttribute("aUserMgno", result.get("userMgno"));
				session.setAttribute("aUserId", result.get("userId"));
				session.setAttribute("aUserName", result.get("userNm"));
				session.setAttribute("aUserEmail", result.get("email"));
				session.setAttribute("aUserAuth", result.get("userAuth"));
				session.setAttribute("aModMonth", result.get("modMonth"));
				session.setAttribute("aHelpYn", result.get("helpYn"));
			}
			
			modelAndView.addObject("userId", jsonMap.get("userId"));
			modelAndView.addObject("state", state);
			modelAndView.addObject("cause", jsonMap.get("cause"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::loginCheck", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * @Method : regCheckGpki
	 * @Date : 2018. 01. 11.
	 * @description : 사용자 로그인
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "regCheckGpki.do", method = RequestMethod.POST, produces = "application/json; charset=euc-kr")
	public ModelAndView regCheckGpki(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/login/loginCheckGpki.do";
			param.put("userpw", EgovFileScrty.encryptPassword((String) param.get("userpw")));
			//String sendParam = "&userpw=" + EgovFileScrty.encryptPassword((String) param.get("userpw"));
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			String state = (String) jsonMap.get("state");
			
			if("1".equals(state)) {
				Map<String, Object> result = (HashMap<String, Object>) jsonMap.get("result");
				
				session.setAttribute("aUserMgno", result.get("userMgno"));
				session.setAttribute("aUserId", result.get("userId"));
				session.setAttribute("aUserName", result.get("userNm"));
				session.setAttribute("aUserEmail", result.get("email"));
				session.setAttribute("aUserAuth", result.get("userAuth"));
				session.setAttribute("aModMonth", result.get("modMonth"));
				session.setAttribute("aHelpYn", result.get("helpYn"));
			}else if("4".equals(state)){
				
				param.put("id", jsonMap.get("userId"));
				
				action = "/login/reRegUser.do";
				
				Map<String, Object> userMap = connService.connNiim(action, param, request, response);
				modelAndView.addObject("userInfo", userMap.get("map"));
			}
			
			modelAndView.addObject("userId", param.get("userId"));
			modelAndView.addObject("state", state);
			modelAndView.addObject("cause", jsonMap.get("cause"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::loginCheck", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * <pre>
	 * @Method : logout
	 * @Date : 2016. 12. 19.
	 * @description : 사용자 로그아웃
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		try {
			if (session.getAttribute("aUserMgno") != null) {
				
				String action = "/login/logout.do";
				String sendParam = "usrMgno=" + (String) session.getAttribute("aUserMgno");
				
				connService.connNiim(action, sendParam, request, response);
			}
		} catch (Exception e) {
			log.error(this.getClass() + "::::logout", e);
		}
		
		session.invalidate();
		
		return "redirect:/login/gpkiLogin.do";
	}
	
	/**
	 * <pre>
	 * @Method : regUser
	 * @Date : 2016. 12. 19.
	 * @description : 사용자 회원가입 화면 진입
	 * </pre>
	 * 
	 * @param model
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "regUser.do")
	public String regUser(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * try{ String action = "/login/regUser.do";
		 * 
		 * Map<String, Object> jsonMap = connService.connNiim(action, param,
		 * request, response);
		 * 
		 * model.addAttribute("userAuth", jsonMap.get("userAuth"));
		 * }catch(Exception e){ log.error(this.getClass() + "::::regUser", e);
		 * model.addAttribute("RETURN_CD",
		 * HttpServletResponse.SC_INTERNAL_SERVER_ERROR); }
		 */
		return "login/register";
	}
	
	/**
	 * <pre>
	 * @Method : reRegUser
	 * @Date : 2016. 12. 19.
	 * @description : 반려사용자 재신청 페이지 진입
	 * </pre>
	 * 
	 * @param model
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "reRegUser.do")
	public String reRegUser(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String action = "/login/reRegUser.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("map", jsonMap.get("map"));
			// model.addAttribute("userAuth", jsonMap.get("userAuth"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::reRegUser", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return "login/register";
	}

	/**
	 * <pre>
	 * @Method : chkDuplication
	 * @Date : 2016. 12. 19.
	 * @description : 아이디 중복체크
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "chkDuplication.do", method = RequestMethod.POST, produces = "application/json; charset=euc-kr")
	public ModelAndView chkDuplication(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");

		try {
			String action = "/login/chkDuplication.do";

			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);

			String state = (String) jsonMap.get("state");

			modelAndView.addObject("state", state);
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::chkDuplication", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		return modelAndView;
	}

	/**
	 * <pre>
	 * @Method : register
	 * @Date : 2016. 12. 19.
	 * @description : 사용자 회원가입
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "register.do", method = RequestMethod.POST, produces = "application/json; charset=euc-kr")
	public ModelAndView register(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/login/register.do";
			
			// 재신청일 경우
			if ("Y".equals(param.get("reapp"))) {
				action = "/login/reRegister.do";
				param.put("usrId", param.get("dup"));
			}
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			String state = (String)jsonMap.get("state");
			String usrMgno = (String)jsonMap.get("list");
			if("1".equals(state) && null != usrMgno){
				userAppReqSms(request, response, usrMgno);
			}
			
			modelAndView.addObject("state", state);
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::register", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	
	/**
	 * <pre>
	 * @Method : update
	 * @Date : 2016. 12. 19.
	 * @description : 사용자 인증서 갱신
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "update.do", method = RequestMethod.POST, produces = "application/json; charset=euc-kr")
	public ModelAndView update(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			String action = "/login/update.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			String state = (String)jsonMap.get("state");
			
			modelAndView.addObject("state", state);
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		} catch (Exception e) {
			log.error(this.getClass() + "::::register", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}

	/**
	 * <pre>
	 * @Method : regWait
	 * @Date : 2016. 12. 19.
	 * @description : 승인대기 화면
	 * </pre>
	 * 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "regWait.do")
	public String regWait(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "login/regWait";
	}

	/**
	 * <pre>
	 * @Method : regReject
	 * @Date : 2016. 12. 19.
	 * @description : 반려 화면
	 * </pre>
	 * 
	 * @param model
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "regReject.do")
	public String regReject(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		model.addAttribute("userId", param.get("userId"));
		model.addAttribute("cause", param.get("cause"));
		return "login/regReject";
	}

	/**
	 * <pre>
	 * @description : gpki 로그인 체크
	 * </pre>
	 * 
	 * @param model
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "gpkiLoginCheck.do")
	public String gpkiLoginCheck(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "login/gpki/gpkiLoginCheck";
	}
	
	/** 
	* <pre>
	* @Method : userAppReqSms
	* @Date : 2018. 01. 10.
	* @description : 회원가입 신청 알림 SMS
	* </pre>
	* @param response
	* @return
	* @throws Exception
	*/
	@SuppressWarnings("unchecked")
	private void userAppReqSms(HttpServletRequest request, HttpServletResponse response, String usrMgno) {
		
		try{
			List<Map<String, Object>> mgrList = (List<Map<String, Object>>)connService.connNiim("/sms/getMgrNum.do", "", request, response).get("mgrNumList");
			Map<String, Object> userInfo = (Map<String, Object>)connService.connNiim("/sms/getUserInfoDetail.do", "usrMgno="+usrMgno, request, response).get("map");
			
			String post 		= (String)userInfo.get("post");		//사용자 소속
			String department 	= (String)userInfo.get("department");		//사용자 부서
			String usrNm 		= (String)userInfo.get("usrNm");		//사용자명
			String position 	= (String)userInfo.get("position");		//사용자 직급
			String usrTel 		= (String)userInfo.get("usrTel");		//사용자 전화번호
			
			String msg = new String(propertiesService.getString("Globals.userReqMsg").getBytes("ISO-8859-1"),"UTF-8")
					.replace("__post__", post)
					.replace("__department__", department)
					.replace("__usrNm__", usrNm);
			
			if(!"".equals(msg) && null != usrTel){
				for(int i=0; i<mgrList.size(); i++){
					String mgrNum = (String)mgrList.get(i).get("usrTel");
					if(null != mgrNum && !"".equals(mgrNum)){
						try{
							sendService.smsSend(mgrNum, usrTel, msg);
						}catch(Exception e){
							log.error(this.getClass() + "::::userAppReqSms", e);
						}
					}
				}
			}
		}catch(Exception e){
			log.error(this.getClass() + "::::userAppReqSms", e);
		}
	}
}
