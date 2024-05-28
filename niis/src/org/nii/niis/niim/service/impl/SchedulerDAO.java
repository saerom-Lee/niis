package org.nii.niis.niim.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository
public class SchedulerDAO extends EgovAbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExpiredList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("schedulerDAO.getExpiredList", sendMap);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getExpiredFileList(Map<String, Object> sendMap) throws Exception {
		return (List<Map<String, Object>>)list("schedulerDAO.getExpiredFileList", sendMap);
	}
}
