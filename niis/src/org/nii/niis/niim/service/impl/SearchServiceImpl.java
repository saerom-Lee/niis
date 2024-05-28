package org.nii.niis.niim.service.impl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.nii.niis.niim.service.MetaService;
import org.nii.niis.niim.service.ParamVO;
import org.nii.niis.niim.service.SearchService;
import org.nii.niis.niim.util.PolicyUtil;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.util.GeometricShapeFactory;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 통합검색 implements 객체 
 */
@Service("niimSearchService")
public class SearchServiceImpl implements SearchService {
	
	@Resource(name="applyDAO")
    private ApplyDAO applyDAO;
	
	@Resource(name="searchDAO")
    private SearchDAO searchDAO;
    
    /** EgovPropertyService */
    @Resource(name="propertiesService")
    private EgovPropertyService propertyService;
    
    @Resource(name="metaService")
    private MetaService metaService;
    
    @Resource(name="policyUtil")
    private PolicyUtil policyUtil;
    
    
	@Override
	public List<?> getZoneList(ParamVO param) {
		// TODO Auto-generated method stub
		return searchDAO.selectZoneList(param);
	}

	@Override
	public List<?> getAirImgList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectAirImgList(param);
	}
	@Override
	public List<?> getAirImgFolderList(ParamVO param) throws Exception {
		return searchDAO.selectAirImgFolderList(param);
	}
	@Override
	public List<?> getAirImgDataList(ParamVO param) throws Exception {
		return searchDAO.selectAirImgDataList(param);
	}
	
	@Override
	public List<?> getAirLibImgFolderList(ParamVO param) throws Exception {
		return searchDAO.selectAirLibImgFolderList(param);
	}
	@Override
	public List<?> getAirLibImgDataList(ParamVO param) throws Exception {
		return searchDAO.selectAirLibImgDataList(param);
	}

	
	@Override
	public List<?> getNirImgList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectNirImgList(param);
	}
	@Override
	public List<?> getNirImgFolderList(ParamVO param) throws Exception {
		return searchDAO.selectNirImgFolderList(param);
	}
	@Override
	public List<?> getNirImgDataList(ParamVO param) throws Exception {
		return searchDAO.selectNirImgDataList(param);
	}
	
	
	@Override
	public List<?> getAirImgYear(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectAirImgYear(param);
	}

	@Override
	public List<?> getAirImgCourse(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectAirImgCourse(param);
	}

	
	@Override
	public List<?> getOrtImgList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectOrtImgList(param);
	}
	@Override
	public List<?> getOrtImgFolderList(ParamVO param) throws Exception {
		return searchDAO.selectOrtImgFolderList(param);
	}
	@Override
	public List<?> getOrtImgDataList(ParamVO param) throws Exception {
		return searchDAO.selectOrtImgDataList(param);
	}
	

	@Override
	public List<?> getDemImgList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectDemImgList(param);
	}
	@Override
	public List<?> getDemImgFolderList(ParamVO param) throws Exception {
		return searchDAO.selectDemImgFolderList(param);
	}
	@Override
	public List<?> getDemImgDataList(ParamVO param) throws Exception {
		return searchDAO.selectDemImgDataList(param);
	}
	
	
	@Override
	public List<?> getSuchiList(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectSuchiList(param);
	}
	
	
	@Override
	public List<?> getLidImgList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectLidImgList(param);
	}
	@Override
	public List<?> getLidImgFolderList(ParamVO param) throws Exception {
		return searchDAO.selectLidImgFolderList(param);
	}
	@Override
	public List<?> getLidImgDataList(ParamVO param) throws Exception {
		return searchDAO.selectLidImgDataList(param);
	}
	
	
	@Override
	public List<?> getTdsImgList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectTdsImgList(param);
	}
	@Override
	public List<?> getTdsImgFolderList(ParamVO param) throws Exception {
		return searchDAO.selectTdsImgFolderList(param);
	}
	@Override
	public List<?> getTdsImgDataList(ParamVO param) throws Exception {
		return searchDAO.selectTdsImgDataList(param);
	}
	
	
	@Override
	public List<?> getNowestAirZoneAreaGeometry(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectNowestAirZoneAreaGeometry(param);
	}

	@Override
	public List<?> getNowestAirZone(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectNowestAirZone(param);
	}
	
	@Override
	public List<?> getZipCodeList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectZipCodeList(param);
	}
	
	@Override
	public List<?> getJusoList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectJusoList(param);
	}
	
	@Override
	public List<?> getBuldNoList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectBuldNoList(param);
	}
	
	@Override
	public List<?> getPOIList(ParamVO param) throws Exception {
		// TODO Auto-generated method stub
		return searchDAO.selectPOIList(param);
	}
	
	/**
	 * 통합검색 - 신청서목록
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<?> getApplicationList() throws Exception {
		return searchDAO.selectApplicationList();
	}
	
	/**
	 * 통합검색 - 워터마크 인덱스 생성
	 * @param idxParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object setcreateIndex(Map<String, Object> idxParam) throws Exception {
		Object obj = "";
		Iterator<String> itr = idxParam.keySet().iterator();
		while(itr.hasNext()){
			String key = itr.next();
			System.out.println(key + "[" + idxParam.get(key) + "]");
		}
		if(!idxParam.containsKey("waterMarkWeight") || "".equals(idxParam.get("waterMarkWeight")) || "nomark".equals(idxParam.get("waterMarkWeight"))){
			obj = null;
		}else{
			obj = searchDAO.insertIndex(idxParam);
		}
		return obj;
	}
	
	/**
	 * 통합검색 - 신청서 생성
	 * @param usrParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public Object setSupIdn(Map<String, Object> usrParam) throws Exception {
		return searchDAO.insertSupIdn(usrParam);
	}
	
	/**
	 * 통합검색 - 1:50000 도엽 검색
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<?> getIndexMapName() throws Exception {
		return searchDAO.selectIndexMapName();
	}
	
	/**
	 * 통합검색 - 영상 사업지구 촬영년도 목록
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<?> getYearList() throws Exception {
		return searchDAO.selectYearList();
	}
	
	/**
	 * 통합검색 - 수치지형도 제작년도 목록
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<?> getMapYearList() throws Exception {
		return searchDAO.selectMapYearList();
	}

	/**
	 * 통합검색 - 신청 내역 등록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> regSupplyItem(Map<String, Object> param) throws Exception {
		
		String supIdn = (String)param.get("supIdn");
		ArrayList<String> fileType = (ArrayList)param.get("fileType");
		ArrayList<String> zoneCode = (ArrayList)param.get("zoneCode");
		ArrayList<String> pathOne  = (ArrayList)param.get("pathOne");
		ArrayList<String> pathTwo  = (ArrayList)param.get("pathTwo");
		ArrayList<String> fileName = (ArrayList)param.get("fileName");
		
		ArrayList<String> imageCde  = new ArrayList<String>();
		ArrayList<String> markIndex = new ArrayList<String>();
		ArrayList<String> filePath  = new ArrayList<String>();
		
		String waterMarkWeight = (String)param.get("waterMarkWeight");
		
		//인덱스 선택안됬을떄
		if (null == supIdn || supIdn.equals("")){
			//supIdn = (String) this.setSupIdn(param);
			throw new Exception("SUP_IDN is not defined!!");
		}

		String today = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		param.put("supDate",today);
		//신청서 수정
		applyDAO.uptDbApp(param);
		
		//화면에서 로딩되지 않은 데이터 처리
		//기존 검색조건 세팅
		String radius 		= "";
		String useAt 		= "";
		
		if (param.containsKey("radius")){
			radius = (String)param.get("radius");
		}
		if (param.containsKey("useAt")){
			useAt = (String)param.get("useAt");
		}
		
		//fileType size가 변경될 수 있으므로 선언 후 진입
		int k = fileType.size();
		
		for(int i=0; i<k; i++){
			if("loadedYet".equals(fileName.get(i))){
				
				//이전 검색 조건 조회
				ParamVO vo = getBeforeSearchCondtion(param);
				//파일이 존재하는 영상만 추출
				vo.setFileExt("O");
				
				Map<String, ArrayList<String>> nodeDataMap = this.getUnloadedData(vo, radius, fileType.get(i), zoneCode.get(i), pathOne.get(i), pathTwo.get(i));
				
				fileType.addAll(nodeDataMap.get("fileType"));
				zoneCode.addAll(nodeDataMap.get("zoneCode"));
				pathOne.addAll(nodeDataMap.get("pathOne"));
				pathTwo.addAll(nodeDataMap.get("pathTwo"));
				fileName.addAll(nodeDataMap.get("fileName"));
				
				//상위 노드 데이터 제거
				fileType.remove(i);
				zoneCode.remove(i);
				pathOne.remove(i);
				pathTwo.remove(i);
				fileName.remove(i--);
				k--;
			}
		}
		
		Map<String, Object> sendMap = new HashMap<String, Object>();
		
		for(int i=0; i<fileType.size(); i++){
			
			sendMap.put("supIdn", supIdn);
			sendMap.put("fileType", fileType.get(i));
			sendMap.put("imageCde", fileType.get(i));
			sendMap.put("zoneCode", zoneCode.get(i));
			sendMap.put("pathOne", pathOne.get(i));
			sendMap.put("pathTwo", pathTwo.get(i));
			sendMap.put("fileName", fileName.get(i));
			sendMap.put("waterMarkWeight", waterMarkWeight);
			
			if(fileType.get(i).equals(propertyService.getString("Globals.airImageCode"))){	//항공사진
				sendMap = this.setAirSupplyItem(sendMap);
			}else if(fileType.get(i).equals(propertyService.getString("Globals.airLibImageCode"))){	//항공사진(해방전후)
				sendMap = this.setAirLibSupplyItem(sendMap);
			}else if(fileType.get(i).equals(propertyService.getString("Globals.demImageCode"))){		//DEM
				sendMap = this.setDemSupplyItem(sendMap);
			}else if(fileType.get(i).equals(propertyService.getString("Globals.ortImageCode"))){		//정사영상
				sendMap = this.setOrtSupplyItem(sendMap);
			}else if(fileType.get(i).equals(propertyService.getString("Globals.lidImageCode"))){		//LiDAR
				sendMap = this.setLidSupplyItem(sendMap);
			}else if(fileType.get(i).equals(propertyService.getString("Globals.nirImageCode"))){		//NIR
				sendMap = this.setNirSupplyItem(sendMap);
			}else if(fileType.get(i).equals(propertyService.getString("Globals.tdsImageCode"))){		//3차원
				sendMap = this.setTdsSupplyItem(sendMap);
			}
			
			imageCde.add((String)fileType.get(i));
			markIndex.add(((null == sendMap.get("markIndex")) ? "nomark" : (String)sendMap.get("markIndex")));
			filePath.add((String)sendMap.get("filePath"));
			
			//3차원객체의 경우 2가지 형태로 반환
			if(fileType.get(i).equals(propertyService.getString("Globals.tdsImageCode"))){
				imageCde.add((String)fileType.get(i));
				markIndex.add(((null == sendMap.get("markIndex")) ? "nomark" : (String)sendMap.get("markIndex_2")));
				filePath.add((String)sendMap.get("filePath_2"));
			}
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("supIdn"		, supIdn);
		returnMap.put("markIndex"	, markIndex);
		returnMap.put("filePath"	, filePath);
		returnMap.put("fileType"	, imageCde);
		returnMap.put("metaCheck"   , param.get("metaCheck"));
		returnMap.put("waterMarkWeight", sendMap.get("waterMarkWeight"));
		
		return returnMap;
	}
	
	@SuppressWarnings("unchecked")
	public void createXML(Map<String, Object> param) throws Exception{
		
		try {
			ArrayList<String> markIndex = (ArrayList<String>)param.get("markIndex");
			ArrayList<String> filePath = (ArrayList<String>)param.get("filePath");
			ArrayList<String> fileType = (ArrayList<String>)param.get("fileType");
			ArrayList<String> metaPath = (ArrayList<String>)param.get("metaPath");
			String waterMarkWeight = (String)param.get("waterMarkWeight");
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = null;
			try {
				docBuilder = docFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("imglist");
			doc.appendChild(rootElement);
			
			Element image = doc.createElement("image");
			rootElement.appendChild(image);
			
			for (int i = 0; i < markIndex.size(); i++) {

				Element staff = doc.createElement("file");
				image.appendChild(staff);
				staff.setAttribute("ssn", Integer.toString(i));

				Element index = doc.createElement("index");
				index.appendChild(doc.createTextNode(markIndex.get(i)));
				staff.appendChild(index);

				Element filename = doc.createElement("filename");
				filename.appendChild(doc.createTextNode(filePath.get(i)));
				staff.appendChild(filename);
				
				Element weight = doc.createElement("weight");
				weight.appendChild(doc.createTextNode(waterMarkWeight));
				staff.appendChild(weight);
				
				Element filetype = doc.createElement("filetype");
				filetype.appendChild(doc.createTextNode(fileType.get(i)));
				staff.appendChild(filetype);
				
			}
			
			if (null != metaPath){
				Element meta = doc.createElement("meta");
				rootElement.appendChild(meta);
			
				for (int i = 0; i < metaPath.size(); i++) {
						
					Element staff = doc.createElement("file");
					meta.appendChild(staff);
					staff.setAttribute("ssn", Integer.toString(i));
					
					Element filename = doc.createElement("filename");
					filename.appendChild(doc.createTextNode(metaPath.get(i)));
					staff.appendChild(filename);
				}
			}
			
			StringWriter sw = new StringWriter();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = null;

			try {
				transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");

				DOMSource source = new DOMSource(doc);
				StreamResult result;
				try {
					result = new StreamResult(new FileOutputStream(new File("C:\\nii\\embedding.xml")));
					try {
						transformer.transform(source, result);
					} catch (TransformerException e) {
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				System.out.println("File saved!");

			} catch (TransformerConfigurationException e1) {
				e1.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 통합검색 - 항공사진 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> setAirSupplyItem(Map<String, Object> sendMap) throws Exception {
		
		String supIdn   = (String)sendMap.get("supIdn");
		String fileType = (String)sendMap.get("fileType");
		String zoneCode = (String)sendMap.get("zoneCode");
		String pathOne  = (String)sendMap.get("pathOne");
		String pathTwo  = (String)sendMap.get("pathTwo");
		String fileName = (String)sendMap.get("fileName");
		
		Map<String, Object> airMap = new HashMap<String, Object>();
		
		airMap.put("supIdn"		, supIdn);
		airMap.put("imageCde"	, fileType);
		airMap.put("zoneCode"	, zoneCode);
		airMap.put("phCourse"	, pathOne);
		airMap.put("phNum"		, pathTwo);		
		airMap.put("fileName"	, fileName);
		//airMap.put("filePath"	, "D:\\"+"data"+"\\"+"air"+"\\"+"air_file"+"\\"+"image"+"\\"+zoneCode+"\\"+pathOne+"\\"+pathTwo+"\\"+fileName+".tif");
//		airMap.put("filePath"	, propertyService.getString("Globals.OrtDrive")+"data"+"\\"+"air"+"\\"+"air_file"+"\\"+"image"+"\\"+zoneCode.substring(0,4)+"\\"+zoneCode.substring(7)+"\\"+pathOne+"\\"+fileName+".tif");
		airMap.put("filePath"	, fileName);
		if(sendMap.get("waterMarkWeight").equals("nomark")){
			airMap.put("markIndex"	,"");
		}else{
			airMap.put("markIndex"	, this.setcreateIndex(sendMap));
		}
		airMap.put("waterMarkWeight", sendMap.get("waterMarkWeight"));
		searchDAO.insertAirSupplyItem(airMap);
		return airMap;
	}
	
	/**
	 * 통합검색 - 항공사진(해방전후) 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> setAirLibSupplyItem(Map<String, Object> sendMap) throws Exception {
		
		String supIdn   = (String)sendMap.get("supIdn");
		String fileType = (String)sendMap.get("fileType");
		String zoneCode = (String)sendMap.get("zoneCode");
		String pathOne  = (String)sendMap.get("pathOne");
		String pathTwo  = (String)sendMap.get("pathTwo");
		String fileName = (String)sendMap.get("fileName");
		
		Map<String, Object> airMap = new HashMap<String, Object>();
		
		airMap.put("supIdn"		, supIdn);
		airMap.put("imageCde"	, fileType);
		airMap.put("zoneCode"	, zoneCode);
		airMap.put("phCourse"	, pathOne);
		airMap.put("phNum"		, pathTwo);		
		airMap.put("fileName"	, fileName);
		//airMap.put("filePath"	, "D:\\"+"data"+"\\"+"air"+"\\"+"air_file"+"\\"+"image"+"\\"+zoneCode+"\\"+pathOne+"\\"+pathTwo+"\\"+fileName+".tif");
//		airMap.put("filePath"	, propertyService.getString("Globals.OrtDrive")+"data"+"\\"+"air"+"\\"+"air_file"+"\\"+"image"+"\\"+zoneCode.substring(0,4)+"\\"+zoneCode.substring(7)+"\\"+pathOne+"\\"+fileName+".tif");
		airMap.put("filePath"	, fileName);
		if(sendMap.get("waterMarkWeight").equals("nomark")){
			airMap.put("markIndex"	,"");
		}else{
			airMap.put("markIndex"	, this.setcreateIndex(sendMap));
		}
		searchDAO.insertAirSupplyItem(airMap);
		return airMap;
	}
	
	/**
	 * 통합검색 - 수치표고 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> setDemSupplyItem(Map<String, Object> sendMap) throws Exception {
		
		String supIdn   = (String)sendMap.get("supIdn");
		String fileType = (String)sendMap.get("fileType");
		String zoneCode = (String)sendMap.get("zoneCode");
		String pathOne  = (String)sendMap.get("pathOne");
		String pathTwo  = (String)sendMap.get("pathTwo");
		String fileName = (String)sendMap.get("fileName");
		
		Map<String, Object> demMap = new HashMap<String, Object>();
		
		demMap.put("supIdn"		, supIdn);
		demMap.put("imageCde"	, fileType);
		demMap.put("zoneCode"	, zoneCode);
		demMap.put("map5000Num"	, pathOne);
		demMap.put("gridInt"	, pathTwo);
		demMap.put("fileName"	, fileName);
//		demMap.put("filePath"	, propertyService.getString("Globals.OrtDrive")+"data"+"\\"+"dem"+"\\"+"dem_file"+"\\"+"sri"+"\\"+zoneCode.substring(0,4)+"\\"+zoneCode.substring(7)+"\\"+pathOne+"\\"+fileName+".tif");
		demMap.put("filePath"	, fileName);
		demMap.put("markIndex"	, "nomark");
		demMap.put("waterMarkWeight", "nomark");
		
		searchDAO.insertDemSupplyItem(demMap);
		return demMap;
	}
	
	/**
	 * 통합검색 - 정사영상 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> setOrtSupplyItem(Map<String, Object> sendMap) throws Exception {
		
		String supIdn   = (String)sendMap.get("supIdn");
		String fileType = (String)sendMap.get("fileType");
		String zoneCode = (String)sendMap.get("zoneCode");
		String pathOne  = (String)sendMap.get("pathOne");
		String pathTwo  = (String)sendMap.get("pathTwo");
		String fileName = (String)sendMap.get("fileName");
		
		Map<String, Object> ortMap = new HashMap<String, Object>();
		
		ortMap.put("supIdn"		, supIdn);
		ortMap.put("imageCde"	, fileType);
		ortMap.put("zoneCode"	, zoneCode);
		ortMap.put("map5000Num"	, pathOne);
		ortMap.put("gtypDst"	, pathTwo);
		ortMap.put("fileName"	, fileName);
//		ortMap.put("filePath"	, propertyService.getString("Globals.OrtDrive")+"data"+"\\"+"ort"+"\\"+"ort_file"+"\\"+"image"+"\\"+zoneCode.substring(0,4)+"\\"+zoneCode.substring(7)+"\\"+pathOne+"\\"+fileName+".tif");
		ortMap.put("filePath"	, fileName);
		if(sendMap.get("waterMarkWeight").equals("nomark")){
			ortMap.put("markIndex"	,"");
		}else{
			ortMap.put("markIndex"	, this.setcreateIndex(sendMap));
		}
		ortMap.put("waterMarkWeight", sendMap.get("waterMarkWeight"));
		
		searchDAO.insertOrtSupplyItem(ortMap);
		return ortMap;
	}

	/**
	 * 통합검색 - 라이다 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> setLidSupplyItem(Map<String, Object> sendMap) throws Exception {
		
		String supIdn   = (String)sendMap.get("supIdn");
		String fileType = (String)sendMap.get("fileType");
		String zoneCode = (String)sendMap.get("zoneCode");
		String pathOne  = (String)sendMap.get("pathOne");
		//String pathTwo  = (String)sendMap.get("pathTwo");
		String fileName = (String)sendMap.get("fileName");
		
		Map<String, Object> lidMap = new HashMap<String, Object>();
		
		lidMap.put("supIdn"		, supIdn);
		lidMap.put("imageCde"	, fileType);
		lidMap.put("zoneCode"	, zoneCode);
		lidMap.put("lidarIdn"	, pathOne);
		lidMap.put("fileName"	, fileName);
//		lidMap.put("filePath"	, propertyService.getString("Globals.OrtDrive")+"data"+"\\"+"dem"+"\\"+"dem_file"+"\\"+"sri"+"\\"+zoneCode.substring(0,4)+"\\"+zoneCode.substring(7)+"\\"+pathOne+"\\"+fileName+".tif");
		lidMap.put("filePath"	, fileName);
		lidMap.put("markIndex"	, "nomark");
		
		searchDAO.insertLidSupplyItem(lidMap);
		return lidMap;
	}

	/**
	 * 통합검색 - NIR 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> setNirSupplyItem(Map<String, Object> sendMap) throws Exception {

		String supIdn   = (String)sendMap.get("supIdn");
		String fileType = (String)sendMap.get("fileType");
		String zoneCode = (String)sendMap.get("zoneCode");
		String pathOne  = (String)sendMap.get("pathOne");
		String pathTwo  = (String)sendMap.get("pathTwo");
		String fileName = (String)sendMap.get("fileName");
		
		Map<String, Object> nirMap = new HashMap<String, Object>();
		
		nirMap.put("supIdn"		, supIdn);
		nirMap.put("imageCde"	, fileType);
		nirMap.put("zoneCode"	, zoneCode);
		nirMap.put("phCourse"	, pathOne);
		nirMap.put("phNum"		, pathTwo);		
		nirMap.put("fileName"	, fileName);
		//airMap.put("filePath"	, "D:\\"+"data"+"\\"+"air"+"\\"+"air_file"+"\\"+"image"+"\\"+zoneCode+"\\"+pathOne+"\\"+pathTwo+"\\"+fileName+".tif");
//		nirMap.put("filePath"	, propertyService.getString("Globals.OrtDrive")+"data"+"\\"+"air"+"\\"+"air_file"+"\\"+"image"+"\\"+zoneCode.substring(0,4)+"\\"+zoneCode.substring(7)+"\\"+pathOne+"\\"+fileName+".tif");
		nirMap.put("filePath"	, fileName);
		nirMap.put("markIndex"	, this.setcreateIndex(sendMap));
		
		searchDAO.insertNirSupplyItem(nirMap);
		return nirMap;
	}

	/**
	 * 통합검색 - 3차원 객체 신청 내역 등록
	 * @param sendMap
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> setTdsSupplyItem(Map<String, Object> sendMap) throws Exception {
		
		String supIdn   = (String)sendMap.get("supIdn");
		String fileType = (String)sendMap.get("fileType");
		String zoneCode = (String)sendMap.get("zoneCode");
		String pathOne  = (String)sendMap.get("pathOne");
		//String pathTwo  = (String)sendMap.get("pathTwo");
		String fileName = (String)sendMap.get("fileName");
		
		Map<String, Object> tdsrMap = new HashMap<String, Object>();
		
		tdsrMap.put("supIdn"		, supIdn);
		tdsrMap.put("imageCde"		, fileType);
		tdsrMap.put("zoneCode"		, zoneCode);
		tdsrMap.put("tdsIdn"		, pathOne);
		tdsrMap.put("fileName"		, fileName);
//		tdsrMap.put("filePath"		, propertyService.getString("Globals.OrtDrive")+"data"+"\\"+"air"+"\\"+"air_file"+"\\"+"image"+"\\"+zoneCode.substring(0,4)+"\\"+zoneCode.substring(7)+"\\"+pathOne+"\\"+fileName+".tif");
		tdsrMap.put("filePath_2"	, fileName);
		tdsrMap.put("filePath"		, fileName);
		tdsrMap.put("markIndex_2"	, this.setcreateIndex(sendMap));
		tdsrMap.put("markIndex"		, "tds");
		
		searchDAO.insertTdsSupplyItem(tdsrMap);
		return tdsrMap;
	}
	
	
	@Override
	public ParamVO getBeforeSearchCondtion(Map<String, Object> param) throws Exception {
		
		ParamVO vo = new ParamVO();
		
		String imgType = "";
		String admcd 	= "";
		String bounds 		= "";
		
		if (param != null){
			
			if (param.containsKey("imgType")){
				imgType = (String)param.get("imgType");
			}
			
			if(param.containsKey("admcd")) {
				admcd = (String)param.get("admcd");
				if(admcd.length() == 2) {
					vo.setTableNm("TL_SCCO_CTPRVN");
				} else if(admcd.length() == 5) {
					vo.setTableNm("TL_SCCO_SIG");
				} else {
					vo.setTableNm("TL_SCCO_EMD");
				}
			}
			
			Iterator<String> iter = param.keySet().iterator();
			while(iter.hasNext()){ 
				String key = (String)iter.next(); 
				
				if (key.equals("sYear")){
					vo.setStartYear((String)param.get(key));
				}
				if (key.equals("eYear")){
					vo.setEndYear((String)param.get(key));
				}
				if (key.equals("zoneCode")){
					//다운로드시 array로 들어옴
					if(param.get(key) instanceof String){
						vo.setZoneCode((String)param.get(key));
					}
				}
				if (key.equals("admcd")){
					admcd = (String)param.get(key);
					if (!admcd.equals("00")){
						vo.setAdmcd(admcd);
					}
				}
				if (key.equals("bounds")){
					bounds = (String)param.get(key);
					if (bounds.indexOf(",") > -1){
						String[] temp = bounds.split(",");
						//minx, miny, maxx, maxy
						vo.setXmin(Float.parseFloat(temp[0]));
						vo.setYmin(Float.parseFloat(temp[1]));
						vo.setXmax(Float.parseFloat(temp[2]));
						vo.setYmax(Float.parseFloat(temp[3]));
					}
				}
				if (key.equals("radius")){
					try{
						vo.setRadius(Integer.parseInt((String)param.get(key)));
					}catch(NumberFormatException e){
						vo.setRadius(0);
					}
				}
				if (key.equals("geometry")){
					String geomtry = (String)param.get(key);
					if (!geomtry.equals("")){
						vo.setGeometry(geomtry);
					}
				}
								
				//영상종류별 파라미터 세팅
				//항공사진, NIR
				if("0".equals(imgType) || "4".equals(imgType)){
					if (key.equals("phCourse")){
						vo.setPhCourse((String)param.get(key));
					}
					if (key.equals("securityCde")){
						vo.setSecurityCde((String)param.get(key));
					}
				}
				//정사영상
				else if("1".equals(imgType)){
					if (key.equals("gtypDst")){
						vo.setGtypDst((String)param.get(key));
					}
					if (key.equals("securityCde")){
						vo.setSecurityCde((String)param.get(key));
					}
					if (key.equals("mapNum")){
						vo.setMapNum((String)param.get(key));
					}
				}
				//수치표고
				else if("2".equals(imgType)){
					if (key.equals("gridInt")){
						vo.setGridInt((String)param.get(key));
					}
					if (key.equals("securityCde")){
						vo.setSecurityCde((String)param.get(key));
					}
					if (key.equals("mapNum")){
						vo.setMapNum((String)param.get(key));
					}
				}
			}
			
			//policyUtil.addPolicy(param, vo);
		}
		return vo;
	}
	
	@Override
	public Map<String, ArrayList<String>> getUnloadedData(ParamVO vo, String radius, String fileType, String zoneCode, String pathOne, String pathTwo) throws Exception {

		Map<String, ArrayList<String>> nodeDataMap = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> fileTypeArr = new ArrayList<String>();
		ArrayList<String> zoneCodeArr = new ArrayList<String>();
		ArrayList<String> pathOneArr = new ArrayList<String>(); 
		ArrayList<String> pathTwoArr = new ArrayList<String>();
		ArrayList<String> fileNameArr = new ArrayList<String>();
		
		//항공사진, NIR, 항공사진(해방전후)
		if(fileType.equals(propertyService.getString("Globals.airImageCode")) || fileType.equals(propertyService.getString("Globals.nirImageCode")) || fileType.equals(propertyService.getString("Globals.airLibImageCode"))){
			String[] s = pathOne.split("\\|");
			
			vo.setZoneCode(zoneCode);
			vo.setPhCourse(s[0]);
			vo.setSecurityCde(s[1]);
			
			try{
				List<?> list = null;
				
				if(fileType.equals(propertyService.getString("Globals.airImageCode"))){
					list = this.getAirImgDataList(vo);
				}else if(fileType.equals(propertyService.getString("Globals.airLibImageCode"))){
					list = this.getAirLibImgDataList(vo);
				}else if(fileType.equals(propertyService.getString("Globals.nirImageCode"))){
					list = this.getNirImgDataList(vo);
				}
				
				if(null != list){
				
					if (!radius.equals("")){
						list = this.getContainsList(vo.getXmin(), vo.getYmin(), vo.getXmax(), vo.getYmax(), list, radius);
					}
					
					for(int i=0; i<list.size(); i++){
						EgovMap airMap = (EgovMap)list.get(i);
						
						String airImageCde 	= String.valueOf(airMap.get("imageCde"));
						//String airStoDrv 	= (String)airMap.get("stoDrv");
						//String airFolderNam = (String)airMap.get("folderNam");
						//String airSceneyear = (String)airMap.get("sceneyear");
						String airZoneCode 	= String.valueOf(airMap.get("zoneCode"));
						String airPhCourse 	= String.valueOf(airMap.get("phCourse"));
						String airPhNum 	= String.valueOf(airMap.get("phNum"));
						//String airFileName 	= (String)airMap.get("fileNam");
						//String airFilePath 	= airStoDrv + ":\\" + airFolderNam + "\\" + airSceneyear + "\\" + airZoneCode.substring(7,11) + "\\" + airPhCourse + "\\" + airFileName;
						String airFilePath 	= String.valueOf(airMap.get("filePath"));
						//String airMetaPath 	= airStoDrv + ":\\air\\meta\\db_meta_air\\" + airSceneyear + "\\" + airZoneCode + "\\air_product_dts.xls";
						
						fileTypeArr.add(airImageCde);
						zoneCodeArr.add(airZoneCode);
						pathOneArr.add(airPhCourse); 
						pathTwoArr.add(airPhNum);
						fileNameArr.add(airFilePath);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//정사영상, 수치표고
		else if(fileType.equals(propertyService.getString("Globals.demImageCode")) || fileType.equals(propertyService.getString("Globals.ortImageCode"))){
			String[] s = pathOne.split("\\|");
			
			vo.setZoneCode(zoneCode);
			vo.setSecurityCde(s[0]);
			vo.setMapNum(s[2]);
			
			try{
				List<?> list = null;
				
				if(fileType.equals(propertyService.getString("Globals.demImageCode"))){
					vo.setGridInt(s[1]);
					list = this.getDemImgDataList(vo);
				}else if(fileType.equals(propertyService.getString("Globals.ortImageCode"))){
					vo.setGtypDst(s[1]);
					list = this.getOrtImgDataList(vo);
				}
				
				if(null != list){
				
					if (!radius.equals("")){
						list = this.getContainsList(vo.getXmin(), vo.getYmin(), vo.getXmax(), vo.getYmax(), list, radius);
					}
					
					for(int i=0; i<list.size(); i++){
						EgovMap ortMap = (EgovMap)list.get(i);
						
						String ortImageCde 		= String.valueOf(ortMap.get("imageCde"));
						//String ortStoDrv 		= (String)ortMap.get("stoDrv");
						//String ortFolderNam 	= (String)ortMap.get("folderNam");
						//String ortSceneyear 	= (String)ortMap.get("sceneyear");
						String ortZoneCode 		= String.valueOf(ortMap.get("zoneCode"));
						String ortMap5000Num 	= String.valueOf(ortMap.get("map5000Num"));
						String ortGtypDst 		= fileType.equals(propertyService.getString("Globals.demImageCode")) ? String.valueOf(ortMap.get("gridInt")) : String.valueOf(ortMap.get("gtypDst"));
						//String ortFileName 		= (String)ortMap.get("fileNam");
						//String ortFilePath 		= ortStoDrv + ":\\" + ortFolderNam + "\\" + ortSceneyear + "\\" + ortGtypDst + "\\" + ortMap5000Num + "\\" + ortFileName;
						String ortFilePath 		= String.valueOf(ortMap.get("filePath"));
						
						fileTypeArr.add(ortImageCde);
						zoneCodeArr.add(ortZoneCode);
						pathOneArr.add(ortMap5000Num); 
						pathTwoArr.add(ortGtypDst); 
						fileNameArr.add(ortFilePath);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//수치지형도
		else if(fileType.equals(propertyService.getString("Globals.mapImageCode"))){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("mapShtNo", zoneCode);
			param.put("mapSerNo", pathOne);
			
			try{
				List<?> list = null;
				
				list = this.getSuchiList(param);
				
				if(null != list){
				
//					if (!radius.equals("")){
//						list = this.getContainsList(vo.getXmin(), vo.getYmin(), vo.getXmax(), vo.getYmax(), list, radius);
//					}
					
					for(int i=0; i<list.size(); i++){
						EgovMap suchiMap = (EgovMap)list.get(i);
						
						String suchiImageCde	= "PDT008";
						String mapShtNo 		= String.valueOf(suchiMap.get("mapShtNo"));
						String mapSerNo 		= String.valueOf(suchiMap.get("mapSerNo"));
						String mapHistoryNo 	= String.valueOf(suchiMap.get("mapHistoryNo"));
						
						fileTypeArr.add(suchiImageCde);
						zoneCodeArr.add(mapShtNo);
						pathOneArr.add(mapSerNo); 
						pathTwoArr.add(mapHistoryNo); 
						fileNameArr.add("");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		nodeDataMap.put("fileType", fileTypeArr);
		nodeDataMap.put("zoneCode", zoneCodeArr);
		nodeDataMap.put("pathOne", pathOneArr);
		nodeDataMap.put("pathTwo", pathTwoArr);
		nodeDataMap.put("fileName", fileNameArr);
		
		return nodeDataMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<?> getContainsList(double tagetMinx, double tagetMiny, double tagetMaxx, double tagetMaxy, List obj, String radius) throws Exception {
		Geometry targetGeom = this.createEnvelopeToGeometry(tagetMinx, tagetMiny, tagetMaxx, tagetMaxy);
		Point targetPoint = targetGeom.getCentroid();
		if (obj != null){
			for (int i = 0; i < obj.size(); i++){
				Map<String, Object> info = (Map<String, Object>) obj.get(i);
				BigDecimal bdXmin = new BigDecimal(info.get("xmin").toString());
				BigDecimal bdYmin = new BigDecimal(info.get("ymin").toString());
				BigDecimal bdXmax = new BigDecimal(info.get("xmax").toString());
				BigDecimal bdYmax = new BigDecimal(info.get("ymax").toString());
				Point point = this.getEnvelopeOfCenter(bdXmin.doubleValue(), bdYmin.doubleValue(), bdXmax.doubleValue(), bdYmax.doubleValue());
				if (!targetPoint.isWithinDistance(point, Double.parseDouble(radius))){
					obj.remove(i);
				}
			}
		}
		return obj;
	}
	
	@Override
	public Geometry createEnvelopeToGeometry(double minx, double miny, double maxx, double maxy) throws Exception {
		Geometry geom = null;
		Coordinate[] points = new Coordinate[5];
		points[0] = new Coordinate(minx, miny);
		points[1] = new Coordinate(maxx, miny);
		points[2] = new Coordinate(maxx, maxy);
		points[3] = new Coordinate(minx, maxy);
		points[4] = new Coordinate(minx, miny);
		GeometryFactory fact = new GeometryFactory();
		GeometryFactory factory = new GeometryFactory();
		LinearRing ring = factory.createLinearRing(points);
		
		Polygon poly = new Polygon(ring, null, fact);
		geom = (Geometry) poly;
		
		return geom;
	}
	
	private Point getEnvelopeOfCenter(double minx, double miny, double maxx, double maxy) throws Exception {
		Coordinate[] points = new Coordinate[5];
		points[0] = new Coordinate(minx, miny);
		points[1] = new Coordinate(maxx, miny);
		points[2] = new Coordinate(maxx, maxy);
		points[3] = new Coordinate(minx, maxy);
		points[4] = new Coordinate(minx, miny);
		GeometryFactory fact = new GeometryFactory();
		GeometryFactory factory = new GeometryFactory();
		LinearRing ring = factory.createLinearRing(points);
	
		Polygon poly = new Polygon(ring, null, fact);
		Point point = poly.getCentroid();
		return point;
	}
	
	private static Geometry createCircle(double x, double y, final double RADIUS) {
	    GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
	    shapeFactory.setNumPoints(32);
	    shapeFactory.setCentre(new Coordinate(x, y));
	    shapeFactory.setSize(RADIUS * 2);
	    return shapeFactory.createCircle();
	}
}