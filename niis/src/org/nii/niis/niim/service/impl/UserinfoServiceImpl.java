package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.UserinfoService;
import org.springframework.stereotype.Service;

import com.woowonSoft.framework.util.StringUtil;

@Service("userinfoService")
public class UserinfoServiceImpl implements UserinfoService {
	
	@Resource(name="userinfoDAO")
	private UserinfoDAO userinfoDAO;

	
	/**
	 * 사용자관리 사용자 리스트카운트
	 * @param param
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int getUserInfoListCnt(Map<String, Object> param) throws Exception {
		return userinfoDAO.getUserInfoListCnt(param);
	}
	
	/**
	 * 사용자관리 사용자 리스트
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getUserInfoList(Map<String, Object> param) throws Exception {
		return userinfoDAO.getUserInfoList(param);
	}

	
	/**
	 * 사용자관리 사용자 상세
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getUserInfoDetail(Map<String, Object> param) throws Exception {
		return userinfoDAO.getUserInfoDetail(param);
	}

	
	/**
	 * 사용자관리 사용자 id 중복검사
	 * @param usrId
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int chkUserId(Map<String, Object> chkMap) throws Exception {
		return userinfoDAO.chkUserId(chkMap);
	}
	
	/**
	 * 사용자관리 사용자 인증서 갱신 용 사용자 정보 확인
	 * @param usrId, usrNm, usrTel 등
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int chkUserInfo(Map<String, Object> chkMap) throws Exception {
		return userinfoDAO.chkUserInfo(chkMap);
	}

	/**
	 * 사용자관리 사용자 등록
	 * @param sendMap
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String regUserInfo(Map<String, Object> sendMap) throws Exception {
		return userinfoDAO.regUserInfo(sendMap);
	}
	
	/**
	 * 사용자관리 인증서 갱신 정보 업데이트
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public String updateUserInfoForGPKI(Map<String, Object> sendMap) throws Exception {
		return userinfoDAO.updateUserInfoForGPKI(sendMap);
	}
	
	/**
	 * 사용자관리 반려 사용자 재신청
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int reRegUserInfo(Map<String, Object> sendMap) throws Exception {
		return userinfoDAO.reRegUserInfo(sendMap);
	}

	
	/**
	 * 사용자관리 사용자 수정
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int uptUserInfo(Map<String, Object> sendMap) throws Exception {
		return userinfoDAO.uptUserInfo(sendMap);
	}

	
	/**
	 * 사용자관리 사용자 삭제
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int delUserInfo(Map<String, Object> sendMap) throws Exception {
		return userinfoDAO.delUserInfo(sendMap);
	}
	
	
	/**
	 * 사용자관리 현재 비밀번호 조회
	 * @param usrMgno
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String getCurrPass(String usrMgno) throws Exception {
		return userinfoDAO.getCurrPass(usrMgno);
	}

	
	/**
	 * 사용자관리 사용자 승인상태 변경
	 * @param sendMap
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int uptUserSt(Map<String, Object> sendMap) throws Exception {
		return userinfoDAO.uptUserSt(sendMap);
	}

	@Override
	public int cfrmInitGuide(Map<String, Object> sendMap) throws Exception {
		return userinfoDAO.cfrmInitGuide(sendMap);
	}
	
	/**
	 * 관리자조회
	 */
	@Override
	public List<String> getMgr() throws Exception {
		return userinfoDAO.getMgr();
	}

	/**
	 * 승인 담당자 번호 조회 (decrypt)
	 */
	public List<String> getManagerNumberList() throws Exception {
		List<String> list = userinfoDAO.getManagerNumberList();
		for(int i=0; i<list.size(); i++) {
			list.set(i, StringUtil.getSeedDecrypt(list.get(i)));
		}
		return list;
	}
	
	/**
	 * 승인 담당자 번호 조회 (decrypt)
	 */
	public List<String> getIimManagerNumberList() throws Exception {
		List<String> list = userinfoDAO.getIimManagerNumberList();
		return list;
	}
	
	/**
	 * 승인 담당자 번호 조회 (decrypt)
	 */
	public List<String> getMapManagerNumberList() throws Exception {
		List<String> list = userinfoDAO.getMapManagerNumberList();
		return list;
	}
}
