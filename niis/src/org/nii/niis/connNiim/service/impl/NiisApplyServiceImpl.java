package org.nii.niis.connNiim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.NiisApplyService;
import org.nii.niis.niim.service.impl.ApplyDAO;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;

@Service("niisApplyService")
public class NiisApplyServiceImpl implements NiisApplyService {

	@Resource(name="propertiesService")
    private EgovPropertyService propertyService;
	
	@Resource(name="applyDAO")
	private ApplyDAO applyDAO;
	
	@Resource(name="niisApplyDAO")
	private NiisApplyDAO niisApplyDAO;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> regApply(Map<String, Object> sendMap) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			Map<String, Object> appMap = new HashMap<String, Object>();
			
			appMap.putAll(sendMap);
			appMap.remove("imgList");
			
			// 자료신청관리 테이블 인서트
			String supIdn = applyDAO.regDbApp(appMap);
			String usrMgno = (String)sendMap.get("connUserNo");
			
			// 유저 자료신청 매핑테이블 인서트
			appMap.put("supIdn", supIdn);
			appMap.put("usrMgno", usrMgno);
			niisApplyDAO.regUserDbApp(appMap);

			List<Map<String, Object>> imgList = (List<Map<String, Object>>)sendMap.get("imgList");
			
			//승인 구분 넣기 위함 2023-11-15
			boolean isIim = false;	//영상이 있다면 true
			boolean isMap = false;	//수치지형도가 있다면 true
			
			for(int i=0; i<imgList.size(); i++){
				Map<String, Object> appSupMap = imgList.get(i);
				appSupMap.put("supIdn", supIdn);
				appSupMap.put("usrMgno", usrMgno);
				
				String imageCde = (String)appSupMap.get("imageCde");
				
				// 항공사진
				if(imageCde.equals(propertyService.getString("Globals.airImageCode"))){
					
					appSupMap.put("zoneCode", appSupMap.get("keyVal1"));
					appSupMap.put("phCourse", appSupMap.get("keyVal2"));
					appSupMap.put("phNum", appSupMap.get("keyVal3"));
					
					niisApplyDAO.regUserDbAppAir(appSupMap);
					
					isIim = true;
				}
				// 해방전후 항공사진
				else if(imageCde.equals(propertyService.getString("Globals.airLibImageCode"))){
					appSupMap.put("zoneCode", appSupMap.get("keyVal1"));
					appSupMap.put("phCourse", appSupMap.get("keyVal2"));
					appSupMap.put("phNum", appSupMap.get("keyVal3"));
					
					niisApplyDAO.regUserDbAppAir(appSupMap);
					
					isIim = true;
				}
				// 수치표고
				else if(imageCde.equals(propertyService.getString("Globals.demImageCode"))){
					appSupMap.put("zoneCode", appSupMap.get("keyVal1"));
					appSupMap.put("map5000Num", appSupMap.get("keyVal2"));
					appSupMap.put("gridInt", appSupMap.get("keyVal3"));
					
					niisApplyDAO.regUserDbAppDem(appSupMap);
					
					isIim = true;
				}
				// 정사영상
				else if(imageCde.equals(propertyService.getString("Globals.ortImageCode"))){
					appSupMap.put("zoneCode", appSupMap.get("keyVal1"));
					appSupMap.put("map5000Num", appSupMap.get("keyVal2"));
					appSupMap.put("gtypDst", appSupMap.get("keyVal3"));
					
					niisApplyDAO.regUserDbAppOrt(appSupMap);
					
					isIim = true;
				}
				// AT성과
				else if(imageCde.equals("atCde")){
					appSupMap.put("zoneCode", appSupMap.get("keyVal1"));
					
					niisApplyDAO.regUserDbAppAt(appSupMap);
					
					isIim = true;
				}
				// 수치지형도
				else if(imageCde.equals(propertyService.getString("Globals.mapImageCode"))) {
					appSupMap.put("mapShtNo", 		appSupMap.get("keyVal1"));
					appSupMap.put("mapSerNo", 		appSupMap.get("keyVal2"));
					appSupMap.put("mapHistoryNo", 	appSupMap.get("keyVal3"));
					
					niisApplyDAO.regUserDbAppMap(appSupMap);
					
					isMap = true;
				}
				
			}
			
			if(isIim) {
				niisApplyDAO.uptUserDbAppIim(appMap);
			}
			
			if(isMap) {
				niisApplyDAO.uptUserDbAppMap(appMap);
			}
			returnMap.put("supIdn", supIdn);
			returnMap.put("isSuccess", "1");
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> regApplyAll(Map<String, Object> sendMap) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			Map<String, Object> appMap = new HashMap<String, Object>();
			
			appMap.putAll(sendMap);
			appMap.remove("imgList");
			
			//자료신청관리 테이블 인서트
			String supIdn = applyDAO.regDbApp(appMap);
			String usrMgno = (String)sendMap.get("connUserNo");
			
