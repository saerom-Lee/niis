package org.nii.niis.originList.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.nii.niis.connNiim.service.impl.NiisApplyDAO;
import org.nii.niis.niim.service.impl.ApplyDAO;
import org.nii.niis.originList.service.DigitTopoMapService;
import org.nii.niis.originList.service.DigitTopoMapVO;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("digitTopoMapService")
public class DigitTopoMapServiceImpl extends EgovAbstractServiceImpl implements DigitTopoMapService{

	@Resource(name="digitTopoMapDAO")
	private DigitTopoMapDAO digitTopoMapDAO;
	
	@Resource(name="applyDAO")
	private ApplyDAO applyDAO;
	
	@Resource(name="niisApplyDAO")
	private NiisApplyDAO niisApplyDAO;
	
	// 원본DB 목록 - 최신 지도성과 목록 리스트 출력
	@Override
	public List<DigitTopoMapVO> selectNewDigitList(DigitTopoMapVO digitVO) throws Exception {
		return digitTopoMapDAO.selectNewDigitList(digitVO);
	}

	// 원본DB 목록 - 최신 지도성과 목록 리스트 cnt 출력
	@Override
	public int selectNewDigitListCnt(DigitTopoMapVO digitVO) throws Exception {
		return digitTopoMapDAO.selectNewDigitListCnt(digitVO);
	}

	// 원본DB 목록 - 과거 지도성과이력 목록 리스트 출력
	@Override
	public List<DigitTopoMapVO> selectHistoryDigitList(DigitTopoMapVO digitVO) {
		return digitTopoMapDAO.selectHistoryDigitList(digitVO);
	}

	// 원본DB 목록 - 과거 지도성과이력 목록 리스트 cnt 출력
	@Override
	public int selectHistoryDigitListCnt(DigitTopoMapVO digitVO) {
		return digitTopoMapDAO.selectHistoryDigitListCnt(digitVO);
	}

	// 원본DB 목록 - 최신 지도성과 목록 상세조회
	@Override
	public DigitTopoMapVO selectNewDigitDetail(DigitTopoMapVO digitVO) {
		return digitTopoMapDAO.selectNewDigitDetail(digitVO);
	}

	// 원본DB 목록 - 과거 지도성과이력 목록 상세조회
	@Override
	public DigitTopoMapVO selectHistoryDigitDetail(DigitTopoMapVO digitVO) {
		return digitTopoMapDAO.selectHistoryDigitDetail(digitVO);
	}


}
