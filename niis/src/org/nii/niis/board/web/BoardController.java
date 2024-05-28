package org.nii.niis.board.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.common.util.FileUtil;
import org.nii.niis.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;
/**
 * board controller 객체
 * 
 * @directed
 */
@Controller
@RequestMapping(value="/board/")
public class BoardController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	/**
	 * Conn controller 객체
	 * 
	 * @directed
	 */
	@Resource(name="connService")
	private ConnService connService;
	/**
	 * fileUtil controller 객체
	 * 
	 * @directed
	 */
	@Resource(name="fileUtil")
    private FileUtil fileUtil;
	/**
	 * 공지 사항에서 미리보기 
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 */
	@RequestMapping(value="previewPop.do")
    public String previewPop(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "board/previewPop";
	}
	
	/**
	 * 공지사항 게시판의 master 정보 가져오기
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="board.do")
    public String board(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/board/board.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			if(param.containsKey("boardLink")){
				model.addAttribute("boardLink", param.get("boardLink"));
			}
			model.addAttribute("master", jsonMap.get("master"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::board", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "board/board";
	}
	/**
	 * 공지사항 게시판 list 가져오기
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="getBoardList.do")
	public ModelAndView getBoardList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/board/getBoardList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			//modelAndView.addObject("top", jsonMap.get("top"));
			modelAndView.addObject("master", jsonMap.get("master"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getBoardList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	/**
	 * 공지사항 게시판의 게시판 본문 가져오기
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="getBoardDetail.do")
	public ModelAndView getBoardDetail(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Cookie cookies[] = request.getCookies();
			Map<String, String> cookieMap = new HashMap<String, String>();
			
			if(request.getCookies() != null){
				for(int i=0; i<cookies.length; i++){
					Cookie cookie = cookies[i];
					cookieMap.put(cookie.getName(), cookie.getValue());
				}
			}
			
			String cookie_read_count = StringUtil.nvl(cookieMap.get("read_count"), "");
			String new_cookie_read_count = "|" + param.get("boardCate") + "_" + param.get("boardSeq");
			
			if(cookie_read_count.indexOf(new_cookie_read_count) == -1){
				
				Cookie cookie = new Cookie("read_count", cookie_read_count + new_cookie_read_count);
				
				response.addCookie(cookie);
				
				param.put("readYn", "N");
			}
			
			String action = "/board/getBoardDetail.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);

			modelAndView.addObject("map", jsonMap.get("map"));
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("detail", jsonMap.get("master"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getBoardDetail", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	/**
	 * 공지사항 게시판의 master 정보 가져오기
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="register.do")
    public String register(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String target = (String)param.get("target");
		String updateYn = (String)param.get("update");
		String boardSeq = (String)param.get("boardSeq");
		
		try{
			String action = "/board/board.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			String today = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime());
			String todayDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			
			model.addAttribute("today", today);
			model.addAttribute("update", updateYn);
			model.addAttribute("boardSeq", boardSeq);
			model.addAttribute("todayDate", todayDate);
			model.addAttribute("parentSeq", param.get("parentSeq"));
			model.addAttribute("master", jsonMap.get("master"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::register", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return target;
	}
	/**
	 * 공지사항 게시판 새글(게시글) 등록
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="insertBoard.do")
	public ModelAndView insertBoard(@RequestParam Map<String, Object> param, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		//Iterator<String> itr = param.keySet().iterator();while(itr.hasNext()){String key = itr.next();log.debug(key+"["+param.get(key)+"]");}
		ModelAndView modelAndView = new ModelAndView("jsonView");
		ArrayList<String> fileList = new ArrayList<String>();
		
		try{
			List<MultipartFile> files = request.getFiles("bFile");
			
			JSONArray jArray = new JSONArray();
			if(files != null && files.size() > 0 && !files.get(0).getOriginalFilename().equals("")){
				for(int i=0; i<files.size(); i++){
					MultipartFile file = files.get(i);
					
					String fileName = file.getOriginalFilename();
					long fileSize 	= file.getSize();
					
					String fileUrl 	= fileUtil.uploadFile(request, file, null, propertiesService.getString("Globals.board") + File.separator + param.get("boardCate"), false, true);
						
					JSONObject jObj = new JSONObject();
					jObj.put("boardCate", param.get("boardCate"));
					jObj.put("fileName", fileName);
					jObj.put("fileUrl", fileUrl);
					jObj.put("fileSize", fileSize);
					jArray.add(jObj);	
					
					fileList.add(fileUrl);
				}
			}
			
			param.put("fileList", jArray);
			
			String action = "/board/insertBoard.do";
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			//통신 실패시 파일 삭제
			if((int)jsonMap.get("RETURN_CD") != HttpServletResponse.SC_OK){
				for(int i=0; i<fileList.size(); i++){
					fileUtil.deleteFile(request, fileList.get(i));
				}
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
		modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::insertBoard", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 공지사항 게시판 새글(팝업글, 팝업 이미지글) 등록
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="insertBoardPop.do")
	public ModelAndView insertBoardPop(@RequestParam Map<String, Object> param, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		ArrayList<String> fileList = new ArrayList<String>();
		
		String fileUrl = null;
		
		try{
			List<MultipartFile> files = request.getFiles("bFile");
			
			if(files != null && files.size() > 0 && !files.get(0).getOriginalFilename().equals("")){
				for(int i=0; i<files.size(); i++){
					
					MultipartFile file = files.get(i);
					
					//Globals.pop =>/upload/pop 에 저장을 시킨다. 
					fileUrl = fileUtil.uploadFile(request,file, null, propertiesService.getString("Globals.pop") + File.separator + param.get("boardCate"), false, true);
					
					fileList.add(fileUrl);
				}
			}
			
			param.put("fileUrl", fileUrl);
			
			String action = "/board/insertBoardPop.do";
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			//통신 실패시 파일 삭제
			if((int)jsonMap.get("RETURN_CD") != HttpServletResponse.SC_OK){
				for(int i=0; i<fileList.size(); i++){
					fileUtil.deleteFile(request,fileList.get(i));
				}
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::insertBoard", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 공지사항 게시판 새글(게시글) 수정
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="updateBoard.do")
	public ModelAndView updateBoard(@RequestParam Map<String, Object> param, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		ArrayList<String> fileList = new ArrayList<String>();
		
		try{
			List<MultipartFile> files = request.getFiles("bFile");
			
			JSONArray jArray = new JSONArray();
			if(files != null && files.size() > 0 && !files.get(0).getOriginalFilename().equals("")){
				for(int i=0; i<files.size(); i++){
					MultipartFile file = files.get(i);
					
					String fileName = file.getOriginalFilename();
					long fileSize 	= file.getSize();
					String fileUrl 	= fileUtil.uploadFile(request, file, null, propertiesService.getString("Globals.board") + File.separator + param.get("boardCate"), false, true);
					
					
					JSONObject jObj = new JSONObject();
					jObj.put("boardCate", param.get("boardCate"));
					jObj.put("boardSeq", param.get("boardSeq"));
					jObj.put("fileName", fileName);
					jObj.put("fileUrl", fileUrl);
					jObj.put("fileSize", fileSize);
					jArray.add(jObj);
					
					fileList.add(fileUrl);
				}
			}
			
			param.put("fileList", jArray);
			
			String action = "/board/updateBoard.do";
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			//통신 실패시 파일 삭제
			if((int)jsonMap.get("RETURN_CD") != HttpServletResponse.SC_OK){
				for(int i=0; i<fileList.size(); i++){
					fileUtil.deleteFile(request, fileList.get(i));
				}
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::updateBoard", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 공지사항 게시판 새글(팝업글)수정
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="updateBoardPop.do")
	public ModelAndView updateBoardPop(@RequestParam Map<String, Object> param, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		ArrayList<String> fileList = new ArrayList<String>();
		String fileUrl = null;
		
		try{
			List<MultipartFile> files = request.getFiles("bFile");
			
			if(files != null && files.size() > 0 && !files.get(0).getOriginalFilename().equals("")){
				for(int i=0; i<files.size(); i++){
					MultipartFile file = files.get(i);
					
					//Globals.pop =>/upload/pop 에 저장을 시킨다. 
					fileUrl 	= fileUtil.uploadFile(request,file, null, propertiesService.getString("Globals.pop") + File.separator + param.get("boardCate"), false, true);
					
					fileList.add(fileUrl);
				}
			}
			
			param.put("fileUrl", fileUrl);
			
			String action = "/board/updateBoardPop.do";
			
			Map<String, Object> jsonMap = connService.connNiimToJson(action, param, request, response);
			
			//통신 실패시 파일 삭제
			if((int)jsonMap.get("RETURN_CD") != HttpServletResponse.SC_OK){
				for(int i=0; i<fileList.size(); i++){
					fileUtil.deleteFile(request, fileList.get(i));
				}
			}
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::updateBoard", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 공지사항 게시판 새글(게시글) 삭제
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value="deleteBoard.do")
	public ModelAndView deleteBoard(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/board/deleteBoard.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("isSuccess", jsonMap.get("isSuccess"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::deleteBoard", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/**
	 * 공지사항 파일 다운로드
	 * @param request
	 * @param response
	 * @param param
	 * @return ModelAndView
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="downloadAttachFile.do")
	public void downloadAttachFile(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/board/getAttachFile.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			Map<String, Object> map = (Map<String, Object>)jsonMap.get("map");
			
			HashMap<String, String> fileMap = new HashMap<String, String>();
			fileMap.put("userAgent"			, request.getHeader("User-Agent"));
			fileMap.put("realFileName"		, (String)map.get("fileUrl"));
			fileMap.put("originalFileName"	, (String)map.get("fileName"));
			
			fileUtil.downloadFile(request, response, fileMap);
			
		}catch(Exception e){
			log.error(this.getClass() + "::::downloadAttachFile", e);
		}
	}
	
	@RequestMapping(value="notice/boardFileUpload.do")
	public void baseInfoFileUpload(final MultipartHttpServletRequest multiRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		MultipartFile file = null;
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		while (itr.hasNext()) {
		    Entry<String, MultipartFile> entry = itr.next();
		    MultipartFile mFile = entry.getValue();
		    if (mFile.getName() != null && mFile.getName().trim().equals("uploadFile")) {
		    	file = mFile;
		    }
		}
		fileUtil.uploadFile(multiRequest, file, null, "/board" + File.separator + "101", false, false); 
	}
	
}
