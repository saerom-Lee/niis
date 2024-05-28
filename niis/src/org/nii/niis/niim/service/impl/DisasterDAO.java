package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("disasterDAO")
public class DisasterDAO extends EgovAbstractDAO {

	@Override
	@Resource(name ="sqlMapClient3")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient){
		super.setSuperSqlMapClient(sqlMapClient);
	}
	
	public int getDisasterListCnt(Map<String, Object> param) throws Exception {
		return (Integer)selectByPk("disasterDAO.getDisasterListCnt", param);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDisasterList(Map<String, Object> param) throws Exception {
		return (List<Map<String, Object>>)list("disasterDAO.getDisasterList", param);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDisasterDetail(Map<String, Object> param) throws Exception {
		return (Map<String, Object>)selectByPk("disasterDAO.getDisasterDetail", param);
	}
	
	public List<Map<String, Object>> getDisasterVideoList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("disasterDAO.getDisasterVideoList", sendMap);
	}

//	public List<Map<String, Object>> getDisasterCodeList(Map<String, Object> sendMap) throws Exception {
//		return (List<Map<String, Object>>)list("disasterDAO.getDisasterCodeList", sendMap);
//	}
//
//	public List<Map<String, Object>> getDisasterRegion1List(Map<String, Object> sendMap) throws Exception {
//		return (List<Map<String, Object>>)list("disasterDAO.getDisasterRegion1List", sendMap);
//	}
//	
//	public List<Map<String, Object>> getDisasterRegion2List(Map<String, Object> sendMap) throws Exception {
//		return (List<Map<String, Object>>)list("disasterDAO.getDisasterRegion2List", sendMap);
//	}
//	
//	public String insertDisasterDownloadHist(Map<String, Object> sendMap) throws Exception {
//		return (String) insert("disasterDAO.insertDisasterDownloadHist", sendMap);
//	}
}
