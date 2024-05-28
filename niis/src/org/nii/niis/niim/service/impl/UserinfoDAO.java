package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("userinfoDAO")
public class UserinfoDAO extends EgovAbstractDAO {

	
	/**
	 * 사용자관리 사용자 리스트카운트
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	public int getUserInfoListCnt(Map<String, Object> param) throws Exception {
		return (Integer) selectByPk("userinfoDAO.getUserInfoListCnt", param);
	}
	
	/**
	 * 사용자관리 사용자 리스트
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<?> getUserInfoList(Map<String, Object> param) throws Exception {
		return (List<?>) getSqlMapClientTemplate().queryForList("userinfoDAO.getUserInfoList", param);
	}

	
	/**
	 * 사용자관리 사용자 상세
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public List<?> getUserInfoDetail(Map<String, Object> param) throws Exception {
		return (List<?>) getSqlMapClientTemplate().queryForList("userinfoDAO.getUserInfoDetail", param);
	}

	
	/**
	 * 사용자관리 사용자 id 중복검사
	 * @param usrId
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public int chkUserId(Map<String, Object> chkMap) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("userinfoDAO.chkUserId", chkMap);
	}
	
	/**
	 * 사용자관리 사용자 인증서 갱신 용 사용자 정보 확인
	 * @param usrId
	 * @return int
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public int chkUserInfo(Map<String, Object> chkMap) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("userinfoDAO.chkUserInfo", chkMap);
	}

	/**
	 * 사용자관리 사용자 등록
	 * @param sendMap
	 * @return String
	 * @throws Exception
	 */
	public String regUserInfo(Map<String, Object> sendMap) throws Exception {
		return (String)insert("userinfoDAO.regUserInfo", sendMap);
	}
	
	/**
	 * 사용자관리 인증서 갱신 정보 업데이트
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	public String updateUserInfoForGPKI(Map<String, Object> sendMap) throws Exception {
		return (String)insert("userinfoDAO.updateUserInfoForGPKI", sendMap);
	}
	
	/**
	 * 사용자관리 반려 사용자 재신청
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	public int reRegUserInfo(Map<String, Object> sendMap) throws Exception {
		return update("userinfoDAO.reRegUserInfo", sendMap);
	}
	
	/**
	 * 사용자관리 사용자 수정
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	public int uptUserInfo(Map<String, Object> sendMap) throws Exception {
		return update("userinfoDAO.uptUserInfo", sendMap);
	}

	
	/**
	 * 사용자관리 사용자 삭제
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	public int delUserInfo(Map<String, Object> sendMap) throws Exception {
		return update("userinfoDAO.uptUserSt", sendMap);
	}
	
	
	/**
	 * 사용자관리 현재 비밀번호 조회
	 * @param usrMgno
	 * @return String
	 * @throws Exception
	 */
	public String getCurrPass(String usrMgno) throws Exception {
		return (String) selectByPk("userinfoDAO.getCurrPass", usrMgno);
	}

	
	/**
	 * 사용자관리 사용자 승인상태 변경
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	public int uptUserSt(Map<String, Object> sendMap) throws Exception {
		return update("userinfoDAO.uptUserSt", sendMap);
	}

	public int cfrmInitGuide(Map<String, Object> sendMap) {
		return update("userinfoDAO.cfrmInitGuide", sendMap);
	}
	/**
	 * 관리자조회
	 * @param usrMgno
	 * @return
	 * @throws Exception
	 */
	public  List<String> getMgr() throws Exception {
		return (List<String>) getSqlMapClientTemplate().queryForList("userinfoDAO.getMgr");
	}
	
	/**
	 * 승인 담당자 번호 조회
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getManagerNumberList() throws Exception {
		return (List<String>) list("userinfoDAO.getManagerNumberList");
	}
	
	/**
	 * 승인 담당자 번호 조회
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getIimManagerNumberList() throws Exception {
		return (List<String>) list("userinfoDAO.getIimManagerNumberList");
	}
	
	/**
	 * 승인 담당자 번호 조회
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<String> getMapManagerNumberList() throws Exception {
		return (List<String>) list("userinfoDAO.getMapManagerNumberList");
	}

}
