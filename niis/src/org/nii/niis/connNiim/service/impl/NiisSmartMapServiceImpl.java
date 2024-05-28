package org.nii.niis.connNiim.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.NiisSmartMapService;
import org.nii.niis.niim.service.ManagementServiceVO;
import org.springframework.stereotype.Service;

@Service("niisSmartMapService")
public class NiisSmartMapServiceImpl implements NiisSmartMapService {
	
	private final String[] metaTable = {
			"air_zone_dts",
			"air_product_dts",
			"air_basemeta_dts",
//			"air_crs_dts",
			"air_orientmap_as",
			"air_orientmap_dts",
			"air_producteo_dts",
			
			"dem_product_dts",
			"dem_basemeta_dts",
			"dem_orientmap_as",
			
			"ort_product_dts",
			"ort_basemeta_dts",
			"ort_orientmap_as"
	};

	@Resource(name="niisSmartMapDAO")
	private NiisSmartMapDAO niisSmartMapDAO;
	
	@Override
	public List<String> getZoneList(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.getZoneList(sendMap);
	}

	@Override
	public int getZoneListCnt(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.getZoneListCnt(sendMap);
	}
	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회 페이징 카운트
	 * @param maParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getZoneListCnt(ManagementServiceVO maParam) throws Exception {
		return niisSmartMapDAO.selectZoneListCnt(maParam);
	}	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회
	 * @param maParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneList(ManagementServiceVO maParam) throws Exception {
		return niisSmartMapDAO.selectZoneList(maParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/사업지구 상세 목록 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getsubResultZoneList(String zoneCode) throws Exception {
		return niisSmartMapDAO.getsubResultZoneList(zoneCode);
	}
	
	/**
	 * 메타데이터 전송
	 * @param sendMap
	 * @return
	 * @throws Exception
	 * @since 2017. 9. 20.
	 */
	public Map<String, List<Map<String, Object>>> getMetaInfo(Map<String, Object> sendMap) throws Exception {
		
		Map<String, List<Map<String, Object>>> metaMap = new LinkedHashMap<String, List<Map<String, Object>>>();
		
		for(int i=0; i<metaTable.length; i++){
			sendMap.put("queryId", metaTable[i]);
			metaMap.put(metaTable[i], niisSmartMapDAO.getMetaInfo(sendMap));
		}
		return metaMap;
	}
	
	@Override
	public List<Map<String, Object>> getAirImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.getAirImgDataList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getAirLibImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.getAirLibImgDataList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getDemImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.getDemImgDataList(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> getOrtImgDataList(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.getOrtImgDataList(sendMap);
	}

	@Override
	public void insFilePath(Map<String, Object> sendMap) throws Exception {
		niisSmartMapDAO.insFilePath(sendMap);
	}

	@Override
	public int uploading(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.uploading(sendMap);
	}
	
	@Override
	public int uploadingSupIdn(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.uploadingSupIdn(sendMap);
	}
	
	@Override
	public List<Map<String, Object>> smartMapfileList(Map<String, Object> sendMap) throws Exception {
		return niisSmartMapDAO.smartMapfileList(sendMap);
	}
	
	public void updateSmartMap(Map<String, Object> sendMap) throws Exception {
		niisSmartMapDAO.updateSmartMap(sendMap);
	}

	@Override
	public List<String> getSmartZoneList() throws Exception {
		return niisSmartMapDAO.getSmartZoneList();
	}
}
