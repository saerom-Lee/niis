package org.nii.niis.originList.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.nii.niis.niim.service.LoginService;
import org.nii.niis.niim.service.UserVO;
import org.nii.niis.originList.service.DigitTopoMapService;
import org.nii.niis.originList.service.DigitTopoMapVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * 
 * @Date : 2023. 09. 04.
 * @description : 원본DB 목록 수치지형도 controller 객체
 *
 */
@Controller
public class DigitTopoMapController {
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name="niimLoginService")
	private LoginService loginService;
	
	@Resource(name = "digitTopoMapService")
	protected DigitTopoMapService digitTopoMapService;
	
	/**
	 * 원본DB 목록 - 수치지형도 페이지
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/originList/digitListSearch.do")
	public String digitListSearch() throws Exception{
		return "originList/digitListSearch";
		
	}
	
	/**
	 * 원본DB 목록 - 최신 지도성과 목록 리스트 출력
	 * @param digitVO
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/originList/selectNewDigitList.do")
	public String selectNewDigitList(@RequestBody DigitTopoMapVO digitVO, ModelMap model) throws Exception{
		
		List<DigitTopoMapVO> selectNewDigitList = digitTopoMapService.selectNewDigitList(digitVO);
		model.addAttribute("selectNewDigitList", selectNewDigitList);
		
		int totalCnt = digitTopoMapService.selectNewDigitListCnt(digitVO);
		model.addAttribute("totalCnt", totalCnt);
		
		return "jsonView";
	}
	
	
	/**
	 * 원본DB 목록 - 과거 지도성과이력 목록 리스트 출력
	 * @param digitVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/originList/selectHistoryDigitList.do")
	public String selectHistoryDigitList(@RequestBody DigitTopoMapVO digitVO, ModelMap model) throws Exception{
		
		List<DigitTopoMapVO> selectHistoryDigitList = digitTopoMapService.selectHistoryDigitList(digitVO);
		model.addAttribute("selectHistoryDigitList", selectHistoryDigitList);
		
		int totalCnt = digitTopoMapService.selectHistoryDigitListCnt(digitVO);
		model.addAttribute("totalCnt", totalCnt);
		
		return "jsonView";
	}
	
	
	/**
	 * 원본DB 목록 - 최신 지도성과 상세조회
	 * @param digitVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/originList/selectNewDigitDetail.do")
	public String selectNewDigitDetail(@RequestBody DigitTopoMapVO digitVO, ModelMap model) throws Exception {
		
		DigitTopoMapVO digitTopoMapVO = digitTopoMapService.selectNewDigitDetail(digitVO);
		model.addAttribute("digitTopoMapVO", digitTopoMapVO);
		
		return "jsonView";
	}
	
	/**
	 * 원본DB 목록 - 과거 지도성과이력 상세조회
	 * @param digitVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/originList/selectHistoryDigitDetail.do")
	public String selectHistoryDigitDetail(@RequestBody DigitTopoMapVO digitVO, ModelMap model) throws Exception {
		
		DigitTopoMapVO digitTopoMapVO = digitTopoMapService.selectHistoryDigitDetail(digitVO);
		model.addAttribute("digitTopoMapVO", digitTopoMapVO);
		
		return "jsonView";
	}
	
	
	
}
