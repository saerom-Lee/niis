package org.nii.niis.niim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vividsolutions.jts.geom.Geometry;

/**
 * 통합검색 interface 객체
 */
public interface SearchService {
	/**
	 * 사업지구 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getZoneList(ParamVO param) throws Exception;
	/**
	 * 항공사진 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getAirImgList(ParamVO param) throws Exception;
	List<?> getAirImgFolderList(ParamVO param) throws Exception;
	List<?> getAirImgDataList(ParamVO param) throws Exception;
	/**
	 * 항공사진(해방전후) 
	 * @param param
	 * @return
	 * @throws Exception
	 * @since 2017. 8. 23.
	 */
	List<?> getAirLibImgFolderList(ParamVO param) throws Exception;
	List<?> getAirLibImgDataList(ParamVO param) throws Exception;
	/**
	 * 근적외선영상 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getNirImgList(ParamVO param) throws Exception;
	List<?> getNirImgFolderList(ParamVO param) throws Exception;
	List<?> getNirImgDataList(ParamVO param) throws Exception;
	/**
	 * 년도 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getAirImgYear(ParamVO param) throws Exception;
	/**
	 * 항공사진 코스 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getAirImgCourse(ParamVO param) throws Exception;
	/**
	 * 정사영상 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getOrtImgList(ParamVO param) throws Exception;
	List<?> getOrtImgFolderList(ParamVO param) throws Exception;
	List<?> getOrtImgDataList(ParamVO param) throws Exception;
	
	/**
	 * 수치표고 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getDemImgList(ParamVO param) throws Exception;
	List<?> getDemImgFolderList(ParamVO param) throws Exception;
	List<?> getDemImgDataList(ParamVO param) throws Exception;
	
	/**
	 * 수치지형도 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getSuchiList(Map<String, Object> param) throws Exception;
	
	/**
	 * 라이다 영상 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getLidImgList(ParamVO param) throws Exception;
	List<?> getLidImgFolderList(ParamVO param) throws Exception;
	List<?> getLidImgDataList(ParamVO param) throws Exception;
	
	/**
	 * 3차원객체 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getTdsImgList(ParamVO param) throws Exception;
	List<?> getTdsImgFolderList(ParamVO param) throws Exception;
	List<?> getTdsImgDataList(ParamVO param) throws Exception;
	
	ParamVO getBeforeSearchCondtion(Map<String, Object> param) throws Exception;
	
	Map<String, ArrayList<String>> getUnloadedData(ParamVO vo, String radius, String fileType, String zoneCode, String pathOne, String pathTwo) throws Exception;
	
	/**
	 * 항공사진 촬영지역 정보 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getNowestAirZoneAreaGeometry(ParamVO param) throws Exception;
	List<?> getNowestAirZone(ParamVO param) throws Exception;

	/**
	 * 지번 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getZipCodeList(ParamVO param) throws Exception;
	
	/**
	 * 새주소 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getJusoList(ParamVO param) throws Exception;
	
	/**
	 * 새주소 건물번호 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getBuldNoList(ParamVO param) throws Exception;
	
	/**
	 * POI 검색
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<?> getPOIList(ParamVO param) throws Exception;
	

	
	/**
	 * 통합검색 - 영상 사업지구 촬영년도 목록
	 * @return
	 * @throws Exception
	 */
	List<?> getYearList() throws Exception;
	
	/**
	 * 통합검색 - 수치지형도 제작년도 목록
	 * @return
	 * @throws Exception
	 */
	List<?> getMapYearList() throws Exception;
	
	
	/**
	 * 통합검색 - 신청서목록
	 * @return
	 * @throws Exception
	 */
	List<?> getApplicationList() throws Exception;
	
	/**
	 * 통합검색 - 신청서 생성
	 * @param usrParam
	 * @return
	 * @throws Exception
	 */
	Object setSupIdn(Map<String, Object> usrParam) throws Exception;
	
	/**
	 * 통합검색 - 1:50000 도엽 검색
	 * @return
	 * @throws Exception
	 */
	List<?> getIndexMapName() throws Exception;
	
	/**
	 * 통합검색 - 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> regSupplyItem(Map<String, Object> param) throws Exception;
	
	/**
	 * 통합검색 - xml 생성
	 * @param param
	 * @return
	 * @throws Exception
	 */
	void createXML(Map<String, Object> param) throws Exception;
	
	/**
	 * 통합검색 - 워터마크 인덱스 생성
	 * @param idxParam
	 * @return
	 * @throws Exception
	 */
	Object setcreateIndex(Map<String, Object> idxParam) throws Exception;
	
	/**
	 * 통합검색 - 항공사진 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> setAirSupplyItem(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 통합검색 - 항공사진(해방전후) 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> setAirLibSupplyItem(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 통합검색 - 수치표고 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> setDemSupplyItem(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 통합검색 - 정사영상 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> setOrtSupplyItem(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 통합검색 - 라이다 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> setLidSupplyItem(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 통합검색 - NIR 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> setNirSupplyItem(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 통합검색 - 3차원 객체 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> setTdsSupplyItem(Map<String, Object> sendMap) throws Exception;
	
	
	@SuppressWarnings("rawtypes")
	List<?> getContainsList(double tagetMinx, double tagetMiny, double tagetMaxx, double tagetMaxy, List obj, String radius) throws Exception;
	Geometry createEnvelopeToGeometry(double minx, double miny, double maxx, double maxy) throws Exception;
}