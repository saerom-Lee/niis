package org.nii.niis.connNiim.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.connNiim.service.NiisCommonService;
import org.nii.niis.niim.util.PolicyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/niim")
public class NiisCommonController {

	@Resource(name="niisCommonService")
	private NiisCommonService niisCommonService;
	
	@Resource(name="policyUtil")
	private PolicyUtil policyUtil;
	
	
	@RequestMapping(value="/common/getCommonCode.do")
	public ModelAndView getCommonCode(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			List<Map<String, Object>> codeList = null;
			
			//xacml 파라미터 추가
			//policyUtil.addPolicy(request, param);
			
			codeList = niisCommonService.getCommonCode(param);
			System.out.println("codeList>>>>>>>>>>>>" + codeList);
			modelAndView.addObject("list", codeList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){	
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}
}
