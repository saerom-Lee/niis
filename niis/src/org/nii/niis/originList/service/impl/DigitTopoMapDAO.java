package org.nii.niis.originList.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nii.niis.originList.service.DigitTopoMapVO;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("digitTopoMapDAO")
public class DigitTopoMapDAO extends EgovAbstractDAO  {

	// 원본DB 목록 - 최신 지도성과 목록 리스트 출력
	@SuppressWarnings("unchecked")
	public List<DigitTopoMapVO> selectNewDigitList(DigitTopoMapVO digitVO) {
		return  (List<DigitTopoMapVO>)list("digitTopoMap_SQL.selectNewDigitList", digitVO);
	}

	// 원본DB 목록 - 최신 지도성과 목록 리스트 cnt 출력
	@SuppressWarnings("deprecation")
	public int selectNewDigitListCnt(DigitTopoMapVO digitVO) {
		return (Integer)selectByPk("digitTopoMap_SQL.selectNewDigitListCnt", digitVO);
	}

	// 원본DB 목록 - 과거 지도성과이력 목록 리스트 출력
	@SuppressWarnings("unchecked")
	public List<DigitTopoMapVO> selectHistoryDigitList(DigitTopoMapVO digitVO) {
		return (List<DigitTopoMapVO>)list("digitTopoMap_SQL.selectHistoryDigitList", digitVO);
	}

	// 원본DB 목록 - 과거 지도성과이력 목록 리스트 cnt 출력
	@SuppressWarnings("deprecation")
	public int selectHistoryDigitListCnt(DigitTopoMapVO digitVO) {
		return (Integer)selectByPk("digitTopoMap_SQL.selectHistoryDigitListCnt", digitVO);
	}

	// 원본DB 목록 - 최신 지도성과 목록 상세조회
	public DigitTopoMapVO selectNewDigitDetail(DigitTopoMapVO digitVO) {
		return (DigitTopoMapVO) select("digitTopoMap_SQL.selectNewDigitDetail", digitVO);
	}

	// 원본DB 목록 - 과거 지도성과이력 목록 상세조회
	public DigitTopoMapVO selectHistoryDigitDetail(DigitTopoMapVO digitVO) {
		return (DigitTopoMapVO) select("digitTopoMap_SQL.selectHistoryDigitDetail", digitVO);
	}

	// 신청서 작성 - 신청서 활용목적 리스트
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPurposeList(String codeId) {
		return (List<Map<String, Object>>) list("digitTopoMap_SQL.getPurposeList", codeId);
	}

	// 신청서 작성 - 자료신청관리 테이블 인서트
	public String regDbApp(DigitTopoMapVO digitVO) {
		return (String)insert("digitTopoMap_SQL.regDbApp", digitVO);
	}

	// 신청서 작성 - 유저 자료신청 매핑테이블 인서트
	public void regUserDbApp(Map<String, Object> appMap) {
		insert("digitTopoMap_SQL.regUserDbApp", appMap);
		insert("digitTopoMap_SQL.regUserDbAppHist", appMap);
	}

	// 신청서 작성 - 수치지형도 정보 insert
	public void regUserDbAppMap(DigitTopoMapVO digitVO) {
		insert("digitTopoMap_SQL.regUserDbAppMap", digitVO);
	}

	
}
