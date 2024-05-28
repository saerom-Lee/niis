package org.nii.niis.niim.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	Map<String, Object> getBoardMaster(Map<String, Object> param) throws Exception;
	
	List<Map<String, Object>> getBoardTopList(Map<String, Object> param) throws Exception;
	
	int getBoardListCnt(Map<String, Object> param) throws Exception;
	List<Map<String, Object>> getBoardList(Map<String, Object> param) throws Exception;

	Map<String, Object> getBoardDetail(Map<String, Object> param) throws Exception;
	List<Map<String, Object>> getBoardFileList(Map<String, Object> param) throws Exception;

	Map<String, Object> insertBoard(Map<String, Object> param) throws Exception;

	Map<String, Object> updateBoard(Map<String, Object> param) throws Exception;

	Map<String, Object> deleteBoard(Map<String, Object> param) throws Exception;

	Map<String, Object> getAttachFile(Map<String, Object> param) throws Exception;
	
	Map<String, Object> insertBoardPop(Map<String, Object> param) throws Exception;
	
	Map<String, Object> updateBoardPop(Map<String, Object> param) throws Exception;
	
	String checkUrl()throws Exception;
}
