package org.nii.niis.niim.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardMaster(Map<String, Object> param) throws Exception {
		return (Map<String, Object>)selectByPk("boardDAO.getBoardMaster", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBoardTopList(Map<String, Object> param) throws Exception {
		return (List<Map<String, Object>>)list("boardDAO.getBoardTopList", param);
	}
	
	public int getBoardListCnt(Map<String, Object> param) throws Exception {
		return (Integer)selectByPk("boardDAO.getBoardListCnt", param);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBoardList(Map<String, Object> param) throws Exception {
		return (List<Map<String, Object>>)list("boardDAO.getBoardList", param);
	}

	public int updateHit(Map<String, Object> param) throws Exception {
		return update("boardDAO.updateHit", param);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getBoardDetail(Map<String, Object> param) throws Exception {
		return (Map<String, Object>)selectByPk("boardDAO.getBoardDetail", param);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getBoardFileList(Map<String, Object> param) throws Exception {
		return (List<Map<String, Object>>)list("boardDAO.getBoardFileList", param);
	}

	public String insertBoard(Map<String, Object> param) throws Exception {
		return (String)insert("boardDAO.insertBoard", param);
	}

	public int updateBoard(Map<String, Object> param) throws Exception {
		return update("boardDAO.updateBoard", param);
	}

	public int deleteBoard(Map<String, Object> param) throws Exception {
		return update("boardDAO.deleteBoard", param);
	}

	public String insertBoardFile(Map<String, Object> fileMap) throws Exception {
		return (String)insert("boardDAO.insertBoardFile", fileMap);
	}

	public int deleteBoardFileAll(Map<String, Object> param) throws Exception {
		return delete("boardDAO.deleteBoardFileAll", param);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getAttachFile(Map<String, Object> param) throws Exception {
		return (Map<String, Object>)selectByPk("boardDAO.getAttachFile", param);
	}
	
	public int updateBoardPop(Map<String, Object> param) throws Exception {
		return update("boardDAO.updateBoardPop", param);
	}
	
	public String insertBoardPop(Map<String, Object> param) throws Exception {
		return (String)insert("boardDAO.insertPop", param);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> checkUrl(Map<String, Object> some)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map= (Map<String, Object>)selectByPk("boardDAO.checkUrl", "null");
		return map;
	}
}
