package org.nii.niis.niim.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.niim.service.ManagementService;
import org.nii.niis.niim.service.ManagementServiceVO;
import org.nii.niis.niim.util.PagingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;

/**
 * Management controller 객체
 * 
 * @stereotype control
 */
@Controller
public class ManagementController {

	/**
	 * management controller 객체
	 * 
	 * @directed
	 */
	@Resource(name = "managementService")
	private ManagementService managementService;

	
	/**
	 * 통합관리 - 사업지구 관리/사업종류 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneType.do")
	public ModelAndView getZoneType(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		List<?> zoneType = null;
		
		zoneType = managementService.getZoneType();
		
		modelAndView.addObject("list", zoneType);
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneList.do")
	public ModelAndView getZoneList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		String zoneType = "";
		String sYear = "";
		String eYear = "";
		
		ManagementServiceVO maParam = new ManagementServiceVO();
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if (param != null){
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){ 
				String key = (String)iter.next(); 
				
				if (key.equals("zoneType")){
					zoneType = (String)param.get(key);
					if(zoneType.equals("전체")){
						zoneType = null;
					}
					maParam.setZoneType(zoneType);
				}
				if (key.equals("sYear")){
					sYear = (String)param.get(key);
					maParam.setsYear(sYear);
				}
				if (key.equals("eYear")){
					eYear = (String)param.get(key);
					maParam.seteYear(eYear);
				}
			}
		}
		
		PagingUtil.getInstance().setPageData(param, maParam, modelAndView, managementService.getZoneListCnt(maParam));
		
		List<?> zoneList = null;
		
		zoneList= managementService.getZoneList(maParam);
		
		modelAndView.addObject("list", zoneList);
		return modelAndView;
	}

	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneListExcel.do")
	public String getZoneListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		String zoneType = "";
		String sYear = "";
		String eYear = "";
		
		
		ManagementServiceVO maParam = new ManagementServiceVO();
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		BASE64Decoder decoder = new BASE64Decoder();
		
		if (param != null){
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){ 
				String key = (String)iter.next(); 
				
				if (key.equals("zoneType")){
					zoneType = (String)param.get(key);
					if(zoneType.equals("전체")){
						zoneType = null;
					}
					String zoneType2 = zoneType;
					maParam.setZoneType(zoneType2);
				}
				if (key.equals("sYear")){
					sYear = (String)param.get(key);
					maParam.setsYear(sYear);
				}
				if (key.equals("eYear")){
					eYear = (String)param.get(key);
					maParam.seteYear(eYear);
				}
			}
		}
		
		
		model.addAttribute("fileName", "사업지구목록");
		model.addAttribute("name", "사업지구목록");
		model.addAttribute("excelList", managementService.getZoneList(maParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getZoneListCols());
		
		return "excelView";
	}
	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getZoneListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();
	
		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("zoneCode::사업지구 코드");
		mapper.add("scale::대표축척");
		mapper.add("gsdVal::공간해상도");
		mapper.add("projectCde::사업종류");
	
		return mapper;
	}

	/**
	 * 통합관리 - 사업지구 관리/사업지구 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subResultZoneList.do")
	public ModelAndView getsubResultZoneList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
		List<?> zoneType = null;
		String zoneCode = (String)param.get("zoneCode");
		
		zoneType = managementService.getsubResultZoneList(zoneCode);
		
		modelAndView.addObject("list", zoneType);
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업연도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/yearListAir.do")
	public ModelAndView getYearList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getYearListAir();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/항공사진 코스번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/phCourseAir.do")
	public ModelAndView getPhCourseAir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				

		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getPhCourseAir(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업지구명(연도별) 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneNamAir.do")
	public ModelAndView getZoneNamAir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> airParam =  new HashMap<String, Object>();

		airParam.put("sYearAir", (String)param.get("sYearAir"));
		airParam.put("eYearAir", (String)param.get("eYearAir"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamAir(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}

	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getZoneNam.do")
	public ModelAndView getZoneNam(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> airParam =  new HashMap<String, Object>();

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNam();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사진번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/phNumAir.do")
	public ModelAndView getPhNumAir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		
		List<?> list = managementService.getPhNumAir(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 코스번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/phCourseOrient.do")
	public ModelAndView getPhCourseOrient(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				

		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getPhCourseOrient(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneNamOrient.do")
	public ModelAndView getZoneNamOrient(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> airParam =  new HashMap<String, Object>();

		airParam.put("sYearAir", (String)param.get("sYearAir"));
		airParam.put("eYearAir", (String)param.get("eYearAir"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamOrient(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사진번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/phNumOrient.do")
	public ModelAndView getPhNumOrient(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		
		List<?> list = managementService.getPhNumOrient(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 코스번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getPhCourseNirCmr.do")
	public ModelAndView getPhCourseNirCmr(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				

		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getPhCourseNirCmr(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 코스번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getPhCourseCmr.do")
	public ModelAndView getPhCourseCmr(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				

		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getPhCourseCmr(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getzoneNamCmr.do")
	public ModelAndView getzoneNamCmr(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> airParam =  new HashMap<String, Object>();

		airParam.put("sYearAir", (String)param.get("sYearAir"));
		airParam.put("eYearAir", (String)param.get("eYearAir"));
				
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getzoneNamCmr(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getzoneNamNirCmr.do")
	public ModelAndView getzoneNamNirCmr(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> nirParam =  new HashMap<String, Object>();

		nirParam.put("sYearNir", (String)param.get("sYearNir"));
		nirParam.put("eYearNir", (String)param.get("eYearNir"));
				
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getzoneNamNirCmr(nirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getOrientmapList.do")
	public ModelAndView getOrientmapList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		

		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));
		airParam.put("sYearAir", (String)param.get("sYearAir"));
		airParam.put("eYearAir", (String)param.get("eYearAir"));
		
		if(airParam.get("phCourse").equals("전체")){
			airParam.put("phCourse", null);
		}
		if(airParam.get("phNum").equals("전체")){
			airParam.put("phNum", null);
		}
		
		PagingUtil.getInstance().setPageData(param, airParam, modelAndView, managementService.getOrientmapListCnt(airParam));
		
		List<?> list = managementService.getOrientmapList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getOrientmapListExcel.do")
	public String getOrientmapListExcel(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));
		airParam.put("sYearAir", (String)param.get("sYearAir"));
		airParam.put("eYearAir", (String)param.get("eYearAir"));
		
		if(airParam.get("phCourse").equals("전체")){
			airParam.put("phCourse", null);
		}
		if(airParam.get("phNum").equals("전체")){
			airParam.put("phNum", null);
		}
		
		model.addAttribute("fileName", "사진주점목록 목록");
		model.addAttribute("name", "사진주점목록 목록");
		model.addAttribute("excelList",  managementService.getOrientmapList(airParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getOrientmapListCols());
		
		return "excelView";
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getOrientmapListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("phCourse::코스번호");
		mapper.add("phNum::사진번호");
		mapper.add("mapNum::도엽번호");
		mapper.add("mapNam::도엽명");
		mapper.add("coorX::주점 X좌표");
		mapper.add("coorY::주점 Y좌표");
		mapper.add("origin::원점구분");
		mapper.add("pCrsIdn::지리좌표계");

		return mapper;
	}
	
	/**
	 * 통합관리 - NIR 관리/사진주점 관리 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getOrientmapNirListExcel.do")
	public String getOrientmapNirListExcel(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		
		nirParam.put("zoneCode", (String)param.get("zoneCode"));
		nirParam.put("phCourse", (String)param.get("phCourse"));
		nirParam.put("phNum", (String)param.get("phNum"));
		nirParam.put("sYearAir", (String)param.get("sYearAir"));
		nirParam.put("eYearAir", (String)param.get("eYearAir"));
		
		if(nirParam.get("phCourse").equals("전체")){
			nirParam.put("phCourse", null);
		}
		if(nirParam.get("phNum").equals("전체")){
			nirParam.put("phNum", null);
		}
		
		model.addAttribute("fileName", "사진주점목록 목록");
		model.addAttribute("name", "사진주점목록 목록");
		model.addAttribute("excelList",  managementService.getOrientmapNirList(nirParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getOrientmapListCols());
		
		return "excelView";
	}
	
	/**
	 * 통합관리 - NIR 관리/사진주점 관리 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getOrientmapNirListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("phCourse::코스번호");
		mapper.add("phNum::사진번호");
		mapper.add("mapNum::도엽번호");
		mapper.add("mapNam::도엽명");
		mapper.add("coorX::주점 X좌표");
		mapper.add("coorY::주점 Y좌표");
		mapper.add("origin::원점구분");
		mapper.add("pCrsIdn::지리좌표계");

		return mapper;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/subOrientmapList.do")
	public ModelAndView getSubOrientmapList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));

		List<?> list = managementService.getSubOrientmapList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustZone.do")
	public ModelAndView updateAdjustZone(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("zoneNam", (String)param.get("zoneNam"));
		airParam.put("zoneYy", (String)param.get("zoneYy"));
		airParam.put("prjNam", (String)param.get("prjNam"));
		airParam.put("gsdVal", (String)param.get("gsdVal"));
		airParam.put("scale", (String)param.get("scale"));
		airParam.put("demGrid", (String)param.get("demGrid"));
		airParam.put("remark", (String)param.get("remark"));
		airParam.put("prjInfoMgno", (String)param.get("prjInfoMgno"));
		airParam.put("projecttypeCde", (String)param.get("projecttypeCde"));
		airParam.put("imageCde", (String)param.get("imageCde"));
		airParam.put("cameraCde", (String)param.get("cameraCde"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustZone(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertZone.do")
	public ModelAndView insertZone(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("zoneNam", (String)param.get("zoneNam"));
		airParam.put("zoneYy", (String)param.get("zoneYy"));
		airParam.put("prjNam", (String)param.get("prjNam"));
		airParam.put("gsdVal", (String)param.get("gsdVal"));
		airParam.put("scale", (String)param.get("scale"));
		airParam.put("demGrid", (String)param.get("demGrid"));
		airParam.put("remark", (String)param.get("remark"));
		airParam.put("prjInfoMgno", (String)param.get("prjInfoMgno"));
		airParam.put("projecttypeCde", (String)param.get("projecttypeCde"));
		airParam.put("imageCde", (String)param.get("imageCde"));
		airParam.put("cameraCde", (String)param.get("cameraCde"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		String list = managementService.insertZone(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delZone.do")
	public ModelAndView delZone(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-") || zoneCode[i].equals("") ) {
					zoneCode[i] = null;
				}
				int list = managementService.delZone(zoneCode[i]);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustOrientmap.do")
	public ModelAndView updateAdjustOrientmap(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));
		airParam.put("pyojungId", (String)param.get("pyojungId"));
		airParam.put("mapNum", (String)param.get("mapNum"));
		airParam.put("mapNam", (String)param.get("mapNam"));
		airParam.put("coorX", (String)param.get("coorX"));
		airParam.put("coorY", (String)param.get("coorY"));
		airParam.put("coorCx", (String)param.get("coorCx"));
		airParam.put("coorCy", (String)param.get("coorCy"));
		airParam.put("pCoorX", (String)param.get("pCoorX"));
		airParam.put("PcoorY", (String)param.get("PcoorY"));
		airParam.put("pCrsIdn", (String)param.get("pCrsIdn"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}

		int list = managementService.updateAdjustOrientmap(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 보안등급  조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getSecurityCode.do")
	public ModelAndView getSecurityCode(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSecurityCode();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 카메라 구분 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getCameraCode.do")
	public ModelAndView getCameraCode(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getCameraCode();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 해상도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/ResolutionAir.do")
	public ModelAndView getResolutionAir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		String zoneCode = "전체".equals(param.get("zoneCode"))? "" : (String)param.get("zoneCode");
		String phCourse = "전체".equals(param.get("phCourse"))? "" : (String)param.get("phCourse");
		String phNum = "전체".equals(param.get("phNum"))? "" : (String)param.get("phNum");
				
		airParam.put("zoneCode", zoneCode);
		airParam.put("phCourse", phCourse);
		airParam.put("phNum", phNum);
				
		List<?> list = managementService.getResolutionAir(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/productAirList.do")
	public ModelAndView getProductAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		try{
			airParam.put("zoneCode", (String)param.get("zoneCode"));
			airParam.put("phCourse", (String)param.get("phCourse"));
			airParam.put("phNum", (String)param.get("phNum"));
			airParam.put("sYearAir", (String)param.get("sYearAir"));
			airParam.put("eYearAir", (String)param.get("eYearAir"));
			airParam.put("securityCde", (String)param.get("securityCde"));
			airParam.put("cameraCde", (String)param.get("cameraCde"));
			airParam.put("resolution", (String)param.get("resolution"));
			
			if(airParam.get("phCourse").equals("전체")){
				airParam.put("phCourse", null);
			}
			if(airParam.get("phNum").equals("전체")){
				airParam.put("phNum", null);
			}
			if(airParam.get("securityCde").equals("전체")){
				airParam.put("securityCde", null);
			}
			if(airParam.get("cameraCde").equals("전체")){
				airParam.put("cameraCde", null);
			}
			if(airParam.get("resolution").equals("전체")){
				airParam.put("resolution", null);
			}
			
			PagingUtil.getInstance().setPageData(param, airParam, modelAndView, managementService.getProductAirListCnt(airParam));
			
			List<?> list = managementService.getProductAirList(airParam);
			modelAndView.addObject("list", list);
		}catch(Exception e)	{e.printStackTrace();}
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/productAirListExcel.do")
	public String productAirListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> airParam =  new HashMap<String, Object>();
		

		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));
		airParam.put("sYearAir", (String)param.get("sYearAir"));
		airParam.put("eYearAir", (String)param.get("eYearAir"));
		airParam.put("securityCde", (String)param.get("securityCde"));
		airParam.put("cameraCde", (String)param.get("cameraCde"));
		airParam.put("resolution",  (String)param.get("resolution"));
		
		if(airParam.get("phCourse").equals("전체")){
			airParam.put("phCourse", null);
		}
		if(airParam.get("phNum").equals("전체")){
			airParam.put("phNum", null);
		}
		if(airParam.get("securityCde").equals("전체")){
			airParam.put("securityCde", null);
		}
		if(airParam.get("cameraCde").equals("전체")){
			airParam.put("cameraCde", null);
		}
		if(airParam.get("resolution").equals("전체")){
			airParam.put("resolution", null);
		}
		
		model.addAttribute("fileName", "성과관리목록");
		model.addAttribute("name", "성과관리목록");
		model.addAttribute("excelList", managementService.getProductAirList(airParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getProductAirListCols());
		
		return "excelView";
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */	
	public List<String> getProductAirListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("cameraCde::카메라구분");
		mapper.add("phCourse::코스번호");
		mapper.add("phNum::사진번호");
		mapper.add("securityCde::지리정보등급");
		mapper.add("aphNum::항공사진수량");
		mapper.add("filmNum::양화필름수량");
		mapper.add("rphNum::성과사진수량");
		mapper.add("nixOx::저해상도 파일유무");
		mapper.add("scurOx::보안지역처리");
		mapper.add("resolution::해상도");

