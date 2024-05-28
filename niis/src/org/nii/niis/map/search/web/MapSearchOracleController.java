package org.nii.niis.map.search.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.map.search.service.MapSearchOracleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/search/map/")
public class MapSearchOracleController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="mapSearchOracleService")
	private MapSearchOracleService mapSearchOracleService;

	/**
	 * 도로명 주소 - 읍면 목록 조회
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/juso/selectEmdList.do")
	public @ResponseBody HashMap<String,Object> selectJibunList(@RequestParam HashMap<String, Object> param){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", mapSearchOracleService.selectEmdList(param));
		return result;
	}
	
	/**
	 * 도로명 주소 - 도로명 목록 조회
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/juso/selectRnNmList.do")
	public @ResponseBody HashMap<String,Object> selectRnNmList(@RequestParam HashMap<String, Object> param){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", mapSearchOracleService.selectRnNmList(param));
		return result;
	}
	
	
	/**
	 * 인덱스 - 축척 조회
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/selectIndexScale.do")
	public @ResponseBody HashMap<String,Object> selectIndexScale() {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", mapSearchOracleService.selectIndexScale());
		return result;
	}
	
	
	/**
	 * 인덱스 - 도엽명 초성 조회
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/selectIndexInitName.do")
	public @ResponseBody HashMap<String,Object> selectIndexInitName(@RequestParam HashMap<String, Object> param) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", mapSearchOracleService.selectIndexInitName(param));
		return result;
	}
	
	
	/**
	 * 인덱스 - 도엽명 조회
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/selectIndexName.do")
	public @ResponseBody HashMap<String,Object> selectIndexName(@RequestParam HashMap<String, Object> param) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", mapSearchOracleService.selectIndexName(param));
		return result;
	}
	
	/**
	 * 인덱스 - 도엽번호 조회
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/selectIndexNum.do")
	public @ResponseBody HashMap<String,Object> selectIndexNum(@RequestParam HashMap<String, Object> param) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", mapSearchOracleService.selectIndexNum(param));
		return result;
	}
	
	/**
	 * 인덱스 - 도엽번호 직접조회
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/selectIndexNum2.do")
	public @ResponseBody HashMap<String,Object> selectIndexNum2(@RequestParam(value="num") String param) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", mapSearchOracleService.selectIndexNum2(param));
		return result;
	}
	
}
