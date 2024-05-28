package org.nii.niis.send.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("sendDAO")
public class SendDAO extends EgovAbstractDAO {

	@Override
	@Resource(name ="sqlMapClient2")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient){
		super.setSuperSqlMapClient(sqlMapClient);
	}
	
	public void smsSend (Map<String, Object> sendMap) {
		insert("sendDAO.smsSend", sendMap);
	}
}
