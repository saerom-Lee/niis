package org.nii.niis.smartMap.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("smartMapDAO")

public class SmartMapDAO extends EgovAbstractDAO {

	@Override
	@Resource(name ="sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient){
		super.setSuperSqlMapClient(sqlMapClient);
	}
	
	/**
	 * 메타데이터 전송 
	 * @param sendMap
	 * @return
	 * @since 2017. 9. 20.
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getMetaInfo(Map<String, Object> sendMap) {
		return (List<Map<String, Object>>)list("smartMapDAO.get_"+sendMap.get("queryId"), sendMap);
	}
	
	
	public void metaInsert (String tableName, Map<String, Object> sendMap) {
		insert("smartMapDAO."+tableName, sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getZoneList(Map<String, Object> sendMap){
		return (List<String>)list("niisSmartMapDAO.getZoneList", sendMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getStoInfo(){
		return (List<Map<String, Object>>)list("smartMapDAO.stoInfo");
	}
	
	@SuppressWarnings("deprecation")
	public String getStoDrv(String stoIdn){
		return (String)getSqlMapClientTemplate().queryForObject("smartMapDAO.stoDrv", stoIdn);
	}
	
	@SuppressWarnings("deprecation")
	public String getFolderNam(String storageCde){
		return (String)getSqlMapClientTemplate().queryForObject("smartMapDAO.folderNam", storageCde);
	}
	
	public void insertOrtLoc(Map<String, Object> sendMap){
		insert("smartMapDAO.insertOrtLoc", sendMap);
	}
	
	public void insertAirLoc(Map<String, Object> sendMap){
		insert("smartMapDAO.insertAirLoc", sendMap);
	}
}
