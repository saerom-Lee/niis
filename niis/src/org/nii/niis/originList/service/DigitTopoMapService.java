package org.nii.niis.originList.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


public interface DigitTopoMapService {

	/**
	 * 원본DB 목록 - 최신 지도성과 목록 리스트 출력
	 * @param digitVO
	 * @return
	 * @throws Exception
	 */
	List<DigitTopoMapVO> selectNewDigitList(DigitTopoMapVO digitVO) throws Exception;

	/**
	 * 원본DB 목록 - 최신 지도성과 목록 리스트 cnt 출력
	 * @param digitVO
	 * @return
	 * @throws Exception
	 */
	int selectNewDigitListCnt(DigitTopoMapVO digitVO) throws Exception;

	
	/**
	 * 원본DB 목록 - 과거 지도성과이력 목록 리스트 출력
	 * @param digitVO
	 * @return
	 * @throws Exception
	 */
	List<DigitTopoMapVO> selectHistoryDigitList(DigitTopoMapVO digitVO);

	/**
	 * 원본DB 목록 - 과거 지도성과이력 목록 리스트 cnt 출력
	 * @param digitVO
	 * @return
	 * @throws Exception
	 */
	int selectHistoryDigitListCnt(DigitTopoMapVO digitVO);

	/**
	 * 원본DB 목록 - 최신 지도성과 상세조회
	 * @param digitVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	DigitTopoMapVO selectNewDigitDetail(DigitTopoMapVO digitVO);

	/**
	 * 원본DB 목록 - 과거 지도성과이력 상세조회
	 * @param digitVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	DigitTopoMapVO selectHistoryDigitDetail(DigitTopoMapVO digitVO);


}
