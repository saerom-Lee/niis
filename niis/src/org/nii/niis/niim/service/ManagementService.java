package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

/**
 * 메인 interface 객체 
 */
public interface ManagementService {

	/**
	 * 통합관리 - 사업지구 관리/사업종류 조회
	 * @param maParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneType() throws Exception;

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
	 * 통합관리 - 항공사진 관리/사진주점 관리 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int getOrientmapListCnt(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getOrientmapList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 상세 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubOrientmapList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/사업지구 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustZone(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustOrientmap(Map<String, Object> airParam) throws Exception;

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
	 * 통합관리 - 항공사진 관리/사진주점 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustAirOrientmapDts(Map<String, Object> airParam) throws Exception;

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
	 * 통합관리 - 항공사진 관리/촬영기록부 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int getAirNoteListCnt(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getAirNoteList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 필름 번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getFilmNumAir(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 상세 목록
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubAirNoteList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustAirNoteDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustAirCoursetestDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustAirNotecourseDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/카메라정보 카메라구분
	 * @return List
	 * @throws Exception
	 */
	List<?> getAirCameraDts() throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 보유업체 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getAirkeepCmpn() throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/카메라정보 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int getAirCameraDtsListCnt(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getAirCameraDtsList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/카메라정보 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustAirCameraDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getYearDEMList() throws Exception;

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 사업지구명 조회
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamDEM(Map<String, Object> demParam) throws Exception;

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 도엽번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getMap5000Num(String zoneCode) throws Exception; 
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 보안등급 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getSecurityCodeDEM() throws Exception;
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 해상도 조회
	 * @return List
	 * @throws Exception
	 */
//	List<?> getResolutionDEM(Map<String, Object> demParam) throws Exception;
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 격자간격 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getGridIntDem(Map<String, Object> demParam) throws Exception;

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
	 * 통합관리 - 수치표고 관리/수치표고 성과 목록 조회 페이징 카운트
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	int getMap5000NumListCnt(Map<String, Object> demParam) throws Exception;
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 목록 조회
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getMap5000NumList(Map<String, Object> demParam) throws Exception;

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 조회
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubMap5000NumList(Map<String, Object> demParam) throws Exception;
	
	/**
	 * 통합관리 - 수치표고 관리/ 격자간격 등록 중복검사
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubGridIntDem(Map<String, Object> demParam) throws Exception;

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 조회(메타데이터)
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubMap5000BaseMeta(Map<String, Object> demParam) throws Exception;

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 수정(메타데이터)
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubMap5000BaseMeta(Map<String, Object> demParam) throws Exception;

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 수정
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubMap5000NumList(Map<String, Object> demParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getYearListOrt() throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 사업지구명 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamOrt(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 도엽번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getMap5000NumOrt(String zoneCode) throws Exception;
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 보안등급 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getSecurityCodeOrt() throws Exception;
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 지상표본거리 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getGtypDstOrt() throws Exception;
	
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 지상표본거리 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getResSeqOrt() throws Exception;


	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 목록 조회 페이징 카운트
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int getMap5000NumOrtListCnt(Map<String, Object> ortParam) throws Exception;
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 목록 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getMap5000NumOrtList(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubMap5000NumOrtList(Map<String, Object> ortParam)  throws Exception;
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubGtypDstOrt(Map<String, Object> ortParam)  throws Exception;
	
	/**
	 * 통합관리 - 정사영상 관리/지상표본거리 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getGtypDst(Map<String, Object> ortParam)  throws Exception;
	
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회(메타데이터)
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubMetaOrtList(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubMap5000NumOrtList(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정(메타데이터)
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubMetaBaseOrt(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정(식별정보)
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubMetaOrtIdent(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubMap5000NumOrtList_2(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubMap5000NumOrtList_3(Map<String, Object> ortParam) throws Exception;
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int adjustSubGtypDstOrtList(Map<String, Object> ortParam)throws Exception;
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamGPS() throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 점의번호 조회
	 * @param gpsParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getGpsBpIdn(Map<String, Object> gpsParam) throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 목록 조회 페이징 카운트
	 * @param gpsParam
	 * @return int
	 * @throws Exception
	 */
	int getGpsListCnt(Map<String, Object> gpsParam) throws Exception;
	
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 목록 조회
	 * @param gpsParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getGpsList(Map<String, Object> gpsParam) throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 수정
	 * @param gpsParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustGpsList(Map<String, Object> gpsParam) throws Exception;

	/**
	 * 통합관리 - 라이다 관리/라이다 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getYearListLid() throws Exception;

	/**
	 * 통합관리 - 라이다 관리/라이다 성과 사업지구명 조회
	 * @param lidParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamLid(Map<String, Object> lidParam) throws Exception;

	/**
	 * 통합관리 - 라이다 관리/라이다 성과 자료 ID 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getLidarIdn(String zoneCode) throws Exception;
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 보안등급 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getSecurityCodeLid() throws Exception;

	/**
	 * 통합관리 - 라이다 관리/라이다 성과 목록 조회(상세목록 포함) 페이징 카운트
	 * @param lidParam
	 * @return int
	 * @throws Exception
	 */
	int getLidListCnt(Map<String, Object> lidParam) throws Exception;
	
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 목록 조회(상세목록 포함)
	 * @param lidParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getLidList(Map<String, Object> lidParam) throws Exception;

	/**
	 * 통합관리 - 라이다 관리/라이다 성과 수정
	 * @param lidParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustLidList(Map<String, Object> lidParam) throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getYearListNir() throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 사업지구명 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamNir(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 코스번호 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhCourseNir(String zoneCode) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 번호 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhNumNir(Map<String, Object> NirParam) throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 해상도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getResolutionNir(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 주점 목록 조회 페이징 카운트
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int getOrientmapNirListCnt(Map<String, Object> nirParam) throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 주점 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getOrientmapNirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 상세 목록
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubOrientmapNirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustOrientmapNir(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 목록 조회 페이징 카운트
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int getProductNirListCnt(Map<String, Object> nirParam) throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getProductNirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubProductNirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회(외부표정요소)
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubProductEONirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회(메타데이터)
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubMetaNirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(획득정보)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustMetaNirmapAcqut(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustNirOrientmapDts(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(제약정보)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustMetaNirmapContr(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustNirZoneDts(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(메타데이터)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustNirBasemetaDts(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(식별정보)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustMetaNirmapIdent(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 목록 조회 페이징 카운트
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int getNirNoteListCnt(Map<String, Object> nirParam) throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getNirNoteList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 플름번호 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getFilmNumNir(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 상세 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubNirNoteList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustNirNoteDts(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustNirCoursetestDts(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustNirNotecourseDts(Map<String, Object> NirParam) throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/카메라정보 카메라 구분 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getNirCameraDts() throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/카메라정보 보유업체 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getNirkeepCmpn() throws Exception;

	/**
	 * 통합관리 - NIR 관리/카메라정보 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getNirCameraDtsList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/카메라정보 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustNirCameraDts(Map<String, Object> NirParam) throws Exception;
	
	/**
	 * 통합관리 - NIR 관리/성과 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustProductNirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - NIR 관리/성과 수정(외부표정요소)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustProductEONirList(Map<String, Object> NirParam) throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getYearPts() throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 사업지구명 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamPts(Map<String, Object> ptsParam) throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 목록 조회 페이징 카운트
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	int getPtsListCnt(Map<String, Object> ptsParam) throws Exception;
	
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 목록 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getPtsList(Map<String, Object> ptsParam) throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 상세 목록 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubPtsList(Map<String, Object> ptsParam) throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 수정
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubPtsList(Map<String, Object> ptsParam) throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/지상기준점 점일련번호 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getPNum(Map<String, Object> PtsParam) throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/지상기준점 목록 조회 페이징 카운트
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	int getPtsCoordListCnt(Map<String, Object> ptsParam) throws Exception;
	
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 목록 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getPtsCoordList(Map<String, Object> ptsParam) throws Exception;

	/**
	 * 통합관리 - 측량조서 관리/지상기준점 수정
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	int updateAdjustSubPtsCoordList(Map<String, Object> ptsParam) throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/사업지구 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertZone(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/사업지구 삭제
	 * @param zoneCode
	 * @return int
	 * @throws Exception
	 */
	int delZone(String zoneCode) throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 등록
	 * @param gpsParam
	 * @return String
	 * @throws Exception
	 */
	String insertGps(Map<String, Object> gpsParam) throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> GpsListZoneNam() throws Exception;

	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 삭제
	 * @param gpsParam
	 * @return int
	 * @throws Exception
	 */
	int delGps(Map<String, Object> gpsParam) throws Exception;


	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> OrientmapZoneNam() throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertOrientmap(Map<String, Object> airParam) throws Exception;

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
	 * 통합관리 - 항공사진 관리/촬영기록부 등록/촬영일자 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> insertphDate(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록/렌즈 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> insertlensNum() throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 삭제
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int delOrient(Map<String, Object> ortParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhCourseOrient(String zoneCode) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사업지구명 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNamOrient(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhNumOrient(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/성과 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int delProList(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhCourseCmr(String zoneCode) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 사업지구명 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getzoneNamCmr(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertAirNoteDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertAirNotecourseDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertAirCoursetestDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/카메라정보 렌즈번호 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getlensNum() throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int delAirNote(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/카메라정보 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	String insertAirCameraDts(Map<String, Object> airParam) throws Exception;

	/**
	 * 통합관리 - 항공사진 관리/카메라 정보 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	int delCmr(Map<String, Object> airParam) throws Exception;
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록(메타데이터)
	 * @param demParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubGridIntDem(Map<String, Object> demParam);

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록(메타데이터)
	 * @param demParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubMap5000BaseMeta(Map<String, Object> demParam);

	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록
	 * @param demParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubMap5000NumList(Map<String, Object> demParam);
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제 이전 조회
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	int getGridIntCnt(Map<String, Object> demParam);
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제 1
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	int delGridIntDem(Map<String, Object> demParam);
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	int delMap5000(Map<String, Object> demParam);

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubMap5000NumOrtList(Map<String, Object> ortParam);

	/**
	 * 통합관리 - 정사영상 관리/해상도 등록
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubGtypDstOrt(Map<String, Object> ortParam);
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubMetaOrtIdent(Map<String, Object> ortParam);

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubMetaBaseOrt(Map<String, Object> ortParam);

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubMap5000NumOrtList_3(Map<String, Object> ortParam);

	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubMap5000NumOrtList_2(Map<String, Object> ortParam);
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제(이전 조회)
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int getGtypDstCnt(Map<String, Object> airParam );
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제(이전 조회)
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int getExistToMetaOrtIdent(Map<String, Object> ortParam);
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제 1
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int delGtypDstOrt(Map<String, Object> ortParam);
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제2
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	int delMap5000Ort(Map<String, Object> ortParam);

	/**
	 * 통합관리 - 라이다 관리/라이다 성과 등록
	 * @param lidParam
	 * @return String
	 * @throws Exception
	 */
	String insertLidList(Map<String, Object> lidParam);

	/**
	 * 통합관리 - 라이다 관리/라이다 성과 삭제
	 * @param lidParam
	 * @return int
	 * @throws Exception
	 */
	int delLid(Map<String, Object> lidParam);

	/**
	 * 통합관리 - NIR 관리/사진주점 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertOrientmapNir(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/사진주점 등록
	 * @return List
	 * @throws Exception
	 */
	List<?> OrientmapNirZoneNam();

	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(메타데이터)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertNirBasemetaDts(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(메타데이터)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertMetaNirmapIdent(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(메타데이터)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertMetaNirmapContr(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(외부표정요소)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertProductEONirList(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/성과관리 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertProductNirList(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/사진주점 삭제
	 * @param nirParam
	 * @return int
	 * @throws Exception
	 */
	int delOrientNir(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/성과 삭제
	 * @param nirParam
	 * @return int
	 * @throws Exception
	 */
	int delProNirList(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/촬영기록부 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertNirNoteDts(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/촬영기록부 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertNirNotecourseDts(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/촬영기록부 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	String insertNirCoursetestDts(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 사업지구명 조회
	 * @param nirParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getzoneNamNirCmr(Map<String, Object> nirParam);

	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	List<?> getPhCourseNirCmr(String zoneCode);

	/**
	 * 통합관리 - NIR 관리/촬영기록부 삭제
	 * @param nirParam
	 * @return int
	 * @throws Exception
	 */
	int delNirNote(Map<String, Object> nirParam);

	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 등록
	 * @param ptsParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubPtsList(Map<String, Object> ptsParam);

	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 삭제
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	int delPts(Map<String, Object> ptsParam);

	/**
	 * 통합관리 - 측량조서 관리/지상기준점 등록
	 * @param ptsParam
	 * @return String
	 * @throws Exception
	 */
	String insertSubPtsCoordList(Map<String, Object> ptsParam);

	/**
	 * 통합관리 - 측량조서 관리/지상기준점 삭제
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	int delPtsCoord(Map<String, Object> ptsParam);

	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업지구명(연도별) 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getZoneNam();

	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록 필름번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	List<?> getFilmNumInsert(Map<String, Object> airParam);

	/**
	 * 통합관리 - 측량조서 관리/지상기준점 측량조서 분류 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getSurvCode();

	/**
	 * 통합관리 - at성과 상세 조회
	 * @return List
	 * @throws Exception
	 */
	List<?> getSubProductAtList(Map<String, Object> sendMap) throws Exception;

}