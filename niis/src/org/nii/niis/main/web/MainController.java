package org.nii.niis.main.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.main.web
* @FileName : MainController.java 
* @Date : 2016. 12. 19.
* @description : 메이화면, 팝업 controller 객체
* </pre>
*/
@Controller
@RequestMapping(value="/main/")
public class MainController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	
	/** 
	* <pre>
	* @Method : main
	* @Date : 2016. 12. 19.
	* @description : 메인화면 진입
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="main.do")
    public String main(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		return "main/main";
	}
	
	/** 
	* <pre>
	* @Method : getMainApply
	* @Date : 2016. 12. 19.
	* @description : 메인화면 신청서 정보 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getMainApply.do")
    public String getMainApply(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
			String action = "/main/getMainApply.do";
			param.put("usrMgno", request.getSession().getAttribute("aUserMgno"));
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("map", jsonMap.get("map"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getMainApply", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "main/mainApply";
	}
	
	/** 
	* <pre>
	* @Method : getMainNotice
	* @Date : 2016. 12. 19.
	* @description : 메인화면 공지사항 정보 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getMainNotice.do")
    public String getMainNotice(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		try{
			String action = "/main/getMainBoard.do";
			param.put("boardCate", "101");
			param.put("cnt", propertiesService.getInt("Globals.mainNoticeCnt"));
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			model.addAttribute("list", jsonMap.get("list"));
			model.addAttribute("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getMainNotice", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "main/mainNotice";
	}
	
	/** 
	* <pre>
	* @Method : getMainPopup
	* @Date : 2016. 12. 19.
	* @description : 메인화면 팝업 정보 조회
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="getMainPopup.do")
    public ModelAndView getMainPopup(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/main/getMainPopup.do";
			param.put("boardCate", "101");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getMainPopup", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	/** 
	* <pre>
	* @Method : openPopup
	* @Date : 2016. 12. 19.
	* @description : 메인화면 팝업 화면
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="openPopup.do")
    public String openPopup(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		String view = "main/popup/noticePopup";
		
		try{
			String popGbnCde = (String)param.get("popGbnCde");
			
			if(!"1".equals(popGbnCde)){
				view += popGbnCde;
			}
			
			String contents = String.valueOf(param.get("contents")).replaceAll("\n", "<br />");
			
			model.addAttribute("boardCate", param.get("boardCate"));
			model.addAttribute("boardSeq", param.get("boardSeq"));
			model.addAttribute("popGbnCde", popGbnCde);
			model.addAttribute("title", param.get("title"));
			model.addAttribute("contents", contents);
			model.addAttribute("imgUrl", param.get("imgUrl"));
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_OK);
		}catch(Exception e){
			log.error(this.getClass() + "::::openPopup", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return view;
	}
	
	/** 
	* <pre>
	* @Method : moveMenuConfirm
	* @Date : 2016. 12. 19.
	* @description : 메뉴 이동시 원본DB 목록 선택 내역 확인 팝업
	* </pre>
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="moveMenuConfirm.do")
    public String moveMenuConfirm(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		return "main/moveMenuConfirm";
	}
}
