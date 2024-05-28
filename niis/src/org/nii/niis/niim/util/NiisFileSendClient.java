package org.nii.niis.niim.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.nii.niis.niim.service.ApplyService;

public class NiisFileSendClient extends FileSendClient {

	protected ApplyService applyService;
	
	public NiisFileSendClient(String serverIp, int serverPort, String supIdn) {
		super(serverIp, serverPort);
		sendMap = new HashMap<String, Object>();
		sendMap.put("lastChangeUsr"	, "");
		sendMap.put("supIdn", supIdn);
		serverNm = "Niis";
	}

	@Override
	public void updateStatus(String approvalCde) {
		try{
			sendMap.put("approvalCde", approvalCde);
			if(null != applyService) System.out.println("applyService is not null " + applyService);
			if(null != sendMap) System.out.println("sendMap is not null " + sendMap);
			applyService.uptDbAppAuth(sendMap);
			if("2".equals(approvalCde)){
				applyAppYnSms();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void setService(Object service) {
		applyService = (ApplyService)service;
		
	}
	
	protected void applyAppYnSms() {
		
		URL url = null;
		URLConnection connection = null;
		
		OutputStream os 		= null;		
		InputStream is 			= null;
		InputStreamReader isr 	= null;
		BufferedReader br 		= null;
		
		try{
			String baseUrl = niisIpPort;
			String action = "/niis/send/applyAppYnSms.do";
			
			String sendParam = "&supIdn=" + sendMap.get("supIdn") + "&approvalCde=2";
			
			url = new URL(baseUrl + action);
			
			connection = (HttpURLConnection)url.openConnection();
			connection.setDoOutput(true);
			
			connection.connect();
			
			os = (OutputStream)connection.getOutputStream();
			//os.write(data.toJSONString().getBytes("UTF-8"));
			os.write(sendParam.getBytes("UTF-8"));
			os.flush();
			os.close();
			
			is = connection.getInputStream();
			
			isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader(isr);
			
			StringBuffer sb = new StringBuffer();
			String line;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			System.out.println(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != os){ try{ os.close(); }catch(Exception e){} }
			if(null != br){ try{ br.close(); }catch(Exception e){} }
			if(null != isr){ try{ isr.close(); }catch(Exception e){} }
			if(null != is){ try{ is.close(); }catch(Exception e){} }
		}
	}
}
