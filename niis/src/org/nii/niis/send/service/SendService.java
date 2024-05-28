package org.nii.niis.send.service;

import java.util.Map;

public interface SendService {

	public void smsSend (Map<String, Object> sendMap) throws Exception;
	
	public void smsSend(String from, String to, String msg) throws Exception; 
}
