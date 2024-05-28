package org.nii.niis.fileDownlaod.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.fileDownlaod.service.FileDownloadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;


@Controller
@RequestMapping(value="/download/")
public class FileDownloadController {
	
	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="fileDownloadService")
	private FileDownloadService fileDownloadService;

	/**
	 * 파일 다운로드 리스트 조회 - 항공사진
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getFileAirList.do")
	public @ResponseBody HashMap<String,Object> getFileAirList(@RequestParam HashMap<String, Object> param) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", fileDownloadService.getFileAirList(param));
		return result;
	}
	
	/**
	 * 파일 다운로드 리스트 조회 - 정사영상
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getFileOrtList.do")
	public @ResponseBody HashMap<String,Object> getFileOrtList(@RequestParam HashMap<String, Object> param) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", fileDownloadService.getFileOrtList(param));
		return result;
	}
	
	/**
	 * 파일 다운로드 리스트 조회 - DEM
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getFileDemList.do")
	public @ResponseBody HashMap<String,Object> getFileDemList(@RequestParam HashMap<String, Object> param) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", fileDownloadService.getFileDemList(param));
		return result;
	}
	
	/**
	 * 파일 다운로드 리스트 조회 - 수치지형도
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getFileMapList.do")
	public @ResponseBody HashMap<String,Object> getFileMapList(@RequestParam HashMap<String, Object> param) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", fileDownloadService.getFileMapList(param));
		return result;
	}
	
	/**
	 * 파일 다운로드 리스트 조회 - AT
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getFileAtList.do")
	public @ResponseBody HashMap<String,Object> getFileAtList(@RequestParam HashMap<String, Object> param) throws Exception{
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", fileDownloadService.getFileAtList(param));
		return result;
	}
	
	
	/**
	 * 파일 다운로드 리스트 조회 - 수치지형도 메타데이터
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getFileMapMetaList.do")
	public void getFileMapMetaList(@RequestParam HashMap<String, Object> param, HttpServletRequest request ,HttpServletResponse response) throws Exception{
		
		
		try {
				request.setCharacterEncoding("UTF-8");
				
				String sn = param.get("sn").toString();
				String pdtCd = param.get("pdtCd").toString();
				String mapKindNm = request.getParameter("mapKindNm");
				String scaleNm = request.getParameter("scaleNm");
				String mapShtNo = request.getParameter("mapShtNo");
				String openDvsnNm = request.getParameter("openDvsnNm");
				
				
				if(sn != null && !("").equals(sn)) {
					
					HashMap<String, String> bsmPk = new HashMap<String, String>();
					bsmPk.put("sn", sn);
					bsmPk.put("pdtCd", pdtCd);
					
					Document document = fileDownloadService.getXMLGeneration(bsmPk);
					
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					DOMSource xmlSource = new DOMSource(document);
					StreamResult streamResult = new StreamResult(byteArrayOutputStream);
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();   
					Transformer transformer = transformerFactory.newTransformer();
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //정렬 스페이스4칸
					transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
					transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //들여쓰기
					transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); 했을때 붙어서 출력되는부분 개행				
					transformer.transform(xmlSource, streamResult);
					
					InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());			
					
					String fileName = (mapKindNm + "_" + scaleNm + "_" + mapShtNo + "_" + openDvsnNm + ".xml").replace(":", "대");
					String encodedFilename = "attachment; filename*=" + "UTF-8" + "''" + URLEncoder.encode(fileName, "UTF-8");
					response.setContentType("application/octet-stream; charset=utf-8");
					response.setHeader("Content-Disposition", encodedFilename);
					response.setContentLength(byteArrayOutputStream.toByteArray().length);
					
					BufferedInputStream in = null;
					BufferedOutputStream out = null;
					
					in = new BufferedInputStream(inputStream);
					
					out = new BufferedOutputStream(response.getOutputStream());
					
					
					try {
						byte[] buffer = new byte[4096];
						int bytesRead = 0;
						
						while ((bytesRead = in.read(buffer)) != -1) {
							out.write(buffer, 0, bytesRead);
						}
						
						out.flush();
						
					} finally {
						in.close();
						out.close();
						inputStream.close();
						byteArrayOutputStream.close();
					}
				}
				

		} catch(Exception e) {
			e.printStackTrace();			
		}
		
	}

}
