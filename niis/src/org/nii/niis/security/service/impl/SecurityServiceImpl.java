package org.nii.niis.security.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CKNB.WatermarkComponent;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
	
	@Resource(name="securityDAO")
	private SecurityDAO securityDAO;
	
	@Autowired
	private WatermarkComponent watermarkComponent;
	
	public void detecting(Map<String,Object> map) throws Exception{
		securityDAO.detecting(map);
	};

}
