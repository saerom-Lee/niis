package org.nii.niis.niim.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.connNiim.service.NiisSmartMapService;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import CKNB.WatermarkComponent;
import egovframework.rte.fdl.property.EgovPropertyService;

@Component
public class Scheduler {

	@Resource(name="propertiesService")
	private EgovPropertyService propertyService;
	
	@Resource(name="schedulerService")
	private SchedulerService schedulerService;
	
	@Resource(name="applyService")
	private ApplyService applyService;
	
	@Resource(name="niisSmartMapService")
	private NiisSmartMapService niisSmartMapService;
	
	@Autowired
	private WatermarkComponent watermartComponent;
	//@Autowired
	//private FileSendClient fileSendClient;
	
	/**
	 * 승인된 신청서 기간 만료 처리
	 */
	
	//@Scheduled(cron="0 5 0 * * *")
	@Scheduled(cron="0 0/1 * * * *")
	public void expiredAppUpt(){
		
		System.out.println("#################### Scheduler.expiredAppUpt START ####################");
		try{
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("limitDay", propertyService.getInt("Globals.downloadLimitDay"));
			
			//승인 후 기간 만료 신청서 조회
			List<Map<String, Object>> expiredList = schedulerService.getExpiredList(sendMap);
			
			for(int i=0; i<expiredList.size(); i++){
				
				Map<String, Object> expiredMap = new HashMap<String, Object>();
				expiredMap.put("supIdn", expiredList.get(i).get("supIdn"));
				expiredMap.put("lastChangeUsr", expiredList.get(i).get("lastChangeUsr"));
				expiredMap.put("approvalCde", "7");
				
				try{
					schedulerService.expiredAppUpt(expiredMap);
					System.out.println("#################### Scheduler.expirdeAppUpt succsess #### supIdn[" + expiredMap.get("supIdn") + "], usrMgno[" + expiredMap.get("usrMgno") + "]");
				}catch(Exception e){
					System.out.println("#################### Scheduler.expirdeAppUpt error   #### supIdn[" + expiredMap.get("supIdn") + "], usrMgno[" + expiredMap.get("usrMgno") + "]");
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("#################### Scheduler.expiredAppUpt E N D ####################");
	}
	
	
	/**
	 * 승인된 신청서 기간 만료 처리
	 */
	//@Scheduled(cron="0 35 2 * * *")
	public void expiredFileDelete(){
		
		System.out.println("#################### Scheduler.expiredFileDelete START ####################");
		try{
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("fileLimitDay", propertyService.getInt("Globals.fileKeepDay"));
			
			//승인 후 기간 만료 신청서 조회
			List<Map<String, Object>> expiredFileList = schedulerService.getExpiredFileList(sendMap);
			
			for(int i=0; i<expiredFileList.size(); i++){
				
				Map<String, Object> expiredMap = expiredFileList.get(i);
				
				try{
					String filePath = propertyService.getString("Globals.supplyImgPath") + "/" + (String)expiredMap.get("supIdn");
					
					File dir = new File(filePath.replaceAll("\\\\", "/"));
					
					if(dir.exists()){
						boolean isSuccsess = schedulerService.deleteDirectory(dir);
						System.out.println("#################### Scheduler.expiredFileDelete succsess   #### supIdn[" + expiredMap.get("supIdn") + "] isSuccsess["+isSuccsess+"]");
					}
					
				}catch(Exception e){
					System.out.println("#################### Scheduler.expiredFileDelete error   #### supIdn[" + expiredMap.get("supIdn") + "]");
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("#################### Scheduler.expiredFileDelete E N D ####################");
	}
	
	public void fileUploadToNiis(){
		
		System.out.println("#################### Scheduler.fileUploadToNiis START ####################");
		try{
			
			String serverIp = propertyService.getString("Globals.uploadServerIp");
			int serverPort = propertyService.getInt("Globals.uploadServerPort");
			
			int smartCnt = niisSmartMapService.uploading(null);
			int applyCnt = niisSmartMapService.uploadingSupIdn(null);
			
			if(smartCnt == 0 && applyCnt == 0) {
				
				ArrayList<String> fileList = new ArrayList<String>();
				ArrayList<String> copyList = new ArrayList<String>();
				
				List<Map<String, Object>> smartList =  niisSmartMapService.smartMapfileList(null); // 업로드 할 스마트 전자지도 파일 리스트
				// 스마트 업로드
				if(smartList.size() > 0){
					
					for(Map<String, Object> smartMap : smartList){
						fileList.add((String)smartMap.get("orgFileNam"));
						copyList.add((String)smartMap.get("newFileNam"));
					}
					
					FileSendClient fileSendClient = new SmartFileSendClient(serverIp, serverPort, fileList);
					fileSendClient.setService(niisSmartMapService);
					fileSendClient.fileSend(fileList, copyList);
					
				}else{
					List<Map<String, Object>> applyList = applyService.getSupIdnList();
					//신청서 업로드
					if(applyList.size() > 0){
						
						String supIdn = (String)applyList.get(applyList.size()-1).get("supIdn");
						String rootPath = propertyService.getString("Globals.supplyImgPath") + "/" + supIdn;
						String niisIpPort = "http://" + propertyService.getString("Globals.niisIp") + ":" + propertyService.getString("Globals.niisPort");
						getWatermarkFileList(rootPath, fileList, copyList);
						
						FileSendClient fileSendClient = new NiisFileSendClient(serverIp, serverPort, supIdn);
						fileSendClient.setService(applyService);
						fileSendClient.setNiisIpPort(niisIpPort);
						fileSendClient.fileSend(fileList, copyList);
					}	
				}
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("#################### Scheduler.fileUploadToNiis E N D ####################");
	}
	
	public void getWatermarkFileList(String dirPath, ArrayList<String> fileList, ArrayList<String> copyList) {
		
		ArrayList<File> files = new ArrayList<File>();
		
		File dir = new File(dirPath);
		try{
			if(dir.exists()) {
				findSubFiles(dir, files);
			}
			
			for(File file : files){
				if(file.isFile()){
					String filePath = file.getCanonicalPath().replaceAll("\\\\", "/");
					fileList.add(filePath);
					copyList.add(filePath.replace(propertyService.getString("Globals.supplyImgPath"), propertyService.getString("Globals.uploadedImgPath")));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void findSubFiles(File parentFile, ArrayList<File> fileList) throws Exception {
		if (parentFile.isFile()) {
			fileList.add(parentFile);
		} else if (parentFile.isDirectory()) {
			File[] childFiles = parentFile.listFiles();
			for (File childFile : childFiles) {
				findSubFiles(childFile, fileList);
			}
		}
	}
	
	//@Scheduled(cron="0 0/1 * * * *")
	//@Scheduled(cron="0 0/1 * * * *")
	public void watermarkEmb() throws Exception{
		try{
			watermartComponent.runEmb();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
