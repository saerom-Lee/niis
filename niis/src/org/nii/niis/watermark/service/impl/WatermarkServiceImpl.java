package org.nii.niis.watermark.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.SearchService;
import org.nii.niis.niim.service.impl.ApplyDAO;
import org.nii.niis.watermark.service.WatermarkService;
import org.springframework.stereotype.Service;

@Service("watermarkService")
public class WatermarkServiceImpl implements WatermarkService {
	
	@Resource(name="watermarkDAO")
	private WatermarkDAO watermarkDAO;
	
	@Resource(name="niimSearchService")
    private SearchService searchService;
	
	@Resource(name="applyDAO")
	private ApplyDAO applyDAO;

	@Override
	public List<?> selectApproval() throws Exception {
		return watermarkDAO.selectApproval();
	}

	@Override
	public Map<String, Object> itemList(Map<String, Object> param) throws Exception{
		param = this.getUsrSupplyitemList(param);
		
		String metaYn = applyDAO.metaCheck(param);
		
		param.put("metaCheck", metaYn);
		
		Map<String, Object> returnMap = searchService.regSupplyItem(param);
		
		applyDAO.UptExpiredDt(param);
		
		//returnMap.put("waterMarkWeight", param.get("waterMarkWeight"));
		
		return returnMap;
	}
	
	
	/**
	 * 공급시스템 영상신청내역 기준 영상데이터 조회
	 * @param supIdn
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	private Map<String, Object> getUsrSupplyitemList(Map<String, Object> param) throws Exception {
		
		String stoImgPath = String.valueOf(param.get("stoImgPath"));
		
		ArrayList<String> fileType = new ArrayList<String>();
		ArrayList<String> zoneCode = new ArrayList<String>();
		ArrayList<String> pathOne  = new ArrayList<String>();
		ArrayList<String> pathTwo  = new ArrayList<String>();
		ArrayList<String> fileName = new ArrayList<String>();
		
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap.put("supIdn", param.get("supIdn"));
		sendMap.put("supplyCheck", "Y");
		
		/*policyUtil.addPolicy(param, sendMap);*/
		System.out.println("============================getAirImgDataList start==================");
		List<Map<String, Object>> airList = watermarkDAO.getAirImgDataList(sendMap);
		System.out.println("==============================getAirImgDataList end====================");
		for(int i=0; i<airList.size(); i++){
			Map<String, Object> map = airList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("phCourse"));
			pathTwo.add((String)map.get("phNum"));
			fileName.add(stoImgPath + (String)map.get("filePath"));

		}
		List<Map<String, Object>> airLibList = watermarkDAO.getAirLibImgDataList(sendMap);
		for(int i=0; i<airLibList.size(); i++){
			Map<String, Object> map = airLibList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("phCourse"));
			pathTwo.add((String)map.get("phNum"));
			fileName.add(stoImgPath + (String)map.get("filePath"));
		}
		
		List<Map<String, Object>> demList = watermarkDAO.getDemImgDataList(sendMap);
		for(int i=0; i<demList.size(); i++){
			Map<String, Object> map = demList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("map5000Num"));
			pathTwo.add((String)map.get("gridInt"));
			fileName.add(stoImgPath + (String)map.get("filePath"));
		}
		List<Map<String, Object>> ortList = watermarkDAO.getOrtImgDataList(sendMap);
		for(int i=0; i<ortList.size(); i++){
			Map<String, Object> map = ortList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("map5000Num"));
			pathTwo.add(String.valueOf(map.get("gtypDst")));
			fileName.add(stoImgPath + (String)map.get("filePath"));
		}
		
		param.put("fileType", fileType);
		param.put("zoneCode", zoneCode);
		param.put("pathOne", pathOne);
		param.put("pathTwo", pathTwo);
		param.put("fileName", fileName);
		
		return param;
	}
	
		
}
