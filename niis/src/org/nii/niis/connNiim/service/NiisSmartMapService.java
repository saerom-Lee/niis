package org.nii.niis.connNiim.service;

import java.util.List;
import java.util.Map;

import org.nii.niis.niim.service.ManagementServiceVO;

public interface NiisSmartMapService {

	List<String> getZoneList (Map<String, Object> sendMap) throws Exception;
	int getZoneListCnt (Map<String, Object> zoneList) throws Exception;
	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회 페이징 카운트
	 * @param maParam
	 * @return int
	 * @throws Exception
	 */
	int getZoneListCnt(ManagementServiceVO maParam) throws Exception;
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회
	 * @param maParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneList(ManagementServiceVO maParam) throws Exception;
	/**
	 * 통합관리 - 사업지구 관리/사업지구 상세 목록 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getsubResultZoneList(String zoneCode) throws Exception;
	
	Map<String, List<Map<String, Object>>> getMetaInfo(Map<String, Object> sendMap) throws Exception;
	
	
	/**
	 * 영상전송 
	 * @param sendMap
	 * @return
	 * @throws Exception
	 * @since 2017. 10. 17.
	 */
	List<Map<String, Object>> getAirImgDataList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getAirLibImgDataList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getDemImgDataList(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> getOrtImgDataList(Map<String, Object> sendMap) throws Exception;
	
	void insFilePath(Map<String, Object> sendMap) throws Exception;
	int uploading(Map<String, Object> sendMap) throws Exception;
	int uploadingSupIdn(Map<String, Object> sendMap) throws Exception;
	List<Map<String, Object>> smartMapfileList(Map<String, Object> sendMap) throws Exception;
	
	void updateSmartMap(Map<String, Object> sendMap) throws Exception;
	List<String> getSmartZoneList() throws Exception;
}
