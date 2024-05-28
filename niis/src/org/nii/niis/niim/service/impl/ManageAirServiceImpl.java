package org.nii.niis.niim.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.ManageAirService;
import org.springframework.stereotype.Service;


/**
 * 메인 implements 객체 
 */
@Service("manageAirService")
public class ManageAirServiceImpl implements ManageAirService {

	/** ManagementDAO */
    @Resource(name="manageAirDAO")
    private ManageAirDAO manageAirDAO;
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업연도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getYearListAir() throws Exception {
		return manageAirDAO.selectYearListAir();
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사업지구명(연도별) 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getZoneNamAir(Map<String, Object> airParam) throws Exception {
		return manageAirDAO.selectZoneNamAir(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhCourseAir(String zoneCode) throws Exception {
		return manageAirDAO.selectPhCourseAir(zoneCode);
	}
	/**
	 * 통합관리 - 항공사진 관리/항공사진 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getPhNumAir(Map<String, Object> airParam) throws Exception {
		return manageAirDAO.selectPhNumAir(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 보안등급  조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSecurityCode() throws Exception {
		return manageAirDAO.selectSecurityCode();
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 카메라 구분 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getCameraCode() throws Exception {
		return manageAirDAO.selectCameraCode();
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 해상도 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getResolutionAir(Map<String, Object> airParam) throws Exception {
		return manageAirDAO.selectResolutionAir(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 목록 조회 페이징 카운트
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getProductAirListCnt(Map<String, Object> airParam) throws Exception {
		return manageAirDAO.selectProductAirListCnt(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getProductAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.selectProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 상세 목록 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubProductAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.selectSubProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 상세 목록 조회(외부표정요소)
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubProductEOAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.selectSubProductEOAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 상세 목록 조회(메타데이터)
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getSubMetaAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.selectSubMetaAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 수정(획득정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaAirmapAcqut(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.updateAdjustMetaAirmapAcqut(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 수정(제약정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaAirmapContr(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.updateAdjustMetaAirmapContr(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 수정(축척)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirZoneDts(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.updateAdjustAirZoneDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 수정(메타데이터)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustAirBasemetaDts(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.updateAdjustAirBasemetaDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 상세 목록 수정(식별정보)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustMetaAirmapIdent(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.updateAdjustMetaAirmapIdent(airParam);
	}
	/**
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 수정
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	public int updateAdjustProductAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.updateAdjustProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 수정(외부표정요소)
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateAdjustProductEOAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.updateAdjustProductEOAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 사업지구명 조회
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> OrientmapZoneNam() throws Exception {
		return manageAirDAO.OrientmapZoneNam();
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 코스번호 조회
	 * @param zoneCode
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> OrientmapPhCourse(String zoneCode) throws Exception {
		return manageAirDAO.OrientmapPhCourse(zoneCode);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 사진번호 조회
	 * @param airParam
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> OrientmapPhNum(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.OrientmapPhNum(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 등록
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertProductAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.insertProductAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 등록(외부표정요소)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertProductEOAirList(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.insertProductEOAirList(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertMetaAirmapIdent(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.insertMetaAirmapIdent(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertAirBasemetaDts(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.insertAirBasemetaDts(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 등록(메타데이터)
	 * @param airParam
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String insertMetaAirmapContr(Map<String, Object> airParam)
			throws Exception {
		return manageAirDAO.insertMetaAirmapContr(airParam);
	}
	/**
	 * 통합관리 - 항공사진 관리/해방전후 항공사진 관리 삭제
	 * @param airParam
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delProList(Map<String, Object> airParam) throws Exception {
		return manageAirDAO.delProList(airParam);
	}
	
}