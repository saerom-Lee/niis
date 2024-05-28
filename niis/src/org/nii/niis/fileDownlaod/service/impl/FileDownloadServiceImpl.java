package org.nii.niis.fileDownlaod.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.nii.niis.fileDownlaod.service.FileDownloadService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("fileDownloadService")
public class FileDownloadServiceImpl implements FileDownloadService{
	
	@Resource(name="fileDownloadDAO")
    private FileDownloadDAO fileDownloadDAO;
	
	@Override
	public List<HashMap<String, Object>> getFileAirList(HashMap<String, Object> sendMap) throws Exception {
		return fileDownloadDAO.getFileAirList(sendMap);
	}
	@Override
	public List<HashMap<String, Object>> getFileOrtList(HashMap<String, Object> sendMap) throws Exception {
		return fileDownloadDAO.getFileOrtList(sendMap);
	}
	@Override
	public List<HashMap<String, Object>> getFileDemList(HashMap<String, Object> sendMap) throws Exception {
		return fileDownloadDAO.getFileDemList(sendMap);
	}
	
	@Override
	public List<HashMap<String, Object>> getFileMapList(HashMap<String, Object> sendMap) throws Exception {
		return fileDownloadDAO.getFileMapList(sendMap);
	}
	
	@Override
	public List<HashMap<String, Object>> getFileAtList(HashMap<String, Object> sendMap) throws Exception {
		return fileDownloadDAO.getFileAtList(sendMap);
	}
	
