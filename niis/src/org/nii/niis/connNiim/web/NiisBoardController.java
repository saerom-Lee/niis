package org.nii.niis.connNiim.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.niim.service.BoardService;
import org.nii.niis.niim.util.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
@RequestMapping(value="/niim")
public class NiisBoardController {
	
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
	@RequestMapping(value="/board/board.do")
	public String board(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/board/board.do";
	}
	/**
	 * 게시판  리스트 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return String
	 */
	@RequestMapping(value="/board/getBoardList.do")
	public String getBoardList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/board/getBoardList.do";
	}
	/**
	 * 게시판 (게시글) 내용 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return String
	 */
	@RequestMapping(value="/board/getBoardDetail.do")
	public String getBoardDetail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/board/getBoardDetail.do";
	}
	/**
	 * 게시판 (게시글) 입력
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/insertBoard.do")
	public ModelAndView insertBoard(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("삽입삽입삽입삽입삽입삽입삽입");
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			sendMap.put("createUsr", sendMap.get("connUserNo"));
			sendMap.put("lastChangeUsr", sendMap.get("connUserNo"));
			
			//신청서 작성
			Map<String, Object> resultMap = boardService.insertBoard(sendMap);
			
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
		
	/**
	 * 게시판 (게시글) 수정
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/updateBoard.do")
	public ModelAndView updateBoard(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			sendMap.put("lastChangeUsr", sendMap.get("connUserNo"));
			
			//신청서 작성
			Map<String, Object> resultMap = boardService.updateBoard(sendMap);
			
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
	/**
	 * 게시판 (게시글) 삭제
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/deleteBoard.do")
	public String deleteBoard(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/board/deleteBoard.do";
	}
	
	@RequestMapping(value="/board/getAttachFile.do")
	public String getAttachFile(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "forward:/niimFor/board/getAttachFile.do";
	}
	/**
	 * 게시판 (팝업글) 입력
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/insertBoardPop.do")
	public ModelAndView insertBoardPop(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			sendMap.put("createUsr", sendMap.get("connUserNo"));
			sendMap.put("lastChangeUsr", sendMap.get("connUserNo"));
			
			//신청서 작성
			Map<String, Object> resultMap = boardService.insertBoardPop(sendMap);
			
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 게시판 (게시글) 수정
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/updateBoardPop.do")
	public ModelAndView updateBoardPop(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			sendMap.put("lastChangeUsr", sendMap.get("connUserNo"));
			
			//신청서 작성
			Map<String, Object> resultMap = boardService.updateBoardPop(sendMap);
			
			modelAndView.addObject("isSuccess", resultMap.get("isSuccess"));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			modelAndView.addObject("isSuccess", "0");
			e.printStackTrace();
		}
		return modelAndView;
	}
}
