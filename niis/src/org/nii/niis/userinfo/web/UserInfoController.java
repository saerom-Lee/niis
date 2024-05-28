package org.nii.niis.userinfo.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
* @Package : org.nii.niis.userinfo.web
* @FileName : UserInfoController.java 
* @Date : 2016. 12. 19.
* @description : 사용자 정보수정 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/userinfo/")
public class UserInfoController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	@Resource(name="fileUtil")
    private FileUtil fileUtil;
	
	/** 
	* <pre>
	* @Method : myInfoPop
	* @Date : 2016. 12. 19.
	* @description : 내 정보 조회
	* </pre>
	* @param model
	* @param request
	* @param response
	* @param session
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="myInfoPop.do")
    public String myInfoPop(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		
		try{
			String userId = (String)session.getAttribute("aUserId");
			
			String action = "/userinfo/myInfoPop.do";
			String sendParam = "userId=" + userId;
			
			Map<String, Object> jsonMap = connService.connNiim(action, sendParam, request, response);
			
			model.addAttribute("userinfo", jsonMap.get("userinfo"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));

		}catch(Exception e){
			log.error(this.getClass() + "::::myInfoPop", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "userinfo/myInfoPop";
	}
	
	/** 
	* <pre>
	* @Method : register
	* @Date : 2016. 12. 19.
	* @description : 내 정보 수정
	* </pre>
	* @param param
	* @param request
	* @param response
	* @param session
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="userinfoUpt.do")
	public ModelAndView register(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response ,HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			String usrMgno = (String)session.getAttribute("aUserMgno");
			param.put("usrMgno", usrMgno);
			
			String action = "/userinfo/userInfoUpt.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("isNewPass", jsonMap.get("isNewPass"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::userinfoUpt", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return  modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : cfrmInitGuide
	* @Date : 2016. 12. 19.
	* @description : 초기 이용가이드 다시보지 않기
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="cfrmInitGuide.do")
	public ModelAndView cfrmInitGuide(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		try{
			param.put("usrMgno", request.getSession().getAttribute("aUserMgno"));
			
			String action = "/userinfo/cfrmInitGuide.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			//세션 갱신 처리
			request.getSession().setAttribute("aHelpYn", jsonMap.get("helpYn"));
			
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::cfrmInitGuide", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return  modelAndView;
	}
}