			//유저 자료신청 매핑테이블 인서트
			appMap.put("supIdn", supIdn);
			appMap.put("usrMgno", usrMgno);
			niisApplyDAO.regUserDbApp(appMap);

			List<Map<String, Object>> imgList = (List<Map<String, Object>>)sendMap.get("imgList");
			
			for(int i=0; i<imgList.size(); i++){
				Map<String, Object> appSupMap = imgList.get(i);
				appSupMap.put("supIdn", supIdn);
				appSupMap.put("usrMgno", usrMgno);
				
				String imageCde = (String)appSupMap.get("imageCde");
				
				//항공사진
				if(imageCde.equals(propertyService.getString("Globals.airImageCode"))){
					appSupMap.put("zoneCode", appSupMap.get("zoneCode"));
					appSupMap.put("phCourse", appSupMap.get("phCourse"));
					appSupMap.put("phNum", appSupMap.get("phNum"));
					
					niisApplyDAO.regUserDbAppAir(appSupMap);
				}
				// 항공사진(해방전후)
				else if(imageCde.equals(propertyService.getString("Globals.airLibImageCode"))){
					appSupMap.put("zoneCode", appSupMap.get("zoneCode"));
					appSupMap.put("phCourse", appSupMap.get("phCourse"));
					appSupMap.put("phNum", appSupMap.get("phNum"));
					
					niisApplyDAO.regUserDbAppAir(appSupMap);
				}
				//수치표고
				else if(imageCde.equals(propertyService.getString("Globals.demImageCode"))){
					appSupMap.put("zoneCode", appSupMap.get("zoneCode"));
					appSupMap.put("map5000Num", appSupMap.get("map5000Num"));
					appSupMap.put("gridInt", appSupMap.get("gridInt"));
					
					niisApplyDAO.regUserDbAppDem(appSupMap);
				}
				//정사영상
				else if(imageCde.equals(propertyService.getString("Globals.ortImageCode"))){
					appSupMap.put("zoneCode", appSupMap.get("zoneCode"));
					appSupMap.put("map5000Num", appSupMap.get("map5000Num"));
					appSupMap.put("gtypDst", appSupMap.get("gtypDst"));
					
					niisApplyDAO.regUserDbAppOrt(appSupMap);
				}
				//AT성과
				else if(imageCde.equals("atCde")){
					appSupMap.put("zoneCode", appSupMap.get("zoneCode"));
					
					niisApplyDAO.regUserDbAppAt(appSupMap);
				}
				
			}
			returnMap.put("supIdn", supIdn);
			returnMap.put("isSuccess", "1");
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}

	@Override
	public int getUserApplyListCnt(Map<String, Object> sendMap) throws Exception {
		return niisApplyDAO.getUserApplyListCnt(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getUserApplyList(Map<String, Object> sendMap) throws Exception {
		return niisApplyDAO.getUserApplyList(sendMap);
	}

	@Override
	public int applyExtension(Map<String, Object> sendMap) throws Exception {
		return niisApplyDAO.applyExtension(sendMap);
	}
	
	@Override
	public Map<String, Object> reRegApply(Map<String, Object> sendMap) throws Exception {
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try{
			String usrMgno = (String)sendMap.get("connUserNo");
			
			sendMap.put("supDate", "");
			sendMap.put("usrMgno", usrMgno);
			sendMap.put("approvalCde", "0");
			sendMap.put("rejectCause", "");
			sendMap.put("lastChangeUsr", usrMgno);
			
			applyDAO.uptDbApp(sendMap);
			applyDAO.uptDbAppAuth(sendMap);
			
			returnMap.put("isSuccess", "1");
		}catch(Exception e){
			e.printStackTrace();
			throw e;	//트랜잭션 rollback 처리를 위해 throw
		}
		
		return returnMap;
	}

	
	@Override
	public List<Map<String, Object>> getPurposeList(String codeId) throws Exception {
		
		List<Map<String, Object>> codeList = niisApplyDAO.getPurposeList(codeId);
		
		if(codeId != null) {
			// 신청목적 상세 (DETAIL_CODE_ID, DETAIL_CODE_NM을 출력히기 위한 리스트)
			return codeList;
		}
		
		// codeId 중복 제거용 
		List<String> purposeSet = new ArrayList<String>();
		// 중복이 제거된 purposeList가 들어갈 리스트
		List<Map<String, Object>> purposeList = new ArrayList<Map<String,Object>>();
		
		for(Map<String,Object> map : codeList) {
			
			String code = (String) map.get("codeId");
			
			if(!purposeSet.contains(code)){
				purposeSet.add(code);
				purposeList.add(map);
			}
		}
		
		// 신청목적 (CODE_ID, CN_DE를 출력하기 위한 리스트)
		return purposeList;
	}

}
