package org.nii.niis.connNiim.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.niim.service.LoginService;
import org.nii.niis.niim.service.UserVO;
import org.nii.niis.niim.service.UserinfoService;
import org.nii.niis.niim.util.BlackList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/niim")
public class NiisUserinfoController {

	@Resource(name="niimLoginService")
    private LoginService loginService;
	
	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	
	/**
	 * 사용자 정보 수정을 위한 데이터 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/userinfo/myInfoPop.do")
	public ModelAndView myInfoPop(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			UserVO userVO = loginService.getLoginCheck((String)param.get("userId"), null).get(0);
			
			String[] usrTel = null;
			String[] usrEml = null;
			
			try{
				usrTel = userVO.getUsrTel().split("-");
			}catch(NullPointerException e){}
			
			try{
				usrEml = userVO.getEmail().split("@");
			}catch(NullPointerException e){}			
			
			Map<String, Object> userinfo = new HashMap<String, Object>();
			userinfo.put("usrNm", userVO.getName());
			userinfo.put("usrId", userVO.getId());
			userinfo.put("usrPw", userVO.getPassword());
			userinfo.put("post", userVO.getPost());
			userinfo.put("department", userVO.getDepartment());
			userinfo.put("position", userVO.getPosition());
			if(null != usrTel && usrTel.length == 3){
				userinfo.put("usrTel1", usrTel[0]);
				userinfo.put("usrTel2", usrTel[1]);
				userinfo.put("usrTel3", usrTel[2]);
			}
			
			if(null != usrEml && usrEml.length == 2){
				userinfo.put("usrEml1", usrEml[0]);
				userinfo.put("usrEml2", usrEml[1]);
			}
			
			modelAndView.addObject("userinfo", userinfo);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	/**
	 * 사용자관리 사용자 정 수정
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/userinfo/userInfoUpt.do")
	public ModelAndView uptUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> sendMap =  new HashMap<String, Object>();
		
		String usrMgno = (String)param.get("usrMgno");
		
		/*
		String usrPw = (String)param.get("usrPw");
		String exPw  = null;
		
		String currPass = userinfoService.getCurrPass(usrMgno);
		exPw = EgovFileScrty.encryptPassword((String)param.get("exPw"));
		
		if(!exPw.equals(currPass)){
			modelAndView.addObject("isNewPass", "1");
			return modelAndView;
		}
	
		if(null != usrPw && !"".equals(usrPw) && !"-".equals(usrPw)){
			usrPw = EgovFileScrty.encryptPassword((String)param.get("usrPw"));
			
			// 기존 비밀번호와 같은지 체크
			if(usrPw.equals(currPass)){
				modelAndView.addObject("isNewPass", "0");
				return modelAndView;
			}
		}
		*/
		
		sendMap.put("usrMgno"		, usrMgno);
		//sendMap.put("usrPw"			, usrPw);
		sendMap.put("post"			, BlackList.getStrCnvrXss((String)param.get("post")));
		sendMap.put("department"	, BlackList.getStrCnvrXss((String)param.get("department")));
		sendMap.put("position"		, BlackList.getStrCnvrXss((String)param.get("position")));
		//sendMap.put("useAt"			, (String)param.get("useAt"));
		sendMap.put("usrTel"		, (String)param.get("usrTel"));
		sendMap.put("usrEml"		, (String)param.get("usrEml"));
		
		int userInfoRst = userinfoService.uptUserInfo(sendMap);
		
		loginService.insertLoginLog(
				usrMgno, 
				"E", 
				(String)param.get("connAcsIp"), 
				usrMgno, 
				(String)request.getAttribute("usrSysCde"));
		
		modelAndView.addObject("list", userInfoRst);
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/userinfo/cfrmInitGuide.do")
	public ModelAndView cfrmInitGuide(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			param.put("helpYn", "N");
			
			userinfoService.cfrmInitGuide(param);
			
			modelAndView.addObject("helpYn", "N");
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			e.printStackTrace();
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
}
