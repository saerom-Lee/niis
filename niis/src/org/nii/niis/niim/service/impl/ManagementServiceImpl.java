package org.nii.niis.niim.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.ManagementService;
import org.nii.niis.niim.service.ManagementServiceVO;
import org.springframework.stereotype.Service;


/**
 * 메인 implements 객체 
 */
@Service("managementService")
public class ManagementServiceImpl implements ManagementService {

	/** ManagementDAO */
    @Resource(name="managementDAO")
    private ManagementDAO managementDAO;

	/**
	 * 통합관리 - 사업지구 관리/사업종류 조회
	 * @param maParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneType() throws Exception {
		return managementDAO.selectZoneType();
	}

	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회 페이징 카운트
	 * @param maParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getZoneListCnt(ManagementServiceVO maParam) throws Exception {
		return managementDAO.selectZoneListCnt(maParam);
	}	
	/**
	 * 통합관리 - 사업지구 관리/사업지구 목록 조회
	 * @param maParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneList(ManagementServiceVO maParam) throws Exception {
		return managementDAO.selectZoneList(maParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/사업지구 상세 목록 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getsubResultZoneList(String zoneCode) throws Exception {
		return managementDAO.getsubResultZoneList(zoneCode);
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getYearListAir() throws Exception {
		return managementDAO.selectYearListAir();
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업지구명(연도별) 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamAir(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectZoneNamAir(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhCourseAir(String zoneCode) throws Exception {
		return managementDAO.selectPhCourseAir(zoneCode);
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhNumAir(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectPhNumAir(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getOrientmapListCnt(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectOrientmapListCnt(airParam);
	}	
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getOrientmapList(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectOrientmapList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 상세 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubOrientmapList(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectSubOrientmapList(airParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/사업지구 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustZone(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustZone(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustOrientmap(Map<String, Object> airParam) throws Exception {
		return managementDAO.updateAdjustOrientmap(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 보안등급  조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSecurityCode() throws Exception {
		return managementDAO.selectSecurityCode();
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 카메라 구분 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getCameraCode() throws Exception {
		return managementDAO.selectCameraCode();
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 해상도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getResolutionAir(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectResolutionAir(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getProductAirListCnt(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectProductAirListCnt(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getProductAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.selectProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubProductAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.selectSubProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회(외부표정요소)
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubProductEOAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.selectSubProductEOAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 상세 목록 조회(메타데이터)
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubMetaAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.selectSubMetaAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(획득정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaAirmapAcqut(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustMetaAirmapAcqut(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirOrientmapDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustAirOrientmapDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(제약정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaAirmapContr(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustMetaAirmapContr(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(축척)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirZoneDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustAirZoneDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 수정(메타데이터)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirBasemetaDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustAirBasemetaDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 성과 상세 목록 수정(식별정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaAirmapIdent(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustMetaAirmapIdent(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getAirNoteListCnt(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectAirNoteListCnt(airParam);
	}	
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getAirNoteList(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectAirNoteList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 필름 번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getFilmNumAir(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.selectFilmNumAir(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 상세 목록
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubAirNoteList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.selectSubAirNoteList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirNoteDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustAirNoteDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirCoursetestDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustAirCoursetestDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirNotecourseDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustAirNotecourseDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 카메라구분
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getAirCameraDts() throws Exception {
		return managementDAO.selectAirCameraDts();
	}
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 보유업체 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getAirkeepCmpn() throws Exception {
		return managementDAO.selectAirkeepCmpn();
	}
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getAirCameraDtsListCnt(Map<String, Object> airParam) throws Exception {
		return managementDAO.selectAirCameraDtsListCnt(airParam);
	}	
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getAirCameraDtsList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.selectAirCameraDtsList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirCameraDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustAirCameraDts(airParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getYearDEMList() throws Exception {
		return managementDAO.selectYearDEMList();
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 사업지구명 조회
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamDEM(Map<String, Object> demParam) throws Exception {
		return managementDAO.selectZoneNamDEM(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 도엽번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getMap5000Num(String zoneCode) throws Exception {
		return managementDAO.getMap5000Num(zoneCode);
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 보안등급 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSecurityCodeDEM() throws Exception {
		return managementDAO.selectSecurityCodeDEM();
	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 해상도 조회
	 * @return List
	 * @throws Exception
	 */
//	@Override
//	public List<?> getResolutionDEM(Map<String, Object> demParam) throws Exception {
//		return managementDAO.selectResolutionDEM(demParam);
//	}
	
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 격자간격 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getGridIntDem(Map<String, Object> demParam) throws Exception {
		return managementDAO.selectGridIntDem(demParam);
	}
	
