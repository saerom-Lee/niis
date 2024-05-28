package org.nii.niis.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.nii.niis.niim.service.BoardService;
import org.nii.niis.niim.service.impl.BoardServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.property.EgovPropertyService;

@Component("fileUtil")
public class FileUtil {
	
	Logger log = LogManager.getLogger(this.getClass());
	
	@Resource(name="propertiesService")
	protected EgovPropertyService propertiesService;
	@Resource(name="boardService")
	protected BoardService BoardService;
	
	/**
	 * 공통 파일 업로드
	 * @param mFile
	 * @param rootpath
	 * @param path
	 * @param isYear
	 * @param isRename
	 * @return String
	 * @throws Exception 
	 */
	public String uploadFile(MultipartHttpServletRequest request, MultipartFile mFile, String rootpath, String path, boolean isYear, boolean isRename) throws Exception{
//		BoardServiceImpl board = new BoardServiceImpl();
		String urlpath = BoardService.checkUrl();
		String realFileName = "";
		String filepath = "";
		try {
			String serverpath = propertiesService.getString("Globals.boardFilePath");
			//String serverpath = request.getSession().getServletContext().getRealPath("/").replaceAll("\\\\", "/");
		/*	filepath = ((null == rootpath || "".equals(rootpath)) ? propertiesService.getString("Globals.uploadPath") : rootpath)
					 + ((null == path || "".equals(path)) ? "" : path)
					 + (isYear ?  "/" + Calendar.getInstance().get(Calendar.YEAR) : "" );*/
			filepath = "/upload"+path+ (isYear ?  "/" + Calendar.getInstance().get(Calendar.YEAR) : "" );
			filepath = filepath.replaceAll("\\\\", "/");
			
			System.out.println("serverpath>>>>>>>>>>>"+serverpath);
			System.out.println("filepath>>>>>>>>>>>"+filepath);
			
			File file = new File(filePathBlackList(serverpath + filepath));
			
	        if(!file.exists()){
	            file.mkdirs();
	        }
	        
	        if(null != mFile && !mFile.isEmpty()){
	        	
	        	String originalFileName = "";
	        			
	        	originalFileName = mFile.getOriginalFilename();
	        	
	        	if(isRename){
    				String today = new SimpleDateFormat("yyyyMMddHH").format(Calendar.getInstance().getTime());
    				realFileName = today + "_" + originalFileName;
    			}else{
    				realFileName = originalFileName;
    			}
                System.out.println("mFile.getOriginalFilename()>>>"+mFile.getOriginalFilename());
                System.out.println("mFile.getContentType()>>>>"+mFile.getContentType());
                System.out.println("filepath>>>"+filepath);
                System.out.println("realFileName>>>>"+realFileName);
                
//                	file = new File(filePathBlackList(serverpath + filepath + "/" + realFileName));
                	file = new File(filePathBlackList(serverpath + filepath + "/" + urlpath));
	        			
				mFile.transferTo(file);
	        }
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		return filePathBlackList(filepath + "/" + realFileName);
		return filePathBlackList(filepath + "/" + urlpath);
	}
	
	/**
	 * 공통 파일 다운로드
	 * @param response
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> map) throws Exception {
		
		String userAgent = map.get("userAgent");
		//String filepath = propertiesService.getString("Globals.uploadFilePath") + map.get("realFileName");
		String serverpath = propertiesService.getString("Globals.boardFilePath");
		//String serverpath = request.getSession().getServletContext().getRealPath("/").replaceAll("\\\\", "/");
		String filepath = map.get("realFileName").replaceAll("\\\\", "/");
		
		System.out.println("userAgent>>>>>>>>>>>"+userAgent);
		System.out.println("serverpath>>>>>>>>>>>"+serverpath);
		System.out.println("filepath>>>>>>>>>>>"+filepath);
		
		File file = new File(serverpath + filepath);
		
		int fSize = (int) file.length();
		
		if (file == null || !file.exists() || file.length() <= 0 || file.isDirectory()){
			throw new FileNotFoundException(filepath);
		}else{
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			
			try{
				//사용자의 사용환경에 따른 한글 처리
				if(userAgent != null && userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
					response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(map.get("originalFileName"), "UTF-8") + ";");
				}else if(userAgent != null && (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1)) { // MS IE (보통은 6.x 이상 가정)
					response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(map.get("originalFileName"), "UTF-8") + ";");
				}else{ // 모질라나 오페라
					response.setHeader("Content-Disposition", "attachment; filename=" + new String(map.get("originalFileName").getBytes("utf-8"), "latin1") + ";");
				}
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				
				String mimetype = "application/octet-stream";
				response.setContentType(mimetype + "; charset=" + "utf-8");
				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setContentLength(fSize);
					
				FileCopyUtils.copy(bis, response.getOutputStream());
			}catch(IOException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				if(null != bis){
					try{ bis.close(); }catch(Exception e){ }
				}
				if(null != fis){
					try{ fis.close(); }catch(Exception e){ }
				}
				
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
	}
	
	/**
	 * 메타 파일 다운로드
	 * @param response
	 * @param map
	 * @return String
	 * @throws Exception
	 */
	public void downloadMetaFile(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> map) throws Exception {
		
		String userAgent = map.get("userAgent");
		String serverpath = "D:/METADATA/";
		String filepath = map.get("realFileName").replaceAll("\\\\", "/");
		
		System.out.println("userAgent>>>>>>>>>>>"+userAgent);
		System.out.println("serverpath>>>>>>>>>>>"+serverpath);
		System.out.println("filepath>>>>>>>>>>>"+filepath);
		
		File file = new File(serverpath + filepath);
		
		int fSize = (int) file.length();
		
		if (file == null || !file.exists() || file.length() <= 0 || file.isDirectory()){
			throw new FileNotFoundException(filepath);
		}else{
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			
			try{
				//사용자의 사용환경에 따른 한글 처리
				if(userAgent != null && userAgent.indexOf("MSIE 5.5") > -1) { // MS IE 5.5 이하
					response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(map.get("originalFileName"), "UTF-8") + ";");
				}else if(userAgent != null && (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1)) { // MS IE (보통은 6.x 이상 가정)
					response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(map.get("originalFileName"), "UTF-8") + ";");
				}else{ // 모질라나 오페라
					response.setHeader("Content-Disposition", "attachment; filename=" + new String(map.get("originalFileName").getBytes("utf-8"), "latin1") + ";");
				}
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				
				String mimetype = "application/octet-stream";
				response.setContentType(mimetype + "; charset=" + "utf-8");
				response.setBufferSize(fSize);
				response.setContentType(mimetype);
				response.setContentLength(fSize);
					
				FileCopyUtils.copy(bis, response.getOutputStream());
			}catch(IOException e) {
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				if(null != bis){
					try{ bis.close(); }catch(Exception e){ }
				}
				if(null != fis){
					try{ fis.close(); }catch(Exception e){ }
				}
				
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
	}
	
	/**
	 * 공통 파일 삭제
	 * @param filepath
	 */
	public void deleteFile(MultipartHttpServletRequest request, String filepath){
		
		try {
			String serverpath = request.getSession().getServletContext().getRealPath("/").replaceAll("\\\\", "/"); 
			File delFile = new File(serverpath + filepath.replaceAll("\\\\", "/"));
			
			if(delFile.exists()){
				System.out.println(delFile.getName() + " 삭제!!");
				delFile.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param value
	 * @return String
	 */
	public static String filePathBlackList(String value) {
		String returnValue = value;
		if (returnValue == null || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", ""); // ../
		returnValue = returnValue.replaceAll("\\.\\.\\\\", ""); // ..\

		return returnValue;
	}
	
	/**
	 * 공통 디렉토리 및 하위 파일 삭제
	 * @param dir
	 */
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
