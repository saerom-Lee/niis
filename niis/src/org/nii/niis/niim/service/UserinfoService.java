package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

public interface UserinfoService {

	/**
	 * 사용자관리 사용자 리스트카운트
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	int getUserInfoListCnt(Map<String, Object> param) throws Exception;
	
	/**
	 * 사용자관리 사용자 리스트
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	List<?> getUserInfoList(Map<String, Object> param) throws Exception;
	

	/**
	 * 사용자관리 사용자 상세
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	List<?> getUserInfoDetail(Map<String, Object> param) throws Exception;

	
	/**
	 * 사용자관리 사용자 id 중복검사
	 * @param chkMap
	 * @return int
	 * @throws Exception
	 */
	int chkUserId(Map<String, Object> chkMap) throws Exception;
	
	
	/**
	 * 사용자관리 인증서 갱신 용 사용자 정보 확인
	 * @param chkMap
	 * @return int
	 * @throws Exception
	 */
	int chkUserInfo(Map<String, Object> chkMap) throws Exception;

	/**
	 * 사용자관리 사용자 등록
	 * @param sendMap
	 * @return String
	 * @throws Exception
	 */
	String regUserInfo(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 사용자관리 인증서 갱신 정보 업데이트
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	String updateUserInfoForGPKI(Map<String, Object> sendMap) throws Exception;

	/**
	 * 사용자관리 반려 사용자 재신청
	 * @param sendMap
	 * @return String
	 * @throws Exception
	 */
	int reRegUserInfo(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 사용자관리 사용자 수정
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	int uptUserInfo(Map<String, Object> sendMap) throws Exception;

	/**
	 * 사용자관리 사용자 삭제
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	int delUserInfo(Map<String, Object> sendMap) throws Exception;
	
	/**
	 * 사용자관리 현재 비밀번호 조회
	 * @param usrMgno
	 * @return String
	 * @throws Exception
	 */
	String getCurrPass(String usrMgno) throws Exception;

	/**
	 * 사용자관리 사용자 승인상태 변경
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	int uptUserSt(Map<String, Object> sendMap) throws Exception;

	int cfrmInitGuide(Map<String, Object> sendMap) throws Exception;
	
	List<String> getMgr() throws Exception;
	
	
	List<String> getManagerNumberList() throws Exception;
	
	List<String> getMapManagerNumberList() throws Exception;
	
	List<String> getIimManagerNumberList() throws Exception;

}
