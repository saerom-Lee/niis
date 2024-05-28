package org.nii.niis.connNiim.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.niim.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
@RequestMapping(value="/niim")
public class NiisDisasterController {
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertyService;
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	/**
	 * 게시판 master 정보 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return String
	 */
	@RequestMapping(value="/disaster/disaster.do")
	public String disaster(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/board/board.do";
	}
	/**
	 * 게시판  리스트 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return String
	 */
	@RequestMapping(value="/disaster/getDisasterList.do")
	public String getDisasterList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/getDisasterList.do";
	}
	/**
	 * 게시판 (게시글) 내용 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return String
	 */
	@RequestMapping(value="/disaster/getDisasterDetail.do")
	public String getDisasterDetail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/getDisasterDetail.do";
	}
	
	@RequestMapping(value="/disaster/getDisasterVideoList.do")
	public String getDisasterVideoList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/getDisasterVideoList.do";
	}
	
	@RequestMapping(value="/disaster/disasterTifImgData.do")
	public String disasterTifImgData(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/disasterTifImgData.do";
	}
	
	@RequestMapping(value="/disaster/disasterFileDwn.do")
	public String disasterFileDwn(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/disasterFileDwn.do";
	}
	
	@RequestMapping(value="/disaster/getDisasterCodeList.do")
	public String getDisasterCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/getDisasterCodeList.do";
	}
		
	@RequestMapping(value="/disaster/getDisasterRegion1List.do")
	public String getDisasterRegion1List(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/getDisasterRegion1List.do";
	}
	
	@RequestMapping(value="/disaster/getDisasterRegion2List.do")
	public String getDisasterRegion2List(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/disaster/getDisasterRegion2List.do";
	}
}
