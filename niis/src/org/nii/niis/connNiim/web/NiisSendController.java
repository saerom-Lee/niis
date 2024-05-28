package org.nii.niis.connNiim.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.service.UserinfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value="/niim")
public class NiisSendController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="userinfoService")
	UserinfoService userinfoService;
	
	@Resource(name="applyService")
	private ApplyService applyService;
	
	/**
	 * SMS 전송 - 관리자 전화번호 획득
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/sms/getMgrNum.do")
	public ModelAndView getMgrNum(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			List<String> mgrNumList = userinfoService.getMgr();
			modelAndView.addObject("mgrNumList", mgrNumList);
		}catch(Exception e){
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return modelAndView;
	}
	
	/**
	 * SMS 전송 - 관리자 (담당자) 전화번호
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/sms/getManagerNumber.do")
	public ModelAndView getManagerNumber(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			List<String> managerNumberList = userinfoService.getManagerNumberList();
			modelAndView.addObject("managerNumberList", managerNumberList);
		}catch(Exception e){
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return modelAndView;
	}
	
	/**
	 * SMS 전송 - 신청자 전화번호 획득
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/sms/getApTel.do")
	public ModelAndView getApTel(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> dbAppDetail = null;
			dbAppDetail = applyService.getDbAppDetail(param);
			
			modelAndView.addObject("apTel", dbAppDetail.get("apTel"));
		}catch(Exception e){
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return modelAndView;
	}
	
	/**
	 * SMS 전송 - 회원가입 신청자 정보 조회
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/sms/getUserInfoDetail.do")
	public ModelAndView getUserInfoDetail(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			List<?> userInfoDetail = userinfoService.getUserInfoDetail(param);
			
			if(null != userInfoDetail && userInfoDetail.size() > 0){
				modelAndView.addObject("map", userInfoDetail.get(0));
			}
		}catch(Exception e){
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return modelAndView;
	}
}
