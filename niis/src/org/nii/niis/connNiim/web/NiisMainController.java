package org.nii.niis.connNiim.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.connNiim.service.NiisMainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;



@Controller
@RequestMapping(value="/niim")
public class NiisMainController {

	@Resource(name="propertiesService")
	private EgovPropertyService propertyService;
	
	@Resource(name="niisMainService")
	private NiisMainService niisMainService;
	
	
	@RequestMapping(value="/main/getMainApply.do")
	public ModelAndView getMainApply(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			//param.put("limitDay", propertyService.getInt("Globals.downloadLimitDay"));
			
			Map<String, Object> resultMap = niisMainService.getMainApply(param);
			
			modelAndView.addObject("map", resultMap);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/main/getMainBoard.do")
	public ModelAndView getMainBoard(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			List<Map<String, Object>> resultList = niisMainService.getMainBoard(param);
			
			modelAndView.addObject("list", resultList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/main/getMainPopup.do")
	public ModelAndView getMainPopup(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			List<Map<String, Object>> resultList = niisMainService.getMainPopup(param);
			
			modelAndView.addObject("list", resultList);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return modelAndView;
	}
}

