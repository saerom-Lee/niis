package org.nii.niis.niim.util;

import java.util.HashMap;
import java.util.List;

import org.nii.niis.connNiim.service.NiisSmartMapService;

public class SmartFileSendClient extends FileSendClient {

	protected NiisSmartMapService niisSmartMapService;
	
	public SmartFileSendClient(String serverIp, int serverPort, List<String> fileList) {
		super(serverIp, serverPort);
		sendMap = new HashMap<String, Object>();
		sendMap.put("lastChangeUsr"	, "");
		sendMap.put("filePath", fileList);
		serverNm = "Smart";
	}

	@Override
	public void updateStatus(String approvalCde) {
		try{
			sendMap.put("approvalCde", approvalCde);
			niisSmartMapService.updateSmartMap(sendMap);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void setService(Object service) {
		niisSmartMapService = (NiisSmartMapService)service;
		
	}
}
