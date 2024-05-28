package org.nii.niis.niim.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.nii.niis.niim.service.SearchAreaVO;
import org.nii.niis.niim.service.SpaceSearchService;
import org.nii.niis.niim.util.BlackList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/niimFor")
public class niimSpaceSearchController {

	/** UniSearchService */
    @Resource(name = "niimSpaceSearchService")
    private SpaceSearchService spaceSearchService;
    
    /**
     * 공간 검색
     * @return 검색 메인 좌측 페이지
     * @exception Exception
     */
	@RequestMapping(value="/search/searchSpaceList.do")
	public String viewSpaceSearchMain() 
			throws Exception {
		
    	return "/search/spaceSearchList";
	}
	
    /**
     * 시도 조회
     * @return 검색 메인 좌측 페이지
     * @exception Exception
     */
    @RequestMapping(value="/search/selectSidoList.do")
    public ModelAndView selectSidoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	List<SearchAreaVO> list = spaceSearchService.selectAreaListSido("");
    	modelAndView.addObject("sidolist", list);
    	return modelAndView;
    }

    /**
     * 시군구 조회
     * @return 검색 메인 좌측 페이지
     * @exception Exception
     */
    @RequestMapping(value="/search/selectSigunguList.do")
    public ModelAndView selectSigunguList(HttpServletRequest request, HttpServletResponse response, @Param("srch") String srch, ModelMap model) throws Exception {
		// 보안 코딩
    	srch = BlackList.getStrCnvrXss(srch);
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	List<SearchAreaVO> list = spaceSearchService.selectAreaListSigungu(srch);
    	modelAndView.addObject("sigungulist", list);
    	return modelAndView;
    }
    
    /**
     * 동 조회
     * @return 검색 메인 좌측 페이지
     * @exception Exception
     */
    @RequestMapping(value="/search/selectDongList.do")
    public ModelAndView selectDongList(HttpServletRequest request, HttpServletResponse response, @Param("srch") String srch, ModelMap model) throws Exception {
		// 보안 코딩
    	srch = BlackList.getStrCnvrXss(srch);
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	List<SearchAreaVO> list = spaceSearchService.selectAreaListDong(srch);
    	modelAndView.addObject("donglist", list);
    	return modelAndView;
    }
    
    /**
     * 동 조회
     * @return 검색 메인 좌측 페이지
     * @exception Exception
     */
    @RequestMapping(value="/search/selectLiList.do")
    public ModelAndView selectLiList(HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap<String, Object> param, ModelMap model) throws Exception {
    	ModelAndView modelAndView = new ModelAndView("jsonView");
    	List<HashMap<String, Object>> list = spaceSearchService.selectAreaListLi(param);
    	modelAndView.addObject("liList", list);
    	return modelAndView;
    }
}
