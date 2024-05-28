package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

public interface ApplyService {

	/**
	 * 원본DB 신청서 관리 리스트카운트
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	int getDbAppListCnt(Map<String, Object> param) throws Exception;
	/**
	 * 원본DB 신청서 관리 리스트
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	List<?> getDbAppList(Map<String, Object> param) throws Exception;

	
	/**
	 * 원본DB 신청서 관리 상세
	 * @param param
	 * @return Map
	 * @throws Exception
	 */
	Map<String, Object> getDbAppDetail(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 원본DB 신청서 관리 상세 발급내역
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	List<?> getDbAppReqList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 원본DB 신청서 관리 상세 결과보고서
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	List<?> getDbAppRstReview(Map<String, Object> param) throws Exception;

	
	/**
	 * 원본DB 신청서 등록
	 * @param param
	 * @return String
	 * @throws Exception
	 */
	String regDbApp(Map<String, Object> sendMap) throws Exception;

	
	/**
	 * 원본DB 신청서 수정
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	int uptDbApp(Map<String, Object> sendMap) throws Exception;

	
	/**
	 * 원본DB 신청서 삭제
	 * @param param
	 * @throws Exception
	 */
	void delDbApp(Map<String, Object> sendMap) throws Exception;
	
	
	/**
	 * 원본DB 공급시스템 신청내역 조회
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	List<?> getDbAppAuthList(Map<String, Object> param) throws Exception;
	
	
	/**
	 * 원본DB 공급시스템 신청 승인상태 변경
	 * @param param
	 * @throws Exception
	 */
	void uptDbAppAuth(Map<String, Object> param) throws Exception;
	Map<String, Object> approvalDbAppAuth(Map<String, Object> param) throws Exception;
	void rejectDbAppAuth(Map<String, Object> param) throws Exception;
	
	List<Map<String, Object>> getSupIdnList() throws Exception;
	
	
	public void uptDbApproval(Map<String,Object> map) throws Exception;
	
	/**
	 * 신청정보 상세 리스트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> getApplyDetailList(Map<String, Object> param) throws Exception;
}
