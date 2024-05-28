package org.nii.niis.connNiim.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.NiisSearchService;
import org.springframework.stereotype.Service;

@Service("niisSearchService")
public class NiisSearchServiceImpl implements NiisSearchService {

	@Resource(name="niisSearchDAO")
	private NiisSearchDAO niisSearchDAO;
	
	@Override
	public List<Map<String, Object>> getAirImgFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAirImgFolderList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getAirImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAirImgDataList(sendMap);
	}

	@Override
	public int getAirImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAirImgDataListCnt(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getAirLibImgFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAirLibImgFolderList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getAirLibImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAirLibImgDataList(sendMap);
	}
	
	@Override
	public int getAirLibImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAirLibImgDataListCnt(sendMap);
	}
	
	
	@Override
	public List<Map<String, Object>> getOrtImgFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getOrtImgFolderList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getOrtImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getOrtImgDataList(sendMap);
	}

	@Override
	public int getOrtImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getOrtImgDataListCnt(sendMap);
	}
	
	
	@Override
	public List<Map<String, Object>> getDemImgFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getDemImgFolderList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getDemImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getDemImgDataList(sendMap);
	}

	@Override
	public int getDemImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getDemImgDataListCnt(sendMap);
	}

	@Override
	public Map<String, Object> getBeforeSearchCondtion(Map<String, Object> param) throws Exception {
		
		Map<String, Object> sendMap = new HashMap<String, Object>();
		
		String imgType = "";
		String admcd 	= "";
		String bounds 		= "";
		
		if (param != null){
			
			if (param.containsKey("imgType")){
				imgType = (String)param.get("imgType");
			}
			
			if (param.containsKey("radius")){
				try{
					sendMap.put("radius", Integer.parseInt((String)param.get("radius")));
				}catch(NumberFormatException e){
					sendMap.put("radius", 0);
				}
			}
			
			if(param.containsKey("admcd")) {
				admcd = (String)param.get("admcd");
				if(admcd.length() == 2) {
					sendMap.put("tableNm", "TL_SCCO_CTPRVN");
				} else if(admcd.length() == 5) {
					sendMap.put("tableNm", "TL_SCCO_SIG");
				} else {
					sendMap.put("tableNm", "TL_SCCO_EMD");
				}
			}
			
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){ 
				String key = (String)iter.next(); 
				
				if(key.equals("sYear") || key.equals("eYear")){
					sendMap.put(key, param.get(key));
				}
				if(key.equals("zoneCode")){
					//다운로드시 array로 들어옴
					if(param.get(key) instanceof String){
						sendMap.put("zoneCode", param.get(key));
					}
				}
				if(key.equals("admcd")){
					if (!admcd.equals("00")){
						sendMap.put("admcd", param.get(key));
					}
				}
				if(key.equals("bounds")){
					bounds = (String)param.get(key);
					if (bounds.indexOf(",") > -1){
						String[] temp = bounds.split(",");
						//minx, miny, maxx, maxy
						sendMap.put("xmin" , Float.parseFloat(temp[0]));
						sendMap.put("ymin" , Float.parseFloat(temp[1]));
						sendMap.put("xmax" , Float.parseFloat(temp[2]));
						sendMap.put("ymax" , Float.parseFloat(temp[3]));
					}
				}
				if(key.equals("geometry")) {
					sendMap.put("geometry", param.get(key));
				}
								
				//영상종류별 파라미터 세팅
				//항공사진
				if("0".equals(imgType)){
					if (key.equals("phCourse") || key.equals("securityCde")){
						sendMap.put(key, param.get(key));
					}
				}
				//정사영상
				else if("1".equals(imgType)){
					if (key.equals("gtypDst") || key.equals("securityCde") || key.equals("mapNum")){
						sendMap.put(key, param.get(key));
					}
				}
				//수치표고
				else if("2".equals(imgType)){
					if (key.equals("gridInt") || key.equals("securityCde") || key.equals("mapNum")){
						sendMap.put(key, param.get(key));
					}
				}
			}
		}
		return sendMap;
	}

	@Override
	public int getAtImgDataListCnt(Map<String, Object> sendMap) throws Exception{
		return niisSearchDAO.getAtImgDataListCnt(sendMap);
	}

	@Override
	public List<Map<String, Object>> getAtImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAtImgDataList(sendMap);
	}

	@Override
	public List<Map<String, Object>> getAtImgFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getAtImgFolderList(sendMap);
	}

	@Override
	public int getMapImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getMapImgDataListCnt(sendMap);
	}

	@Override
	public List<Map<String, Object>> getMapImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getMapImgDataList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getSuchiFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getSuchiFolderList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getSuchiDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getSuchiDataList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getSuchi2FolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getSuchi2FolderList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getLandFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getLandFolderList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getLandUseFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getLandUseFolderList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getCoastFolderList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getCoastFolderList(sendMap);
	}
	
	@Override
	public int getMapHisImgDataListCnt(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getMapHisImgDataListCnt(sendMap);
	}

	@Override
	public List<Map<String, Object>> getMapHisImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getMapHisImgDataList(sendMap);
	}

	@Override
	public Map<String, Object> getMapListSrchDetail(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getMapListSrchDetail(sendMap);
	}

	@Override
	public Map<String, Object> getMapHistoryListSrchDetail(Map<String, Object> sendMap) throws Exception {
		return niisSearchDAO.getMapHistoryListSrchDetail(sendMap);
	}
}
