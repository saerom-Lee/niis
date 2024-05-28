package org.nii.niis.send.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.send.service.SendService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

@Service("sendService")
public class SendServiceImpl implements SendService {
	
	@Resource(name="sendDAO")
	private SendDAO sendDAO;
	
	@Resource(name="txManager")
	protected DataSourceTransactionManager txManager;

	@Override
	public void smsSend(Map<String, Object> sendMap) throws Exception {
		sendDAO.smsSend(sendMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void smsSend(String to, String from, String msg) throws Exception {
		
		Map<String, Object> sendMap = new HashMap();
		sendMap.put("trPhone", to);
		sendMap.put("trCallback", from);
		sendMap.put("trMsg", msg);
		sendDAO.smsSend(sendMap);
	}
}
