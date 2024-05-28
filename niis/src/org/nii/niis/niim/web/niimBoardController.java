package org.nii.niis.niim.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nii.niis.niim.service.BoardService;
import org.nii.niis.niim.util.BlackList;
import org.nii.niis.niim.util.PagingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * Board controller 객체
 * 
 * @stereotype control
 */
@Controller
@RequestMapping(value="niimFor")
public class niimBoardController {
	/**
	 * Board controller 객체
	 * 
	 * @stereotype control
	 */
	@Resource(name="boardService")
	private BoardService boardService;
	
	/**
	 * 게시판 master 정보 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/board.do")
	public @ResponseBody Map<String,Object> board(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//ModelAndView modelAndView = new ModelAndView("jsonView");
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("master", boardService.getBoardMaster(param));
						
		System.out.println(result.toString());
		
		return result;
		
		
	}
	/**
	 * 게시판 리스트 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/getBoardList.do")
	public @ResponseBody Map<String, Object> getBoardList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		Map<String,Object> result = new HashMap<String, Object>();
		
		if(param.containsKey("srchType")){
			param.put("srchType", BlackList.getStrCnvrXss((String)param.get("srchType")));
		}
		if(param.containsKey("srchCondition")){
			param.put("srchCondition", BlackList.getStrCnvrXss((String)param.get("srchCondition")));
		}
		
		//PagingUtil.getInstance().setPageData(param, modelAndView, boardService.getBoardListCnt(param));
		
		List<Map<String, Object>> boardList = boardService.getBoardList(param);
		List<Map<String, Object>> boardTopList = boardService.getBoardTopList(param);
		
		result.put("list", boardList);
		result.put("top", boardTopList);
		result.put("master", boardService.getBoardMaster(param));
		
		return result;
	}
	/**
	 * 게시판 한개 정보 가져오기
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/getBoardDetail.do")
	public @ResponseBody Map<String, Object> getBoardDetail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Map<String, Object> boardMap = boardService.getBoardDetail(param);
		List<Map<String, Object>> boardFileList = boardService.getBoardFileList(param);
		
		result.put("map", boardMap);
		result.put("list", boardFileList);
		result.put("master", boardService.getBoardMaster(param));
		
		return result;
	}
	/**
	 * 게시판 입력
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/insertBoard.do")
	public @ResponseBody Map<String,Object> insertBoard(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			Map<String, Object> resultMap = boardService.insertBoard(this.setUsrMgno(request, param));
			
			
			result.put("isSuccess", resultMap.get("isSuccess"));
			
		}catch(Exception e){
			
			result.put("isSuccess", "0");
			e.printStackTrace();		
		}
		return result;
	}
	/**
	 * 게시판 입력
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/updateBoard.do")
	public @ResponseBody Map<String,Object> updateBoard(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			Map<String, Object> resultMap = boardService.updateBoard(this.setUsrMgno(request, param));
			
			
			result.put("isSuccess", resultMap.get("isSuccess"));
			
		}catch(Exception e){
			
			result.put("isSuccess", "0");
			e.printStackTrace();		
		}
		return result;
	}
	/**
	 * 게시판 삭제
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/deleteBoard.do")
	public @ResponseBody Map<String,Object> deleteBoard(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			Map<String, Object> resultMap = boardService.deleteBoard(this.setUsrMgno(request, param));
			
			
			result.put("isSuccess", resultMap.get("isSuccess"));
			
		}catch(Exception e){
			
			result.put("isSuccess", "0");
			e.printStackTrace();		
		}
		return result;
		
		
	}
	/**
	 * 게시판 파일 다운로드 
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="/board/getAttachFile.do")
	public @ResponseBody Map<String, Object> getAttachFile(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			Map<String, Object> resultMap = boardService.getAttachFile(param);
			result.put("map", resultMap);
		}catch(Exception e){
			result.put("isSuccess", "0");
			e.printStackTrace();		
		}
		return result;
	}
	/**
	 * 세션 이용자 정보 저장 
	 */
	private Map<String, Object> setUsrMgno(HttpServletRequest request, Map<String, Object> param) {
		
		String usrMgno = "";
		
		//공급시스템 요청
		if(param.containsKey("connUserNo")){
			usrMgno = (String)param.get("connUserNo");
		}
		//관리시스템 요청
		else{
			HttpSession session = request.getSession();
			usrMgno = (String)session.getAttribute("sUserMgno");
		}
		param.put("createUsr", usrMgno);
		param.put("lastChangeUsr", usrMgno);
		
		return param;
	}
}
