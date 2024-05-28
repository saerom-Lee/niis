package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

/**
 * 메인 interface 객체 
 */
public interface ManageAirService {

	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getYearListAir() throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업지구명(연도별) 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamAir(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/항공사진 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhCourseAir(String zoneCode) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/항공사진 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhNumAir(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 보안등급  조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getSecurityCode() throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 카메라 구분 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getCameraCode() throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 해상도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getResolutionAir(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int getProductAirListCnt(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getProductAirList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubProductAirList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회(외부표정요소)
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubProductEOAirList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회(메타데이터)
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubMetaAirList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 수정(획득정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustMetaAirmapAcqut(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 수정(제약정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustMetaAirmapContr(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 수정(축척)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustAirZoneDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 수정(메타데이터)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustAirBasemetaDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/항공사진 성과 상세 목록 수정(식별정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustMetaAirmapIdent(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과관리 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustProductAirList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과관리 수정(외부표정요소)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustProductEOAirList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> OrientmapZoneNam() throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> OrientmapPhCourse(String zoneCode) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> OrientmapPhNum(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과관리 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertProductAirList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(외부표정요소)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertProductEOAirList(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertMetaAirmapIdent(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertAirBasemetaDts(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertMetaAirmapContr(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/성과 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int delProList(Map<String, Object> airParam) throws Exception;


}