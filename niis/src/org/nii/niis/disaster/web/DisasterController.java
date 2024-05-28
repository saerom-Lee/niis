package org.nii.niis.disaster.web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.common.service.ConnService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import egovframework.rte.fdl.property.EgovPropertyService;
/**
 * disaster controller 객체
 * 
 * @directed
 */
@Controller
@RequestMapping(value="/disaster/")
public class DisasterController {

	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	private EgovPropertyService propertiesService;
	/**
	 * Conn controller 객체
	 * 
	 * @directed
	 */
	@Resource(name="connService")
	private ConnService connService;
	
	/**
	 * 공지사항 게시판의 master 정보 가져오기
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
//	@RequestMapping(value="disaster.do")
//    public String disaster(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		
//		try{
//			String action = "/disaster/disaster.do";
//			
////			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
//			
//			if(param.containsKey("boardLink")){
//				model.addAttribute("boardLink", param.get("boardLink"));
//			}
////			model.addAttribute("master", jsonMap.get("master"));
//			model.addAttribute("RETURN_CD", HttpServletResponse.SC_OK);
//		}catch(Exception e){
//			log.error(this.getClass() + "::::board", e);
//			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		}
//		
//		return "disaster/disaster";
//	}
	
	/**
	 * 공지사항 게시판 list 가져오기
	 * @param request
	 * @param response
	 * @param session
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value="getDisasterList.do")
	public ModelAndView getDisasterList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/disaster/getDisasterList.do";
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			//modelAndView.addObject("top", jsonMap.get("top"));
			modelAndView.addObject("totalcnt", jsonMap.get("totalcnt"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getBoardList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
		
	@RequestMapping(value="getDisasterVideoList.do")
	public ModelAndView getDisasterVideoList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		try{
			String action = "/disaster/getDisasterVideoList.do";
			
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("totalcnt", jsonMap.get("totalcnt"));
			modelAndView.addObject("map", jsonMap.get("map"));
			modelAndView.addObject("_pageData_", jsonMap.get("_pageData_"));
			modelAndView.addObject("imageCde", propertiesService.getString("Globals.airZoneCode"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getAirProductList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="listSearch.do")
    public String listSearch(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "disaster/disListSearch";
	}
	
	/** 
	* <pre>
	* @Method : disasterTifImgData
	* @description : 미리보기 팝업
	* </pre>
	* @param model
	* @param param
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(value="disasterTifImgData.do")
	public String disasterTifImgData(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			/**
			 * 전체 내역을 가져와 DIV에 넘기는 로직
			 * 속도 개선을 위해 조회 파라미터를 넘겨 자바단에서 처리토록 변경
			 */
			String action = "/disaster/disasterTifImgData.do";
			//보안등급 공개제한만 조회
			//param.put("securityCde", "SEC002");
			
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			String tempPath = propertiesService.getString("Globals.g119TempPath");
			String fileContent = getFileContent(tempPath+"noImg.png");
			String fileName = "noImg";
			