	/**
	/**
	 * 통합관리 - 항공사진 관리/성과관리 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustProductAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과관리 수정(외부표정요소)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustProductEOAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.updateAdjustProductEOAirList(airParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 목록 조회 페이징 카운트
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getMap5000NumListCnt(Map<String, Object> demParam) throws Exception {
		return managementDAO.getMap5000NumListCnt(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 목록 조회
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getMap5000NumList(Map<String, Object> demParam)
			throws Exception {
		return managementDAO.getMap5000NumList(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 조회
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubMap5000NumList(Map<String, Object> demParam)
			throws Exception {
		return managementDAO.getSubMap5000NumList(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/ 격자간격 등록 중복검사
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubGridIntDem(Map<String, Object> demParam)
			throws Exception {
		return managementDAO.getSubGridIntDem(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 조회(메타데이터)
	 * @param demParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubMap5000BaseMeta(Map<String, Object> demParam)
			throws Exception {
		return managementDAO.getSubMap5000BaseMeta(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 수정(메타데이터)
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubMap5000BaseMeta(Map<String, Object> demParam)
			throws Exception {
		return managementDAO.updateAdjustSubMap5000BaseMeta(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 수정
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubMap5000NumList(Map<String, Object> demParam)
			throws Exception {
		return managementDAO.updateAdjustSubMap5000NumList(demParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getYearListOrt() throws Exception {
		return managementDAO.getYearListOrt();
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 사업지구명 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamOrt(Map<String, Object> ortParam) throws Exception {
		return managementDAO.getZoneNamOrt(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 도엽번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getMap5000NumOrt(String zoneCode) throws Exception {
		return managementDAO.getMap5000NumOrt(zoneCode);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 보안등급 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSecurityCodeOrt() throws Exception {
		return managementDAO.selectSecurityCodeOrt();
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 지상표본거리 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getGtypDstOrt() throws Exception {
		return managementDAO.selectGtypDstOrt();
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 지상표본거리 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getResSeqOrt() throws Exception {
		return managementDAO.selectResSeqOrt();
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 목록 조회 페이징 카운트
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getMap5000NumOrtListCnt(Map<String, Object> ortParam) throws Exception {
		return managementDAO.getMap5000NumOrtListCnt(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 목록 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getMap5000NumOrtList(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.getMap5000NumOrtList(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubMap5000NumOrtList(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.getSubMap5000NumOrtList(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubGtypDstOrt(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.getSubGtypDstOrt(ortParam);
	}
	/**
	 * 통합관리 -  정사영상 관리/해상도 입력 지상표본거리 조회
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getGtypDst(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.getGtypDst(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 상세 목록 조회(메타데이터)
	 * @param ortParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubMetaOrtList(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.getSubMetaOrtList(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubMap5000NumOrtList(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.updateAdjustSubMap5000NumOrtList(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정(메타데이터)
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubMetaBaseOrt(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.updateAdjustSubMetaBaseOrt(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정(식별정보)
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubMetaOrtIdent(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.updateAdjustSubMetaOrtIdent(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubMap5000NumOrtList_2(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.updateAdjustSubMap5000NumOrtList_2(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubMap5000NumOrtList_3(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.updateAdjustSubMap5000NumOrtList_3(ortParam);
	}
	
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 수정
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int adjustSubGtypDstOrtList(Map<String, Object> ortParam)
			throws Exception {
		return managementDAO.adjustSubGtypDstOrtList(ortParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamGPS() throws Exception {
		return managementDAO.selectZoneNamGPS();
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 점의번호 조회
	 * @param gpsParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getGpsBpIdn(Map<String, Object> gpsParam) throws Exception {
		return managementDAO.selectGpsBpIdn(gpsParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 목록 조회 페이징 카운트
	 * @param gpsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getGpsListCnt(Map<String, Object> gpsParam) throws Exception {
		return managementDAO.selectGpsListCnt(gpsParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 목록 조회
	 * @param gpsParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getGpsList(Map<String, Object> gpsParam) throws Exception {
		return managementDAO.selectGpsList(gpsParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 수정
	 * @param gpsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustGpsList(Map<String, Object> gpsParam)
			throws Exception {
		return managementDAO.updateAdjustGpsList(gpsParam);
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getYearListLid() throws Exception {
		return managementDAO.selectYearListLid();
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 사업지구명 조회
	 * @param lidParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamLid(Map<String, Object> lidParam) throws Exception {
		return managementDAO.selectZoneNamLid(lidParam);
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 자료 ID 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getLidarIdn(String zoneCode) throws Exception {
		return managementDAO.selectLidarIdn(zoneCode);
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 보안등급 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSecurityCodeLid() throws Exception {
		return managementDAO.selectSecurityCodeLid();
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 목록 조회(상세목록 포함) 페이징 카운트
	 * @param lidParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getLidListCnt(Map<String, Object> lidParam) throws Exception {
		return managementDAO.selectLidListCnt(lidParam);
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 목록 조회(상세목록 포함)
	 * @param lidParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getLidList(Map<String, Object> lidParam) throws Exception {
		return managementDAO.selectLidList(lidParam);
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 수정
	 * @param lidParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustLidList(Map<String, Object> lidParam)
			throws Exception {
		return managementDAO.updateAdjustLidList(lidParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getYearListNir() throws Exception {
		return managementDAO.selectYearListNir();
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사업지구명 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamNir(Map<String, Object> NirParam) throws Exception {
		return managementDAO.selectZoneNamNir(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 코스번호 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhCourseNir(String zoneCode) throws Exception {
		return managementDAO.selectPhCourseNir(zoneCode);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 번호 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhNumNir(Map<String, Object> NirParam) throws Exception {
		return managementDAO.selectPhNumNir(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 해상도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getResolutionNir(Map<String, Object> NirParam) throws Exception {
		return managementDAO.selectResolutionNir(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 주점 목록 조회 페이징 카운트
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getOrientmapNirListCnt(Map<String, Object> nirParam) throws Exception {
		return managementDAO.selectOrientmapNirListCnt(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진 주점 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getOrientmapNirList(Map<String, Object> NirParam) throws Exception {
		return managementDAO.selectOrientmapNirList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 상세 목록
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubOrientmapNirList(Map<String, Object> NirParam) throws Exception {
		return managementDAO.selectSubOrientmapNirList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustOrientmapNir(Map<String, Object> NirParam) throws Exception {
		return managementDAO.updateAdjustOrientmapNir(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 목록 조회 페이징 카운트
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getProductNirListCnt(Map<String, Object> nirParam) throws Exception {
		return managementDAO.selectProductNirListCnt(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getProductNirList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.selectProductNirList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubProductNirList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.selectSubProductNirList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회(외부표정요소)
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubProductEONirList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.selectSubProductEONirList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 조회(메타데이터)
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubMetaNirList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.selectSubMetaNirList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(획득정보)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaNirmapAcqut(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustMetaNirmapAcqut(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 사진주점 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustNirOrientmapDts(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustNirOrientmapDts(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(제약정보)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaNirmapContr(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustMetaNirmapContr(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustNirZoneDts(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustNirZoneDts(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(메타데이터)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustNirBasemetaDts(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustNirBasemetaDts(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 상세 목록 수정(식별정보)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaNirmapIdent(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustMetaNirmapIdent(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 목록 조회 페이징 카운트
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getNirNoteListCnt(Map<String, Object> nirParam) throws Exception {
		return managementDAO.selectNirNoteListCnt(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getNirNoteList(Map<String, Object> NirParam) throws Exception {
		return managementDAO.selectNirNoteList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 플름번호 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getFilmNumNir(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.selectFilmNumNir(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 상세 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubNirNoteList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.selectSubNirNoteList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustNirNoteDts(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustNirNoteDts(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustNirCoursetestDts(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustNirCoursetestDts(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/NIR 성과 촬영기록부 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustNirNotecourseDts(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustNirNotecourseDts(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/카메라정보 카메라 구분 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getNirCameraDts() throws Exception {
		return managementDAO.selectNirCameraDts();
	}
	/**
	 * 통합관리 - NIR 관리/카메라정보 보유업체 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getNirkeepCmpn() throws Exception {
		return managementDAO.selectNirkeepCmpn();
	}
	/**
	 * 통합관리 - NIR 관리/카메라정보 목록 조회
	 * @param NirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getNirCameraDtsList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.selectNirCameraDtsList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/카메라정보 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustNirCameraDts(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustNirCameraDts(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/성과 수정
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustProductNirList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustProductNirList(NirParam);
	}
	/**
	 * 통합관리 - NIR 관리/성과 수정(외부표정요소)
	 * @param NirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustProductEONirList(Map<String, Object> NirParam)
			throws Exception {
		return managementDAO.updateAdjustProductEONirList(NirParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getYearPts() throws Exception {
		return managementDAO.getYearPts();
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 사업지구명 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamPts(Map<String, Object> ptsParam) throws Exception {
		return managementDAO.getzoneNamPts(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 목록 조회 페이징 카운트
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getPtsListCnt(Map<String, Object> ptsParam) throws Exception {
		return managementDAO.getPtsListCnt(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 목록 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPtsList(Map<String, Object> ptsParam) throws Exception {
		return managementDAO.getPtsList(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 상세 목록 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubPtsList(Map<String, Object> ptsParam) throws Exception {
		return managementDAO.getSubPtsList(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 수정
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubPtsList(Map<String, Object> ptsParam)
			throws Exception {
		return managementDAO.updateAdjustSubPtsList(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 점일련번호 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPNum(Map<String, Object> PtsParam) throws Exception {
		return managementDAO.getPNum(PtsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 목록 조회 페이징 카운트
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getPtsCoordListCnt(Map<String, Object> ptsParam) throws Exception {
		return managementDAO.getPtsCoordListCnt(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 목록 조회
	 * @param ptsParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPtsCoordList(Map<String, Object> ptsParam)
			throws Exception {
		return managementDAO.getPtsCoordList(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 수정
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustSubPtsCoordList(Map<String, Object> ptsParam)
			throws Exception {
		return managementDAO.updateAdjustSubPtsCoordList(ptsParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/사업지구 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertZone(Map<String, Object> airParam) {
		return managementDAO.insertZone(airParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/사업지구 삭제
	 * @param zoneCode
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delZone(String zoneCode) throws Exception {
		return managementDAO.delZone(zoneCode);
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 등록
	 * @param gpsParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertGps(Map<String, Object> gpsParam) throws Exception {
		return managementDAO.insertGps(gpsParam);
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS 기준국 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> GpsListZoneNam() throws Exception {
		return managementDAO.GpsListZoneNam();
	}
	/**
	 * 통합관리 - 사업지구 관리/GPS기준국 삭제
	 * @param gpsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delGps(Map<String, Object> gpsParam) throws Exception {
		return managementDAO.delGps(gpsParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> OrientmapZoneNam() throws Exception {
		return managementDAO.OrientmapZoneNam();
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertOrientmap(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertOrientmap(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> OrientmapPhCourse(String zoneCode) throws Exception {
		return managementDAO.OrientmapPhCourse(zoneCode);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> OrientmapPhNum(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.OrientmapPhNum(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과관리 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertProductAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(외부표정요소)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertProductEOAirList(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertProductEOAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertMetaAirmapIdent(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertMetaAirmapIdent(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertAirBasemetaDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertAirBasemetaDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertMetaAirmapContr(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertMetaAirmapContr(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록/촬영일자 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> insertphDate(Map<String, Object> airParam) throws Exception {
		return managementDAO.insertphDate(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록/렌즈 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> insertlensNum() throws Exception {
		return managementDAO.insertlensNum();
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 삭제
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delOrient(Map<String, Object> airParam) throws Exception {
		return managementDAO.delOrient(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhCourseOrient(String zoneCode) throws Exception {
		return managementDAO.getPhCourseOrient(zoneCode);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사업지구명 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamOrient(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.getZoneNamOrient(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/사진주점 관리 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhNumOrient(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.getPhNumOrient(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/성과 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delProList(Map<String, Object> airParam) throws Exception {
		return managementDAO.delProList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhCourseCmr(String zoneCode) throws Exception {
		return managementDAO.getPhCourseCmr(zoneCode);
		
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 관리 사업지구명 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getzoneNamCmr(Map<String, Object> airParam) throws Exception {
		return managementDAO.getzoneNamCmr(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertAirNoteDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertAirNoteDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertAirNotecourseDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertAirNotecourseDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertAirCoursetestDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertAirCoursetestDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 렌즈번호 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getlensNum() throws Exception {
		return managementDAO.getlensNum();
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delAirNote(Map<String, Object> airParam) throws Exception {
		return managementDAO.delAirNote(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/카메라정보 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertAirCameraDts(Map<String, Object> airParam)
			throws Exception {
		return managementDAO.insertAirCameraDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/카메라 정보 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delCmr(Map<String, Object> airParam) throws Exception {
		return managementDAO.delCmr(airParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록(메타데이터)
	 * @param demParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubGridIntDem(Map<String, Object> demParam) {
		return managementDAO.insertSubGridIntDem(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록(메타데이터)
	 * @param demParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubMap5000BaseMeta(Map<String, Object> demParam) {
		return managementDAO.insertSubMap5000BaseMeta(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 상세 목록 등록
	 * @param demParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubMap5000NumList(Map<String, Object> demParam) {
		return managementDAO.insertSubMap5000NumList(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getGridIntCnt(Map<String, Object> demParam) {
		return managementDAO.getGridIntCnt(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제1
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delGridIntDem(Map<String, Object> demParam) {
		return managementDAO.delGridIntDem(demParam);
	}
	/**
	 * 통합관리 - 수치표고 관리/수치표고 성과 삭제
	 * @param demParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delMap5000(Map<String, Object> demParam) {
		return managementDAO.delMap5000(demParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록
	 * @param ortParam
	 * @return String 
	 * @throws Exception
	 */
	@Override
	public String insertSubMap5000NumOrtList(Map<String, Object> ortParam) {
		return managementDAO.insertSubMap5000NumOrtList(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/해상도 등록
	 * @param ortParam
	 * @return String 
	 * @throws Exception
	 */
	@Override
	public String insertSubGtypDstOrt(Map<String, Object> ortParam) {
		return managementDAO.insertSubGtypDstOrt(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubMetaOrtIdent(Map<String, Object> ortParam) {
		return managementDAO.insertSubMetaOrtIdent(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubMetaBaseOrt(Map<String, Object> ortParam) {
		managementDAO.insertSubMetaBaseOrt(ortParam);
		managementDAO.insertSubMetaOrtIdent(ortParam);
		managementDAO.insertSubMetaOrtMait(ortParam);
		managementDAO.insertSubMetaOrtDistr(ortParam);
		managementDAO.insertSubMap5000NumOrtList_2(ortParam);
		return managementDAO.insertSubMap5000NumOrtList_3(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubMap5000NumOrtList_3(Map<String, Object> ortParam) {
		return managementDAO.insertSubMap5000NumOrtList_3(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 등록(메타데이터)
	 * @param ortParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubMap5000NumOrtList_2(Map<String, Object> ortParam) {
		return managementDAO.insertSubMap5000NumOrtList_2(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getGtypDstCnt(Map<String, Object> airParam) {
		return managementDAO.getGtypDstCnt(airParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getExistToMetaOrtIdent(Map<String, Object> ortParam) {
		return managementDAO.getExistToMetaOrtIdent(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delGtypDstOrt(Map<String, Object> ortParam) {
		return managementDAO.delGtypDstOrt(ortParam);
	}
	/**
	 * 통합관리 - 정사영상 관리/정사영상 성과 삭제
	 * @param ortParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delMap5000Ort(Map<String, Object> ortParam) {
		return managementDAO.delMap5000Ort(ortParam);
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 등록
	 * @param lidParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertLidList(Map<String, Object> lidParam) {
		return managementDAO.insertLidList(lidParam);
	}
	/**
	 * 통합관리 - 라이다 관리/라이다 성과 삭제
	 * @param lidParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delLid(Map<String, Object> lidParam) {
		return managementDAO.delLid(lidParam);
	}
	/**
	 * 통합관리 - NIR 관리/사진주점 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertOrientmapNir(Map<String, Object> nirParam) {
		return managementDAO.insertOrientmapNir(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/사진주점 등록
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> OrientmapNirZoneNam() {
		return managementDAO.OrientmapNirZoneNam();
	}
	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(메타데이터)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertNirBasemetaDts(Map<String, Object> nirParam) {
		return managementDAO.insertNirBasemetaDts(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(메타데이터)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertMetaNirmapIdent(Map<String, Object> nirParam) {
		return managementDAO.insertMetaNirmapIdent(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(메타데이터)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertMetaNirmapContr(Map<String, Object> nirParam) {
		return managementDAO.insertMetaNirmapContr(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/성과 관리 등록(외부표정요소)
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertProductEONirList(Map<String, Object> nirParam) {
		return managementDAO.insertProductEONirList(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/성과관리 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertProductNirList(Map<String, Object> nirParam) {
		return managementDAO.insertProductNirList(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/사진주점 삭제
	 * @param nirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delOrientNir(Map<String, Object> nirParam) {
		return managementDAO.delOrientNir(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/성과 삭제
	 * @param nirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delProNirList(Map<String, Object> nirParam) {
		return managementDAO.delProNirList(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/촬영기록부 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertNirNoteDts(Map<String, Object> nirParam) {
		return managementDAO.insertNirNoteDts(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/촬영기록부 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertNirNotecourseDts(Map<String, Object> nirParam) {
		return managementDAO.insertNirNotecourseDts(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/촬영기록부 등록
	 * @param nirParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertNirCoursetestDts(Map<String, Object> nirParam) {
		return managementDAO.insertNirCoursetestDts(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 사업지구명 조회
	 * @param nirParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getzoneNamNirCmr(Map<String, Object> nirParam) {
		return managementDAO.getzoneNamNirCmr(nirParam);
	}
	/**
	 * 통합관리 - NIR 관리/촬영기록부 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhCourseNirCmr(String zoneCode) {
		return managementDAO.getPhCourseNirCmr(zoneCode);
	}
	/**
	 * 통합관리 - NIR 관리/촬영기록부 삭제
	 * @param nirParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delNirNote(Map<String, Object> nirParam) {
		return managementDAO.delNirNote(nirParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 등록
	 * @param ptsParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubPtsList(Map<String, Object> ptsParam) {
		return managementDAO.insertSubPtsList(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/측량사업지구 삭제
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delPts(Map<String, Object> ptsParam) {
		return managementDAO.delPts(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 등록
	 * @param ptsParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertSubPtsCoordList(Map<String, Object> ptsParam) {
		return managementDAO.insertSubPtsCoordList(ptsParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 삭제
	 * @param ptsParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delPtsCoord(Map<String, Object> ptsParam) {
		return managementDAO.delPtsCoord(ptsParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업지구명(연도별) 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNam() {
		return managementDAO.getZoneNam();
	}
	/**
	 * 통합관리 - 항공사진 관리/촬영기록부 등록 필름번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getFilmNumInsert(Map<String, Object> airParam) {
		return managementDAO.getFilmNumInsert(airParam);
	}
	/**
	 * 통합관리 - 측량조서 관리/지상기준점 측량조서 분류 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSurvCode() {
		return managementDAO.getSurvCode();
	}

	/**
	 * 통합관리 - at성과 상세 조회
	 * @param sendMap
	 * @return
	 */
	@Override
	public List<?> getSubProductAtList(Map<String, Object> sendMap) throws Exception {
		return managementDAO.getSubProductAtList(sendMap);	
	}		

}