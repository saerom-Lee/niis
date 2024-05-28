package org.nii.niis.connNiim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.NiisSupplyService;
import org.nii.niis.niim.service.MetaService;
import org.nii.niis.niim.service.impl.ApplyDAO;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;

@Service("niisSupplyService")
public class NiisSupplyServiceImpl implements NiisSupplyService {

	@Resource(name="propertiesService")
	private EgovPropertyService propertyService;
	
	@Resource(name="metaService")
	private MetaService metaService;
	
	@Resource(name="niisSupplyDAO")
	private NiisSupplyDAO niisSupplyDAO;
	
	@Resource(name="applyDAO")
	private ApplyDAO applyDAO;
	
	
	@Override
	public List<Map<String, Object>> getDownloadAirList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getDownloadAirList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDownloadAirLibList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getDownloadAirLibList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDownloadDemList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getDownloadDemList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDownloadOrtList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getDownloadOrtList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDownloadAtList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getDownloadAtList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDownloadMapList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getDownloadMapList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getAirFileList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getAirFileList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getAirLibFileList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getAirLibFileList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDemFileList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getDemFileList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getOrtFileList(Map<String, Object> sendMap) throws Exception {
		return niisSupplyDAO.getOrtFileList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getMetaFileList(Map<String, Object> sendMap) throws Exception {
		
		List<Map<String, Object>> metaFileList = new ArrayList<Map<String, Object>>();
		
		//String[] airMetaTable = metaService.getAirMetaTable();
		//String[] demMetaTable = metaService.getDemMetaTable();
		//String[] ortMetaTable = metaService.getOrtMetaTable();
		
		//List<Map<String, Object>> airMetaList = niisSupplyDAO.getAirMetaList(sendMap);
		//List<Map<String, Object>> demMetaList = niisSupplyDAO.getDemMetaList(sendMap);
		//List<Map<String, Object>> ortMetaList = niisSupplyDAO.getOrtMetaList(sendMap);
		
		/*
		metaFileList.addAll(setMetaFileList(niisSupplyDAO.getAirMetaList(sendMap), metaService.getAirMetaTable()));
		metaFileList.addAll(setMetaFileList(niisSupplyDAO.getDemMetaList(sendMap), metaService.getDemMetaTable()));
		metaFileList.addAll(setMetaFileList(niisSupplyDAO.getOrtMetaList(sendMap), metaService.getOrtMetaTable()));
		*/
		
		String imageCde = (String)sendMap.get("imageCde");
		
		if(imageCde.equals(propertyService.getString("Globals.airImageCode")) || imageCde.equals(propertyService.getString("Globals.airLibImageCode"))){
			
			metaFileList = setMetaFileList(niisSupplyDAO.getAirMetaList(sendMap), metaService.getAirMetaTable());
			
		}else if(imageCde.equals(propertyService.getString("Globals.demImageCode"))){
			
			metaFileList = setMetaFileList(niisSupplyDAO.getDemMetaList(sendMap), metaService.getDemMetaTable());
			
		}else if(imageCde.equals(propertyService.getString("Globals.ortImageCode"))){
			
			metaFileList = setMetaFileList(niisSupplyDAO.getOrtMetaList(sendMap), metaService.getOrtMetaTable());
		}
		
		return metaFileList;
	}
	
	public List<Map<String, Object>> setMetaFileList(List<Map<String, Object>> metaList, String[] metaTable) {
		
		List<Map<String, Object>> metaFileList = new ArrayList<Map<String, Object>>();
		
		for(int i=0; i<metaList.size(); i++){
			
			Map<String, Object> metaMap = metaList.get(i);
			for(int j=0; j<metaTable.length; j++){
				Map<String, Object> map = new HashMap<String, Object>();
				map.putAll(metaMap);
				map.put("downloadUrl", map.get("downloadUrl") + metaTable[j] + ".csv");
				map.put("printFileName", map.get("printFileName") + metaTable[j] + ".csv");
				
				metaFileList.add(map);
			}
		}
		
		return metaFileList;
	}

	@Override
	public void downloadComplete(Map<String, Object> sendMap) throws Exception {
		
		String imageCde = (String)sendMap.get("imageCde");
		String group = (String)sendMap.get("group");
		
		if("meta".equals(group)){
		
		}else if(imageCde.equals(propertyService.getString("Globals.airImageCode")) || imageCde.equals(propertyService.getString("Globals.airLibImageCode"))){
			
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyAirImgLimit"));
			niisSupplyDAO.uptAirImgDownState(sendMap);
			
		}else if(imageCde.equals(propertyService.getString("Globals.demImageCode"))){
			
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyDemImgLimit"));
			niisSupplyDAO.uptDemImgDownState(sendMap);
			
		}else if(imageCde.equals(propertyService.getString("Globals.ortImageCode"))){
			
			sendMap.put("imageDownloadLimit", propertyService.getInt("Globals.supplyOrtImgLimit"));
			niisSupplyDAO.uptOrtImgDownState(sendMap);
		}
		
		if(0 == niisSupplyDAO.getDownloadAppCnt(sendMap)){
			applyDAO.uptDbAppAuth(sendMap);
		}
	}

	/**
	 * 신청서 승인 페이지에서 다운로드 버튼 클릭시 상태값 UPDATE
		APPROVAL_CDE = '50' 
		상태 : '확인'
	 */
	@Override
	public void updateApprvalCde(Map<String, Object> sendMap) throws Exception {
		niisSupplyDAO.updateApprvalCde(sendMap);
	}
}
