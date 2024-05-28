package org.nii.niis.guide.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *  Guide controller 객체
 * 
 * @directed
 */
@Controller
@RequestMapping(value="/guide/")
public class GuideController {
	/**
	 * 도움글 열기
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="guideOpen.do")
    public String guideOpen(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "guide/"+param.get("guideId");
	}
	/**
	 * 도움글 초기화
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="initGuidePop.do")
    public String initGuidePop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "guide/initGuidePop";
	}
}