		return mapper;
	}
	
	/**
	 * 통합관리 - NIR 관리/성과 관리 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/productNirListExcel.do")
	public String productNirListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		

		nirParam.put("zoneCode", (String)param.get("zoneCode"));
		nirParam.put("phCourse", (String)param.get("phCourse"));
		nirParam.put("phNum", (String)param.get("phNum"));
		nirParam.put("sYearNir", (String)param.get("sYearNir"));
		nirParam.put("eYearNir", (String)param.get("eYearNir"));
		nirParam.put("securityCde", (String)param.get("securityCde"));
		nirParam.put("cameraCde", (String)param.get("cameraCde"));
		nirParam.put("resolution", (String)param.get("resolution"));
		
		if(nirParam.get("phCourse").equals("전체")){
			nirParam.put("phCourse", null);
		}
		if(nirParam.get("phNum").equals("전체")){
			nirParam.put("phNum", null);
		}
		if(nirParam.get("securityCde").equals("전체")){
			nirParam.put("securityCde", null);
		}
		if(nirParam.get("cameraCde").equals("전체")){
			nirParam.put("cameraCde", null);
		}
		if(nirParam.get("resolution").equals("전체")){
			nirParam.put("resolution", null);
		}
		
		model.addAttribute("fileName", "성과관리목록");
		model.addAttribute("name", "성과관리목록");
		model.addAttribute("excelList", managementService.getProductNirList(nirParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getProductNirListCols());
		
		return "excelView";
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */	
	public List<String> getProductNirListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("cameraCde::카메라구분");
		mapper.add("phCourse::코스번호");
		mapper.add("phNum::사진번호");
		mapper.add("securityCde::지리정보등급");
		mapper.add("aphNum::항공사진수량");
		mapper.add("filmNum::양화필름수량");
		mapper.add("rphNum::성과사진수량");
		mapper.add("nixOx::저해상도 파일유무");
		mapper.add("scurOx::보안지역처리");
		mapper.add("resolution::해상도");

		return mapper;
	}

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/subProductAirList.do")
	public ModelAndView subProductAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));

		List<?> list = managementService.getSubProductAirList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회(외부표정요소)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/subProductEOAirList.do")
	public ModelAndView subProductEOAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));

		List<?> list = managementService.getSubProductEOAirList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/subMetaAirList.do")
	public ModelAndView subMetaAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));

		List<?> list = managementService.getSubMetaAirList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(외부표정요소)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertProductEOAirList.do")
	public ModelAndView insertProductEOAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("eoX"         ,(String)param.get("eoX"));
		airParam.put("eoY"         ,(String)param.get("eoY"));
		airParam.put("eoZ",(String)param.get("eoZ"));
		airParam.put("eoOmega",(String)param.get("eoOmega"));          
		airParam.put("eoPhi",(String)param.get("eoPhi"));
		airParam.put("eoKappa",(String)param.get("eoKappa"));
		airParam.put("crsIdn",(String)param.get("crsIdn"));
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		String list = managementService.insertProductEOAirList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(외부표정요소)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertProductEONirList.do")
	public ModelAndView insertProductEONirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		
		nirParam.put("eoX"         ,(String)param.get("eoX"));
		nirParam.put("eoY"         ,(String)param.get("eoY"));
		nirParam.put("eoZ",(String)param.get("eoZ"));
		nirParam.put("eoOmega",(String)param.get("eoOmega"));          
		nirParam.put("eoPhi",(String)param.get("eoPhi"));
		nirParam.put("eoKappa",(String)param.get("eoKappa"));
		nirParam.put("crsIdn",(String)param.get("crsIdn"));
		nirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		nirParam.put("phCourse"         ,(String)param.get("phCourse"));
		nirParam.put("phNum"            ,(String)param.get("phNum"));
		
		if (nirParam != null) {
			Iterator<String> iter = nirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (nirParam.get(key).equals("-") || nirParam.get(key).equals("")) {
					nirParam.put(key, null);
				}
			}
		}


		String list = managementService.insertProductEONirList(nirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertNirBasemetaDts.do")
	public ModelAndView insertNirBasemetaDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		
		nirParam.put("resOfficename"    ,(String)param.get("resOfficename"));    
		nirParam.put("metaDate"         ,(String)param.get("metaDate"));
		nirParam.put("metaVersion"      ,(String)param.get("metaVersion"));
		nirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		nirParam.put("phCourse"         ,(String)param.get("phCourse"));
		nirParam.put("phNum"            ,(String)param.get("phNum"));
		nirParam.put("constCo"            ,(String)param.get("constCo"));
		nirParam.put("inputCo"            ,(String)param.get("inputCo"));
		nirParam.put("spatialData"      ,(String)param.get("spatialData"));
		nirParam.put("distFormatName"   ,(String)param.get("distFormatName"));
		nirParam.put("distFormatVersion",(String)param.get("distFormatVersion"));
		nirParam.put("distOfficename"   ,(String)param.get("distOfficename"));
		nirParam.put("distPostname"     ,(String)param.get("distPostname"));
		nirParam.put("distPhone"        ,(String)param.get("distPhone"));
		nirParam.put("qualityTarget"    ,(String)param.get("qualityTarget"));
		nirParam.put("scan"             ,(String)param.get("scan"));
		nirParam.put("film"             ,(String)param.get("film"));
		nirParam.put("document"         ,(String)param.get("document"));
		nirParam.put("securityCde"      ,(String)param.get("securityCde"));
		nirParam.put("fileIdentifier"   ,(String)param.get("fileIdentifier"));
		nirParam.put("language"         ,(String)param.get("language"));  
		nirParam.put("targetName"       ,(String)param.get("targetName"));       
		nirParam.put("resPostname"      ,(String)param.get("resPostname"));
		nirParam.put("resPhone"         ,(String)param.get("resPhone"));         
		nirParam.put("resFax"           ,(String)param.get("resFax"));
		nirParam.put("resAddress"       ,(String)param.get("resAddress"));
		nirParam.put("resPostalCode"    ,(String)param.get("resPostalCode"));
		nirParam.put("resolution"       ,(String)param.get("resolution"));
		                                
		
		
		if (nirParam != null) {
			Iterator<String> iter = nirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (nirParam.get(key).equals("-") || nirParam.get(key).equals("")) {
					nirParam.put(key, null);
				}
			}
		}

		String list1 = managementService.insertNirBasemetaDts(nirParam);
		String list2 = managementService.insertMetaNirmapIdent(nirParam);
		String list3 = managementService.insertMetaNirmapContr(nirParam);

		modelAndView.addObject("list1", list1);
		modelAndView.addObject("list2", list2);
		modelAndView.addObject("list3", list3);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertAirBasemetaDts.do")
	public ModelAndView insertAirBasemetaDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("resOfficename"    ,(String)param.get("resOfficename"));    
		airParam.put("metaDate"         ,(String)param.get("metaDate"));
		airParam.put("metaVersion"      ,(String)param.get("metaVersion"));
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		airParam.put("constCo"            ,(String)param.get("constCo"));
		airParam.put("inputCo"            ,(String)param.get("inputCo"));
		airParam.put("spatialData"      ,(String)param.get("spatialData"));
		airParam.put("distFormatName"   ,(String)param.get("distFormatName"));
		airParam.put("distFormatVersion",(String)param.get("distFormatVersion"));
		airParam.put("distOfficename"   ,(String)param.get("distOfficename"));
		airParam.put("distPostname"     ,(String)param.get("distPostname"));
		airParam.put("distPhone"        ,(String)param.get("distPhone"));
		airParam.put("qualityTarget"    ,(String)param.get("qualityTarget"));
		airParam.put("scan"             ,(String)param.get("scan"));
		airParam.put("film"             ,(String)param.get("film"));
		airParam.put("document"         ,(String)param.get("document"));
		airParam.put("securityCde"      ,(String)param.get("securityCde"));
		airParam.put("fileIdentifier"   ,(String)param.get("fileIdentifier"));
		airParam.put("language"         ,(String)param.get("language"));  
		airParam.put("targetName"       ,(String)param.get("targetName"));       
		airParam.put("resPostname"      ,(String)param.get("resPostname"));
		airParam.put("resPhone"         ,(String)param.get("resPhone"));         
		airParam.put("resFax"           ,(String)param.get("resFax"));
		airParam.put("resAddress"       ,(String)param.get("resAddress"));
		airParam.put("resPostalCode"    ,(String)param.get("resPostalCode"));
		airParam.put("resolution"       ,(String)param.get("resolution"));
		                                
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}

		String list1 = managementService.insertAirBasemetaDts(airParam);
		String list2 = managementService.insertMetaAirmapIdent(airParam);
		String list3 = managementService.insertMetaAirmapContr(airParam);

		modelAndView.addObject("list1", list1);
		modelAndView.addObject("list2", list2);
		modelAndView.addObject("list3", list3);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록/렌즈 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */		
	@RequestMapping(value = "/management/insertlensNum.do")
	public ModelAndView insertlensNum(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();

		List<?> list = managementService.insertlensNum();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록/촬영일자 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertPhDate.do")
	public ModelAndView insertphDate(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		List<?> list = managementService.insertphDate(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustAirBasemetaDts.do")
	public ModelAndView updateAdjustAirBasemetaDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("resOfficename"    ,(String)param.get("resOfficename"));    
		airParam.put("metaDate"         ,(String)param.get("metaDate"));
		airParam.put("metaVersion"      ,(String)param.get("metaVersion"));
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		airParam.put("spatialData"      ,(String)param.get("spatialData"));
		airParam.put("distFormatName"   ,(String)param.get("distFormatName"));
		airParam.put("distFormatVersion",(String)param.get("distFormatVersion"));
		airParam.put("distOfficename"   ,(String)param.get("distOfficename"));
		airParam.put("distPostname"     ,(String)param.get("distPostname"));
		airParam.put("distPhone"        ,(String)param.get("distPhone"));
		airParam.put("qualityTarget"    ,(String)param.get("qualityTarget"));
		airParam.put("scan"             ,(String)param.get("scan"));
		airParam.put("film"             ,(String)param.get("film"));
		airParam.put("document"         ,(String)param.get("document"));
		                                
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustAirBasemetaDts(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/항공사진 성과 상세 목록 수정(식별정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustMetaAirmapIdent.do")
	public ModelAndView updateAdjustMetaAirmapIdent(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> AirParam =  new HashMap<String, Object>();
		
		AirParam.put("fileIdentifier"   ,(String)param.get("fileIdentifier"));
		AirParam.put("language"         ,(String)param.get("language"));  
		AirParam.put("targetName"       ,(String)param.get("targetName"));       
		AirParam.put("resPostname"      ,(String)param.get("resPostname"));
		AirParam.put("resPhone"         ,(String)param.get("resPhone"));         
		AirParam.put("resFax"           ,(String)param.get("resFax"));
		AirParam.put("resAddress"       ,(String)param.get("resAddress"));
		AirParam.put("resPostalCode"    ,(String)param.get("resPostalCode"));
		AirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		AirParam.put("phCourse"         ,(String)param.get("phCourse"));
		AirParam.put("phNum"            ,(String)param.get("phNum"));
		AirParam.put("resolution"       ,(String)param.get("resolution"));
		
		if (AirParam != null) {
			Iterator<String> iter = AirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (AirParam.get(key).equals("-")) {
					AirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustMetaAirmapIdent(AirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(축척)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustAirZoneDts.do")
	public ModelAndView updateAdjustAirZoneDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		airParam.put("scale"            ,(String)param.get("scale"));
		                                
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustAirZoneDts(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(제약정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustMetaAirmapContr.do")
	public ModelAndView updateAdjustMetaAirmapContr(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		airParam.put("securityCde"      ,(String)param.get("securityCde"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustMetaAirmapContr(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustAirOrientmapDts.do")
	public ModelAndView updateAdjustAirOrientmapDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		airParam.put("map_num"          ,(String)param.get("map_num"));
		airParam.put("map_nam"          ,(String)param.get("map_nam"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustAirOrientmapDts(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(획득정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustMetaAirmapAcqut.do")
	public ModelAndView updateAdjustMetaAirmapAcqut(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		airParam.put("cmrType"          ,(String)param.get("cmrType"));          
		airParam.put("cmrNum"           ,(String)param.get("cmrNum"));
		airParam.put("pilot"            ,(String)param.get("pilot"));
		airParam.put("cmrMan"           ,(String)param.get("cmrMan"));
		airParam.put("notePlane"        ,(String)param.get("notePlane"));
		airParam.put("lensNum"          ,(String)param.get("lensNum"));
		airParam.put("phDate"           ,(String)param.get("phDate"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustMetaAirmapAcqut(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getAirNoteList.do")
	public ModelAndView getAirNoteList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("filmNum"         ,(String)param.get("filmNum"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(airParam.get("phCourse").equals("전체")){
			airParam.put("phCourse", null);
		}
		if(airParam.get("filmNum").equals("전체")){
			airParam.put("filmNum", null);
		}
		
		PagingUtil.getInstance().setPageData(param, airParam, modelAndView, managementService.getAirNoteListCnt(airParam));
		
		List<?> zoneList = null;
		
		zoneList= managementService.getAirNoteList(airParam);
		
		modelAndView.addObject("list", zoneList);
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getAirNoteListExcel.do")
	public String getAirNoteListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("filmNum"         ,(String)param.get("filmNum"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(airParam.get("phCourse").equals("전체")){
			airParam.put("phCourse", null);
		}
		if(airParam.get("filmNum").equals("전체")){
			airParam.put("filmNum", null);
		}
		
		
		model.addAttribute("fileName", "촬영기록부 목록");
		model.addAttribute("name", "촬영기록부 목록");
		model.addAttribute("excelList",  managementService.getAirNoteList(airParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getAirNoteListCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */	
	public List<String> getAirNoteListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("phDate::촬영일자");
		mapper.add("lensNum::렌즈번호");
		mapper.add("phCourse::코스번호");
		mapper.add("phNum::사진번호");
		mapper.add("filmNum::필름번호");

		return mapper;
	}
	
	/**
	 * 통합관리 - NIR 관리/촬영기록부 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getNirNoteListExcel.do")
	public String getNirNoteListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> nirParam =  new HashMap<String, Object>();
		
		nirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		nirParam.put("phCourse"         ,(String)param.get("phCourse"));
		nirParam.put("filmNum"         ,(String)param.get("filmNum"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(nirParam.get("phCourse").equals("전체")){
			nirParam.put("phCourse", null);
		}
		if(nirParam.get("filmNum").equals("전체")){
			nirParam.put("filmNum", null);
		}
		
		
		model.addAttribute("fileName", "촬영기록부 목록");
		model.addAttribute("name", "촬영기록부 목록");
		model.addAttribute("excelList",  managementService.getNirNoteList(nirParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getNirNoteListCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - NIR 관리/촬영기록부 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */	
	public List<String> getNirNoteListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("phDate::촬영일자");
		mapper.add("lensNum::렌즈번호");
		mapper.add("phCourse::코스번호");
		mapper.add("phNum::사진번호");
		mapper.add("filmNum::필름번호");

		return mapper;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 필름 번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/filmNumAir.do")
	public ModelAndView getFilmNumAir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		
		if(airParam.get("phCourse").equals("전체")){
			return modelAndView;
		}
		
		List<?> list = managementService.getFilmNumAir(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록 필름번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getFilmNumInsert.do")
	public ModelAndView getFilmNumInsert(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phDate", (String)param.get("phDate"));
		airParam.put("lensNum", (String)param.get("lensNum"));
		airParam.put("courseCount", (String)param.get("courseCount"));
		
	
		List<?> list = managementService.getFilmNumInsert(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 상세 목록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/subAirNoteList.do")
	public ModelAndView subAirNoteList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("filmNum", (String)param.get("filmNum"));
		
	
		List<?> list = managementService.getSubAirNoteList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustAirNoteList.do")
	public ModelAndView updateAdjustAirNoteList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneYy"         ,(String)param.get("zoneYy"));
		airParam.put("zoneNam"         ,(String)param.get("zoneNam"));
		airParam.put("phDate"            ,(String)param.get("phDate"));
		airParam.put("noteDate"          ,(String)param.get("noteDate"));          
		airParam.put("lensNum"           ,(String)param.get("lensNum"));
		airParam.put("phCourse"            ,(String)param.get("phCourse"));
		airParam.put("phNum"           ,(String)param.get("phNum"));
		airParam.put("filmNum"        ,(String)param.get("filmNum"));
		airParam.put("rptMan"          ,(String)param.get("rptMan"));
		airParam.put("courseCount"           ,(String)param.get("courseCount"));
		airParam.put("phDrct"           ,(String)param.get("phDrct"));
		airParam.put("phAngle"           ,(String)param.get("phAngle"));
		airParam.put("windDirect"           ,(String)param.get("windDirect"));
		airParam.put("windSpeed"           ,(String)param.get("windSpeed"));
		airParam.put("zoneCode"           ,(String)param.get("zoneCode"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustAirNoteDts(airParam);
		int list2 = managementService.updateAdjustAirNotecourseDts(airParam);
		int list3 = managementService.updateAdjustAirCoursetestDts(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertAirNoteList.do")
	public ModelAndView insertAirNoteList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("phDate"            ,(String)param.get("phDate"));
		airParam.put("noteDate"          ,(String)param.get("noteDate"));          
		airParam.put("lensNum"           ,(String)param.get("lensNum"));
		airParam.put("phCourse"            ,(String)param.get("phCourse"));
		airParam.put("phNum"           ,(String)param.get("phNum"));
		airParam.put("filmNum"        ,(String)param.get("filmNum"));
		airParam.put("rptMan"          ,(String)param.get("rptMan"));
		airParam.put("courseCount"           ,(String)param.get("courseCount"));
		airParam.put("phDrct"           ,(String)param.get("phDrct"));
		airParam.put("phAngle"           ,(String)param.get("phAngle"));
		airParam.put("windDirect"           ,(String)param.get("windDirect"));
		airParam.put("windSpeed"           ,(String)param.get("windSpeed"));
		airParam.put("zoneCode"           ,(String)param.get("zoneCode"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		String list = managementService.insertAirNoteDts(airParam);
		String list2 = managementService.insertAirNotecourseDts(airParam);
		String list3 = managementService.insertAirCoursetestDts(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/촬영기록부 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertNirNoteList.do")
	public ModelAndView insertNirNoteList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		
		nirParam.put("phDate"            ,(String)param.get("phDate"));
		nirParam.put("noteDate"          ,(String)param.get("noteDate"));          
		nirParam.put("lensNum"           ,(String)param.get("lensNum"));
		nirParam.put("phCourse"            ,(String)param.get("phCourse"));
		nirParam.put("phNum"           ,(String)param.get("phNum"));
		nirParam.put("filmNum"        ,(String)param.get("filmNum"));
		nirParam.put("rptMan"          ,(String)param.get("rptMan"));
		nirParam.put("courseCount"           ,(String)param.get("courseCount"));
		nirParam.put("phDrct"           ,(String)param.get("phDrct"));
		nirParam.put("phAngle"           ,(String)param.get("phAngle"));
		nirParam.put("windDirect"           ,(String)param.get("windDirect"));
		nirParam.put("windSpeed"           ,(String)param.get("windSpeed"));
		nirParam.put("zoneCode"           ,(String)param.get("zoneCode"));
		
		
		if (nirParam != null) {
			Iterator<String> iter = nirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (nirParam.get(key).equals("-") || nirParam.get(key).equals("")) {
					nirParam.put(key, null);
				}
			}
		}


		String list = managementService.insertNirNoteDts(nirParam);
		String list2 = managementService.insertNirNotecourseDts(nirParam);
		String list3 = managementService.insertNirCoursetestDts(nirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 카메라구분, 보유업체 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getAirCameraDts.do")
	public ModelAndView getAirCameraDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getAirCameraDts();
		List<?> list2 = managementService.getAirkeepCmpn();
		
		modelAndView.addObject("list", list);
		modelAndView.addObject("list2", list2);
		
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 렌즈번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getlensNum.do")
	public ModelAndView getlensNum(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getlensNum();
		
		modelAndView.addObject("list", list);
		
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getAirCameraDtsList.do")
	public ModelAndView getAirCameraDtsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("camCde", (String)param.get("camCde"));
		airParam.put("keepCmpn", (String)param.get("keepCmpn"));
		
		if(airParam.get("camCde").equals("전체")){
			airParam.put("camCde", null);
		}
		if(airParam.get("keepCmpn").equals("전체")){
			airParam.put("keepCmpn", null);
		}
		
		PagingUtil.getInstance().setPageData(param, airParam, modelAndView, managementService.getAirCameraDtsListCnt(airParam));
		
		List<?> list = managementService.getAirCameraDtsList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getAirCameraDtsListExcel.do")
	public String getAirCameraDtsListExcel(Model model,@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("camCde", (String)param.get("camCde"));
		airParam.put("keepCmpn", (String)param.get("keepCmpn"));
		
		if(airParam.get("camCde").equals("전체")){
			airParam.put("camCde", null);
		}
		if(airParam.get("keepCmpn").equals("전체")){
			airParam.put("keepCmpn", null);
		}
		
		model.addAttribute("fileName", "GPS기준국 목록");
		model.addAttribute("name", "GPS기준국 목록");
		model.addAttribute("excelList",  managementService.getAirCameraDtsList(airParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getAirCameraDtsListCols());
		
		return "excelView";
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */	
	public List<String> getAirCameraDtsListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("lensNum::렌즈번호");
		mapper.add("focusDis::초점거리");
		mapper.add("camCde::카메라구분");
		mapper.add("cmrCmpn::카메라제작사");
		mapper.add("calDate::측정일자");
		mapper.add("keepCmpn::보유회사");
		mapper.add("fmc::FMC기능여부");
		mapper.add("fmcDate::FMC도입일");
		mapper.add("cmrType::카메라종류");
		mapper.add("page1::파일경로");

		return mapper;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustAirCameraDts.do")
	public ModelAndView updateAdjustAirCameraDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("lensNum"         ,(String)param.get("lensNum"));
		airParam.put("focusDis"         ,(String)param.get("focusDis"));
		airParam.put("camCde",(String)param.get("camCde"));
		airParam.put("cmrCmpn",(String)param.get("cmrCmpn"));          
		airParam.put("calDate",(String)param.get("calDate"));
		airParam.put("keepCmpn",(String)param.get("keepCmpn"));
		airParam.put("fmc",(String)param.get("fmc"));
		airParam.put("fmcDate",(String)param.get("fmcDate"));
		airParam.put("cmrType",(String)param.get("cmrType"));
		airParam.put("page_1",(String)param.get("page1"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustAirCameraDts(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertAirCameraDts.do")
	public ModelAndView insertAirCameraDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("lensNum"         ,(String)param.get("lensNum"));
		airParam.put("focusDis"         ,(String)param.get("focusDis"));
		airParam.put("camCde",(String)param.get("camCde"));
		airParam.put("cmrCmpn",(String)param.get("cmrCmpn"));          
		airParam.put("calDate",(String)param.get("calDate"));
		airParam.put("keepCmpn",(String)param.get("keepCmpn"));
		airParam.put("fmc",(String)param.get("fmc"));
		airParam.put("fmcDate",(String)param.get("fmcDate"));
		airParam.put("page1",(String)param.get("page1"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		String list = managementService.insertAirCameraDts(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과관리 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustProductAirList.do")
	public ModelAndView updateAdjustProductAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("aphNum"         ,(String)param.get("aphNum"));
		airParam.put("aphNow"         ,(String)param.get("aphNow"));
		airParam.put("filmNum",(String)param.get("filmNum"));
		airParam.put("filmNow",(String)param.get("filmNow"));          
		airParam.put("rphNum",(String)param.get("rphNum"));
		airParam.put("rphNow",(String)param.get("rphNow"));
		airParam.put("md1Num",(String)param.get("md1Num"));
		airParam.put("md2Num",(String)param.get("md2Num"));
		airParam.put("remark",(String)param.get("remark"));
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustProductAirList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과관리 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertProductAirList.do")
	public ModelAndView insertProductAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("aphNum"         ,(String)param.get("aphNum"));
		airParam.put("aphNow"         ,(String)param.get("aphNow"));
		airParam.put("filmNum",(String)param.get("filmNum"));
		airParam.put("filmNow",(String)param.get("filmNow"));          
		airParam.put("rphNum",(String)param.get("rphNum"));
		airParam.put("rphNow",(String)param.get("rphNow"));
		airParam.put("md1Num",(String)param.get("md1Num"));
		airParam.put("md2Num",(String)param.get("md2Num"));
		airParam.put("remark",(String)param.get("remark"));
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		String list = managementService.insertProductAirList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과관리 수정(외부표정요소)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/adjustProductEOAirList.do")
	public ModelAndView updateAdjustProductEOAirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("eoX"         ,(String)param.get("eoX"));
		airParam.put("eoY"         ,(String)param.get("eoY"));
		airParam.put("eoZ",(String)param.get("eoZ"));
		airParam.put("eoOmega",(String)param.get("eoOmega"));          
		airParam.put("eoPhi",(String)param.get("eoPhi"));
		airParam.put("eoKappa",(String)param.get("eoKappa"));
		airParam.put("crsIdn",(String)param.get("crsIdn"));
		airParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		airParam.put("phCourse"         ,(String)param.get("phCourse"));
		airParam.put("phNum"            ,(String)param.get("phNum"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustProductEOAirList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - NIR 관리/성과관리 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertProductNirList.do")
	public ModelAndView insertProductNirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		
		nirParam.put("aphNum"         ,(String)param.get("aphNum"));
		nirParam.put("aphNow"         ,(String)param.get("aphNow"));
		nirParam.put("filmNum",(String)param.get("filmNum"));
		nirParam.put("filmNow",(String)param.get("filmNow"));          
		nirParam.put("rphNum",(String)param.get("rphNum"));
		nirParam.put("rphNow",(String)param.get("rphNow"));
		nirParam.put("md1Num",(String)param.get("md1Num"));
		nirParam.put("md2Num",(String)param.get("md2Num"));
		nirParam.put("remark",(String)param.get("remark"));
		nirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		nirParam.put("phCourse"         ,(String)param.get("phCourse"));
		nirParam.put("phNum"            ,(String)param.get("phNum"));
		
		
		if (nirParam != null) {
			Iterator<String> iter = nirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (nirParam.get(key).equals("-") || nirParam.get(key).equals("")) {
					nirParam.put(key, null);
				}
			}
		}


		String list = managementService.insertProductNirList(nirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/zoneNamGPS.do")
	public ModelAndView getZoneNamGPS(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamGPS();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 점의번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getGpsBpIdn.do")
	public ModelAndView getGpsBpIdn(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> gpsParam =  new HashMap<String, Object>();

		gpsParam.put("zoneNamGPS", (String)param.get("zoneNamGPS"));
		
		if(gpsParam.get("zoneNamGPS").equals("전체")){
			gpsParam.put("zoneNamGPS", null);
		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getGpsBpIdn(gpsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getGpsList.do")
	public ModelAndView getGpsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> gpsParam =  new HashMap<String, Object>();

		gpsParam.put("zoneNamGPS", (String)param.get("zoneNamGPS"));
		gpsParam.put("gpsBpIdn", (String)param.get("gpsBpIdn"));
		
		if(gpsParam.get("zoneNamGPS").equals("전체")){
			gpsParam.put("zoneNamGPS", null);
		}
		if(gpsParam.get("gpsBpIdn").equals("전체")){
			gpsParam.put("gpsBpIdn", null);
		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		PagingUtil.getInstance().setPageData(param, gpsParam, modelAndView, managementService.getGpsListCnt(gpsParam));
		
		List<?> list = managementService.getGpsList(gpsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 목록 엑셀 출력
	 * @param param
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/gpsListExcel.do")
	public String getGpsListExcel(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> gpsParam =  new HashMap<String, Object>();

		gpsParam.put("zoneNamGPS", (String)param.get("zoneNamGPS"));
		gpsParam.put("gpsBpIdn", (String)param.get("gpsBpIdn"));
		
		if(gpsParam.get("zoneNamGPS").equals("전체")){
			gpsParam.put("zoneNamGPS", null);
		}
		if(gpsParam.get("gpsBpIdn").equals("전체")){
			gpsParam.put("gpsBpIdn", null);
		}
		
		
		model.addAttribute("fileName", "GPS기준국 목록");
		model.addAttribute("name", "GPS기준국 목록");
		model.addAttribute("excelList",  managementService.getGpsList(gpsParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getGpsListCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */	
	public List<String> getGpsListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("survYmd::관측일");
		mapper.add("gpsBpIdn::점의조서ID");
		mapper.add("inputCo::작업기관");
		mapper.add("rptMan::작성자");
		mapper.add("basNam::기준국명");
		mapper.add("posi::위치");

		return mapper;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/GpsListZoneNam.do")
	public ModelAndView GpsListZoneNam(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.GpsListZoneNam();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/OrientmapZoneNam.do")
	public ModelAndView OrientmapZoneNam(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.OrientmapZoneNam();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/사진주점 관리 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/OrientmapNirZoneNam.do")
	public ModelAndView OrientmapNirZoneNam(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.OrientmapNirZoneNam();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 코스번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/OrientmapPhCourse.do")
	public ModelAndView OrientmapPhCourse(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.OrientmapPhCourse(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사진번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/OrientmapPhNum.do")
	public ModelAndView OrientmapPhNum(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode" 			,(String)param.get("zoneCode"));
		airParam.put("phCourse" 			,(String)param.get("phCourse"));      
		
		List<?> list = managementService.OrientmapPhNum(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertGps.do")
	public ModelAndView insertGps(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> gpsParam =  new HashMap<String, Object>();
		
		gpsParam.put("inputCo" 			,(String)param.get("inputCo"));
		gpsParam.put("basNam" 			,(String)param.get("basNam"));      
		gpsParam.put("rptMan"			,(String)param.get("rptMan"));
		gpsParam.put("survYmd"			,(String)param.get("survYmd"));
		gpsParam.put("observer"			,(String)param.get("observer"));
		gpsParam.put("posi"		        ,(String)param.get("posi"));
		gpsParam.put("latitude"	        ,(String)param.get("latitude"));
		gpsParam.put("norVal"	        ,(String)param.get("norVal"));
		gpsParam.put("longitude"		,(String)param.get("longitude"));
		gpsParam.put("easVal"			,(String)param.get("easVal"));
		gpsParam.put("datumElev"		,(String)param.get("datumElev"));
		gpsParam.put("rigElev"			,(String)param.get("rigElev"));      
		gpsParam.put("survTt"			,(String)param.get("survTt"));
		gpsParam.put("survInt"			,(String)param.get("survInt"));
		gpsParam.put("survSatNum"		,(String)param.get("survSatNum"));
		gpsParam.put("pdop"		        ,(String)param.get("pdop"));
		gpsParam.put("resAng"	        ,(String)param.get("resAng"));
		gpsParam.put("antElev"	        ,(String)param.get("antElev"));
		gpsParam.put("zoneCode"	        ,(String)param.get("zoneCode"));
		gpsParam.put("gpsBpIdn"	        ,(String)param.get("gpsBpIdn"));
		
		if (gpsParam != null) {
			Iterator<String> iter = gpsParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (gpsParam.get(key).equals("-") || gpsParam.get(key).equals("")) {
					gpsParam.put(key, null);
				}
			}
		}


		String list = managementService.insertGps(gpsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertOrientmap.do")
	public ModelAndView insertOrientmap(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));
		airParam.put("phCourse", (String)param.get("phCourse"));
		airParam.put("phNum", (String)param.get("phNum"));
		airParam.put("pyojungId", (String)param.get("pyojungId"));
		airParam.put("mapNum", (String)param.get("mapNum"));
		airParam.put("mapNam", (String)param.get("mapNam"));
		airParam.put("coorX", (String)param.get("coorX"));
		airParam.put("coorY", (String)param.get("coorY"));
		airParam.put("coorCx", (String)param.get("coorCx"));
		airParam.put("coorCy", (String)param.get("coorCy"));
		airParam.put("pCoorX", (String)param.get("pCoorX"));
		airParam.put("PcoorY", (String)param.get("PcoorY"));
		airParam.put("pCrsIdn", (String)param.get("pCrsIdn"));
		
		if (airParam != null) {
			Iterator<String> iter = airParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (airParam.get(key).equals("-") || airParam.get(key).equals("")) {
					airParam.put(key, null);
				}
			}
		}


		String list = managementService.insertOrientmap(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/사진주점 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/insertOrientmapNir.do")
	public ModelAndView insertOrientmapNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		
		nirParam.put("zoneCode", (String)param.get("zoneCode"));
		nirParam.put("phCourse", (String)param.get("phCourse"));
		nirParam.put("phNum", (String)param.get("phNum"));
		nirParam.put("pyojungId", (String)param.get("pyojungId"));
		nirParam.put("mapNum", (String)param.get("mapNum"));
		nirParam.put("mapNam", (String)param.get("mapNam"));
		nirParam.put("coorX", (String)param.get("coorX"));
		nirParam.put("coorY", (String)param.get("coorY"));
		nirParam.put("coorCx", (String)param.get("coorCx"));
		nirParam.put("coorCy", (String)param.get("coorCy"));
		nirParam.put("pCoorX", (String)param.get("pCoorX"));
		nirParam.put("PcoorY", (String)param.get("PcoorY"));
		nirParam.put("pCrsIdn", (String)param.get("pCrsIdn"));
		
		if (nirParam != null) {
			Iterator<String> iter = nirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (nirParam.get(key).equals("-") || nirParam.get(key).equals("")) {
					nirParam.put(key, null);
				}
			}
		}


		String list = managementService.insertOrientmapNir(nirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delGps.do")
	public ModelAndView delGps(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] gpsBpIdn = request.getParameterValues("gpsBpIdn[]");
		
		Map<String, Object> gpsParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( gpsBpIdn[i].equals("-")) {
					gpsBpIdn[i] = null;
				}
				
				gpsParam.put("zoneCode" ,zoneCode[i]);
				gpsParam.put("gpsBpIdn" ,gpsBpIdn[i]);
				int list = managementService.delGps(gpsParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delOrient.do")
	public ModelAndView delOrient(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] phCourse = request.getParameterValues("phCourse[]");
		String[] phNum = request.getParameterValues("phNum[]");
		
		Map<String, Object> airParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( phCourse[i].equals("-")) {
					phCourse[i] = null;
				}
				if ( phNum[i].equals("-")) {
					phNum[i] = null;
				}
				
				airParam.put("zoneCode" ,zoneCode[i]);
				airParam.put("phCourse" ,phCourse[i]);
				airParam.put("phNum" ,phNum[i]);
				int list = managementService.delOrient(airParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/사진주점 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delOrientNir.do")
	public ModelAndView delOrientNir(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] phCourse = request.getParameterValues("phCourse[]");
		String[] phNum = request.getParameterValues("phNum[]");
		
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( phCourse[i].equals("-")) {
					phCourse[i] = null;
				}
				if ( phNum[i].equals("-")) {
					phNum[i] = null;
				}
				
				nirParam.put("zoneCode" ,zoneCode[i]);
				nirParam.put("phCourse" ,phCourse[i]);
				nirParam.put("phNum" ,phNum[i]);
				int list = managementService.delOrientNir(nirParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/카메라 정보 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delCmr.do")
	public ModelAndView delCmr(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] lensNum = request.getParameterValues("lensNum[]");
		
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		if(lensNum != null && lensNum.length > 0){
			for(int i=0; i<lensNum.length; i++){
				if ( lensNum[i].equals("-")) {
					lensNum[i] = null;
				}
				
				airParam.put("lensNum" ,lensNum[i]);
				int list = managementService.delCmr(airParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delAirNote.do")
	public ModelAndView delAirNote(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] phDate = request.getParameterValues("phDate[]");
		String[] lensNum = request.getParameterValues("lensNum[]");
		
		Map<String, Object> airParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( phDate[i].equals("-")) {
					phDate[i] = null;
				}
				if ( lensNum[i].equals("-")) {
					lensNum[i] = null;
				}
				
				airParam.put("zoneCode" ,zoneCode[i]);
				airParam.put("phDate" ,phDate[i]);
				airParam.put("lensNum" ,lensNum[i]);
				int list = managementService.delAirNote(airParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/촬영기록부 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delNirNote.do")
	public ModelAndView delNirNote(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] phDate = request.getParameterValues("phDate[]");
		String[] lensNum = request.getParameterValues("lensNum[]");
		
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( phDate[i].equals("-")) {
					phDate[i] = null;
				}
				if ( lensNum[i].equals("-")) {
					lensNum[i] = null;
				}
				
				nirParam.put("zoneCode" ,zoneCode[i]);
				nirParam.put("phDate" ,phDate[i]);
				nirParam.put("lensNum" ,lensNum[i]);
				int list = managementService.delNirNote(nirParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 항공사진 관리/성과 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delProList.do")
	public ModelAndView delProList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] phCourse = request.getParameterValues("phCourse[]");
		String[] phNum = request.getParameterValues("phNum[]");
		
		Map<String, Object> airParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( phCourse[i].equals("-")) {
					phCourse[i] = null;
				}
				if ( phNum[i].equals("-")) {
					phNum[i] = null;
				}
				
				airParam.put("zoneCode" ,zoneCode[i]);
				airParam.put("phCourse" ,phCourse[i]);
				airParam.put("phNum" ,phNum[i]);
				int list = managementService.delProList(airParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/성과 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delProNirList.do")
	public ModelAndView delProNirList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] phCourse = request.getParameterValues("phCourse[]");
		String[] phNum = request.getParameterValues("phNum[]");
		
		Map<String, Object> nirParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( phCourse[i].equals("-")) {
					phCourse[i] = null;
				}
				if ( phNum[i].equals("-")) {
					phNum[i] = null;
				}
				
				nirParam.put("zoneCode" ,zoneCode[i]);
				nirParam.put("phCourse" ,phCourse[i]);
				nirParam.put("phNum" ,phNum[i]);
				int list = managementService.delProNirList(nirParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */		
	@RequestMapping(value = "/management/adjustGpsList.do")
	public ModelAndView updateAdjustGpsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> gpsParam =  new HashMap<String, Object>();
		
		gpsParam.put("zoneYy"			,(String)param.get("zoneYy"));
		gpsParam.put("zoneNam"			,(String)param.get("zoneNam"));
		gpsParam.put("inputCo" 			,(String)param.get("inputCo"));
		gpsParam.put("basNam" 			,(String)param.get("basNam"));      
		gpsParam.put("rptMan"			,(String)param.get("rptMan"));
		gpsParam.put("survYmd"			,(String)param.get("survYmd"));
		gpsParam.put("observer"			,(String)param.get("observer"));
		gpsParam.put("posi"		        ,(String)param.get("posi"));
		gpsParam.put("latitude"	        ,(String)param.get("latitude"));
		gpsParam.put("norVal"	        ,(String)param.get("norVal"));
		gpsParam.put("longitude"		,(String)param.get("longitude"));
		gpsParam.put("easVal"			,(String)param.get("easVal"));
		gpsParam.put("datumElev"		,(String)param.get("datumElev"));
		gpsParam.put("rigElev"			,(String)param.get("rigElev"));      
		gpsParam.put("survTt"			,(String)param.get("survTt"));
		gpsParam.put("survInt"			,(String)param.get("survInt"));
		gpsParam.put("survSatNum"		,(String)param.get("survSatNum"));
		gpsParam.put("pdop"		        ,(String)param.get("pdop"));
		gpsParam.put("resAng"	        ,(String)param.get("resAng"));
		gpsParam.put("antElev"	        ,(String)param.get("antElev"));
		gpsParam.put("zoneCode"	        ,(String)param.get("zoneCode"));
		gpsParam.put("gpsBpIdn"	        ,(String)param.get("gpsBpIdn"));
		
		
		
		if (gpsParam != null) {
			Iterator<String> iter = gpsParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (gpsParam.get(key).equals("-") || gpsParam.get(key).equals("")) {
					gpsParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustGpsList(gpsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	
	
	///////////////////////////////////DEM//////////////////////////////////////////
	
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 사업연도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/yearListDEM.do")
	public ModelAndView getYearDEMList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getYearDEMList();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneNamDEM.do")
	public ModelAndView getZoneNamDEM(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> demParam =  new HashMap<String, Object>();

		demParam.put("sYearDEM", (String)param.get("sYearDEM"));
		demParam.put("eYearDEM", (String)param.get("eYearDEM"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamDEM(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 도엽번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getMap5000Num.do")
	public ModelAndView getMap5000Num(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getMap5000Num(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 보안등급 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getSecurityCodeDEM.do")
	public ModelAndView getSecurityCodeDEM(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSecurityCodeDEM();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 격자간격 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
//	@RequestMapping(value = "/management/ResolutionDem.do")
//	public ModelAndView getResolutionDEM(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView modelAndView = new ModelAndView("jsonView");
//		
//		Map<String, Object> demParam =  new HashMap<String, Object>();
//		
//		String map5000Num = "전체".equals(param.get("map5000Num"))? "" : (String)param.get("map5000Num");
//		String zoneCode = "전체".equals(param.get("zoneCode"))? "" : (String)param.get("zoneCode");
//		String securityCde = "전체".equals(param.get("securityCde"))? "" : (String)param.get("securityCde");
//				
//		demParam.put("map5000Num", map5000Num);
//		demParam.put("zoneCode", zoneCode);
//		demParam.put("securityCde", securityCde);
//				
//		List<?> list = managementService.getResolutionDEM(demParam);
//		modelAndView.addObject("list", list);
//		
//		return modelAndView;
//	}
	
	/**
	 * 통합관리 -수치표고 관리/성과 격자간격 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/getGridIntDem.do")
	public ModelAndView getGridIntDem(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		String map5000Num = "전체".equals(param.get("map5000Num"))? "" : (String)param.get("map5000Num");
		String zoneCode = "전체".equals(param.get("zoneCode"))? "" : (String)param.get("zoneCode");
		
		System.out.println("==================================================map5000Num["+map5000Num+"] zoneCode["+zoneCode+"]");
		demParam.put("map5000Num", map5000Num);
		demParam.put("zoneCode", zoneCode);
				
		List<?> list = managementService.getGridIntDem(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getMap5000NumList.do")
	public ModelAndView getMap5000NumList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		demParam.put("securityCde", (String)param.get("securityCde"));
		demParam.put("gridInt", (String)param.get("gridInt"));
		
		if(demParam.get("map5000Num").equals("전체")){
			demParam.put("map5000Num", null);
		}
		
		if(demParam.get("gridInt").equals("전체")){
			demParam.put("gridInt", null);
		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		PagingUtil.getInstance().setPageData(param, demParam, modelAndView, managementService.getMap5000NumListCnt(demParam));
		
		List<?> list = managementService.getMap5000NumList(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subMap5000NumList.do")
	public ModelAndView getSubMap5000NumList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> demParam =  new HashMap<String, Object>();


		demParam.put("map5000Num", (String)param.get("map5000Num"));
		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("securityCde", (String)param.get("securityCde"));
		demParam.put("gridInt", (String)param.get("gridInt"));

		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSubMap5000NumList(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/ 격자간격 등록 중복검사
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getSubGridIntDem.do")
	public ModelAndView getSubGridIntDem(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> demParam =  new HashMap<String, Object>();

		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		demParam.put("gridInt", (String)param.get("gridInt"));

		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSubGridIntDem(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 조회(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subMap5000BaseMeta.do")
	public ModelAndView getSubMap5000BaseMeta(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> demParam =  new HashMap<String, Object>();

		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		demParam.put("securityCde", (String)param.get("securityCde"));

		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSubMap5000BaseMeta(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 수정(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubMap5000BaseMeta.do")
	public ModelAndView updateAdjustSubMap5000BaseMeta(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		demParam.put("fileIdentifier", (String)param.get("fileIdentifier"));
		demParam.put("language", (String)param.get("language"));
		demParam.put("resOfficename", (String)param.get("resOfficename"));
		demParam.put("resPostname", (String)param.get("resPostname"));
		demParam.put("resPhone", (String)param.get("resPhone"));
		demParam.put("resFax", (String)param.get("resFax"));
		demParam.put("resAddress", (String)param.get("resAddress"));
		demParam.put("resPostalCode", (String)param.get("resPostalCode"));
		demParam.put("metaDate", (String)param.get("metaDate"));
		demParam.put("metaVersion", (String)param.get("metaVersion"));
		demParam.put("distFormatName", (String)param.get("distFormatName"));
		demParam.put("distFormatVersion", (String)param.get("distFormatVersion"));
		demParam.put("distOfficename", (String)param.get("distOfficename"));
		demParam.put("distPostname", (String)param.get("distPostname"));
		demParam.put("distPhone", (String)param.get("distPhone"));
		demParam.put("qualityTarget", (String)param.get("qualityTarget"));
		demParam.put("document", (String)param.get("document"));
		demParam.put("targetName", (String)param.get("targetName"));
		demParam.put("spatialData", (String)param.get("spatialData"));
		demParam.put("sigunguNam", (String)param.get("sigunguNam"));
		demParam.put("sigunguCde", (String)param.get("sigunguCde"));
		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		
		
		if (demParam != null) {
			Iterator<String> iter = demParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (demParam.get(key).equals("-")) {
					demParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustSubMap5000BaseMeta(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubGridIntDem.do")
	public ModelAndView insertSubGridIntDem(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		demParam.put("gridInt", (String)param.get("gridInt"));
		
		if (demParam != null) {
			Iterator<String> iter = demParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (demParam.get(key).equals("-")) {
					demParam.put(key, null);
				}
			}
		}


		String list = managementService.insertSubGridIntDem(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubMap5000BaseMeta.do")
	public ModelAndView insertSubMap5000BaseMeta(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		demParam.put("fileIdentifier", (String)param.get("fileIdentifier"));
		demParam.put("language", (String)param.get("language"));
		demParam.put("resOfficename", (String)param.get("resOfficename"));
		demParam.put("resPostname", (String)param.get("resPostname"));
		demParam.put("resPhone", (String)param.get("resPhone"));
		demParam.put("resFax", (String)param.get("resFax"));
		demParam.put("resAddress", (String)param.get("resAddress"));
		demParam.put("resPostalCode", (String)param.get("resPostalCode"));
		demParam.put("metaDate", (String)param.get("metaDate"));
		demParam.put("metaVersion", (String)param.get("metaVersion"));
		demParam.put("distFormatName", (String)param.get("distFormatName"));
		demParam.put("distFormatVersion", (String)param.get("distFormatVersion"));
		demParam.put("distOfficename", (String)param.get("distOfficename"));
		demParam.put("distPostname", (String)param.get("distPostname"));
		demParam.put("distPhone", (String)param.get("distPhone"));
		demParam.put("qualityTarget", (String)param.get("qualityTarget"));
		demParam.put("document", (String)param.get("document"));
		demParam.put("targetName", (String)param.get("targetName"));
		demParam.put("spatialData", (String)param.get("spatialData"));
		demParam.put("sigunguNam", (String)param.get("sigunguNam"));
		demParam.put("sigunguCde", (String)param.get("sigunguCde"));
		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		
		
		if (demParam != null) {
			Iterator<String> iter = demParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (demParam.get(key).equals("-")) {
					demParam.put(key, null);
				}
			}
		}


		String list = managementService.insertSubMap5000BaseMeta(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubMap5000NumList.do")
	public ModelAndView updateAdjustSubMap5000NumList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		demParam.put("zoneNam", (String)param.get("zoneNam"));
		demParam.put("zoneYy", (String)param.get("zoneYy"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		demParam.put("map5000Nam", (String)param.get("map5000Nam"));
		demParam.put("originData", (String)param.get("originData"));
		demParam.put("acqOrigin", (String)param.get("acqOrigin"));
		demParam.put("originForm", (String)param.get("originForm"));
		demParam.put("originYear", (String)param.get("originYear"));
		demParam.put("gridInt", (String)param.get("gridInt"));
		demParam.put("resSeq", (String)param.get("resSeq"));
		demParam.put("unit", (String)param.get("unit"));
		demParam.put("interpolMethod", (String)param.get("interpolMethod"));
		demParam.put("lbX", (String)param.get("lbX"));
		demParam.put("lbY", (String)param.get("lbY"));
		demParam.put("rtX", (String)param.get("rtX"));
		demParam.put("rtY", (String)param.get("rtY"));
		demParam.put("crsIdn", (String)param.get("crsIdn"));
		demParam.put("coordinate", (String)param.get("coordinate"));
		demParam.put("origin", (String)param.get("origin"));
		demParam.put("elevNorm", (String)param.get("elevNorm"));
		demParam.put("heivarKind", (String)param.get("heivarKind"));
		demParam.put("maxElev", (String)param.get("maxElev"));
		demParam.put("minElev", (String)param.get("minElev"));
		demParam.put("dateForm", (String)param.get("dateForm"));
		demParam.put("accuracy", (String)param.get("accuracy"));
		demParam.put("producer", (String)param.get("producer"));
		demParam.put("publisher", (String)param.get("publisher"));
		demParam.put("reference", (String)param.get("reference"));
		demParam.put("prp", (String)param.get("prp"));
		demParam.put("securityCde", (String)param.get("securityCde"));
		demParam.put("dtdOx", (String)param.get("dtdOx"));
		demParam.put("shadedImgOx", (String)param.get("shadedImgOx"));
		demParam.put("obsRgsOx", (String)param.get("obsRgsOx"));
		demParam.put("flightPlanOx", (String)param.get("flightPlanOx"));
		demParam.put("gpsinsOx", (String)param.get("gpsinsOx"));
		demParam.put("gpsBpOx", (String)param.get("gpsBpOx"));
		demParam.put("pLbX", (String)param.get("pLbX"));
		demParam.put("pLbY", (String)param.get("pLbY"));
		demParam.put("pRtX", (String)param.get("pRtX"));
		demParam.put("pRtY", (String)param.get("pRtY"));
		demParam.put("pCrsIdn", (String)param.get("pCrsIdn"));
		demParam.put("imageCde", (String)param.get("imageCde"));
		demParam.put("zoneCode", (String)param.get("zoneCode"));
		
		if (demParam != null) {
			Iterator<String> iter = demParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (demParam.get(key).equals("-")) {
					demParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustSubMap5000NumList(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubMap5000NumList.do")
	public ModelAndView insertSubMap5000NumList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		demParam.put("map5000Nam", (String)param.get("map5000Nam"));
		demParam.put("originData", (String)param.get("originData"));
		demParam.put("acqOrigin", (String)param.get("acqOrigin"));
		demParam.put("originForm", (String)param.get("originForm"));
		demParam.put("originYear", (String)param.get("originYear"));
		demParam.put("gridInt", (String)param.get("gridInt"));
		demParam.put("unit", (String)param.get("unit"));
		demParam.put("interpolMethod", (String)param.get("interpolMethod"));
		demParam.put("lbX", (String)param.get("lbX"));
		demParam.put("lbY", (String)param.get("lbY"));
		demParam.put("rtX", (String)param.get("rtX"));
		demParam.put("rtY", (String)param.get("rtY"));
		demParam.put("crsIdn", (String)param.get("crsIdn"));
		demParam.put("coordinate", (String)param.get("coordinate"));
		demParam.put("origin", (String)param.get("origin"));
		demParam.put("elevNorm", (String)param.get("elevNorm"));
		demParam.put("heivarKind", (String)param.get("heivarKind"));
		demParam.put("maxElev", (String)param.get("maxElev"));
		demParam.put("minElev", (String)param.get("minElev"));
		demParam.put("dateForm", (String)param.get("dateForm"));
		demParam.put("accuracy", (String)param.get("accuracy"));
		demParam.put("producer", (String)param.get("producer"));
		demParam.put("publisher", (String)param.get("publisher"));
		demParam.put("reference", (String)param.get("reference"));
		demParam.put("prp", (String)param.get("prp"));
		demParam.put("securityCde", (String)param.get("securityCde"));
		demParam.put("dtdOx", (String)param.get("dtdOx"));
		demParam.put("shadedImgOx", (String)param.get("shadedImgOx"));
		demParam.put("obsRgsOx", (String)param.get("obsRgsOx"));
		demParam.put("flightPlanOx", (String)param.get("flightPlanOx"));
		demParam.put("gpsinsOx", (String)param.get("gpsinsOx"));
		demParam.put("gpsBpOx", (String)param.get("gpsBpOx"));
		demParam.put("pLbX", (String)param.get("pLbX"));
		demParam.put("pLbY", (String)param.get("pLbY"));
		demParam.put("pRtX", (String)param.get("pRtX"));
		demParam.put("pRtY", (String)param.get("pRtY"));
		demParam.put("pCrsIdn", (String)param.get("pCrsIdn"));
		demParam.put("imageCde", (String)param.get("imageCde"));
		demParam.put("zoneCode", (String)param.get("zoneCode"));
		
		if (demParam != null) {
			Iterator<String> iter = demParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (demParam.get(key).equals("-")) {
					demParam.put(key, null);
				}
			}
		}


		String list = managementService.insertSubMap5000NumList(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제 이전 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getGridIntCnt.do")
	public ModelAndView getGridIntCnt(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		Map<String, Object> demParam =  new HashMap<String, Object>();

		demParam.put("zoneCode", (String)param.get("zoneCode"));
		demParam.put("map5000Num", (String)param.get("map5000Num"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSubMap5000BaseMeta(demParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/delMap5000.do")
	public ModelAndView delMap5000(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] map5000Num = request.getParameterValues("map5000Num[]");
		String[] gridInt = request.getParameterValues("gridInt[]");
		String[] resSeq = request.getParameterValues("resSeq[]");
		
		Map<String, Object> demParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( map5000Num[i].equals("-")) {
					map5000Num[i] = null;
				}
				if ( gridInt[i].equals("-")) {
					gridInt[i] = null;
				}
				demParam.put("zoneCode", zoneCode[i]);
				demParam.put("map5000Num", map5000Num[i]);
				int cnt =  managementService.getGridIntCnt(demParam);
			
				if(cnt > 1){
					demParam.put("zoneCode", zoneCode[i]);
					demParam.put("map5000Num", map5000Num[i]);
					demParam.put("gridInt", gridInt[i]);
					demParam.put("resSeq", resSeq[i]);
					int list = managementService.delGridIntDem(demParam);
				}else{
					demParam.put("zoneCode" ,zoneCode[i]);
					demParam.put("map5000Num" ,map5000Num[i]);
					demParam.put("gridInt" ,gridInt[i]);
					demParam.put("resSeq", resSeq[i]);
					int list = managementService.delGridIntDem(demParam);
					int list2 = managementService.delMap5000(demParam);
				}
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getMap5000NumListExcel.do")
	public String getMap5000NumListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> demParam =  new HashMap<String, Object>();
		
		demParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		demParam.put("map5000Num"         ,(String)param.get("map5000Num"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(demParam.get("map5000Num").equals("전체")){
			demParam.put("map5000Num", null);
		}
		
		model.addAttribute("fileName", "수치표고 목록");
		model.addAttribute("name", "수치표고 목록");
		model.addAttribute("excelList",  managementService.getMap5000NumList(demParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getMap5000NumListCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getMap5000NumListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("map5000Nam::도엽명");
		mapper.add("map5000Num::도엽번호");
		mapper.add("securityCde::지리정보등급");
		mapper.add("dtdOx::수치지면자료 유무");
		mapper.add("shadedImgOx::관리관측도 유무");
		mapper.add("obsRgsOx::음영기복도 유무");

		return mapper;
	}

	
	
	
	
	
	
	////////////////////////////////////////////////정사영상/////////////////////////////////////////////////////////
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 사업연도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/yearListOrt.do")
	public ModelAndView getYearListOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getYearListOrt();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneNamOrt.do")
	public ModelAndView getZoneNamOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ortParam =  new HashMap<String, Object>();

		ortParam.put("sYearOrt", (String)param.get("sYearOrt"));
		ortParam.put("eYearOrt", (String)param.get("eYearOrt"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamOrt(ortParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 도엽번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getMap5000NumOrt.do")
	public ModelAndView getMap5000NumOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getMap5000NumOrt(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 보안등급 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getSecurityCodeOrt.do")
	public ModelAndView getSecurityCodeOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSecurityCodeOrt();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/헤상도 등록 중복검사
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getGtypDstOrt.do")
	public ModelAndView getGtypDstOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getGtypDstOrt();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/헤상도 등록 중복검사
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getResSeqOrt.do")
	public ModelAndView getResSeqOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getResSeqOrt();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getMap5000NumOrtList.do")
	public ModelAndView getMap5000NumOrtList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ortParam =  new HashMap<String, Object>();

		ortParam.put("map5000Num", (String)param.get("map5000Num"));
		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("securityCde", (String)param.get("securityCde"));
		ortParam.put("gtypDst", (String)param.get("gtypDst"));
		
		if(ortParam.get("map5000Num").equals("전체")){
			ortParam.put("map5000Num", null);
		}

		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		PagingUtil.getInstance().setPageData(param, ortParam, modelAndView, managementService.getMap5000NumOrtListCnt(ortParam));
				
		List<?> list = managementService.getMap5000NumOrtList(ortParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subMap5000NumOrtList.do")
	public ModelAndView getSubMap5000NumOrtList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ortParam =  new HashMap<String, Object>();

		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("securityCde", (String)param.get("securityCde"));
		ortParam.put("gtypDst", (String)param.get("gtypDst"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSubMap5000NumOrtList(ortParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/해상도 입력 지상표본거리 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getSubGtypDstOrt.do")
	public ModelAndView getSubGtypDstOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> ortParam =  new HashMap<String, Object>();

		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("gtypDst", (String)param.get("gtypDst"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSubGtypDstOrt(ortParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subMetaOrtList.do")
	public ModelAndView getSubMetaOrtList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		
		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("securityCde", (String)param.get("securityCde"));
		ortParam.put("gtypDst", (String)param.get("gtypDst"));

		List<?> list = managementService.getSubMetaOrtList(ortParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubMap5000NumOrtList.do")
	public ModelAndView updateAdjustSubMap5000NumOrtList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("zoneNam",(String)param.get("zoneNam"));
		ortParam.put("zoneYy",(String)param.get("zoneYy"));
		ortParam.put("map5000Nam",(String)param.get("map5000Nam"));
		ortParam.put("scale",(String)param.get("scale"));
		ortParam.put("constCo",(String)param.get("constCo"));
		ortParam.put("inputCo",(String)param.get("inputCo"));
		ortParam.put("origin",(String)param.get("origin"));
		ortParam.put("workYmd",(String)param.get("workYmd"));
		ortParam.put("maskOx", (String)param.get("maskOx"));
		ortParam.put("securityCde",(String)param.get("securityCde"));
		ortParam.put("prp", (String)param.get("prp"));
		ortParam.put("usedDem", (String)param.get("usedDem"));
		ortParam.put("crsIdn", (String)param.get("crsIdn"));
		ortParam.put("pCrsIdn",(String)param.get("pCrsIdn"));
		ortParam.put("reference", (String)param.get("reference"));
		ortParam.put("colorGbn", (String)param.get("colorGbn"));
		ortParam.put("originImg",(String)param.get("originImg"));
		ortParam.put("satNam", (String)param.get("satNam"));
		ortParam.put("cmrType", (String)param.get("cmrType"));
		ortParam.put("scaleRelac", (String)param.get("scaleRelac"));
		ortParam.put("imageCde", (String)param.get("imageCde"));
		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));

		if (ortParam != null) {
			Iterator<String> iter = ortParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (ortParam.get(key).equals("-")) {
					ortParam.put(key, null);
				}
			}
		}
		int list = managementService.updateAdjustSubMap5000NumOrtList(ortParam);
		int list2 = managementService.updateAdjustSubMap5000NumOrtList_2(ortParam);
		int list3 = managementService.updateAdjustSubMap5000NumOrtList_3(ortParam);
		
		modelAndView.addObject("list1", list);
		modelAndView.addObject("list2", list2);
		modelAndView.addObject("list3", list3);

		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubMetaBaseOrt.do")
	public ModelAndView updateAdjustSubMetaBaseOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("resOfficename",(String)param.get("resOfficename"));
		ortParam.put("metaVersion",(String)param.get("metaVersion"));
		ortParam.put("distOfficename",(String)param.get("distOfficename"));
		ortParam.put("distPostname",(String)param.get("distPostname"));
		ortParam.put("distPhone",(String)param.get("distPhone"));
		ortParam.put("qualityTarget",(String)param.get("qualityTarget"));
		ortParam.put("document",(String)param.get("document"));
		ortParam.put("sigunguNam",(String)param.get("sigunguNam"));
		ortParam.put("sigunguCde",(String)param.get("sigunguCde"));
		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));


		
		if (ortParam != null) {
			Iterator<String> iter = ortParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (ortParam.get(key).equals("-")) {
					ortParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustSubMetaBaseOrt(ortParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정(식별정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubMetaOrtIdent.do")
	public ModelAndView updateAdjustSubMetaOrtIdent(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("fileIdentifier",(String)param.get("fileIdentifier"));
		ortParam.put("language",(String)param.get("language"));
		ortParam.put("gtypDst",(String)param.get("gtypDst"));
		ortParam.put("targetName",(String)param.get("targetName"));
		ortParam.put("scale",(String)param.get("scale"));
		ortParam.put("inputCo",(String)param.get("inputCo"));
		ortParam.put("resPostname",(String)param.get("resPostname"));
		ortParam.put("resPhone",(String)param.get("resPhone"));
		ortParam.put("resFax",(String)param.get("resFax"));
		ortParam.put("resAddress",(String)param.get("resAddress"));
		ortParam.put("resPostalCode",(String)param.get("resPostalCode"));
		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));


		
		if (ortParam != null) {
			Iterator<String> iter = ortParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (ortParam.get(key).equals("-")) {
					ortParam.put(key, null);
				}
			}
		}

		int list = managementService.updateAdjustSubMetaOrtIdent(ortParam);
		
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정(식별정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubGtypDstOrtList.do")
	public ModelAndView adjustSubGtypDstOrtList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("zoneCode",(String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("gtypDst",(String)param.get("gtypDst"));
		ortParam.put("resSeq",(String)param.get("resSeq"));

		if (ortParam != null) {
			Iterator<String> iter = ortParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (ortParam.get(key).equals("-")) {
					ortParam.put(key, null);
				}
			}
		}

		int list = managementService.adjustSubGtypDstOrtList(ortParam);
		
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubMap5000NumOrtList.do")
	public ModelAndView insertSubMap5000NumOrtList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("map5000Nam",(String)param.get("map5000Nam"));
		ortParam.put("scale",(String)param.get("scale"));
		ortParam.put("gtypDst",(String)param.get("gtypDst"));
		ortParam.put("constCo", (String)param.get("constCo"));
		ortParam.put("inputCo", (String)param.get("inputCo"));
		ortParam.put("origin", (String)param.get("origin"));
		
		ortParam.put("workYmd",(String)param.get("workYmd"));
		ortParam.put("prp", (String)param.get("prp"));
		ortParam.put("usedDem", (String)param.get("usedDem"));
		ortParam.put("crsIdn",(String)param.get("crsIdn"));
		ortParam.put("pCrsIdn", (String)param.get("pCrsIdn"));
		ortParam.put("reference", (String)param.get("reference"));
		ortParam.put("colorGbn", (String)param.get("colorGbn"));
		
		ortParam.put("originImg", (String)param.get("originImg"));
		ortParam.put("satNam", (String)param.get("satNam"));
		ortParam.put("cmrType", (String)param.get("cmrType"));
		
		ortParam.put("scaleRelac", (String)param.get("scaleRelac"));
		ortParam.put("satNam", (String)param.get("satNam"));
		ortParam.put("imageCde",(String)param.get("imageCde"));
		ortParam.put("zoneCode", (String)param.get("zoneCode"));

		String list = managementService.insertSubMap5000NumOrtList(ortParam);

		modelAndView.addObject("list", list);
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 정사영상 관리/해상도 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getExistToMetaOrtIdent.do")
	public ModelAndView getExistToMetaOrtIdent(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("zoneCode",(String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		
		int list = managementService.getExistToMetaOrtIdent(ortParam);
		
		modelAndView.addObject("list", list);

		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 정사영상 관리/해상도 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getGtypDstCnt.do")
	public ModelAndView getGtypDstCnt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("zoneCode",(String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("gtypDst",(String)param.get("gtypDst"));
		
		int list = managementService.getGtypDstCnt(ortParam);
		
		modelAndView.addObject("list", list);

		
		return modelAndView;
		
	}
	/**
	 * 통합관리 - 정사영상 관리/해상도 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubGtypDstOrt.do")
	public ModelAndView insertSubGtypDstOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("zoneCode",(String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("gtypDst",(String)param.get("gtypDst"));
		
		System.out.println(ortParam);
		if (ortParam != null) {
			Iterator<String> iter = ortParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (ortParam.get(key).equals("-")) {
					ortParam.put(key, null);
				}
			}
		}


		String list = managementService.insertSubGtypDstOrt(ortParam);

		
		modelAndView.addObject("list", list);

		
		return modelAndView;
		
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubMetaBaseOrt.do")
	public ModelAndView insertSubMetaBaseOrt(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		ortParam.put("resOfficename",(String)param.get("resOfficename"));
		ortParam.put("metaVersion",(String)param.get("metaVersion"));
		ortParam.put("distOfficename",(String)param.get("distOfficename"));
		ortParam.put("distPostname",(String)param.get("distPostname"));
		ortParam.put("distPhone",(String)param.get("distPhone"));
		ortParam.put("qualityTarget",(String)param.get("qualityTarget"));
		ortParam.put("document",(String)param.get("document"));
		ortParam.put("sigunguNam",(String)param.get("sigunguNam"));
		ortParam.put("sigunguCde",(String)param.get("sigunguCde"));
		ortParam.put("zoneCode", (String)param.get("zoneCode"));
		ortParam.put("map5000Num",(String)param.get("map5000Num"));
		ortParam.put("scale",(String)param.get("scale"));
		ortParam.put("gtypDst",(String)param.get("gtypDst"));
		ortParam.put("inputCo",(String)param.get("inputCo"));


		ortParam.put("fileIdentifier",(String)param.get("fileIdentifier"));
		ortParam.put("language",(String)param.get("language"));
		ortParam.put("gtypDst",(String)param.get("gtypDst"));
		ortParam.put("targetName",(String)param.get("targetName"));
		ortParam.put("resPostname",(String)param.get("resPostname"));
		ortParam.put("resPhone",(String)param.get("resPhone"));
		ortParam.put("resFax",(String)param.get("resFax"));
		ortParam.put("resAddress",(String)param.get("resAddress"));
		ortParam.put("resPostalCode",(String)param.get("resPostalCode"));
		ortParam.put("maskOx", (String)param.get("maskOx"));
		ortParam.put("securityCde",(String)param.get("securityCde"));
		ortParam.put("crsIdn_2", (String)param.get("crsIdn_2"));
		ortParam.put("metaDate", (String)param.get("metaDate"));
		ortParam.put("distFormatVersion", (String)param.get("distFormatVersion"));
		ortParam.put("distFormatName", (String)param.get("distFormatName"));

		String list = managementService.insertSubMetaBaseOrt(ortParam);
		
		modelAndView.addObject("list", list);

		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/delMap5000Ort.do")
	public ModelAndView delMap5000Ort(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] map5000Num = request.getParameterValues("map5000Num[]");
		String[] gtypDst = request.getParameterValues("gtypDst[]");

		
		Map<String, Object> ortParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( map5000Num[i].equals("-")) {
					map5000Num[i] = null;
				}
				if ( gtypDst[i].equals("-")) {
					gtypDst[i] = null;
				}
				
				Map<String, Object> airParam =  new HashMap<String, Object>();
				airParam.put("zoneCode", (String)zoneCode[i] );
				airParam.put("map5000Num", (String)map5000Num[i] );
				
				int cnt = managementService.getGtypDstCnt(airParam);
				System.out.println("=========경과값 보기 : "+cnt);//Window.Console.log("");
				if (cnt > 1){
					ortParam.put("zoneCode" ,zoneCode[i]);
					ortParam.put("map5000Num" ,map5000Num[i]);
					ortParam.put("gtypDst", gtypDst[i]);
					int list = managementService.delGtypDstOrt(ortParam);
					
				}else{
					ortParam.put("zoneCode" ,zoneCode[i]);
					ortParam.put("map5000Num" ,map5000Num[i]);
					ortParam.put("gtypDst", gtypDst[i]);
					int list = managementService.delMap5000Ort(ortParam);
				}
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getMap5000NumOrtListExcel.do")
	public String getMap5000NumOrtListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> ortParam =  new HashMap<String, Object>();
		
		ortParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		ortParam.put("map5000Num"         ,(String)param.get("map5000Num"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(ortParam.get("map5000Num").equals("전체")){
			ortParam.put("map5000Num", null);
		}
		
		model.addAttribute("fileName", "정사영상 목록");
		model.addAttribute("name", "정사영상 목록");
		model.addAttribute("excelList",  managementService.getMap5000NumOrtList(ortParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getMap5000NumOrtListCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getMap5000NumOrtListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("map5000Nam::도엽명");
		mapper.add("map5000Num::도엽번호");
		mapper.add("originImg::원시영상자료 유무");
		mapper.add("constCo::제작기관");
		mapper.add("workYmd::제작년월일");
		mapper.add("usedDem::사용수치표고");

		return mapper;
	}

	
	
	
	
	
	
	
	/////////////////////////////////////////////LiDAR////////////////////////////////////////////////////
	
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 사업연도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/yearListLid.do")
	public ModelAndView getYearListLid(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getYearListLid();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneNamLid.do")
	public ModelAndView getZoneNamLid(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> lidParam =  new HashMap<String, Object>();

		lidParam.put("sYearLid", (String)param.get("sYearLid"));
		lidParam.put("eYearLid", (String)param.get("eYearLid"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamLid(lidParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 자료 ID 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getLidarIdn.do")
	public ModelAndView getLidarIdn(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				

		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getLidarIdn(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 보안등급 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getSecurityCodeLid.do")
	public ModelAndView getSecurityCodeLid(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSecurityCodeLid();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 목록 조회(상세목록 포함)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getLidList.do")
	public ModelAndView getLidList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> lidParam =  new HashMap<String, Object>();

		lidParam.put("zoneCode", (String)param.get("zoneCode"));
		lidParam.put("lidarIdn", (String)param.get("lidarIdn"));
		lidParam.put("securityCde", (String)param.get("securityCde"));
		
		if(lidParam.get("zoneCode").equals("전체")){
			lidParam.put("zoneCode", null);
		}
		if(lidParam.get("lidarIdn").equals("전체")){
			lidParam.put("lidarIdn", null);
		}
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		PagingUtil.getInstance().setPageData(param, lidParam, modelAndView, managementService.getLidListCnt(lidParam));
				
		List<?> list = managementService.getLidList(lidParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustLidList.do")
	public ModelAndView updateAdjustLidList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> lidParam =  new HashMap<String, Object>();
		lidParam.put("zoneNam" 			,(String)param.get("zoneNam")); 			
		lidParam.put("zoneYy"			,(String)param.get("zoneYy"));			
		lidParam.put("lidarIdn"			,(String)param.get("lidarIdn"));			
		lidParam.put("lidaroriginForm"	,(String)param.get("lidaroriginForm"));	
		lidParam.put("lidaroriginYmd" 	,(String)param.get("lidaroriginYmd")); 	
		lidParam.put("coordinate" 		,(String)param.get("coordinate")); 		
		lidParam.put("constCo"  		,(String)param.get("constCo"));  		
		lidParam.put("inputCo"  		,(String)param.get("inputCo"));  		
		lidParam.put("securityCde" 		,(String)param.get("securityCde")); 		
		lidParam.put("prp"				,(String)param.get("prp"));				
		lidParam.put("crsIdn"  		 	,(String)param.get("crsIdn"));
        lidParam.put("zoneCode"		    ,(String)param.get("zoneCode"));		   
        lidParam.put("name"		   		,(String)param.get("name"));		   

		
		if (lidParam != null) {
			Iterator<String> iter = lidParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (lidParam.get(key).equals("-")) {
					lidParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustLidList(lidParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertLidList.do")
	public ModelAndView insertLidList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> lidParam =  new HashMap<String, Object>();
		lidParam.put("lidarIdn"			,(String)param.get("lidarIdn"));			
		lidParam.put("lidaroriginForm"	,(String)param.get("lidaroriginForm"));	
		lidParam.put("lidaroriginYmd" 	,(String)param.get("lidaroriginYmd")); 	
		lidParam.put("coordinate" 		,(String)param.get("coordinate")); 		
		lidParam.put("constCo"  		,(String)param.get("constCo"));  		
		lidParam.put("inputCo"  		,(String)param.get("inputCo"));  		
		lidParam.put("securityCde" 		,(String)param.get("securityCde")); 		
		lidParam.put("prp"				,(String)param.get("prp"));				
		lidParam.put("crsIdn"  		 	,(String)param.get("crsIdn"));
        lidParam.put("zoneCode"		    ,(String)param.get("zoneCode"));		   
        lidParam.put("name"		   		,(String)param.get("name"));		
		
		if (lidParam != null) {
			Iterator<String> iter = lidParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (lidParam.get(key).equals("-")) {
					lidParam.put(key, null);
				}
			}
		}


		String list = managementService.insertLidList(lidParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/delLid.do")
	public ModelAndView delLid(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] zoneCode = request.getParameterValues("zoneCode[]");
		String[] lidarIdn = request.getParameterValues("lidarIdn[]");

		
		Map<String, Object> lidParam =  new HashMap<String, Object>();
		

		if(zoneCode != null && zoneCode.length > 0){
			for(int i=0; i<zoneCode.length; i++){
				if ( zoneCode[i].equals("-")) {
					zoneCode[i] = null;
				}
				if ( lidarIdn[i].equals("-")) {
					lidarIdn[i] = null;
				}

				
				lidParam.put("zoneCode" ,zoneCode[i]);
				lidParam.put("lidarIdn" ,lidarIdn[i]);
				int list = managementService.delLid(lidParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getLidListExcel.do")
	public String getLidListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> lidParam =  new HashMap<String, Object>();
		
		lidParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		lidParam.put("lidarIdn"         ,(String)param.get("lidarIdn"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(lidParam.get("lidarIdn").equals("전체")){
			lidParam.put("lidarIdn", null);
		}
		
		model.addAttribute("fileName", "라이다 목록");
		model.addAttribute("name", "라이다 목록");
		model.addAttribute("excelList",  managementService.getLidList(lidParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getLidListCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getLidListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("zoneYy::사업년도");
		mapper.add("zoneNam::사업지구명");
		mapper.add("lidaroriginForm::자료형식");
		mapper.add("lidarIdn::자료ID");
		mapper.add("coordinate::좌표계");
		mapper.add("constCo::제작기관");
		mapper.add("inputCo::작업기관");
		mapper.add("securityCde::지리정보등급");

		return mapper;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////////////////////NIR////////////////////////////////////////////////////
	
	
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사업연도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/yearListNir.do")
	public ModelAndView getYearListNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getYearListNir();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 코스번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/phCourseNir.do")
	public ModelAndView getPhCourseNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				

		String zoneCode = (String)param.get("zoneCode");
		
		List<?> list = managementService.getPhCourseNir(zoneCode);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/zoneNamNir.do")
	public ModelAndView getZoneNamNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> NirParam =  new HashMap<String, Object>();

		NirParam.put("sYearNir", (String)param.get("sYearNir"));
		NirParam.put("eYearNir", (String)param.get("eYearNir"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamNir(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/phNamNir.do")
	public ModelAndView getPhNumNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		
		List<?> list = managementService.getPhNumNir(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 주점 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getOrientmapNirList.do")
	public ModelAndView getOrientmapNirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		

		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("phNum", (String)param.get("phNum"));
		NirParam.put("sYearNir", (String)param.get("sYearNir"));
		NirParam.put("eYearNir", (String)param.get("eYearNir"));
		
		if(NirParam.get("phCourse").equals("전체")){
			NirParam.put("phCourse", null);
		}
		if(NirParam.get("phNum").equals("전체")){
			NirParam.put("phNum", null);
		}
		
		PagingUtil.getInstance().setPageData(param, NirParam, modelAndView, managementService.getOrientmapNirListCnt(NirParam));
		
		List<?> list = managementService.getOrientmapNirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 상세 목록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subOrientmapNirList.do")
	public ModelAndView getSubOrientmapNirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("phNum", (String)param.get("phNum"));

		List<?> list = managementService.getSubOrientmapNirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustOrientmapNir.do")
	public ModelAndView updateAdjustOrientmapNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("phNum", (String)param.get("phNum"));
		NirParam.put("pyojungId", (String)param.get("pyojungId"));
		NirParam.put("mapNum", (String)param.get("mapNum"));
		NirParam.put("mapNam", (String)param.get("mapNam"));
		NirParam.put("coorX", (String)param.get("coorX"));
		NirParam.put("coorY", (String)param.get("coorY"));
		NirParam.put("coorCx", (String)param.get("coorCx"));
		NirParam.put("coorCy", (String)param.get("coorCy"));
		NirParam.put("pCoorX", (String)param.get("pCoorX"));
		NirParam.put("PcoorY", (String)param.get("PcoorY"));
		NirParam.put("pCrsIdn", (String)param.get("pCrsIdn"));
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}

		int list = managementService.updateAdjustOrientmapNir(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과관리 해상도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/ResolutionNir.do")
	public ModelAndView getResolutionNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		String zoneCode = "전체".equals(param.get("zoneCode"))? "" : (String)param.get("zoneCode");
		String phCourse = "전체".equals(param.get("phCourse"))? "" : (String)param.get("phCourse");
		String phNum = "전체".equals(param.get("phNum"))? "" : (String)param.get("phNum");
				
		NirParam.put("zoneCode", zoneCode);
		NirParam.put("phCourse", phCourse);
		NirParam.put("phNum", phNum);
				
		List<?> list = managementService.getResolutionNir(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/productNirList.do")
	public ModelAndView getProductNirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		

		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("phNum", (String)param.get("phNum"));
		NirParam.put("sYearNir", (String)param.get("sYearNir"));
		NirParam.put("eYearNir", (String)param.get("eYearNir"));
		NirParam.put("securityCde", (String)param.get("securityCde"));
		NirParam.put("cameraCde", (String)param.get("cameraCde"));
		NirParam.put("resolution", (String)param.get("resolution"));
		
		if(NirParam.get("phCourse").equals("전체")){
			NirParam.put("phCourse", null);
		}
		if(NirParam.get("phNum").equals("전체")){
			NirParam.put("phNum", null);
		}
		if(NirParam.get("securityCde").equals("전체")){
			NirParam.put("securityCde", null);
		}
		if(NirParam.get("cameraCde").equals("전체")){
			NirParam.put("cameraCde", null);
		}
		if(NirParam.get("resolution").equals("전체")){
			NirParam.put("resolution", null);
		}
		
		PagingUtil.getInstance().setPageData(param, NirParam, modelAndView, managementService.getProductNirListCnt(NirParam));
		
		List<?> list = managementService.getProductNirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subProductNirList.do")
	public ModelAndView subProductNirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("phNum", (String)param.get("phNum"));
		NirParam.put("resolution", (String)param.get("resolution"));

		List<?> list = managementService.getSubProductNirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회(외부표정요소)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subProductEONirList.do")
	public ModelAndView subProductEONirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("phNum", (String)param.get("phNum"));
		NirParam.put("resolution", (String)param.get("resolution"));

		List<?> list = managementService.getSubProductEONirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subMetaNirList.do")
	public ModelAndView subMetaNirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("phNum", (String)param.get("phNum"));
		NirParam.put("resolution", (String)param.get("resolution"));

		List<?> list = managementService.getSubMetaNirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(식별정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustMetaNirmapIdent.do")
	public ModelAndView updateAdjustMetaNirmapIdent(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("fileIdentifier"   ,(String)param.get("fileIdentifier"));
		NirParam.put("language"         ,(String)param.get("language"));  
		NirParam.put("targetName"       ,(String)param.get("targetName"));       
		NirParam.put("resPostname"      ,(String)param.get("resPostname"));
		NirParam.put("resPhone"         ,(String)param.get("resPhone"));         
		NirParam.put("resFax"           ,(String)param.get("resFax"));
		NirParam.put("resAddress"       ,(String)param.get("resAddress"));
		NirParam.put("resPostalCode"    ,(String)param.get("resPostalCode"));
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		NirParam.put("resolution"       ,(String)param.get("resolution"));
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}

		int list = managementService.updateAdjustMetaNirmapIdent(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustNirBasemetaDts.do")
	public ModelAndView updateAdjustNirBasemetaDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("resOfficename"    ,(String)param.get("resOfficename"));    
		NirParam.put("metaDate"         ,(String)param.get("metaDate"));
		NirParam.put("metaVersion"      ,(String)param.get("metaVersion"));
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		NirParam.put("spatialData"      ,(String)param.get("spatialData"));
		NirParam.put("distFormatName"   ,(String)param.get("distFormatName"));
		NirParam.put("distFormatVersion",(String)param.get("distFormatVersion"));
		NirParam.put("distOfficename"   ,(String)param.get("distOfficename"));
		NirParam.put("distPostname"     ,(String)param.get("distPostname"));
		NirParam.put("distPhone"        ,(String)param.get("distPhone"));
		NirParam.put("qualityTarget"    ,(String)param.get("qualityTarget"));
		NirParam.put("scan"             ,(String)param.get("scan"));
		NirParam.put("film"             ,(String)param.get("film"));
		NirParam.put("document"         ,(String)param.get("document"));
		                                
		
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustNirBasemetaDts(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustNirZoneDts.do")
	public ModelAndView updateAdjustNirZoneDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		NirParam.put("scale"            ,(String)param.get("scale"));
		                                
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustNirZoneDts(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(제약정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustMetaNirmapContr.do")
	public ModelAndView updateAdjustMetaNirmapContr(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		NirParam.put("securityCde"      ,(String)param.get("securityCde"));
		
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustMetaNirmapContr(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustNirOrientmapDts.do")
	public ModelAndView updateAdjustNirOrientmapDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		NirParam.put("map_num"          ,(String)param.get("map_num"));
		NirParam.put("map_nam"          ,(String)param.get("map_nam"));
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustNirOrientmapDts(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(획득정보)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustMetaNirmapAcqut.do")
	public ModelAndView updateAdjustMetaNirmapAcqut(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		NirParam.put("cmrType"          ,(String)param.get("cmrType"));          
		NirParam.put("cmrNum"           ,(String)param.get("cmrNum"));
		NirParam.put("pilot"            ,(String)param.get("pilot"));
		NirParam.put("cmrMan"           ,(String)param.get("cmrMan"));
		NirParam.put("notePlane"        ,(String)param.get("notePlane"));
		NirParam.put("lensNum"          ,(String)param.get("lensNum"));
		NirParam.put("phDate"           ,(String)param.get("phDate"));
		
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustMetaNirmapAcqut(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getNirNoteList.do")
	public ModelAndView getNirNoteList(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("filmNum"         ,(String)param.get("filmNum"));
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		if(NirParam.get("phCourse").equals("전체")){
			NirParam.put("phCourse", null);
		}
		if(NirParam.get("filmNum").equals("전체")){
			NirParam.put("filmNum", null);
		}
		
		PagingUtil.getInstance().setPageData(param, NirParam, modelAndView, managementService.getNirNoteListCnt(NirParam));
		
		List<?> zoneList = null;
		
		zoneList= managementService.getNirNoteList(NirParam);
		
		modelAndView.addObject("list", zoneList);
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 플름번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/filmNumNir.do")
	public ModelAndView getFilmNumNir(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		
		if(NirParam.get("phCourse").equals("전체")){
			NirParam.put("phCourse", null);
		}
		
		List<?> list = managementService.getFilmNumNir(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subNirNoteList.do")
	public ModelAndView subNirNoteList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneCode", (String)param.get("zoneCode"));
		NirParam.put("phCourse", (String)param.get("phCourse"));
		NirParam.put("filmNum", (String)param.get("filmNum"));
		
	
		List<?> list = managementService.getSubNirNoteList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustNirNoteList.do")
	public ModelAndView updateAdjustNirNoteList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("zoneYy"         ,(String)param.get("zoneYy"));
		NirParam.put("zoneNam"         ,(String)param.get("zoneNam"));
		NirParam.put("phDate"            ,(String)param.get("phDate"));
		NirParam.put("noteDate"          ,(String)param.get("noteDate"));          
		NirParam.put("lensNum"           ,(String)param.get("lensNum"));
		NirParam.put("phCourse"            ,(String)param.get("phCourse"));
		NirParam.put("phNum"           ,(String)param.get("phNum"));
		NirParam.put("filmNum"        ,(String)param.get("filmNum"));
		NirParam.put("rptMan"          ,(String)param.get("rptMan"));
		NirParam.put("courseCount"           ,(String)param.get("courseCount"));
		NirParam.put("phDrct"           ,(String)param.get("phDrct"));
		NirParam.put("phAngle"           ,(String)param.get("phAngle"));
		NirParam.put("windDirect"           ,(String)param.get("windDirect"));
		NirParam.put("windSpeed"           ,(String)param.get("windSpeed"));
		NirParam.put("zoneCode"           ,(String)param.get("zoneCode"));
		
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustNirNoteDts(NirParam);
		int list2 = managementService.updateAdjustNirNotecourseDts(NirParam);
		int list3 = managementService.updateAdjustNirCoursetestDts(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/카메라정보 카메라 구분, 보유업체 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getNirCameraDts.do")
	public ModelAndView getNirCameraDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getNirCameraDts();
		List<?> list2 = managementService.getNirkeepCmpn();
		
		modelAndView.addObject("list", list);
		modelAndView.addObject("list2", list2);
		
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/카메라정보 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getNirCameraDtsList.do")
	public ModelAndView getNirCameraDtsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("camCde", (String)param.get("camCde"));
		NirParam.put("keepCmpn", (String)param.get("keepCmpn"));
		
		if(NirParam.get("camCde").equals("전체")){
			NirParam.put("camCde", null);
		}
		if(NirParam.get("keepCmpn").equals("전체")){
			NirParam.put("keepCmpn", null);
		}
		
		List<?> list = managementService.getNirCameraDtsList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - NIR 관리/카메라정보 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustNirCameraDts.do")
	public ModelAndView updateAdjustNirCameraDts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("lensNum"         ,(String)param.get("lensNum"));
		NirParam.put("focusDis"         ,(String)param.get("focusDis"));
		NirParam.put("camCde",(String)param.get("camCde"));
		NirParam.put("cmrCmpn",(String)param.get("cmrCmpn"));          
		NirParam.put("calDate",(String)param.get("calDate"));
		NirParam.put("keepCmpn",(String)param.get("keepCmpn"));
		NirParam.put("fmc",(String)param.get("fmc"));
		NirParam.put("fmcDate",(String)param.get("fmcDate"));
		NirParam.put("cmrType",(String)param.get("cmrType"));
		NirParam.put("page_1",(String)param.get("page1"));
		
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustNirCameraDts(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - NIR 관리/성과 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustProductNirList.do")
	public ModelAndView updateAdjustProductNirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("aphNum"         ,(String)param.get("aphNum"));
		NirParam.put("aphNow"         ,(String)param.get("aphNow"));
		NirParam.put("filmNum",(String)param.get("filmNum"));
		NirParam.put("filmNow",(String)param.get("filmNow"));          
		NirParam.put("rphNum",(String)param.get("rphNum"));
		NirParam.put("rphNow",(String)param.get("rphNow"));
		NirParam.put("md1Num",(String)param.get("md1Num"));
		NirParam.put("md2Num",(String)param.get("md2Num"));
		NirParam.put("remark",(String)param.get("remark"));
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustProductNirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - NIR 관리/성과 수정(외부표정요소)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustProductEONirList.do")
	public ModelAndView updateAdjustProductEONirList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> NirParam =  new HashMap<String, Object>();
		
		NirParam.put("eoX"         ,(String)param.get("eoX"));
		NirParam.put("eoY"         ,(String)param.get("eoY"));
		NirParam.put("eoZ",(String)param.get("eoZ"));
		NirParam.put("eoOmega",(String)param.get("eoOmega"));          
		NirParam.put("eoPhi",(String)param.get("eoPhi"));
		NirParam.put("eoKappa",(String)param.get("eoKappa"));
		NirParam.put("crsIdn",(String)param.get("crsIdn"));
		NirParam.put("zoneCode"         ,(String)param.get("zoneCode"));
		NirParam.put("phCourse"         ,(String)param.get("phCourse"));
		NirParam.put("phNum"            ,(String)param.get("phNum"));
		
		if (NirParam != null) {
			Iterator<String> iter = NirParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (NirParam.get(key).equals("-")) {
					NirParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustProductEONirList(NirParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	
	
	
	
	
	//////////////////////////////////////////////////////측량 조서////////////////////////////////////////////////////
	
	
	
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 사업연도 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getYearPts.do")
	public ModelAndView getYearPts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getYearPts();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 사업지구명 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getZoneNamPts.do")
	public ModelAndView getZoneNamPts(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> PtsParam =  new HashMap<String, Object>();

		PtsParam.put("sYearPtsCoord", (String)param.get("sYearPtsCoord"));
		PtsParam.put("eYearPtsCoord", (String)param.get("eYearPtsCoord"));
		
		
		if(PtsParam.get("sYearPtsCoord").equals("")){
			PtsParam.put("sYearPtsCoord", null);
		}
		if(PtsParam.get("eYearPtsCoord").equals("")){
			PtsParam.put("eYearPtsCoord", null);
		}
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getZoneNamPts(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getPtsList.do")
	public ModelAndView getPtsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		Map<String, Object> PtsParam =  new HashMap<String, Object>();
				
		PtsParam.put("sYearPts", (String)param.get("sYearPts"));
		PtsParam.put("eYearPts", (String)param.get("eYearPts"));	
		
		PagingUtil.getInstance().setPageData(param, PtsParam, modelAndView, managementService.getPtsListCnt(PtsParam));

		List<?> list = managementService.getPtsList(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 상세 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/subPtsList.do")
	public ModelAndView getSubPtsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> PtsParam =  new HashMap<String, Object>();

		PtsParam.put("survCode", (String)param.get("survCode"));
		PtsParam.put("p_num", (String)param.get("p_num"));
		

		List<?> list = managementService.getSubPtsList(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubPtsList.do")
	public ModelAndView updateAdjustSubPtsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> PtsParam =  new HashMap<String, Object>();
		
		PtsParam.put("survCode"      ,(String)param.get("survCode"));
		PtsParam.put("survNam"       ,(String)param.get("survNam"));
		PtsParam.put("prjNam"    	   ,(String)param.get("prjNam"));
		PtsParam.put("book"      		,(String)param.get("book"));          
		PtsParam.put("prjComp"    	,(String)param.get("prjComp"));
		PtsParam.put("prjYy"      	,(String)param.get("prjYy"));
		PtsParam.put("survtype"   	,(String)param.get("survtype"));
		PtsParam.put("survwayCde"     ,(String)param.get("survwayCde"));
		
		if (PtsParam != null) {
			Iterator<String> iter = PtsParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (PtsParam.get(key).equals("-")) {
					PtsParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustSubPtsList(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubPtsList.do")
	public ModelAndView insertSubPtsList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> PtsParam =  new HashMap<String, Object>();
		
		PtsParam.put("survCode"      ,(String)param.get("survCode"));
		PtsParam.put("survNam"       ,(String)param.get("survNam"));
		PtsParam.put("prjNam"    	   ,(String)param.get("prjNam"));
		PtsParam.put("book"      		,(String)param.get("book"));          
		PtsParam.put("prjComp"    	,(String)param.get("prjComp"));
		PtsParam.put("prjYy"      	,(String)param.get("prjYy"));
		PtsParam.put("survtype"   	,(String)param.get("survtype"));
		PtsParam.put("survwayCde"     ,(String)param.get("survwayCde"));
		
		if (PtsParam != null) {
			Iterator<String> iter = PtsParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (PtsParam.get(key).equals("-")) {
					PtsParam.put(key, null);
				}
			}
		}


		String list = managementService.insertSubPtsList(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 점일련번호 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getPNum.do")
	public ModelAndView getPNum(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> PtsParam =  new HashMap<String, Object>();

		PtsParam.put("survCode", (String)param.get("survCode"));
		PtsParam.put("survpts", (String)param.get("survpts"));

		if(PtsParam.get("survpts").equals("전체")){
			PtsParam.put("survpts", null);
		}

		
		List<?> list = managementService.getPNum(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 목록 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getPtsCoordList.do")
	public ModelAndView getPtsCoordList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> PtsParam =  new HashMap<String, Object>();

		PtsParam.put("survCode", (String)param.get("survCode"));
		PtsParam.put("pNum", (String)param.get("pNum"));
		PtsParam.put("survpts", (String)param.get("survpts"));
		

		if(PtsParam.get("pNum").equals("전체")){
			PtsParam.put("pNum", null);
		}
		if(PtsParam.get("survpts").equals("전체")){
			PtsParam.put("survpts", null);
		}
		
		PagingUtil.getInstance().setPageData(param, PtsParam, modelAndView, managementService.getPtsCoordListCnt(PtsParam));
		
		List<?> list = managementService.getPtsCoordList(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 측량조서 분류 조회
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getSurvCode.do")
	public ModelAndView getSurvCode(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		List<?> list = managementService.getSurvCode();
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 수정
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/adjustSubPtsCoordList.do")
	public ModelAndView updateAdjustSubPtsCoordList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> PtsParam =  new HashMap<String, Object>();
		
		PtsParam.put("survptsCde" 		  ,(String)param.get("survptsCde")); 	
		PtsParam.put("survCode" 		  ,(String)param.get("survCode")); 	
		PtsParam.put("pNum"		 		  ,(String)param.get("pNum"));		 
		PtsParam.put("pntName"			  ,(String)param.get("pntName"));		
		PtsParam.put("phNum"			  ,(String)param.get("phNum"));		 
		PtsParam.put("do" 	    		  ,(String)param.get("do")); 	    	
		PtsParam.put("gun"				  ,(String)param.get("gun"));			
		PtsParam.put("myeon"		 	  ,(String)param.get("myeon"));		
		PtsParam.put("ri"		    	  ,(String)param.get("ri"));		 
		PtsParam.put("originGrd"          ,(String)param.get("originGrd")); 
		PtsParam.put("route"              ,(String)param.get("route"));     
		PtsParam.put("des"                ,(String)param.get("des"));       
		PtsParam.put("rptDate"            ,(String)param.get("rptDate"));   
		PtsParam.put("grdcoorX"           ,(String)param.get("grdcoorX"));  
		PtsParam.put("grdcoorY"           ,(String)param.get("grdcoorY"));  
		PtsParam.put("grdcoorZ"           ,(String)param.get("grdcoorZ"));  
		PtsParam.put("grdcrsIdn"          ,(String)param.get("grdcrsIdn")); 
		PtsParam.put("pGrdcoorX"          ,(String)param.get("pGrdcoorX")); 
		PtsParam.put("pGrdcoorY"          ,(String)param.get("pGrdcoorY")); 
		PtsParam.put("pGrdcrsIdn"         ,(String)param.get("pGrdcrsIdn"));
		
		if (PtsParam != null) {
			Iterator<String> iter = PtsParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (PtsParam.get(key).equals("-")) {
					PtsParam.put(key, null);
				}
			}
		}


		int list = managementService.updateAdjustSubPtsCoordList(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 등록
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/insertSubPtsCoordList.do")
	public ModelAndView insertSubPtsCoordList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> PtsParam =  new HashMap<String, Object>();
		
		PtsParam.put("survptsCde" 		  ,(String)param.get("survptsCde")); 	
		PtsParam.put("survCode" 		  ,(String)param.get("survCode")); 	
		PtsParam.put("pNum"		 		  ,(String)param.get("pNum"));		 
		PtsParam.put("pntName"			  ,(String)param.get("pntName"));		
		PtsParam.put("phNum"			  ,(String)param.get("phNum"));		 
		PtsParam.put("do" 	    		  ,(String)param.get("do")); 	    	
		PtsParam.put("gun"				  ,(String)param.get("gun"));			
		PtsParam.put("myeon"		 	  ,(String)param.get("myeon"));		
		PtsParam.put("ri"		    	  ,(String)param.get("ri"));		 
		PtsParam.put("originGrd"          ,(String)param.get("originGrd")); 
		PtsParam.put("route"              ,(String)param.get("route"));     
		PtsParam.put("des"                ,(String)param.get("des"));       
		PtsParam.put("rptDate"            ,(String)param.get("rptDate"));   
		PtsParam.put("grdcoorX"           ,(String)param.get("grdcoorX"));  
		PtsParam.put("grdcoorY"           ,(String)param.get("grdcoorY"));  
		PtsParam.put("grdcoorZ"           ,(String)param.get("grdcoorZ"));  
		PtsParam.put("grdcrsIdn"          ,(String)param.get("grdcrsIdn")); 
		PtsParam.put("pGrdcoorX"          ,(String)param.get("pGrdcoorX")); 
		PtsParam.put("pGrdcoorY"          ,(String)param.get("pGrdcoorY")); 
		PtsParam.put("pGrdcrsIdn"         ,(String)param.get("pGrdcrsIdn"));
		
		if (PtsParam != null) {
			Iterator<String> iter = PtsParam.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (PtsParam.get(key).equals("-")) {
					PtsParam.put(key, null);
				}
			}
		}


		String list = managementService.insertSubPtsCoordList(PtsParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
		
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/delPts.do")
	public ModelAndView delPts(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] survCode = request.getParameterValues("survCode[]");

		
		Map<String, Object> ptsParam =  new HashMap<String, Object>();
		

		if(survCode != null && survCode.length > 0){
			for(int i=0; i<survCode.length; i++){
				if ( survCode[i].equals("-")) {
					survCode[i] = null;
				}
				
				ptsParam.put("survCode" ,survCode[i]);
				int list = managementService.delPts(ptsParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 삭제
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/delPtsCoord.do")
	public ModelAndView delPtsCoord(HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");		
		
		String[] survCode = request.getParameterValues("survCode[]");
		String[] survptsCde = request.getParameterValues("survptsCde[]");
		String[] pNum = request.getParameterValues("pNum[]");

		
		Map<String, Object> ptsParam =  new HashMap<String, Object>();
		

		if(survCode != null && survCode.length > 0){
			for(int i=0; i<survCode.length; i++){
				if ( survCode[i].equals("-")) {
					survCode[i] = null;
				}
				if ( survptsCde[i].equals("-")) {
					survptsCde[i] = null;
				}
				if ( pNum[i].equals("-")) {
					pNum[i] = null;
				}
				
				ptsParam.put("survCode" ,survCode[i]);
				ptsParam.put("survptsCde" ,survptsCde[i]);
				ptsParam.put("pNum" ,pNum[i]);
				int list = managementService.delPtsCoord(ptsParam);
			}
		}

		return modelAndView;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getPtsListExcel.do")
	public String getPtsListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> PtsParam =  new HashMap<String, Object>();
		
		PtsParam.put("sYearPts", (String)param.get("sYearPts"));
		PtsParam.put("eYearPts", (String)param.get("eYearPts"));	
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
	
		model.addAttribute("fileName", "측량사업지구 목록");
		model.addAttribute("name", "측량사업지구 목록");
		model.addAttribute("excelList",  managementService.getPtsList(PtsParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getPtsListCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getPtsListCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("survCode::측량지구코드");
		mapper.add("survNam::측량지구명");
		mapper.add("prjNam::제작년도");
		mapper.add("prjYy::제작년도");
		mapper.add("survtypeCde::측량사업구분");
		mapper.add("survwayCde::작업방식");
		mapper.add("book::측량지구책번호");
		mapper.add("prjComp::제작업체");

		return mapper;
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 목록 엑셀 출력
	 * @param model
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/management/getPtsCoordListExcel.do")
	public String getPtsCoordListExcel(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam Map<String, Object> param) throws Exception {

		Map<String, Object> PtsParam =  new HashMap<String, Object>();
		
		PtsParam.put("survCode", (String)param.get("survCode"));
		PtsParam.put("survpts", (String)param.get("survpts"));	
		PtsParam.put("pNum", (String)param.get("pNum"));	
		
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
	
		if(PtsParam.get("survpts").equals("전체")){
			PtsParam.put("survpts", null);
		}
		if(PtsParam.get("pNum").equals("전체")){
			PtsParam.put("pNum", null);
		}
		
		
		model.addAttribute("fileName", "지상기준점관리 목록");
		model.addAttribute("name", "지상기준점관리 목록");
		model.addAttribute("excelList",  managementService.getPtsCoordList(PtsParam));
		//model.addAttribute("colHeads", getDbAppReqHead());
		model.addAttribute("colNames", getPtsListCoordCols());
		
		return "excelView";
		
	}
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 목록 엑셀 출력 항목
	 * @return List
	 * @throws Exception
	 */
	public List<String> getPtsListCoordCols() throws Exception {
		List<String> mapper = new ArrayList<String>();

		mapper.add("survCode::측량지구코드");
		mapper.add("survNam::측량지구명");
		mapper.add("prjNam::측량사업명");
		mapper.add("survptsCde::측량조서구분코드");
		mapper.add("pNum::점일련번호");
		mapper.add("pntName::점번호");
		mapper.add("phNum::조서사진번호");

		return mapper;
	}
	
	
	
	

	
	
	// 2023.08.08
	//////////////////////////////////////////////////////AT성과////////////////////////////////////////////////////
	
	/**
	 * 통합관리 - AT성과 상세 목록 조회(메타데이터)
	 * @param param
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws Exception
	 */	
	@RequestMapping(value = "/management/subMetaAtList.do")
	public ModelAndView subMetaAtList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView("jsonView");
				
		Map<String, Object> airParam =  new HashMap<String, Object>();
		
		airParam.put("zoneCode", (String)param.get("zoneCode"));

		List<?> list = managementService.getSubProductAtList(airParam);
		modelAndView.addObject("list", list);
		
		return modelAndView;
	}
}

