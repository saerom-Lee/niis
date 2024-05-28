package org.nii.niis.connNiim.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nii.niis.common.egov.EgovFileScrty;
import org.nii.niis.connNiim.service.NiisCommonService;
import org.nii.niis.niim.service.LoginService;
import org.nii.niis.niim.service.UserVO;
import org.nii.niis.niim.service.UserinfoService;
import org.nii.niis.niim.util.BlackList;
import org.nii.niis.niim.util.JSONUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/niim")
public class NiisLoginController {
	
	@Resource(name="niimLoginService")
    private LoginService loginService;
	
	@Resource(name="niisCommonService")
	private NiisCommonService niisCommonService;
	
	@Resource(name="userinfoService")
	private UserinfoService userinfoService;
	
	
	/**
	 * 사용자 로그인
	 * @param param
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/login/loginCheck.do")
	public ModelAndView loginCheckTest(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("=============niislogincontroller=======");
		String userID = "";
		String userPW = "";
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if (param != null){
			
			if(param.containsKey("userId") && null != param.get("userId")){
				userID = BlackList.getStrCnvrXss((String)param.get("userId"));
			}
			
			if (param.containsKey("userpw")){
				userPW = (String)param.get("userpw");
			}
		}
		
		try {
			List<UserVO> userObj = this.loginService.getLoginCheck(userID, userPW);
			if (userObj.size() == 1){
				
				if(userPW.equals(userObj.get(0).getPassword())){
					
					//승인
					if("C".equals(userObj.get(0).getSt())){
						modelAndView.addObject("state", "1");
						Map<String, Object> returnMap =  new HashMap<String, Object>();
						returnMap.put("userMgno", userObj.get(0).getUniqId());
						returnMap.put("userId", userObj.get(0).getId());
						returnMap.put("userNm", userObj.get(0).getName());
						returnMap.put("email", userObj.get(0).getEmail());
						returnMap.put("userAuth", userObj.get(0).getUseAt());
						returnMap.put("modMonth", userObj.get(0).getLastModMonth());
						returnMap.put("helpYn", userObj.get(0).getHelpYn());
						
						modelAndView.addObject("result", returnMap);
						
						//로그인 로그 기록
						loginService.insertLoginLog(
								userObj.get(0).getUniqId(), 
								"I", 
								(String)param.get("connAcsIp"), 
								userObj.get(0).getUniqId(), 
								(String)request.getAttribute("usrSysCde"));
						
					}
					//반려
					else if("B".equals(userObj.get(0).getSt())){
						modelAndView.addObject("state", "4");
						modelAndView.addObject("cause", userObj.get(0).getCause());
					}
					//승인요청
					else if("A".equals(userObj.get(0).getSt())){
						modelAndView.addObject("state", "5");
					}
					else{
						modelAndView.addObject("state", "6");
					}
					
				//비밀번호가 틀린 경우
				}else{
					
					loginService.insertLoginLog(
							userObj.get(0).getUniqId(), 
							"J", 
							(String)param.get("connAcsIp"), 
							userObj.get(0).getUniqId(), 
							(String)request.getAttribute("usrSysCde"));
					
					modelAndView.addObject("state", "3");
				}
			//아이디가 존재하지 않는 경우	
			}else{
				modelAndView.addObject("state", "2");
			}
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
        return modelAndView;
    }
	
	/**
	 * gpki사용자 로그인
	 * @param param
	 * @param request
	 * @param response
	 * @param session
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/login/loginCheckGpki.do"/*, method=RequestMethod.POST, produces = "application/json; charset=euc-kr"*/)
	//public ModelAndView loginCheckGpki(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
	public ModelAndView loginCheckGpki(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			Map<String, Object> param = JSONUtil.jsonToMap(body);
			
			String userPW = "";
			if (param != null && param.containsKey("userpw")){
				userPW = (String)param.get("userpw");
			}
			
			List<UserVO> userObj = this.loginService.getLoginCheckGpki(userPW);
			if (userObj.size() == 1){
				//승인
				if("C".equals(userObj.get(0).getSt())){
					modelAndView.addObject("state", "1");
					Map<String, Object> returnMap =  new HashMap<String, Object>();
					returnMap.put("userMgno", userObj.get(0).getUniqId());
					returnMap.put("userId", userObj.get(0).getId());
					returnMap.put("userNm", userObj.get(0).getName());
					returnMap.put("email", userObj.get(0).getEmail());
					returnMap.put("userAuth", userObj.get(0).getUseAt());
					returnMap.put("modMonth", userObj.get(0).getLastModMonth());
					returnMap.put("helpYn", userObj.get(0).getHelpYn());
					
					modelAndView.addObject("result", returnMap);
					
					//로그인 로그 기록
					loginService.insertLoginLog(
							userObj.get(0).getUniqId(), 
							"I", 
							(String)param.get("connAcsIp"), 
							userObj.get(0).getUniqId(), 
							(String)request.getAttribute("usrSysCde"));
					
				}
				//반려
				else if("B".equals(userObj.get(0).getSt())){
					modelAndView.addObject("state", "4");
					modelAndView.addObject("userId", userObj.get(0).getId());
					modelAndView.addObject("cause", userObj.get(0).getCause());
				}
				//승인요청
				else if("A".equals(userObj.get(0).getSt())){
					modelAndView.addObject("state", "5");
				}
				else{
					modelAndView.addObject("state", "6");
				}
			//아이디가 존재하지 않는 경우	
			}else{
				modelAndView.addObject("state", "2");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return modelAndView;
    }
	
	/**
	 * 사용자 로그아웃
	 * @param param
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="/login/logout.do")
    public ModelAndView logout(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			loginService.insertLoginLog(
					(String)param.get("usrMgno"), 
					"O", 
					(String)param.get("connAcsIp"), 
					(String)param.get("usrMgno"), 
					(String)request.getAttribute("usrSysCde"));
			
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/login/regUser.do")
	public ModelAndView regUser(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try {
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("queryID", "mainDAO.getCommonCode");
			
			sendMap.put("cdeCde", "USER_AUTH");
			modelAndView.addObject("userAuth", niisCommonService.getCommonCode(sendMap));
			
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		} catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/login/reRegUser.do")
	public ModelAndView getApplyInfo(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			UserVO userVO = loginService.getLoginCheck((String)param.get("id"), null).get(0);
			
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
			
			modelAndView.addObject("map", userinfo);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * id 중복체크
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/login/chkDuplication.do")
    public ModelAndView chkDuplication(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> chkMap = new HashMap<String, Object>();
			chkMap.put("usrId", param.get("usrId"));
		
			int idCnt = userinfoService.chkUserId(chkMap);
			
			if(idCnt == 0){
				modelAndView.addObject("state", "1");
			}else{
				modelAndView.addObject("state", "2");
			}
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch (Exception e) {
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
        return modelAndView;
	}
	
	
	/**
	 * 사용자 회원가입
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/login/register.do")
    public ModelAndView register(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> chkMap = new HashMap<String, Object>();
			chkMap.put("usrId", param.get("usrId"));
			
			int idCnt = userinfoService.chkUserId(chkMap);
			
			if(idCnt == 0){
			
				Map<String, Object> sendMap =  new HashMap<String, Object>();
				
				sendMap.put("usrNm"			, BlackList.getStrCnvrXss((String)param.get("usrNm")));
				sendMap.put("usrId"			, BlackList.getStrCnvrXss((String)param.get("usrId")));
				sendMap.put("usrPw"			, EgovFileScrty.encryptPassword((String)param.get("usrPw")));
				sendMap.put("post"			, BlackList.getStrCnvrXss((String)param.get("post")));
				sendMap.put("department"	, BlackList.getStrCnvrXss((String)param.get("department")));
				sendMap.put("position"		, BlackList.getStrCnvrXss((String)param.get("position")));
				sendMap.put("useAt"			, (String)param.get("useAt"));
				sendMap.put("usrTel"		, (String)param.get("usrTel"));
				sendMap.put("usrEml"		, (String)param.get("usrEml"));
				sendMap.put("st"			, "A");
				sendMap.put("stplatAgre1"	, (String)param.get("stplatAgre1"));
				sendMap.put("stplatAgre2"	, (String)param.get("stplatAgre2"));
				sendMap.put("stplatAgre3"	, (String)param.get("stplatAgre3"));
				
				String usrMgno = userinfoService.regUserInfo(sendMap);
				loginService.insertLoginLog(
						usrMgno, 
						"A", 
						(String)param.get("connAcsIp"), 
						usrMgno, 
						(String)request.getAttribute("usrSysCde"));
				
				modelAndView.addObject("list", usrMgno);
				//위의 list 의 사용여부를 확인하기가 어려워 새로 선언 SMS용
				modelAndView.addObject("usrMgno", usrMgno);
			}
			modelAndView.addObject("state", "1");
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(DuplicateKeyException e){
			modelAndView.addObject("state", "2");
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
		return modelAndView;
	}
	
	
	/**
	 * 사용자 인증서 갱신
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/login/update.do")
    public ModelAndView update(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		
		try{
			Map<String, Object> chkMap = new HashMap<String, Object>();
			chkMap.put("usrId", (String)param.get("usrId"));
			chkMap.put("usrNm", (String)param.get("usrNm"));
			chkMap.put("post", (String)param.get("post"));
			chkMap.put("usrTel", (String)param.get("usrTel"));
			
			
			int idCnt = userinfoService.chkUserInfo(chkMap);
			
			
			if(idCnt > 0){
				
			
				Map<String, Object> sendMap =  new HashMap<String, Object>();
				
				sendMap.put("usrId"			, BlackList.getStrCnvrXss((String)param.get("usrId")));
				sendMap.put("usrNm"			, BlackList.getStrCnvrXss((String)param.get("usrNm")));
				sendMap.put("usrPw"			, EgovFileScrty.encryptPassword((String)param.get("usrPw")));
				sendMap.put("post"			, BlackList.getStrCnvrXss((String)param.get("post")));
				sendMap.put("usrTel"		, (String)param.get("usrTel"));
				
				String usrMgno = userinfoService.updateUserInfoForGPKI(sendMap);
				loginService.insertLoginLog(
						usrMgno, 
						"A", 
						(String)param.get("connAcsIp"), 
						usrMgno, 
						(String)request.getAttribute("usrSysCde"));
				
				modelAndView.addObject("list", usrMgno);
				//위의 list 의 사용여부를 확인하기가 어려워 새로 선언 SMS용
				modelAndView.addObject("usrMgno", usrMgno);
				modelAndView.addObject("state", "1");
				modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			}
			else {
				modelAndView.addObject("state", "2");
				modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			}
			
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
		return modelAndView;
	}
	
	/**
	 * 사용자 반려 재신청
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/login/reRegister.do")
    public ModelAndView reRegister(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String userID = (String)param.get("usrId");
			
			List<UserVO> userObj = loginService.getLoginCheck(userID, null);
			
			if(userObj != null && userObj.size() == 1 && "B".equals(userObj.get(0).getSt())){
			
				String usrMgno = userObj.get(0).getUniqId();
				
				Map<String, Object> sendMap =  new HashMap<String, Object>();
				
				sendMap.put("usrMgno"		, usrMgno);
				sendMap.put("usrNm"			, BlackList.getStrCnvrXss((String)param.get("usrNm")));
				sendMap.put("usrPw"			, EgovFileScrty.encryptPassword((String)param.get("usrPw")));
				sendMap.put("post"			, BlackList.getStrCnvrXss((String)param.get("post")));
				sendMap.put("department"	, BlackList.getStrCnvrXss((String)param.get("department")));
				sendMap.put("position"		, BlackList.getStrCnvrXss((String)param.get("position")));
				sendMap.put("useAt"			, (String)param.get("useAt"));
				sendMap.put("usrTel"		, (String)param.get("usrTel"));
				sendMap.put("usrEml"		, (String)param.get("usrEml"));
				sendMap.put("st"			, "A");
				sendMap.put("stplatAgre1"	, (String)param.get("stplatAgre1"));
				sendMap.put("stplatAgre2"	, (String)param.get("stplatAgre2"));
				sendMap.put("stplatAgre3"	, (String)param.get("stplatAgre3"));
				
				int cnt = userinfoService.reRegUserInfo(sendMap);
				loginService.insertLoginLog(
						usrMgno, 
						"A", 
						(String)param.get("connAcsIp"), 
						usrMgno, 
						(String)request.getAttribute("usrSysCde"));
				
				if(cnt == 1){
					modelAndView.addObject("usrMgno", usrMgno);
					modelAndView.addObject("state", "1");
				}else{
					modelAndView.addObject("state", "3");
				}
			}else{
				modelAndView.addObject("state", "3");
			}
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
		}
		
		return modelAndView;
	}
}