	@Override
	public List<EgovMap> getMapMetaList(HashMap<String, Object> sendMap) throws Exception {
		
		List<EgovMap> getFileMapBisList = fileDownloadDAO.getFileMapBisList(sendMap);
		
		return getFileMapBisList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Document getXMLGeneration(HashMap<String, String> bsmPk) throws Exception {
		String name;
		String value;
		EntityMap map;
		Document document = null;
		try {
			EntityMap bsmInfo = fileDownloadDAO.selectBsmDetailMap(bsmPk);
			
			System.out.println(bsmInfo);
			if(bsmInfo != null) {
					
				
				// XML 문서 파싱
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = factory.newDocumentBuilder();
				
				// 새로운 XML 생성! //
				// 새로운 Document 객체 생성
				document = documentBuilder.newDocument();
				document.setXmlStandalone(true);
				
				//////////////////////////////////////////////////
				// <mdb:MD_Metadata>
				// 0. [메타데이터_메인] 및 [네임스페이스_정보] 
				EntityMap paramMap = new EntityMap();
				paramMap.put("pdtCd", bsmPk.get("pdtCd"));
				List<EntityMap> mdMetaDataList = fileDownloadDAO.selectSmdNamespaceInfo(paramMap);
				if(mdMetaDataList != null && mdMetaDataList.size() > 0) {
					// root <mdb:MD_Metadata> 생성
					Element root = document.createElement((String)mdMetaDataList.get(0).get("NMSP")+":"+(String)mdMetaDataList.get(0).get("IEMNM"));
					
					// root <mdb:MD_Metadata> 속성 설정
					for(int j=0; j<mdMetaDataList.size(); j++) {
						map = mdMetaDataList.get(j);
						name = (String)map.get("DCLR") + ":" + (String)map.get("NMSP_NM");
						value = (String)map.get("NMSP_LC_VALUE");
						root.setAttribute(name, value);
					}
					
					// root <mdb:MD_Metadata> schemaLocation 속성 설정
					for(int j=0; j<mdMetaDataList.size(); j++) {
						map = mdMetaDataList.get(j);
						if(map.get("SCHEMA_LC_VALUE") != null && !"".equals((String)map.get("SCHEMA_LC_VALUE"))) {
							name = (String)map.get("NMSP_NM") + ":schemaLocation";
							value = (String)map.get("SCHEMA_LC_VALUE");
							root.setAttribute(name, value);						
						}
					}					
					// 자식 요소 추가
					document.appendChild(root);
					
					paramMap.put("nmsp", "mdb");
					List<EntityMap> mdbList = fileDownloadDAO.selectSmdSchemaInfo(paramMap);
					
					for(int j=0; j<mdbList.size(); j++) {
						map = mdbList.get(j);
						
						// MDB 자식 노드 생성
						name = (String)map.get("NMSP_NM") + ":" + (String)map.get("ENGNM");		
						Element mdbElement = document.createElement(name);
						
						this.makeElementXml(document, mdbElement, bsmPk.get("pdtCd"), (String)map.get("SN"), bsmInfo);
						
						root.appendChild(mdbElement);
					}
						
				}																	
				
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private Element makeElementXml(Document document, Element parentElement, String pdtCd, String sn, EntityMap bsmInfo) throws Exception {
		
		EntityMap paramMap = new EntityMap();
		paramMap.put("pdtCd", pdtCd);
		paramMap.put("upSn", sn);
		List<EntityMap> childSchemaList = fileDownloadDAO.selectSmdSchemaInfo(paramMap);
		
		if(childSchemaList.size() > 0)
		{
			String name;
			String value;

			EntityMap map;
			for(int i=0; i<childSchemaList.size(); i++)
			{
				map = childSchemaList.get(i);
				name = (String)map.get("NMSP_NM") + ":" + (String)map.get("ENGNM");
				Element childElement = document.createElement(name);
				if(map.get("TRGT_TABLE") != null && map.get("TRGT_FIELD") != null)
				{
					if(map.get("CD_YN") != null && "Y".equals((String)map.get("CD_YN")))
					{
						name = (String)map.get("VALUE_FOM");
						Element subChildElement = document.createElement(name);
						String[] attr = ((String)map.get("CD_LIST")).split("=");
						subChildElement.setAttribute(attr[0], (String)attr[1].replace("\"", ""));
						
						EgovMap codeMap = fileDownloadDAO.selectMapSmdCdInfo((String)bsmInfo.get((String)map.get("TRGT_FIELD")));
						value = ((String)codeMap.get("cdNm")).trim() + " (" + (String)codeMap.get("cdNmEng") + ")";
						subChildElement.setAttribute((String)map.get("CD_VALUE"), value);
						subChildElement.setTextContent(value);
						
						childElement.appendChild(subChildElement);
					}
					else if("gco:DateTime".equals((String)map.get("VALUE_FOM")))
					{
						System.out.println("2TRGT_FIELD========>" + (String)map.get("TRGT_FIELD"));
						System.out.println("2VALUE_FOM========>" + (String)map.get("VALUE_FOM"));
						System.out.println("2value=============>" + bsmInfo.get((String)map.get("TRGT_FIELD")));
						
						name = (String)map.get("VALUE_FOM");
						Element subChildElement = document.createElement(name);
						value =	(String)bsmInfo.get((String)map.get("TRGT_FIELD"));
						subChildElement.setTextContent(value);						
						
						childElement.appendChild(subChildElement);
						
					}										
					else
					{
						System.out.println("TRGT_FIELD========>" + (String)map.get("TRGT_FIELD"));
						System.out.println("VALUE_FOM========>" + (String)map.get("VALUE_FOM"));
						System.out.println("value=============>" + bsmInfo.get((String)map.get("TRGT_FIELD")));
						
						name = (String)map.get("VALUE_FOM");
						Element subChildElement = document.createElement(name);
						value = String.valueOf(bsmInfo.get((String)map.get("TRGT_FIELD")));						
						subChildElement.setTextContent(value);
						
						childElement.appendChild(subChildElement);					
						
						
					}
					
				}

				this.makeElementXml(document, childElement, pdtCd, (String)map.get("SN"), bsmInfo);
				
				parentElement.appendChild(childElement);				
			}			
		}
		else
		{
			return parentElement;
		}						
		
		return parentElement;
	}
}
