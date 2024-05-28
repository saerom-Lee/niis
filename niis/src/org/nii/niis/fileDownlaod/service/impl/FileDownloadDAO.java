package org.nii.niis.fileDownlaod.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Repository("fileDownloadDAO")
public class FileDownloadDAO extends EgovAbstractDAO{
	
	/**
	 * 파일 다운로드 - 항공사진
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getFileAirList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("fileDownloadDAO.getFileAirList", param);
		
	}
	
	/**
	 * 파일 다운로드 - 정사영상
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getFileOrtList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("fileDownloadDAO.getFileOrtList", param);
		
	}
	
	/**
	 * 파일 다운로드 - DEM
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getFileDemList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("fileDownloadDAO.getFileDemList", param);
		
	}
	
	/**
	 * 파일 다운로드 - 수치지형도
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getFileMapList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("fileDownloadDAO.getFileMapList", param);
	}
	
	/**
	 * 파일 다운로드 - AT
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<HashMap<String, Object>> getFileAtList(HashMap<String, Object> param) {
		return (List<HashMap<String, Object>>)list("fileDownloadDAO.getFileAtList", param);
		
	}

	/**
	 * 파일 다운로드 - 수치지형도 표준메타데이터
	 * @param sendMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EgovMap> getFileMapBisList(HashMap<String, Object> sendMap) {
		
		/*
			MAP_SER_NO, MAP_HISTORY_NO, MAP_SHT_NO
			MAP_HISTORY_NO 가 0이면 		MAP_INFO_TBL@lk_mms		 에서 POLCY_MANU, BSNSPLAN_NO, MAP_KIND_CD, SCALE_CD 조회
			MAP_HISTORY_NO 가 0이 아니면 	MAP_HISTORY_TBL@lk_mms	 에서 POLCY_MANU, BSNSPLAN_NO, MAP_KIND_CD, SCALE_CD 조회
			
			MAP_KIND_CD가 수치지형도 V1.0 수치지형도 V2.0 이면서
			SCALE_CD가 1:1000, 1:5000 인것만 표준메타데이터를 반환한다. 
			 
			TN_SMD_MAP_5000@LK_NLIP 
			TN_SMD_MAP_1000@LK_NLIP 테이블에서
			POLCY_MANU랑 BSNSPLAN_NO 로 SN 조회
			
			1:1000일때 SM0011
			1:5000일때 SM0012 를 PDT_CD 코드로 반환해준다.
			수치지형도 V1.0 수치지형도 V2.0 만 해주면 된다.
			
			COMMON_CD_TBL@LK_MMS
			CD_KIND		CD_TYPE		CD_KIND_NM		CD_TYPE_NM
			21			01			map_scale		1,000
			21			02			map_scale		5,000
			
			11			01			map_kind_cd		수치지형도V1.0
			11			04			map_kind_cd		수치지도V2.0
		*/
			
		@SuppressWarnings("unchecked")
		List<EgovMap> getFileMapList = (List<EgovMap>)list("fileDownloadDAO.getFileMapList", sendMap);	
		
		List<EgovMap> getBisList = new ArrayList<EgovMap>();
		
		for(int i=0; i<getFileMapList.size(); i++) {
			
			String mapSerNo = getFileMapList.get(i).get("mapSerNo").toString();
			String mapHistoryNo = getFileMapList.get(i).get("mapHistoryNo").toString();
			String mapShtNo = getFileMapList.get(i).get("mapShtNo").toString();
			
			HashMap<String, String> mapPk = new HashMap<String, String>();
			mapPk.put("mapSerNo", mapSerNo);
			mapPk.put("mapHistoryNo", mapHistoryNo);
			mapPk.put("mapShtNo", mapShtNo);
			
			EgovMap getFileMapBis = (EgovMap) select("fileDownloadDAO.getFileMapBis", mapPk);
			
			String mapKindCd = getFileMapBis.get("mapKindCd").toString();
			String scaleCd = getFileMapBis.get("scaleCd").toString();
			
			/*
			 	MAP_KIND_CD가 	수치지형도V1.0, 수치지형도V2.0 이면서
				SCALE_CD가 		1:1000, 1:5000 인것만 표준메타데이터를 반환한다. 
			 */
			
			if(		"01".equals(mapKindCd) && "01".equals(scaleCd) 
				||	"01".equals(mapKindCd) && "02".equals(scaleCd)
				||	"04".equals(mapKindCd) && "01".equals(scaleCd)	
				||	"04".equals(mapKindCd) && "02".equals(scaleCd)) {
				
				getBisList.add(getFileMapBis);
			}
			
		}
		
		List<EgovMap> selectBsmDetailList = new ArrayList<EgovMap>();
		if(!getBisList.isEmpty()) {
			for(int i=0; i<getBisList.size(); i++) {
				
				String polcyManu = getBisList.get(i).get("polcyManu").toString();
				String bsnsplanNo = getBisList.get(i).get("bsnsplanNo").toString();
				
				String mapKindNm = getBisList.get(i).get("mapKindNm").toString();
				String scaleNm = getBisList.get(i).get("scaleNm").toString();
				String mapShtNo = getBisList.get(i).get("mapShtNo").toString();
				String openDvsnNm = getBisList.get(i).get("openDvsnNm").toString();
				
				HashMap<String, String> bisPk = new HashMap<String, String>();
				bisPk.put("polcyManu", polcyManu);
				bisPk.put("bsnsplanNo", bsnsplanNo);
				
				String scaleCd = getBisList.get(i).get("scaleCd").toString();
				
				EgovMap selectBsmDetail = new EgovMap();
				
				if("01".equals(scaleCd)) {			// 1:1000 일떄
					selectBsmDetail = (EgovMap) select("fileDownloadDAO.selectBsmDetail1000", bisPk);
				}else if("02".equals(scaleCd)) {	// 1:5000 일떄
					selectBsmDetail = (EgovMap) select("fileDownloadDAO.selectBsmDetail5000", bisPk);
				}
				if(selectBsmDetail != null && !selectBsmDetail.isEmpty()) {
					selectBsmDetail.put("mapKindNm", mapKindNm);
					selectBsmDetail.put("scaleNm", scaleNm);
					selectBsmDetail.put("mapShtNo", mapShtNo);
					selectBsmDetail.put("openDvsnNm", openDvsnNm);
					selectBsmDetailList.add(selectBsmDetail);
				}
				
			}
		}
		
		
		return selectBsmDetailList;
	}

	// 수치지형도 표준메타데이터관리 상세조회
	public EntityMap selectBsmDetailMap(HashMap<String, String> bsmPk) {
		return (EntityMap) select("fileDownloadDAO.selectBsmDetailMap", bsmPk);
	}

	// 수치지형도 표준메타데이터관리 namespace 조회
	public List<EntityMap> selectSmdNamespaceInfo(EntityMap paramMap) {
		return (List<EntityMap>)list("fileDownloadDAO.selectSmdNamespaceInfo", paramMap);
	}

	// 수치지형도 표준메타데이터관리 스키마정보 조회
	public List<EntityMap> selectSmdSchemaInfo(EntityMap paramMap) {
		return (List<EntityMap>)list("fileDownloadDAO.selectSmdSchemaInfo", paramMap);	
	}
	
	// 표준메타데이터_코드_정보
	public EgovMap selectMapSmdCdInfo(String cd) {
		return (EgovMap) select("bsmDAO.selectMapSmdCdInfo", cd);
	}

}
