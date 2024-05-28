package org.nii.niis.connNiim.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nii.niis.connNiim.service.NiisSmartMapService;
import org.nii.niis.niim.util.JSONUtil;
import org.nii.niis.niim.util.PagingUtil;
import org.nii.niis.niim.util.PolicyUtil;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
@RequestMapping(value="/niim")
public class NiisSmartMapController {
	
	@Resource(name="niisSmartMapService")
	private NiisSmartMapService niisSmartMapService;
	
	@Resource(name="policyUtil")
	private PolicyUtil policyUtil;
	
	@Resource(name="txManager")
	protected DataSourceTransactionManager txManager;
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@RequestMapping(value="/smartMap/getZoneList.do")
	public ModelAndView getZoneList(@RequestBody String body, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			Map<String, Object> sendMap = JSONUtil.jsonToMap(body);
			
			//xacml 파라미터 추가
//			policyUtil.addPolicy(request, sendMap);
			
			PagingUtil.getInstance().setPageData(sendMap, modelAndView, niisSmartMapService.getZoneListCnt(sendMap));
			List<String> list = (List<String>) niisSmartMapService.getZoneList(sendMap);
			modelAndView.addObject("list", list);
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/smartMap/zoneListDetail.do")
	public ModelAndView zoneListDetail(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			
//			List<?> zoneType = null;
			String zoneCode = (String)param.get("zoneCode");
			
//			zoneType = managementService.getsubResultZoneList(zoneCode);
					
			modelAndView.addObject("zone", niisSmartMapService.getsubResultZoneList(zoneCode).get(0));
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/smartMap/metadataSend.do")
	public ModelAndView metadataSend(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String zone = (String)param.get("zoneCodes");
			zone = zone.substring(1,zone.length()-1);
			String[] array = zone.split(", ");
			
			Map<String, Object> sendMap = new HashMap<String, Object>();
			Map<String, List<Map<String, Object>>> metaMap = new LinkedHashMap<String, List<Map<String, Object>>>();
			
			sendMap.put("zoneList", array);
			metaMap = niisSmartMapService.getMetaInfo(sendMap);
			
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
			modelAndView.addObject("metaMap",metaMap);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/smartMap/uploadFile.do")
	public ModelAndView uploadFile(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
//		modelAndView.setViewName("/main/upload");
		
		try{
			String zone = (String)param.get("zoneCodes");
			zone = zone.substring(1,zone.length()-1);
			String[] array = zone.split(", ");
			String stoDrv = (String)param.get("stoDrv");
			
			Map<String, Object> sendMap = new HashMap<String, Object>();
			sendMap.put("zoneList", array);
			
			// 영상전송
			List<Map<String, Object>> airList = niisSmartMapService.getAirImgDataList(sendMap);
			List<Map<String, Object>> airLibList = niisSmartMapService.getAirLibImgDataList(sendMap);
			List<Map<String, Object>> demList = niisSmartMapService.getDemImgDataList(sendMap);
			List<Map<String, Object>> ortList = niisSmartMapService.getOrtImgDataList(sendMap);
			
			// 파일경로만 추출
			List<String> fileArray = new ArrayList<String>();
			List<Map<String, Object>> insMap = new ArrayList<Map<String, Object>>();
			
			// 트랜젝션 설정
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		    TransactionStatus txStatus = txManager.getTransaction(def);
			
			if (airList.size()>0){
				for(int i=0; i<airList.size(); i++){ //nix
					Map<String, Object> map_data = new HashMap<String, Object>();
					Map<String, Object> map_file = new HashMap<String, Object>();
//					fileArray.add((String) ortList.get(i).get("filePath") + propertiesService.getString("Globals.ortImageFormat"));
//					fileArray.add((String) ortList.get(i).get("nixPath") + ".nix");
					map_data.put("orgFileNam", airList.get(i).get("nixNlsPath") + ".nix");
					String zonceCode = (String)airList.get(i).get("zoneCode");
					String zoneCde = zonceCode.substring(zonceCode.length()-4, zonceCode.length());
					String zoneYy = (String)airList.get(i).get("zoneYy");
					String phCourse = (String)airList.get(i).get("phCourse");
					String fileNam = (String)airList.get(i).get("fileNam");
					String newDataNam = stoDrv+":/"+"AIR_DATA"+"/"+"IMAGE"+"/"+zoneYy+"/"+zoneCde+"/"+fileNam+".nix";
					map_data.put("newFileNam", newDataNam);
					insMap.add(map_data);
					
					map_file.put("orgFileNam", airList.get(i).get("nixPath") + "s.nix");
					String newFileNam = stoDrv+":/"+"AIR_FILE"+"/"+"IMAGE"+"/"+zoneYy+"/"+zoneCde+"/"+fileNam+"s.nix";
					map_file.put("newFileNam", newFileNam);
					
					insMap.add(map_file);
				}
			}
			if (airLibList.size()>0){
				for(int i=0; i<airLibList.size(); i++){
					fileArray.add((String) airLibList.get(i).get("filePath") + propertiesService.getString("Globals.airImageFormat"));
					fileArray.add((String)airLibList.get(i).get("nixPath") + ".nix");
				}
			}
			if (demList.size()>0){
				for(int i=0; i<demList.size(); i++){
					fileArray.add((String) demList.get(i).get("filePath") + propertiesService.getString("Globals.demImageFormat"));
					fileArray.add((String)demList.get(i).get("nixPath") + ".nix");
				}
			}
			if (ortList.size()>0){
				for(int i=0; i<ortList.size(); i++){ //tif
					Map<String, Object> map = new HashMap<String, Object>();
//					fileArray.add((String) ortList.get(i).get("filePath") + propertiesService.getString("Globals.ortImageFormat"));
//					fileArray.add((String) ortList.get(i).get("nixPath") + ".nix");
					map.put("orgFileNam", ortList.get(i).get("filePath") + propertiesService.getString("Globals.ortImageFormat"));
					
					String gtypDst = null;

					if(ortList.get(i).get("gtypDst").equals("51CM")){
						gtypDst = "ORT_DATA";
					}else if(ortList.get(i).get("gtypDst").equals("25CM")){
						gtypDst = "ORT_FILE";
					}
					String zonceCode = (String)ortList.get(i).get("zoneCode");
					String zoneCde = zonceCode.substring(zonceCode.length()-4, zonceCode.length());
					String map5000Num = (String)ortList.get(i).get("map5000Num");
					String zoneYy = (String)ortList.get(i).get("zoneYy");
					String fileNam = (String)ortList.get(i).get("fileNam");

					String newFileNam = stoDrv+":/"+gtypDst+"/"+"IMAGE"+"/"+zoneYy+"/"+zoneCde+"/"+fileNam+propertiesService.getString("Globals.ortImageFormat");
					map.put("newFileNam", newFileNam);
					insMap.add(map);
				}
				for(int i=0; i<ortList.size(); i++){ //nix
					Map<String, Object> map = new HashMap<String, Object>();
					if(ortList.get(i).get("gtypDst").equals("51CM")){
						map.put("orgFileNam", ortList.get(i).get("nixPath") + "s.nix");
						String gtypDst = "ORT_DATA";
						String zonceCode = (String)ortList.get(i).get("zoneCode");
						String zoneCde = zonceCode.substring(zonceCode.length()-4, zonceCode.length());
						String zoneYy = (String)ortList.get(i).get("zoneYy");
						String map5000Num = (String)ortList.get(i).get("map5000Num");
						String mapNum =  map5000Num.substring(0, 5);
						String newFileNam = stoDrv+":/"+gtypDst+"/"+"IMAGE"+"/"+zoneYy+"/"+zoneCde+"/"+mapNum+"s.nix";
						map.put("newFileNam", newFileNam);
						insMap.add(map);
					}
				}
			}
			
		    try{
				for(int i=0; i<insMap.size(); i++){
					Map <String, Object> map = new HashMap<String, Object>();
					map.put("orgFileNam", insMap.get(i).get("orgFileNam"));
					map.put("approvalCde", 8); // 8 : 업로드준비중
					map.put("newFileNam", insMap.get(i).get("newFileNam"));
					niisSmartMapService.insFilePath(map);
				}
				txManager.commit(txStatus);
		    }catch(Exception e){
				txManager.rollback(txStatus);
				System.out.println(e.getMessage());
				e.printStackTrace();
		    }
		    
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_OK);
		}catch(Exception e){
			modelAndView.addObject("rtnCd", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/smartMap/stoLocIns.do")
	public ModelAndView stoLocIns(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String zone = (String)param.get("zoneCodes");
		zone = zone.substring(1,zone.length()-1);
		String[] array = zone.split(", ");
		
		Map<String, Object> sendMap = new HashMap<String, Object>();
		sendMap.put("zoneList", array);
		
		// 영상
		List<Map<String, Object>> airList = niisSmartMapService.getAirImgDataList(sendMap);
		List<Map<String, Object>> airLibList = niisSmartMapService.getAirLibImgDataList(sendMap);
		List<Map<String, Object>> demList = niisSmartMapService.getDemImgDataList(sendMap);
		List<Map<String, Object>> ortList = niisSmartMapService.getOrtImgDataList(sendMap);
		
		modelAndView.addObject("airList", airList);
		modelAndView.addObject("airLibList", airLibList);
		modelAndView.addObject("demList", demList);
		modelAndView.addObject("ortList", ortList);
		
		return modelAndView;
	}
			
}
