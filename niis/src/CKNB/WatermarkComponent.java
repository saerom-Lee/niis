package CKNB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.nii.niis.niim.service.ApplyService;
import org.nii.niis.niim.service.MetaService;
import org.nii.niis.niim.util.CSVUtil;
import org.nii.niis.security.service.impl.SecurityDAO;
import org.nii.niis.watermark.service.WatermarkService;
import org.springframework.stereotype.Controller;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/** 
* <pre>
* @Project : niis 
* @Package : org.nii.niis.supply.web
* @FileName : SupplyConroller.java 
* @Date : 2016. 12. 19.
* @description : 영상 다운로드 controller 객체
* </pre>
*/
@Controller
public class WatermarkComponent {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	
	@Resource(name="connService")
	private ConnService connService;
	
	@Resource(name="watermarkService")
	private WatermarkService watermarkService;
	
	@Resource(name="applyService")
	private ApplyService applyService;
	
	@Resource(name="metaService")
	private MetaService metaService;
	
	@Resource(name="csvUtil")
	private CSVUtil csvUtil;
	
	@Resource(name="securityDAO")
	private SecurityDAO securityDAO;
	
	public void runEmb() throws Exception{
		
		//ModelAndView model = new ModelAndView();
		
		List<?> list =  watermarkService.selectApproval();
		
		/*Iterator iterator = list.iterator();
		
		while(iterator.hasNext()){
			String str = (String) iterator.next();
			System.out.println("str>>>>>>>>>" + str);
		}*/
		
		int num = 0;
		if(list.size()>0){
			for (Object object : list) {
			
				EgovMap map1 = (EgovMap)list.get(num);
				
				String supIdn = (String)map1.get("supIdn");
				String approvalCde = (String)map1.get("approvalCde");
				System.out.println("approvalCde>>>>>>>" + approvalCde);
				
				/*if(approvalCde.equals("10") || approvalCde.equals("11") || approvalCde.equals("12")){*/
				
				if(approvalCde.equals("14")){
					map1.put("waterMarkWeight", "1.0f");			
				}else if(approvalCde.equals("15")){
					map1.put("waterMarkWeight", "0.5f");
				}else if(approvalCde.equals("16")){
					map1.put("waterMarkWeight", "0.1f");
				}else if(approvalCde.equals("17")){
					map1.put("waterMarkWeight", "nomark");
				}
				
				
				map1.put("limitDay",propertiesService.getInt("Globals.downloadLimitDay"));
				
				
				map1.put("stoImgPath", propertiesService.getString("Globals.stoImgPath"));
				
				Map<String, Object> returnMap = watermarkService.itemList(map1);
				
				String userPath = propertiesService.getString("Globals.uploadedImgPath") + "/" + returnMap.get("supIdn");
				
				returnMap.put("CKNBLibraryPath", propertiesService.getString("Globals.CKNBLibraryPth"));
				returnMap.put("userPath",userPath);
				
				
				if(approvalCde.equals("17")){
					returnMap.put("waterMarkWeight","nomark");
				}
				
				runCKNB(returnMap);
				
				map1.put("approvalCde","2");
				applyService.uptDbApproval(map1);
								
				System.out.println("여기까지 성공=================");
			/*}else if(approvalCde.equals("13")){
				map1.put("limitDay",propertiesService.getInt("Globals.downloadLimitDay"));
								
				watermarkService.itemList(map1);
				
				map1.put("approvalCde","2");
				applyService.uptDbApproval(map1);
			}*/
			num++;		
			}		
		}else{
			System.out.println("리스트가 없습니다.");
		}
		//return model;	
	}
	
	
	@SuppressWarnings("unchecked")
	public void runCKNB(Map<String, Object> sendMap) throws Exception {
		
		System.out.println("****************************** WatermarkComponent.runCKNB START [" + sendMap.get("supIdn") + "] ******************************");
		
		long startTime = System.currentTimeMillis();
		
		String supIdn = (String)sendMap.get("supIdn");
		String userPath = (null == sendMap.get("userPath")? "D:/TEST" : (String)sendMap.get("userPath"));
		String waterMarkWeight = (String)sendMap.get("waterMarkWeight");
		System.out.println("sendMap>>>>>>>>>>>>>" + sendMap);
		ArrayList<String> filePath  = (ArrayList<String>)sendMap.get("filePath");
		//ArrayList<String> fileType = (ArrayList<String>)sendMap.get("fileType");
		ArrayList<String> markIndex = (ArrayList<String>)sendMap.get("markIndex");
		//ArrayList<String> metaPath  = (ArrayList<String>)sendMap.get("metaPath");
		
		//프로그래스 정보 갱신
		//setProgressInfo(supIdn, "다운로드 준비중...", String.valueOf(filePath.size()), "0");
		System.out.println("waterMarkWeight>>>>>>>>>" + waterMarkWeight);
		//워터마크 미삽입
		if(null == waterMarkWeight || "".equals(waterMarkWeight) || "nomark".equals(waterMarkWeight)){
			
			for(int i=0; i<filePath.size(); i++){
				
				
				long fileStartTime = System.currentTimeMillis();
						
				String strOpenPath = filePath.get(i).replace("\\", "/");
				String SatePath = strOpenPath.replaceAll(propertiesService.getString("Globals.stoImgPath"),"");
				String strSavePath = userPath + SatePath; 
				
				
				//setProgressInfo(supIdn, strSavePath.substring(strSavePath.lastIndexOf("/") + 1), String.valueOf(filePath.size()), String.valueOf(i));
				
				String strSaveFolderPath = strSavePath.substring(0, strSavePath.lastIndexOf("/"));
				File saveFile = new File(strSaveFolderPath);
				
		        if(!saveFile.exists()){
		        	saveFile.mkdirs();
		        }
		        System.out.println((i+1) + " / " + filePath.size() + " " + strOpenPath + " Download Start");
				File openFile = new File(strOpenPath);
				
				FileInputStream fis = null;
				FileOutputStream fos = null;
				FileChannel fcin = null;
				FileChannel fcout = null;
				
				try{
					fis = new FileInputStream(openFile);
					fos = new FileOutputStream(strSavePath);
					
					fcin = fis.getChannel();
					fcout = fos.getChannel();
					
					long size = fcin.size();
					fcin.transferTo(0, size, fcout);
					
					long fileEndTime = System.currentTimeMillis();
					long fileDownTime = fileEndTime - fileStartTime;
					System.out.println((i+1) + " / " + filePath.size() + " Download Time[" + fileDownTime + "(ms)]");
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(null != fcout){ try{ fcout.close(); }catch(Exception e){} }
					if(null != fcin){ try{ fcin.close(); }catch(Exception e){} }
					if(null != fos){ try{ fos.close(); }catch(Exception e){} }
					if(null != fis){ try{ fis.close(); }catch(Exception e){} }
				}
				
				//setProgressInfo(supIdn, strSavePath.substring(strSavePath.lastIndexOf("/") + 1), String.valueOf(filePath.size()), String.valueOf(i + 1));
			}
		}
		//워터마크 삽입
		else if(!(null == waterMarkWeight || "".equals(waterMarkWeight) || "nomark".equals(waterMarkWeight))) {
		
			String CKNBLibraryPath = (String)sendMap.get("CKNBLibraryPath");
			
			//워터마크 강도
			float waterMark = 0;
			try{
				waterMark = Float.parseFloat(waterMarkWeight);
			}catch(Exception e){
				
			}
			
			for(int i=0; i<filePath.size(); i++){
				
				long fileStartTime = System.currentTimeMillis();
				
				String strOpenPath = filePath.get(i).replace("\\", "/");
				String SatePath = strOpenPath.replaceAll(propertiesService.getString("Globals.stoImgPath"),"");
				String strSavePath = userPath + SatePath; 
				//strOpenPath.substring(5);
				String strWatermark = markIndex.get(i);
				System.out.println("strOpenPath>>>>>>>>>>>>>>" + strOpenPath);
				System.out.println("strSavePath>>>>>>>>>>>>>>" + strSavePath);
				System.out.println("strWatermark>>>>>>>>>>" + strWatermark);
				System.out.println("waterMark>>>>>>>>>>" + waterMark);
				
				//setProgressInfo(supIdn, strSavePath.substring(strSavePath.lastIndexOf("/") + 1), String.valueOf(filePath.size()), String.valueOf(i));
				
				String strSaveFolderPath = strSavePath.substring(0, strSavePath.lastIndexOf("/"));
				System.out.println("strSaveFolderPath>>>>>>>" + strSaveFolderPath);
				File file = new File(strSaveFolderPath);
				
		        if(!file.exists()){
		            file.mkdirs();
		        }
				
				int EmbRet = 0;
				
				try{
					EmbRet = CKNBClass.LoadLibrary(CKNBLibraryPath);
					//EmbRet = CKNBClass.LoadLibrary("D:/국토정보지리원_워터마크_설치파일/201113/SDK/NGII/bin/CKNB/");
					
					try{
						EmbRet = CKNBClass.Embedding(strOpenPath, strSavePath, strWatermark, waterMark);
						System.out.println("Embedding Result = " + String.format("0x%08x", EmbRet));
						
						long fileEndTime = System.currentTimeMillis();
						long fileDownTime = fileEndTime - fileStartTime;
						System.out.println((i+1) + " / " + filePath.size() + " Download Time[" + fileDownTime + "(ms)]");
					}catch (Exception ex){
						System.out.println(ex.getMessage() + "Embedding Error");
					}
				}catch (Exception ex){
					System.out.println(ex.getMessage() + "LoadLibrary Error");
				}
				
				//setProgressInfo(supIdn, strSavePath.substring(strSavePath.lastIndexOf("/") + 1), String.valueOf(filePath.size()), String.valueOf(i + 1));
			}
		}
		
		
		//메타데이터 생성
		if(null != sendMap.get("metaCheck") && "Y".equals(sendMap.get("metaCheck"))){
			
			//setProgressInfo(supIdn, "메타데이터 생성중입니다.", String.valueOf(filePath.size()), String.valueOf(filePath.size()));
			
			//세션에 정보설정 들어갈 부분 start 
			
			//세션에 정보설정 들어갈 부분 end
			long fileStartTime = System.currentTimeMillis();
			System.out.println("MetaData Download Start");
			try{
				metaExport(propertiesService.getString("Globals.airImageCode"), sendMap);
				metaExport(propertiesService.getString("Globals.demImageCode"), sendMap);
				metaExport(propertiesService.getString("Globals.ortImageCode"), sendMap);
				metaExport(propertiesService.getString("Globals.lidImageCode"), sendMap);
				metaExport(propertiesService.getString("Globals.nirImageCode"), sendMap);
				//metaExport(propertiesService.getString("Globals.tdsImageCode"), sendMap);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			long fileEndTime = System.currentTimeMillis();
			long fileDownTime = fileEndTime - fileStartTime;
			System.out.println("MetaData Download Time[" + fileDownTime + "(ms)]");
		}
		
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		
		//프로그래스 정보 제거
		//remProgressInfo(supIdn);
		
		System.out.println("****************************** Total running time[" + duration + "(ms)]");		
		System.out.println("****************************** WatermarkComponent.runCKNB E N D [" + supIdn + "] ******************************");
	}
	
	public void metaExport(String imageCde, Map<String, Object> sendMap) throws Exception {
		String userPath = "D:/METADATA/"+sendMap.get("supIdn");
		
		/**
		 * 테이블별 데이터 조회
		 * Map<테이블명, 조회데이터> 형태 리턴
		 */
		Map<String, List<Map<String, Object>>> metaMap = new HashMap<String, List<Map<String, Object>>>();
		
		if(imageCde.equals(propertiesService.getString("Globals.airImageCode"))){	//항공사진
			metaMap = metaService.getAirMetaInfo(sendMap);
		}else if(imageCde.equals(propertiesService.getString("Globals.demImageCode"))){		//DEM
			metaMap = metaService.getDemMetaInfo(sendMap);
		}else if(imageCde.equals(propertiesService.getString("Globals.ortImageCode"))){		//정사영상
			metaMap = metaService.getOrtMetaInfo(sendMap);
		}else if(imageCde.equals(propertiesService.getString("Globals.lidImageCode"))){		//LiDAR
			metaMap = metaService.getLidMetaInfo(sendMap);
		}else if(imageCde.equals(propertiesService.getString("Globals.nirImageCode"))){		//NIR
			metaMap = metaService.getNirMetaInfo(sendMap);
		}else if(imageCde.equals(propertiesService.getString("Globals.tdsImageCode"))){		//3차원
			metaMap = metaService.getTdsMetaInfo(sendMap);
		}
		
		System.out.println(metaMap.toString());
		
		//테이블명
		Iterator<String> itr = metaMap.keySet().iterator();
		
		//while 한번에 테이블 하나
		while(itr.hasNext()){
			
			//테이블명
			String fileName = itr.next();
			
			//테이블에서 조회된 리스트
			List<Map<String, Object>> dataList = metaMap.get(fileName);
			
			//csv 헤더로 내보낼 컬러명 세팅
			String[] col = null;
			
			//csv 내보낼 데이터를 세팅
			List<String[]> csvList = new ArrayList<String[]>();
			
			for(int i=0; i<dataList.size(); i++){
				
				Map<String, Object> rowData = dataList.get(i);
				
				if(i == 0){
					//헤더값 세팅
					col = rowData.keySet().toArray(new String[0]);
					csvList.add(col);
				}
				
				/** java.lang.ArrayStoreException: java.math.BigDecimal
				String[] row = rowData.values().toArray(new String[0]);
				*/
				
				String[] row = new String[col.length];
				for(int j=0; j<col.length; j++){
					row[j] = nvl(rowData.get(col[j]));
				}
				
				csvList.add(row);
				
				
				/* 폴더 구조 변경 검증 로직 start */
				String basePath = "";
				String thisVal = "";
				String nextVal = "";
				
				//항공사진
				if(imageCde.equals(propertiesService.getString("Globals.airImageCode"))){
					
					basePath = propertiesService.getString("Globals.airMetaPath");
					
					thisVal = rowData.get("ZONE_CODE") + "/";
					
					//마지막 로우가 아닐때
					if((i+1) < dataList.size()){
						nextVal = dataList.get(i+1).get("ZONE_CODE") + "/";
					}
				}
				//수치표고
				else if(imageCde.equals(propertiesService.getString("Globals.demImageCode"))){
					
					basePath = propertiesService.getString("Globals.demMetaPath");
					
					thisVal = String.valueOf(rowData.get("ZONE_CODE")).substring(0, 4) + "/"
							+ getDemScale(rowData.get("MAP5000_NUM")) + "/"
							+ String.valueOf(rowData.get("MAP5000_NUM")).substring(0, 5) + "/";
				
					//마지막 로우가 아닐때
					if((i+1) < dataList.size()){
						nextVal = String.valueOf(dataList.get(i+1).get("ZONE_CODE")).substring(0, 4) + "/"
								+ getDemScale(dataList.get(i+1).get("MAP5000_NUM")) + "/"
								+ String.valueOf(dataList.get(i+1).get("MAP5000_NUM")).substring(0, 5) + "/";
					}
				}
				//정사영상
				else if(imageCde.equals(propertiesService.getString("Globals.ortImageCode"))){
					
					basePath = propertiesService.getString("Globals.ortMetaPath");
					
					thisVal = String.valueOf(rowData.get("ZONE_CODE")).substring(0, 4) + "/"
							+ String.valueOf(rowData.get("MAP5000_NUM")).substring(0, 5) + "/";
				
					//마지막 로우가 아닐때
					if((i+1) < dataList.size()){
						nextVal = String.valueOf(dataList.get(i+1).get("ZONE_CODE")).substring(0, 4) + "/"
								+ String.valueOf(dataList.get(i+1).get("MAP5000_NUM")).substring(0, 5) + "/";
					}
				}
				//라이다
				else if(imageCde.equals(propertiesService.getString("Globals.lidImageCode"))){
					
					basePath = propertiesService.getString("Globals.lidMetaPath");
					
					thisVal = String.valueOf(rowData.get("ZONE_CODE")).substring(0, 4) + "/"
							+ getDemScale(rowData.get("MAP5000_NUM")) + "/"
							+ String.valueOf(rowData.get("MAP5000_NUM")).substring(0, 5) + "/";
				
					//마지막 로우가 아닐때
					if((i+1) < dataList.size()){
						nextVal = String.valueOf(dataList.get(i+1).get("ZONE_CODE")).substring(0, 4) + "/"
								+ getDemScale(dataList.get(i+1).get("MAP5000_NUM")) + "/"
								+ String.valueOf(dataList.get(i+1).get("MAP5000_NUM")).substring(0, 5) + "/";
					}
				}
				//NIR
				else if(imageCde.equals(propertiesService.getString("Globals.nirImageCode"))){
					
					basePath = propertiesService.getString("Globals.nirMetaPath");
					
					thisVal = rowData.get("ZONE_CODE") + "/";
					
					//마지막 로우가 아닐때
					if((i+1) < dataList.size()){
						nextVal = dataList.get(i+1).get("ZONE_CODE") + "/";
					}
				}
				//3차원객체
				else if(imageCde.equals(propertiesService.getString("Globals.tdsImageCode"))){
					
					basePath = propertiesService.getString("Globals.tdsMetaPath");
				}
				
				//다음 로우의 사업지구 코드가 다르면 임포트
				if(!nextVal.equals(thisVal)){
					
					String csvFilePath = userPath + basePath + "/" + thisVal + fileName + ".csv";
					System.out.println("csvFilePath>>>>>>>>>>>>" + csvFilePath);
					try{
					csvUtil.writeCSV(csvFilePath, csvList);
					
					//리스트 초기화 및 헤더값 세팅
					csvList.clear();
					csvList.add(col);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				/* 폴더 구조 변경 검증 로직 end */
			}
		}
		
	}

	public static String detecting(String filePath) throws Exception{

		 String detMark = "";
		    try
		    {	
		    	
		    	CKNBClass.LoadLibrary("C:/NII/");
		    	detMark = CKNBClass.Detecting(filePath); 
		    	
	           if (detMark != null) {
	               System.out.println("Detecting Success = " + detMark);
	             } else {
	               System.out.println("Detecting Fail");
	             }

		         return detMark;
		       }
		       catch (Exception ex)
		       {
		         System.out.println(ex.getMessage() + "LoadLibrary Error");
		       }
		    return detMark;
	}

	public String nvl(Object obj){
		
		String str = "";
		
		if(null != obj){
			str = String.valueOf(obj);
		}
		return str;
	}
	
	
	public String getDemScale(Object obj){
		
		String scale = "";
		
		if(obj instanceof String){
			String map5000Num = (String)obj;
			
			//LENGTH(A.MAP5000_NUM), 5, '50K', 6, '25K', 7, '10K', 8, '5K'
			if(map5000Num.length() == 5){
				scale = "50K";
			}else if(map5000Num.length() == 6){
				scale = "25K";
			}else if(map5000Num.length() == 7){
				scale = "10K";
			}else if(map5000Num.length() == 8){
				scale = "5K";
			}
		}
		return scale;
	}


}
