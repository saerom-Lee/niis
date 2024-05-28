package org.nii.niis.niim.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.SchedulerService;
import org.springframework.stereotype.Service;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService {

	@Resource(name="schedulerDAO")
	private SchedulerDAO schedulerDAO;
	
	@Resource(name="applyDAO")
	private ApplyDAO applyDAO;
	
	
	@Override
	public List<Map<String, Object>> getExpiredList(Map<String, Object> sendMap) throws Exception {
		return schedulerDAO.getExpiredList(sendMap);
	}
	
	@Override
	public void expiredAppUpt(Map<String, Object> sendMap) throws Exception {
		applyDAO.uptDbAppAuth(sendMap);
	}

	@Override
	public List<Map<String, Object>> getExpiredFileList(Map<String, Object> sendMap) throws Exception {
		return schedulerDAO.getExpiredFileList(sendMap);
	}

	@Override
	public boolean deleteDirectory(File dir) throws Exception {
		
		if(!dir.exists()){
			return false;
		}
		
		File[] files = dir.listFiles();
		
		for(File childFile : files){
			if(childFile.isDirectory()){
				this.deleteDirectory(childFile);
			}else{
				childFile.delete();
			}
		}
		
		return dir.delete();
	}
}
