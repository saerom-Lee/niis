package org.nii.niis.search.service.impl;

import java.util.Iterator;
import java.util.Map;

import org.nii.niis.search.service.SearchService;
import org.springframework.stereotype.Service;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.search.service.impl
* @FileName : SearchServiceImpl.java 
* @Date : 2016. 12. 19.
* @description : 원본DB 검색 implement 객체
* </pre>
*/
@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Override
	public String getImgSrchCondToString(Map<String, Object> param) throws Exception {
		
		String sendParam = "";
		
		if (param != null){
			String imgType = "";
			String radius = "";
			
			if (param.containsKey("imgType")){
				imgType = (String)param.get("imgType");
				sendParam += "&imgType=" + imgType;
			}
			
			if (param.containsKey("radius")){
				radius = (String)param.get("radius");
				sendParam += "&radius=" + radius;
			}
			
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){ 
				String key = (String)iter.next(); 
				
				if (key.equals("sYear")){
					sendParam += "&sYear=" + (String)param.get(key);
				}
				if (key.equals("eYear")){
					sendParam += "&eYear=" + (String)param.get(key);
				}
				if (key.equals("zoneCode")){
					sendParam += "&zoneCode=" + (String)param.get(key);
				}
				if (key.equals("sigunguCode")){
					sendParam += "&sigunguCode=" + (String)param.get(key);
				}
				if (key.equals("bounds")){
					sendParam += "&bounds=" + (String)param.get(key);
				}
								
				//영상종류별 파라미터 세팅
				//항공사진, NIR
				if("0".equals(imgType) || "4".equals(imgType)){
					if (key.equals("phCourse")){
						sendParam += "&phCourse=" + (String)param.get(key);
					}
					if (key.equals("securityCde")){
						sendParam += "&securityCde=" + (String)param.get(key);
					}
				}
				//정사영상
				else if("1".equals(imgType)){
					if (key.equals("gtypDst")){
						sendParam += "&gtypDst=" + (String)param.get(key);
					}
					if (key.equals("securityCde")){
						sendParam += "&securityCde=" + (String)param.get(key);
					}
					if (key.equals("mapNum")){
						sendParam += "&mapNum=" + (String)param.get(key);
					}
				}
				//수치표고
				else if("2".equals(imgType)){
					if (key.equals("gridInt")){
						sendParam += "&gridInt=" + (String)param.get(key);
					}
					if (key.equals("securityCde")){
						sendParam += "&securityCde=" + (String)param.get(key);
					}
					if (key.equals("mapNum")){
						sendParam += "&mapNum=" + (String)param.get(key);
					}
				}
				//라이다
				else if("3".equals(imgType)){
					if (key.equals("securityCde")){
						sendParam += "&securityCde=" + (String)param.get(key);
					}
				}
				//3차원 객체
				else if("5".equals(imgType)){
					if (key.equals("utl3dMpn")){
						sendParam += "&utl3dMpn=" + (String)param.get(key);
					}
				}
			}
		}
		return sendParam;
	}

}
