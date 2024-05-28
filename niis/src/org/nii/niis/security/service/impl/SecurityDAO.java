package org.nii.niis.security.service.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("securityDAO")
public class SecurityDAO extends EgovAbstractDAO {
	
	public void detecting(Map<String,Object> map) throws Exception{
		insert("watermarkDAO.insertMarkIndex",map);
	}
}
