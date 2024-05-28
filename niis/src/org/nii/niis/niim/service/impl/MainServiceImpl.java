package org.nii.niis.niim.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.nii.niis.niim.service.MainService;
import org.springframework.stereotype.Service;


/**
 * 메인 implements 객체 
 */
@Service("mainService")
public class MainServiceImpl implements MainService {

	/** MainDAO */
    @Resource(name="mainDAO")
    private MainDAO mainDAO;
    
    /**
	 * 공통코드조회
	 * @param param
	 * @return List
	 * @throws Exception
	 */
	@Override
	public List<?> getCommonCode(Map<String, Object> param) throws Exception {
		return mainDAO.getCommonCode(param);
	}

	@Override
	public Map<String, Object> getMainReqCnt(Map<String, Object> param) throws Exception {
		return mainDAO.getMainReqCnt(param);
	}

	@Override
	public boolean isThreadRunning(String threadName) throws Exception {
		
		try{
			Iterator<Thread> itr = Thread.getAllStackTraces().keySet().iterator();
			
			while(itr.hasNext()){
				
				Thread thread = itr.next();
				String name = thread.getName();
				
				if(name.indexOf(threadName) >= 0){
					if(Thread.State.WAITING == thread.getState()){
						return false;
					}else{
						return true;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return true;
		}
		return false;
	}
}