			List<Map<String, Object>> list = (List<Map<String, Object>>) jsonMap.get("list");
			if(list != null) {
				Map<String, Object> data = list.get(0);
				String msfrtnId = data.get("msfrtnId").toString();
				String fileEngName = data.get("fileEngNm").toString();
				fileName = data.get("fileNm").toString();
				
				String sendUrl = propertiesService.getString("Globals.g119SendUrl");
				sendUrl += "&msfrtnId="+msfrtnId;
				sendUrl += "&fileNm="+fileEngName;
				sendUrl += "&type=img";
				
				Calendar calendar = Calendar.getInstance();
				java.util.Date date = calendar.getTime();
				String today = (new SimpleDateFormat("yyyyMMddHHmmss").format(date));
				tempPath += fileName+"_"+today+".png";
				
				System.out.println("sendUrl  >>>> "+sendUrl);
				System.out.println("tempPath >>>> "+tempPath);
				
				try {
					URL url = new URL(sendUrl);
					BufferedImage image = ImageIO.read(url);
					if(image != null) {
						File file = new File(tempPath);
						ImageIO.write(image, "png", file);
						
						fileContent = getFileContent(tempPath);
						file.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			model.addAttribute("cnt", 0);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_OK);
			model.addAttribute("imgSrc", fileContent);
			model.addAttribute("imgNm", fileName);
		}catch(Exception e){
			log.error(this.getClass() + "::::previewConfirm", e);
			model.addAttribute("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return "disaster/disasterTifImgData";
	}
	
	@RequestMapping(value="disasterFileDwn.do")
	public ModelAndView disasterFileDwn(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String action = "/disaster/disasterFileDwn.do";
		
		Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
		
	    String zipFileName =  (String) jsonMap.get("zipFileName");
	    String outFileName =  (String) jsonMap.get("outFileName");
		model.addAttribute("zipFileName", URLEncoder.encode(zipFileName, "UTF-8"));
		model.addAttribute("outFileName", URLEncoder.encode(outFileName, "UTF-8"));
				
		return modelAndView;
	}
	
	@RequestMapping(value="disasterFileDown.do")
	public void disasterFileDown(Model model, @RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = (String) param.get("path");
		String name = (String) param.get("name");
		String fname = (String) param.get("name");
		if(path != null) path = URLDecoder.decode(path, "UTF-8");
		if(fname != null) fname = URLDecoder.decode(name, "UTF-8") + ".zip";
		if(name != null) name = URLDecoder.decode(name, "UTF-8");
		
		HashMap<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("userAgent"			, request.getHeader("User-Agent"));
		fileMap.put("realFileName"		, path);
		fileMap.put("originalFileName"	, fname);
		fileMap.put("fileName"			, name);
		
		try {
			downloadFile(request, response, fileMap, true);
//			downloadFile2(request, response, fileMap, true);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@RequestMapping(value="getDisaCodeList.do")
	public ModelAndView getDisaCodeList(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String action = "/disaster/getDisasterCodeList.do";
		
		try{
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDisaCodeList", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="getDisaRegion1List.do")
	public ModelAndView getDisaRegion1List(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String action = "/disaster/getDisasterRegion1List.do";
		
		try{
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDisaRegion1List", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="getDisaRegion2List.do")
	public ModelAndView getDisaRegion2List(@RequestParam Map<String, Object> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("jsonView");
		
		String action = "/disaster/getDisasterRegion2List.do";
		
		try{
			Map<String, Object> jsonMap = connService.connNiim(action, param, request, response);
			
			modelAndView.addObject("list", jsonMap.get("list"));
			modelAndView.addObject("RETURN_CD", jsonMap.get("RETURN_CD"));
		}catch(Exception e){
			log.error(this.getClass() + "::::getDisaRegion2List", e);
			modelAndView.addObject("RETURN_CD", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return modelAndView;
	}
	
	public static String getFileContent(String filePath) {
		byte[] filebyte = convertFileContentToBlob(filePath);
		return convertBlobToBase64(filebyte);
	}
	
	public static byte[] convertFileContentToBlob(String filePath) {
		byte[] result = null;
		try {
			result = FileUtils.readFileToByteArray(new File(filePath));
		} catch (IOException ie) {
			System.out.println("file convert Error : " + filePath);
		}
		return result;
	}
	
	public static String convertBlobToBase64(byte[] blob) {
		return new String(Base64.encodeBase64(blob));
	}
	
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> map, boolean type)
			throws Exception {

		String filepath = map.get("realFileName").replaceAll("\\\\", "/");

		File file = new File(filepath);

		if (file == null || !file.exists() || file.length() <= 0 || file.isDirectory()) {
			throw new FileNotFoundException(filepath);
		} else {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			ServletOutputStream so = null;
			BufferedOutputStream bos = null;

			try {
				String downName = null;
				String fileName = map.get("originalFileName");
				String browser = request.getHeader("User-Agent");
				
				if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
					downName = URLEncoder.encode(fileName, "UTF-8").replace("\\+", "%20");
				} else {
					downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				}
				
				response.setHeader("Content-Disposition", "attachment; filename=\"" + downName + "\";");
				response.setContentType("application/zip;");
				response.setHeader("Content-Transfer-Encoding", "binary;");
				
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				so = response.getOutputStream();
				bos = new BufferedOutputStream(so);
				
				byte[] data = new byte[2048];
				int input = 0;
				
				while ((input = bis.read(data)) != -1) {
					bos.write(data, 0, input);
					bos.flush();
				}
			} catch (ClientAbortException e) {
//				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != bos) {
					try {
						bos.close();
					} catch (Exception e) {
					}
				}
				if (null != bis) {
					try {
						bis.close();
					} catch (Exception e) {
					}
				}
				if (null != so) {
					try {
						so.close();
					} catch (Exception e) {
					}
				}
				if (null != fis) {
					try {
						fis.close();
					} catch (Exception e) {
					}
				}
				
		        if(type && file.exists()) {
		        	file.delete();
		        }
			}
		}
	}
	
	public void downloadFile2(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> map, boolean type)
			throws Exception {

		String filepath = map.get("realFileName").replaceAll("\\\\", "/");
		String fileName = map.get("fileName");

		File file = new File(filepath);

		if (file == null || !file.exists() || file.length() <= 0 || file.isDirectory()) {
			throw new FileNotFoundException(filepath);
		} else {
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			ServletOutputStream out = null;
			BufferedOutputStream bos = null;

			try {
				String downName = "";
				String header = request.getHeader("User-Agent");
				response.setContentType("application/zip");
		        response.setHeader("Content-Length",""+file.length());
				
				// 브라우저 별 한글 인코딩
				if (header.contains("Edge")) {
					downName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
					response.setHeader("Content-Disposition", "attachment;filename=\"" + downName + "\".zip;");
				} else if (header.contains("MSIE") || header.contains("Trident")) { // IE 11버전부터 Trident로 변경되었기때문에 추가해준다.
					downName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
					response.setHeader("Content-Disposition", "attachment;filename=" + downName + ".zip;");
				} else if (header.contains("Chrome")) {
					downName = URLEncoder.encode(fileName, "UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + downName + "\".zip");
				} else if (header.contains("Opera")) {
					downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + downName + "\".zip");
				} else if (header.contains("Firefox")) {
					downName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
					response.setHeader("Content-Disposition", "attachment; filename=" + downName + ".zip");
				}
		 
		        fis = new FileInputStream(filepath);
		        bis = new BufferedInputStream(fis);
		        out = response.getOutputStream();
		        bos = new BufferedOutputStream(out);
		        
				byte[] data = new byte[2048];
				int input = 0;
				
				while ((input = bis.read(data)) != -1) {
					bos.write(data, 0, input);
					bos.flush();
				}
			} catch (ClientAbortException e) {
//				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != bos) {
					try {
						bos.close();
					} catch (Exception e) {
					}
				}
				if (null != bis) {
					try {
						bis.close();
					} catch (Exception e) {
					}
				}
				if (null != out) {
					try {
						out.close();
					} catch (Exception e) {
					}
				}
				if (null != fis) {
					try {
						fis.close();
					} catch (Exception e) {
					}
				}
				
		        if(type && file.exists()) {
		        	file.delete();
		        }
			}
		}
	}
}
