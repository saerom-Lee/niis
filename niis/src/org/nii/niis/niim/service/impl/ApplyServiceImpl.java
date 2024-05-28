package org.nii.niis.niim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.impl.NiisSearchDAO;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.service.SearchService;
import org.nii.niis.niim.util.PolicyUtil;
import org.springframework.stereotype.Service;

@Service("applyService")
public class ApplyServiceImpl implements ApplyService {

	@Resource(name="niimSearchService")
    private SearchService searchService;
	
	@Resource(name="applyDAO")
	private ApplyDAO applyDAO;

	@Resource(name="niisSearchDAO")
	private NiisSearchDAO niisSearchDAO;
	
	@Resource(name="policyUtil")
	private PolicyUtil policyUtil;
	
	
	/**
	 * 원본DB 신청서 관리 리스트카운트
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getDbAppListCnt(Map<String, Object> param) throws Exception {
		return applyDAO.getDbAppListCnt(param);
	}
	/**
	 * 원본DB 신청서 관리 리스트
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getDbAppList(Map<String, Object> param) throws Exception {
		return applyDAO.getDbAppList(param);
	}

	
	/**
	 * 원본DB 신청서 관리 상세
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getDbAppDetail(Map<String, Object> param) throws Exception {
		return applyDAO.getDbAppDetail(param);
	}
	
	
	/**
	 * 원본DB 신청서 관리 상세 발급내역
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getDbAppReqList(Map<String, Object> param) throws Exception {
		return applyDAO.getDbAppReqList(param);
	}
	
	
	/**
	 * 원본DB 신청서 관리 상세 결과보고서
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getDbAppRstReview(Map<String, Object> param) throws Exception {
		return applyDAO.getDbAppRstReview(param);
	}

	
	/**
	 * 원본DB 신청서 등록
	 * @param param
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String regDbApp(Map<String, Object> sendMap) throws Exception {
		return applyDAO.regDbApp(sendMap);
	}

	
	/**
	 * 원본DB 신청서 수정
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int uptDbApp(Map<String, Object> sendMap) throws Exception {
		return applyDAO.uptDbApp(sendMap);
	}

	
	/**
	 * 원본DB 신청서 삭제
	 * @param param
	 * @throws Exception
	 */
	@Override
	public void delDbApp(Map<String, Object> sendMap) throws Exception {
		applyDAO.delDbApp(sendMap);
		/*
		applyDAO.delDbAppDts(sendMap);
		applyDAO.delDbAppAirSupply(sendMap);
		applyDAO.delDbAppDemSupply(sendMap);
		applyDAO.delDbAppOrtSupply(sendMap);
		*/
	}
	
	@Override
	public List<?> getDbAppAuthList(Map<String, Object> param) throws Exception {
		return applyDAO.getDbAppAuthList(param);
	}
	
	@Override
	public void uptDbAppAuth(Map<String, Object> param) throws Exception {
		//사용자 신청 내역 업데이트
		applyDAO.uptDbAppAuth(param);
	}
	
	@Override
	public Map<String, Object> approvalDbAppAuth(Map<String, Object> param) throws Exception {
		
		param = this.getUsrSupplyitemList(param);
		
		param.put("metaCheck", param.get("metaYn"));
		
		Map<String, Object> returnMap = searchService.regSupplyItem(param);
		
		returnMap.put("waterMarkWeight", param.get("waterMarkWeight"));
		//searchService.createXML(returnMap);
		
		//사용자 신청 내역 업데이트
		applyDAO.uptDbAppAuth(param);
		
		//신청서 수정 -> searchService.regSupplyItem 으로 이동
		//applyDAO.uptDbApp(param);
		
		return returnMap;
	}
	
	@Override
	public void rejectDbAppAuth(Map<String, Object> param) throws Exception {
		applyDAO.uptDbAppAuth(param);
	}
	
	
	/**
	 * 공급시스템 영상신청내역 기준 영상데이터 조회
	 * @param supIdn
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	private Map<String, Object> getUsrSupplyitemList(Map<String, Object> param) throws Exception {
		
		ArrayList<String> fileType = new ArrayList<String>();
		ArrayList<String> zoneCode = new ArrayList<String>();
		ArrayList<String> pathOne  = new ArrayList<String>();
		ArrayList<String> pathTwo  = new ArrayList<String>();
		ArrayList<String> fileName = new ArrayList<String>();
		
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap.put("supIdn", param.get("supIdn"));
		
		//policyUtil.addPolicy(param, sendMap);
		
		List<Map<String, Object>> airList = niisSearchDAO.getAirImgDataList(sendMap);
		for(int i=0; i<airList.size(); i++){
			Map<String, Object> map = airList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("phCourse"));
			pathTwo.add((String)map.get("phNum"));
			fileName.add((String)map.get("filePath"));
		}
		List<Map<String, Object>> airLibList = niisSearchDAO.getAirLibImgDataList(sendMap);
		for(int i=0; i<airLibList.size(); i++){
			Map<String, Object> map = airLibList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("phCourse"));
			pathTwo.add((String)map.get("phNum"));
			fileName.add((String)map.get("filePath"));
		}
		
		List<Map<String, Object>> demList = niisSearchDAO.getDemImgDataList(sendMap);
		for(int i=0; i<demList.size(); i++){
			Map<String, Object> map = demList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("map5000Num"));
			pathTwo.add((String)map.get("gridInt"));
			fileName.add((String)map.get("filePath"));
		}
		List<Map<String, Object>> ortList = niisSearchDAO.getOrtImgDataList(sendMap);
		for(int i=0; i<ortList.size(); i++){
			Map<String, Object> map = ortList.get(i);
			
			fileType.add((String)map.get("imageCde"));
			zoneCode.add((String)map.get("zoneCode"));
			pathOne.add((String)map.get("map5000Num"));
			pathTwo.add((String)map.get("gtypDst"));
			fileName.add((String)map.get("filePath"));
		}
		
		param.put("fileType", fileType);
		param.put("zoneCode", zoneCode);
		param.put("pathOne", pathOne);
		param.put("pathTwo", pathTwo);
		param.put("fileName", fileName);
		
		return param;
	}
	@Override
	public List<Map<String, Object>> getSupIdnList() throws Exception {
		return applyDAO.getSupIdnList();
	}
	
	
	@Override
	public void uptDbApproval(Map<String,Object> map) throws Exception{
		applyDAO.uptDbApproval(map);
	}
	
	/**
	 * 신청정보 상세 list
	 */
	@Override
	public List<Map<String, Object>> getApplyDetailList(Map<String, Object> param) throws Exception {
		return applyDAO.getApplyDetailList(param);
	};
}
