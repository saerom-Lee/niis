package org.nii.niis.niim.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.nii.niis.niim.service.ParamVO;
import org.springframework.stereotype.Component;

import egovframework.rte.fdl.property.EgovPropertyService;

@Component("policyUtil")
public class PolicyUtil {
	
	@Resource(name = "propertiesService")
	private EgovPropertyService propertiesService;
	
	
	@SuppressWarnings("unchecked")
	public void addPolicy(HttpServletRequest request, Map<String, Object> sendMap){
		
		Map<String, List<String>> policyMap = (Map<String, List<String>>)request.getAttribute("_policyInfo");
		
		Iterator<String> itr = policyMap.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			sendMap.put("_" + key + "_", policyMap.get(key));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addPolicy(HttpServletRequest request, ParamVO vo){
		
		Map<String, List<String>> policyMap = (Map<String, List<String>>)request.getAttribute("_policyInfo");
		
		Map<String, List<String>> sendMap = new HashMap<String, List<String>>();
		
		Iterator<String> itr = policyMap.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			
			sendMap.put("_" + key + "_", policyMap.get(key));
			
			if("IMG_AIR_POLICY".equals(key)){
				vo.set_IMG_AIR_POLICY_(policyMap.get(key));
			}else if("IMG_DEM_POLICY".equals(key)){
				vo.set_IMG_DEM_POLICY_(policyMap.get(key));
			}else if("IMG_ORT_POLICY".equals(key)){
				vo.set_IMG_ORT_POLICY_(policyMap.get(key));
			}else if("IMG_LID_POLICY".equals(key)){
				vo.set_IMG_LID_POLICY_(policyMap.get(key));
			}else if("IMG_NIR_POLICY".equals(key)){
				vo.set_IMG_NIR_POLICY_(policyMap.get(key));
			}else if("IMG_TDS_POLICY".equals(key)){
				vo.set_IMG_TDS_POLICY_(policyMap.get(key));
			}
		}
		
		vo.setPolicyInfo(policyMap);
	}
	
	@SuppressWarnings("unchecked")
	public void addPolicy(Map<String, Object> param, ParamVO vo){
		
		Iterator<String> itr = param.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			
			if("_IMG_AIR_POLICY_".equals(key)){
				vo.set_IMG_AIR_POLICY_((List<String>)param.get(key));
			}else if("_IMG_DEM_POLICY_".equals(key)){
				vo.set_IMG_DEM_POLICY_((List<String>)param.get(key));
			}else if("_IMG_ORT_POLICY_".equals(key)){
				vo.set_IMG_ORT_POLICY_((List<String>)param.get(key));
			}else if("_IMG_LID_POLICY_".equals(key)){
				vo.set_IMG_LID_POLICY_((List<String>)param.get(key));
			}else if("_IMG_NIR_POLICY_".equals(key)){
				vo.set_IMG_NIR_POLICY_((List<String>)param.get(key));
			}else if("_IMG_TDS_POLICY_".equals(key)){
				vo.set_IMG_TDS_POLICY_((List<String>)param.get(key));
			}
		}
		
		//vo.setPolicyInfo(param);
	}
	
	public void addPolicy(Map<String, Object> param, Map<String, Object> sendMap){
		
		Iterator<String> itr = param.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			if(key.indexOf("_") > -1 && key.indexOf("POLICY") > -1){
				sendMap.put(key, param.get(key));
			}
		}
	}
}
