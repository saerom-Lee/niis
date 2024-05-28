package org.nii.niis.watermark.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WaterMarkFileUploadController {
	protected Logger log = Logger.getLogger(this.getClass());
	
	FileOutputStream fos;

  	@RequestMapping(value="/waterMark/StoFileUpload.do")
  	public void uploadFilemap(HttpServletRequest request, HttpServletResponse response, @RequestParam("file")MultipartFile file, @RequestParam("filepath")String fpath, @RequestParam("filename")String fname,@RequestParam("osType")String osType ) throws IOException {    
  		
  		String result ="";
  		log.info(fpath);
  		if(osType.equals("UNIX")){
  			result = writeFileUnix(file,fpath,fname);
  		}
  		else if(osType.equals("WINDOW")){
  			result = writeFileWindow(file,fpath,fname);	
  		}
  		
  		PrintWriter out= response.getWriter();
  		log.info(fpath+"_"+fname+"_"+result);
  		out.print(result);
  	}
  	
  	
public String writeFileUnix(MultipartFile file, String path, String fileName){     
		
	File dir = new File(path);
	
	if(!dir.isDirectory()){
		if(!dir.mkdirs()){
			log.info("director create fail! plz check permission");
			return "ERR001";
		}
	}
	try{             
		
		byte fileData[] = file.getBytes();    	
		fos = new FileOutputStream(path + "/" + fileName); 
		fos.write(fileData); 
	}catch(Exception e){ 
		e.printStackTrace(); 
		return "ERR002";
	}finally{     
		if(fos != null){  
			try{  
				fos.close();   
			}catch(Exception e){
				return "ERR003";
			}    
		}    
	}// try end; 
	
	return "SUCCESS";
	}
    
public String writeFileWindow(MultipartFile file, String path, String fileName){     
	
	File dir = new File(path);

	if(!dir.isDirectory()){
		if(!dir.mkdirs()){
			log.info("director create fail! plz check permission");
			return "ERR001";
		}
	}
	try{             
		
		byte fileData[] = file.getBytes();    
		fos = new FileOutputStream(path + "\\" + fileName); 
		fos.write(fileData); 
	}catch(Exception e){ 
		e.printStackTrace(); 
		return "ERR002";
	}finally{     
		if(fos != null){  
			try{  
				fos.close();   
			}catch(Exception e){
				return "ERR003";
			}    
		}    
	}// try end; 
	
	return "SUCCESS";
}
 
			
	
	public String writeFile(MultipartFile file, String path, String fileName){     
		
		File dir = new File(path);

		if(!dir.isDirectory()){
			if(!dir.mkdirs()){
				log.info("director create fail! plz check permission");
				return "ERR001";
			}
		}
		try{             
			
			byte fileData[] = file.getBytes();    
			fos = new FileOutputStream(path + "\\" + fileName); 
			fos.write(fileData); 
		}catch(Exception e){ 
			e.printStackTrace(); 
			return "ERR002";
		}finally{     
			if(fos != null){  
				try{  
					fos.close();   
				}catch(Exception e){
					return "ERR003";
				}    
			}    
		}// try end; 
		
		return "SUCCESS";
	}
	
	

}