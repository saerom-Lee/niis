package org.nii.niis.map.search.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.map.search.service.MapSearchPostgresService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/search/map/")
public class MapSearchPostgresController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="mapSearchPostgresService")
	private MapSearchPostgresService mapSearchPostgresService;

	/**
	 * 지번 검색
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/selectJibunList.do")
	public @ResponseBody List<HashMap<String,Object>> selectJibunList(@RequestParam HashMap<String, Object> param){
		return mapSearchPostgresService.selectJibunList(param);
	}
	
	
	/**
	 * 도로명 검색
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/selectJusoList.do")
	public @ResponseBody List<HashMap<String,Object>> selectJusoList(@RequestParam HashMap<String, Object> param){
		return mapSearchPostgresService.selectJusoList(param);
	}
	
}
